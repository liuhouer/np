<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				<div class="row">
				
					<c:forEach items="${eqlist }" var="s" varStatus="ss">
					<div class="col-sm-4  col-xs-6" >
					<div class="clearfix bg-white margin-t10 margin-b10 padding20" >
								<div class="row">
									<div class="col-sm-5">
										<div class="thumbnail border-1 center">
											<a title="${s.title}">
												<img src="${s.img }" >
											</a>
											
										</div>
									</div>
									
									<div class="col-sm-7">
										<p><small class="label label-gray">${s.date }</small> &nbsp; <br><br>
										
										
										<a style="text-decoration: none;" title="${s.title}">${s.title}</a> </p>
										<p id="brief_${ss.index}">
										
										${s.brief }
										<%-- <c:if test="${s.brief!=s.article }">
											<button class="clearfix btn btn-gray btn-xs click2show"  data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</c:if> --%>
										</p>
										<p><a class="readmore  no-decoration" target="_blank" href="/romeo/${s.id }.html">READ MORE</a></p>
											<%-- 	<div class="clearfix hidden" id="text_${ss.index}" >
													${s.article }
												</div> --%>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 
						 
					
		  	</div>

