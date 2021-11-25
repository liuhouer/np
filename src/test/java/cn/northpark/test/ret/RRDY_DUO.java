package cn.northpark.test.ret;

import cn.northpark.utils.*;
import cn.northpark.utils.encrypt.MD5Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bruce
 * @date 2021年11月25日 13:36:25
 */
@Slf4j
public class RRDY_DUO {
    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1800));
        //21-144
        //1680
        //29
        //200-

        for (int i = 75; i <= 1757; i++) {
            trans10DatasTask myTask = new trans10DatasTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完成别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();


    }




    /**
     * 执行一页的爬取
     */
    static class trans10DatasTask implements Runnable {
        private int taskNum;

        public trans10DatasTask(int index) {
            this.taskNum = index;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);

            //====================执行逻辑=====================

            //17579
            //0*10,10
            //1*10,10
            //...
            //1757*10,10

            int start_index = taskNum * 10;

            String sql = "select * from yyets order by id asc limit "+ start_index +",10 ";
            List<Map<String, Object>> maps = LocalQueryRunner.query(sql, new MapListHandler());

            System.err.println("====================================");

            List<Map<String, String>> list = Lists.newArrayList();
            //拿到集合并行更新
            maps.stream().parallel().unordered().forEach(item -> {
                Map<String, String> map = Maps.newConcurrentMap();

                //基本信息获取
                Object cnname = item.get("cnname");
                Object enname = item.get("enname");
                Object aliasname = item.get("aliasname");

                String dataStr = item.get("data").toString();
                Map<String, Object> dataMap = JsonUtil.json2map(dataStr);

                String data_inner = dataMap.get("data").toString();

                Map<String, Object> data_inner_map = JsonUtil.json2map(data_inner);


                //"info": {
                //		"area": "美国",
                //		"show_type": "",
                //		"aliasname": "",
                //		"year": [2012],
                //		"cnname": "星际旅行 动画版",
                //		"expire": "1610393635",
                //		"channel": "tv",
                //		"id": 26486,
                //		"channel_cn": "美剧",
                //		"views": 6,
                //		"enname": "Star Trek The Animated"
                //	}
                String d_info = data_inner_map.get("info").toString();
                Map<String, Object> d_info_map = JsonUtil.json2map(d_info);
                Object area = d_info_map.get("area");
                Object year = d_info_map.get("year");
                Object channel_cn = d_info_map.get("channel_cn");
                String expire_date = TimeUtils.nowdate();
                if (Objects.nonNull(d_info_map.get("expire"))) {
                    //timestamp
                    expire_date = TimeUtils.getHalfDate(TimeUtils.Timestamp2DateStr(Long.parseLong(d_info_map.get("expire").toString())));
                }

                //下载资源解析
                StringBuilder sb_down = new StringBuilder();
                List<Map<String, Object>> listMap = JsonUtil.json2ListMap(data_inner_map.get("list").toString());
                for (Map<String, Object> item_ : listMap) {
                    //不同格式列表
                    JSONArray formats = JSON.parseArray(item_.get("formats").toString());
                    Object down_name = item_.get("season_cn");
                    sb_down.append("<div class='left'>");
                    sb_down.append("<p>").append(down_name).append("</p>");
                    Map<String, Object> formatsMap = JsonUtil.json2map(item_.get("items").toString());
                    for (Object format : formats) {

                        sb_down.append("<p>").append("<bold class='text-danger'>").append(format).append("</bold>").append("</p>");
                        List<Map<String, Object>> format_downs = JsonUtil.json2ListMap(formatsMap.get(format).toString());

                        for (Map<String, Object> format_down : format_downs) {
                            Object name = format_down.get("name");
                            Object size = format_down.get("size");
                            sb_down.append("<p>").append(name).append("-").append(size).append("</p>");

                            if (Objects.isNull(format_down.get("files"))) {
                                continue;
                            }
                            JSONArray files = JSON.parseArray(format_down.get("files").toString());

                            for (Object obj : files) {
                                //"address": "magnet:?xt=urn:btih:HFS7OB6KVE452C4LTCJ6H6AIOHCKPION&tr.0=http://idowns.org:6969/announce&tr.1=http://idowns.org:2710/announce&tr.2=http://tracker.openbittorrent.com/announce&tr.3=udp://tracker.openbittorrent.com:80/announce&tr.4=http://tracker.thepiratebay.org/announce&tr.5=http://tracker.publicbt.com/announce",
                                //			"passwd": "",
                                //			"way_cn": "磁力",
                                //			"way": "2"
                                Map<String, Object> file = JsonUtil.json2map(obj.toString());
                                Object address = file.get("address");
                                Object passwd = file.get("passwd");
                                Object way_cn = file.get("way_cn");

                                if (Objects.nonNull(way_cn) && StringUtils.isNotEmpty(way_cn.toString())) {
                                    sb_down.append("<p>").append("下载方式：")
                                            .append("<a href='"+address+"' target='_blank'>")
                                            .append("<span class='text-primary'>").append(way_cn).append("</span>").append("</p>")
                                            .append("</a>");
                                }
                                if (Objects.nonNull(passwd) && StringUtils.isNotEmpty(passwd.toString())) {
                                    sb_down.append("<p>").append("密码：").append(passwd).append("</p>");
                                }


                            }

                            //{
                            //		"itemid": "65051",
                            //		"size": "5GB",
                            //		"dateline": "1336855335",
                            //		"yyets_trans": 0,
                            //		"name": "危情谍战.Knight.and.Day.2010.Bluray.720p.DTS.x264-CHD.mkv  5 GB",
                            //		"files": [{
                            //			"address": "ed2k://|file|[危情谍战].Knight.and.Day.2010.Bluray.720p.DTS.x264-CHD.mkv|5368388375|08c33e07c02998268a450b0a45fb45e1|h=kuavcoflnpjnjwfq7uh7okmcovlhihwi|/",
                            //			"passwd": "",
                            //			"way_cn": "电驴",
                            //			"way": "1"
                            //		}],
                            //		"episode": "0"
                        }
                    }

                }

                sb_down.append("</div>");
                System.err.println(sb_down.toString());
                System.err.println("**************************************");


                String title = cnname + "-" + enname;
                String tag = channel_cn + "," + area + "," + year;
                StringBuilder sb_article = new StringBuilder();
                //"info": {
                //                //		"area": "美国",
                //                //		"show_type": "",
                //                //		"aliasname": "",
                //                //		"year": [2012],
                //                //		"cnname": "星际旅行 动画版",
                //                //		"expire": "1610393635",
                //                //		"channel": "tv",
                //                //		"id": 26486,
                //                //		"channel_cn": "美剧",
                //                //		"views": 6,
                //                //		"enname": "Star Trek The Animated"
                //                //	}
                sb_article.append("<p>").append("中文名：").append(cnname).append("</p>");
                sb_article.append("<p>").append("英文名：").append(enname).append("</p>");
                sb_article.append("<p>").append("别名：").append(aliasname).append("</p>");
                sb_article.append("<p>").append("地区：").append(area).append("</p>");
                sb_article.append("<p>").append("年份：").append(year).append("</p>");
                sb_article.append("<p>").append("收录时间：").append(expire_date).append("</p>");
                map.put("title", title);
                map.put("a_url", "");
                map.put("date", expire_date);
                map.put("article", sb_article.toString());
                map.put("ret_code", MD5Utils.encrypt(title, MD5Utils.MD5_KEY));
                map.put("tag", tag);
                map.put("tag_code", PinyinUtil.paraseStringToPinyin(tag).toLowerCase());
                map.put("path", sb_down.toString());

                list.add(map);

            });


            //每页请求一次新增数据
            String jsonData = JsonUtil.object2json(list);
            log.info("爬取的数据----》,{}", jsonData);

            String url = "https://northpark.cn/ret/movies/data";
            try {
                HttpGetUtils.sendPostJsonData(url, jsonData);
            } catch (ClientProtocolException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
                log.info("第" + taskNum + "页===OVER================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //====================执行逻辑====================================

            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}
