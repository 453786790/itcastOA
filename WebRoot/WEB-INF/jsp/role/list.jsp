<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title>岗位列表</title>
		<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
	</head>
	<body>

		<div id="Title_bar">
			<div id="Title_bar_Head">
				<div id="Title_Head"></div>
				<div id="Title">
					<!--页面标题-->
					<img border="0" width="13" height="13"
						src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
					岗位管理
				</div>
				<div id="Title_End"></div>
			</div>
		</div>

		<div id="MainArea">
			<table cellspacing="0" cellpadding="0" class="TableStyle">

				<!-- 表头-->
				<thead>
					<tr align="CENTER" valign="MIDDLE" id="TableTitle">
						<td width="200px">
							岗位名称
						</td>
						<td width="300px">
							岗位说明
						</td>
						<td>
							相关操作
						</td>
					</tr>
				</thead>

				<!--显示数据列表-->
				<tbody id="TableData" class="dataContainer">
					<s:iterator value="list">
						<tr class="TableDetail1 template">
							<td>
								${name }
							</td>
							<td>
								${description }
							</td>
							<td>
								<s:if test="#session.loginUser.hasPrivilegeName('岗位删除')">
									<s:a action="role_delete?id=%{id}" namespace="/"
										onclick="javascript:return  confirm('确认是否删除当前记录')">删除</s:a>
								</s:if>
								<s:if test="#session.loginUser.hasPrivilegeName('岗位修改')">
									<s:a action="role_editUI?id=%{id}" namespace="/">修改</s:a>
								</s:if>
								<s:if test="#session.loginUser.hasPrivilegeName('设置权限')">
									<s:a action="role_setPrivilegeUI?id=%{id}" namespace="/">设置权限</s:a>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

			<!-- 其他功能超链接 -->
			<div id="TableTail">
				<div id="TableTail_inside">
					<s:if test="#session.loginUser.hasPrivilegeName('岗位添加')">
						<a
							href="javascript:window.location.href='${pageContext.request.contextPath}/role_addUI.action'">
							<img
								src="${pageContext.request.contextPath}/style/images/createNew.png" />
						</a>
					</s:if>
				</div>
			</div>
		</div>
	</body>
</html>
