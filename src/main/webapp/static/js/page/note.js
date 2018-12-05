function removes(obj) {
    var id = $(obj).attr('rel');
    art.dialog.confirm('你确定要删除曾经的故事吗？', function () {
        $.ajax({
            url: "/note/remove",
            type: "post",
            beforeSend: beforeSend, //发送请求
            complete: complete,
            data: {"id": id},
            dataType: "json",
            success: function (msg) {
                //console.log(msg);
                if (msg.data == "success.") {
                    //成功|js移除模块

                    $(obj).parent().parent().remove();
                    art.dialog.tips(msg.result);
                } else {
                    art.dialog.tips(msg.result);
                }
            }
        });

    }, function () {
        return;
    });


}

function beforeSend(XMLHttpRequest) {
    $("#showResult").append("<div><img src='/statics/img/loading.gif' style='width:32px;height:32px;' /><div>");
}

function complete(XMLHttpRequest, textStatus) {
    $("#showResult").empty();
}


$(function () {
    var editor = $('#J_md_text').wangEditor({
        'menuConfig': [
            ['viewSourceCode'],
            ['fontFamily', 'fontSize', 'bold', 'setHead'],
            ['list', 'justify', 'blockquote'],
            ['createLink', 'insertHr', 'undo', 'redo'],
            ['insertImage', 'insertVideo', 'insertLocation', 'insertCode']
        ]
    });
});