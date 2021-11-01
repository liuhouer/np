
package cn.northpark.dao.impl;

import cn.northpark.dao.PoemDao;
import cn.northpark.model.Poem;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("PoemDao")
public class PoemDaoImpl extends HibernateDaoImpl<Poem, Serializable> implements PoemDao {

}