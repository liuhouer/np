<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="org.springframework.web.util.UrlPathHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="description" content="添加我的最爱:NorthPark / 记住美好,保留回忆,分享最爱。">
    <meta name="keywords" content="NorthPark">
    <meta name="author" content="bruce">
    <meta name="robots" content="index,follow,archive">
    <title>添加我的最爱 | NorthPark / </title>
    <%@ include file="../common/common.jsp" %>
    <style type="text/css">
        #preview {
            width: 200px;
            height: 200px;
            border: 0;
            overflow: hidden;
            top: 25%;
            position: relative;
        }

        #imghead {
            filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
        }
    </style>
</head>

<body>
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>


<div class="clearfix maincontent ">
    <div class="container">
        <div class="mainbody" style="margin-top: 5em;">
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">
                <form method="POST" action="/lyrics/addLyrics.action" accept-charset="UTF-8" role="form"
                      id="addItemForm" style="color: #444;" class="form margin-t20" enctype="multipart/form-data">
                    <div class="clearfix">
                        <h4>
                            <span class="glyphicon glyphicon-plus"></span> 添加我的最爱
                        </h4>
                        <hr>
                    </div>

                    <span style="color: #999; opacity: 1;">主题</span>
                    <div class="form-group padding-b20">
                        <input id="title" placeholder="暴雨中漫步"
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="title" type="text">
                    </div>
                    <span style="color: #999; opacity: 1;">主题图片</span>
                    <div class="form-group  padding-b20">
                        <input placeholder="专辑图片 选择一个你喜欢的图片" name="file" id="file1" type="file"
                               onchange="previewImage(this)" accept=".jpg,.gif,.png,.ico,.bmp"
                               class="form-control   border-light-1 bg-lyellow grid98 radius-0"/>
                    </div>
                    <div class="clearfix hidden" id="searchResult"></div>

                    <div id="preview" class="form-group ">
                        <img id="imghead" border=0 src="https://northpark.cn/statics/img/head_180.jpg" width="200" height="200" alt="avatar"/>
                    </div>
                    <div class="form-group">
                        <input disabled="disabled" id="formSubmit" data-activetext="添加 ››"
                               class="btn btn-hero btn-xlg margin-t10 grid50" value="添加"
                               type="submit">
                    </div>
                </form>
            </div>

            <br>
            <br>

        </div>


    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script src="https://northpark.cn/statics/js/page/lrc/lyricadd.js"></script>


</body>
</html>