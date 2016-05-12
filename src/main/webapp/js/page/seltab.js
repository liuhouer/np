$(function(){
	
		var tabs = "${tabs}";
		//set样式
		$("#J_tabs").find('li').each(function(){
			if(tabs==$(this).attr('cname')){
				$(this).addClass('active');//添加样式，样式名为className
			}
			
		});
});