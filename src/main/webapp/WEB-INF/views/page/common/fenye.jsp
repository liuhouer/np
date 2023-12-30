<%@ page language="java" import="java.util.*"
         pageEncoding="UTF-8" %>
<%@ page import="com.github.pagehelper.PageInfo" %>

<div class="row center grayback">
    <%
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

        String actionUrl = (String) request.getAttribute("actionUrl");

    %>

    <form id="pageForm" action="">
        <ul class="qinco-pagination pagination-lg ">
            <li><a href="<%=actionUrl%>/page/1">‹‹</a></li>
            <li><a href="<%=actionUrl%>/page/<%=pageInfo.getPrePage()%>"
                    <% if (!pageInfo.isHasPreviousPage()) { %>
                   onclick="return false;"
                    <% } %>
            >‹</a>

            </li>
            <%
                //<显示分页码
                for (int i = pageInfo.getNavigateFirstPage(); i <= pageInfo.getNavigateLastPage(); i++) {
                    if (i != pageInfo.getPageNum()) {//如果i不等于当前页
            %>
            <li><a href="<%=actionUrl%>/page/<%=i%>"><%=i%>
            </a></li>
            <%
            } else {
            %>
            <li class="active"><a
                    href="<%=actionUrl%>/page/<%=i%>"><%=i%>
            </a></li>
            <%
                    }
                }//显示分页码>
            %>
            <li><a href="<%=actionUrl%>/page/<%=pageInfo.getNextPage()%>"
                    <% if (!pageInfo.isHasNextPage()) { %>
                   onclick="return false;"
                    <% } %>
            >›</a></li>
            <li><a href="<%=actionUrl%>/page/<%=pageInfo.getPages()%>">››</a>
            </li>
        </ul>

        <input id="pagenow" value="<%=pageInfo.getPageNum()%>" type="hidden"/>


    </form>
</div>