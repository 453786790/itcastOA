<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   	<!--分页信息-->
		<div id=PageSelectorBar>
			<div id=PageSelectorMemo>
				页次：${currentPage }/${pageCount }页 &nbsp;
				每页显示：${pageSize }条 &nbsp;
				总记录数：${recordCount }条
			</div>
			<div id=PageSelectorSelectorArea>
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
				-->
				<a href="javascript:gotoPageNum(1)" title="首页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
				<s:iterator begin="beginPageIndex" end="endPageIndex" var="s">
				&nbsp;
				    <s:if test="#s==currentPage">
						<span style="cursor: hand;" >
						  <s:property value="#s"/>
						</span>
				    </s:if>
				    <s:else>
						<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(<s:property value="#s"/>);" >
						  <s:property value="#s"/>
						</span>
				    </s:else>
				</s:iterator>
				&nbsp;
				<a href="#" onclick="gotoPageNum(${pageCount})" title="尾页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>
				
				转到：
				<select id="pages" onchange="gotoPageNum(this.value)">
				<s:iterator begin="1" end="pageCount" status="s" >
					<option value='<s:property value="#s.count"/>'>
						<s:property value="#s.count"/>
					</option>
				</s:iterator>
				</select>
			</div>
		</div>
		<script type="text/javascript">
			function gotoPageNum(currentPage){
				$("#pageForm").append('<input type="hidden" name="currentPage" id="currentPage" value="'+currentPage+'"/>');
				$("#pageForm").submit();
				}
			$("#pages").val(${currentPage});//下拉框数据回显
		</script>
