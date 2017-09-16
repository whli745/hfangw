<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  	<head>
    <base href="<%=basePath%>"/>
    <%@ include file="../../../include/heard.inc"%>
    <script type="text/javascript">
		jQuery(document).ready(function(){
			jQuery("#listTab1 tr:even").addClass("alt");
			jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
			initTableTitle('listTab1');
		});
	</script>
 	</head>
 	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dd>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
						<caption>
							<p class="title">工作台管理</p>
							<p class="btnbars_more"><a href="querySysWorkbenchList.action" target="_parent"><img src="images/more.gif" /></a></p>
						</caption>
						<tr>
							<td class="datatitle" width="10%">序号</td>
							<td class="datatitle" style="width: 15%">所属区划</td>
							<td class="datatitle" style="width: 24%">所属角色</td>
							<td class="datatitle" style="width: 15%">模块名称</td>
							<td class="datatitle" style="width: 15%">是否默认</td>
							<td class="datatitle" style="width: 16%">当前状态</td>
						</tr>	
						<script>noticerow=1</script>		
						<s:iterator value="sysWorkbenchList" var="sysWorkbench" status="st">
						<script>noticerow++;</script>
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td><s:property value="sysArea.areaName" /></td>
								<td><s:property value="sysRole.roleName" /></td>
								<td><s:property value="workbenchName" /></td>
								<td>
									<s:iterator value="@util.BaseParameter@WORK_BENCH">
										<s:if test="key==workbenchDefault"><s:property value="value" /></s:if>
									</s:iterator>
								</td>
								<td>
									<s:iterator value="@util.BaseParameter@STATUS">
										<s:if test="key==workbenchUseFlag"><s:property value="value" /></s:if>
									</s:iterator>
								</td>
							</tr>	
						</s:iterator>
						<script>
	                          while(noticerow <= 8){
	                          	 document.write('<tr>');
								 document.write('<td class="box"> </td><td> </td><td > </td><td> </td><td> </td><td> </td>');
		                         document.write('</tr>');
		                         noticerow++;   
		                       }
						</script>
					</table>
				</dd>
			</dl>
		</div>
	</body>
</html>
