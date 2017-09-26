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
	<table>
		<thead>
			<tr>
				<th>序号</th>
				<th>角色名称</th>
				<th>角色描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roles }" var="role" varStatus="status">
				<tr>					
					<td>
						${status.index + 1 }
					</td>
					<td>
						${role.name }
					</td>
					<td>
						${role.description }
					</td>
					<td>
						<a href="<c:url value='/role/assignUI?roleId=${role.id }' />">分配权限</a>
						<a href="<c:url value='/role/detail?id=${role.id }' />">修改</a>
						<a href="<c:url value='/role/delete?id=${role.id }' />">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
</body>
</html>