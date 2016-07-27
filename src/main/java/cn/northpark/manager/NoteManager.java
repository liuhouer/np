
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Note;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

public interface NoteManager {
	
	public Note findNote(Integer id);

	public List<Note> findAll();

	public void addNote(Note note);

	public boolean delNote(Integer id);

	public boolean updateNote(Note note);
	
	public QueryResult<Note> findByCondition(PageView<Note> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Note> findByCondition(
			String wheresql);
	
	
	public PageView<List<Map<String, Object>>> findmixByCondition(String currentpage,String wheresql) ;
	
	//只获取分页信息
	public PageView<List<Map<String, Object>>> findmixPageByCondition(String currentpage,String wheresql) ;

	public int findmixCount(String whereSql);
	
	/**
	 * 根据sql语句查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public int countSql(String sql) ;
	
	
	/**
	 * 根据实体查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public  int countHql(Note note,String wheresql);
	
	/**
	 * sql
	 * @return
	 */
	public List<Note> querySql(String sql);
	

	
}


