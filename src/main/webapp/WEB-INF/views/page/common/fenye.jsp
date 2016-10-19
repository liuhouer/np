<%@ page language="java" import="java.util.*,cn.northpark.utils.MyConstant,cn.northpark.utils.PageView" pageEncoding="UTF-8"%>

<div class="row center grayback">
	 <%
	 	int liststep = 5;//最多显示分页页数
	 	int maxresult = MyConstant.MAXRESULT;//每页显示记录数
	 	int currentpage = 0;//当前页
	 	int totalpage = 0;//总页数
	 	PageView pageView = (PageView) request.getAttribute("pageView");
	 	currentpage = pageView.getCurrentpage();
	 	int totalrecord = pageView.getTotalrecord(); //总记录数
	 	totalpage = pageView.getTotalpage(); //总页数
	 	if (totalpage == 0) {
	 		totalpage = 1;
	 	}
	 	int listbegin = (currentpage + 2 - (int) Math
	 			.ceil((double) liststep / 2));//从第几页开始显示分页信息
	 	if (listbegin < 1) {
	 		listbegin = 1;
	 	}

	 	int listend = currentpage + 2 + liststep / 2;//分页信息显示到第几页
	 	if (listend > totalpage) {
	 		listend = totalpage + 1;
	 	}

	 	String actionUrl = (String) request.getAttribute("actionUrl");
	 	

	 	int sumstart = MyConstant.MAXRESULT * currentpage;
	 	session.setAttribute("sumstart", sumstart);
	 %>    
	     
	 <form id="pageForm" action="">
		<ul class="qinco-pagination pagination-lg">
			<li><a href="<%=actionUrl%>/page0">首页</a></li>
			<li><a href="<%=actionUrl%>/page<%=currentpage - 1%>">‹</a>
			</li>
			<%
				//<显示分页码
				for (int i = listbegin; i < listend; i++) {
					if (i != currentpage + 1) {//如果i不等于当前页
			%>
			<li><a href="<%=actionUrl%>/page<%=i - 1%>"><%=i%></a></li>
			<%
				} else {
			%>
			<li class="active"><a
				href="<%=actionUrl%>/page<%=i - 1%>"><%=i%></a></li>
			<%
				}
				}//显示分页码>
			%>
			<li><a href="<%=actionUrl%>/page<%=currentpage + 1%>">...</a></li>
			<li><a href="<%=actionUrl%>/page<%=currentpage + 1%>">›</a></li>
			<li><a href="<%=actionUrl%>/page<%=totalpage - 1%>"><%=totalpage %>页</a> 
			</li>
			<%-- <li><a>共<%=totalpage %>页</a> 
			</li> --%>
		</ul>

		<input id="pagenow" value="<%=currentpage + 1%>"  type="hidden"/>         
      
     
     </form>
	</div>