//绑定捂住眼睛的动作 
$(function () {
    $('#login #password').focus(function () {
        $('#owl-login').addClass('password');
    }).blur(function () {
        $('#owl-login').removeClass('password');
    });
});

$(document).ready(function () {

    code_draw();

    // 点击后刷新验证码
    $("#canvas").on('click', function() {
        code_draw();
    })


    $('#loginForm').on('keyup', '#password', function (event) {
        if ($('#password').val().length >= 6)
            $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext'));
        else
            $('#formSubmit').attr('disabled', true);
    });

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            var cansub = $('#formSubmit').attr('disabled');
            var can = (cansub == 'disabled');
            if (!cansub) {//没有禁用

                $("#formSubmit").click();
            }

        }
    });


    $('#formSubmit').click(function () {
        //校验验证码
        // 将输入的内容转为大写，可通过这步进行大小写验证
        var val = $("#code").val().toLowerCase();
        // 获取生成验证码值
        var num = $('#canvas').attr('data-code');
        if (val == '') {
            art.dialog.tips('请输入验证码...', 3);
            return ;
        } else if (val == num) {

            $.ajax({
                url: "/cm/login",
                type: "post",
                beforeSend: beforeSend, //发送请求
                complete: complete,
                dataType: "json",
                data: $("#loginForm").serialize(),
                success: function (msg) {
                    if(msg.result){
                        //登陆成功
                        console.log(msg.data);
                        //禁用提交按钮。防止点击起来没完
                        $('#formSubmit').attr('disabled', true);
                        art.dialog.tips(msg.message + ' | 正在跳转..', 3);
                        var uri = $("#redirectURI").val();
                        if (uri.trim()) {
                            window.location.href = uri;
                        } else {

                            window.location.href = "/";
                        }
                    }else{
                        art.dialog.tips('登录异常：'+msg.message);
                    }

                }

            });



        } else {

            art.dialog.tips('验证码错误！请重新输入！', 3);
            return ;
        }


    });

    function beforeSend(XMLHttpRequest) {
        $("#showResult").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:32px;height:32px;' /></div>");
    }

    function complete(XMLHttpRequest, textStatus) {
        $("#showResult").empty();
    }


});


