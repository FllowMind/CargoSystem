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
	<form action="<c:url value='/permission/update' />" method="post">
		<input type="hidden" name="id" value="${permission.id }"  /><br/>
		<input type="text" name="name" value="${permission.name }" /><br/>
		<input type="text" name="percode" value="${permission.percode }" />	
		<input type="submit" value="提交" />
	</form>
</body>
</html>