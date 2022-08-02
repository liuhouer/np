<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"
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
    <meta name="description" content="NorthPark￥ﾽﾱ￨ﾧﾆ">
    <meta name="keywords" content="NorthPark">
    <meta name="author" content="bruce">
    <meta name="robots" content="index,follow,archive">
    <title>NorthPark / ￦ﾷﾻ￥ﾊﾠ￨ﾯﾾ￧ﾨﾋ|￤ﾹﾦ￧ﾱﾍ</title>

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
                            <span class="glyphicon glyphicon-plus"></span> ￦ﾷﾻ￥ﾊﾠ￥ﾭﾦ￤ﾹﾠ￨ﾵﾄ￦ﾺﾐ
                        </h4>
                        <hr>
                    </div>
					<input type="hidden" name="id" value="${model.id }"/>
                    <div class="form-group ">
                        <span class="glyphicon glyphicon-star"></span> ￨ﾯﾾ￧ﾨﾋ|￤ﾹﾦ￧ﾱﾍ
                        <input id="J_name" placeholder="￨ﾯﾾ￧ﾨﾋ/￤ﾹﾦ￧ﾱﾍ" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="title" type="text" value="${model.title }">
                    </div>
                    <div class="form-group ">
                               
                         <span class="glyphicon glyphicon-star"></span>￤ﾸﾋ￨ﾽﾽ￥ﾜﾰ￥ﾝﾀ     
                         <textarea id="J_path" style="height: 200px; max-height: 400px;"
                                      name="path" rows="5">
								${model.path }
						 </textarea>      
                    </div>
					<div class="form-group ">
						<span class="glyphicon glyphicon-star"></span>￥ﾭﾦ￤ﾹﾠ￩ﾢﾜ￨ﾉﾲ
                        <input id="J_color" placeholder="￥ﾭﾦ￤ﾹﾠ￩ﾢﾜ￨ﾉﾲ" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="color" type="text" value="${model.color }">
                    </div>
                    <div class="form-group ">
                        <span class="glyphicon glyphicon-star"></span>￥ﾭﾦ￤ﾹﾠ￦ﾠﾇ￧ﾭﾾ
                        <input id="J_tag" placeholder="￥ﾭﾦ￤ﾹﾠ￦ﾠﾇ￧ﾭﾾ" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="tags" type="text" value="${model.tags },￨ﾯﾾ￧ﾨﾋ￥ﾈﾆ￤ﾺﾫ">
                    </div>
                    <div class="form-group ">
                        <span class="glyphicon glyphicon-star"></span>￥ﾭﾦ￤ﾹﾠ￦ﾠﾇ￧ﾭﾾ-￨ﾋﾱ￦ﾖﾇ
                        <input id="J_tag_code" placeholder="￥ﾭﾦ￤ﾹﾠ￦ﾠﾇ￧ﾭﾾ-￨ﾋﾱ￦ﾖﾇ" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="tags_code" type="text" value="${model.tags_code },classhare">
                    </div>

                    <div class="form-group ">
                        <span class="glyphicon glyphicon-star"></span>￥ﾮﾚ￤ﾻﾷ
                        <input id="J_price" placeholder="￥ﾭﾦ￤ﾹﾠ￥ﾮﾚ￤ﾻﾷ" required
                               class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
                               name="price" type="number" value="${model.price }">
                    </div>

                    <div class="form-group">
                        <span class="glyphicon glyphicon-star"></span>￥ﾭﾦ￤ﾹﾠ￩ﾢﾄ￨ﾧﾈ
                        <textarea id="J_md_brief" style="height: 200px; max-height: 400px;"
                                  name="brief" rows="5">
                            ${model.brief }

                        <blockquote style="padding: 0px 0px 0px 10px; border-left-color: rgb(208, 229, 242); line-height: 1.4;"><pre style="box-sizing: inherit; overflow-y: scroll; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 14px; line-height: 20px; font-family: &quot;courier new&quot;; padding: 30px; margin-top: 20px; color: rgb(111, 187, 114); background: rgb(43, 48, 59); border-width: 0px; border-style: initial; border-color: initial; border-radius: 3px;">￨﾿ﾙ￩ﾗﾨ￨ﾯﾾ￦ﾘﾯ￥ﾉﾍ￧ﾫﾯ￥ﾤﾧ￧ﾥﾞCoderWhy￥ﾅﾨ￧ﾨﾋ￤ﾺﾲ￨ﾇﾪ￦ﾎﾈ￨ﾯﾾ,￨ﾯﾦ￧ﾻﾆ￣ﾀﾁ￦ﾷﾱ￥ﾅﾥ￣ﾀﾁ￧ﾳﾻ￧ﾻﾟ￥ﾭﾦ￤ﾹﾠ￥ﾉﾍ￧ﾫﾯ￧ﾟﾥ￨ﾯﾆ,
                        ￧ﾎﾋ￧ﾺﾢ￥ﾅﾃ￤ﾽﾜ￤ﾸﾺ￥ﾤﾚ￦ﾉﾀ985￣ﾀﾁ211￥ﾐﾍ￧ﾉﾌ￥ﾤﾧ￥ﾭﾦ￧ﾉﾹ￨ﾁﾘ￨ﾮﾲ￥ﾸﾈ￯ﾼﾌ￦ﾾﾳ￥ﾤﾧ￥ﾈﾩ￤ﾺﾚThe Wain￥ﾅﾬ￥ﾏﾸCTO￣ﾀﾂ
                        ￤ﾸﾰ￥ﾯﾌ￧ﾚﾄ￨ﾽﾯ￤ﾻﾶ￥ﾼﾀ￥ﾏﾑ￥ﾒﾌ￦ﾕﾙ￥ﾭﾦ￧ﾻﾏ￩ﾪﾌ￯ﾼﾌ￦ﾛﾾ￧ﾻﾏ￥ﾸﾦ￩ﾢﾆ￥ﾛﾢ￩ﾘﾟ￥ﾼﾀ￥ﾏﾑ￥ﾇﾺ￤ﾼﾗ￥ﾤﾚ￥ﾤﾧ￥ﾞﾋ￩ﾡﾹ￧ﾛﾮ￥ﾒﾌ￨ﾽﾯ￤ﾻﾶ￧ﾳﾻ￧ﾻﾟ,
                        ￨ﾯﾾ￧ﾨﾋ￤ﾽﾓ￧ﾳﾻ￨﾿ﾘ￦ﾘﾯ￥ﾾﾈ￥ﾐﾸ￥ﾼﾕ￤ﾺﾺ￧ﾚﾄ,￦ﾄﾟ￥ﾅﾴ￨ﾶﾣ￧ﾚﾄ￥ﾰﾏ￤ﾼﾙ￤ﾼﾴ￥ﾏﾯ￤ﾻﾥ￧ﾜﾋ￤ﾸﾋ￤ﾸﾋ￩ﾝﾢ￧ﾚﾄ￥ﾤﾧ￧ﾺﾲ￧ﾛﾮ￥ﾽﾕ.</pre></blockquote><p></p>

                        </textarea>
                    </div>

                    <div class="form-group">
                        <span class="glyphicon glyphicon-star"></span>￥ﾭﾦ￤ﾹﾠ￥ﾆﾅ￥ﾮﾹ
							<textarea id="J_md_text" style="height: 200px; max-height: 400px;"
                                      name="content" rows="5">
								${model.content }

        <pre style="box-sizing: inherit; overflow-y: scroll; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 14px; line-height: 20px; font-family: &quot;courier new&quot;; padding: 30px; margin-top: 20px; color: rgb(111, 187, 114); background: rgb(43, 48, 59); border-width: 0px; border-style: initial; border-color: initial; border-radius: 3px;">￨ﾯﾾ￧ﾨﾋ￧ﾛﾮ￥ﾽﾕ￯ﾼﾚ
        ￢ﾔﾜ￢ﾔﾀ￢ﾔﾀ￥ﾅﾬ￥ﾼﾀ￨ﾯﾾ
        | ￢ﾔﾜ￢ﾔﾀ￢ﾔﾀ02￣ﾀﾁ￩ﾂﾂ￩ﾀﾅ￥ﾉﾍ￧ﾫﾯ￥ﾼﾀ￥ﾏﾑ.mp4 216.92M
        | ￢ﾔﾔ￢ﾔﾀ￢ﾔﾀ￥ﾉﾍ￧ﾫﾯ￨ﾁﾌ￤ﾸﾚ￥ﾏﾑ￥ﾱﾕ.mp4 1.14G
        ￢ﾔﾜ￢ﾔﾀ￢ﾔﾀ￧ﾳﾻ￧ﾻﾟ￨ﾯﾾ

        </pre>
						    </textarea>
                    </div>


					
                    <div class="form-group">
                        <input id="formSubmit" data-activetext="￦ﾷﾻ￥ﾊﾠ ￢ﾀﾺ￢ﾀﾺ"
                               class="btn btn-hero btn-xlg margin-t10 grid50" value="￦ﾷﾻ￥ﾊﾠ"
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



        //￨﾿ﾽ￥ﾊﾠ￥ﾭﾗ￧ﾬﾦ￤ﾸﾲ
        //editor.append('##￥ﾭﾦ￤ﾹﾠ￧ﾮﾀ￤ﾻﾋ');

        //￦ﾏﾐ￤ﾺﾤ￨ﾡﾨ￥ﾍﾕ
        $("#formSubmit").click(function () {
            if ($("#J_name").val() && $("#J_md_text").val() && $("#J_color").val() && $("#J_path").val() && $("#J_tag").val() && $("#J_tag_code").val() ) {

                $.ajax({
                    url: "/learning/addItem",
                    type: "post",
                    dataType: "json",
                    data: $('#addItemForm').serialize(),// ￨ﾦﾁ￦ﾏﾐ￤ﾺﾤ￧ﾚﾄ￨ﾡﾨ￥ﾍﾕ ,
                    success: function (msg) {

                        if (msg.data == "success") {

                            art.dialog.tips('￦ﾷﾻ￥ﾊﾠ￦ﾈﾐ￥ﾊﾟ');
                            $('#formSubmit').attr("disabled", 'disabled');

                        }

                    }

                });
            } else {
                art.dialog.tips('￥ﾡﾫ￥ﾆﾙ￥﾿ﾅ￨ﾦﾁ￤﾿ﾡ￦ﾁﾯ');
            }
        });
    });
</script>
</body>
</html>