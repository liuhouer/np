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
    <link rel="shortcut icon" href="/static/img/favicon.ico">
    <title>${poem_enjoy.title }-${poem_enjoy.author }:诗词秀 | NorthPark</title>
    <meta name="keywords" content="${poem_enjoy.title },${poem_enjoy.author },诗词秀,诗词赏析,诗词天地,爱诗词,NorthPark">
    <meta name="description"
          content="${poem_enjoy.content }">
          
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <style>

        body {
            background-color: #2D2D2D;
            color: white;
        }

        p {

            font-size: 24px;
            line-height: 1.67;
            font-weight: 400;
            letter-spacing: normal;
            margin: 0 20px 5px 0;
            vertical-align: bottom;
            margin-top: 0px;
            margin-right: 20px;
            margin-bottom: 5px;
            padding: 0px;
            -webkit-font-smoothing: antialiased;
            color: rgb(153, 153, 153);
        }
    </style>
</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- <div id="mydiv" style="height:500px;"></div> -->


<div id="mydiv">


    <div class="container">

        <div class="mainbody" style="margin-top:120px; ">


            <div class="row">
                <div class="col-md-12">


                    <div class="col-sm-8  col-md-offset-2 clearfix  margin-t20 margin-b10 padding20  ">
                        <div class="row ">
                            <!-- 文字区不需要请连css部分代码一并删除 -->
                            <h1><p>${poem_enjoy.title }</p></h1>
                            <p style="font-style: italic;">${poem_enjoy.author }</p>
                            <p>${poem_enjoy.content1 }</p>

                            <%-- <p >${poem_enjoy.enjoys }</p>	    --%>


                        </div>
                    </div>

                </div>

            </div>


        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>
<script type="text/javascript" src="/static/js/canvas-particle.js"></script>
<script type="text/javascript">
    //禁止图片拉伸
    $(function () {
    	$(".bg-white").find("img").each(function () {
            $(this).css('max-width', ($(".bg-white").width()));
            $(this).css('padding-right', '20%');
        })
    })

    $(function () {
        $(window).resize(function () {
            $('body').css('min-height', ($(window).height()));
            $('#mydiv').css('min-height', ($(window).height() - 100));
            $('body').css('overflowX', 'hidden');

        }).resize();
    });

</script>
<script type="text/javascript">
    window.onload = function () {
        //配置
        var config = {
            vx: 4,	//小球x轴速度,正为右，负为左
            vy: 4,	//小球y轴速度
            height: 2,	//小球高宽，其实为正方形，所以不宜太大
            width: 2,
            count: 200,		//点个数
            color: "121, 162, 185", 	//点颜色
            stroke: "130,255,255", 		//线条颜色
            dist: 6000, 	//点吸附距离
            e_dist: 20000, 	//鼠标吸附加速距离
            max_conn: 10 	//点到点最大连接数
        }

        //调用
        CanvasParticle(config);
    }
</script>


</body>
</html>
