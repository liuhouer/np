
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

	@Override
	public String getMixSql(NoteQueryCondition condition) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT a.id as noteid,a.brief as brief ,a.note as note,a.opened as openid,a.createtime as createtime,a.userid as userid,b.username as username,b.headpath as headpath ,b.email as email "
					+" FROM                                   	"
					+" bc_note a                              	"
					+" inner JOIN bc_user  b on a.userid = b.id where 1=1 ");
		
		 if(StringUtils.isNotEmpty(condition.getOpened())){
	        	sql.append(" and a.opened = '");
				sql.append(condition.getOpened());
				sql.append("' ");
	        }
		 sql.append(" order by a.createtime desc ");
		return sql.toString();
	}

}



