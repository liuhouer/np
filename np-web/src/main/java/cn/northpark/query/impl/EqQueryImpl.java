
package cn.northpark.query.impl;

import cn.northpark.query.EqQuery;
import cn.northpark.query.condition.EqQueryCondition;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("EqQuery")
public class EqQueryImpl implements EqQuery {

    @Override
    public String getSql(EqQueryCondition eqQueryCondition) {
        StringBuilder sql = new StringBuilder(" where 1=1 ");


        return sql.toString();
    }

}



