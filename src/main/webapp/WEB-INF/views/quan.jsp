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
<title>NorthPark / 红包</title>
<meta name="description" content="NorthPark::最及时红包">
<meta name="keywords" content="NorthPark::最及时红包">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body style="">

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback">
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		
		     <div class="view ">
                <form id="J_tag_fm" class=" form-inline margin-t20" action="/cp/search" method="post">
                 <%--  <input id="keyword" placeholder="美团|滴滴|饿了么.."    value="${keyword }"	class="form-control input-lg  border-light-1 bg-lyellow grid98 radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" class="form-control btn btn-hero " value="搜索" type="submit"> --%>
                    <div class="clearfix align-center  margin-t10 margin-b10 padding20">
                     	<input type="hidden"  id="keyword"  name="keyword"  value="${keyword}">
                    	<c:forEach items="${B_tags }" var="tags" varStatus="tagsStatus">
                    		<input class="btn tag-node" type="button" value="${tags }">
                    	</c:forEach>
                    	<span id="J_clear_tag" class="glyphicon glyphicon-repeat" style="padding-left: 10px" aria-hidden="true"></span>
                    	
                    </div>
                   
                </form>
              </div> 
		
		
			<div class="row">
					
					<div class="col-sm-12 ">
					<div class="clearfix align-center bg-white margin-t10 margin-b10 padding20">
							<p class="align-center"><small class="label label-gray"></small>~本页面数据用redis缓存实现，定时爬取最新的数千红包地址。想抢各种优惠券请收藏本页面呦~	 &nbsp; </p>

					</div>
					
					</div>
					<c:forEach items="${quan }" var="s" varStatus="ss">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-12">
										<div class="thumbnail border-0 center">
											<p><small class="gray-text">红包来源:【<font size="5"> ${s.from} </font>】</small></p>
											
											<p><small class="gray-text">发布时间: 【 ${s.publishtime} 】</small></p>
											
											

											<p><small  class="gray-text" >红包提供者:	 &nbsp; 【 ${s.authorIP } 】</small></p>
											
											<p> 
											 <%-- <button class="clearfix btn btn-green btn-xs click2show" onclick="showquan('${s.id}')"  <span class="glyphicon glyphicon-yen">领取红包</span> &nbsp; </button>
											  --%>
											 <a  onclick="showquan('${s.id}')" class="btn btn-hero"><span class="glyphicon glyphicon-plus"></span>领取红包</a>
											 </p>
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
								</div>
							</div>
					</div>
					</c:forEach>
					
					
		  	</div>

		  	
		  	
		</div>
	</div>
</div>

	

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
	<script src="/js/page/quan.js"></script>



</body></html>