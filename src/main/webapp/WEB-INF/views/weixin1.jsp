<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>易元康健康</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/qinco.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/main2.css">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="form-control  btn-hero left"><label style="vertical-align: bottom;font-size: 16px;">预约信息填写</label><label class="margin-l10 margin-t10 label-small baizi">信息会加密保证您的隐私安全</label></div>


				<form  id="J_weixin1_form" class="form margin-t20" enctype="multipart/form-data">

					<span style="color: #999; opacity: 1;">姓名<font color="red">*</font></span>
					<div class="form-group padding-b20">
						<input id="J_name" placeholder="王小二" class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="name" type="text">
					</div>
					<span style="color: #999; opacity: 1;">电话<font color="red">*</font></span>
					<div class="form-group padding-b20">
						<input id="J_phone" placeholder="13826185040" class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="phone" type="text">
					</div>
					<span style="color: #999; opacity: 1;">地址</span>
					<div class="form-group padding-b20">
						<input id="J_address" placeholder="广州市白云区东平大道8号" class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="address" type="text">
					</div>
					<span style="color: #999; opacity: 1;">预约项目<font color="red">*</font></span>
					<div class="form-group padding-b20">
						<input id="J_project" placeholder="医生外出治疗" class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="project" type="text">
					</div>
					<div class="form-group padding-b20 ">
						<div>同意<label class="padding-l10"><input type="checkbox" class="" style="display: inline-block"/><a>服务知情同意书<a></a></label> </div>
					</div>
					<div class="form-group center">
						<input disabled="disabled" id="formSubmit" data-activetext="提交信息 ››"
							class="btn btn-hero btn-xlg margin-t10 grid90" value="提交信息"
							type="submit">
					</div>
				</form>
			</div>
	</div>
</div>
</body>
</html>