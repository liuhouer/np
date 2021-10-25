
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.model.Knowledge;
import cn.northpark.dao.KnowledgeDao;
/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Service("KnowledgeDao")
public class KnowledgeDaoImpl extends HibernateDaoImpl<Knowledge, Serializable> implements KnowledgeDao {

}