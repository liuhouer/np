<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="y" items="${list }" varStatus="ss">
    <div class="row">
        <div class="col-xs-3 col-sm-2 avatar">

            <a href="/cm/channel/${y.from_uid}" title="${y.from_uname }">
                <span class="imgbreath text-${y.from_span.toLowerCase()}">${y.from_uname.substring(0,1) }</span>
            </a>

        </div>
        <div class="col-xs-9 col-sm-10">
            <p>
                    <c:if test="${y.to_uname!=null && y.to_uname!=''}">
                        @${y.to_uname}
                    </c:if>
                    ${y.content }
            </p>
            <p>
                <small class="label label-gray">${y.addTime }</small>
                <c:if test="${user!=null }">
                    <div class="form-group clearfix note-comment hidden" id="comment_${y.topic_id}_${ss.index}_${ss.index}">
                        <textarea id="input_cm_${y.topic_id}_${ss.index}_${ss.index}"
                                                  class="form-control border-light-1 bg-lyellow radius-0"
                                                  placeholder="回复点什么..."
                                                  rows="2"></textarea>
                        <button topic-id="${y.topic_id}"
                                topic-type="${y.topic_type}"
                                from-uid="${user.id}"
                                from-uname="${user.username}"
                                to-uname="${y.from_uname}"
                                to-uid="${y.from_uid}"
                                data-dismiss="#comment_${y.topic_id}_${ss.index}_${ss.index}"
                                data-target="#text_${ss.index}"
                                data-input="#input_cm_${y.topic_id}_${ss.index}_${ss.index}"
                                class="btn btn-hero click2save margin-t10"><i class="fa fa-floppy-o"></i> 回复</button>
                    </div>
                    <button class="clearfix btn btn-gray btn-xs click2comment"
                            title="点击回复/收起回复"
                            data-dismiss="#comment_${y.topic_id}_${ss.index}_${ss.index}"
                            data-target="#comment_${y.topic_id}_${ss.index}_${ss.index}">
                        <span class="glyphicon glyphicon-comment"></span>
                    </button>
                </c:if>
            </p>
        </div>

    </div>
    <hr>
</c:forEach>

