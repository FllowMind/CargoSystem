<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户列表</title>
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
</head>
<body>
   	<div id="tree"></div>
   	<script type="text/javascript">
   		$tree = $("#tree");
		<c:forEach items="${permissions }" var="permission">
			<c:if test="${permission.layer == 1}">
				$tree.append("<ul id='node_${permission.layer}'><li><input type='checkbox' name='permissionIds' value='${permission.id}'/>${permission.name}</li></ul>");
			</c:if>
			<c:if test="${permission.layer > 1}">
				var $li = $("#node_${permission.layer-1}").children(":last");
				if($li.find('ul').length != 0) {
					$li.children(":ul").append("<li><input type='checkbox' name='permissionIds' value='${permission.id}'/>${permission.name}</li>");
				}else{
					$li.append("<ul id='node_${permission.layer}'><li><input type='checkbox' name='permissionIds' value='${permission.id}'/>${permission.name}</li></ul>");
				}
			</c:if>
		</c:forEach>
	</script>
	<script type="text/javascript">
   		$("#tree").treeview();
   	</script>
   	<script type="text/javascript">
		$(function(){
			// 指定事件处理函数
			$("[name=permissionIds]").click(function(){
				// 当选中或取消一个权限时，也同时选中或取消所有的下级权限
				$(this).siblings("ul").find("input").attr("checked", this.checked);
				// 当选中一个权限时，也要选中所有的直接上级权限
				if(this.checked == true){
					$(this).parents("li").children("input").attr("checked", true);
				}
			});
		});
	</script>
</body>
</html>