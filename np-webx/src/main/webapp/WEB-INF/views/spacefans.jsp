<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
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
    <link rel="shortcut icon" href="https://northpark.cn/statics/img/favicon.ico">
    <title>${MyInfo.username}的迷妹们呦 ໒( ͡◕ ᴥ ◕͡ )७| NorthPark</title>
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>

</head>

<body style="">
<form action="" method="post" id="f2">
    <input name="userid" value="${MyInfo.id }" type="hidden">
</form>
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<%@ include file="/WEB-INF/views/page/common/centrespace.jsp" %>

<input type="hidden" id="J_uid" value="${ user.id}"/>

<input type="hidden" id="J_gz" value="${gz }"/>

<div class="clearfix maincontent">
    <div class="container">
        <div class="mainbody padding10" style="margin-top:2em;">

            <div class="clearfix margin-b20">
                <ul class="nav nav-tabs">
                    <li><a


                            <c:if test="${MyInfo.tailSlug==null || MyInfo.tailSlug==''}">
                                href="/cm/channel/${MyInfo.id}"
                            </c:if>
                            <c:if test="${MyInfo.tailSlug!=null }">
                                href="/people/${MyInfo.tailSlug}"
                            </c:if>

                    >最爱</a></li>
                    <li><a href="/note/viewNotes/${MyInfo.id}">留言</a></li>
                    <li class="active"><a href="/cm/fans/${MyInfo.id}">Fans</a></li>

                </ul>
            </div>
            <c:forEach items="${fansList }" var="s" varStatus="ss">
                <div class="row">
                    <div class="col-md-2">
                        <h3 class="label label-gray">${s.follow.create_time }：</h3>
                    </div>

                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-xs-4 col-sm-2 center avatar">
                                <a
                                        <c:if test="${s.user.tailSlug==null || s.user.tailSlug==''}">
                                            href="/cm/channel/${s.user.id}"
                                        </c:if>
                                        <c:if test="${s.user.tailSlug!=null }">
                                            href="/people/${s.user.tailSlug }"
                                        </c:if>

                                        title="${s.user.username }" class="thumbnail border-0 ">


                                    <c:if test="${s.user.headPath==null }">
                                        <span class=" ${s.user.headSpanClass }"
                                              alt="${s.user.username}">${s.user.headSpan }</span>

                                    </c:if>
                                    <c:if test="${s.user.headPath!=null }">
                                        <img alt="${s.user.username }的最爱"
                                        <c:choose>
                                             <c:when test="${fn:contains(s.user.headPath ,'http://') }">src="${s.user.headPath}"</c:when>
                                             <c:otherwise>src="/bruce/${s.user.headPath }"</c:otherwise>
                                        </c:choose>

                                        >
                                    </c:if>


                                    <p>${s.user.username }</p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>
            </c:forEach>

        </div>
        <br>
        <br>

    </div>

</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script data-cfasync="false" src="https://northpark.cn/statics/js/page/spacefans.js"></script>


</body>
</html>
