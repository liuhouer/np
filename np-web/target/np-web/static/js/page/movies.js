//
//	function pay(movie_id,price){
//		
//		if(movie_id && price){
//			$("#J_movieid").val(movie_id);
//			$("#J_price").val(price);
//			$("#J_sub_ali").click();
//		}
//		
//	}
//	
//	
//	function showtoggle(id){
//		if(id){
//			$("#donate_"+id).toggle();
//		}
//	}


function handup(id) {
    $.ajax({
        url: "/movies/handup",
        type: "post",
        data: {"id": id},
        dataType: "json",
        success: function (msg) {
            if (msg.data == "success") {
                art.dialog.tips('置顶成功');
            } else {
                art.dialog.tips('error happened.');
            }
        }
    });

}

function hideup(id) {
    $.ajax({
        url: "/movies/hideup",
        type: "post",
        data: {"id": id},
        dataType: "json",
        success: function (msg) {
            if (msg.data == "success") {
                art.dialog.tips('隐藏成功');
                window.location.href = window.location.href;
            } else {
                art.dialog.tips('error happened.');
            }
        }
    });

}