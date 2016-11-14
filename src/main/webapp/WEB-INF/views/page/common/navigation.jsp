<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="navbar navbar-default navbar-fixed-top mainhead-navbox" role="navigation">

	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle mainhead-navbtn" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">菜单导航</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="navbar-brand">
					<a href="/" class="mainhead-avatar"><img src="/img/davatar.jpg" class="img-circle max-width-50" height="40" width="40"></a>
					&nbsp; &nbsp; <a href="/lyrics/add" class="btn btn-hero"><span class="glyphicon glyphicon-plus"></span> 添加</a>
					&nbsp; &nbsp; 
					<c:if test="${user!=null }"><a href="/cm/logout" id="J_log_info_l" ><span class="glyphicon glyphicon-off"></span>退出</a></c:if>
					<c:if test="${user==null }"><a href="/login" id="J_log_info_l" ><span class="glyphicon glyphicon-on"></span>登陆</a></c:if>
							</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav" id="J_tabs">
				
				<li cname="pic"><a href="/love" title="一张图片，爱满满的">最爱</a></li>
				<li cname="note"><a href="/note/list" title="一段歌词，一段回忆">碎碎念</a></li>
				<li cname="soft"><a href="/soft/mac" title="丰富的mac软件资源">Mac软件</a></li>
				<li cname="equp"><a href="/romeo" title="情圣养成日记">情圣日记</a></li>
				<li cname="pcenter">
				<a id="J_log_info_r" <c:if test="${user!=null }"> href="/cm/pcentral"</c:if><c:if test="${user==null }">href="/login"</c:if>><c:if test="${user!=null }">我自己</c:if><c:if test="${user==null }">登陆</c:if></a>
				</li>
			</ul>
		</div>
	</div>
</div>
