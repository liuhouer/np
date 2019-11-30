function addSpan(obj) {
    document.getElementById(obj).className = "span";
}

function rmSpan(obj) {
    document.getElementById(obj).className = "";
}

function removes(lyricsid, userlyricsid) {
    art.dialog.confirm('你确定要删除这首最爱歌词吗？', function () {
        $("#f2").attr("action", "lyrics/remove.action?lyricsid=" + lyricsid + "&userlyricsid=" + userlyricsid).submit();
    }, function () {
        return;
    });
}


var uid = $("#J_uid").val();
var gz = $("#J_gz").val();

$("#J_gz_btn").click(function () {

    //把userid的判断转为后台判断
    var url = window.location.href;
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
                    dataType: "json",
                    data: {"author_id": author_id, "follow_id": userid},
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
