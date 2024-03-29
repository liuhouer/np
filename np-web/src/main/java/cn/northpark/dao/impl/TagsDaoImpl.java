
package cn.northpark.dao.impl;

import cn.northpark.dao.TagsDao;
import cn.northpark.model.Tags;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("TagsDao")
public class TagsDaoImpl extends HibernateDaoImpl<Tags, Serializable> implements TagsDao {

}