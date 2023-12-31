
package cn.northpark.controller;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.constant.BC_Constant;
import cn.northpark.exception.NorthParkException;
import cn.northpark.model.Knowledge;
import cn.northpark.model.Tags;
import cn.northpark.result.Result;
import cn.northpark.result.ResultEnum;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.KnowledgeService;
import cn.northpark.service.TagsService;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.encrypt.MD5Utils;
import cn.northpark.utils.safe.WAQ;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class KnowledgeController {

	@Autowired
	private KnowledgeService knowledgeService;
	
	@Autowired
	private TagsService tagsService;

	/**
	 * 每页展示多少条学习数
	 */
	private static int LearningCount = 10;

	
	/**
	 * 查看列表
	 * @param map
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="")
	public String list1(ModelMap map,
						HttpServletRequest request,
						HttpSession session) throws IOException {

		session.removeAttribute("tabs");
		session.setAttribute("tabs", "learning");

		String whereSql = " where displayed is null ";
		


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
				whereSql += " and title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
			} else {
				whereSql += " and title like '%" + keyword + "%' ";
			}


		}

		log.info("sql ---"+whereSql);

		//排序条件
		String _orderBy = "";
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotEmpty(orderBy)) {
			if ("hot".equals(orderBy)) {
				_orderBy = "hot_index desc" ;
			} else if ("latest".equals(orderBy)) {
				_orderBy = "id desc" ;
			}
			map.put("orderBy", orderBy);
		} else {
			_orderBy = "post_date desc,id desc" ;
		}
		
		PageHelper.startPage(1,LearningCount);
		List<Knowledge> result_list = knowledgeService.findByCondition(whereSql,_orderBy);
		PageInfo pageInfo = new PageInfo(result_list);

		//处理标签列表
		handleTag(result_list);

		map.put("page", 1);
		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("list", result_list);
		map.addAttribute("actionUrl","/learning");

		//获取标签模块
		getTags(map, request);

		return "learning";
	}
	
	

	/**
	 * 查看列表分页
	 * @param map
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/page/{page}")
	public String list2(ModelMap map,
						@PathVariable Integer page,
						HttpServletRequest request,
				        HttpSession session) throws IOException {

		session.removeAttribute("tabs");
		session.setAttribute("tabs", "learning");

		String whereSql = " where displayed is null ";



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
				whereSql += " and title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
			} else {
				whereSql += " and title like '%" + keyword + "%' ";
			}


		}

		log.info("sql ---"+whereSql);

		//排序条件
		String _orderBy = "";
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotEmpty(orderBy)) {
			if ("hot".equals(orderBy)) {
				_orderBy = "hot_index desc" ;
			} else if ("latest".equals(orderBy)) {
				_orderBy = "id desc" ;
			}
			map.put("orderBy", orderBy);
		} else {
			_orderBy = "post_date desc,id desc" ;
		}

		PageHelper.startPage(page,LearningCount);
		List<Knowledge> result_list = knowledgeService.findByCondition(whereSql,_orderBy);
		PageInfo pageInfo = new PageInfo(result_list);

		//处理标签列表
		handleTag(result_list);

		map.put("page", page);
		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("list", result_list);
		map.addAttribute("actionUrl","/learning");

		//获取标签模块
		getTags(map, request);

		return "learning";
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
	public String postDetail(ModelMap map, @PathVariable String id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		if (StringUtils.isNotEmpty(id)) {
			//sql注入处理
			id = WAQ.forSQL().escapeSql(id);
			Knowledge model = knowledgeService.findKnowledge(Integer.valueOf(id));
			if(model!=null) {
				//SEO优化
				if(StringUtils.isNotEmpty(model.getContent())) map.put("content", Jsoup.parse(model.getContent()).select("p").text());
				map.addAttribute("model", model);
			}
		}


		return "learning-article";
	}



	//=====================================标签=====================================
	/**
	 * 按照标签计算
	 *
	 * @param map
	 * @param tags_code
	 * @param request
	 * @return
	 */
	@RequestMapping("/tag/{tags_code}")
	public String tagSearch(ModelMap map, @PathVariable String tags_code, HttpServletRequest request) {
		//防止sql注入
		tags_code = WAQ.forSQL().escapeSql(tags_code);
		String whereSql = " where tags_code like '%" + tags_code + "%' ";

		map.put("sel_tag", tags_code);


		log.info("sql ---" + whereSql);

		//排序条件
		String _orderBy = "hot_index desc,id desc";

		PageHelper.startPage(1,LearningCount);
		List<Knowledge> result_list = knowledgeService.findByCondition(whereSql,_orderBy);
		PageInfo pageInfo = new PageInfo(result_list);

		//处理标签列表
		handleTag(result_list);

		map.put("page", 1);

		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("list", result_list);
		map.addAttribute("actionUrl", "/learning/tag/" + tags_code);
		//获取标签模块
		getTags(map, request);

		return "learning";
	}


	/**
	 * 按照标签分页计算
	 *
	 * @param map
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tag/{tags_code}/page/{page}")
	public String tag_list_page(ModelMap map, @PathVariable Integer page, HttpServletRequest request, @PathVariable String tags_code,
							  HttpServletResponse response, HttpSession session) throws IOException {

		//防止sql注入
		tags_code = WAQ.forSQL().escapeSql(tags_code);
		String whereSql = " where tags_code like '%" + tags_code + "%' ";

		map.put("sel_tag", tags_code);


		log.info("sql ---" + whereSql);

		//排序条件
		String _orderBy = "hot_index desc,id desc";

		PageHelper.startPage(page,LearningCount);
		List<Knowledge> result_list = knowledgeService.findByCondition(whereSql,_orderBy);
		PageInfo pageInfo = new PageInfo(result_list);

		//处理标签列表
		handleTag(result_list);

		map.put("page", page);

		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("list", result_list);
		map.addAttribute("actionUrl", "/learning/tag/" + tags_code);
		//获取标签模块
		getTags(map, request);

		return "learning";
	}


	/**
	 * 处理标签列表
	 *
	 * @param result_list
	 */
	private void handleTag(List<Knowledge> result_list) {
		if (!CollectionUtils.isEmpty(result_list)) {

			for (Knowledge m : result_list) {
				String tag = m.getTags();
				String tag_code = m.getTagsCode();
				if (StringUtils.isNotEmpty(tag) && StringUtils.isNotEmpty(tag_code)) {
					String[] tags = tag.split(",");
					String[] tag_codes = tag_code.split(",");
					if (tags.length != tag_codes.length) {
						throw new NorthParkException(ResultEnum.Movie_Tag_Not_Match);
					}
					List<Map<String, String>> tag_list = Lists.newArrayList();
					for (int i = 0; i < tags.length; i++) {
						Map<String, String> map = Maps.newHashMap();
						map.put("tag", tags[i]);
						map.put("tag_code", tag_codes[i]);
						tag_list.add(map);
						map = null;
					}
					m.setTag_list(tag_list);
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
		String tags_str = RedisUtil.getInstance().get("learnTags");
		String learn_hot_list_str = RedisUtil.getInstance().get("learn_hot_list");
		if(StringUtils.isNotEmpty(tags_str) && StringUtils.isNotEmpty(learn_hot_list_str)) {

			learn_hot_list = JsonUtil.json2ListMap(learn_hot_list_str);
			tags = JsonUtil.json2list(tags_str, Tags.class);
		}

		//从数据库取
		if (CollectionUtils.isEmpty(tags) && CollectionUtils.isEmpty(learn_hot_list)) {
			//获取标签

			tags = tagsService.findByCondition(" where tag_type = '4' ","");

			//获取热门学习
			String hot_sql = "select id,title,color from bc_knowledge where tags_code like '%classhare%' order by rand() desc limit 0,50";
			learn_hot_list = knowledgeService.querySqlMap(hot_sql);

			RedisUtil.getInstance().set("learn_hot_list", JsonUtil.object2json(learn_hot_list), 24 * 60 * 60);
			RedisUtil.getInstance().set("learnTags", JsonUtil.object2json(tags), 24 * 60 * 60);

		}


		map.put("learn_hot_list", learn_hot_list);
		map.put("learnTags", tags);

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
			String max_hot_sql_id = "select max(hot_index) as hot_index from bc_knowledge ";
			List<Map<String, Object>> list = knowledgeService.querySqlMap(max_hot_sql_id);
			Integer hot_index = 0;
			if (!CollectionUtils.isEmpty(list) && Objects.nonNull(list.get(0).get("hot_index"))) {
					hot_index = (Integer) list.get(0).get("hot_index");
					hot_index++;
			}
			
			if (hot_index > 0) {
				Knowledge m = knowledgeService.findKnowledge(Integer.parseInt(id));
				if (m != null) {
					m.setHotIndex(hot_index);
					knowledgeService.updateKnowledge(m);
				}
			}



		} catch (Exception e) {
			log.error("learning action 置顶的方法------>", e);
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

			Knowledge m = knowledgeService.findKnowledge(Integer.parseInt(id));
			if (m != null) {
				m.setDisplayed("N");
				knowledgeService.updateKnowledge(m);
			}

		} catch (Exception e) {
			log.error("learnings action 隐藏学习的方法------>", e);
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
			Knowledge  model = knowledgeService.findKnowledge(Integer.valueOf(id));
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
				knowledgeService.updateKnowledge(model);

				//从redis set里面删除更新的失效资源
				if(RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).toString().contains(model.getId().toString())){
					RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).forEach(item->{
						if(item.contains(model.getId().toString())) {
							RedisUtil.getInstance().sRem(BC_Constant.REDIS_FEEDBACK, item);
						}
					});
				}

			}else {//新增

				model.setRetCode(MD5Utils.encrypt(model.getTitle(),MD5Utils.MD5_KEY));
				model.setPostDate(TimeUtils.nowdate());
				knowledgeService.addKnowledge(model);
			}

		} catch (Exception e) {
			log.error("保存学习的方法------>", e);
			rs = "ex";
		}
		return ResultGenerator.genSuccessResult(rs);
	}
   //=====================================资源编辑========================================


}
