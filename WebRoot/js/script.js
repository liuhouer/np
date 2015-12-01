
/* 页面启动即刻加载 */
$(function(){
	/* 打开层 */
	$(".openP").click(function() {
		openPop({
			cover_layer : ".cover_layer",
			list_qr_pop : ".themes_alert"
		});
	});
	
	$('.icon_xl').click(function() {
		$(this).next().toggle();
	});
	$('.xl_con a').click(function() {
		$(this).parent().hide();
	});
	
});

/* 前台登录 */
function logon(){
	var username = $(".username").val().trim();
	var password = $(".password").val().trim();
	var sid = $(".schoolid").val().trim();
	var status = $(".login-status");
	
	if (username.length == 0) {
		status.html("用户名不能为空！");
		$(".username").focus();
	} else if (password.length == 0) {
		status.html("密码不能为空！");
		$(".password").focus();
	}else{
		$.ajax({
			type : 'POST',
			url : "client/login.action",
			data : {
				schoolid: sid,
				num : username,
				password : password
			},
			success: function(datas){
				 if(datas.status == "success"){
					 location.href=datas.result;
					 closePop(".themes_alert",".cover_layer");
					 status.html("&nbsp;");
				 }else{
					 status.html("用户名或密码不存在");
					 $(".username").focus();
					 $(".password").val("");
				 }
			} 
		});
	}
}

/* 删除表格记录 */
function del(id, action) {
	$.ajax({
		type : 'GET',
		url : action,
		data : {
			id : id
		},
		success : function() {
			parent.location.reload(true);
		}
	});
}

/* 退出登录 */
function logout(id) {
	$.ajax({
		type : 'POST',
		url : "/yunlu/user/logout",
		data : {
			userId : id
		},
		success : function() {
			window.parent.location.href = "/yunlu/page/ground/admin/login.jsp?loginType=false";
		}
	});
}

/* 添加系统设置 */
function addSysettings() {
	$(".addSysettings").colorbox({
		href : "/yunlu/page/ground/sysettings/addSysettings.jsp",
		inline : true,
		// width : "50%",
		// height : "45%",
		contentWidth : "750px",
		contentHeight : "450px",
		contentIframe : true
//		title : "添加系统"
	});
}




