
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.model.NotifyRemind;
import cn.northpark.dao.NotifyRemindDao;
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