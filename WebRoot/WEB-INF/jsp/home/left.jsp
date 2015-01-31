<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title>导航菜单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="JavaScript" src="script/jquery.js"></script>
		<script language="JavaScript" src="script/menu.js"></script>
		<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
	</head>
	<body style="margin: 0">
		<div id="Menu">
			<ul id="MenuUl">
			    <s:iterator value="#application.topPrivilegeList">
					<s:if test="#session.loginUser.hasPrivilegeName(name)">
						<li class="level1">
							<div onClick="menuClick(this)" class="level1Style">
							<!-- 修改图标同步 -->
								<img
									src="${pageContext.request.contextPath}/style/images/MenuIcon/${id}.gif"
									class="Icon" />
								${name }
							</div>
							<ul style="display: none;" class="MenuLevel2">
								   <s:iterator value="children">
							 		 <s:if test="#session.loginUser.hasPrivilegeName(name)">
										<li class="level2">
											<div class="level2Style">
												<img
													src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" />
													<%--<s:a action="%{url}" namespace="/" target="right">${name }</s:a>--%>
													<a href="${pageContext.request.contextPath}${url}.action" target="right">${name }</a>
											</div>
										</li>
								      </s:if>
									</s:iterator>
							</ul>
						</li>
					  </s:if>
				</s:iterator>
			</ul>
		</div>
	</body>
</html>

