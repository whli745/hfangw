<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%@ include file="../../../include/heard.inc"%>
		<script type="text/javascript" src="js/zDialog/zDialog.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				jQuery("#listTab1 tr:even").addClass("alt");
				jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
				initTableTitle('listTab1');
			})
		</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dd>
					<form name="myForm" method="get" action="queryLogActionDescList.action">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr>
							<td class="txtR">
								功能描述：
							</td>
							<td>
								<input type="text" name="sqlObj.actionDesc" value="<s:property value='sqlObj.actionDesc'/>" class="ipt_s" />
							</td>
							<td class="txtR">
								请求地址：
							</td>
							<td>
								<input type="text" name="sqlObj.actionUrl" value="<s:property value='sqlObj.actionUrl'/>" class="ipt_s" />
							</td>
						</tr>
						<tr>
							<td class="txtR">
								是否记录日志：
							</td>
							<td>
								<label style="cursor: pointer;"><input type="radio" name="sqlObj.useFlag" value='' <s:if test="sqlObj == null || sqlObj.useFlag == ''">checked</s:if> />全部</label>
									<label style="cursor: pointer;"><input type="radio" name="sqlObj.useFlag" value='<s:property value="@util.BaseParameter@NO"/>' <s:if test="sqlObj.useFlag == @util.BaseParameter@NO">checked</s:if> />记录</label>
									<label style="cursor: pointer;"><input type="radio"  name="sqlObj.useFlag" value='<s:property value="@util.BaseParameter@YES"/>' <s:if test="sqlObj.useFlag == @util.BaseParameter@YES">checked</s:if>/>不记录</label>
							</td>
							<td class="txtR">
							</td>
							<td>
							</td>
						</tr>
					</table>
					<div class="btnbarBig" >
						<a href="javascript:void(0)" class="btnG"  onclick="myForm.submit();">查询</a>
					</div>  
					
					</form>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
						<caption>
							<p class="title">系统功能描述管理</p>
							<p class="btnbars">
								<a href="initLogActionDesc.action?sqlObj.actionDesc=<s:property value='sqlObj.actionDesc'/>&sqlObj.actionUrl=<s:property value='sqlObj.actionUrl'/>"><img src="./images/ico_btn_plus.gif" />添加</a>
							</p>
						</caption>
						<tr>
							<td class="datatitle" width="8%">序号</td>
							<td class="datatitle">功能描述</td>
							<td class="datatitle" style="width: 25%">请求地址</td>
							<td class="datatitle" style="width: 15%">关键参数</td>
							<td class="datatitle" style="width: 10%">是否记录日志</td>
							<td class="datatitle" style="width: 18%">操作</td>
						</tr>			
						<s:iterator value="logActionDescList" status="st">
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td style="text-align: left;"><s:property value="actionDesc" /></td>
								<td style="text-align: left;"><s:property value="actionUrl" /></td>
								<td><s:property value="actionKey" /></td>
								<td><s:if test="useFlag == @util.BaseParameter@NO">记录</s:if><s:if test="useFlag == @util.BaseParameter@YES">不记录</s:if></td>
								<td>
									<a href='initLogActionDesc.action?oid=<s:property value="oid"/>&sqlObj.actionDesc=<s:property value="sqlObj.actionDesc"/>&sqlObj.actionUrl=<s:property value="sqlObj.actionUrl"/>'>修改</a>&nbsp;|&nbsp;
									<a href="javascript:void(0)" onclick="Dialog.confirm('确定要删除数据吗？',function(){window.location.href='deleteLogActionDesc.action?oid=<s:property value='oid'/>&sqlObj.actionDesc=<s:property value="sqlObj.actionDesc"/>&sqlObj.actionUrl=<s:property value="sqlObj.actionUrl"/>'},'','','','2')">删除</a>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="6" class="datatitle">
								<%@ include file="../../../include/pagination.inc" %>
							</td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
	</body>
</html>
