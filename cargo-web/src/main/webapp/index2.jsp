<%@ page language="Java"  pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>i18n</title>
</head>
<body>
    
	${aa }:${dd }
	<br> 
	<spring:message code="i18n.username" /> 
    <br>  
    <spring:message code="i18n.password" /> 
    <br>
    <spring:message code="i18n.aaa" /> 
    	
   
</body>
</html>