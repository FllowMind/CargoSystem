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
	<form action="<c:url value='/user/add' />" method="post">
		用户名：<input type="text" name="username" />
		密码：<input type="password" name="password" />
		<select name="ids" multiple="multiple">
			<c:forEach items="${roles }" var="role">
				<option value="${role.id }">${role.name }</option>
			</c:forEach>
		</select>
		<input type="submit" value="提交" />
	</form>
</body>
</html>