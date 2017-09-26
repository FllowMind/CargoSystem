<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
</head>
<body>
	<form action="<c:url value='/role/assign' />" method="post">
		<input type="hidden" value="${param.roleId }" name="roleId" />
		已拥有权限：
		<c:forEach items="${existPermissions }" var="existPermission">
			${existPermission.name }
		</c:forEach>
		<br/>
		<select name="ids" multiple="multiple">
			<c:forEach items="${permissions }" var="permission">
				<option value="${permission.id }">${permission.name }</option>
			</c:forEach>
		</select>
		<input type="submit" value="提交" />
	</form>
</body>
</html>