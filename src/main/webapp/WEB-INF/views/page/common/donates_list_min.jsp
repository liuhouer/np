<%@ page import="cn.northpark.utils.page.PageView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="col-md-12 margin-t10">

 <%
        PageView pageView = (PageView) request.getAttribute("pageView");

        String type_id = (String) request.getAttribute("type_id");

    %>
        <div class="tab-pane fade in active" id="3">

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

	

		<div class=" clearfix center pageinfo ">
		    <ul class="qinco-pagination pagination-sm ">
		    	<li><a onclick="loadDonates(<%=type_id%>,1)">‹‹</a></li>
		    	<li><a onclick="loadDonates(<%=type_id%>,<%=pageView.getCurrentpage() - 1%>)">‹</a></li>
		        <%
		            //<显示分页码
		            for (int i = pageView.getPageindex().getStartindex(); i <= pageView.getPageindex().getEndindex(); i++) {
		                if (i != pageView.getCurrentpage()) {//如果i不等于当前页
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
		        <li><a onclick="loadDonates(<%=type_id%>,<%=pageView.getCurrentpage() + 1%>)">›</a></li>
		        <li><a onclick="loadDonates(<%=type_id%>,<%=pageView.getTotalpage()%>)">››</a></li>
            
		    </ul>
			
			</div> 

            <div class=" clearfix ">
               <%-- <p>
                    <span class="glyphicon  glyphicon-asterisk margin5"></span>

                </p>--%>
                <p>
                    ~ 生活不止苟且，还有诗和远方 ~
                </p>
            </div>

        </div>
