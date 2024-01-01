<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <meta name="description" content="NorthPark影视">
        <meta name="keywords" content="NorthPark">
        <meta name="author" content="bruce">
        <meta name="robots" content="index,follow,archive">
        <title>NorthPark / 添加课程|书籍</title>

        <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
        <link href="/static/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet"/>

</head>

<body>
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>


<div class="clearfix maincontent">
        <div class="container">
                <div class="mainbody" style="margin-top: 5em;">
                        <div class="align-center bg-white radius-5 padding10 max-width-700 min-width-300">
                                <form method="POST" action="/learning/addItem" accept-charset="UTF-8" role="form" id="addItemForm"
                                      style="color: #444;" class="form margin-t20" enctype="multipart/form-data">
                                        <div class="clearfix">
                                                <h4>
                                                        <span class="glyphicon glyphicon-plus"></span> 添加学习资源
                                                </h4>
                                                <hr>
                                        </div>
                                        <input type="hidden" name="id" value="${model.id }"/>
                                        <div class="form-group ">
                                                <span class="glyphicon glyphicon-star"></span> 课程|书籍
                                                <input id="J_name" placeholder="课程/书籍" required
                                                       class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                                                       name="title" type="text" value="${model.title }">
                                        </div>
                                        <div class="form-group ">

                                                <span class="glyphicon glyphicon-star"></span>下载地址
                                                <textarea id="J_path" style="height: 200px; max-height: 400px;"
                                                          name="path" rows="5">
                                                        ${model.path }
                                                </textarea>
                                        </div>
                                        <div class="form-group ">
                                                <span class="glyphicon glyphicon-star"></span>学习颜色
                                                <input id="J_color" placeholder="学习颜色" required
                                                       class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                                                       name="color" type="text" value="${model.color }">
                                        </div>
                                        <div class="form-group ">
                                                <span class="glyphicon glyphicon-star"></span>学习标签
                                                <input id="J_tag" placeholder="学习标签" required
                                                       class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                                                       name="tags" type="text" value="${model.tags },课程分享">
                                        </div>
                                        <div class="form-group ">
                                                <span class="glyphicon glyphicon-star"></span>学习标签-英文
                                                <input id="J_tag_code" placeholder="学习标签-英文" required
                                                       class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                                                       name="tagsCode" type="text" value="${model.tagsCode },classhare">
                                        </div>

                                        <div class="form-group ">
                                                <span class="glyphicon glyphicon-star"></span>定价
                                                <input id="J_price" placeholder="学习定价" required
                                                       class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                                                       name="price" type="number" value="${model.price }">
                                        </div>

                                        <div class="form-group">
                                                <span class="glyphicon glyphicon-star"></span>学习预览
                                                <textarea id="J_md_brief" style="height: 200px; max-height: 400px;"
                                                          name="brief" rows="5" >

                                                        <c:out value="${model.brief }" escapeXml="true"></c:out>

                                                 <c:if test="${model.brief ==null}">
                                                         <pre style="box-sizing: inherit; overflow-y: scroll; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 14px; line-height: 20px; font-family: 'courier new'; padding: 30px; margin-top: 20px; margin-bottom: 10px; color: rgb(111, 187, 114); background: rgb(43, 48, 59); border-width: 0px; border-style: initial; border-color: initial; border-radius: 3px;">—/马哥/0000000000000马哥Linux高端运维云计算就业班-教学总监老王主讲/
                                                        ├──1-01-课程架构介绍和计算机基础.mp4 147.51M
                                                        ├──1-02-计算机硬件组成.mp4 341.69M
                                                        ├──1-03-操作系统基础.mp4 205.91M
                                                        ├──1-04-Linux介绍.mp4 361.36M
                                                        ├──10-1文本处理三剑客之sed.mp4 620.57M
                                                        ├──11-01-软件管理基础.mp4 216.81M</pre>



                                                 </c:if>
                                                  </textarea>

                                        </div>

                                        <div class="form-group">
                                                <span class="glyphicon glyphicon-star"></span>学习内容
                                                <textarea id="J_md_text" style="height: 200px; max-height: 400px;"
                                                          name="content" rows="5" >
                                                         <c:out value="${model.content }" escapeXml="true"></c:out>

                                                       <c:if test="${model.content ==null}">
                                                      <pre style="box-sizing: inherit; overflow-y: scroll; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 14px; line-height: 20px; font-family: 'courier new'; padding: 30px; margin-top: 20px; margin-bottom: 10px; color: rgb(111, 187, 114); background: rgb(43, 48, 59); border-width: 0px; border-style: initial; border-color: initial; border-radius: 3px;">—/马哥/0000000000000马哥Linux高端运维云计算就业班-教学总监老王主讲/
                                                        ├──1-01-课程架构介绍和计算机基础.mp4 147.51M
                                                        ├──1-02-计算机硬件组成.mp4 341.69M
                                                        ├──1-03-操作系统基础.mp4 205.91M
                                                        ├──1-04-Linux介绍.mp4 361.36M
                                                        ├──10-1文本处理三剑客之sed.mp4 620.57M
                                                        ├──11-01-软件管理基础.mp4 216.81M</pre>


                                                       </c:if>
                                                </textarea>
                                        </div>



                                        <div class="form-group">
                                                <input id="formSubmit" data-activetext="添加 ››"
                                                       class="btn btn-hero btn-xlg margin-t10 grid50" value="添加"
                                                       type="button">
                                        </div>
                                </form>
                        </div>

                        <br>
                        <br>

                </div>


        </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


<script src="/static/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="/static/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>
<script data-cfasync="false" type="text/javascript">
        $(function () {
                let editor = $('#J_md_text').wangEditor({
                        'menuConfig': [
                                ['viewSourceCode'],
                                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                                ['list', 'justify', 'blockquote'],
                                ['createLink', 'insertHr', 'undo'],
                                ['insertImage', 'insertVideo', 'insertLocation', 'insertCode']
                        ]
                });

                let editor2 = $('#J_path').wangEditor({
                        'menuConfig': [
                                ['viewSourceCode'],
                                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                                ['list', 'justify', 'blockquote'],
                                ['createLink', 'insertHr', 'undo'],
                                ['insertImage', 'insertVideo', 'insertLocation', 'insertCode']
                        ]
                });

                let editor3 = $('#J_md_brief').wangEditor({
                        'menuConfig': [
                                ['viewSourceCode'],
                                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                                ['list', 'justify', 'blockquote'],
                                ['createLink', 'insertHr', 'undo'],
                                ['insertImage', 'insertVideo', 'insertLocation', 'insertCode']
                        ]
                });



                //追加字符串
                //editor.append('##学习简介');

                //提交表单
                $("#formSubmit").click(function () {
                        if ($("#J_name").val() && $("#J_md_text").val() && $("#J_color").val() && $("#J_path").val() && $("#J_tag").val() && $("#J_tag_code").val() ) {

                                $.ajax({
                                        url: "/learning/addItem",
                                        type: "post",
                                        dataType: "json",
                                        data: $('#addItemForm').serialize(),// 要提交的表单 ,
                                        success: function (msg) {

                                                if (msg.data == "success") {

                                                        art.dialog.tips('添加成功');
                                                        $('#formSubmit').attr("disabled", 'disabled');

                                                }

                                        }

                                });
                        } else {
                                art.dialog.tips('填写必要信息');
                        }
                });
        });
</script>
</body>
</html>