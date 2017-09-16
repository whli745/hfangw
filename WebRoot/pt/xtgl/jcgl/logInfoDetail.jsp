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
	</head>

	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dd>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr>
							<th colspan="4"><p class="title">查看日志</p></th>
						</tr>
						<tr>
							<td width="15%" class="txtR">操作人：</td>
							<td width="35%"><s:property value="logInfo.userName" /></td>
							<td width="15%" class="txtR">操作时间：</td>
							<td width="35%"><s:date name="logInfo.actTime" format="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<tr>
							<td class="txtR">操作IP：</td>
							<td><s:property value="logInfo.ip" /></td>
							<td class="txtR">所属角色：</td>
							<td>
								<s:iterator value="logInfo.sysRoleList" status="idx">
									<s:if test="#idx.index==0"><s:property value="roleName" /></s:if>
									<s:else>,<s:property value="roleName" /></s:else>
								</s:iterator>
								<s:if test="logInfo.sysRoleList==null||logInfo.sysRoleList.size()==0">无相关信息</s:if>
							</td>
						</tr>
						<tr>
							<td class="txtR">所属区划：</td>
							<td><s:property value="logInfo.sysArea.areaName" /></td>
							<td class="txtR">业务类型：</td>
							<td>
								<s:iterator value="logInfo.sysDictList" status="idx">
									<s:if test="#idx.index==0"><s:property value="dictName" /></s:if>
									<s:else>,<s:property value="dictName" /></s:else>
								</s:iterator>
								<s:if test="logInfo.sysDictList==null||logInfo.sysDictList.size()==0">无相关信息</s:if>
							</td>
						</tr>
						<tr>
							<td class="txtR">操作内容：</td>
							<td colspan="3" style="width: 85px;"><s:property value="logInfo.actText" /></td>
						</tr>
						<tr>
							<td class="txtR">用户类型：</td>
							<td colspan="3" style="width: 85px;">
								<s:if test="logInfo.userType == @util.BaseParameter@NO">无用户 </s:if>
								<s:elseif test="logInfo.userType == @util.BaseParameter@YES">平台用户 </s:elseif>
								<s:elseif test="logInfo.userType == @util.BaseParameter@WORK_TYPESETTING_2">中介机构 </s:elseif>
								<s:elseif test="logInfo.userType == @util.BaseParameter@NOTICE_TYPE_3">项目单位 </s:elseif>
							</td>
						</tr>
						<tr>
							<td class="txtR">访问地址：</td>
							<td colspan="3" style="width: 85px;">
								<s:property value="logInfo.actionUrl" />
							</td>
						</tr>
					</table>
					<div class="btnbarBig">
						<a href="javascript:void(0)" class="btnO" onclick="back()">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
