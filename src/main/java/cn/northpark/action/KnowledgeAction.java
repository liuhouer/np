
package cn.northpark.action;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.constant.BC_Constant;
import cn.northpark.constant.ResultEnum;
import cn.northpark.exception.NorthParkException;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.KnowledgeManager;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Knowledge;
import cn.northpark.model.Tags;
import cn.northpark.query.KnowledgeQuery;
import cn.northpark.query.condition.KnowledgeQueryCondition;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.encrypt.MD5Utils;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/learning")
@Slf4j
public class KnowledgeAction {

	@Autowired
	private KnowledgeManager knowledgeManager;
	
	@Autowired
	private KnowledgeQuery knowledgeQuery;

	@Autowired
	private TagsManager tagsManager;

	/**
	 * 每页展示多少条学习数
	 */
	private static int LearningCount = 10;

	
	/**
	 * 查看列表
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="")
	public String list1(ModelMap map,KnowledgeQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		session.removeAttribute("tabs");
		session.setAttribute("tabs", "learning");

		String result="/learning";
		String sql = knowledgeQuery.getMixSql(condition);
		
		log.info("sql ---"+sql);

		//搜索
		String keyword = request.getParameter("keyword");
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		map.put("keyword", keyword);
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = WAQ.forSQL().escapeSql(keyword);
			if (keyword.contains(" ")) {
				String keyword2 = keyword.replaceAll(" ", "");
				sql += " and title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
			} else {
				sql += " and title like '%" + keyword + "%' ";
			}


		}

		//排序条件
		LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
		String orderby = request.getParameter("orderby");
		if (StringUtils.isNotEmpty(orderby)) {
			if ("hot".equals(orderby)) {
				order.put("hotindex", "desc");
			} else if ("latest".equals(orderby)) {
				order.put("id", "desc");
			}
			map.put("orderby", orderby);
		} else {
			order.put("post_date", "desc");
			order.put("id", "desc");
		}
		
		//定义pageview
		PageView<Knowledge> pageview = new PageView<Knowledge>(1, LearningCount);
		
		//获取分页结构不获取数据

		QueryResult<Knowledge> qr = this.knowledgeManager.findByCondition(pageview, sql,order);
		List<Knowledge> resultlist = qr.getResultlist();

		//处理标签列表
		handleTag(resultlist);
		//生成分页信息
		pageview.setQueryResult(qr);

		map.put("page", 1);
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.put("condition", condition);
		map.addAttribute("actionUrl","/learning/list");

		//获取标签模块
		getTags(map, request);

		return result;
	}
	
	

	/**
	 * 查看列表分页
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/page/{page}")
	public String list2(ModelMap map, KnowledgeQueryCondition condition, @PathVariable String page, HttpServletRequest request,
						HttpServletResponse response, HttpSession session) throws IOException {

		session.removeAttribute("tabs");
		session.setAttribute("tabs", "learning");

		String result="/learning";
		String sql = knowledgeQuery.getMixSql(condition);
		
		log.info("sql ---"+sql);
		int currentpage = Integer.parseInt(page);


		//搜索
		String keyword = request.getParameter("keyword");
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		map.put("keyword", keyword);
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = WAQ.forSQL().escapeSql(keyword);
			if (keyword.contains(" ")) {
				String keyword2 = keyword.replaceAll(" ", "");
				sql += " and title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
			} else {
				sql += " and title like '%" + keyword + "%' ";
			}


		}

		//排序条件
		LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
		String orderby = request.getParameter("orderby");
		if (StringUtils.isNotEmpty(orderby)) {
			if ("hot".equals(orderby)) {

				order.put("hotindex", "desc");

			} else if ("latest".equals(orderby)) {

				order.put("post_date", "desc");
				order.put("id", "desc");
			}
			map.put("orderby", orderby);
		} else {
			order.put("post_date", "desc");
			order.put("id", "desc");
		}


		//定义pageview
		PageView<Knowledge> pageview = new PageView<Knowledge>(currentpage, LearningCount);

		//获取分页结构不获取数据

		QueryResult<Knowledge> qr = this.knowledgeManager.findByCondition(pageview, sql,order);

		List<Knowledge> resultlist = qr.getResultlist();
		//处理标签列表
		handleTag(resultlist);
		//生成分页信息
		pageview.setQueryResult(qr);

		map.put("page", page);
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.put("condition", condition);
		map.addAttribute("actionUrl","/learning/list");

		//获取标签模块
		getTags(map, request);
		return result;
	}


	/**
	 * 跳转到书籍/课程详情页面
	 *
	 * @param map
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/post-{id}.html")
	public String postdetail(ModelMap map, @PathVariable String id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		if (StringUtils.isNotEmpty(id)) {
			//sql注入处理
			id = WAQ.forSQL().escapeSql(id);
			Knowledge model = knowledgeManager.findKnowledge(Integer.valueOf(id));
			if(model!=null) {
				if(StringUtils.isNotEmpty(model.getContent())) map.put("content", Jsoup.parse(model.getContent()).select("p").text());
				map.addAttribute("model", model);
			}
		}


		return "/learning-article";
	}



	//=====================================标签=====================================
	/**
	 * 按照标签计算
	 *
	 * @param map
	 * @param tagscode
	 * @param request
	 * @return
	 */
	@RequestMapping("/tag/{tagscode}")
	public String tagsearch(ModelMap map, @PathVariable String tagscode, HttpServletRequest request) {
		String rs = "redirect:/learning/tag/" + tagscode + "/page/1";
		return rs;
	}


	/**
	 * 按照标签分页计算
	 *
	 * @param map
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tag/{tagscode}/page/{page}")
	public String taglistpage(ModelMap map, @PathVariable String page, HttpServletRequest request, @PathVariable String tagscode,
							  HttpServletResponse response, HttpSession session) throws IOException {

		String result = "/learning";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
		String whereSql = " where tags_code like '%" + tagscode + "%' ";

		map.put("seltag", tagscode);


		log.info("sql ---" + whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
		order.put("hotindex", "desc");
		order.put("id", "desc");

		//获取pageview
		PageView<Knowledge> pageview = new PageView<Knowledge>(Integer.parseInt(currentpage), LearningCount);
		QueryResult<Knowledge> qr = this.knowledgeManager.findByCondition(pageview, whereSql, order);
		List<Knowledge> resultlist = qr.getResultlist();

		//生成分页信息
		pageview.setQueryResult(qr);

		//处理标签列表
		handleTag(resultlist);

		int pages = 0;
		try {
			pages = Integer.parseInt(page) + 1;

		} catch (Exception e) {
			pages = 1;
		}
		map.put("page", pages);

		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl", "/learning/tag/" + tagscode);
		//获取标签模块
		getTags(map, request);

		return result;
	}


	/**
	 * 处理标签列表
	 *
	 * @param resultlist
	 */
	private void handleTag(List<Knowledge> resultlist) {
		if (!CollectionUtils.isEmpty(resultlist)) {

			for (Knowledge m : resultlist) {
				String tag = m.getTags();
				String tagcode = m.getTags_code();
				if (StringUtils.isNotEmpty(tag) && StringUtils.isNotEmpty(tagcode)) {
					String[] tags = tag.split(",");
					String[] tagcodes = tagcode.split(",");
					if (tags.length != tagcodes.length) {
						throw new NorthParkException(ResultEnum.Movie_Tag_Not_Match);
					}
					List<Map<String, String>> taglist = Lists.newArrayList();
					for (int i = 0; i < tags.length; i++) {
						Map<String, String> map = Maps.newHashMap();
						map.put("tag", tags[i]);
						map.put("tagcode", tagcodes[i]);
						taglist.add(map);
						map = null;
					}
					m.setTaglist(taglist);
				}

			}
		}
	}


	/**
	 * 获取标签模块
	 *
	 * @param map
	 */
	private void getTags(ModelMap map, HttpServletRequest request) {
		List<Tags> tags = null;
		List<Map<String, Object>> learn_hot_list = null;

		//从redis取
		String tags_str = RedisUtil.getInstance().get("learn_tags");
		String learn_hot_list_str = RedisUtil.getInstance().get("learn_hot_list");
		if(StringUtils.isNotEmpty(tags_str) && StringUtils.isNotEmpty(learn_hot_list_str)) {

			learn_hot_list = JsonUtil.json2ListMap(learn_hot_list_str);
			tags = JsonUtil.json2list(tags_str, Tags.class);
		}

		//从数据库取
		if (CollectionUtils.isEmpty(tags) && CollectionUtils.isEmpty(learn_hot_list)) {
			//获取标签

			tags = tagsManager.findByCondition(" where tagtype = '4' ").getResultlist();

			//获取热门学习
			String hotsql = "select id,title,color from bc_knowledge where tags_code like '%classhare%' order by rand() desc limit 0,50";
			learn_hot_list = knowledgeManager.querySqlMap(hotsql);

			RedisUtil.getInstance().set("learn_hot_list", JsonUtil.object2json(learn_hot_list), 24 * 60 * 60);
			RedisUtil.getInstance().set("learn_tags", JsonUtil.object2json(tags), 24 * 60 * 60);

		}


		map.put("learn_hot_list", learn_hot_list);
		map.put("learn_tags", tags);

	}
	//=====================================标签=====================================



	//=====================================资源编辑========================================
	/**
	 * @param request
     * @return
	 * @author Bruce
     * 置顶的方法
     */
	@RequestMapping("/handup")
	@ResponseBody
	@BruceOperation
	public Result<String> handup(HttpServletRequest request) {
		String rs = "success";
		try {
			String id = request.getParameter("id");
			String max_hot_sql_id = "select max(hotindex) as hotindex from bc_knowledge ";
			List<Map<String, Object>> list = knowledgeManager.querySqlMap(max_hot_sql_id);
			Integer hotindex = 0;
			if (!CollectionUtils.isEmpty(list) && Objects.nonNull(list.get(0).get("hotindex"))) {
					hotindex = (Integer) list.get(0).get("hotindex");
					hotindex++;
			}
			
			hotindex = hotindex>0?hotindex:888;

			if (hotindex > 0) {
				Knowledge m = knowledgeManager.findKnowledge(Integer.parseInt(id));
				if (m != null) {
					m.setHotindex(hotindex);
					knowledgeManager.updateKnowledge(m);
				}
			}



		} catch (Exception e) {
			log.error("moviesacton------>", e);
			rs = "ex";
		}
		return ResultGenerator.genSuccessResult(rs);
	}


	/**
	 * 隐藏学习的方法
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/hideup")
	@ResponseBody
	@BruceOperation
	public Result<String> hideup(HttpServletRequest request) {
		String rs = "success";
		try {
			String id = request.getParameter("id");

			Knowledge m = knowledgeManager.findKnowledge(Integer.parseInt(id));
			if (m != null) {
				m.setDisplayed("N");
				knowledgeManager.updateKnowledge(m);
			}

		} catch (Exception e) {
			log.error("moviesacton------>", e);
			rs = "ex";
		}
		return ResultGenerator.genSuccessResult(rs);
	}


	/**
	 * 跳转后台添加
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping("/add")
	@BruceOperation
	public String toAdd(ModelMap map, HttpServletRequest request) {

		String rs = "/page/admin/learn/learnAdd";
		return rs;
	}


	/**
	 * 跳转到学习编辑页面
	 *
	 * @param map
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	@BruceOperation
	public String edit(ModelMap map, @PathVariable String id, HttpServletRequest request ) {

		if (StringUtils.isNotEmpty(id)) {
			//sql注入处理
			id = WAQ.forSQL().escapeSql(id);
			Knowledge  model = knowledgeManager.findKnowledge(Integer.valueOf(id));
			if(model!=null) {
				map.addAttribute("model", model);
			}
		}


		return "/page/admin/learn/learnAdd";
	}


	/**
	 * 保存学习的方法
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping("/addItem")
	@ResponseBody
	@BruceOperation
	public Result<String> addItem(ModelMap map, Knowledge model) {

		assert Objects.nonNull(model)
				&& StringUtils.isNotBlank(model.getContent())
				&& StringUtils.isNotBlank(model.getTitle())
				&& StringUtils.isNotBlank(model.getPath())
				&& StringUtils.isNotBlank(model.getColor());
		String rs = "success";
		try {
			//更新
			if(model.getId()!=null && model.getId()!=0) {
				Knowledge old = knowledgeManager.findKnowledge(model.getId());
				old.setTitle(model.getTitle());
				old.setPath(model.getPath());
				old.setColor(model.getColor());
				old.setContent(model.getContent());
				knowledgeManager.updateKnowledge(old);

				//从redis set里面删除更新的失效资源
				if(RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).toString().contains(model.getId().toString())){
					RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).forEach(item->{
						if(item.contains(model.getId().toString())) {
							RedisUtil.getInstance().sRem(BC_Constant.REDIS_FEEDBACK, item);
						}
					});
				}

			}else {//新增

				model.setRet_code(MD5Utils.encrypt(model.getTitle(),MD5Utils.MD5_KEY));
				model.setPost_date(TimeUtils.nowdate());
				knowledgeManager.addKnowledge(model);
			}

		} catch (Exception e) {
			log.error("保存学习的方法------>", e);
			rs = "ex";
		}
		return ResultGenerator.genSuccessResult(rs);
	}
   //=====================================资源编辑========================================


}