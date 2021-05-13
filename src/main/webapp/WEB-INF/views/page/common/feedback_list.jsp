<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${feedback_list!=null}">

	<h4 class="center"><span class=" glyphicon  glyphicon-exclamation-sign margin5"></span>已失效资源</h4>

</c:if>


<div class="clearfix sidebar " >

	<c:forEach var="y" items="${feedback_list}" varStatus="ss" >

		<div class="col-md-12 margin-t10">
			<div class="col-xs-2 avatar">

				<span class="text-${ Math.round(Math.random()*(9+1-1)+1)}">${ fn:toLowerCase(fn:substring( y.title ,0,1)) }</span>

			</div>
			<div class="col-xs-10">

				<a style="font-size: 14px; line-height: 32px; color: #888"
					href="${y.href}" title="${y.title}">${y.title} </a>

			</div>


		</div>
	</c:forEach>


</div>
