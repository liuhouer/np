
package cn.northpark.dao.impl;

import cn.northpark.dao.PoemEnjoyDao;
import cn.northpark.model.PoemEnjoy;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("PoemEnjoyDao")
public class PoemEnjoyDaoImpl extends HibernateDaoImpl<PoemEnjoy, Serializable> implements PoemEnjoyDao {

}