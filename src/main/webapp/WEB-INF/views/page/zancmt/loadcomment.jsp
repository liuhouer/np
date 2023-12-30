<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="y" items="${plList }">
    <div class="row" id="commentbox_${y.userid }">
        <div class="col-xs-3 col-sm-2 avatar">
            <a

                    <c:if test="${y.tailSlug==null || y.tailSlug==''}">
                        href="/cm/channel/${y.id}"
                    </c:if>
                    <c:if test="${y.tailSlug!=null }">
                        href="/people/${y.tailSlug }"
                    </c:if>

                    title="${y.username }的最爱">
                <c:if test="${y.headPath == null}">
                    <span class="${y.head_span_class }">${y.head_span }</span>
                </c:if>
                <c:if test="${y.headPath != null}">
                    <img
                    <c:choose>
                            <c:when test="${fn:contains(y.headPath,'http://') }">src="${y.headPath }"</c:when>
                            <c:otherwise>src="/bruce/${y.headPath}"</c:otherwise>
                    </c:choose>
                            alt="${y.username }的最爱">
                </c:if>
            </a>
        </div>
        <div class="col-xs-9 col-sm-10">
            <p>
                <a


                        <c:if test="${y.tailSlug==null || y.tailSlug==''}">
                            href="/cm/channel/${y.id}"
                        </c:if>
                        <c:if test="${y.tailSlug!=null }">
                            href="/people/${y.tailSlug }"
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

<script>

    var tail = "${tail}";
    $("#J_tail").val(tail);

</script>				
					
