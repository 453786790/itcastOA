<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>岗位列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		<table>
			<tr>
				<th>
					名称
				</th>
				<th>
					说明
				</th>
				<th>
					操作
				</th>
			</tr>
			<s:iterator value="list">
				<tr>
					<td>
						${name }
					</td>
					<td>
						${description }
					</td>
					<td>
						<s:a action="role_delete?id=%{id}" namespace="/" onclick="javascript:return  confirm('确认是否删除当前记录')">删除</s:a>
						<s:a action="role_editUI?id=%{id}" namespace="/">修改</s:a>
						<s:a action="role_setPrivilege" namespace="/">设置权限</s:a>
					</td>
				</tr>
			</s:iterator>
			<tr>
			  <td>
			  <input type="button" value="新建" onclick="javascript:window.location.href='${pageContext.request.contextPath}/role_addUI.action'"/>
			  </td>
			</tr>
		</table>
	</body>
</html>
