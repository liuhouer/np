$(function(){
	
		var tabs = $("#J_tabs").val();
		//set样式
		$("#J_tabs").find('li').each(function(){
			if(tabs==$(this).attr('cname')){
				$(this).addClass('active');//添加样式，样式名为className
			}
			
		});
});