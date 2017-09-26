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
	<form action="<c:url value='/role/update' />" method="post">
		<input type="hidden" name="id" value="${role.id }"  />
		<input type="text" name="name" value="${role.name }" />	
		<textarea rows="10" cols="50" name="description">
			${role.description }
		</textarea>
		<input type="submit" value="提交" />
	</form>
</body>
</html>