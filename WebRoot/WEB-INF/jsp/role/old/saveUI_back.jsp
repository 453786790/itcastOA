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

		<title>添加岗位</title>
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

		<s:form action="role_add" namespace="/" theme="simple">
			<table>
				<tr>
					<td>
						岗位名称

					</td>

					<td>
						<s:textfield name="name">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						岗位描述
					</td>
					<td>
						<s:textarea cols="30" rows="4">
						</s:textarea>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td>
					<input type="button" value="返回" onclick="window.history.go(-1);"/>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
