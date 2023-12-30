
package cn.northpark.service;

import cn.northpark.model.NotifyRemindB;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface NotifyRemindService {

    NotifyRemindB findNotifyRemind(Integer id);

    List<NotifyRemindB> findAll();

    void addNotifyRemind(NotifyRemindB notifyremind);

    boolean delNotifyRemind(Integer id);

    boolean updateNotifyRemind(NotifyRemindB notifyremind);

}


