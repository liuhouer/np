package cn.northpark.dao;

import cn.northpark.model.Poem;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface PoemDao extends HibernateDao<Poem, Serializable> {

}