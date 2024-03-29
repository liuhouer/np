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

    code_draw();

    // 点击后刷新验证码
    $("#canvas").on('click', function() {
        code_draw();
    })


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
                        //禁用提交按钮。防止点击起来没完
                        $('#formSubmit').attr('disabled', true);
                        art.dialog.tips(msg.data + ' | 正在跳转..', 3);
                        var uri = $("#redirectURI").val();
                        if (uri.trim()) {
                            window.location.href = uri;
                        } else {

                            window.location.href = "/";
                        }
                    }else{//注册失败
                        art.dialog.tips('注册异常：'+msg.message);
                    }

                }

            });


        } else {

            art.dialog.tips('验证码错误！请重新输入！', 3);
            return ;
        }




    })
    
    
    function beforeSend(XMLHttpRequest) {
        $("#showResult").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:32px;height:32px;' /><div>");
    }

    function complete(XMLHttpRequest, textStatus) {
        $("#showResult").empty();
    }

});