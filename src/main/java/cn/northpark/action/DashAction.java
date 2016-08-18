
package cn.northpark.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.EqManager;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.NoteManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.model.Eq;
import cn.northpark.model.Movies;
import cn.northpark.query.NoteQuery;
import cn.northpark.query.condition.NoteQueryCondition;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.JedisUtil;
import cn.northpark.utils.PageView;
import cn.northpark.utils.SerializationUtil;
import cn.northpark.utils.TimeUtils;


/**
 * @author zhangyang
 * 处理首页的内容
 * 所有内容
 * 从redis缓存获取
 */
@Controller
@RequestMapping("")
public class DashAction {

	 @Autowired	
	 private UserLyricsManager userlyricsManager;
	 @Autowired	
	 private NoteManager noteManager;
	 @Autowired	
	 private NoteQuery noteQuery;
	 @Autowired	
	 private EqManager eqManager;
	 @Autowired	
	 private MoviesManager moviesManager;

	 
	 
	    /**
		 *首页
		 */
		@RequestMapping("/")
		public String dashborard(HttpServletRequest request,HttpServletResponse response, ModelMap map) throws Exception {
	 	  	
			//slider
			request.getSession().removeAttribute("tabs");
			
			//if www return no www
			String getDoamin = request.getServerName();
			if(getDoamin.startsWith("www")){
				response.sendRedirect(BC_Constant.Domain);
			}
		    
			return "/dashboard";
		 	  	
		}	
		
		
		
				//异步获取首页的love数据 从redis缓存！！！
				@RequestMapping(value="/dash/getLove")
				public String getLove(ModelMap map) {
					
					 byte[] b = JedisUtil.getListByte("lovelist");
					 List<Map<String, Object>> lovelist = (List<Map<String, Object>>) SerializationUtil.deserialize(b);
					 if(CollectionUtils.isEmpty(lovelist)){
						 //查询数据库并且放到缓存
						 pushLove2Redis(map);
					 }else{
						 map.addAttribute("lovelist", lovelist==null?"":lovelist);
					 }
					 
					
					
					return "/page/love/lovedata";
				}
				
				//异步获取首页的《碎碎念》数据 从redis缓存！！！
				@RequestMapping(value="/dash/getNote")
				public String getNote(ModelMap map) {
					
					 byte[] b = JedisUtil.getListByte("notelist");
					 List<Map<String, Object>> notelist = (List<Map<String, Object>>) SerializationUtil.deserialize(b);
					 if(CollectionUtils.isEmpty(notelist)){
						 //查询数据库并且放到缓存
						  pushNote2Redis(map);
							
					 }else{
						 map.addAttribute("notelist", notelist==null?"":notelist);
					 }
					 
					
					
					return "/page/dash/notedata";
				}
				
				//异步获取首页的《情圣》数据 从redis缓存！！！
				@RequestMapping(value="/dash/getRomeo")
				public String getRomeo(ModelMap map) {
					
					 byte[] b = JedisUtil.getListByte("eqlist");
					 List<Eq>  eqlist = (List<Eq>) SerializationUtil.deserialize(b);
					 if(CollectionUtils.isEmpty(eqlist)){
						 //查询数据库并且放到缓存
						  pushEQ2Redis(map);
							
							
							
					 }else{
						 map.addAttribute("eqlist", eqlist==null?"":eqlist);
					 }
					 
					
					
					return "/page/dash/romeodata";
				}
				
				//异步获取首页的《电影》数据 从redis缓存！！！
				@RequestMapping(value="/dash/getMovies")
				public String getMovies(ModelMap map) {
					
					 byte[] b = JedisUtil.getListByte("movieslist");
					 List<Movies>  movieslist = (List<Movies>) SerializationUtil.deserialize(b);
					 if(CollectionUtils.isEmpty(movieslist)){
						 //查询数据库并且放到缓存
						 
						pushMovies2Redis(map);
							
							
							
					 }else{
						 map.addAttribute("movieslist", movieslist==null?"":movieslist);
					 }
					 
					
					
					return "/page/dash/moviesdata";
				}



				/**
				 * @param map
				 */
				public void pushMovies2Redis(ModelMap map) {
					//取出热门电影
						String msql = "select * from bc_movies order by id desc limit 1,50";
						List<Movies> movieslist = moviesManager.querySql(msql);
						JedisUtil.addList("movieslist", movieslist==null?new ArrayList<>():movieslist);
						map.addAttribute("movieslist", movieslist==null?"":movieslist);
				}



				/**
				 * @param map
				 */
				public void pushEQ2Redis(ModelMap map) {
					//取出一部分情圣日记
						
						String eqsql = "select * from bc_eq  where date ='2016-07-21' or date ='2016-07-19' or date ='2016-07-15' order by date desc";
						List<Eq> eqlist = this.eqManager.querySql(eqsql);
						
						JedisUtil.addList("eqlist", eqlist==null?new ArrayList<>():eqlist);
						map.addAttribute("eqlist", eqlist==null?"":eqlist);
				}



				/**
				 * 把首页的日记数据查询出来并且添加到缓存里去
				 * @param map
				 */
				public void pushNote2Redis(ModelMap map) {
					//取出一部分日记
						
						NoteQueryCondition notecondition = new NoteQueryCondition();
						notecondition.setOpened("yes");
						String noteSql = noteQuery.getMixSql(notecondition);
						noteSql =  noteSql.replace("order by a.createtime desc", "order by a.id ");
						PageView<List<Map<String, Object>>> notepageView = this.noteManager.findmixByCondition("0",noteSql);
						List<Map<String, Object>> notelist = notepageView.getMapRecords();
						
						for (int i = 0; i < notelist.size(); i++) {
							//时间处理
							String createtime = (String) notelist.get(i).get("createtime"); //e:/yunlu/upload/1399976848969.jpg
							if(StringUtils.isNotEmpty(createtime)){
								createtime = TimeUtils.getHalfDate(createtime);
							}
							notelist.get(i).put("createtime", createtime);
							
						}
						
						JedisUtil.addList("notelist", notelist==null?new ArrayList<>():notelist);
						
						map.addAttribute("notelist", notepageView.getMapRecords()==null?"":notelist);
				}



				/**
				 * 把首页的love数据查询出来并且添加到缓存里去
				 * @param map
				 */
				public void pushLove2Redis(ModelMap map) {
					//取出一部分love数据
					PageView<List<Map<String, Object>>> lovepageView = this.userlyricsManager.getMixMapData("0","");
					
					List<Map<String, Object>> lovelist = lovepageView.getMapRecords();
					if(!CollectionUtils.isEmpty(lovelist)){
						for (int i = 0; i < lovelist.size(); i++) {
							Map<String, Object> map2 = lovelist.get(i);
							
							//处理标题截断
							String title = (String) map2.get("title");
							String cutString = HTMLParserUtil.CutString(title, 12);
							map2.put("cuttitle", cutString);
							
							//处理日期显示格式
							String updatedate = (String) map2.get("updatedate");
							String engDate = TimeUtils.parse2EnglishDate(updatedate);
							map2.put("engDate", engDate);
						}
					}
					
					JedisUtil.addList("lovelist", lovelist==null?new ArrayList<>():lovelist);
					
					map.addAttribute("lovelist", lovelist==null?"":lovelist);
				}	
	 	

	
	/**
	 * 过滤掉文本的样式
	 * @param htmlStr
	 * @return
	 */
	public String delHTMLTag(String htmlStr){   
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式   
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式   
           
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);   
        Matcher m_style=p_style.matcher(htmlStr);   
        htmlStr=m_style.replaceAll(""); //过滤style标签   
           
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);   
        Matcher m_html=p_html.matcher(htmlStr);   
        htmlStr=m_html.replaceAll(""); //过滤html标签   
          
        htmlStr=htmlStr.replace(" ","");  
        htmlStr=htmlStr.replaceAll("\\s*|\t|\r|\n","");  
        htmlStr=htmlStr.replace("“","");  
        htmlStr=htmlStr.replace("”","");  
        htmlStr=htmlStr.replaceAll("　","");  
            
        return htmlStr.trim(); //返回文本字符串   
    }  
}
