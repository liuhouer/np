var lrcid = $("#J_lrcid").val();
var uid = $("#J_uid").val();
var yizan = $("#J_yizan").val();
$(document).ready(function () {


    //获取所有点赞人填充
    $("#J_lovers_box").click(function () {

        $.ajax({

            url: "/lyrics/getMoreZan",

            type: "post",

            data: {"lyricsid": lrcid},

            success: function (data) {
                //填充结果
                $("#J_zan_div").empty().prepend(data.replace(/(^")|("$)/g, ''));
            }

        });
    })

    $("#J_commentBtn").click(function () {
        var comment = $("#J_comment").val();
        if (comment) {
            $.ajax({
                url: "/zanAction/addComment",
                type: "post",
                data: {"lyricsid": lrcid, "userid": uid, "comment": comment},
                success: function (msg) {
                    if (msg == "success") {
                        art.dialog.tips('评论成功');
                        window.location.href = window.location.href;
                    }
                }
            });
        }

    });


    $("#J_gz_btn").click(function () {
        //把userid的判断转为后台判断
        var uri = window.location.href;
        if (yizan == 'yizan') {
            return false;
        }
        $.ajax({
            url: "/cm/loginFlag",
            type: "post",
            success: function (msg) {
                if (msg == "1") {//已登录
                    var userid = uid;
                    $.ajax({
                        url: "/zanAction/zan",
                        type: "post",
                        data: {"lyricsid": lrcid, "userid": userid},
                        success: function (msg) {
                            if (msg == "success") {
                                $("#J_gz_btn").text('已爱上~');
                            }
                        }
                    });

                } else if (msg == "0") {//没有登录

                    window.location.href = "/login?redirectURI=" + uri;
                }

            }
        });


    });


    //load comment
    loadcmt();


    //加载更多
    $("#loadStuffCommentBtn").click(function () {
        var pagenow = $("#comment_id_from").val();
        $("#comment_id_from").val(parseInt(pagenow) + 1);
        loadcmt();

    })


});


$(function () {
    if (uid) {

        var editor = $('#J_comment').wangEditor({
            'menuConfig': [
                ['viewSourceCode'],
                ['fontFamily', 'fontSize', 'bold', 'setHead'],
                ['list', 'justify', 'blockquote'],
                ['createLink', 'insertHr', 'undo', 'redo'],
                ['insertImage', 'insertVideo', 'insertLocation', 'insertCode']

            ]
        });
    }
});


//load data...
function loadcmt() {
    var pagenow = $("#comment_id_from").val();
    var lrcid = $("#J_lrc_id").val();
    $.ajax({
        url: "/lyrics/commentQuery",
        type: "post",
        data: {"currentpage": pagenow, "lrcid": lrcid},
        beforeSend: beforeSend, //发送请求
        complete: complete,
        success: function (data) {
            if (data) {
                $("#stuffCommentBox").append(data);
                var tail = $("#J_tail").val();
                if (tail == 'tail') {
                    $("#loadStuffCommentBtn").remove();
                }
            }
        }
    });

}


function beforeSend(XMLHttpRequest) {
    $("#loadingAnimation").show();
}

function complete(XMLHttpRequest, textStatus) {
    $("#loadingAnimation").hide();
}
  
  