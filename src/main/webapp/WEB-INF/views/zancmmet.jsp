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
<link rel="shortcut icon" href="img/favicon.ico">
<title>布.词</title>
<meta name="description" content="布.词会让您记住每一件美好的事物，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<script src="/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<link href="/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet" />
<script src="/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>

</head>

<body style="">




	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
 <div  class="clearfix maincontent">
	    <div  class="container">
	    	
<div  class="mainbody padding10"  style="margin-top:5em;">

		<div  class="row">
		<div  class="col-md-8">
		
			<h2  class="margin0">${ lrc.title} &nbsp; 
				<small><span  class="glyphicon glyphicon-user"></span> 由<a  
				
				<c:if test="${by.tail_slug==null || by.tail_slug==''}">
			    href="/cm/detail/${by.id}" 
			    </c:if>
			    <c:if test="${by.tail_slug!=null }">
			    href="/people/${by.tail_slug }" 
			    </c:if>
				
				title="花菜菜的最爱">${by.username }</a>创建</small>
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
						<h7><label class="control-label iteminfo ">${lrc.updatedate }</label> </h7>
						<h4><span  class="glyphicon glyphicon-heart"></span> ${zanNum }人最爱</h4>
						<p  class="pline">
						 
						  <div id="J_zan_div" class="pline">
							  <c:forEach var="x" varStatus="xx" items="${zanList }">
							    	<span><a  
									  	<c:if test="${x.tail_slug==null || x.tail_slug==''}">
									    href="/cm/detail/${x.id}" 
									    </c:if>
									    <c:if test="${x.tail_slug!=null }">
									    href="/people/${x.tail_slug }" 
									    </c:if>
							  	
							  	 title="${x.username }">${x.username }</a> &nbsp;</span>
							  	
							  </c:forEach>
						  </div>
						  <!-- >10个喜欢才有查看更多按钮 -->
						  <c:if test="${zanNum > 10}">
						  	<button id="J_lovers_box" class="btn btn-gray btn-xs click2show"  data-target=".lovers_box">查看更多 ›› </button>
						  </c:if>
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
						   		<a  
							   		<c:if test="${user.tail_slug==null || user.tail_slug==''}">
									    href="/cm/detail/${user.id}" 
								    </c:if>
								    <c:if test="${user.tail_slug!=null }">
								    	href="/people/${user.tail_slug }" 
								    </c:if>
						   		
						   		 title="${user.username }的最爱"><img  <c:if test="${user.headpath == null}">src="/img/davatar.jpg"</c:if><c:if test="${user.headpath != null}">
						   		 		<c:choose>
                                            <c:when test="${fn:contains(user.headpath  ,'http://') }">src="${user.headpath  }"</c:when>
                                            <c:otherwise>src="/bruce/${user.headpath  }"</c:otherwise>
                                         </c:choose>   
						   		
						   		</c:if> class="img-responsive  img-circle max-width-60"  alt="${user.username }的最爱"></a>							</div>
						   	<div  class="col-xs-9 col-sm-10">
					                 	<div  class="form-group">
					                 		<textarea  class="form-control"  style="height:200px; max-height:400px;"    id="J_comment" name="comment"  rows="3"></textarea>
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
										<a 
										
										
									<c:if test="${y.tail_slug==null || y.tail_slug==''}">
									    href="/cm/detail/${y.id}" 
								    </c:if>
								    <c:if test="${y.tail_slug!=null }">
								    	href="/people/${y.tail_slug }" 
								    </c:if>
										
										
										
										title="${y.username }的最爱"><img
											<c:if test="${y.headpath == null}">src="/img/davatar.jpg"</c:if><c:if test="${y.headpath != null}">
											 <c:choose>
   												<c:when test="${fn:contains(y.headpath,'http://') }">src="${y.headpath }"</c:when>
                                  				<c:otherwise>src="/bruce/${y.headpath}"</c:otherwise>
                                			</c:choose>   
											
											
											</c:if>
											class="img-responsive  img-circle max-width-60"
											alt="${y.username }的最爱"></a>
									</div>
									<div class="col-xs-9 col-sm-10">
										<p>
											<a 
											
											
											<c:if test="${y.tail_slug==null || y.tail_slug==''}">
											    href="/cm/detail/${y.id}" 
										    </c:if>
										    <c:if test="${y.tail_slug!=null }">
										    	href="/people/${y.tail_slug }" 
										    </c:if>
											
											
											title="${y.username }的最爱">${y.username }</a>：${y.comment }
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
                                    <c:otherwise>src="/bruce/${z.headpath }"</c:otherwise>
                                </c:choose>   

				        	
				        	
				        	</c:if>></div>
				        	<div  class="col-xs-10"  style="line-height:30px;">
				        		<a  
				        		
				        		
				        					<c:if test="${z.tail_slug==null || z.tail_slug==''}">
											    href="/cm/detail/${z.userid}" 
										    </c:if>
										    <c:if test="${z.tail_slug!=null }">
										    	href="/people/${z.tail_slug }" 
										    </c:if>
				        		
				        		
				        		 title="${z.username }的最爱">${z.username }</a>爱上了<a  title="${z.title }"  href="lyrics/comment/${z.lyricsid }.html">${z.title }</a>
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

	 

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>	

<script src="/js/page/zancmt.js"></script>



</body></html>