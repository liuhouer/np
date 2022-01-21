<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 页面标题 -->
<h1 class="font-elegant center">${MyInfo.username}</h1>
<div class="clearfix mainhead grayback">
    <div class="container">
        <div class="row margin-b20 margin-t20">
            <div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
                <div class="row margin-b20 margin-t20">
                    <div class="col-xs-4 center">
                        <div class="thumbnail bg-no margin-t5 border-0">
                            <div class="avatar centre">
                                <c:if test="${MyInfo.head_path==null }">
                                    <span class=" ${MyInfo.head_span_class }"
                                          alt="${s.get('username')}">${MyInfo.head_span }</span>

                                </c:if>
                                <c:if test="${MyInfo.head_path!=null }">
                                    <img alt="${MyInfo.username }" class="img-circle img-responsive "
                                    <c:choose>
                                         <c:when test="${fn:contains(MyInfo.head_path ,'http://') }">src="${MyInfo.head_path}"</c:when>
                                         <c:otherwise>src="/bruce/${MyInfo.head_path }"</c:otherwise>
                                    </c:choose>

                                    >
                                </c:if>
                                <br>
                                <!-- <a href="javascript:void(0);" onclick="toEditInfo()" class="btn btn-gray btn-sm"><span class="glyphicon glyphicon-pencil"></span> 编辑档案</a> -->
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-8">
                        <h1 class="margin0">${MyInfo.username } </h1>
                        <h4 class="margin0">
                            <small>http://NorthPark.cn/people/${MyInfo.tail_slug }</small>
                        </h4>
                        <p class="white-line"></p>


                        <c:if test="${MyInfo.meta!=null }">
                            <h3 class="margin0">
                                <small>${MyInfo.meta }</small>
                            </h3>
                        </c:if>
                        <c:if test="${MyInfo.blog_site!=null }">
                            <h3 class="margin0">
                                <small>${MyInfo.blog_site }</small>
                            </h3>
                        </c:if>
                        <h3 class="margin0">
                            <small>加入时间：${MyInfo.date_joined }</small>
                        </h3>

                        <h2><a class="btn btn-warning btn-xlg" id="J_gz_btn"><span
                                class="glyphicon glyphicon-heart"></span>
                            <c:if test="${gz eq 'ygz' }">已关注</c:if>
                            <c:if test="${gz ne 'ygz' }">关注此人 </c:if>
                        </a></h2>
                        <input type="hidden" id="by_id" value="${MyInfo.id }"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>