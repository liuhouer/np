<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>	<%@ include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ut-8">
<title>检查用户名</title>
</head>
<body topmargin="0" leftmargin="0">

<table width="300" height="200" border="0" align="center" cellpadding="0" cellspacing="0">
	<c:if test="${flag eq true }"><li> <font color="red">此用户名已经存在，请选用一个新用户名！</font></li></c:if>
	<c:if test="${flag eq false }"><li><font color="green">此用户名可以使用！</font></li></c:if>
	 
	<p align="center"> <a href="javascript:window.close()" >关 闭</a><br>
	  <br>
	</p>
</table>
 
</body>
</html>