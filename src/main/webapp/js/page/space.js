
	
	
	$("#J_gz_btn").click(function(){
		//把userid的判断转为后台判断
 	    var url = window.location.href;
 	    $.ajax({
          url:"/cm/loginFlag",
          type:"post",
          success:function(msg){
          if(msg=="1"){//已登录
        	  var userid = '${user.id}' ;
      	 	var author_id = $("#by_id").val();
      	 	var gz_status = '${gz }';
      	 		if(author_id==userid){
      	 			art.dialog.alert('您不能关注自己');
      	 			return ;
      	 		}
      	 		if(gz_status=='ygz'){
      	 			return ;
      	 		}
      	 		$.ajax({
      	 			url:"/cm/follow",
      	 			type:"post",
      	 			data:{"author_id":author_id,"follow_id":userid},
      	 			success:function(msg){
      	 				if(msg=="success"){
      	 					art.dialog.tips('已关注');
      	 					window.location.href = window.location.href;
      	 				}			
      	 			}
      	 		});
          }else if(msg=="0"){//没有登录
          	window.location.href = "/cm/toLogin?redirectURI="+url; 
          }
          
          }
		});
 	
	 	

	});
