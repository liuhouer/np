package cn.northpark.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2023年10月10日 11:19:17
 */
public class EnvCfgUtil {

    public static synchronized String getValByCfgName(String vcCfgName){
        List<Map<String, Object>> query = NPQueryRunner.query("select vc_cfg_value from bc_env_cfg where vc_cfg_name = ? and c_status = 1", new MapListHandler(), vcCfgName);
        return CollectionUtils.isNotEmpty(query)?query.get(0).get("vc_cfg_value").toString(): "";
    }

}
