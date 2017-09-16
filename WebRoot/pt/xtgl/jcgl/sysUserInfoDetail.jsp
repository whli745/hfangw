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
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysUserList.action">用户管理</a><a>查看</a>
					</p>
					<a class="back" onclick="history.go(-1)">返回</a>
				</dt>
				<dd>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr>
							<th colspan="4"><p class="title">查看</p></th>
						</tr>
						<tr>
							<td width="15%" class="txtR">用户账号：</td>
							<td width="35%"><s:property value="sysUser.userCode" /></td>
							<td width="15%" class="txtR">用户名称：</td>
							<td width="35%"><s:property value="sysUser.userName" /></td>
						</tr>
						<tr>
							<td class="txtR">性别：</td>
							<td>
								<s:iterator value="@util.BaseParameter@PERSON_SEX">
									<s:if test="sysUser.userSex==key"><s:property value="value" /></s:if>
								</s:iterator>
							</td>
							<td class="txtR">出生年月：</td>
							<td><s:property value="sysUser.userBirthday" /></td>
						</tr>
						<tr>
							<td class="txtR">职务：</td>
							<td><s:property value="sysUser.sysPost.dictName" /></td>
							<td class="txtR">手机：</td>
							<td><s:property value="sysUser.userPhone" /></td>
						</tr>
						<tr>
							<td class="txtR">电话：</td>
							<td><s:property value="sysUser.userTel" /></td>
							<td class="txtR">邮箱：</td>
							<td><s:property value="sysUser.userEmail" /></td>
						</tr>
						<tr>
							<td class="txtR">序号：</td>
							<td><s:property value="sysUser.userSort" /></td>
							<td class="txtR">所属区划：</td>
							<td><s:property value="sysUser.sysArea.areaName" /></td>
						</tr>
						<tr>
							<td class="txtR">所属机构：</td>
							<td><s:property value="sysUser.sysOrgan.organName" /></td>
							<td class="txtR">所属组：</td>
							<td >
								<s:iterator value="userRoleList">
									<s:property value='sysRole.roleName'/>
								</s:iterator>
							</td>
						</tr>
					</table>
					<div class="btnbarBig">
						<a href="javascript:void(0)" class="btnO" onclick="history.back()">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
