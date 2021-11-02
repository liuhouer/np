package cn.northpark.utils;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author bruce
 * @date 2021年11月02日 17:05:31
 */
public class NotifyUtil {

    public synchronized static Map<String, String> getObjectContent(String topic_type, Integer topic_id) {

        ConcurrentMap<String, String> map = Maps.newConcurrentMap();
//        1-碎碎念
//        2-图册
//        3-软件
//        4-电影
//        5-诗词秀
//        6-情商提升
//        7-赞助我们
//        8-学习
        try {

            if(topic_type.equals("1")){
                Map<String, Object> bc_note = NPQueryRunner.findById("bc_note", topic_id);
                map.put("title",bc_note.get("brief").toString());
                map.put("href","/note");
            }else if(topic_type.equals("2")){
                Map<String, Object> bc_lyrics = NPQueryRunner.findById("bc_lyrics", topic_id);
                map.put("title",bc_lyrics.get("title").toString());
                map.put("href","/love/"+bc_lyrics.get("titlecode")+".html");
            }else if(topic_type.equals("3")){
                Map<String, Object> bc_soft = NPQueryRunner.findById("bc_soft", topic_id);
                map.put("title",bc_soft.get("title").toString());
                map.put("href","/soft/"+bc_soft.get("retcode")+".html");
            }else if(topic_type.equals("4")){
                Map<String, Object> bc_soft = NPQueryRunner.findById("bc_movies", topic_id);
                map.put("title",bc_soft.get("moviename").toString());
                map.put("href","/movies/post-"+topic_id+".html");
            }else if(topic_type.equals("6")){
                Map<String, Object> bc_eq = NPQueryRunner.findById("bc_eq", topic_id);
                map.put("title",bc_eq.get("title").toString());
                map.put("href","/romeo/"+topic_id+".html");
            }else if(topic_type.equals("7")){
                map.put("title","赞助本站");
                map.put("href","/donate");
            }else if(topic_type.equals("8")){
                Map<String, Object> bc_learn = NPQueryRunner.findById("bc_knowledge", topic_id);
                map.put("title",bc_learn.get("title").toString());
                map.put("href","/learning/post-"+topic_id+".html");
            }

        }catch (Exception ig){
            map.put("title","");
            map.put("href","");
        }


        return map;
    }
}
