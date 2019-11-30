<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 页面标题 -->
<h1 class="font-elegant center">${user.username}</h1>
<div class="clearfix mainhead grayback">
    <div class="container">
        <div class="row margin-b20 margin-t20">
            <div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
                <div class="row margin-b20 margin-t20">
                    <div class="col-xs-4 center">
                        <div class="thumbnail bg-no margin-t5 border-0">
                            <div class="avatar centre">
                                <c:if test="${MyInfo.headpath==null }">
                                    <span class=" ${MyInfo.headspanclass }"
                                          alt="${s.get('username')}">${MyInfo.headspan }</span>

                                </c:if>
                                <c:if test="${MyInfo.headpath!=null }">
                                    <img alt="${MyInfo.username }" class="img-circle img-responsive "

                                    <c:choose>
                                         <c:when test="${fn:contains(MyInfo.headpath ,'http://') }">src="${MyInfo.headpath}"</c:when>
                                         <c:otherwise>src="/bruce/${MyInfo.headpath }"</c:otherwise>
                                    </c:choose>
                                    >
                                </c:if>
                                <br>
                            </div>
                            <a href="/me/settings" class="btn btn-gray btn-sm margin-t20"><span
                                    class="glyphicon glyphicon-pencil"></span> 编辑档案</a>
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
                        <c:if test="${MyInfo.blogsite!=null }">
                            <h3 class="margin0">
                                <small>${MyInfo.blogsite }</small>
                            </h3>
                        </c:if>
                        <h3 class="margin0">
                            <small>加入时间：${MyInfo.date_joined }</small>
                        </h3>

                        <h2><a href="/note/findAll" class="btn btn-hero btn-lg">+ 添加碎碎念</a></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>