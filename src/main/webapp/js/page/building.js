/* var i = 5;
		var intervalid;
		intervalid = setInterval("fun()", 1000);
		function fun() {
			if (i == 0) {
				window.location.href = "/";
				clearInterval(intervalid);
			}
			$("#mes").html("<font color=\"blue\">"+i+"</font>");
			i--;
		}
		 */
		 

			$(function(){
				$("#mes").click(function(){
					window.location.href="/";
				})
			})
		console.log('非法请求资源页，IP已记录');