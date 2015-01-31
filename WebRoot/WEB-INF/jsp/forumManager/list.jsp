<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>版块列表</title>
    <%@ include file="/WEB-INF/jsp/public/head.jsp"%>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
 
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">版块名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
            </tr>
        </thead>
 
		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:iterator value="recordList" status="s">
			<tr class="TableDetail1 template">
			<s:property value=""/>
			<s:property value="s.last"/>
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
				    <s:a action="forumManage_delete.action?id=%{id}" onclick="return confirm('确定删除当前记录么?')">删除</s:a>
				    <s:a action="forumManage_editUI.action?id=%{id}" namespace="/">修改</s:a>
				    <s:if test="#s.first">
				                    上移
				    </s:if>
				    <s:else>
				    	<s:a action="forumManage_moveUp.action?id=%{id}" namespace="/">上移</s:a>
				    </s:else>
				    <s:if test="#s.last">
				       	下移
				    </s:if>
				    <s:else>
					    <s:a action="forumManage_moveDown.action?id=%{id}" namespace="/">下移</s:a>
				    </s:else>
				</td>
			</tr>
		</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
           <s:a action="forumManage_addUI.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
<s:form action="forumManage_list" namespace="/" id="pageForm">

</s:form>
 <%@ include file="/WEB-INF/jsp/public/pageView.jsp" %>
<div class="Description">
	说明：<br />
	1，显示的列表按其sortOrder值升序排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>
</body>
</html>


