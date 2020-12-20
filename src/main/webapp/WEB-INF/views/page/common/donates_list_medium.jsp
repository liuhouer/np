<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="col-md-12 margin-t10">

    <c:forEach var="y" items="${donates_list_medium }" varStatus="ss">
        <div class="col-xs-10 clearfix border-bottom padding-t20">
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


    <div class="col-xs-10 clearfix ">
        <p>
            <span class="glyphicon  glyphicon-asterisk margin5"></span>

        </p>
        <p>
            ~ 生活不止苟且，还有诗和远方 ~
        </p>
    </div>

</div>
