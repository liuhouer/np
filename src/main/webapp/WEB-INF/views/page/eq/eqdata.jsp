<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-4 " >
					<div class="clearfix bg-white margin-t10 margin-b10 padding20" >
								<div class="row">
									<div class="col-sm-4">
										<div class="thumbnail border-0 center">
											<a title="${s.title}">
											<c:if test="${s.img==null ||s.img==''}">
												<img src="/img/davatar.jpg" class="imgbreath">
											</c:if>
											<c:if test="${s.img!=null }">
												<img src="${s.img }" class="imgbreath">
											</c:if>
											</a>
											<p><label class="bold-text">${s.title}</label></p>
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-8">
										<p><small class="label label-gray">${s.date }</small> &nbsp; <br><br>
										
										
										<a href="/romeo/${s.id }.html" class="no-decoration" title="${s.title}">${s.title}</a> ：</p>
										<p id="brief_${ss.index}">
										
										${s.brief }
										<c:if test="${s.brief!=s.article }">
											<button class="clearfix btn btn-gray btn-xs click2show"  data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</c:if>
										</p>
												<div class="clearfix hidden" id="text_${ss.index}" >
													${s.article }
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 
						 
					
		  	</div>

