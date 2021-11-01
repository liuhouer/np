
package cn.northpark.dao.impl;

import cn.northpark.dao.TopicCommentDao;
import cn.northpark.model.TopicComment;
import org.springframework.stereotype.Service;

import java.io.Serializable;
/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Service("TopicCommentDao")
public class TopicCommentDaoImpl extends HibernateDaoImpl<TopicComment, Serializable> implements TopicCommentDao {

}