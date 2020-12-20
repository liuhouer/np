<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="col-md-12 margin-t10">

        <div class="tab-pane fade in active" id="3">

            <c:forEach var="y" items="${donates_list_min }" varStatus="ss">
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


            <div class="col-xs-10 clearfix border-bottom padding-t20">
                <p>
                    <span class="glyphicon glyphicon-time margin5"></span>
                    2017-09-20 16:52:12
                </p>

                <p>

                <p>
                    <span class="glyphicon glyphicon-user margin5"></span>
                    匿名用户
                </p>
                <p>
                    <span class="glyphicon glyphicon-barcode margin5"></span>
                    10000503012***267214285
                </p>
                <p>
                    <span class="glyphicon glyphicon-comment margin5"></span>
                    WinclonePro5.5MacCN
                </p>
                <p>
                    <span class="glyphicon glyphicon-link margin5"></span>

                    <a href="https://northpark.cn/soft/post-14058.html" target="_blank">Winclone Pro 5.5 Mac中文破解版</a>

                </p>
                <p>
                    <span class="glyphicon  margin5">￥</span>
                    3.00
                </p>

            </div>

            <div class="col-xs-10 clearfix border-bottom padding-t20">
                <p>
                    <span class="glyphicon glyphicon-time margin5"></span>
                    2017-08-01 03:19:04
                </p>

                <p>

                <p>
                    <span class="glyphicon glyphicon-user margin5"></span>
                    匿名用户
                </p>
                <p>
                    <span class="glyphicon glyphicon-barcode margin5"></span>
                    10000503012***034005463
                </p>
                <p>
                    <span class="glyphicon glyphicon-comment margin5"></span>
                    多谢你分享的stata!
                </p>

                <p>
                    <span class="glyphicon  margin5">￥</span>
                    5.00
                </p>

            </div>



            <div class="col-xs-10 clearfix ">
                <p>
                    <span class="glyphicon  glyphicon-asterisk margin5"></span>

                </p>
                <p>
                    ~ 生活不止苟且，还有诗和远方 ~
                </p>
            </div>

        </div>
