package cn.northpark.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.northpark.utils.wx.qyh.ParamesAPI.ParamesAPI;
import cn.northpark.utils.wx.qyh.encryption.AesException;
import cn.northpark.utils.wx.qyh.encryption.WXBizMsgCrypt;

@Controller
@RequestMapping("/wx")
public class WeixinAction  extends MultiActionController  {

	
	
	/**
	 * @author jeyy - 验证企业号的oauth请求
	 * @param request
	 * @param response
	 * @return sEchoStr
	 */
	@RequestMapping("/qyh/verfy")
	public void verfy(HttpServletRequest request,  HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		try {

			String sToken = ParamesAPI.token;
			String sCorpID = ParamesAPI.corpId;
			String sEncodingAESKey = ParamesAPI.encodingAESKey;

			/*
			 * 假定公众平台上开发者设置的Token 1. 验证回调URL 点击验证时，企业收到类似请求： GET
			 * /cgi-bin/wxpush?msg_signature
			 * =5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3
			 * &timestamp=1409659589&nonce=263014780
			 * &echostr=P9nAzCzyDtyTWESHep1vC5X9xho
			 * %2FqYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp
			 * %2B4RPcs8TgAE7OaBO%2BFZXvnaqQ%3D%3D HTTP/1.1 Host:
			 * qy.weixin.qq.com 接收到该请求时，企业应1.先验证签名的正确性 2. 解密出echostr原文。
			 * 以上两步用VerifyURL完成
			 */

			// 微信加密签名
			String sVerifyMsgSig = request.getParameter("msg_signature");
			// 时间戳
			String sVerifyTimeStamp = request.getParameter("timestamp");
			// 随机数
			String sVerifyNonce = request.getParameter("nonce");
			// 随机字符串
		 
			String sVerifyEchoStr = request.getParameter("echostr") ;


			String sEchoStr; // 需要返回的明文
			PrintWriter out = response.getWriter();
			WXBizMsgCrypt wxcpt;
			try {
				wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
				sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
						sVerifyNonce, sVerifyEchoStr);
				System.out.println("fanhui---->"+sEchoStr);
				// 验证URL成功，将sEchoStr返回
				out.print(sEchoStr);
			} catch (AesException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	

	
	

}
