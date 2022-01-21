
package cn.northpark.dao.impl;

import cn.northpark.dao.NotifyRemindDao;
import cn.northpark.model.NotifyRemind;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Service("NotifyRemindDao")
public class NotifyRemindDaoImpl extends HibernateDaoImpl<NotifyRemind, Serializable> implements NotifyRemindDao {

}