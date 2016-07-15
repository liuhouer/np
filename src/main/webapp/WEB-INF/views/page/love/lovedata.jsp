<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				
					   	<div class="row">
							 <c:if test="${!empty list }">
							   <c:forEach items="${list }" var="s" varStatus="ss">
				   				<div class="col-xs-12 col-sm-4 margin-b20 ">
				   				
										<div
											class="card project-card project-card-small project-card-clickable">
											<a href="/lyrics/comment/${s.id }.html" data-g-label="/lyrics/comment/${s.id }.html"
												class="project-card-link"><div
													class="stage project-card-small-stage">
													<div class="stage-image imgbreath"
														<c:choose>
														  <c:when test="${fn:contains(s.albumImg ,'http://') }">
														  style="background-image: url(${s.albumImg })"
														  </c:when>
														  <c:otherwise>
														  style="background-image: url(http://northpark.cn/bruce/${s.albumImg })"
														  </c:otherwise>
														</c:choose> 
																
														
														alt="${s.title }">
														
														
													</div>
													<div class="stage-content-bottom">
														<p class="project-card-small-title">${s.album }</p>
														<p class="project-card-small-organization">— ${s.artist }</p>
													</div>
												</div>
												
											</a>	
												<div class="card-content project-card-small-content">
													<p>
													<c:if test="${s.yizan eq 'yizan' }"><span class="glyphicon glyphicon-heart"></span>${s.zan }</c:if>
											    	<c:if test="${s.yizan ne 'yizan' }">
											    	<span class="glyphicon glyphicon-heart-empty" style="cursor: pointer;" 
											    	<c:if test="${user.id!=null }">onclick="zan('${s.id}','${user.id }')"</c:if> ></span>${s.zan }</c:if>
											    	
											    	
												    
												<span class="hidden-sm hidden-xs"> &nbsp; 
												<span class="glyphicon glyphicon-comment" style="cursor: pointer;" onclick="location.href='/lyrics/comment/${s.id }.html'"></span>  ${s.pl } </span>
												</p>
												</div>
												<!-- <div class="card-footer">
													<div class="card-footer-button-flat">LEARN MORE</div>
												</div> -->
											
											
											
											
											
											
										</div>
										
								</div>
						   </c:forEach>
						 </c:if>
				   	 </div>
	
	
					
					
		   


