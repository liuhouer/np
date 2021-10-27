<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="navbar navbar-default navbar-fixed-top mainhead-navbox" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle mainhead-navbtn" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">菜单导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">
                <a

                        <c:if test="${user!=null }"> href="/cm/pcentral" title="个人中心" </c:if>

                        <c:if test="${user==null }">href="/login" title="登陆" </c:if>

                        class="mainhead-avatar avatar centre " height="40" width="40">

                    <c:if test="${user==null  }">
                        <img src="https://northpark.cn/statics/img/davatar.jpg" alt="davatar"
                             class="img-circle max-width-50" height="40" width="40">
                    </c:if>

                    <c:if test="${user!=null }">
                        <c:if test="${user.headpath==null }">
                            <span class="max-width-50  ${user.headspanclass }" alt="${user.username}" height="40"
                                  width="40">${user.headspan }</span>
                        </c:if>
                        <c:if test="${user.headpath!=null }">
                            <img src="/bruce${user.headpath}" alt="davatar" class="img-circle max-width-50" height="40"
                                 width="40">
                        </c:if>
                    </c:if>
                </a>

                &nbsp; &nbsp; <a href="/lyrics/add" title="添加最爱" class="btn btn-hero">
                    <span class="fa fa-plus"></span> 添加</a>
                &nbsp; &nbsp;
                    <a href="/" title="首页" class="btn-xs">
                    <span class="fa fa-home fa-lg padding5"></span></a>

                    <c:if test="${user!=null }">
                    <a href="/cm/logout" title="退出登录" id="J_log_info_l" class="btn-xs">
                    <i class="fa fa-sign-out fa-lg padding5"></i></a>
                    </c:if>

                    <c:if test="${user==null }">
                    <a href="/login" title="登录" id="J_log_info_l" class="btn-xs">
                    <i class="fa fa-sign-in fa-lg padding5"></i></a>
                    </c:if>
            </div>
        </div>
        <div class="navbar-collapse collapse mainhead-collapse">
            <ul class="nav mainhead-nav" id="J_tabs">

                <li cname="movies"><a href="/movies" title="包含最新的影视剧资源"><i class="fa fa-film padding5"></i>影视</a></li>
                <li cname="soft"><a href="/soft/mac" title="丰富的Mac软件资源"><i class="fa fa-apple padding5"></i>软件</a></li>
                <li cname="learning"><a href="/learning" title="学习/课程/书籍/知识"><i class="fa fa-graduation-cap padding5"></i>学习</a></li>
<%--                <li cname="soft"><a href="/soft/mac" title="丰富的Mac软件资源"><i class="fa fa-music padding5"></i>音乐</a></li>--%>
                <li cname="pic"><a href="/love" title="最爱主题图册"><i class="fa fa-picture-o padding5"></i>最爱</a></li>
                <li cname="note"><a href="/note" title="碎碎念的精神角落"><i class="fa fa-comment padding5"></i>树洞</a> </li>
                <li cname="pcenter">
                    <a id="J_log_info_r" title="登录-个人中心" <c:if test="${user!=null }"> href="/cm/pcentral"</c:if>
                       <c:if test="${user==null }">href="/login"</c:if>>
                        <c:if test="${user!=null }"><i class="fa fa-user padding5"></i>空间</c:if>
                        <c:if test="${user==null }"><i class="fa fa-sign-in padding5"></i>登陆</c:if></a>
                </li>
            </ul>
        </div>
    </div>
</div>
