package cn.northpark.test.ret;

import cn.northpark.utils.NPQueryRunner;
import com.google.common.base.Splitter;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2022年08月25日 13:26:51
 */
public class HandleRetCode {
    public static void main2(String[] args) {

        List<Map<String, Object>> list = NPQueryRunner.query("select * from bc_soft where LENGTH(ret_code) >40 order by id desc",new MapListHandler());


        for (Map<String, Object> map : list) {
            String ret_code = map.get("ret_code").toString();
            String id = map.get("id").toString();
            List<String> strings = Splitter.on("-").omitEmptyStrings().splitToList(ret_code);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < strings.size(); i++) {
                if(i<(strings.size()/2)){
                    sb.append(strings.get(i)).append("-");
                }

            }

            if(sb.toString().endsWith("and-")){
                sb = sb.replace(sb.length()-4,sb.length(),"");
            }
            sb.append(strings.get(strings.size()-1));

            System.err.println(strings);
            System.err.println(sb);
            System.err.println("=========================");

            //NPQueryRunner.update("update bc_soft set ret_code =? where id = ?",sb.toString(),id);
        }

    }


    public static void main(String[] args) {

        List<Map<String, Object>> list = NPQueryRunner.query("select id,ret_code from bc_soft where ret_code not like 'post-%' order by id desc",new MapListHandler());


        for (Map<String, Object> map : list) {
            String id = map.get("id").toString();

            StringBuilder sb = new StringBuilder();

            sb.append("post-");

            sb.append(id);

            System.err.println(sb);
            System.err.println("=========================");

            NPQueryRunner.update("update bc_soft set ret_code =? where id = ?",sb.toString(),id);
        }

    }
}
