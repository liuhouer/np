package cn.northpark.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.support.json.JSONUtils;

import cn.northpark.manager.AstroManager;
import cn.northpark.model.Astro;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.wx.WXTokenUtil;
import cn.northpark.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.utils.wx.qyh.ParamesAPI.ParamesAPI;
import cn.northpark.utils.wx.qyh.msg.Message;


/**
 * @author zhangyang
 * <p>
 * 定时推送微信消息
 */
public class AstroTask {

	
	@Autowired
	public  AstroManager astroManager;

    private static final Logger LOGGER = Logger
            .getLogger(AstroTask.class);


    public void runTask() {
      	try {

            /////////////////////推送微信定时星座运势塔罗牌天气、、、、、、、、、、、、、、、、、、、、、、、、

			LOGGER.info("send wx astro msg task==============start="+TimeUtils.getNowTime());
			try {
				List<Astro> astrolist = astroManager.findByCondition(" where status = 1").getResultlist();
				if(!CollectionUtils.isEmpty(astrolist)){
					for (int i = 0; i < astrolist.size(); i++) {
						Astro astro = astrolist.get(i);
						String wx_cop_userid = astro.getWx_cop_userid();
						String xzname = astro.getXzname();
						
						Map<String, Object> json = JsonUtil.json2map(WXTokenUtil.getXZYS(xzname, "today"));

						// 调取凭证
						String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
						
						StringBuffer buffer = new StringBuffer();
						buffer.append("\ue423").append(xzname).append("\ue31f").append("\n\n");
						buffer.append("\ue21c").append(TimeUtils.nowdate()).append("\n");
						buffer.append("\ue21d 综合运势:").append(json.get("presummary")).append("\n");
						buffer.append("\ue21e 贵人星座:").append(json.get("star")).append("\n");
						buffer.append("\ue21f 爱情运势:").append(json.get("love")).append("\n");
						buffer.append("\ue220 幸运颜色:").append(json.get("color")).append("\n");
						buffer.append("\ue221 工作运势:").append(json.get("career")).append("\n");
						buffer.append("\ue222 幸运数字:").append(json.get("number")).append("\n");
						buffer.append("\ue223 财运运势:").append(json.get("money")).append("\n");
						buffer.append("\ue224 健康运势:").append(json.get("health")).append("\n");
						String content = buffer.toString();  
						
						                      
						//发送消息的jsontext
						String jsostr = Message.getSendJsonText(wx_cop_userid, "@all", "@all", content);
						String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
						int result = WeixinQyhUtil.PostMessage(access_token, "POST", POST_URL, jsostr);
						System.out.println("jsonstr--"+jsostr);
						System.out.println("result--"+result);
						LOGGER.info("send wx astro msg info log result==============="+result);
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
			LOGGER.info("send wx astro msg task==============end="+TimeUtils.getNowTime());
        } catch (Exception e) {
            // TODO: handle exception
        }


    }


}
