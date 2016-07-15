
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.NoteDao;
import cn.northpark.manager.NoteManager;
import cn.northpark.model.Note;
import cn.northpark.model.User;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

@Service("NoteManager")
public class NoteManagerImpl implements NoteManager {

    @Autowired
	private NoteDao noteDao;

	@Override
	public Note findNote(Integer id) {
		return noteDao.find(id);
	}

	@Override
	public List<Note> findAll() {
		return noteDao.findAll();
	}

	@Override
	public void addNote(Note note) {
		noteDao.save(note);
	}

	@Override
	public boolean delNote(Integer id) {
		Note note = noteDao.find(id);
		noteDao.delete(note);
		return true;
	}

	@Override
	public boolean updateNote(Note note) {
		noteDao.update(note);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Note> findByCondition(PageView<Note> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = noteDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Note> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = noteDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public PageView<List<Map<String, Object>>> findmixByCondition(String currentpage,String wheresql) {
		// TODO Auto-generated method stub
		
		PageView<List<Map<String, Object>>> pageview = setPageviewParam(
				currentpage, wheresql);
		
		
		pageview = noteDao.QuerySQLForMapList(wheresql, pageview);
		return pageview;
		
	}

	/**
	 * @param currentpage
	 * @param wheresql
	 * @return
	 * @throws NumberFormatException
	 */
	private PageView<List<Map<String, Object>>> setPageviewParam(
			String currentpage, String wheresql) throws NumberFormatException {
		String sql = wheresql;
		
		//设置pageview参数
		PageView<List<Map<String, Object>>> pageview=new PageView<List<Map<String, Object>>>();
		int pages = 0; // 总页数
		int n =  noteDao.countSql(sql);
		int maxresult = pageview.getMaxresult();
		/** 每页显示记录数 **/
		if (n % maxresult == 0) {
			pages = n / maxresult;
		} else {
			pages = n / maxresult + 1;
		}
		if (StringUtils.isEmpty(currentpage)) {
			currentpage = "0";
		} else {
            try {//胡乱把currentpage写成英文，捕捉异常
            	if (Integer.parseInt(currentpage) >= pages) {
    				currentpage = pages - 1 +"";
    			}
    			if (Integer.parseInt(currentpage) < 0) {
    				currentpage = "0";
    			}
			} catch (Exception e) {
				// TODO: handle exception
				currentpage = "0";
			}
			
		}
		int startindex = Integer.parseInt(currentpage) * maxresult;
		int endindex = startindex + maxresult - 1;
		pageview.setStartindex(startindex);
		pageview.setEndindex(endindex);
		pageview.setTotalrecord(n);
		pageview.setCurrentpage(Integer.parseInt(currentpage));
		pageview.setTotalpage(pages);
		pageview.setMaxresult(maxresult);
		//设置pageview参数 end
		
		
		
		return pageview;
	}

	@Override
	public int findmixCount(String whereSql) {
		// TODO Auto-generated method stub
		return noteDao.querySql(whereSql).size();
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.NoteManager#countSql(java.lang.String)
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return noteDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.NoteManager#countHql(cn.northpark.model.User, java.lang.String)
	 */
	@Override
	public int countHql(Note note, String wheresql) {
		// TODO Auto-generated method stub
		return noteDao.countHql(note.getClass(), wheresql);
	}

	@Override
	public PageView<List<Map<String, Object>>> findmixPageByCondition(
			String currentpage, String wheresql) {
		// TODO Auto-generated method stub
		return setPageviewParam(currentpage, wheresql);
	}
}

