
package cn.northpark.query;

import cn.northpark.query.condition.PoemEnjoyQueryCondition;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

public interface PoemEnjoyQuery {
    public String getSql(PoemEnjoyQueryCondition poemenjoyQueryCondition);
}
