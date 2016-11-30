<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>明日概述：</h3>
<p>${data.summary }
</p>
<p>综合运势：${data.all }</p>
<p>贵人星座：${data.QFriend }</p>
<p>爱情运势：${data.love }</p>
<p>幸运颜色：${data.color }</p>
<p>工作运势：${data.work }</p>
<p>幸运数字：${data.number }</p>
<p>财运运势：${data.money }</p>
<p>健康运势：${data.health }</p>
