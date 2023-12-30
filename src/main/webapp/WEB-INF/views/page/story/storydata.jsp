<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="row">
    <c:forEach items="${list }" var="s" varStatus="ss">
        <div class="col-sm-6 ">
            <div class="clearfix bg-white margin-t10 margin-b10 padding20">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="thumbnail border-0 center ">
                            <div class="avatar">
                                <a

                                        <c:if test="${s.tailSlug==null ||s.tailSlug==''}">
                                            href="/cm/channel/${s.userid}"
                                        </c:if>
                                        <c:if test="${s.tailSlug!=null }">
                                            href="/people/${s.tailSlug}"
                                        </c:if>

                                        title="${s.get('username')}:我的故事">
                                    <c:if test="${s.get('headPath') ==null||s.get('headPath') ==''||s.get('headPath').length()==0}">
                                        <span class=" imgbreath ${s.head_span_class }"
                                              alt="${s.get('username')}">${s.head_span }</span>
                                    </c:if>
                                    <c:if test="${s.get('headPath') !=null && s.get('headPath').length()>0}">
                                        <img class="imgbreath"
                                        <c:choose>
                                             <c:when test="${fn:contains(s.headPath ,'http://') }">src="${s.headPath }"</c:when>
                                             <c:otherwise>src="/bruce/${s.headPath }"</c:otherwise>
                                        </c:choose>

                                             alt="${s.username}的故事">
                                    </c:if>
                                </a>
                                <p>
                                    <small class="gray-text">${s.username}</small>
                                </p>
                                <div class="clearfix visible-xs">
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-9">
                        <p>
                            <small class="label label-gray">${s.create_time }</small> &nbsp; <a

                                <c:if test="${s.tailSlug==null ||s.tailSlug==''}">
                                    href="/cm/channel/${s.userid}"
                                </c:if>
                                <c:if test="${s.tailSlug!=null }">
                                    href="/people/${s.tailSlug}"
                                </c:if>


                                title="${s.username}的最爱">${s.username}</a> 写到：
                        </p>
                        <div id="brief_${ss.index}" class="note-brief">

                            <c:if test="${!fn:startsWith(s.brief, '<')}">
                            <p></p>
                            <p>
                                </c:if>
                                    ${s.brief }

                                <c:if test="${!fn:endsWith(s.brief, '>')}">
                            </p>
                            </c:if>


                        </div>
                        <div class="clearfix note-brief hidden" id="text_${ss.index}">
                                ${s.note }

                        </div>

                        <div class="hidden" id="stuffCommentList_${s.noteid}">

                            <h4><span class="glyphicon glyphicon-comment" title="展示评论详情;登录后参与评论"></span></h4>
                            <hr>
                                <%--展示评论详情--%>
                            <div class="clearfix" id="stuffCommentBox_${s.noteid}">

                            </div>

                        </div>




                        <c:if test="${user!=null }">
                            <div class="form-group clearfix note-comment" id="comment_${s.noteid}_${ss.index}" style="display: none">
                                <textarea id="input_cm_${s.noteid}_${ss.index}"
                                          class="form-control bg-lyellow"
                                          placeholder="回复点什么..."
                                          rows="3"></textarea>

                                <button title="提交评论"
                                        class="btn btn-hero click2save margin-t10"
                                        topic-id="${s.noteid}"
                                        topic-type="1"
                                        from-uid="${user.id}"
                                        from-uname="${user.username}"
                                        data-dismiss="#comment_${s.noteid}_${ss.index}"
                                        data-target="#text_${ss.index}"
                                        data-input="#input_cm_${s.noteid}_${ss.index}"
                                ><i class="fa fa-floppy-o"></i> 回复</button>

                            </div>
                        </c:if>



                            <button class="clearfix btn btn-gray btn-xs click2show "
                                    topic-id="${s.noteid}"
                                    data-input="#comment_${s.noteid}_${ss.index}"
                                    data-dismiss="#brief_${ss.index}"
                                    data-target="#text_${ss.index}"> &nbsp;
                             <span class="glyphicon glyphicon-chevron-down"></span> &nbsp;
                            </button>


                        <c:if test="${user!=null }">
                            <button class="clearfix btn btn-gray btn-xs click2comment "
                                    title="点击回复/收起回复"
                                    data-dismiss="#comment_${s.noteid}_${ss.index}"
                                    data-target="#comment_${s.noteid}_${ss.index}"> &nbsp;
                                <span class="glyphicon glyphicon-comment"></span> &nbsp;
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

