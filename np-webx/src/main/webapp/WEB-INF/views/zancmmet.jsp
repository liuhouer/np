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
    <title>${ dataMap.lrc_title}:最爱主题图册互动 | NorthPark</title>
    <meta name="keywords" content="NorthPark,最爱主题图册互动">
    <meta name="description"
          content="NorthPark最爱主题图册互动、评论、关注.">
    <link href="https://northpark.cn/statics/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <link href="https://northpark.cn/statics/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet"/>


</head>

<body style="">


<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant">${ dataMap.lrc_title}</h1>
<div class="clearfix maincontent">
    <div class="container">

        <div class="mainbody padding10" style="margin-top:5em;">
            <input type="hidden" id="J_lrcid" value="${ dataMap.lrc_id}"/>
            <input type="hidden" id="J_uid" value="${ user.id}"/>
            <input type="hidden" id="J_yizan" value="${yizan }"/>
            <div class="row">
                <div class="col-md-8">

                    <h2 class="margin0">${ dataMap.lrc_title} &nbsp;
                        <small><span class="glyphicon glyphicon-user"></span> 由<a

                                <c:if test="${dataMap.by_tail_slug==null || dataMap.by_tail_slug==''}">
                                    href="/cm/channel/${dataMap.by_id}"
                                </c:if>
                                <c:if test="${dataMap.by_tail_slug!=null }">
                                    href="/people/${dataMap.by_tail_slug }"
                                </c:if>

                                title="${dataMap.by_username }的最爱">${dataMap.by_username }</a>创建
                        </small>
                    </h2>

                    <hr>

                    <div class="row">
                        <div class="col-sm-7 ">

                            <div class="clearfix" style="position:relative">
                                <div class="clearfix" id="mainThumb"><img class="img-responsive img-full"
                                                                          src="/bruce/${dataMap.lrc_album_img }"
                                                                          alt="${ dataMap.lrc_title}"></div>

                            </div>


                        </div>




                        <div class="col-sm-5">



                            <div class="clearfix">
                                <h4><span class="glyphicon glyphicon-heart"></span> ${dataMap.zanNum }人最爱</h4>
                                <p class="pline">

                                <div id="J_zan_div" class="pline">
                                    <c:forEach var="x" varStatus="xx" items="${zanList }">
							    	<span><a
                                            <c:if test="${x.tailSlug==null || x.tailSlug==''}">
                                                href="/cm/channel/${x.id}"
                                            </c:if>
									    <c:if test="${x.tailSlug!=null }">
                                            href="/people/${x.tailSlug }"
                                        </c:if>

                                            title="${x.username }">${x.username }</a> &nbsp;</span>

                                    </c:forEach>
                                </div>
                                <!-- >10个喜欢才有查看更多按钮 -->
                                <c:if test="${dataMap.zanNum > 10}">
                                    <button id="J_lovers_box" class="btn btn-gray btn-xs click2show"
                                            data-target=".lovers_box">查看更多 ››
                                    </button>
                                </c:if>
                                </p>
                            </div>

                            <input type="hidden" id="by_id" value="${dataMap.by_id }"/>

                            <div id="showResult" class="control-group center margen-b10">
                            </div>

                            <!-- 隐藏组件 -->
                            <div class="form-group clearfix  hidden" id="loveBox" >

                                <p>
                                    <span class="bold-text">设置爱上时间</span>
                                </p>
                                <p>
                                    <input id="loveDate" placeholder="1995-06-06"
                                           class="form_datetime form-inline border-light-1  bg-lyellow  grid50 radius-0"
                                           name="loveDate" type="text" >
                                    <button data-dismiss="#loveBox"
                                            id="J_gz_btn"
                                            title="加入我的最爱"
                                            class="btn btn-hero form-inline" style="vertical-align: bottom;padding: 2px 10px;">
                                        <i class="fa fa-floppy-o"></i> 爱上</button>
                                </p>

                            </div>
                            <!-- 隐藏组件 -->

                            <h2>
                                <%--id="J_gz_btn"--%>
                                <button class="btn btn-warning btn-xlg click2love"
                                        title="加入我的最爱 展开/收起"
                                        data-target="#loveBox">
                                    <span class="glyphicon glyphicon-heart"></span>
                                    <c:if test="${yizan eq 'yizan' }">已爱上~</c:if>
                                    <c:if test="${yizan ne 'yizan' }">加入我的最爱 </c:if>
                                </button>
                            </h2>

                        </div>
                    </div>
                    <div class="clearfix margin-t20">

                        <h4><span class="glyphicon glyphicon-comment"></span> ${dataMap.plNum }条回忆</h4>
                        <hr>
                        <%--发表评论--%>
                        <c:if test="${user!=null }">
                            <div class="row">
                                <div class="col-xs-3 col-sm-2">
                                    <a
                                            <c:if test="${user.tailSlug==null || user.tailSlug==''}">
                                                href="/cm/channel/${user.id}"
                                            </c:if>
                                            <c:if test="${user.tailSlug!=null }">
                                                href="/people/${user.tailSlug }"
                                            </c:if>

                                            title="${user.username }的最爱"><img
                                            <c:if test="${user.headPath == null}">src="https://northpark.cn/statics/img/davatar.jpg"</c:if>
                                        <c:if test="${user.headPath != null}">
                                    <c:choose>
                                            <c:when test="${fn:contains(user.headPath  ,'http://') }">src="${user.headPath  }"</c:when>
                                            <c:otherwise>src="/bruce/${user.headPath  }"</c:otherwise>
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
                        <%--发表评论--%>


                        <%--评论列表--%>
                        <div class="clearfix" id="stuffCommentBox">

                        </div>

                        <div class="row center">


                            <div class="row margin-b20" id="loadingAnimation">
                                <img alt="load comment of ${ dataMap.lrc_title}" src="https://northpark.cn/statics/img/loading.gif" width="30"
                                     height="30" />
                            </div>
                            <button class="btn btn-default btn-lg margin-b20" id="loadStuffCommentBtn"
                                    data-htmlboxid="stuffCommentBox" rel="938">加载更多 <span
                                    class="glyphicon glyphicon-chevron-down"></span></button>
                            <input type="hidden" id="comment_id_from" value="1">
                            <input type="hidden" id="J_lrc_id" value="${dataMap.lrc_id }">
                            <input type="hidden" id="J_tail" value="${tail }">
                        </div>
                        <%--评论列表--%>


                    </div>

                </div>

                <%--右侧广场--%>
                <div class="col-md-4">
                    <div class="clearfix sidebar radius-5">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-th-large margin-b20"></span> 随便看看</h4>
                        </div>
                        <c:forEach var="z" items="${loveList }"> <%-- map --%>
                            <div class="row padding10">
                                <div class="col-xs-2 avatar">
                                    <c:if test="${z.head_path == null}">
                                        <span class="${z.head_span_class }">${z.head_span }</span>
                                    </c:if>
                                    <c:if test="${z.head_path != null}">
                                        <img alt=""
                                        <c:choose>
                                             <c:when test="${fn:contains(z.head_path ,'http://') }">src="${z.head_path }"</c:when>
                                             <c:otherwise>src="/bruce/${z.head_path }"</c:otherwise>
                                        </c:choose>
                                        >
                                    </c:if>

                                </div>
                                <div class="col-xs-10" style="line-height:30px;">
                                    <a


                                            <c:if test="${z.tail_slug==null || z.tail_slug==''}">
                                                href="/cm/channel/${z.userid}"
                                            </c:if>
                                            <c:if test="${z.tail_slug!=null }">
                                                href="/people/${z.tail_slug }"
                                            </c:if>


                                            title="${z.username }的最爱">${z.username }</a> 爱上了 <a style="color: #45d0c6"
                                                                                                title="${z.title }"
                                                                                                href="/love/${z.title_code }.html">${z.title }</a>
                                </div>
                            </div>
                        </c:forEach>

                        <p class="white-line margin0"></p>
                    </div>

                </div>
                <%--右侧广场--%>
            </div>


        </div>


    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>

<script data-cfasync="false" src="https://northpark.cn/statics/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script data-cfasync="false" src="https://northpark.cn/statics/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>
<script src="https://northpark.cn/statics/js/bootstrap-datetimepicker.js"></script>
<script src="https://northpark.cn/statics/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script data-cfasync="false" src="/static/js/page/zancmt.js"></script>

<script>
    $(function () {
        $('.form_datetime').datetimepicker({
            language:'zh-CN',
            format:'yyyy-mm-dd',
            dateFormat: 'yyyy-mm-dd',
            minView: "month",//选择日期后，不会再跳转去选择时分秒
            autoclose:true
        })

        $(".click2love").click(function (){
            //1
            if (yizan == 'yizan') {
                return ;
            }
            //2
            let cl = $(this);
            let uri = window.location.href;
            //日期控件
            let love_date_id = $(this).data('target');

            $.ajax({
                url: "/cm/loginFlag",
                type: "get",
                dataType: "json",
                success: function (msg) {
                    if (msg.data == "1") {//已登录

                        //3
                        // 点击添加最爱按钮,展开
                        let display = $(love_date_id).css("display");
                        if (display == 'none') {
                            $(love_date_id).removeClass("hidden").css("display", "block");
                            $(cl).find('span').addClass("glyphicon-chevron-up").removeClass("glyphicon-heart");
                        } else if (display == 'block') {
                            $(love_date_id).addClass("hidden").css("display", "none");
                            $(cl).find('span').addClass("glyphicon-heart").removeClass("glyphicon-chevron-up");
                        }



                    } else if (msg.data == "0") {//没有登录

                        window.location.href = "/login?redirectURI=" + uri;
                    }

                }
            });

        })
    })

</script>

</body>
</html>