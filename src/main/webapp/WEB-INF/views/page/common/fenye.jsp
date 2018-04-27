<%@ page language="java" import="java.util.*,cn.northpark.utils.page.MyConstant,cn.northpark.utils.page.PageView"
         pageEncoding="UTF-8" %>

<div class="row center grayback">
    <%
        PageView pageView = (PageView) request.getAttribute("pageView");

        String actionUrl = (String) request.getAttribute("actionUrl");

    %>

    <form id="pageForm" action="">
        <ul class="qinco-pagination pagination-lg ">
            <li><a href="<%=actionUrl%>/page/1">‹‹</a></li>
            <li><a href="<%=actionUrl%>/page/<%=pageView.getCurrentpage() - 1%>">‹</a>
            </li>
            <%
                //<显示分页码
                for (int i = pageView.getPageindex().getStartindex(); i <= pageView.getPageindex().getEndindex(); i++) {
                    if (i != pageView.getCurrentpage()) {//如果i不等于当前页
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
            <li><a href="<%=actionUrl%>/page/<%=pageView.getCurrentpage() + 1%>">›</a></li>
            <li><a href="<%=actionUrl%>/page/<%=pageView.getTotalpage()%>">››</a>
            </li>
        </ul>

        <input id="pagenow" value="<%=pageView.getCurrentpage()%>" type="hidden"/>


    </form>
</div>