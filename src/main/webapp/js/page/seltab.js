$(function(){
	
		var tabs = $("#J_tab_name").val();
		//set样式
		$("#J_tabs").find('li').each(function(){
			
			if(tabs==$(this).attr('cname')){
				$(this).addClass('active');//添加样式，样式名为className
			}
			
		});
});