<%@ page import="com.github.pagehelper.PageInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="col-md-12 margin-t10" id="1">


    <%
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

        String type_id = (String) request.getAttribute("type_id");

    %>

    <c:forEach var="y" items="${list }" varStatus="ss">
        <div 
                    <c:if test="${ss.index == 0 }">
                       class=" clearfix border-bottom padding-t10"
		  </c:if>
                    <c:if test="${ss.index > 0 }">
                       class=" clearfix border-bottom padding-t20"
		  </c:if>
         >
            <p>
                <span class="glyphicon glyphicon-time margin5"></span>
                    ${y.add_time}
            </p>
            <p>
            </p>
            <p>
                <span class="glyphicon glyphicon-user margin5"></span>
                    ${y.account_name}
            </p>
            <p>
                <span class="glyphicon glyphicon-barcode margin5"></span>
                    ${y.alipay_trans_id}
            </p>
            <p>
                <span class="glyphicon glyphicon-comment margin5"></span>
                    ${y.reward_msg}
            </p>
            <p>
                <span class="glyphicon  margin5">￥</span>
                    ${y.order_amount}
            </p>
        </div>
    </c:forEach>


    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2018-04-10 08:51
        </p>

        <p>

        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            黄婧怡
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20180410210***507700812
        </p>
        <p>
            <span class="glyphicon glyphicon-retweet margin5"></span>
            人非常好，重要的是网速特快
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="https://jillhuang1085.github.io/" target="_blank">https://github.com/JillHuang1085</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>


    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2018-03-29 22:17:15
        </p>

        <p>

        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            fromqtoj
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            10000395010***730739800
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="https://fromqtoj.github.io/" target="_blank">https://github.com/fromqtoj</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            100.00
        </p>

    </div>
    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2018-03-29 10:56:54
        </p>

        <p>

        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            augustvino
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            10000395010***560211665
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="http://blog.augustvino.cn/" target="_blank">http://blog.augustvino.cn/</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>
    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2018-02-12 15:42
        </p>

        <p>

        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            韩锡勋哦哦
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20180212210***247083668

        </p>
        <p>
            <span class="glyphicon glyphicon-retweet margin5"></span>
            同学挺好的！山西的大学生！！
        </p>
        <p>
            <span class="glyphicon glyphicon-comment margin5"></span>
            大哥人很好，支持
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="http://hanxixun.github.io/" target="_blank">http://hanxixun.github.io/</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>
    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2018-01-09 22:30
        </p>

        <p>

        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            b***re
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20180109210***234125769
        </p>
        <p>
            <span class="glyphicon glyphicon-retweet margin5"></span>
            这位同学很谦虚好学，赞！
        </p>
        <p>
            <span class="glyphicon glyphicon-comment margin5"></span>
            教导非常细心
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="http://billvampire.github.io/" target="_blank">http://billvampire.github.io/</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>


    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2017-08-29 22:00
        </p>

        <p>
            <span class="glyphicon  glyphicon-phone margin5"></span>


            2751874403
        </p>
        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            Billvampire
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20170829210***205016276
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="http://billvampire.github.io/" target="_blank">http://billvampire.github.io/</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>
    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2017-08-13 01:31
        </p>

        <p>
            <span class="glyphicon  glyphicon-phone margin5"></span>


            544510*5
        </p>
        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            *梓涵
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20170813210***217537854
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="https://zihanedric.github.io/" target="_blank">https://zihanedric.github.io</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            100.00
        </p>

    </div>
    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2017-08-05 23:02:42
        </p>

        <p>
            <span class="glyphicon  glyphicon-phone margin5"></span>


            178****2085
        </p>
        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            季*菲
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20170805210***203034131
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>
            <a href="http://xxxfei.github.io" target="_blank">http://xxxfei.github.io</a>

        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>


    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2017-07-03 18:41
        </p>

        <p>
            <span class="glyphicon  glyphicon-phone margin5"></span>


            139****1786

        </p>
        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            臧*康
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            20170703210***217675150
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="https://wl059.github.io/" target="_blank">https://wl059.github.io/</a>
        </p>
        <p>
            <span class="glyphicon  margin5">￥</span>
            99.00
        </p>

    </div>


    <div class=" clearfix border-bottom padding-t20">
        <p>
            <span class="glyphicon glyphicon-time margin5"></span>
            2017-04-28 23:08:59
        </p>

        <p>
            <span class="glyphicon  glyphicon-phone margin5"></span>


            139****1786

        </p>
        <p>
            <span class="glyphicon glyphicon-user margin5"></span>
            lo***ng
        </p>
        <p>
            <span class="glyphicon glyphicon-barcode margin5"></span>
            10000394010***323845052
        </p>
        <p>
            <span class="glyphicon glyphicon-link margin5"></span>

            <a href="http://leopardpan.github.io" target="_blank">http://baixin.io/</a>
        </p>

        <p>
            <span class="glyphicon  margin5">￥</span>
            100.00
        </p>

    </div>

	<div class=" clearfix center pageinfo ">
    <ul class="qinco-pagination pagination-sm ">
        <li><a onclick="loadDonates(<%=type_id%>,1)">‹‹</a></li>
        <li><a onclick="loadDonates(<%=type_id%>,<%=pageInfo.getPrePage()%>)"
                <% if (!pageInfo.isHasPreviousPage()) { %>
               onclick="return false;"
                <% } %>

        >‹</a></li>
        <%
            //<显示分页码
            for (int i = pageInfo.getNavigateFirstPage(); i <= pageInfo.getNavigateLastPage(); i++) {
                if (i != pageInfo.getPageNum()) {//如果i不等于当前页
        %>
        <li><a onclick="loadDonates(<%=type_id%>,<%=i%>)"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li class="active"><a><%=i%>
        </a></li>
        <%
                }
            }//显示分页码>
        %>
        <li><a onclick="loadDonates(<%=type_id%>,<%=pageInfo.getNextPage()%>)">›</a></li>
        <li><a onclick="loadDonates(<%=type_id%>,<%=pageInfo.getPages()%>)">››</a></li>
    </ul>
	
	</div> 
	
    <div class=" clearfix ">
        <!-- <p>
            <span class="glyphicon  glyphicon-asterisk margin5"></span>

        </p> -->
        <p>
            ~ 生活不止苟且，还有诗和远方 ~
        </p>
    </div>

	
</div>
