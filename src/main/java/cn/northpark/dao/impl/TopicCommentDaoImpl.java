
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.model.TopicComment;
import cn.northpark.dao.TopicCommentDao;
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