$(function () {
    $('#login #newPassword').focus(function () {
        $('#owl-login').addClass('password');
    }).blur(function () {
        $('#owl-login').removeClass('password');
    });
});

function em(email) {

    var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;

    if (Regex.test(email)) {

        return true;

    } else {
        return false;
    }

}

$(document).ready(function () {


    if ($('#formSubmit').val().length < 6) {

        $('#formSubmit').attr('disabled', true);
    }


    $('#signupForm').on('keyup', '#newPassword', function (event) {
        if ($('#newPassword').val().length >= 6 && em($('#newAccount').val()))
            $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext'));
        else
            $('#formSubmit').attr('disabled', true);
    });

    $("#newAccount").change(function () {
        if ($('#newPassword').val().length >= 6 && em($('#newAccount').val()))
            $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext'));
        else
            $('#formSubmit').attr('disabled', true);
    });


    //signup em....
    $("#formSubmit").click(function () {
    	$.ajax({
            url: "/cm/signup",
            type: "post",
            beforeSend: beforeSend, //发送请求
            complete: complete,
            dataType:"json",
            data: $("#signupForm").serialize(),
            success: function (msg) {
            	//请求成功200
            	if(msg.result){
            		
            		//注册成功
            		if (msg.data.result == "success") {
                        //禁用提交按钮。防止点击起来没完
                        $('#formSubmit').attr('disabled', true);
                        art.dialog.tips(msg.data.info + ' | 正在跳转..', 3);
                        var uri = $("#redirectURI").val();
                        if (uri.trim()) {
                            window.location.href = uri;
                        } else {

                            window.location.href = "/";
                        }
                    } else {//注册异常
                        art.dialog.tips(msg.data.info);
                        //禁用提交按钮。防止点击起来没完
                        $('#formSubmit').attr('disabled', true);
                    }
            	}else{//请求失败了
            		  art.dialog.tips('注册异常：'+msg.message);
            	}
                
            }
        	
        });
    })
    
    
    function beforeSend(XMLHttpRequest) {
        $("#showResult").append("<div><img src='/static/img/loading.gif' style='width:32px;height:32px;' /><div>");
    }

    function complete(XMLHttpRequest, textStatus) {
        $("#showResult").empty();
    }

});