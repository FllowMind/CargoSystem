<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>test</title>
</head>
<body> 
	<form action="<c:url value='/login.action' />" method="post">
		用户名：<input type="text" name="username" /><br/>
		密码：<input type="password" name="password" /><br/>
		<input type="submit" value="提交" />
	</form>
</body>
</html>