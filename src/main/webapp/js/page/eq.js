


//load data...



$(function(){
	var pagenow = parseInt($("#pagenow").val())-1;
	 $.ajax({
			url:"/romeo/equery",
			type:"post",
			data:{"currentpage":pagenow},
			beforeSend:beforeSend, //发送请求
	        complete:complete,
			success:function(data){
				if(data){
					$("#J_maincontent").append(data);
				}			
			}
		});
	 
	 
	 
	 
	 
	 //搜索点击事件
	 $("#J_search").click(function(){
		 
		 var keyword = $("#keyword").val();
		 if(keyword){
			 
			 $.ajax({
				 url:"/romeo/equery",
				 type:"post",
				 data:{"keyword":keyword},
				 beforeSend:beforeSend, //发送请求
				 complete:complete,
				 success:function(data){
					 if(data){
						 $("#J_maincontent").empty().append(data);
						 $("#pageForm").remove();
					 }			
				 }
			 });
		 }
	 });
	 
})


 function beforeSend(XMLHttpRequest){
	  $("#J_progress").append("<div><img src='/img/loading.gif' style='width:48px;height:48px;' /><div>");
  }
  
  function complete(XMLHttpRequest, textStatus){
	  $("#J_progress").empty();
  }
  

