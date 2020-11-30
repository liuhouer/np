<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="y" items="${list }" varStatus="ss">
    <div class="row">
        <div class="col-xs-3 col-sm-2 avatar">
            <span class="imgbreath text-${y.from_span.toLowerCase()}">${y.from_span}</span>
        </div>
        <div class="col-xs-9 col-sm-10">
            <p>
                <a href="/cm/detail/${y.from_uid}">${y.from_uname }</a>

                ：
                    <c:if test="${y.to_uname!=null && y.to_uname!=''}">
                        @${y.to_uname}
                    </c:if>
                    ${y.content }
            </p>
            <p>
                <small class="label label-gray">${y.add_time }</small>
                <c:if test="${user!=null }">
                    <div class="form-group clearfix note-comment hidden" id="comment_${y.topic_id}_${ss.index}_${ss.index}">
                                        <textarea id="input_cm_${y.topic_id}_${ss.index}_${ss.index}" class="form-inline input-lg grid80 border-light-1 bg-lyellow radius-0" style="height: 49px;max-height: 400px;margin: 0px 10px 0px 0px;" rows="3">

                                        </textarea>
                        <button topic-id="${y.topic_id}"
                                topic-type="1"
                                from-uid="${user.id}"
                                from-uname="${user.username}"
                                to-uname="${y.from_uname}"
                                to-uid="${y.from_uid}"
                                data-dismiss="#comment_${y.topic_id}_${ss.index}_${ss.index}"
                                data-target="#text_${ss.index}"
                                data-input="#input_cm_${y.topic_id}_${ss.index}_${ss.index}"
                                class="glyphicon glyphicon-ok-sign click2save form-inline "></button>
                    </div>
                    <button class="clearfix btn btn-gray btn-xs click2comment "
                            data-dismiss="#comment_${y.topic_id}_${ss.index}_${ss.index}"
                            data-target="#comment_${y.topic_id}_${ss.index}_${ss.index}"> &nbsp; <span
                            class="glyphicon glyphicon-comment"></span> &nbsp;
                    </button>
                </c:if>
            </p>
            <hr>
        </div>
    </div>
</c:forEach>

