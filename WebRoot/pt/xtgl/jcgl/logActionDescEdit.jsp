<%@ page language="java" pageEncoding="UTF-8"%>
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
		<script>
			function save(){
				var actionDesc=document.getElementById("actionDesc");
				if(actionDesc.value.length<=0){
					Dialog.alertFocus("功能描述不能为空！",'actionDesc');
					return false;
				}
				var actionUrl=document.getElementById("actionUrl");
				if(actionUrl.value.length<=0){
					Dialog.alertFocus("请求地址不能为空！",'actionUrl');
					return false;
				}
				Dialog.confirm('是否确定此操作？',function(){
					document.myForm.submit();
				});
			}
	</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dd>
					<form name="myForm" method="post" action="editLogActionDesc.action" enctype="multipart/form-data">
						<input type="hidden" name="sqlObj.actionDesc" value="<s:property value='sqlObj.actionDesc'/>" />
						<input type="hidden" name="sqlObj.actionUrl" value="<s:property value='sqlObj.actionUrl'/>" />
						<input type="hidden" name="logActionDesc.oid" value="<s:property value='logActionDesc.oid'/>"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
							<tr>
								<th colspan="4"><p class="title"><s:if test='logActionDesc!=null'>修改</s:if><s:else>新增</s:else></p></th>
							</tr>
							<tr>
								<td width="15%" class="txtR">功能描述：</td>
								<td width="35%">
									<input name="logActionDesc.actionDesc" id="actionDesc" class="ipt" 
										value="<s:property value='logActionDesc.actionDesc'/>" data-maxlength="200"/><font color="red">&nbsp;*</font>
								</td>
								<td width="15%" class="txtR">请求地址：</td>
								<td width="35%">
									<input name="logActionDesc.actionUrl" id="actionUrl" class="ipt" 
										value="<s:property value='logActionDesc.actionUrl'/>" data-maxlength="200"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">关键参数：</td>
								<td colspan="3">
									<input name="logActionDesc.actionKey" id="actionKey" class="ipt" 
										value="<s:property value='logActionDesc.actionKey'/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td class="txtR">是否记录日志：</td>
								<td colspan="3">
									<label style="cursor: pointer;"><input type="radio" name="logActionDesc.useFlag" value='<s:property value="@util.BaseParameter@NO"/>' <s:if test="logActionDesc == null || logActionDesc.useFlag == '' || logActionDesc.useFlag == @util.BaseParameter@NO">checked</s:if> />记录</label>
									<label style="cursor: pointer;"><input type="radio"  name="logActionDesc.useFlag" value='<s:property value="@util.BaseParameter@YES"/>' <s:if test="logActionDesc.useFlag == @util.BaseParameter@YES">checked</s:if>/>不记录</label>
								</td>
							</tr>
						</table>
						<div class="btnbarBig">
							<a href="javascript:void(0)" class="btnG" onclick="save()">确定</a>
							<a href="javascript:void(0)" class="btnO" onclick="back()">返回</a>
						</div>
					</form>
				</dd>
			</dl>
		</div>
	</body>
</html>
