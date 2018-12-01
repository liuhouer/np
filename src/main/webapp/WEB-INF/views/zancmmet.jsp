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
    <title>${ datamap.lrc_title}:最爱主题图册互动 | NorthPark</title>
    <meta name="keywords" content="NorthPark,最爱主题图册互动">
    <meta name="description"
          content="NorthPark最爱主题图册互动、评论、关注.">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <link href="https://northpark.cn/statics/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet"/>


</head>

<body style="">


<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant">${ datamap.lrc_title}</h1>
<div class="clearfix maincontent">
    <div class="container">

        <div class="mainbody padding10" style="margin-top:5em;">
            <input type="hidden" id="J_lrcid" value="${ datamap.lrc_id}"/>
            <input type="hidden" id="J_uid" value="${ user.id}"/>
            <input type="hidden" id="J_yizan" value="${yizan }"/>
            <div class="row">
                <div class="col-md-8">

                    <h2 class="margin0">${ datamap.lrc_title} &nbsp;
                        <small><span class="glyphicon glyphicon-user"></span> 由<a

                                <c:if test="${datamap.by_tail_slug==null || datamap.by_tail_slug==''}">
                                    href="/cm/detail/${datamap.by_id}"
                                </c:if>
                                <c:if test="${datamap.by_tail_slug!=null }">
                                    href="/people/${datamap.by_tail_slug }"
                                </c:if>

                                title="${datamap.by_username }的最爱">${datamap.by_username }</a>创建
                        </small>
                    </h2>
                    <hr>

                    <div class="row">
                        <div class="col-sm-7 ">

                            <div class="clearfix" style="position:relative">
                                <div class="clearfix" id="mainThumb"><img class="img-responsive img-full"
                                                                          src="/bruce/${datamap.lrc_albumImg }"
                                                                          alt="${ datamap.lrc_title}"></div>

                            </div>


                        </div>
                        <div class="col-sm-5">

                            <div class="clearfix">
                                <h7><label class="control-label iteminfo ">${datamap.lrc_updatedate }</label></h7>
                                <h4><span class="glyphicon glyphicon-heart"></span> ${datamap.zanNum }人最爱</h4>
                                <p class="pline">

                                <div id="J_zan_div" class="pline">
                                    <c:forEach var="x" varStatus="xx" items="${zanList }">
							    	<span><a
                                            <c:if test="${x.tail_slug==null || x.tail_slug==''}">
                                                href="/cm/detail/${x.id}"
                                            </c:if>
									    <c:if test="${x.tail_slug!=null }">
                                            href="/people/${x.tail_slug }"
                                        </c:if>

                                            title="${x.username }">${x.username }</a> &nbsp;</span>

                                    </c:forEach>
                                </div>
                                <!-- >10个喜欢才有查看更多按钮 -->
                                <c:if test="${datamap.zanNum > 10}">
                                    <button id="J_lovers_box" class="btn btn-gray btn-xs click2show"
                                            data-target=".lovers_box">查看更多 ››
                                    </button>
                                </c:if>
                                </p>
                            </div>

                            <h2><a class="btn btn-warning btn-xlg" id="J_gz_btn"><span
                                    class="glyphicon glyphicon-heart"></span>
                                <c:if test="${yizan eq 'yizan' }">已爱上~</c:if><c:if
                                        test="${yizan ne 'yizan' }">加入我的最爱 </c:if>


                            </a></h2>
                            <input type="hidden" id="by_id" value="${datamap.by_id }"/>


                        </div>
                    </div>
                    <div class="clearfix margin-t20">

                        <h4><span class="glyphicon glyphicon-comment"></span> ${datamap.plNum }条回忆</h4>
                        <hr>
                        <c:if test="${user!=null }">
                            <div class="row">
                                <div class="col-xs-3 col-sm-2">
                                    <a
                                            <c:if test="${user.tail_slug==null || user.tail_slug==''}">
                                                href="/cm/detail/${user.id}"
                                            </c:if>
                                            <c:if test="${user.tail_slug!=null }">
                                                href="/people/${user.tail_slug }"
                                            </c:if>

                                            title="${user.username }的最爱"><img
                                            <c:if test="${user.headpath == null}">src="https://northpark.cn/statics/img/davatar.jpg"</c:if>
                                        <c:if test="${user.headpath != null}">
                                    <c:choose>
                                            <c:when test="${fn:contains(user.headpath  ,'http://') }">src="${user.headpath  }"</c:when>
                                            <c:otherwise>src="/bruce/${user.headpath  }"</c:otherwise>
                                    </c:choose>

                                    </c:if> class="img-responsive  img-circle max-width-60" alt="${user.username }的最爱"></a>
                                </div>
                                <div class="col-xs-9 col-sm-10">
                                    <div class="form-group">
                                        <textarea class="form-control" style="height:200px; max-height:400px;"
                                                  id="J_comment" name="comment" rows="3"></textarea>
                                    </div>
                                    <div class="form-group text-right">
                                        <input class="btn btn-info btn-md" type="button" id="J_commentBtn" value="+ 发布">
                                    </div>

                                    <hr>
                                </div>
                            </div>
                        </c:if>


                        <div class="clearfix" id="stuffCommentBox">

                        </div>

                        <div class="row center">
                            <div class="row margin-b20" id="loadingAnimation">
                                <img alt="load comment of ${ datamap.lrc_title}" src="https://northpark.cn/statics/img/loading.gif" width="30"
                                     height="30">
                            </div>
                            <button class="btn btn-default btn-lg margin-b20" id="loadStuffCommentBtn"
                                    data-htmlboxid="stuffCommentBox" rel="938">加载更多 <span
                                    class="glyphicon glyphicon-chevron-down"></span></button>
                            <input type="hidden" id="comment_id_from" value="1">
                            <input type="hidden" id="J_lrc_id" value="${datamap.lrc_id }">
                            <input type="hidden" id="J_tail" value="${tail }">
                        </div>


                    </div>

                </div>

                <div class="col-md-4">
                    <div class="clearfix sidebar radius-5">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-th-large margin-b20"></span> 随便看看</h4>
                        </div>
                        <c:forEach var="z" items="${loveList }">
                            <div class="row padding10">
                                <div class="col-xs-2 avatar">
                                    <c:if test="${z.headpath == null}">
                                        <span class="${z.headspanclass }">${z.headspan }</span>
                                    </c:if>
                                    <c:if test="${z.headpath != null}">
                                        <img alt=""
                                        <c:choose>
                                             <c:when test="${fn:contains(z.headpath ,'http://') }">src="${z.headpath }"</c:when>
                                             <c:otherwise>src="/bruce/${z.headpath }"</c:otherwise>
                                        </c:choose>
                                        >
                                    </c:if>

                                </div>
                                <div class="col-xs-10" style="line-height:30px;">
                                    <a


                                            <c:if test="${z.tail_slug==null || z.tail_slug==''}">
                                                href="/cm/detail/${z.userid}"
                                            </c:if>
                                            <c:if test="${z.tail_slug!=null }">
                                                href="/people/${z.tail_slug }"
                                            </c:if>


                                            title="${z.username }的最爱">${z.username }</a> 爱上了 <a style="color: #45d0c6"
                                                                                                title="${z.title }"
                                                                                                href="/love/${z.titlecode }.html">${z.title }</a>
                                </div>
                            </div>
                        </c:forEach>

                        <p class="white-line margin0"></p>
                    </div>

                </div>

            </div>


        </div>


    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>

<script src="https://northpark.cn/statics/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="https://northpark.cn/statics/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>
<script src="https://northpark.cn/statics/js/page/zancmt.js"></script>


</body>
</html>