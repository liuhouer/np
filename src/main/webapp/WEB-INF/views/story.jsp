<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="author" content="www.qinco.net">
    <meta name="robots" content="index,follow,archive">
    <link rel="shortcut icon" href="/img/favicon.ico">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <c:if test="${page==null || page==''}">
        <title>碎碎念-精神角落 | NorthPark</title>
    </c:if>
    <c:if test="${page!=null && page!=''}">
        <title>碎碎念-精神角落::第${page}页 | NorthPark</title>
    </c:if>


    <meta name="keywords" content="NorthPark,碎碎念,树洞,精神角落">
    <meta name="description" content="NorthPark碎碎念的精神角落。富有交互性和趣味性，文艺范和小清新。">
</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant">碎碎念-精神角落</h1>
<div class="clearfix maincontent grayback">
    <div class="container">
        <div class="mainbody padding-t20" id="J_maincontent" style="margin-top:70px;">


            <div id="J_progress" class="center padding-t20"></div>


        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/page/common/fenye.jsp" %>

<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script src="/js/page/story.js"></script>
</body>
</html>
