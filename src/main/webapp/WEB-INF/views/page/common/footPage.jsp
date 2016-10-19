<%@ page language="java" import="java.util.*,cn.NorthPark.utils.MyConstant,cn.NorthPark.utils.PageView" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
<style>

.qinco-pagination {
  display: inline-block;
  padding-left: 0;
  margin: 20px 0;
  border-radius: 4px;
}
.qinco-pagination > li {
  display: inline;
}
.qinco-pagination > li > a,
.qinco-pagination > li > span {
  position: relative;
  float: left;
  padding: 6px 12px;
  margin-left: -1px;
  line-height: 1.42857143;
  color: #7a8c8c;
  text-decoration: none;
  background-color: #fff;
  border: 1px solid #ddd;

}
.qinco-pagination > li:first-child > a,
.qinco-pagination > li:first-child > span {
  margin-left: 0;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}
.qinco-pagination > li:last-child > a,
.qinco-pagination > li:last-child > span {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}
.qinco-pagination > li > a:hover,
.qinco-pagination > li > span:hover,
.qinco-pagination > li > a:focus,
.qinco-pagination > li > span:focus {
    color: #7a8c8c;
  background-color: #eee;
  border-color: #ddd;
}
.qinco-pagination > .active > a,
.qinco-pagination > .active > span,
.qinco-pagination > .active > a:hover,
.qinco-pagination > .active > span:hover,
.qinco-pagination > .active > a:focus,
.qinco-pagination > .active > span:focus {
  z-index: 2;
  color: #fff;
  cursor: default;
  background-color: #7a8c8c;
  border-color: #7a8c8c;
}
.qinco-pagination > .disabled > span,
.qinco-pagination > .disabled > span:hover,
.qinco-pagination > .disabled > span:focus,
.qinco-pagination > .disabled > a,
.qinco-pagination > .disabled > a:hover,
.qinco-pagination > .disabled > a:focus {
  color: #999;
  cursor: not-allowed;
  background-color: #fff;
  border-color: #ddd;
}

.alert-success {border:0;color:#333;}
.alert-danger {border:0;color:#333;background:#f8e04d;}

@media (min-width: 768px) {
  .container
  {
    width:768px
  }
  
  .sm-text-left
  {
    text-align:left
  }
  
  .sm-text-right
  {
    text-align:right
  }
  
  .sm-text-center
  {
    text-align:center
  }
}

@media (min-width: 992px) {
  .container
  {
    width:992px
  }
  
  .md-text-left
  {
    text-align:left
  }
  
  .md-text-right
  {
    text-align:right
  }
  
  .md-text-center
  {
    text-align:center
  }
}

@media (min-width: 1200px) {
  .container
  {
    width:1180px
  }
  
  .lg-text-left
  {
    text-align:left
  }
  
  .lg-text-right
  {
    text-align:right
  }
  
  .lg-text-center
  {
    text-align:center
  }
}
 

</style>

    <div id="pag" style="width: 80%;left: 10%;height: 80px;border-color: skyblue;position:absolute; margin-top:10px;  top: 80%;z-index: 22;text-align:center;">
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
	 	
	 	String userid = (String) request.getAttribute("userid");

	 	int sumstart = MyConstant.MAXRESULT * currentpage;
	 	session.setAttribute("sumstart", sumstart);
	 %>    
	     
	 <form id="pageForm" action="">
		<ul class="qinco-pagination pagination-lg">
			<li><a href="<%=actionUrl%>?userid=<%=userid %>&currentpage=0">‹‹</a></li>
			<li><a href="<%=actionUrl%>?userid=<%=userid %>&currentpage=<%=currentpage - 1%>">‹</a>
			</li>
			<%
				//<显示分页码
				for (int i = listbegin; i < listend; i++) {
					if (i != currentpage + 1) {//如果i不等于当前页
			%>
			<li><a href="<%=actionUrl%>?userid=<%=userid %>&currentpage=<%=i - 1%>"><%=i%></a></li>
			<%
				} else {
			%>
			<li class="active"><a
				href="<%=actionUrl%>?userid=<%=userid %>&currentpage=<%=i - 1%>"><%=i%></a></li>
			<%
				}
				}//显示分页码>
			%>
			<li><a href="<%=actionUrl%>?userid=<%=userid %>&currentpage=<%=currentpage + 1%>">›</a></li>
			<li><a href="<%=actionUrl%>?userid=<%=userid %>&currentpage=<%=totalpage - 1%>">››</a> 
			</li>
			<li style="text-align: center;padding-left: 100px;"><font size="5" style="margin-top: 10px;">
			共<%=totalpage%>页</font>
			</li>
		</ul>

		<input id="pagenow" value="<%=currentpage + 1%>"  type="hidden"/>         
      
     
     </form>
	</div>
