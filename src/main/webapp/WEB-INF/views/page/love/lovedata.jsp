<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				
				 <c:if test="${!empty list }">
				   <c:forEach items="${list }" var="s" varStatus="ss">
					    <c:if test="${(ss.index+1) % 4==0}"> 
					   		  <div class="row">
					    </c:if>  
				   				<div class="col-xs-6 col-sm-3 margin-b20 ">
										<div class="thumbnail radius-0 border-0 margin-b0" >
											<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">
											<img alt=""  
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
										</div>
								</div>
				   	  <c:if test="${(ss.index+1) % 4==0}">
				   		  </div>
				   	  </c:if> 
				   </c:forEach>
				 </c:if>
	
	
					
					
		   


