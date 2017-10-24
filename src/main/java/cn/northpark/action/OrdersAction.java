//
//package cn.northpark.action;
///*
//*@author bruce
//*
//**/
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import cn.northpark.manager.MoviesManager;
//import cn.northpark.manager.OrdersManager;
//import cn.northpark.model.Movies;
//import cn.northpark.model.Orders;
//import cn.northpark.query.OrdersQuery;
//import cn.northpark.utils.TimeUtils;
//import cn.northpark.utils.alipay.config.AlipayConfig;
//import cn.northpark.utils.alipay.util.AlipayNotify;
//import cn.northpark.utils.alipay.util.AlipaySubmit;
//
//@Controller
//@RequestMapping("/order")
//@SessionAttributes({ "list", "orders" })
//public class OrdersAction {
//
// @Autowired	
// private OrdersManager ordersManager;
// @Autowired	
// private OrdersQuery ordersQuery;
// @Autowired	
// private MoviesManager moviesManager;
//
//	/**等待买家付款
//	 * 
//	 */
//	 public final String WAIT_BUYER_PAY	            = "1";//等待买家付款
//	 
//	 /**买家已付款，等待卖家发货
//		 * 
//		 */
//	 public final String WAIT_SELLER_SEND_GOODS	    = "2";//买家已付款，等待卖家发货
//	 
//	 /**卖家已发货，等待买家确认
//		 * 
//		 */
//	 public final String WAIT_BUYER_CONFIRM_GOODS	= "3";//卖家已发货，等待买家确认
//	 
//	 /**交易成功结束
//		 * 
//		 */
//	 public final String TRADE_FINISHED	            = "4";//交易成功结束
//	 
//	 /**交易中途关闭（已结束，未成功完成）
//		 * 
//		 */
//	 public final String TRADE_CLOSED	            = "5";//交易中途关闭（已结束，未成功完成）
//	 
//	 
//	 /**等待卖家发货（货到付款）
//		 * 
//		 */
//	 public final String COD_WAIT_SELLER_SEND_GOODS	= "6";//等待卖家发货（货到付款）
//	 
//	 /**等待买家签收付款（货到付款）
//		 * 
//		 */
//	 public final String COD_WAIT_BUYER_PAY	        = "7";//等待买家签收付款（货到付款）
//	 
//	 /**签收成功等待系统打款给卖家（货到付款）
//		 * 
//		 */
//	 public final String COD_WAIT_SYS_PAY_SELLER	= "8";//签收成功等待系统打款给卖家（货到付款）
//	
//	
//	@RequestMapping("/toPay")
//	public String toPay(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
//		String movie_id = new String(request.getParameter("movie_id"));
//	    //付款金额
//		String price = new String(request.getParameter("WIDprice"));
//	    
////		String price = new String("0.01");
//	    Orders order = new Orders();
//	    order.setAddtime(TimeUtils.nowTime());
//	    order.setFee(Integer.parseInt(price));
////	    order.setFee(1);
//	    order.setMovie_id(movie_id);
//	    order.setStatus(WAIT_BUYER_PAY);
//	    ordersManager.addOrders(order);
//	    
//	    String orderid = String.valueOf(order.getId());
//			////////////////////////////////////请求参数//////////////////////////////////////
//					
//			//支付类型
//			String payment_type = "1";
//			//必填，不能修改
//			//服务器异步通知页面路径
//			String notify_url = "http://northpark.cn/order/notify";
//			//需http://格式的完整路径，不能加?id=123这类自定义参数
//			
//			//页面跳转同步通知页面路径
//			String return_url = "http://northpark.cn/order/verfyPay";
//			//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
//			
//			//商户订单号
//			String out_trade_no = orderid;
//			//商户网站订单系统中唯一订单号，必填
//			
//			//订单名称
//			String subject = new String(request.getParameter("WIDsubject"));
//			//必填
//			
//			//付款金额
//			//String price = new String(request.getParameter("WIDprice"));
//			//必填
//			
//			//商品数量
//			String quantity = "1";
//			//必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
//			//物流费用
//			String logistics_fee = "0.00";
//			//必填，即运费
//			//物流类型
//			String logistics_type = "EXPRESS";
//			//必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
//			//物流支付方式
//			String logistics_payment = "SELLER_PAY";
//			//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
//			//订单描述
//			
//			String body = new String(request.getParameter("WIDbody"));
//			//商品展示地址
//			String show_url = new String(request.getParameter("WIDshow_url"));
//			//需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
//			
//			//收货人姓名
//			String receive_name = new String(request.getParameter("WIDreceive_name"));
//			//如：张三
//			
//			//收货人地址
//			String receive_address = new String(request.getParameter("WIDreceive_address"));
//			//如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
//			
//			//收货人邮编
//			String receive_zip = new String(request.getParameter("WIDreceive_zip"));
//			//如：123456
//			
//			//收货人电话号码
//			String receive_phone = new String(request.getParameter("WIDreceive_phone"));
//			//如：0571-88158090
//			
//			//收货人手机号码
//			String receive_mobile = new String(request.getParameter("WIDreceive_mobile"));
//			//如：13312341234
//			
//			
//			//////////////////////////////////////////////////////////////////////////////////
//			
//			//把请求参数打包成数组
//			Map<String, String> sParaTemp = new HashMap<String, String>();
//			sParaTemp.put("service", "create_partner_trade_by_buyer");
//			sParaTemp.put("partner", AlipayConfig.partner);
//			sParaTemp.put("seller_email", AlipayConfig.seller_email);
//			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//			sParaTemp.put("payment_type", payment_type);
//			sParaTemp.put("notify_url", notify_url);
//			sParaTemp.put("return_url", return_url);
//			sParaTemp.put("out_trade_no", out_trade_no);
//			sParaTemp.put("subject", subject);
//			sParaTemp.put("price", price);
//			sParaTemp.put("quantity", quantity);
//			sParaTemp.put("logistics_fee", logistics_fee);
//			sParaTemp.put("logistics_type", logistics_type);
//			sParaTemp.put("logistics_payment", logistics_payment);
//			sParaTemp.put("body", body);
//			sParaTemp.put("show_url", show_url);
//			sParaTemp.put("receive_name", receive_name);
//			sParaTemp.put("receive_address", receive_address);
//			sParaTemp.put("receive_zip", receive_zip);
//			sParaTemp.put("receive_phone", receive_phone);
//			sParaTemp.put("receive_mobile", receive_mobile);
//			
//			//建立请求
//			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
//			request.getSession().setAttribute("text", sHtmlText);
//		return "/alipayapi";
//	}
//	
//	//获取交易成功与否的信息
//	@RequestMapping("/verfyPay")
//	public String verfyPay(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
//		
//		try{
//	//获取支付宝GET过来反馈信息
//		Map<String,String> params = new HashMap<String,String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			params.put(name, valueStr);
//		}
//		
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		//商户订单号
//
//		String out_trade_no = new String(request.getParameter("out_trade_no"));
//
//		//支付宝交易号
//
//		String trade_no = new String(request.getParameter("trade_no"));
//
//		//交易状态
//		String trade_status = new String(request.getParameter("trade_status"));
//
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//		
//		//计算得出通知验证结果
//		boolean verify_result = AlipayNotify.verify(params);
//		
//		if(verify_result){//验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			//请在这里加上商户的业务逻辑程序代码
//			
//			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//			
//			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
//				//判断该笔订单是否在商户网站中已经做过处理
//					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					//如果有做过处理，不执行商户的业务程序
//				
//				//自动发货操作
//				
//						////////////////////////////////////请求参数//////////////////////////////////////
//						
//						//支付宝交易号
////						String trade_no = new String(request.getParameter("WIDtrade_no").getBytes("ISO-8859-1"),"UTF-8");
//						//必填
//						
//						//物流公司名称
//						String logistics_name = new String("顺丰快递");
//						//必填
//						
//						//物流发货单号
//						
//						String invoice_no = new String("SF"+new Date().getTime());
//						//物流运输类型
//						String transport_type = new String("EXPRESS");
//						//三个值可选：POST（平邮）、EXPRESS（快递）、EMS（EMS）
//						
//						
//						//////////////////////////////////////////////////////////////////////////////////
//						
//						//把请求参数打包成数组
//						Map<String, String> sParaTemp = new HashMap<String, String>();
//						sParaTemp.put("service", "send_goods_confirm_by_platform");
//						sParaTemp.put("partner", AlipayConfig.partner);
//						sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//						sParaTemp.put("trade_no", trade_no);
//						sParaTemp.put("logistics_name", logistics_name);
//						sParaTemp.put("invoice_no", invoice_no);
//						sParaTemp.put("transport_type", transport_type);
//						
//						//建立请求
//						String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
//						
//						request.getSession().setAttribute("sendgood", sHtmlText);
//						
//						Orders order = ordersManager.findOrders(Integer.parseInt(out_trade_no));
//						if(order!=null){
//							Movies movies = moviesManager.findMovies(Integer.parseInt(order.getMovie_id()));
//							if(movies!=null){
//								map.put("curl", movies.getPath());
//							}
//						}
//						
//						
//			}
//			
//			//该页面可做页面美工编辑
//			//out.println("验证成功<br />");
//			
//			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//			map.put("status", "支付验证成功");
//
//			//////////////////////////////////////////////////////////////////////////////////////////
//		}else{
//			//该页面可做页面美工编辑
//			//out.println("验证失败");
//			map.put("status", "支付验证失败");
//		}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return "/return_url";
//	}
//	
//	
//	
//	//异步获取交易成功与否的信息
//		@RequestMapping("/notify")
//		public String notify(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
//			
//			try{
//				//获取支付宝POST过来反馈信息
//				Map<String,String> params = new HashMap<String,String>();
//				Map requestParams = request.getParameterMap();
//				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//					String name = (String) iter.next();
//					String[] values = (String[]) requestParams.get(name);
//					String valueStr = "";
//					for (int i = 0; i < values.length; i++) {
//						valueStr = (i == values.length - 1) ? valueStr + values[i]
//								: valueStr + values[i] + ",";
//					}
//					//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//					//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//					params.put(name, valueStr);
//				}
//				
//				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//				//商户订单号
//
//				String out_trade_no = new String(request.getParameter("out_trade_no"));
//
//				//支付宝交易号
//
//				String trade_no = new String(request.getParameter("trade_no"));
//
//				//交易状态
//				String trade_status = new String(request.getParameter("trade_status"));
//
//				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//
//				if(AlipayNotify.verify(params)){//验证成功
//					//////////////////////////////////////////////////////////////////////////////////////////
//					//请在这里加上商户的业务逻辑程序代码
//
//					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//					
//					if(trade_status.equals("WAIT_BUYER_PAY")){
//						//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
//						
//							//判断该笔订单是否在商户网站中已经做过处理
//								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					    			//请务必判断请求时的price、quantity、seller_id与通知时获取的price、quantity、seller_id为一致的
//								//如果有做过处理，不执行商户的业务程序
//							
////							out.println("success");	//请不要修改或删除
//							map.put("status", "success");
//						} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
//						//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
//						
//							//判断该笔订单是否在商户网站中已经做过处理
//								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					    			//请务必判断请求时的price、quantity、seller_id与通知时获取的price、quantity、seller_id为一致的
//								//如果有做过处理，不执行商户的业务程序
//							
////							out.println("success");	//请不要修改或删除
//							map.put("status", "success");
//						} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
//						//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
//						
//							//判断该笔订单是否在商户网站中已经做过处理
//								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					    			//请务必判断请求时的price、quantity、seller_id与通知时获取的price、quantity、seller_id为一致的
//								//如果有做过处理，不执行商户的业务程序
//							
////							out.println("success");	//请不要修改或删除
//							map.put("status", "success");
//						} else if(trade_status.equals("TRADE_FINISHED")){
//						//该判断表示买家已经确认收货，这笔交易完成
//						
//							//判断该笔订单是否在商户网站中已经做过处理
//								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					    			//请务必判断请求时的price、quantity、seller_id与通知时获取的price、quantity、seller_id为一致的
//								//如果有做过处理，不执行商户的业务程序
//							
////							out.println("success");	//请不要修改或删除
//							map.put("status", "success");
//						}
//						else {
////							out.println("success");	//请不要修改或删除
//							map.put("status", "success");
//						}
//
//					//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//
//					//////////////////////////////////////////////////////////////////////////////////////////
//				}else{//验证失败
////					out.println("fail");
//					map.put("status", "fail");
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			return "/return_url";
//		}
//		
//
//}
