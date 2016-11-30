<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<meta http-equiv="Content-Language" content="zh-CN">

<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon" href="/img/favicon.ico">
<title>星座运势 | NorthPark</title>
<meta name="description" content="星座运势">
<meta name="keywords" content="星座运势">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<style type="text/css">




</style>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
		<div class="clearfix maincontent grayback">
			<div class="container mainbody">
				<div class="row">
					<div class="col-md-12">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 " id="J_white_div">
								<div class="row margin10 post_article">

								<div class="navbar-collapse  ">
									<ul class="nav mainhead-nav"  id="J_ul_astro">
										
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E7%89%A1%E7%BE%8A%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>白羊座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E9%87%91%E7%89%9B%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>金牛座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%8F%8C%E5%AD%90%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>双子座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%B7%A8%E8%9F%B9%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>巨蟹座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E7%8B%AE%E5%AD%90%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>狮子座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%A4%84%E5%A5%B3%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>处女座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%A4%A9%E7%A7%A4%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>天秤座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%A4%A9%E8%9D%8E%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>天蝎座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%B0%84%E6%89%8B%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>射手座</span>
											</a>
										</li>
										<li >
											<a  class="astroa" id="J_moji">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E6%91%A9%E7%BE%AF%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>摩羯座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E6%B0%B4%E7%93%B6%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>水瓶座</span>
											</a>
										</li>
										<li >
											<a  class="astroa">
												<img src="http://7xpfpd.com1.z0.glb.clouddn.com/%E5%8F%8C%E9%B1%BC%E5%BA%A7.png" class="img-circle max-width-50" height="40"
												width="40" ><br>
												<span>双鱼座</span>
											</a>
										</li>
										
										
										
										
										

									</ul>
								</div>
								<div class="clearfix ">
											<hr>
							    </div>
							    <div class="row padding1" id="J_tag_div">
											<a class="btn btn-hero  no-decoration "  style="padding: 6px 6px 6px;" oid="today">今日运势</a>
											<a class="btn   no-decoration "  style="padding: 6px 6px 6px;" oid="tomorrow">明日运势</a>
											<a class="btn   no-decoration "  style="padding: 6px 6px 6px;" oid="week">本周运势</a>
											<a class="btn   no-decoration "  style="padding: 6px 6px 6px;" oid="month">本月运势</a>
											
											<!-- today,tomorrow,week,nextweek,month,year -->
											
							    </div>
							    <input id="J_xzname" type="text" value="摩羯座">
							    <input id="J_type" type="text" value="today">
							    <div class="" id="J_content" >
										
											
							    </div>

									
								</div>
								
							</div>
					</div>
				</div>
			</div>
		</div>

	

    
	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
	<script type="text/javascript">
	//禁止图片拉伸
	$(function(){
		$("img").each(function(){
			$(this).css('max-width',($("#J_white_div").width()));
		})
		$(".astroa").css("font-size","10px");
		$("#J_ul_astro>li").css("margin-left","0px");
		$("#J_ul_astro>li").click(function(){
			$(this).addClass("background","#45d0c6;" );
			var xzname = $(this).find("span").text();
			if(xzname){
				//设值
				$("#J_xzname").val(xzname);
			}
			var xzname = $("#J_xzname").val();
			var type = $("#J_type").val();
			//执行查询
			 $.ajax({
	             url:"/wx/getAstro",
	             type:"post",
	             data:{"xzname":xzname,"type":type},
	             success:function(msg){
	            	 console.log(msg);
	            	 $("#J_content").html(msg);
	             }
	         });
		})
		$("#J_tag_div>a").click(function(){
			$(this).addClass("btn-hero").siblings().removeClass("btn-hero");
			var oid = $(this).attr("oid");
			if(oid){
				//设值
				$("#J_type").val(oid);
			}
			var xzname = $("#J_xzname").val();
			var type = $("#J_type").val();
			//执行查询
			 $.ajax({
	             url:"/wx/getAstro",
	             type:"post",
	             data:{"xzname":xzname,"type":type},
	             success:function(msg){
	            	 console.log(msg);
	            	 $("#J_content").html(msg);
	             }
	         });
		})
		
		
		
		//初始化
		$("#J_moji").addClass("btn-hero").siblings().removeClass("btn-hero");
		$("#J_moji").click();
		
		
	})
	
	</script>

	

</body></html>
