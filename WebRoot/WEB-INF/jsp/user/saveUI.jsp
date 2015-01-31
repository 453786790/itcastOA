<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>人员添加/修改</title>
		<%@ include file="/WEB-INF/jsp/public/head.jsp" %>
		<script type="text/javascript">
			$(function(){
				//为loginName输入框绑定失去焦点事件
					$("#loginName").blur(function(){
						//通过Ajax发送登录名到Action进行校验
						$.post('${pageContext.request.contextPath}/user_findLoginNameByAjax.action',{'loginName':$(this).val()},function(data){
								if(data == '1'){
									//登录名存在
									$("#showMsg").html("<font color='red'>此用户名已经存在！</font>");
								}else{
									//登录名不存在
									$("#showMsg").html("<font color='green'>此用户名可以使用！</font>");
								}
						});
					});
			});
		</script>
	</head>
	<body>
 
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>
 
<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_%{id!=null?'edit':'add'}" namespace="/" theme="simple">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        <s:select list="departmentList" listKey="id" listValue="name" headerKey="" headerValue="请选择" name="departmentId" cssClass="SelectStyle"></s:select>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td>
                        <s:textfield name="loginName" cssClass="InputStyle" id="loginName"></s:textfield>
                        *
							（登录名要唯一）
							<div id="showMsg"></div>
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td>
                          <s:textfield name="name" cssClass="InputStyle"></s:textfield>
                        *</td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                          <s:radio list="#{'1':'男','0':'女'}" name="gender"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td>
                          <s:textfield name="phoneNumber" cssClass="InputStyle"></s:textfield>
                        </td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td>
                          <s:textfield name="email" cssClass="InputStyle"></s:textfield>
                        </td>
                    </tr>
                    <tr><td>备注</td>
                        <td>
                          <s:textfield name="description" cssClass="TextareaStyle"></s:textfield>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>
                            <s:select name="roleIds" list="roleList" listKey="id" listValue="name" multiple="true" size="10" cssClass="SelectStyle">
                            
                            
                            </s:select>
                            按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
 
<div class="Description">
	说明：<br />
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改用户信息时，登录名不可修改。
</div>
 
</body>
</html>
