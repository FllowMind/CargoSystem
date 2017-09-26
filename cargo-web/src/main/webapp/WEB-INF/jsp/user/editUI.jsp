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
	<form action="<c:url value='/user/edit' />" method="post">
		<input type="hidden" name="id" value="${user.id }"  />
		用户名：<input type="text" name="username" value="${user.username }" /><br/>	
		已拥有角色：
		<c:forEach items="${userRoles }" var="userRole">
				${userRole.name },
		</c:forEach>
		<br/>
		<select name="ids" multiple="multiple">
			<c:forEach items="${roles }" var="role">
				<option value="${role.id }">${role.name }</option>
			</c:forEach>
		</select>
		<input type="submit" value="提交" />
	</form>
</body>
</html>