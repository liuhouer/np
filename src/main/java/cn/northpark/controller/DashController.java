package cn.northpark.controller;

import cn.northpark.annotation.Desc;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.BC_Constant;
import cn.northpark.model.Eq;
import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.EqService;
import cn.northpark.service.NoteService;
import cn.northpark.service.UserLyricsService;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.*;
import cn.northpark.vo.UserVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
@Slf4j
public class DashController {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	@Autowired
	UserLyricsService userlyricsManager;

	@Autowired
	NoteService noteManager;

	@Autowired
	EqService eqManager;



	//利用nginx配置静态谷歌ads映射

	@RequestMapping("/donate")
	@Desc(value = "跳转微信1 test..")
	public String donateDetail(ModelMap map,HttpServletRequest request) {

		//数据埋点-站长统计
		//===================================异步操作=================================================
		ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {

				//获取IP+地址
				String ipAndDetail = "";
				try {

					// 获取当前日期
					LocalDate currentDate = LocalDate.now();

					// 构建计数器键
					String counterKey =  EnvCfgUtil.getValByCfgName("COUNTER_KEY_PREFIX") + currentDate.toString();

					Long counter = RedisUtil.getInstance().incrAndGet(counterKey);
					String gd_ip_max = EnvCfgUtil.getValByCfgName("GD_IP_MAX");
					if(counter > Integer.parseInt(gd_ip_max)){
						log.error("当天统计ip信息数目已超过"+gd_ip_max+"，不再请求");
					}else{
						ipAndDetail = AddressUtils.getInstance().getIpAndDetail(request);
					}


				}catch (Exception ignore){
					log.error(ignore.getMessage());
				}


				//发送异步站长通知消息
				try {
					//=================================消息提醒====================================================

					UserVO userInfo = RequestHolder.getUserInfo(request);

					//判断主题类型
					NotifyEnum match = NotifyEnum.WEBMASTER;

					//提醒系统赋值
					NotifyRemind nr = new NotifyRemind();

					//common
					if(Objects.nonNull(userInfo)){
						nr.setMessage(userInfo.toString()+"---"+ipAndDetail+"---"+TimeUtils.nowTime()+"---请求了donate界面---");
					}else {
						nr.setMessage(JSON.toJSONString(CookieUtil.readCookieUA(request))+"---"+ipAndDetail+"---"+TimeUtils.nowTime()+"---请求了donate界面---");
					}
					nr.setStatus("0");


					log.error(JsonUtil.object2json(CookieUtil.readCookieUA(request)));
					match.notifyInstance.execute(nr);

					//=================================消息提醒====================================================
				}catch (Exception ig){
					log.error("donate-notice-has-ignored-------:",ig);
				}
			}



		});
		//===================================异步操作=================================================


		return "/donateMe";
	}


	@RequestMapping("/")
	@Desc(value = "首页")
	public String dashBorard(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

		//slider
		request.getSession().removeAttribute("tabs");

		//if www return no www
		String getDomain = request.getServerName();
		if (getDomain.startsWith("www")) {
			response.sendRedirect("http://" + BC_Constant.Domain);
		}

		return "/dashboard";

	}


	@RequestMapping("/about.html")
	@Desc(value = "关于Northpark")
	public String about(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

		return "/about";

	}


	@RequestMapping(value = "/dash/getLove")
	@Desc(value = "异步获取首页的love数据")
	@RateLimit
	public String getLove(ModelMap map) {

		List<Map<String, Object>> data = pushLove2Map();

		map.addAttribute("lovelist", data);

		return "/page/love/lovedata";
	}

	@RequestMapping(value = "/dash/getNote")
	@Desc(value = "异步获取首页的《碎碎念》数据")
	@RateLimit
	public String getNote(ModelMap map) {


		pushNote2Map(map);


		return "/page/dash/notedata";
	}

	@RequestMapping(value = "/dash/getRomeo")
	@Desc(value = "异步获取首页的《情圣》数据")
	@RateLimit
	public String getRomeo(ModelMap map) {

		pushEQ2Map(map);


		return "/page/dash/romeodata";
	}

	@RequestMapping(value = "/dash/getMovies")
	@Desc(value = "异步获取首页的《电影》数据")
	@RateLimit
	public String getMovies(ModelMap map) {


		pushMovies2Map(map);


		return "/page/dash/moviesdata";
	}


//	@RequestMapping(value = "/dash/getDonates")
//	@Desc(value = "异步获取捐赠数据")
//	@RateLimit
//	public String getDonates(HttpServletRequest request, ModelMap map) {
//
//		String type_id = request.getParameter("type_id");
//
//		String page = request.getParameter("page");
//
//		if (StringUtils.isEmpty(page)) {
//			page = "1";
//		}
//
//		assert StringUtils.isNotBlank(type_id);
//
//		String result = DonatesRedisKeyEnum.match(type_id).getRedis_key();
//
//		pushDonates2Map(map, type_id, result, page);
//
//
//		return "/page/common/" + result.replace("_z", "");
//	}

//	/**
//	 * @param map
//	 * @param page
//	 */
//	public void pushDonates2Map(ModelMap map, String type_id, String result, String page) {
//		//取出捐赠数据
//
//
//		List<Map<String, Object>> list = null;
//
//		//组装+计算分页信息=================
//		PageView<List<Map<String, Object>>> pageView = new PageView<List<Map<String, Object>>>(Integer.parseInt(page), MyConstant.MAX_RESULT, 3);
//		pageView = noteManager.getMixMapPage(pageView, DonatesEnum.match(type_id).getSql_fetch());
//		//组装+计算分页信息=================
//
//		//从redis取
//
//		//获取数据条数
//		Long zCard = RedisUtil.getInstance().zCard(result + page);
//
//		//从redis查询
//		if (Objects.nonNull(zCard) && zCard.intValue() > 0) {
//
//			//从redis获取数据
//			Set<String> zRangeByScore = RedisUtil.getInstance().zRangeByScore(result + page, "0",MyConstant.MAX_RESULT + "", 0, MyConstant.MAX_RESULT);
//			list = zRangeByScore.stream().map(i -> JsonUtil.json2map(i)).collect(Collectors.toList());
//
//		} else {
//
//			//没有结果-从数据库取
//
//			//根据计算的分页仅仅获取数据
//			list = this.noteManager.findmixByCondition(pageView, DonatesEnum.match(type_id).getSql_fetch());
//
//			//写入redis
//			for (int i = 0; i < list.size(); i++) {
//				RedisUtil.getInstance().zAdd(result + page, i, JsonUtil.object2json(list.get(i)));
//			}
//		}
//
//		//组装默认分页信息=================
//		map.addAttribute("pageView", pageView);
//		map.addAttribute("type_id", type_id);
//		map.addAttribute("page", page);
//		map.addAttribute("list", list);
//		//组装默认分页信息=================
//
//
//	}


	/**
	 * @param map
	 */
	public void pushMovies2Map(ModelMap map) {
		//取出热门电影
		List<Map<String, Object>> home_movies_list = null;

		//从redis取
		String str = RedisUtil.getInstance().get("home_movies_list");
		if (StringUtils.isNotEmpty(str)) {
			home_movies_list = JsonUtil.json2ListMap(str);
		}


		//从数据库取 :1天刷新
		if (CollectionUtils.isEmpty(home_movies_list)) {

			String msql = "select id,movie_name from bc_movies order by rand() limit 1,24";
			home_movies_list = NPQueryRunner.query(msql,new MapListHandler());

			RedisUtil.getInstance().set("home_movies_list", JsonUtil.object2json(home_movies_list), 24 * 60 * 60);

		}

		map.addAttribute("movies_list", home_movies_list);
	}


	/**
	 * @param map
	 */
	public void pushEQ2Map(ModelMap map) {
		//取出一部分情圣日记

		List<Eq> home_eq_list = null;

		//从redis取
		String str = RedisUtil.getInstance().get("home_eq_list");
		if (StringUtils.isNotEmpty(str)) {
			home_eq_list = JsonUtil.json2list(str, Eq.class);
		}

		//从数据库取 :1天刷新
		if (CollectionUtils.isEmpty(home_eq_list)) {
			//开启驼峰映射
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			String eq_sql = "select * from bc_eq  where date ='2016-07-21' or date ='2016-07-19' or date ='2016-07-15' order by date desc";
			home_eq_list = NPQueryRunner.query(eq_sql,new BeanListHandler<Eq>(Eq.class,processor));
			RedisUtil.getInstance().set("home_eq_list", JsonUtil.object2json(home_eq_list));


		}
		map.addAttribute("eq_list", home_eq_list);
	}


	/**
	 * 把首页的日记数据查询出来并且添加到缓存里去
	 *
	 * @param map
	 */
	public void pushNote2Map(ModelMap map) {
		//取出一部分日记-随机
		List<Map<String, Object>> home_note_list = null;

		//从redis取
		String str = RedisUtil.getInstance().get("home_note_list");
		if (StringUtils.isNotEmpty(str)) {
			home_note_list = JsonUtil.json2ListMap(str);
		}


		//从数据库取 :1天刷新
		if (CollectionUtils.isEmpty(home_note_list)) {
			PageHelper.startPage(1,16);
			List<Map<String, Object>> note_list = this.noteManager.getHotNoteList();

			//时间处理
			note_list.forEach(item -> {
				String create_time = (String) item.get("create_time");
				if (StringUtils.isNotEmpty(create_time)) item.put("create_time", TimeUtils.getHalfDate(create_time));
			});

			RedisUtil.getInstance().set("home_note_list", JsonUtil.object2json(note_list), 24 * 60 * 60);

		}

		map.addAttribute("note_list", home_note_list);
	}


	/**
	 * 把首页的love数据查询出来并且添加到缓存里去
	 */
	public List<Map<String, Object>> pushLove2Map() {

		List<Map<String, Object>> home_lovelist = null;

		//从redis取
		String str = RedisUtil.getInstance().get("home_lovelist");
		if (StringUtils.isNotEmpty(str)) {
			home_lovelist = JsonUtil.json2ListMap(str);
		}

		//从数据库取
		if (CollectionUtils.isEmpty(home_lovelist)) {
			//取出一部分love数据
			PageHelper.startPage(1,12);
			String randSql = userlyricsManager.getRandSql();
			home_lovelist = this.userlyricsManager.execSql(randSql);

			if (!CollectionUtils.isEmpty(home_lovelist)) {

				home_lovelist.forEach(item -> {
					//处理标题截断
					String title = (String) item.get("title");
					if (StringUtils.isNotEmpty(title)) item.put("cut_title", HTMLParserUtil.CutString(title, 12));

					//处理日期显示格式
					String update_date = (String) item.get("update_date");
					if (StringUtils.isNotEmpty(update_date))
						item.put("engDate", TimeUtils.parse2EnglishDate(update_date));
				});
			}

			RedisUtil.getInstance().set("home_lovelist", JsonUtil.object2json(home_lovelist), 2 * 24 * 60 * 60);

		}

		return home_lovelist;
	}


	@RequestMapping("/transPic")
	@Desc(value = "传送图片到minio")
	@ResponseBody
	public Result transPic(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

		String bucketName = request.getParameter("bucketName");
		String sourceDir = request.getParameter("sourceDir");
		MinioUtils.uploadFiles(bucketName,sourceDir);
		return ResultGenerator.genSuccessResult();
	}

}