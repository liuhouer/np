package cn.northpark.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bruce
 * @date 2024年01月12日 20:14:27
 */
@Slf4j
public class SoftUtils {

    /**
     * 根据标题解析出真实的软件名
     *
     * FxFactory 4.0.2完美破解版->FxFactory,
     * Final Cut Pro X 10.0.9更新->Final Cut Pro X,
     * iMovie视频剪辑软件->iMovie,
     * 百度音乐 Mac版->百度音乐,
     * 网易云音乐 for Mac->网易云音乐
     * @param softwareTitle
     * @return
     */
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

    /**
     * 根据软件名称构建请求码
     * @param softwareTitle 
     * @return
     */
    public static String buildPostCode(String softwareTitle) {
        String buildCode = extractSoftwareName(softwareTitle).replace(" ", "-").replace(":","").replace(".","-").replace(",","").replace("-–-","-").replace("!","").replace("——","-").replace("&","");
        log.info("根据软件名称构建请求码["+softwareTitle+"]"+"---->"+buildCode);
        return buildCode;
    }

    /**
     * 根据软件码构建历史搜索码
     * @param retCode
     * @return
     */
    public static String buildMergeSearchTile(String retCode) {
        return retCode.replace("-"," ");
    }

    public static void main(String[] args) {
        buildPostCode("Final Cut Pro X 10.0.9更新");
        buildPostCode("百度音乐 Mac版");
    }
}
