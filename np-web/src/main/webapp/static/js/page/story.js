//load data...
$(function () {
    var pagenow = parseInt($("#pagenow").val()) - 1;
    $.ajax({
        url: "/note/storyquery",
        type: "post",
        data: {"currentPage": pagenow},
        beforeSend: beforeSend, //发送请求
        complete: complete,
        success: function (data) {
            if (data) {
                $("#J_maincontent").append(data);
            }
        }
    });
})


function beforeSend(XMLHttpRequest) {
    $("#J_progress").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:48px;height:48px;' /></div>");
}

function complete(XMLHttpRequest, textStatus) {
    $("#J_progress").empty();
}
  

