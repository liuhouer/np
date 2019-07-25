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
    <meta name="description" content="NorthPark影视">
    <meta name="keywords" content="NorthPark">
    <meta name="author" content="bruce">
    <meta name="robots" content="index,follow,archive">
    <title>NorthPark / 添加电影</title>

    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <link href="https://northpark.cn/statics/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet"/>

</head>

<body>
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>


<div class="clearfix maincontent">
    <div class="container">
        <div class="mainbody" style="margin-top: 5em;">
            <div class="align-center bg-white radius-5 padding10 max-width-700 min-width-300">
                <form method="POST" action="/soft/addItem" accept-charset="UTF-8" role="form" id="addItemForm"
                      style="color: #444;" class="form margin-t20" enctype="multipart/form-data">
                    <div class="clearfix">
                        <h4>
                            <span class="glyphicon glyphicon-plus"></span> 添加软件资源
                        </h4>
                        <hr>
                    </div>
					<input type="hidden" name="id" value="${model.id }"/>
                    <div class="form-group ">
                        <span class="glyphicon glyphicon-star padding-b20"></span> 标题
                        <input id="J_name" placeholder="标题" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="title" type="text" value="${model.title }">
                    </div>
                    <div class="form-group ">
						<span class="glyphicon glyphicon-star padding-b20"></span>采集地址【链接请求地址】
                        <input id="J_color" placeholder="采集地址【链接请求地址】" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="retcode" type="text" value="${model.retcode }">
                    </div>
                    <div class="form-group ">
                               
                         <span class="glyphicon glyphicon-star padding-b20"></span>下载地址     
                         <textarea id="J_path" style="height: 200px; max-height: 400px;"
                                      name="path" rows="5">
								${model.path }
								<a href="https://waitsun.ctfile.com/fs/160721-293646240" class="btn btn-donate mr-3 downtip btn-warning" role="button" title="Disk Drill 3商业破解版 专业Mac数据恢复软件" target="_black"><i class="dobby v3-download"></i> 云盘下载</a>
						 </textarea>      
                    </div>
                    
                    <div class="form-group">
                    		<span class="glyphicon glyphicon-star padding-b20"></span>软件简介
							<textarea id="J_brief" style="height: 200px; max-height: 400px;"
                                      name="brief" rows="5">
								${model.brief }
								
								001
								系统、应用软件
								002
								开发、设计软件
								003
								媒体软件
								004
								网络、安全软件
								005
								其他软件
								006
								游戏一箩筐
								007
								限免软件
								008
								疑难杂症
								005
								其他软件
						    </textarea>
                    </div>
                    <div class="form-group">
                    		<span class="glyphicon glyphicon-star padding-b20"></span>软件详情
							<textarea id="J_md_text" style="height: 200px; max-height: 400px;"
                                      name="content" rows="5">
								${model.content }
						    </textarea>
                    </div>
                    
                    
                    <div class="form-group ">
						<span class="glyphicon glyphicon-star padding-b20 "></span>标签
                        <input id="J_color" placeholder="标签" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="tags" type="text" value="${model.tags }">
                    </div>
                    
                    <div class="form-group ">
						<span class="glyphicon glyphicon-star  padding-b20"></span>英文标签
                        <input id="J_color" placeholder="英文标签" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="tagscode" type="text" value="${model.tagscode }">
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


<script src="https://northpark.cn/statics/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="https://northpark.cn/statics/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        var editor = $('#J_md_text').wangEditor({
            'menuConfig': [
                ['viewSourceCode'],
                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                ['list', 'justify', 'blockquote'],
                ['createLink', 'insertHr', 'undo']
            ]
        });
        
        var editor2 = $('#J_path').wangEditor({
            'menuConfig': [
                ['viewSourceCode'],
                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                ['list', 'justify', 'blockquote'],
                ['createLink', 'insertHr', 'undo']
            ]
        });
        
        var editor3 = $('#J_brief').wangEditor({
            'menuConfig': [
                ['viewSourceCode'],
                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                ['list', 'justify', 'blockquote'],
                ['createLink', 'insertHr', 'undo']
            ]
        });

        //追加字符串
        //editor.append('##电影简介');

        //提交表单
        $("#formSubmit").click(function () {
            if ($("#J_name").val() && $("#J_md_text").val()  && $("#J_path").val()) {

                $.ajax({
                    url: "/soft/addItem",
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