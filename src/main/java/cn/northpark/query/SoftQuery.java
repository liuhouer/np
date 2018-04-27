
package cn.northpark.query;

import cn.northpark.query.condition.SoftQueryCondition;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

public interface SoftQuery {
    public String getSql(SoftQueryCondition softQueryCondition);
}
