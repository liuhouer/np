<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 代码开始 -->
<div class="moviebox">

    <div class="picbox">

        <ul class="piclist mainlist">
            <c:forEach items="${movieslist }" var="s" varStatus="ss">
                <li><a class="no-decoration" href="/movies/post-${s.id }.html" target="_blank"><img
                        src="/img/index/movie${ss.index%11 +1}.png"/>${s.moviename }</a></li>
            </c:forEach>

        </ul>

        <ul class="piclist swaplist"></ul>

    </div>

    <div class="og_prev"></div>

    <div class="og_next"></div>

</div>
<!-- 代码结束 -->