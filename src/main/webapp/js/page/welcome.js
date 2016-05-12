
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


