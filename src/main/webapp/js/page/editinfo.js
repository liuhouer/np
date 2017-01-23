
var tail_slug = $("#J_tail_slug").val();

$(function(){
	$('#user_nick').datepicker({
		format: 'yyyy-mm-dd',
		autoclose:true
	});
})
function saves(){
	var newpwd1=$("#new_password").val();
	var newpwd2=$("#new_password_confirmation").val();
	var slug = $("#tail_slug").val();
	if(newpwd2!=newpwd1&&newpwd1!=""&&newpwd1!=''&&newpwd1!=null){//修改密码填了不一致
		art.dialog.alert('2次密码不一致');
		return;
	}
	if(slug && slug!=tail_slug){
		$.ajax({

            url:"/cm/tailFlag",

            type:"post",

            data:{"tail":slug},

            success:function(msg){

                if(msg=="exist"){//存在

                    art.dialog.tips('域名代号已存在');
                    $("#tail_slug").focus();

					return;

                }        

            }

        });
	}else{
        	
		$("#f1").attr("action","/cm/saveEditInfo").submit();
	}

	 
	
	
}