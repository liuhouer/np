function addSpan(obj) {
    document.getElementById(obj).className = "span";
}

function rmSpan(obj) {
    document.getElementById(obj).className = "";
}

function removes(obj) {
    var id = $(obj).attr('rel');
    art.dialog.confirm('你确定要移除这个粉丝吗？', function () {
        $.ajax({
            url: "/cm/rmfollow",
            type: "post",
            data: {"id": id},
            dataType: "json",
            success: function (msg) {
                if (msg.data == "success") {
                    art.dialog.tips('已移除');
                    window.location.href = window.location.href;
                } else {
                    art.dialog.tips('what happend...');
                }
            }
        });
    }, function () {
        return;
    });
}

var uid = $("#J_uid").val();
var gz = $("#J_gz").val();


$("#J_gz_btn").click(function () {
    var url = window.location.href;
    //把userid的判断转为后台判断
    $.ajax({
        url: "/cm/loginFlag",
        type: "get",
        dataType: "json",
        success: function (msg) {
            if (msg.data == "1") {//已登录
                var userid = uid;
                var author_id = $("#by_id").val();
                var gz_status = gz;
                if (author_id == userid) {
                    art.dialog.alert('您不能关注自己');
                    return;
                }
                if (gz_status == 'ygz') {
                    return;
                }
                $.ajax({
                    url: "/cm/follow",
                    type: "post",
                    data: {"author_id": author_id, "follow_id": userid},
                    dataType: "json",
                    success: function (msg) {
                        if (msg.data == "success") {
                            art.dialog.tips('已关注');
                            window.location.href = window.location.href;
                        }
                    }
                });
            } else if (msg.data == "0") {//没有登录
                window.location.href = "/login?redirectURI=" + url;
            }

        }
    });


});
