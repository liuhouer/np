$(document).ready(function () {


    $('#formSubmit').attr('disabled', true);
    $('#addItemForm').on('keyup', '#title', function (event) {
        if ($('#title').val().length >= 1 && $('#file1').val().length >= 1) {
            $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext'));

        }
        else
            $('#formSubmit').attr('disabled', true);

    });


    $('#addItemForm').on('change', '#file1', function (event) {
        if ($('#title').val().length >= 1 && $('#file1').val().length >= 1) {
            $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext'));

        }
        else {
            $('#formSubmit').attr('disabled', true);
        }

    });


});


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
                    var ness = $("#lrcid").val();
                    if (ness == "") {
                        art.dialog.alert('请选择您要上传的歌词文件！');
                        return false;
                    }

                    $("#f1")
                        .attr("action",
                            "lyrics/addLyrics.action")
                        .submit();

                },
                failure: function () {
                    return false;
                }
            });
}
			
	  