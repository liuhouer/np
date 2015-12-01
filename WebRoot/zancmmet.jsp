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
<link rel="shortcut icon" href="img/favicon.png">
<title>布.词故事::</title>
<meta name="description" content="布.词故事::第1页::布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/page/common/common.jsp"%>
<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=gfedgF9ayBPjTunPY2MpdH&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">




	<%@ include file="/page/common/navigation.jsp"%>
	 
	
 <div  class="clearfix maincontent">
	    <div  class="container">
	    	
<div  class="mainbody padding10"  style="margin-top:5em;">

		<div  class="row">
		<div  class="col-md-8">
		
			<h2  class="margin0">${ lrc.title} &nbsp; 
				<small><span  class="glyphicon glyphicon-user"></span> 由<a  href="/cm/detail/${by.id }"  title="花菜菜的最爱">${by.username }</a>创建</small>
			</h2>
			<hr>
		
			<div  class="row">
				<div  class="col-sm-7 ">

					<div  class="clearfix"  style="position:relative">
						<div  class="clearfix"  id="mainThumb"><img  class="img-responsive img-full"  src="/bruce/${lrc.albumImg }"  alt="${ lrc.title}"></div>

											</div>

					
					
				</div>
				<div  class="col-sm-5">

					<div  class="clearfix">
						<h4><span  class="glyphicon glyphicon-heart"></span> ${zanNum }人最爱</h4>
						<p  style="line-height:200%;">
						  <c:forEach var="x" items="${zanList }">
						  	<span><a  href="/cm/detail/${x.id }"  title="${x.username }">${x.username }</a> &nbsp;</span>
						  </c:forEach>
						  <button  class="btn btn-gray btn-xs click2show"  data-target=".lovers_box">查看更多 ›› </button>
						</p>
					</div>

				    <h2> <a  class="btn btn-warning btn-xlg" id="J_gz_btn"><span  class="glyphicon glyphicon-heart"></span>
				    <c:if test="${gz eq 'ygz' }">已关注</c:if><c:if test="${gz ne 'ygz' }">关注此人 </c:if>
				    
				    
				    </a></h2>
					<input type="hidden" id="by_id" value="${by.id }"/>
					
					
					
					
				</div>
			</div>
			<a  name="comments"></a>
			<div  class="clearfix margin-t20">

				<h4><span  class="glyphicon glyphicon-comment"></span> ${plNum }条回忆</h4>
				<hr>
                        <c:if test="${user!=null }">
						   <div  class="row">
						   	<div  class="col-xs-3 col-sm-2">
						   		<a  href="/cm/detail/${user.id }"  title="${user.username }的最爱"><img  <c:if test="${user.headpath == null}">src="/img/davatar.jpg"</c:if><c:if test="${user.headpath != null}">
						   		 		<c:choose>
                                            <c:when test="${fn:contains(user.headpath  ,'http://') }">src="${user.headpath  }"</c:when>
                                            <c:otherwise>src="bruce/${user.headpath  }"</c:otherwise>
                                         </c:choose>   
						   		
						   		</c:if> class="img-responsive  img-circle max-width-60"  alt="${user.username }的最爱"></a>							</div>
						   	<div  class="col-xs-9 col-sm-10">
					                 	<div  class="form-group">
					                 		<textarea  class="form-control" id="J_comment" name="comment"  rows="3"></textarea>
					                 	</div>
					                    <div  class="form-group text-right">
					                       <input  class="btn btn-info btn-md"  type="button" id="J_commentBtn"  value="+ 发布">
					                    </div>
                           
					                 <hr>
						   	</div>
						   </div>
				      </c:if>


				<div  class="clearfix"  id="stuffCommentBox">
				     <c:forEach var="y" items="${plList }">
								<div class="row" id="commentbox_${y.userid }">
									<div class="col-xs-3 col-sm-2">
										<a href="/cm/detail/${y.userid }" title="${y.username }的最爱"><img
											<c:if test="${y.headpath == null}">src="/img/davatar.jpg"</c:if><c:if test="${y.headpath != null}">
											 <c:choose>
   												<c:when test="${fn:contains(y.headpath,'http://') }">src="${y.headpath }"</c:when>
                                  				<c:otherwise>src="bruce/${y.headpath}"</c:otherwise>
                                			</c:choose>   
											
											
											</c:if>
											class="img-responsive  img-circle max-width-60"
											alt="${y.username }的最爱"></a>
									</div>
									<div class="col-xs-9 col-sm-10">
										<p>
											<a href="/cm/detail/${y.userid }" title="${y.username }的最爱">${y.username }</a>：${y.comment }
										</p>
										<p>
											<small class="label label-gray">${y.create_time }</small>
										</p>
										<hr>
									</div>
								</div>
					</c:forEach>
				</div>
					
					<div  class="row center">
						<!-- <div  class="row"  id="loadingAnimation"  style="display: none;">
							<img  src="./reg_files/ajax_loading.gif">						</div> -->

						<button  class="btn btn-default btn-lg"  id="loadStuffCommentBtn"  data-htmlboxid="stuffCommentBox"  rel="938"  style="display: none;">加载更多回忆 <span  class="glyphicon glyphicon-chevron-down"></span></button>
						<input  type="hidden"  id="comment_id_from"  value="-1">
						<input  type="hidden"  id="comment_perpage"  value="30">
						<input  type="hidden"  id="auto_loading"  value="no">
					</div>
								
				
			</div>

		</div>

		<div  class="col-md-4">
			<div  class="clearfix sidebar radius-5">
				<div  class="clearfix border-bottom">
					<h4><span  class=" glyphicon glyphicon-th-large"></span> 随便看看</h4>
				</div>
				<c:forEach var="z" items="${loveList }">
				        <div  class="row padding10">
				        	<div  class="col-xs-2"><img  class="img-responsive img-circle max-width-30"  <c:if test="${z.headpath == null}">src="/img/davatar.jpg"</c:if><c:if test="${z.headpath != null}">
				        	    <c:choose>
   									<c:when test="${fn:contains(z.headpath ,'http://') }">src="${z.headpath }"</c:when>
                                    <c:otherwise>src="bruce/${z.headpath }"</c:otherwise>
                                </c:choose>   

				        	
				        	
				        	</c:if>></div>
				        	<div  class="col-xs-10"  style="line-height:30px;">
				        		<a  href="/cm/detail/${z.userid }"  title="${z.username }的最爱">${z.username }</a>爱上了<a  title="${z.title }"  href="lyrics/comment/${z.lyricsid }">${z.title }</a>
				        	</div>
				        </div>
				</c:forEach>

				<p  class="white-line margin0"></p>
			 </div>
			
		</div>
		 
	</div>
 
	
</div>

 


	    </div>
	</div>

	 

<%@ include file="/page/common/container.jsp"%>	

	<style  type="text/css">
	.btn-white
	{
	  color:#333;
	  text-shadow:none;
	  background-color:#fff;
	  border:none
	}

	.btn-white:hover,.btn-white:focus
	{
	  color:#666;
	  text-shadow:none;
	  background-color:#fff
	}
	</style>



<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400553738';
	_aj['user_keychain']='CBBDECB98732';

	
	
	 $("#J_commentBtn").click(function(){
         var comment = $("#J_comment").val();
      	var lyricsid = '${lrc.id}';
      	var userid = '${user.id}' ;
      	 if(comment){
      		$.ajax({
      			url:"/zanAction/addComment",
      			type:"post",
      			data:{"lyricsid":lyricsid,"userid":userid,"comment":comment},
      			success:function(msg){
      				if(msg=="success"){
      					art.dialog.tips('评论成功');
      					window.location.href = window.location.href;
      				}			
      			}
      		});
      	} 
	
	 });
	});
	
$("#J_gz_btn").click(function(){
 	    //把userid的判断转为后台判断
 	    var url = window.location.href;
 	    $.ajax({
			url:"/cm/loginFlag",
			type:"post",
			data:{"url":url},
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
					
					window.location.href = "/cm/loginTT?url="+url; 
				}			
				
			}
		});
 	
 	
 	

});
	
	

	
	
$('.row-thumbnails').on('click', '.stuffThumb', function(event) {
    $('#mainThumb').html($(this).html());
  });



	$(".loveyearBtn")
			.click(
					function() {
						var now_year = $(this).attr('title');
						var love_id = $(this).attr('rel');
						var select_html = "从<select id='newloveyear'>";
						for (var i = 2014; i > 1950; i--) {
							if (i == now_year)
								select_html += "<option value='"+i+"' selected>"
										+ i + "年</option>";
							else
								select_html += "<option value='"+i+"'>" + i
										+ "年</option>";
						}
						select_html += "</select>开始爱上 <button class='btn btn-sm btn-white' id='changeLoveYearBtn' rel='"+love_id+"'>修改</button>";
						$("#loveryearBox").html(select_html);
					});

	$('body').on('click', '#changeLoveYearBtn', function(event) {
		$(this).html('处理中...');
		_aj['new_year'] = $('#newloveyear').val();
		_aj['love_id'] = $(this).attr('rel');
		$.get('/do/changeloveryear', _aj, ajaxDone);
	});

	function loadStuffCommentDone(returndata) {
		$('#loadingAnimation').hide();
		$("#auto_loading").val('no');

		data = jQuery.parseJSON(returndata);
		if (data.response == 'YES') {
			$('#comment_id_from').val(data.last_id);
			if (data.last_id != '-1') {
				$('#loadStuffCommentBtn').show();
				$('#stuffCommentBox').append(data.html);
			}

		}

		ajaxDone(returndata);
	}

	$("#loadStuffCommentBtn").click(function() {
		$(this).hide();
		$('#loadingAnimation').show();
		$("#auto_loading").val('yes');

		_aj['comment_id_from'] = $('#comment_id_from').val();
		_aj['comment_perpage'] = $('#comment_perpage').val();
		_aj['stuff_id'] = $(this).attr('rel');
		$.get('/do/loadstuffcomment', _aj, loadStuffCommentDone);
	});

	$("#loadStuffCommentBtn").click();

	$(window).scroll(
			function() {
				if ((($(window).scrollTop() + $(window).height()) + 150) >= $(
						document).height()) {
					if ($("#auto_loading").val() == 'no'
							&& $('#comment_id_from').val() > 0) {
						$("#auto_loading").val('yes');
						$("#loadStuffCommentBtn").click();
					}
				}

			});
</script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-199262-13', 'buci.cc');
  ga('send', 'pageview');

</script>

 


<script type="text/javascript" charset="utf-8" id="ABD75F83F0359849_Analytics" src="http://tajs.qq.com/stats?sId=26628622"></script>
<script  id="hiddenlpsubmitdiv"  style="display: none;"></script><script>try{for(var lastpass_iter=0; lastpass_iter < document.forms.length; lastpass_iter++){ var lastpass_f = document.forms[lastpass_iter]; if(typeof(lastpass_f.lpsubmitorig2)=="undefined"){ lastpass_f.lpsubmitorig2 = lastpass_f.submit; lastpass_f.submit = function(){ var form=this; var customEvent = document.createEvent("Event"); customEvent.initEvent("lpCustomEvent", true, true); var d = document.getElementById("hiddenlpsubmitdiv"); if (d) {for(var i = 0; i < document.forms.length; i++){ if(document.forms[i]==form){ if (typeof(d.innerText) != 'undefined') { d.innerText=i; } else { d.textContent=i; } } } d.dispatchEvent(customEvent); }form.lpsubmitorig2(); } } }}catch(e){}</script>
</body></html>