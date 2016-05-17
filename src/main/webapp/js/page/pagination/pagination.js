
//pagination
function pagination(posturl,pagenow){
	
			
			//load data 
			$.ajax({
					url:posturl,
					type:"post",
					data:{"currentpage":pagenow},
					beforeSend:beforeSend, //发送请求
			        complete:complete,
					success:function(data){
						if(data){
							$("#J_maincontent").empty().append("<div id=\"J_progress\" class=\"center padding-t20\"></div>").append(data);
						}			
					}
				});
			
			
		
}


//除去所有的active
 function rmactive(){
	 $(".qinco-pagination ").find("li").each(function(){
		 $(this).removeClass("active");
	 })
 }

 function beforeSend(XMLHttpRequest){
	  $("#J_progress").append("<div><img src='/img/loading.gif' style='width:48px;height:48px;' /><div>");
  }
  
  function complete(XMLHttpRequest, textStatus){
	  $("#J_progress").empty();
  }
  


