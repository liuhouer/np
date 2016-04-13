<%@page import="com.bruce.utils.MyConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bruce.utils.PageView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common.jsp"%>
<script src="js/jquery-1.9.0.js"></script>
<title>布.图列表</title>

</head>

<body>

	<form  id="form" action="lyrics/addMenu.action" method="post">

        <input type="hidden" name="userid" id="userid" value="${user.id }"/>
        <div style="text-align: left; width: 80% ;padding-left: 50px;">
        <input type="button" value="新增" name="add" id="add" onclick="adds();">
        <input type="button" value="编辑" name="edit" id="edit" onclick="edits()">
        <input type="button" value="删除" name="delete" id="delete" onclick="removes()">
        
        
        </div>
          <%@ include file="/page/common/footPage.jsp"%>  
		<table border="1em"  class="purple" style="width: 80%;">
			<tbody>
				<tr>
				    <th><input type="checkbox" id="al" onclick="selectAll()"></th>
					<th>NO</th>
					<th>Type</th>
					<th>Artist</th>
					<th>Title</th>
					<th>Album</th>
					<th>MediaLength</th>
					<th>Rating</th>
					<th>Downloads</th>
					<th>UpdateDate</th>
					<th align="center">操作</th>
				</tr>
				 <c:if test="${!empty list }">
					<c:forEach items="${list }" var="s" varStatus="v">
						<c:if test="${v.index % 2 == 0}">
							<tr class="odd">
						</c:if>
						<c:if test="${v.index % 2 != 0}">
							<tr>
						</c:if>
						<td>
						<input type="checkbox"  name="checkbox" value="${s.id }" >
						</td>
						<td>${v.index+1+sumstart}</td>
						<c:forEach items="${LList }" var="y" varStatus="yy">
						<c:if test="${y.id==s.lyricsid }">
						<td><c:out value="${y.type }"></c:out></td>
						<td><c:out value="${y.artist }"></c:out></td>
						<td><c:out value="${y.title }"></c:out></td>
						<td><c:out value="${y.album }"></c:out></td>
						<td><c:out value="${y.medialength }"></c:out></td>
						<td>
						<c:if test="${y.rating ==null}"><c:out value="1.0"></c:out></c:if>
						<c:if test="${y.rating !=null}"><c:out value="${y.rating }"></c:out></c:if>
						</td>
						<td>
						<c:if test="${y.downloads ==null}"><c:out value="0"></c:out></c:if>
						<c:if test="${y.downloads !=null}"><c:out value="${y.downloads }"></c:out></c:if>
						</td>
						<td><c:out value="${y.updatedate }"></c:out></td>
						</c:if>
						</c:forEach>
						<td align="center" >
						<a onclick="editsOne('${s.lyricsid }')"  style="text-decoration: underline;"><img src="img/crud/icon_edit.png" alt="Edit" /></a>  
                        <a  href="lyrics/remove.action?lyricsid=${s.lyricsid }&userid=${s.userid }&userlyricsid=${s.id}" style="text-decoration: underline;padding-left: 10px;"><img src="img/crud/icon_missing.png" alt="Delete" /></a> 
                        </td>

						</tr>
					</c:forEach>
				</c:if> 
			</tbody>
		</table>
       
	</form>
	<script type="text/javascript">
	/* function adds(){
      // $("#form").attr("action","PermissionsAction/toAdd").submit();
       //window.open("PermissionsAction/toAdd" ,"NewWindow","width=488,height=156,top=120,left=120" );
       top.dialog.open("PermissionsAction/toAdd");
	} */
	
	function adds(){ 
		var userid=$("#userid").val();
		art.dialog.open('lyrics/add?userid='+userid, {
	        id: 'pg123',
	        title: '添加',width:'600px',height:'450px',lock:true,resize: false,
	        ok: function () {
	            var iframe = this.iframe.contentWindow;
	            iframe.document.getElementById("btnsave").click();
	            return false;
	        },
	        cancel: true
	    });

		
    }
	function showtip2(str) {
	    art.dialog.tips(str, 3);
	    setTimeout('originreload()', 1000);
	}
	function originreload()
	{
	    var win = art.dialog.open.origin;//来源页面
	    win.location.reload();
	}
	
	function edits(){
		var userid=$("#userid").val();
		   var ids = $("input[name='checkbox']:checked");
           var length = ids.length;
           if(length<1){
        	   art.dialog.alert("请选择一个菜单");
        	   return;
           }else if(length>1){
        	   art.dialog.alert("只能同时编辑一个菜单");
        	   return;
           }else{
            var id = getCheckedIds('checkbox');
            art.dialog.open('lyrics/toEdit.action?id='+id+"&userid="+userid, {
    	        title: '编辑',width:'600px',height:'450px',lock:true,
    	        ok: function () {
    	            var iframe = this.iframe.contentWindow;
    	            iframe.document.getElementById("btnsave").click();
    	            return false;
    	        },
    	        cancel: true
    	    });
           //$("#form").attr("action","PermissionsAction/toEdit.action?id="+id).submit();
           }
		}
	
	
		function editsOne(id) {
			var userid=$("#userid").val();
			art.dialog.open('lyrics/toEdit.action?id=' + id+"&userid="+userid, {
				title : '编辑',
				width : '600px',
				height : '450px',lock:true,
				ok : function() {
					var iframe = this.iframe.contentWindow;
					iframe.document.getElementById("btnsave").click();
					return false;
				},
				cancel : true
			});
		}
		
		function removes() {
			var userid=$("#userid").val();
			var ids = $("input[name='checkbox']:checked");
			var length = ids.length;
			if (length < 1) {
				art.dialog.alert("请选择一个菜单");
				return;
			} else {
				ids = getCheckedIds('checkbox');
			}
			$("#form").attr("action", "lyrics/removes.action?ids=" + ids+"&userid="+userid)
					.submit();
		}

		/**
		 * 获取选中的ids
		 * @return 
		 */
		function getCheckedIds(checkboxName) {
			var ids = "";
			var checkboxs = $("input[name='" + checkboxName + "']:checked");
			var arr = [];
			$.each(checkboxs, function(i, param) {
				arr.push(param.value);
			});
			ids = arr.join(",");
			return ids;
		}
	</script>

</body>
</html>