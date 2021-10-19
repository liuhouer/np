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
                &nbsp; &nbsp; <a href="/lyrics/add" title="添加图册" class="btn btn-hero"><span
                    class="glyphicon glyphicon-plus"></span> 添加</a>
                &nbsp; &nbsp;
                <c:if test="${user!=null }"><a href="/cm/logout" title="退出" id="J_log_info_l">
                    <i class="fa fa-sign-out padding5"></i>退出</a></c:if>
                <c:if test="${user==null }"><a href="/login" title="登录" id="J_log_info_l">
                    <i class="fa fa-sign-in padding5"></i>登陆</a></c:if>
            </div>
        </div>
        <div class="navbar-collapse collapse mainhead-collapse">
            <ul class="nav mainhead-nav" id="J_tabs">

                <li cname="movies"><a href="/movies" title="包含最新的影视剧资源"><i class="fa fa-film fa-lg padding5"></i>影视窝</a></li>
                <li cname="soft"><a href="/soft/mac" title="丰富的Mac软件资源"><i class="fa fa-apple fa-lg padding5"></i>Mac软件</a>
                </li>
                <li cname="pic"><a href="/love" title="最爱主题图册"><i class="fa fa-picture-o fa-lg padding5"></i>最爱</a></li>
                <li cname="note"><a href="/note/list" title="碎碎念的精神角落"><i class="fa fa-comment fa-lg padding5"></i>留言</a></li>
                <li cname="pcenter">
                    <a id="J_log_info_r" title="登录-个人中心" <c:if test="${user!=null }"> href="/cm/pcentral"</c:if>
                       <c:if test="${user==null }">href="/login"</c:if>>
                        <c:if test="${user!=null }"><i class="fa fa-user fa-lg padding5"></i>空间</c:if>
                        <c:if test="${user==null }"><i class="fa fa-sign-in fa-lg padding5"></i>登陆</c:if></a>
                </li>
            </ul>
        </div>
    </div>
</div>
