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
        <title>学习/课程/书籍/知识 | NorthPark</title>
    </c:if>
    <c:if test="${page!=null && page!=''}">
        <title>学习/课程/书籍/知识 ::第${page}页 | NorthPark</title>
    </c:if>

    <meta name="keywords" content="NorthPark,学习,课程,书籍,知识">
    <meta name="description"
          content="NorthPark学习包含技术课程、书籍、和知识分享">

</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant">学习</h1>
<div class="clearfix maincontent grayback">
    <div class="container">
        <div class="mainbody" style="margin-top:80px; ">


            <div class="row">
                <div class="col-md-8">

                    <div class="col-sm-12">


                        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/learning/page/1" onkeydown="if(event.keyCode==13){return false;}">
                            <input id="keyword" placeholder="课程/书籍" value="${keyword }"
                                   class="width100 input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0"
                                   name="keyword" type="text">
                            <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="button"
                                   id="J_ser_btn">
                        </form>

                        <div class="row   padding20">
                            <input class="btn tag-node " oid="hot" type="button" value="热门排序">
                            <input class="btn tag-node " oid="latest" type="button" value="上新排序">
                        </div>


                    </div>

                    <c:forEach items="${list }" var="s" varStatus="ss">

                        <div class="col-sm-12">
                            <div class="clearfix bg-white margin-b10 padding20 ">
                                <div class="row margin5  word-return">
                                    <div class="border-0 center">
                                        <p>
                                            <a href="/learning/post-${s.id }.html" oid="${s.id }">
                                                <small class="green-text">
                                                    <font size="5"><strong>
                                                        <c:if test="${s.hotIndex>0}">
                                                            <i class="fa fa-thumb-tack" title="已置顶"></i>
                                                        </c:if>
                                                            ${s.title}</strong></font>
                                                </small>
                                            </a>
                                        </p>


                                        <div class="clearfix visible-xs">
                                            <hr>
                                        </div>
                                    </div>

                                    <p>

                                        	发表于：<span class=" glyphicon glyphicon-time margin10"></span><span
                                            class="common-a-right" title="${s.postDate}"
                                            href="/learning/date/${s.postDate}">${s.postDate}</span>

                                        <span class=" glyphicon glyphicon-tags margin10"></span>

                                        <c:forEach items="${s.tag_list }" var="y" varStatus="yy"><%--map--%>
                                            <strong><a class="common-a-right" title="${y.tag}"
                                                       href="/learning/tag/${y.tag_code }">${y.tag}</a></strong>
                                        </c:forEach>
                                        <c:if test="${user!=null }">
                                            <c:if test="${user.email == '654714226@qq.com' || user.email == 'qhdsoft@126.com' || user.email == 'woaideni@qq.com'}">
                                                <span class=" glyphicon glyphicon-arrow-up margin10"></span>
		                                        <a class="common-a-right" title="置顶" href="" onclick="handup('${s.id}')">置顶</a>
		                                        <span class=" glyphicon glyphicon-eye-close margin10"></span>
		                                        <a class="common-a-right" title="隐藏" href="" onclick="hideup('${s.id}')">大尺度隐藏</a>
		                                        <span class="glyphicon glyphicon-pencil margin10"></span>   
		                                        <a class="common-a-right" title="编辑" href="/learning/edit/${s.id}" >快速编辑</a>   
                                            </c:if>
                                        </c:if>

                                    </p>
                                    <p id="brief_${ss.index}">
                                        <c:if test="${s.briefImg!='' && s.briefImg!=null}">
                                            <img src="${s.briefImg }"></img>
                                        </c:if>


                                        <c:if test="${s.brief ne '' && s.brief ne null}">
                                            ${s.brief }
                                        </c:if>
                                        <c:if test="${s.brief eq ''||s.brief eq null}">
                                            ${s.content }
                                        </c:if>
                                    </p>
                                    
                                    <p>
                                        <a class="btn btn-warning margin-t10" href="/learning/post-${s.id}.html">
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

                    <!-- donate  -->
                    <div class="clearfix sidebar radius-5 ">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon  glyphicon-usd margin5"></span> Donate</h4>
                        </div>
                        <!-- load donate list  -->
                        <%@ include file="/WEB-INF/views/page/common/donate.jsp" %>


                    </div>
                    <!-- donate  -->


                    <!-- hot  -->
                    <div class="clearfix sidebar radius-5 ">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-leaf margin5"></span> 随便看看</h4>
                        </div>
                        <c:forEach var="z" items="${learn_hot_list }">

                            <div class="col-md-12 margin-t10">
                                <div class="col-xs-2 avatar">

                                    <span class="text-${ z.color }">${ fn:toUpperCase(fn:substring(z.title ,0,1))   }</span>

                                </div>
                                <div class="col-xs-10">

                                    <a style="font-size: 14px;line-height: 32px;color: #888"
                                       href="/learning/post-${z.id }.html" title="${z.title }">${z.title } </a>

                                </div>


                            </div>
                        </c:forEach>


                    </div>


                    <!-- tags  -->
                    <div class="clearfix sidebar radius-10 ">
                        <div class="clearfix border-bottom">
                            <h4><span class=" glyphicon glyphicon-tags margin5"></span> 标签</h4>
                        </div>
                        <c:forEach var="z" items="${learnTags }"> <%--bean--%>

                            <div class="col-md-10 margin5">
                                <c:if test="${z.tagCode == sel_tag }">
                                    <span class="glyphicon glyphicon-arrow-right margin5"></span>
                                    <a style="color: #45d0c6;" href="/learning/tag/${z.tagCode }"
                                       title="${z.tag }">${z.tag } </a>
                                </c:if>
                                <c:if test="${z.tagCode != sel_tag }">
                                    <span class="glyphicon glyphicon-tag margin5"></span>
                                    <a href="/learning/tag/${z.tagCode }" title="${z.tag }">${z.tag } </a>
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

<script data-cfasync="false" type="text/javascript">
    //禁止图片拉伸
    $(function () {


        //设置标签颜色,绑定动作按钮
        $(".tag-node").each(function () {
            $(this).css("backgroundColor", getRandomColor());
            $(this).css("color", "#fff");

            $(this).click(function () {
                var oid = $(this).attr("oid");
                window.location.href = "/learning/page/1?orderBy=" + oid;
            })
        });


    })
</script>

<script data-cfasync="false" type="text/javascript">

    $(function () {
        //搜索事件处理
        $("#J_ser_btn").click(function () {
            $("#J_ser_btn").attr('disabled', true);
            var keyword = $("#keyword").val();
            if (keyword && keyword != "${keyword }") {
                window.location.href = "/learning/page/1?keyword=" + keyword;
            }
            setTimeout("$('#J_ser_btn').removeAttr('disabled')", 5000); //设置5秒后提交按钮 显示
        })

        //ENTER事件
        $("body").keydown(function () {
            if (event.keyCode.toString() === "13"){
                $("#J_ser_btn").click();
            }
        });
    })

    /*  ##set query param */
    var keyword = $("#keyword").val();
    if (keyword) {
        $("#pageForm a").each(function () {
            var href = $(this).attr("href");
            $(this).attr("href", href + "?keyword=" + keyword);
        })
    }

    var orderBy = "${orderBy}";
    if (orderBy) {
        $("#pageForm a").each(function () {
            var href = $(this).attr("href");
            $(this).attr("href", href + "?orderBy=" + orderBy);
        });

        //设置选中的标签格式
        $(".tag-node").each(function () {
            if ($(this).attr("oid") == orderBy) {
                $(this).css("border-radius", '0px');
            }

        });
    }


    //1、随机色的函数-rgb
    function getRandomColor() {
        var rgb = 'rgb(' + Math.floor(Math.random() * 255) + ','
            + Math.floor(Math.random() * 255) + ','
            + Math.floor(Math.random() * 255) + ')';
        console.log(rgb);
        return rgb;
    }


    function handup(id) {
        $.ajax({
            url: "/learning/handup",
            type: "post",
            data: {"id": id},
            dataType: "json",
            success: function (msg) {
                if (msg.data == "success") {
                    art.dialog.tips('置顶成功');
                } else {
                    art.dialog.tips('error happened.');
                }
            }
        });

    }

    function hideup(id) {
        $.ajax({
            url: "/learning/hideup",
            type: "post",
            data: {"id": id},
            dataType: "json",
            success: function (msg) {
                if (msg.data == "success") {
                    art.dialog.tips('隐藏成功');
                    window.location.href = window.location.href;
                } else {
                    art.dialog.tips('error happened.');
                }
            }
        });

    }


</script>

<script>

    $(function () {
        $(".bg-white").find("img").each(function () {
            $(this).css('max-width', ($(".bg-white").width() * 0.85));
            $(this).css('margin-right', '20%');
        })

    })
</script>

<script>

    loadDonates(1);

</script>
</body>
</html>
