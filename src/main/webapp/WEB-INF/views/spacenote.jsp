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
    <link rel="shortcut icon" href="https://northpark.cn/statics/img/favicon.ico">
    <title>${MyInfo.username}的碎碎念 -精神角落 | NorthPark </title>
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>

</head>

<body style="">

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


                            <c:if test="${MyInfo.tail_slug==null || MyInfo.tail_slug==''}">
                                href="/cm/detail/${MyInfo.id}"
                            </c:if>
                            <c:if test="${MyInfo.tail_slug!=null }">
                                href="/people/${MyInfo.tail_slug}"
                            </c:if>

                    >最爱</a></li>
                    <li class="active"><a href="/note/viewNotes/${MyInfo.id}">碎碎念</a></li>
                    <li><a href="/cm/fans/${MyInfo.id}">Fans</a></li>


                </ul>
            </div>
            <form id="f2" method="post"><input name="userid" type="hidden" value="${MyInfo.id }"/></form>
            <div class="row bg-white margin-t10 margin-b10  ">
                <div class="col-sm-1 avatar">
                    <a

                            <c:if test="${MyInfo.tail_slug==null || MyInfo.tail_slug==''}">
                                href="/cm/detail/${MyInfo.id}"
                            </c:if>
                            <c:if test="${MyInfo.tail_slug!=null }">
                                href="/people/${MyInfo.tail_slug}"
                            </c:if>

                            title="${MyInfo.username}的最爱">
                        <c:if test="${MyInfo.headpath==null }">
                            <span class=" ${MyInfo.headspanclass }"
                                  alt="${s.get('username')}">${MyInfo.headspan }</span>

                        </c:if>
                        <c:if test="${MyInfo.headpath!=null }">
                            <img alt="${MyInfo.username }的最爱"
                            <c:choose>
                                 <c:when test="${fn:contains(MyInfo.headpath ,'http://') }">src="${MyInfo.headpath}"</c:when>
                                 <c:otherwise>src="/bruce/${MyInfo.headpath }"</c:otherwise>
                            </c:choose>

                            >
                        </c:if>
                        <!-- <img src="img/davatar.jpg" class="img-responsive  img-circle max-width-50" alt="654714226的最爱"> -->
                    </a>
                </div>
            </div>

            <c:forEach items="${list }" var="s" varStatus="ss">

                <div class='row bg-white margin-t10 margin-b10' id='notebox_${s.id }'>
                    <div class='col-sm-1'>
                        <small class='label label-gray'>${s.createtime }</small>
                    </div>
                    <div class='col-sm-11'>
                            <%-- <label class='btn btn-gray btn-xs pull-right delNoteBtn1' rel='${s.id }' onclick="removes(this)"><span class='glyphicon glyphicon-remove'></span></label> --%>
                        <p>${s.note }</p>
                        <hr/>
                    </div>

                </div>
            </c:forEach>


        </div>

        <div class="row center">
        </div>
        <br>
        <br>

    </div>

    <%@ include file="/WEB-INF/views/page/common/fenye.jsp" %>
</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script data-cfasync="false" src="https://northpark.cn/statics/js/page/spacenote.js"></script>


</body>
</html>