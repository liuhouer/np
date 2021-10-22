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
    <title>${model.title} | NorthPark </title>
    <meta name="keywords" content="NorthPark,情商提升,情圣日记，撩妹技巧">
    <meta name="description"
          content="NorthPark情商提升的技巧和讲解">

    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>

</head>

<body>
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<div class="clearfix maincontent grayback">
    <div class="container mainbody">
        <div class="row">
            <div class="col-md-12">
                <div class="col-sm-8  col-md-offset-2 ">
                    <div class="clearfix bg-white margin-t10 margin-b10 padding20 " id="J_white_div">
                        <div class="row margin10 post_article">
                            <div class="thumbnail border-0 center">
                                <p>
                                    <small class="green-text">
                                        <h1>
                                            ${model.title}

                                        </h1>
                                    </small>
                                </p>

                                <p>
                                    <a class="common-a" title="${article.postdate}">
                                        ${model.date }
                                    </a>
                                </p>

                                <hr>
                            </div>
                            <p class="margin-t10">
                                <a title="${model.title}">
                                    <img src="${model.img }" alt="${model.title}">
                                </a>
                            </p>

                            <p id="content_">

                                ${model.article }
                            </p>
                            <div class="clearfix visible-xs">
                                <hr>
                            </div>
                        </div>


                        <!-- 打赏 -->
                        <div>


                            <div style="padding: 10px 0; margin: 20px auto; width: 90%; text-align: center">
                                <div class="margin10">生活不止苟且,还有我喜爱的海岸.</div>
                                <button id="rewardButton" ,="" disable="enable"
                                        onclick="var qr = document.getElementById('QR'); if (qr.style.display === 'none') {qr.style.display='block';} else {qr.style.display='none'}"
                                        style="cursor: pointer; border: 0; outline: 0; border-radius: 100%; padding: 0; margin: 0; letter-spacing: normal; text-transform: none; text-indent: 0px; text-shadow: none">
                                    <span onmouseover="this.style.color='rgb(236,96,0)';this.style.background='rgb(204,204,204)'"
                                          onmouseout="this.style.color='#fff';this.style.background='rgb(236,96,0)'"
                                          style="display: inline-block; width: 70px; height: 70px; border-radius: 100%; color: rgb(255, 255, 255); font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 35px; line-height: 75px; font-family: microsofty; background: rgb(236, 96, 0);">赏</span>
                                </button>
                                <div id="QR" style="display: none;">

                                    <div id="wechat" style="display: inline-block ;margin-right: 20px;" >
                                        <a href="http://liuhouer.python-project.com/blog/donate/praise.jpg"
                                           class="fancybox" rel="group"><img id="wechat_qr"
                                                                             src="http://liuhouer.python-project.com/blog/donate/praise.jpg"
                                                                             alt="Bruce WeChat Pay"
                                                                             style="width: 200px;height:200px; max-width: 100%; display: inline-block"></a>
                                        <p>微信打赏</p>
                                    </div>


                                    <div id="alipay" style="display: inline-block ;margin-right: 20px;" >
                                        <a href="http://liuhouer.python-project.com/blog/donate/alipay.png"
                                           class="fancybox" rel="group"><img id="alipay_qr"
                                                                             src="http://liuhouer.python-project.com/blog/donate/alipay.png"
                                                                             alt="Bruce Alipay"
                                                                             style="width: 200px;height:200px; max-width: 100%; display: inline-block"></a>
                                        <p>支付宝打赏</p>
                                    </div>

                                </div>
                            </div>


                        </div>

                        <!-- 打赏 -->


                        <!-- northpark评论模块 -->
                        <div id="comment" class="col-md-10" >
                            <hr>
                            <%--展示评论详情--%>
                            <div class="clearfix" id="stuffCommentBox_${model.id}">


                            </div>


                            <div id="J_progress" class="center padding-t20"></div>


                            <div class="form-group clearfix note-comment margin-t10 " id="comment_${model.id}">
                                <textarea id="input_cm_${model.id}" placeholder="说点什么吧..."
                                   class="form-control bg-lyellow"
                                   rows="3"></textarea>


                                <button title="发布评论"


                                        class="btn btn-hero margin-t5 click2save"
                                        topic-id="${model.id}"
                                        topic-type="6"
                                        from-uid="${user.id}"
                                        from-uname="${user.username}"
                                        data-input="#input_cm_${model.id}">
                                    <span class="fa fa-floppy-o padding5"></span>发布评论</button>


                            </div>


                        </div>
                        <!-- northpark评论模块 -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>

<script data-cfasync="false" type="text/javascript">


    $(function () {
    	$(".bg-white").find("img").each(function () {
            $(this).css('max-width', ($(".bg-white").width()));
            $(this).css('padding-right', '20%');
            
        })

        //展示全文和评论详情-- northpark评论模块 --
        loadComment('${model.id}', 6);

    })

    function beforeSend(XMLHttpRequest) {
        $("#J_progress").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:48px;height:48px;' /></div>");
    }


    function complete(XMLHttpRequest, textStatus) {
        $("#J_progress").empty();
    }
</script>
</body>
</html>
