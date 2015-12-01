
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.NoteQuery;
import com.bruce.query.condition.NoteQueryCondition;
@Service("NoteQuery")

public class NoteQueryImpl implements NoteQuery {

	@Override
	public String getSql(NoteQueryCondition noteQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
		 if(StringUtils.isNotEmpty(noteQueryCondition.getOpened())){
	        	sql.append(" and opened = '");
				sql.append(noteQueryCondition.getOpened());
				sql.append("' ");
	        }
		 if(StringUtils.isNotEmpty(noteQueryCondition.getUserid())&&!noteQueryCondition.getUserid().equals("null")){
	        	sql.append(" and userid = '");
				sql.append(noteQueryCondition.getUserid());
				sql.append("' ");
	        }
        
		return sql.toString();
	}

}



