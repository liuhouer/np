package soft;

import cn.northpark.utils.NPQueryRunner;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bruce
 * @date 2024年01月02日 17:34:06
 */
@Slf4j
public class HandleSimilarSoft {
    public static void main(String[] args) {
        List<Map<String, Object>> mapList = NPQueryRunner.query("select id,ret_code,title,path from bc_soft", new MapListHandler());
        // 使用HashMap保存软件名称和下载地址
        HashMap<String, List<Map<String,Object>>> softwareMap = new HashMap<>();

        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map1 = mapList.get(i);
            String title = (String) map1.get("title");
            String softwareName = extractSoftwareName(title);

            if(StringUtils.isNotBlank(softwareName)){
                // 检查HashMap中是否已存在该软件的记录
                if (softwareMap.containsKey(softwareName)) {
                    // 如果存在，将下载地址追加到已有记录的下载地址字段
                    CopyOnWriteArrayList<Map<String, Object>> maps = new CopyOnWriteArrayList<>();
                    maps.addAll(softwareMap.get(softwareName));
                    maps.add(map1);
                    softwareMap.put(softwareName, maps);
                } else {
                    // 如果不存在，添加新的记录到HashMap
                    softwareMap.put(softwareName, Arrays.asList(map1));
                }
            }

//            log.info("softwareMap-----,{}",softwareMap);
        }


        // 输出 Redis 中的软件信息
        for (Map.Entry<String, List<Map<String, Object>>> entry : softwareMap.entrySet()) {
            String softwareName = entry.getKey();
            log.info("softwareName-----,{}",softwareName);
            List<Map<String, Object>> softwareList = entry.getValue();
            log.info("softwareList-----,{}",softwareList);
            Long maxId = softwareList.stream().mapToLong(t -> Long.parseLong(t.get("id").toString())).max().getAsLong();
            List<Long> otherIds  = softwareList.stream().filter(t -> {
                long id = Long.parseLong(t.get("id").toString());
                return !maxId.equals(id);
            }).mapToLong(t -> Long.parseLong(t.get("id").toString())).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//            for (Map<String, Object> software : softwareList) {
//                String downloadLink = (String) software.get("path");
//                System.out.println("下载地址: " + downloadLink);
//            }

            if(CollectionUtils.isNotEmpty(otherIds)) {
                String insSQL = "insert into bc_soft_merge_drop select * from bc_soft where id in (" + Joiner.on(",").join(otherIds) + ")";
                NPQueryRunner.insert(insSQL);
            }
        }

//        Jedis jedis = null;
//        try {
//            jedis = RedisGPTUtil.getJedis();
//            jedis.set("softwareMap", JsonUtil.object2json(softwareMap));
//
//
//            // 从 Redis 中获取存储的 JSON 字符串
//            String retrievedJson = jedis.get("softwareMap");
//
//            // 将 JSON 字符串转换回 softwareMap
//            Map<String, Object> retrievedMap =
//                    JsonUtil.json2map(retrievedJson);
//
//            // 输出 Redis 中的软件信息
//            for (Map.Entry<String, Object> entry : retrievedMap.entrySet()) {
//                String softwareName = entry.getKey();
//                List<Map<String, Object>> softwareList = JsonUtil.json2ListMap(JsonUtil.object2json(entry.getValue()));
//                System.out.println("软件名称: " + softwareName);
//                for (Map<String, Object> software : softwareList) {
//                    String downloadLink = (String) software.get("path");
//                    System.out.println("下载地址: " + downloadLink);
//                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            log.error("set 出错", e);
//        } finally {
//            jedis.close();
//        }





    }

    public static String extractSoftwareName(String softwareTitle) {
        // 规则1: 从开头往后找到数字为止
        String regex1 = "^.*?(?=\\d)";
        // 规则2: 从开头往后找到中文为止
        String regex2 = "^.*?(?=[\\u4e00-\\u9fa5])";
        // 规则3: 开头是中文的，往后找到Mac版、for Mac或者数字或者英文空格为止
        String regex3 = "^[\\u4e00-\\u9fa5].*?(?=(Mac版|for Mac|\\d|[A-Za-z ]))";

        String softwareName = "";

        // 根据规则提取软件名称
        Pattern pattern1 = Pattern.compile(regex1);
        if(Objects.isNull(pattern1) || StringUtils.isBlank(softwareTitle)){return "";}
        Matcher matcher1 = pattern1.matcher(softwareTitle);
        if (matcher1.find()) {
            softwareName = matcher1.group().trim();
        } else {
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(softwareTitle);
            if (matcher2.find()) {
                softwareName = matcher2.group().trim();
            } else {
                Pattern pattern3 = Pattern.compile(regex3);
                Matcher matcher3 = pattern3.matcher(softwareTitle);
                if (matcher3.find()) {
                    softwareName = matcher3.group().trim();
                }
            }
        }

        return softwareName;
    }


}
