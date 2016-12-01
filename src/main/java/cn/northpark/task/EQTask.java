package cn.northpark.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.northpark.manager.AstroManager;
import cn.northpark.manager.EqManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Astro;
import cn.northpark.model.Eq;
import cn.northpark.model.Soft;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.wx.WXTokenUtil;
import cn.northpark.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.utils.wx.qyh.ParamesAPI.ParamesAPI;
import cn.northpark.utils.wx.qyh.msg.Message;
import cn.northpark.utils.wx.qyh.msg.MessageUtil;
import cn.northpark.utils.wx.qyh.msg.Resp.TextMessage;


/**
 * @author zhangyang
 *
 * 定时爬取今日情圣文章+软件
 */
public class EQTask {
	
	@Autowired
	public EqManager EqManager;
	@Autowired
	public SoftManager softManager;
	@Autowired
	public AstroManager astroManager;


	public void runTask(){
		
		Logger logger = Logger.getLogger(EQTask.class);  
		System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
		try {

    		Map<String, String> map = HTMLParserUtil.retTodayEq();
    		
    		String title = map.get("title");
			String brief = map.get("brief");
			String date = map.get("date");
			String article = map.get("article");
			//是不存在的文章
			int flag = EqManager.countHql(new Eq(), " where o.date= '"+date+"' ");
			
			if(flag<=0){
				//生成并且设置图片
				List<String> meizi = HTMLParserUtil.gegeMEIZITU();
				
				int index = HTMLParserUtil.getRandomOne(meizi);
				String img = meizi.get(index);
				
	    		Eq eq = new Eq();
	    		eq.setArticle(article);
	    		eq.setBrief(brief);
	    		eq.setDate(date);
	    		eq.setTitle(title);
	    		eq.setImg(img);
	    		EqManager.addEq(eq);
			}
			
			//去重
			String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM bc_eq GROUP BY date HAVING ( COUNT(date) > 1 )) AS p)" ;
			
			EqManager.executeSql(delsql);
			
			
			
			
			

    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    	}

		
		try {
			
			System.out.println("soft task==============start="+TimeUtils.getNowTime());
			logger.info("soft task==============start="+TimeUtils.getNowTime());
			Map<String,String> map = null;
				
			List<Map<String, String>> list = HTMLParserUtil.retSoft(1);
			
			
			if(!CollectionUtils.isEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					map  = list.get(i);
					
					String title = map.get("title");
					String aurl = map.get("aurl");
					String brief = map.get("brief");
					String date = map.get("date");
					String article = map.get("article");
					String tag = map.get("tag");
					String code = map.get("code");
					String os = map.get("os");
					String month  = map.get("month");
					String year  = map.get("year");
					String tagcode = map.get("tagcode");
					
					

					//是不存在的文章
					int flag = softManager.countHql(new Soft(), " where o.retcode= '"+code+"' ");
					
					if(flag<=0){
						
			    		Soft model = new Soft();
			    		model.setBrief(brief);
			    		model.setContent(article);
			    		model.setOs(os);
			    		model.setPostdate(date);
			    		model.setRetcode(code);
			    		model.setReturl(aurl);
			    		model.setTags(tag);
			    		model.setTitle(title);
			    		model.setMonth(month);
			    		model.setYear(year);
			    		model.setTagscode(tagcode);
			    		softManager.addSoft(model);
					}
				}
			}
			
			
			logger.info("soft task==============end="+TimeUtils.getNowTime());
			logger.trace("soft task==============end="+TimeUtils.getNowTime());
			System.out.println("soft task==============end="+TimeUtils.getNowTime());
			
			/////////////////////推送微信定时星座运势塔罗牌天气、、、、、、、、、、、、、、、、、、、、、、、、
			
			logger.info("send wx astro msg task==============start="+TimeUtils.getNowTime());
			try {
				List<Astro> astrolist = astroManager.findByCondition(" where status = 0").getResultlist();
				if(!CollectionUtils.isEmpty(astrolist)){
					for (int i = 0; i < astrolist.size(); i++) {
						Astro astro = astrolist.get(i);
						String wx_cop_userid = astro.getWx_cop_userid();
						String xzname = astro.getXzname();
						
						JSONObject json = WXTokenUtil.getXZYS(xzname, "today");

						// 调取凭证
						String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
						TextMessage text = new TextMessage();
						
						StringBuffer buffer = new StringBuffer();
						buffer.append("\ue409").append(xzname).append("\n\n");
						buffer.append("\ue21c").append(TimeUtils.nowdate()).append("\n");
						buffer.append("\ue21d 综合运势:").append(json.get("summary")).append("\n");
						buffer.append("\ue21d 贵人星座:").append(json.get("QFriend")).append("\n");
						buffer.append("\ue21d 爱情运势:").append(json.get("love")).append("\n");
						buffer.append("\ue21d 幸运颜色:").append(json.get("color")).append("\n");
						buffer.append("\ue21d 工作运势:").append(json.get("work")).append("\n");
						buffer.append("\ue21d 幸运数字:").append(json.get("number")).append("\n");
						buffer.append("\ue21d 财运运势:").append(json.get("money")).append("\n");
						buffer.append("\ue21d 健康运势:").append(json.get("health")).append("\n");
						String content = buffer.toString();  
						
						text.setContent(content);
						                      
						// 图文转json
						String articlesList = JSONArray.toJSONString(list);
						// Post的数据
						String PostData =Message.sendTextMsg(wx_cop_userid, "@all", "@all", "5", articlesList);
						String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
						int result = WeixinQyhUtil.PostMessage(access_token, "POST", POST_URL, PostData);
						logger.info("send wx astro msg info log result==============="+result);
						// 打印结果
						if (0 == result) {
							System.out.println("操作成功");
						} else {
							System.out.println("操作失败");
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			logger.info("send wx astro msg task==============end="+TimeUtils.getNowTime());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

		
		System.out.println("情圣定时任务结束"+TimeUtils.getNowTime());
	}
	
	
	
	/**
	 * 关注推送测试
	 * 
	 * @return
	 */
	public static String getMainMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue409，我是学霸测试机器人，请回复数字选择服务").append("\n\n");
		buffer.append("\ue21c  砸别人家玻璃").append("\n");
		buffer.append("\ue21d  抢小孩子的糖").append("\n");
		buffer.append("\ue21e  勾引别人老婆").append("\n");
		buffer.append("\ue21f  骂别人是傻逼").append("\n\n");
		buffer.append("回复“?”返回菜单");
		return buffer.toString();
	}
	
}
