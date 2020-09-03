<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="row">
    <c:forEach items="${list }" var="s" varStatus="ss">
        <div class="col-sm-6 ">
            <div class="clearfix bg-white margin-t10 margin-b10 padding20">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="thumbnail border-0 center ">
                            <div class="avatar">
                                <a

                                        <c:if test="${s.tail_slug==null ||s.tail_slug==''}">
                                            href="/cm/detail/${s.userid}"
                                        </c:if>
                                        <c:if test="${s.tail_slug!=null }">
                                            href="/people/${s.tail_slug}"
                                        </c:if>

                                        title="${s.get('username')}:我的故事">
                                    <c:if test="${s.get('headpath') ==null||s.get('headpath') ==''||s.get('headpath').length()==0}">
                                        <span class=" imgbreath ${s.headspanclass }"
                                              alt="${s.get('username')}">${s.headspan }</span>
                                    </c:if>
                                    <c:if test="${s.get('headpath') !=null && s.get('headpath').length()>0}">
                                        <img class="imgbreath"
                                        <c:choose>
                                             <c:when test="${fn:contains(s.headpath ,'http://') }">src="${s.headpath }"</c:when>
                                             <c:otherwise>src="/bruce/${s.headpath }"</c:otherwise>
                                        </c:choose>

                                             alt="${s.username}的故事">
                                    </c:if>
                                </a>
                                <p>
                                    <small class="gray-text">${s.username}</small>
                                </p>
                                <div class="clearfix visible-xs">
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-9">
                        <p>
                            <small class="label label-gray">${s.createtime }</small> &nbsp; <a

                                <c:if test="${s.tail_slug==null ||s.tail_slug==''}">
                                    href="/cm/detail/${s.userid}"
                                </c:if>
                                <c:if test="${s.tail_slug!=null }">
                                    href="/people/${s.tail_slug}"
                                </c:if>


                                title="${s.username}的最爱">${s.username}</a> 写到：
                        </p>
                        <div id="brief_${ss.index}" class="note-brief">

                            <c:if test="${!fn:startsWith(s.brief, '<')}">
                            <p></p>
                            <p>
                                </c:if>
                                    ${s.brief }

                                <c:if test="${!fn:endsWith(s.brief, '>')}">
                            </p>
                            </c:if>


                        </div>
                        <div class="clearfix hidden" id="text_${ss.index}">
                                ${s.note }
                        </div>

                        <c:if test="${s.brief!=s.note }">
                            <button class="clearfix btn btn-gray btn-xs click2show margin-b5 padding-b20"
                                    data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span
                                    class="glyphicon glyphicon-chevron-down"></span> &nbsp;
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

