package soft;

import cn.northpark.utils.NPQueryRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2024年01月12日 20:53:28
 */
@Slf4j
public class BuildEndfixPostCode {
    public static void main(String[] args) {
        //更新软件的请求码为软件名[不含版本号]
        List<Map<String, Object>> mapList = NPQueryRunner.query("select id,ret_code,title,path from bc_soft where " +
                "(ret_code like '%\\-for' " +
                "or ret_code like '%\\-for\\-Mac'  )", new MapListHandler());

        log.info("mapList--"+mapList.size());
        for (int i = 0; i < mapList.size(); i++) {
            String ret_code = mapList.get(i).get("ret_code").toString();
            Object id = mapList.get(i).get("id");
            if(StringUtils.isNotBlank(ret_code)) {
                ret_code = ret_code.replace("-for","").replace("-for-Mac","");
                    log.info(ret_code+"------------>"+ret_code);
                    String upSQL = "update bc_soft set ret_code = ? where id =?";
                    NPQueryRunner.update(upSQL,ret_code,id);
            }
        }
    }
}
