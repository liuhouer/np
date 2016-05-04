<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<meta http-equiv="Content-Language" content="zh-CN">

<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon" href="img/favicon.ico">
<title>布.词 | 电影::潮流</title>
<meta name="description" content="布.词::电影::潮流">
<meta name="keywords" content="布.词::电影::潮流">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body style="">

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		
		     <div class="view ">
                <form class=" form-inline margin-t20" action="/movies/search" method="post">
                  <input id="keyword" placeholder="电影名"    value="${keyword }"	class="form-control input-lg  border-light-1 bg-lyellow grid98 radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" class="form-control btn btn-hero " value="搜索" type="submit">
                </form>
              </div>
		
		
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-3">
										<div class="thumbnail border-0 center">
											<p><small class="green-text">${s.moviename}</small></p>
											
											<p><small class="red-text">￥${s.price}</small></p>
											
											<p> 
											 <button class="clearfix btn btn-gray btn-xs click2show" onclick="pay('${s.id}','${s.price }')"  <span class="glyphicon glyphicon-yen">donate</span> &nbsp; </button>
											 
											 </p>
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-9">
										<p><small class="label label-gray"><p><small class="red-text">${s.path}</small></p></small> &nbsp; <a 
										
										title="${s.moviename}的简介">${s.moviename}</a> 简介：</p>
										<p id="brief_${ss.index}">
										
										  ${s.description }
										</p>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 

									 

			
			<div style="display: none">
			
			 <form name="alipayment" id="J_pay_form" action="/order/toPay" method="post" target="_blank">
												<div id="body" style="clear: left">
													<dl class="content">
														<dt>商户订单号：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"  
																name="WIDout_trade_no" /> <span>商户网站订单系统中唯一订单号，必填
															</span>
														</dd>
														<dt>订单名称：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDsubject" value="购买电影"/> <span>必填 </span>
														</dd>
														<dt>付款金额：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDprice" value="" id="J_price"/> <span>必填 </span>
														</dd>
														<dt>订单描述 ：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDbody" value="购买电影"/> <span></span>
														</dd>
														<dt>商品展示地址：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDshow_url" value=""/> <span>需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
															</span>
														</dd>
														<dt>收货人姓名：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDreceive_name" value="${user.username }"/> <span>如：张三 </span>
														</dd>
														<dt>收货人地址：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDreceive_address" value="北京市朝阳区国典华园4号楼#306"/> <span>如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
															</span>
														</dd>
														<dt>收货人邮编：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDreceive_zip" value="100096"/> <span>如：123456 </span>
														</dd>
														<dt>收货人电话号码：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDreceive_phone" value="0571-88158090" /> <span>如：0571-88158090
															</span>
														</dd>
														<dt>收货人手机号码：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30"
																name="WIDreceive_mobile" value="18612345678"/> <span>如：13312341234</span>
														</dd>
														<dt>电影编码：</dt>
														<dd>
															<span class="null-star">*</span> <input size="30" id="J_movieid"
																name="movie_id" value=""/> <span>如：dfjhdfjgj</span>
														</dd>
														<dt></dt>
														<dd>
															<span class="new-btn-login-sp">
																<button class="new-btn-login" type="submit" id="J_sub_ali"
																	style="text-align: center;">确 认</button>
															</span>
														</dd>
													</dl>
												</div>
											</form>  
			</div>	
								 
						 
					
		  	</div>

		  	
		  	
		</div>
	</div>
</div>

	
	<!-- <script>document.forms['alipaysubmit'].submit();</script> -->

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script type="text/javascript">
	$("body").on('click', '.click2show', function() {
			$(this).hide();
	});
	
	function pay(movie_id,price){
		
		if(movie_id && price){
			$("#J_movieid").val(movie_id);
			$("#J_price").val(price);
			$("#J_sub_ali").click();
		}
		
	}
	
	</script>



</body></html>