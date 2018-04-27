$(function () {
    //设置
    $(".tag-node").each(function () {
        $(this).click(function () {
            var val = $(this).val();

            $("#keyword").val(val);

            if (val) {
                $("#J_tag_fm").submit();
            }
        })

    })

    var keyword = $("#keyword").val();
    //选中样式
    $(".tag-node").each(function () {
        var val = $(this).val();

        if (keyword == val) {
            $(this).removeClass().addClass("btn tag-node-sel");
        }

    })


    //清除选项
    $("#J_clear_tag").click(function () {
        $("#keyword").val("");
        $("#J_tag_fm").submit();

    })
})

function showquan(id) {

    if (id) {
        window.open("/cp/show/" + id);
    }

}