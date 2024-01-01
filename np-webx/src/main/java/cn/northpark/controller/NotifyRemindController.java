
package cn.northpark.controller;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.MyConstant;
import cn.northpark.model.NotifyRemindB;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.NotifyRemindService;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.TimeUtils;
import cn.northpark.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("")
@Slf4j
public class NotifyRemindController {

 @Autowired	
 private NotifyRemindService notifyRemindService;

	/**
	 * 拉取未读消息数量
	 */
	@RequestMapping("/notify/count")
	@ResponseBody
	@RateLimit
	public Result<Integer> notifyNum(HttpServletRequest request) {


		try {
			UserVO userInfo = RequestHolder.getUserInfo(request);

			String notifyNumSql = " select * from bc_notify_remind_b   where recipient_id = ? and status = '0' ";

			int i = NPQueryRunner.query(notifyNumSql,new MapListHandler(),userInfo.getId()).size();

			return ResultGenerator.genSuccessResult(i);

		} catch (Exception e) {
			e.printStackTrace();

			log.error("notify/count--->" + e);
		}


		return ResultGenerator.genSuccessResult(0);
	}


	/**
	 * 当前页的消息设置为已读
	 */
	@RequestMapping("/notify/readNotify")
	@ResponseBody
	@RateLimit
	public Result<Boolean> readNotify(HttpServletRequest request) {


		try {
			String ids = request.getParameter("id");

			if(StringUtils.isNotEmpty(ids)){

				UserVO userInfo = RequestHolder.getUserInfo(request);

				String readNotifySql = "update bc_notify_remind_b   set status = ? where  recipient_id = ? and id in ("+ids+")";

				NPQueryRunner.update(readNotifySql,"1",userInfo.getId());
			}



		} catch (Exception e) {
			e.printStackTrace();

			log.error("notify/count--->" + e);

			return ResultGenerator.genSuccessResult(false);

		}

		return ResultGenerator.genSuccessResult(true);

	}


	/**
	 * 查看通知
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/notifications")
	@CheckLogin
	public String list1(ModelMap map,HttpServletRequest request) throws IOException {
		request.getSession().removeAttribute("tabs");
		UserVO userInfo = RequestHolder.getUserInfo(request);
		String where_sql = " where recipient_id = '" + userInfo.getId() + "' ";

		//标签分类
		String remindID = request.getParameter("remindID");
		if(StringUtils.isNotEmpty(remindID)){
			try {
				Preconditions.checkArgument(Integer.parseInt(remindID)>0,"编号错误");
			}catch (Exception e){
				throw new IllegalArgumentException("u r shit");
			}

			where_sql+=" and remind_id= '"+remindID+"' ";

			map.put("remindID",remindID);
		}

		log.info("where_sql ---" + where_sql);

		//排序条件
		String _orderBy = "status asc,created_at desc";

		PageHelper.startPage(1,MyConstant.MAX_RESULT);
		List<NotifyRemindB> result_list = notifyRemindService.findByCondition(where_sql,_orderBy);


		//格式化时间
		result_list.parallelStream().forEach(item->{
			item.setCreateTime(TimeUtils.format(item.getCreatedAt()));
		});
		PageInfo pageInfo = new PageInfo(result_list);

		map.addAttribute("list", result_list);
		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("actionUrl", "/notifications");


		return "notify";
	}


	/**
	 * 消息分页列表
	 *
	 * @param map
	 * @param page
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/notifications/page/{page}")
	public String listPage(ModelMap map, @PathVariable Integer page, HttpServletRequest request) throws IOException {

		UserVO userInfo = RequestHolder.getUserInfo(request);
		String where_sql = " where recipient_id = '" + userInfo.getId() + "' ";

		//标签分类
		String remindID = request.getParameter("remindID");
		if(StringUtils.isNotEmpty(remindID)){
			try {
				Preconditions.checkArgument(Integer.parseInt(remindID)>0,"编号错误");
			}catch (Exception e){
				throw new IllegalArgumentException("u r shit");
			}

			where_sql+=" and remind_id= '"+remindID+"' ";

			map.put("remindID",remindID);
		}

		log.info("where_sql ---" + where_sql);

		//排序条件
		String _orderBy = "status asc,created_at desc";

		PageHelper.startPage(page,MyConstant.MAX_RESULT);
		List<NotifyRemindB> result_list = notifyRemindService.findByCondition(where_sql,_orderBy);


		//格式化时间
		result_list.parallelStream().forEach(item->{
			item.setCreateTime(TimeUtils.format(item.getCreatedAt()));
		});
		PageInfo pageInfo = new PageInfo(result_list);

		map.addAttribute("list", result_list);
		map.addAttribute("pageInfo", pageInfo);
		map.addAttribute("actionUrl", "/notifications");
		map.addAttribute("page", page);


		return "/notify";
	}


}
