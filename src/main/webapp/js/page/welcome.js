
$(function(){
	var flag = "${signout}";
	if(flag=='true'){
		if(QC.Login.check()){
			QC.Login.signOut();
		}
	}
})


function zan(lrcid,userid){
   $.ajax({
		url:"/zanAction/zan",
		type:"post",
		data:{"lyricsid":lrcid,"userid":userid},
		success:function(msg){
			if(msg=="success"){
				art.dialog.tips('已赞!');
				window.location.href = window.location.href;
			}			
		}
	});
}

//load data...
$(function(){
	var pagenow = parseInt($("#pagenow").val())-1;
	 $.ajax({
			url:"/lovequery",
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


 function beforeSend(XMLHttpRequest){
	  $("#J_progress").append("<div><img src='/img/loading.gif' style='width:48px;height:48px;' /><div>");
  }
  
  function complete(XMLHttpRequest, textStatus){
	  $("#J_progress").empty();
  }
  
