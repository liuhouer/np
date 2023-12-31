$(function () {
    var album_path = $("#album_path").val();
    if (album_path != "" && album_path != null && album_path != '' && album_path != 'Failure...') {
        window.parent.showtip2("成功！");
    }
    if (album_path == 'Failure...') {
        window.parent.showtip2("添加失败！");
    }
});

function openIt() {
    // SUCCESS AJAX CALL, replace "success: false," by:     success : function() { callSuccessFunction() }, 
    $("[class^=validate]")
        .validationEngine(
            {
                success: function () {

                    $("#f1")
                        .attr("action",
                            "lyrics/update.action")
                        .submit();

                },
                failure: function () {
                    return false;
                }
            });
}
			