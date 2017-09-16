<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<%@ include file="../../../include/heard.inc"%>
		<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript">
		var setting = {
			check: {enable: true},
			data: {simpleData: {enable: true}}
		};
		
		var zNodes = [<s:property value='moduleJSONStr' escape='false' />];
		
		jQuery(document).ready(function(){
			jQuery.fn.zTree.init(jQuery("#treeDemo"), setting, zNodes);
		});
		</script>
	</head>

	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：
						<a>系统管理</a><a>基础管理</a><a href="querySysRoleList.action">角色管理</a><a>查看</a>
					</p>
					<a href="querySysRoleList.action" class="back">返回</a>
				</dt>
				<dd>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formTab">
						<tr>
							<th colspan=4><p class="title">查看角色</p></th>
						</tr>
						<tr>
							<td width="15%" class="txtR">角色名称：</td>
							<td>
								<s:property value="sysRole.roleName"/>
							</td>
							<td width="15%" class="txtR" rowspan="4">拥有模块：</td>
							<td rowspan="6" width="30%">
								<ul id="treeDemo" class="ztree"	style="width:320px; overflow:auto; height: 320px;" ></ul>
							</td>
						</tr>
						<tr>
							<td class="txtR">所属区划：</td>
							<td><s:property value="sysRole.sysArea.areaName" /></td>
						</tr>
						<tr>
							<td class="txtR">角色状态：</td>
							<td><s:property value="@util.BaseParameter@STATUS[sysRole.roleStatus]" /></td>
						</tr>
						<tr style="height:240px">
							<td class="txtR">角色描述：</td>
							<td><s:property value="sysRole.roleDesc" escape="false"/></td>
						</tr>
					</table>
					<div class="btnbarBig">
						<a href="querySysRoleList.action" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
	</body>
</html>
