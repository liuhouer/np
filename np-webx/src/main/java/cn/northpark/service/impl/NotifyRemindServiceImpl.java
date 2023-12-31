
package cn.northpark.service.impl;

import cn.northpark.mapper.NotifyRemindBMapper;
import cn.northpark.model.NotifyRemindB;
import cn.northpark.service.NotifyRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Service(value = "notifyRemindService")
public class NotifyRemindServiceImpl implements NotifyRemindService {

    @Autowired
	NotifyRemindBMapper notifyMapper;

	@Override
	public NotifyRemindB findNotifyRemind(Integer id) {
		return notifyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<NotifyRemindB> findAll() {
		return notifyMapper.selectAll();
	}

	@Override
	@Transactional
	public void addNotifyRemind(NotifyRemindB notifyremind) {
		notifyMapper.insert(notifyremind);
	}

	@Override
	public boolean delNotifyRemind(Integer id) {
		notifyMapper.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public boolean updateNotifyRemind(NotifyRemindB notifyremind) {
		notifyMapper.updateByPrimaryKey(notifyremind);
		return true;
	}

	@Override
	public List<NotifyRemindB> findByCondition(String where_sql, String orderBy) {
		return notifyMapper.findByCondition(where_sql,orderBy);
	}

}

