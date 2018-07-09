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
        <title>情商提升 | NorthPark</title>
    </c:if>
    <c:if test="${page!=null && page!=''}">
        <title>情商提升日记::第${page}页 | NorthPark</title>
    </c:if>
    <meta name="keywords" content="NorthPark,情商提升,情圣日记，撩妹技巧">
    <meta name="description"
          content="NorthPark情商提升的技巧和讲解">
</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>


<!-- 页面标题 -->
<h1 class="font-elegant">情商提升</h1>
<div class="clearfix maincontent grayback">
    <div class="container">

        <div class="mainbody" id="J_maincontent">


            <div class="row">
                <div class="col-sm-8 margin-b10">
                    <form class=" form-search " action="/movies/search" method="post" accept-charset="UTF-8">
                        <input id="keyword" placeholder="约不出来怎么办" value="${keyword }"
                               class="width100 input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0"
                               name="keyword" type="text">
                        <input data-activetext="搜索 ››" id="J_search" class=" btn btn-hero " value="搜索" type="button">
                    </form>
                </div>

                <div id="J_progress" class="center padding-t20"></div>

                <c:forEach items="${list }" var="s" varStatus="ss">
                    <div class="col-sm-6">
                        <div class="clearfix bg-white margin-t10 margin-b10 padding20">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="thumbnail border-0 center">
                                        <a title="${s.title}">
                                            <c:if test="${s.img==null ||s.img==''}">
                                                <img src="/img/davatar.jpg" alt="${s.title}" class="imgbreath">
                                            </c:if>
                                            <c:if test="${s.img!=null }">
                                                <img src="${s.img }" alt="${s.title}" class="imgbreath">
                                            </c:if>
                                        </a>
                                        <p><label class="bold-text cutline " title="${s.title}">${s.title}</label></p>

                                        <div class="clearfix visible-xs">
                                            <hr>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-8">
                                    <p>
                                        <small class="label label-gray">${s.date }</small>
                                        <a href="/romeo/${s.id }.html#comment">
                                            <small class="label label-gray ds-thread-count"
                                                   data-thread-key="romeo${s.id }">
                                        </a></small><br><br>


                                        <a href="/romeo/${s.id }.html" class="no-decoration"
                                           title="${s.title}">${s.title}</a> ：
                                    </p>
                                    <div id="brief_${ss.index}">

                                            ${s.brief }
                                        <c:if test="${s.brief!=s.article }">
                                            <button class="clearfix btn btn-gray btn-xs click2show"
                                                    data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}">
                                                &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp;
                                            </button>
                                        </c:if>
                                    </div>
                                    <div class="clearfix hidden" id="text_${ss.index}">
                                            ${s.article }
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
<%@ include file="/WEB-INF/views/page/common/fenye.jsp" %>
<%@ include file="/WEB-INF/views/page/common/container.jsp" %>

<script src="/js/page/eq.js"></script>


</body>
</html>
