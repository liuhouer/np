
package cn.northpark.query.impl;

import org.springframework.stereotype.Service;

import cn.northpark.query.VpsQuery;
import cn.northpark.query.condition.VpsQueryCondition;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("VpsQuery")
public class VpsQueryImpl implements VpsQuery {

    @Override
    public String getSql(VpsQueryCondition vpsQueryCondition) {
        StringBuilder sql = new StringBuilder(" where 1=1");


        return sql.toString();
    }

    @Override
    public String getMixSql(VpsQueryCondition condition) {
        // TODO Auto-generated method stub
        StringBuilder sql = new StringBuilder(" where 1=1");


        return sql.toString();
    }

}



