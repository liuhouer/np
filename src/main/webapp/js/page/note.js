
function removes(obj){
	      var id=$(obj).attr('rel');
	      art.dialog.confirm('你确定要删除曾经的故事吗？', function () {
	    	  $("#f2").attr("action","note/remove?id="+id).submit();
			}, function () {
			    return ;
			});
	      
	      
}

function toEditInfo(){
	$("#f1").submit();
}



$(function(){
	var editor = $('#J_md_text').wangEditor({
		'menuConfig': [
		                ['viewSourceCode'],
						['fontFamily','fontSize','bold','setHead'],
						['list','justify','blockquote'],
						['createLink','insertHr','undo']
					]
	});
});