
package cn.northpark.query;

import cn.northpark.query.condition.NoteQueryCondition;

public interface NoteQuery {
    public String getSql(NoteQueryCondition noteQueryCondition);

    public String getMixSql(NoteQueryCondition condition);
}
