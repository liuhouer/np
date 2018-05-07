$(function () {
    var albumpath = $("#albumpath").val();
    if (albumpath != "" && albumpath != null && albumpath != '' && albumpath != 'Failure...') {
        window.parent.showtip2("成功！");
    }
    if (albumpath == 'Failure...') {
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
			