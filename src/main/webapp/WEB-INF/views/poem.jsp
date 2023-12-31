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
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <c:if test="${page==null || page==''}">
        <title>诗词秀 | NorthPark</title>
    </c:if>
    <c:if test="${page!=null && page!=''}">
        <title>诗词秀::第${page}页 | NorthPark</title>
    </c:if>

    <meta name="keywords" content="NorthPark,诗词秀,诗词赏析,诗词天地,爱诗词">
    <meta name="description"
          content="NorthPark诗词秀，包含丰富的诗词，涵盖了唐宋元明清,五代十国南北朝诗词作品。">

</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant">诗词秀</h1>

<div class="clearfix maincontent grayback">


    <div class="container">

        <div class="mainbody" style="margin-top:80px; ">


            <div class="row">
                <div class="col-md-8">

                    <div class="col-sm-12">

                        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8"
                              action="/poem/page/1">
                            <input id="keyword" placeholder="诗词名句" value="${keyword }"
                                   class="width100 input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0"
                                   name="keyword" type="text">
                            <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="button"
                                   id="J_ser_btn">
                        </form>

                    </div>

                    <c:forEach items="${list }" var="s" varStatus="ss">

                        <div class="col-sm-12 ">
                            <div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
                                <div class="row margin5">
                                    <div class="border-0 center">
                                        <p>
                                            <a href="/poem/enjoy/${s.id }.html">
                                                <small class="green-text">
                                                    <font size="5"><strong>${s.title}</strong></font>
                                                </small>
                                            </a>
                                        </p>


                                        <div class="clearfix visible-xs">
                                            <hr>
                                        </div>
                                    </div>

                                    <p>


                                        <c:if test="${s.picPoem!='' }"><img
                                                title="${s.title}" alt="${s.title}" src="${s.picPoem }"></c:if>


                                    </p>
                                    <p>

                                        收录于：<a class="common-a-right" title="${s.author}">${s.author}</a>

                                        <a class="common-a-right" title="${s.years}"
                                           href="/poem/dynasty/${s.yearsCode }">${s.years}</a>

                                        <a class="common-a" title="${s.types}"
                                           href="/poem/types/${s.typesCode }">${s.types}</a>


                                    </p>
                                    <p id="brief_${ss.index}">

                                            ${s.content }
                                    </p>

                                    <p>
                                        <a class="btn btn-warning margin-t10" href="/poem/enjoy/${s.id }.html">
                                            Read More
                                            <span class="glyphicon  glyphicon-circle-arrow-right padding5"></span>
                                        </a>
                                    </p>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${pagein!='no' }">
                        <%@ include file="/WEB-INF/views/page/common/fenye.jsp" %>
                    </c:if>
                </div>

                <div class="col-md-4 ">

                    <!-- 轮播开始 -->
                    <div class="row padding-t20  bg-lyellow">
                        <div class="col-md-10 col-md-offset-1">
                            <div class="testimonails-slider">

                                <div class="flex-viewport"
                                     style="overflow: hidden; position: relative;">
                                    <ul class="slides"
                                        style="width: 1000%; transition-duration: 0.6s; transform: translate3d(-1500px, 0px, 0px);vertical-align: middle;">

                                        <c:forEach items="${poem }" var="s" varStatus="ss">

                                            <li class="center"

                                                    <c:if test="${ss.index % 3==0}">
                                                        class="clone"
                                                    </c:if>
                                                    <c:if test="${ss.index %3 ==1 } ">
                                                        class=""
                                                    </c:if>
                                                    <c:if test="${ss.index %3 ==2} ">
                                                        class="flex-active-slide"
                                                    </c:if>

                                                aria-hidden="true" style="float: left; display: block; width: 750px;">

                                                <div class="testimonails-content avatar ">
                                                    <p class="text-color-${ fn:toLowerCase(fn:substring( s.retCode ,0,1))}">${s.title }</p>
                                                    <p class="text-color-${ fn:toLowerCase(fn:substring( s.retCode ,0,1))}">${s.content1 }</p>
                                                    <p>
                                                        <a

                                                                href="/poem/enjoy/${s.id }.html"

                                                                title="${s.title}">


                                                            <span class=" imgbreath text-${ fn:toLowerCase(fn:substring( s.retCode ,0,1))}"
                                                                  alt="${s.title}">${ fn:toUpperCase(fn:substring( s.retCode ,0,1))}</span>
                                                        </a>
                                                    </p>
                                                    <h6 class="gray-text">
                                                            ${s.author }
                                                    </h6>
                                                    <h6 class="gray-text">
                                                            ${s.years }
                                                    </h6>
                                                </div>
                                            </li>
                                        </c:forEach>

                                    </ul>
                                </div>
                                <ul class="flex-direction-nav">
                                    <li><a class="flex-prev" href="#"></a></li>
                                    <li><a class="flex-next" href="#"></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- 轮播结束 -->
                    <!-- hot  -->
                    <div class="row margin-t20 clearfix sidebar radius-5  bg-lyellow ">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-leaf margin5"></span> 朝代</h4>
                        </div>
                        <c:forEach var="z" items="${years_tag }">

                            <div class="col-md-10 margin5">
                                <c:if test="${z.tagCode == sel_tag }">
                                    <span class="glyphicon glyphicon-arrow-right margin5"></span>
                                    <a style="color: #45d0c6;" href="/poem/dynasty/${z.tagCode}"
                                       title="${z.tag }">${z.tag } </a>
                                </c:if>
                                <c:if test="${z.tagCode != sel_tag }">
                                    <span class="glyphicon glyphicon-tree-conifer margin5"></span>
                                    <a href="/poem/dynasty/${z.tagCode}" title="${z.tag }">${z.tag } </a>
                                </c:if>


                            </div>
                        </c:forEach>

                    </div>


                    <!-- tags  -->
                    <div class="row margin-t20 clearfix sidebar radius-10   bg-lyellow">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-tags margin5"></span> 词牌类别</h4>
                        </div>
                        <c:forEach var="z" items="${types_tag }">

                            <div class="col-md-10 margin5">
                                <c:if test="${z.tagCode == sel_tag }">
                                    <span class="glyphicon glyphicon-arrow-right margin5"></span>
                                    <a style="color: #45d0c6;" href="/poem/types/${z.tagCode}"
                                       title="${z.tag }">${z.tag } </a>
                                </c:if>
                                <c:if test="${z.tagCode != sel_tag }">
                                    <span class="glyphicon glyphicon-tag  margin5"></span>
                                    <a href="/poem/types/${z.tagCode}" title="${z.tag }">${z.tag } </a>
                                </c:if>


                            </div>
                        </c:forEach>


                    </div>


                </div>


            </div>


        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script src="https://northpark.cn/statics/js/poem/easing.js" type="text/javascript"></script>

<script src="https://northpark.cn/statics/js/poem/timers.js" type="text/javascript"></script>

<script src="https://northpark.cn/statics/js/poem/dualSlider.js" type="text/javascript"></script>

<script data-cfasync="false" type="text/javascript">

    $(function () {
        //搜索事件处理
        $("#J_ser_btn").click(function () {
            var keyword = $("#keyword").val();
            if (keyword) {
                window.location.href = "/poem/page/1?keyword=" + keyword;
            }
        })
    })


    /*  ##set query param */
    var keyword = $("#keyword").val();
    if (keyword) {
        $("#pageForm a").each(function () {
            var href = $(this).attr("href");
            $(this).attr("href", href + "?keyword=" + keyword);
        })
    }


</script>
<link media="all" type="text/css" rel="stylesheet" href="https://northpark.cn/statics/css/flexslider.css"><!-- 轮播css -->
<script type="text/javascript" src="https://northpark.cn/statics/js/jquery.flexslider.js"></script><!-- 轮播js -->
<script data-cfasync="false" type="text/javascript">

    $(document).ready(function () {


        //激活动作
        $('.flexslider').flexslider({
            prevText: '',
            nextText: ''
        });

        $('.testimonails-slider').flexslider({
            animation: 'slide',
            slideshowSpeed: 5000,
            prevText: '',
            nextText: '',
            controlNav: false
        });


    });

</script>


</body>
</html>
