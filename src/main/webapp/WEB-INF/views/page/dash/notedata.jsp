<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="testimonails-slider">

            <div class="flex-viewport"
                 style="overflow: hidden; position: relative;">
                <ul class="slides"
                    style="width: 1000%; transition-duration: 0.6s; transform: translate3d(-1500px, 0px, 0px);">

                    <c:forEach items="${note_list }" var="s" varStatus="ss">


                        <li class="center"

                                <c:if test="${ss.index % 3==0}">
                                    class="clone"
                                </c:if>
                                <c:if test="${ss.index %3 ==1 } ">
                                    class=""
                                </c:if>
                                <c:if test="${ss.index %3 ==2} ">
                                    class="flex-active-slide"
                                </c:if>

                            aria-hidden="true" style="float: left; display: block; width: 750px;">

                            <div class="testimonails-content avatar">
                                <p class="text-color-${s.headSpan }">${s.brief }</p>
                                <p>
                                    <a

                                            <c:if test="${s.tailSlug==null ||s.tailSlug==''}">
                                                href="/cm/channel/${s.userid}"
                                            </c:if>
                                            <c:if test="${s.tailSlug!=null }">
                                                href="/people/${s.tailSlug}"
                                            </c:if>

                                            title="${s.get('username')}">


                                        <c:if test="${s.get('headPath') ==null||s.get('headPath') ==''||s.get('headPath').length()==0}">
                                            <span class=" imgbreath ${s.headSpanClass }"
                                                  alt="${s.get('username')}">${s.headSpan }</span>
                                        </c:if>
                                        <c:if test="${s.get('headPath') !=null && s.get('headPath').length()>0}">
                                            <img class="imgbreath" style="text-align: center;display: inline-block;"
                                            <c:choose>
                                                 <c:when test="${fn:contains(s.headPath ,'http://') }">src="${s.headPath }"</c:when>
                                                 <c:otherwise>src="/bruce/${s.headPath }"</c:otherwise>
                                            </c:choose>

                                                 alt="${s.username}">
                                        </c:if>
                                    </a>
                                </p>
                                <h6 class="gray-text">
                                        ${s.username }
                                </h6>
                                <h6 class="gray-text">
                                        ${s.create_time }
                                </h6>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </div>
            <ul class="flex-direction-nav">
                <li><a class="flex-prev" href="#"></a></li>
                <li><a class="flex-next" href="#"></a></li>
            </ul>
        </div>
    </div>
</div>

