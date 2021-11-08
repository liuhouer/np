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

                <c:if test="${user==null }">
                    <a href="/login" title="登陆" class="mainhead-avatar avatar centre" height="40" width="40">
                        <img src="https://northpark.cn/statics/img/davatar.jpg" alt="davatar"
                             class="img-circle max-width-50" height="40" width="40">
                    </a>
                </c:if>
                <c:if test="${user!=null }">

                    <a href="/cm/pcentral" title="个人中心" class="mainhead-avatar avatar centre" height="40" width="40">
                        <c:if test="${user.headpath==null }">
                            <span class="max-width-50  ${user.headspanclass }" alt="${user.username}" height="40"
                                  width="40">${user.headspan }</span>
                        </c:if>
                        <c:if test="${user.headpath!=null }">
                            <img src="/bruce${user.headpath}" alt="davatar" class="img-circle max-width-50" height="40"
                                 width="40" />
                        </c:if>
                    </a>

                </c:if>

                    <a href="/lyrics/add" title="添加最爱" class="btn btn-hero margin-l20">
                    <span class="fa fa-plus"></span> 添加</a>

                    <a href="/" title="首页" class="btn-xs margin-l10">
                    <span class="fa fa-home fa-lg padding5"></span></a>

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
                    <c:if test="${user==null }">
                        <a id="J_log_info_r" title="登录-个人中心" href="/login">
                            <i class="fa fa-sign-in padding5"></i>登陆
                        </a>

                    </c:if>
                    <c:if test="${user!=null }">

                            <div class="dropdown">
                                <a class="dropdown-toggle " id="dropdownMenu1" data-toggle="dropdown" style="padding-right:1.2em;padding-left:1.2em;font-size:1.2em;line-height:38px;font-weight:400;color:#999;text-decoration:none;text-shadow:none;border-bottom:3px solid transparent;position:relative;display:block;padding:10px 15px;">
                                    <i class="fa fa-user "></i>
                                    <span>${user.username}</span>
                                    <i class="fa fa-caret-down padding5"></i>
                                </a>
                                <ul class="dropdown-menu grayback" role="menu" aria-labelledby="dropdownMenu1" style="position: relative">

                                    <li role="presentation" >
                                        <a title="未读消息" href="/notifications">
                                            <i  class="fa fa-envelope padding10"></i>消息
                                            <span class="badge margin5" id="J_notify_box">0</span>
                                        </a>
                                    </li>
                                    <li role="presentation" class="divider"></li>
                                    <li role="presentation" >
                                        <a role="menuitem" tabindex="-1" href="/cm/pcentral" title="${user.username}的个人空间">
                                             <i class="fa fa-bullseye fa-lg padding10"></i>空间
                                        </a>
                                    </li>

                                    <li role="presentation" >
                                        <a role="menuitem" tabindex="-1" href="/me/settings" title="修改档案">
                                            <i class="fa fa-cog fa-lg padding10"></i>档案
                                        </a>
                                    </li>

                                    <li role="presentation" class="divider"></li>
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" href="/cm/logout">
                                            <i class="fa fa-sign-out fa-lg padding10"></i>注销
                                        </a>
                                    </li>
                                </ul>
                            </div>


                    </c:if>

                </li>

            </ul>
        </div>
    </div>
</div>
