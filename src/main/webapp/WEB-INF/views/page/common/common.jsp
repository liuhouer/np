<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <base href="<%=basePath%>"> --%>
<!-- <base href="http://northpark.cn"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--QQ登陆  -->
<!-- <meta property="qc:admins" content="61073355176476541272743636" /> 

<script type="text/javascript" data-callback="true" 
 src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101204466" data-redirecturi="http://northpark.cn/love" charset="utf-8"></script>
  -->

<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/qinco.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/main2.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrapValidator.min.css">



<input  type="hidden" id="J_tab_name" value="${ tabs}"/>




