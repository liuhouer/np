<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="clearfix mainhead " style="background:#f4f3f1;">
		<div class="container">
			<div class="row margin-b20 margin-t20">
				<div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
					<div class="row margin-b20 margin-t20">
						<div class="col-xs-4 center">
							<div class="thumbnail bg-no margin-t5 border-0">
								<img alt="${MyInfo.username }的最爱" class="img-circle img-responsive "
								<c:if test="${MyInfo.headpath==null }">src="/img/davatar.jpg"</c:if>
								<c:if test="${MyInfo.headpath!=null }">
								
								<c:choose>
                                  <c:when test="${fn:contains(MyInfo.headpath ,'http://') }">src="${MyInfo.headpath}"</c:when>
                                  <c:otherwise>src="bruce/${MyInfo.headpath }"</c:otherwise>
                                </c:choose> 
								</c:if>
								>
								<br>
								<a href="javascript:void(0);" onclick="toEditInfo()" class="btn btn-gray btn-sm"><span class="glyphicon glyphicon-pencil"></span> 编辑档案</a>
							</div>
						</div>
						<div class="col-xs-8">
							<h1 class="margin0">${MyInfo.username } </h1>
							<h4 class="margin0"><small>http://buci.cc/${MyInfo.username }</small></h4>
							<p class="white-line"></p>

							
							
							<h3 class="margin0"><small>加入时间：${MyInfo.date_joined }</small></h3>
						 
						 	<h2><a href="lyrics/add" class="btn btn-hero btn-lg">+ 添加我的最爱</a></h2>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>