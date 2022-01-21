
package cn.northpark.query;

import cn.northpark.query.condition.NoteQueryCondition;

public interface NoteQuery {
   String getSql(NoteQueryCondition noteQueryCondition);

   String getMixSql(NoteQueryCondition condition);

   String getRandSql(NoteQueryCondition condition);
}
