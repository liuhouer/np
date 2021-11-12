<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="author" content="www.qinco.net">
    <meta name="robots" content="index,follow,archive">
    <link rel="shortcut icon" href="https://northpark.cn/statics/img/favicon.ico">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <c:if test="${page==null || page==''}">
        <title>提醒系统 | NorthPark</title>
    </c:if>
    <c:if test="${page!=null && page!=''}">
        <title>提醒系统 ::第${page}页 | NorthPark</title>
    </c:if>

    <meta name="keywords" content="NorthPark,提醒系统">
    <meta name="description" content="NorthPark提醒系统">
</head>

<body>

<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<!-- 页面标题 -->
<h1 class="font-elegant center">Mac软件</h1>
<div class="clearfix maincontent grayback">
    <div class="container">
        <div class="mainbody" style="margin-top:100px; ">


            <div class="row">
                <div class="col-sm-7">
                    <c:forEach var="y" items="${list }" varStatus="ss">
                    <%--remindID--%>
                        <div class="row bg-white padding20">

                            <div class="col-sm-10 avatar">

                                <%-- 1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】--%>
                                <c:if test="${y.remindID==1}">
                                        <p>
                                        <span class="text-${y.senderID.substring(0,1) }" style="width: 28px;height: 28px;line-height: 28px;">
                                                ${y.senderName.substring(0,1) }
                                        </span>
                                            <a href="/cm/detail/${y.senderID}" title="${y.senderName }">
                                                    ${y.senderName }
                                            </a>
                                            <label class="padding5">在回复</label>
                                            <a href="${y.objectLinks}" style="font-size: 12px;line-height: 1.67;font-weight: 400;letter-spacing: normal;">${y.object}</a>
                                            <label class="padding5">时提到了你</label>
                                            <c:if test="${y.status==0}">
                                                <i class="fa fa-bell-o padding5" title="未读"></i>
                                                <input type="hidden" name="unReadId" value="${y.id}">
                                            </c:if>
                                        </p>
                                        <blockquote>
                                            <p style="font-size: 12px;line-height: 1.67;font-weight: 400;letter-spacing: normal;">
                                                    ${y.message }
                                            </p>
                                        </blockquote>

                                </c:if>

                                <%--留言--%>
                                <c:if test="${y.remindID==3}">
                                    <p>
                                        <span class="text-${y.senderID.substring(0,1) }" style="width: 28px;height: 28px;line-height: 28px;">
                                                ${y.senderName.substring(0,1) }
                                        </span>
                                        <a href="/cm/detail/${y.senderID}" title="${y.senderName }">
                                                ${y.senderName }
                                        </a>
                                        <label class="padding5">在回复</label>
                                        <a href="${y.objectLinks}" style="font-size: 12px;line-height: 1.67;font-weight: 400;letter-spacing: normal;">${y.object}</a>
                                        <label class="padding5">时提到了你</label>
                                        <c:if test="${y.status==0}">
                                            <i class="fa fa-bell-o padding5" title="未读"></i>
                                            <input type="hidden" name="unReadId" value="${y.id}">
                                        </c:if>
                                    </p>
                                    <blockquote>
                                        <p style="font-size: 12px;line-height: 1.67;font-weight: 400;letter-spacing: normal;">
                                                ${y.message }
                                        </p>
                                    </blockquote>

                                </c:if>

                                <%--最爱--%>
                                <c:if test="${y.remindID==2}">
                                    <p>
                                        <span class="text-${y.senderID.substring(0,1) }" style="width: 28px;height: 28px;line-height: 28px;">
                                                ${y.senderName.substring(0,1) }
                                        </span>
                                        <a href="/cm/detail/${y.senderID}" title="${y.senderName }">
                                                ${y.senderName }
                                        </a>
                                        <label class="padding5">${y.message }:</label>
                                        <a href="${y.objectLinks}">${y.object}</a>
                                        <c:if test="${y.status==0}">
                                            <i class="fa fa-bell-o padding5" title="未读"></i>
                                            <input type="hidden" name="unReadId" value="${y.id}">
                                        </c:if>
                                    </p>
                                </c:if>

                                <%--关注--%>
                                <c:if test="${y.remindID==4}">
                                    <p>
                                        <span class="text-${y.senderID.substring(0,1) }" style="width: 28px;height: 28px;line-height: 28px;">
                                                ${y.senderName.substring(0,1) }
                                        </span>
                                        <a href="/cm/detail/${y.senderID}" title="${y.senderName }">
                                                ${y.senderName }
                                        </a>
                                        <label class="padding5">${y.message }</label>
                                        <c:if test="${y.status==0}">
                                            <i class="fa fa-bell-o padding5" title="未读"></i>
                                            <input type="hidden" name="unReadId" value="${y.id}">
                                        </c:if>
                                    </p>
                                </c:if>

                                <%--5类-站内消息--%>
                                <c:if test="${y.remindID==5}">
                                    <p>
                                        <span class="text-${y.senderID.substring(0,1) }" style="width: 28px;height: 28px;line-height: 28px;">
                                                ${y.senderName.substring(0,1) }
                                        </span>
                                            ${y.senderName }
                                            <p>
                                            <label class="padding5  text-primary">${y.message }</label>
                                            </p>
                                            <c:if test="${y.status==0}">
                                                <i class="fa fa-bell-o padding5" title="未读"></i>
                                                <input type="hidden" name="unReadId" value="${y.id}">
                                            </c:if>
                                    </p>
                                </c:if>
                                <p>
                                    <small class="label label-gray">${y.createTime }</small>
                                </p>
                            </div>

                        </div>
                        <hr>
                    </c:forEach>
                    <c:if test="${pagein!='no' and list.size()>0 }">
                        <%@ include file="/WEB-INF/views/page/common/fenye.jsp" %>
                    </c:if>
                    <c:if test="${ list.size()==0 }">
                        <p class="center">
                            <small class="label label-gray">空空如也</small>
                        </p>

                        <hr class="border-light-1">
                    </c:if>
                </div>
                <div class="col-sm-offset-1 col-sm-4 ">

                    <div class="row bg-lblue padding20 radius-5">
                        <c:if test="${user.headpath!=null and user.headpath!=''}">
                            <div class="col-xs-2 avatar padding10">
                             <img src="/bruce/${user.headpath}"></img>
                            </div>
                        </c:if>
                        <c:if test="${user.headpath==null or user.headpath==''}">
                            <div class="col-xs-2 avatar padding10">
                                <span class="text-1" >
                                        ${user.username.substring(0,1) }
                                </span>
                            </div>

                        </c:if>
                        <div class="col-xs-10">
                            <h4 style="color:#333">${user.username}</h4>
                            <hr>
                            <p class="gray-text"><i class="fa fa-envelope padding5"></i>${user.email}</p>
                            <p class="gray-text"><i class="fa fa-link padding5"></i>
                                <c:if test="${user.blogsite==null or user.blogsite==''}">
                                    -
                                </c:if>
                                <c:if test="${user.blogsite!=null and user.blogsite!=''}">
                                    ${user.blogsite}
                                </c:if>
                            </p>
                            <p class="gray-text"><i class="fa fa-globe padding5"></i>
                                <c:if test="${user.meta==null or user.meta==''}">
                                -
                                </c:if>
                                <c:if test="${user.meta!=null and user.meta!=''}">
                                    ${user.meta}
                                </c:if>

                            </p>
                            <p class="gray-text"><i class="fa fa-calendar-times-o padding5"></i>${user.date_joined}</p>
                        </div>

                    </div>

                </div>
            </div>


        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp" %>

<script type="text/javascript" data-cfasync="false">
    //禁止图片拉伸
    $(function () {
        $("img").each(function () {
            $(this).css('max-width', $(".bg-white").width());
        })

    })

    function readNotify(){
        const arr =[];
        $("input[name='unReadId']").each(function (){
            arr.push($(this).val());
        });
        if(arr.length>0){
            var id = arr.join(",");
            console.log("id",id);
            if(id){
                $.ajax({
                    url: "/notify/readNotify",
                    type: "post",
                    dataType: "json",
                    data:{"id":id},
                    success: function (msg) {

                        if(msg.result){
                            //清理未读消息
                            console.log('清理未读消息',msg.data);
                        }else{
                            console.log('清理未读消息失败--->'+msg.message);
                        }
                    }
                });
            }

        }

    }


    setTimeout(readNotify(), 30*1000 );

</script>


<script>

    //loadDonates(1);

</script>
</body>
</html>
