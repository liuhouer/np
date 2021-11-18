
package cn.northpark.action;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.NotifyRemindManager;
import cn.northpark.model.NotifyRemind;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.vo.UserVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
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
public class NotifyRemindAction {

 @Autowired	
 private NotifyRemindManager notifyRemindManager;

	/**
	 * 拉取未读消息数量
	 */
	@RequestMapping("/notify/count")
	@ResponseBody
	public Result<Integer> notifyNum(HttpServletRequest request) {


		try {
			UserVO userInfo = RequestHolder.getUserInfo(request);

			String notifyNumSql = " select * from bc_notify_remind   where recipientID = ? and status = '0' ";

			int i = notifyRemindManager.querySql(notifyNumSql,userInfo.getId()).size();

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
	public Result<Boolean> readNotify(HttpServletRequest request) {


		try {
			String ids = request.getParameter("id");

			if(StringUtils.isNotEmpty(ids)){

				UserVO userInfo = RequestHolder.getUserInfo(request);

				String readNotifySql = "update bc_notify_remind   set status = ? where  recipientID = ? and id in ("+ids+")";

				notifyRemindManager.executeSql(readNotifySql,"1",userInfo.getId());
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

		UserVO userInfo = RequestHolder.getUserInfo(request);
		String where_sql = " where recipientID = '" + userInfo.getId() + "' ";

		log.info("where_sql ---" + where_sql);

		//排序条件
		LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
		order.put("status", "asc");
		order.put("createdAt", "desc");

		//获取pageView
		PageView<NotifyRemind> page_view = new PageView<NotifyRemind>(1, MyConstant.MAX_RESULT);

		QueryResult<NotifyRemind> qr = notifyRemindManager.findByCondition(page_view, where_sql, order);

		List<NotifyRemind> result_list = qr.getResultList();

		//格式化时间
		result_list.parallelStream().forEach(item->{
			item.setCreateTime(TimeUtils.format(item.getCreatedAt()));
		});

		//生成分页信息
		page_view.setQueryResult(qr);

		map.addAttribute("list", result_list);
		map.addAttribute("pageView", page_view);
		map.addAttribute("actionUrl", "/notifications");


		return "/notify";
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
	public String listPage(ModelMap map, @PathVariable String page, HttpServletRequest request) throws IOException {

		UserVO userInfo = RequestHolder.getUserInfo(request);
		String where_sql = " where recipientID = '" + userInfo.getId() + "' ";

		log.info("where_sql ---" + where_sql);

		//排序条件
		LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
		order.put("status", "asc");
		order.put("createdAt", "desc");

		//获取pageView
		PageView<NotifyRemind> page_view = new PageView<NotifyRemind>(Integer.parseInt(page), MyConstant.MAX_RESULT);

		QueryResult<NotifyRemind> qr = notifyRemindManager.findByCondition(page_view, where_sql, order);

		List<NotifyRemind> result_list = qr.getResultList();

		//生成分页信息
		page_view.setQueryResult(qr);

		map.addAttribute("list", result_list);
		map.addAttribute("pageView", page_view);
		map.addAttribute("actionUrl", "/notifications");
		map.addAttribute("page", page);


		return "/notify";
	}


}
