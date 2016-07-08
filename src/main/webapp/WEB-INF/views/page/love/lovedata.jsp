<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				
					   	<div class="row">
							 <c:if test="${!empty list }">
							   <c:forEach items="${list }" var="s" varStatus="ss">
				   				<div class="col-xs-6 col-sm-3 margin-b20 ">
				   				
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
														<p class="project-card-small-title"></p>
														<p class="project-card-small-organization">— ${s.artist }</p>
													</div>
												</div>
												<!-- <div class="card-content project-card-small-content">
													<p></p>
												</div>
												<div class="card-footer">
													<div class="card-footer-button-flat">LEARN MORE</div>
												</div> -->
											</a>
											
											
											
											
											<div class="row margin-t0 iteminfo">
											<%-- <div class="col-xs-7 text-left">
												<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">${s.title }...</a>
											</div> --%>
											<div class="col-xs-6 text-left">
											    <c:if test="${s.yizan eq 'yizan' }"><span class="glyphicon glyphicon-heart"></span></c:if>
											    <c:if test="${s.yizan ne 'yizan' }"><span class="glyphicon glyphicon-heart-empty" style="cursor: pointer;" <c:if test="${user.id!=null }">onclick="zan('${s.id}','${user.id }')"</c:if> ></span></c:if>
												 ${s.zan } 
												<span class="hidden-sm hidden-xs"> &nbsp; 
												<span class="glyphicon glyphicon-comment" style="cursor: pointer;" onclick="location.href='/lyrics/comment/${s.id }.html'"></span>  ${s.pl } </span>
											</div>
										</div>
										</div>
										
										
										<%-- <div class="thumbnail radius-0 border-0 margin-b0"  >
											<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">
											<img alt=""  class="imgbreath"  style="width: 289px;height: 217px;"
												<c:choose>
												  <c:when test="${fn:contains(s.albumImg ,'http://') }">src="${s.albumImg }"</c:when>
												  <c:otherwise>src="/bruce/${s.albumImg }"</c:otherwise>
												</c:choose> 
											
											 alt="${s.title }"></a>
												
										</div>
										
									
										<div class="row margin-t0 iteminfo">
											<div class="col-xs-7 text-left">
												<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">${s.title }...</a>
											</div>
											<div class="col-xs-5 text-right">
											    <c:if test="${s.yizan eq 'yizan' }"><span class="glyphicon glyphicon-heart"></span></c:if>
											    <c:if test="${s.yizan ne 'yizan' }"><span class="glyphicon glyphicon-heart-empty" style="cursor: pointer;" <c:if test="${user.id!=null }">onclick="zan('${s.id}','${user.id }')"</c:if> ></span></c:if>
												 ${s.zan } 
												<span class="hidden-sm hidden-xs"> &nbsp; 
												<span class="glyphicon glyphicon-comment" style="cursor: pointer;" onclick="location.href='/lyrics/comment/${s.id }.html'"></span>  ${s.pl } </span>
											</div>
										</div> --%>
								</div>
						   </c:forEach>
						 </c:if>
				   	 </div>
	
	
					
					
		   


