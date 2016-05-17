


//load data...
$(function(){
	var pagenow = parseInt($("#pagenow").val())-1;
	 $.ajax({
			url:"/note/storyquery",
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
})

//bind pagination

$(function(){
	
	//分页事件	
	$(".qinco-pagination ").find("li a").each(function(){
		
		
		$(this).click(function(){
			var pagenow = $(this).attr("data");
			pagination("/note/storyquery",pagenow);
			//移除所有的active
			rmactive();
			//添加选中样式
			$(this).parent().addClass('active');
		})
	})
	
	
});

