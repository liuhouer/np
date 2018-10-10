//load data...


$(function () {


    //搜索
    $("#J_search").click(function () {
        $("#J_search").attr('disabled', true);
        if ($("#keyword").val() && $("#keyword").val() != "${keyword }") {

            window.location.href = "/romeo/page/1?keyword=" + $("#keyword").val();
        }
        setTimeout("$('#J_search').removeAttr('disabled')", 5000); //设置5秒后提交按钮 显示  
    })

    //搜索点击事件
//	 $("#J_search").click(function(){
//		 
//		 var keyword = $("#keyword").val();
//		 if(keyword){
//			 
//			 $.ajax({
//				 url:"/romeo/equery",
//				 type:"post",
//				 data:{"keyword":keyword},
//				 beforeSend:beforeSend, //发送请求
//				 complete:complete,
//				 success:function(data){
//					 if(data){
//						 $("#J_maincontent").empty().append(data);
//						 $("#pageForm").remove();
//					 }			
//				 }
//			 });
//		 }
//	 });

})


var keyword = $("#keyword").val();
if (keyword) {
    $("#pageForm a").each(function () {
        var href = $(this).attr("href");
        $(this).attr("href", href + "?keyword=" + keyword);
    })
}


function beforeSend(XMLHttpRequest) {
    $("#J_progress").append("<div><img src='/static/img/loading.gif' style='width:48px;height:48px;' /><div>");
}

function complete(XMLHttpRequest, textStatus) {
    $("#J_progress").empty();
}
  

