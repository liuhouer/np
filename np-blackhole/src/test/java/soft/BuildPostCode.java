package soft;

import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.SoftUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2024年01月12日 20:28:28
 */
@Slf4j
public class BuildPostCode {
    public static void main(String[] args) {
        //更新软件的请求码为软件名[不含版本号]
        List<Map<String, Object>> mapList = NPQueryRunner.query("select id,ret_code,title,path from bc_soft", new MapListHandler());
        for (int i = 0; i < mapList.size(); i++) {
            String title = mapList.get(i).get("title").toString();
            Object id = mapList.get(i).get("id");
            if(StringUtils.isNotBlank(title)) {
                String postCode = SoftUtils.buildPostCode(title);
                if(StringUtils.isNotBlank(postCode)){
                    log.info(title+"------------>"+postCode);
                    String upSQL = "update bc_soft set ret_code = ? where id =?";
                    NPQueryRunner.update(upSQL,postCode,id);
                }
            }
        }
    }
}
