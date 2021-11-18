$(document).ready(function () {


    $('#formSubmit').attr('disabled', true);
    $('#addItemForm').on('keyup', '#title', function (event) {
        if ($('#title').val().length >= 1 && ('#loveDate').val().length >= 1 && $('#file1').val().length >= 1) {
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
			
	  