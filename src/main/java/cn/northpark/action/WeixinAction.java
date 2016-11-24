package cn.northpark.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import cn.northpark.utils.wx.qyh.ParamesAPI.ParamesAPI;
import cn.northpark.utils.wx.qyh.encryption.AesException;
import cn.northpark.utils.wx.qyh.encryption.WXBizMsgCrypt;
import cn.northpark.utils.wx.qyh.service.CoreService;

@Controller
@RequestMapping("/wx")
public class WeixinAction  extends MultiActionController  {

	
	
	/**
	 * @author jeyy - 验证企业号的请求
	 * @param request
	 * @param response
	 * @return sEchoStr
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
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
	

	
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
	public  String processRequest(HttpServletRequest request,HttpServletResponse response) {
		
		
		String encryptMsg = "";
		try {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// 微信加密签名
			String msg_signature = request.getParameter("msg_signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");

			// 从请求中读取整个post数据
			InputStream inputStream = request.getInputStream();
			// commons.io.jar 方法
			String Post = IOUtils.toString(inputStream, "UTF-8");
			// Post打印结果
			System.out.println(Post);
			System.out.println("msg_signature========="+msg_signature);
			System.out.println("timestamp==========="+timestamp);
			System.out.println("nonce========"+nonce);

			String Msg = "";
			WXBizMsgCrypt wxcpt = null;
			try {
				wxcpt = new WXBizMsgCrypt(ParamesAPI.token, ParamesAPI.encodingAESKey, ParamesAPI.corpId);
				
				System.out.println("WXBizMsgCrypt entity===="+wxcpt);
				// 解密消息
				Msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, Post);
				
				System.out.println("after decrypt msg: " + Msg);
				// TODO: 解析出明文xml标签的内容进行处理
				// For example:
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				StringReader sr = new StringReader(Msg);
				InputSource is = new InputSource(sr);
				Document document = db.parse(is);

				Element root = document.getDocumentElement();
				NodeList nodelist1 = root.getElementsByTagName("Content");
				if(nodelist1!=null){
					if(nodelist1.getLength()>0){
						String Content = nodelist1.item(0).getTextContent();
						System.out.println("Content：" + Content);
					}
				}
				
				
			} catch (AesException e) {
				e.printStackTrace();
			}
			// Msg打印结果
			System.out.println("Msg打印结果：" + Msg);

			// 调用核心业务类接收消息、处理消息
			String respMessage = CoreService.processRequest(Msg);

			// respMessage打印结果
			System.out.println("respMessage打印结果：" + respMessage);
			
			try {
				// 加密回复消息
				encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
			} catch (AesException e) {
				e.printStackTrace();
			}

			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(encryptMsg);
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		return encryptMsg;
	}
	
	

}
