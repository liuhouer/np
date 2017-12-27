<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<!-- 代码开始 -->
		<div class="wc1200 row rowE">
			<div class="bd mt20" style="margin: 10px;">
				<div id="sca1" class="warp-pic-list">
					<div id="wrapBox1" class="wrapBox">
						<ul id="count1" class="count clearfix">
						
						<%-- /img/movies/qh${ss.index%3 +1}.png --%>
						 	<c:forEach items="${movieslist }" var="s" varStatus="ss">
								
							<li>
								<a href="/movies/search?id=${s.id }" class="img_wrap" target="_blank">
								
									<img alt="${s.moviename }" 
									
									<c:if test="${s.img !=''}">
									src="${s.img }" 
									</c:if>
									<c:if test="${s.img == ''}">
									src="/img/movies/qh${ss.index%3 +1}.png" 
									</c:if>
									border="0"></a>
								<span class="qh_title">${s.moviename }</span>
							</li>
						</c:forEach>

						</ul>

					</div>
					<a id="right1" class="prev icon btn"></a>
					<a id="left1" class="next icon btn"></a>
				</div>
			</div>
		</div>
		<!-- 代码结束 -->

		
		
		