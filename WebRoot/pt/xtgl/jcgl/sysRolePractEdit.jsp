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
		<%@ include file="../../../include/zTree.inc"%>
		<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
		<script type="text/javascript">
			function checkAll(){
				if(jQuery.trim(jQuery("#roleName").val())==""){
					Dialog.alertFocus('角色名称不能为空！', 'roleName');
					return false;
				 }
				setCheckedValues();
				Dialog.confirm('是否确定此操作？',function(){
					document.form1.submit();
				});
			}
			var settingRoles = {
				check: {enable: true},
				data: {simpleData: {enable: true}},
				view: {fontCss : {color:"black"}}
			};
			
			var zNodesRoles =[<s:property value='moduleJSONStr' escape='false' />];
			var code;
			
			function setCheckedValues(){
				var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
				var nodes = zTree.getCheckedNodes();
				var rids = document.getElementById("moduleIds");
				rids.value='';
				for(var i=0;i<nodes.length;i++){
					rids.value += nodes[i].id;
					if(i<nodes.length-1) rids.value += ";";
				}
			}
			jQuery(document).ready(function(){
				jQuery.fn.zTree.init(jQuery("#treeDemo"), settingRoles, zNodesRoles);
			});
		
	</script>
	</head>

	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>政府采购</a><a>企业管理</a><a href="querySysRolePractList.action">角色管理</a><a><s:if test="sysRolePract==null">添加</s:if><s:else>修改</s:else></a>
					</p>
					<a href="querySysRolePractList.action" class="back">返回</a>
				</dt>
				<dd>
					<form name="form1" action="editSysRolePract.action" method="post">
						<input type="hidden" name="moduleIds" id="moduleIds" />
						<s:if test="sysRolePract==null">
							<input type="hidden" name="sysRolePract.memberId"	value="<s:property value="memberId"/>" />
						</s:if>
						<s:else>
							<input type="hidden" name="sysRolePract.roleId"	value='<s:property value="sysRolePract.roleId"/>' />
							<input type="hidden" name="sysRolePract.memberId" value='<s:property value="sysRolePract.memberId"/>' />
						</s:else>
						
						
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">
									<s:if test="sysRolePract==null">添加</s:if><s:else>修改</s:else>角色</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">角色名称：</td>
								<td width="40%" >
									<input id="roleName" class="ipt" size="40"	name="sysRolePract.roleName" value="<s:property value="sysRolePract.roleName"/>" /><font color="red">&nbsp;*</font>
								</td>
								<td width="15%" class="txtR" rowspan="6">拥有模块：</td>
								<td rowspan="6" width="30%" valign="top" style="padding-top: 10px;">
									<ul id="treeDemo" class="ztree"	style="width:320px; overflow:auto; height: 220px;" ></ul>
								</td>
							</tr>
							<tr>
								<td class="txtR">角色状态：</td>
								<td>
									<s:iterator value="@util.BaseParameter@STATUS" >
										<label>
											<input type="radio" name="sysRolePract.roleStatus" value="<s:property value="key"/>"
												<s:if test="key.equals(sysRolePract.roleStatus)">checked</s:if> 
												<s:if test="sysRolePract==null&&key.equals(@util.BaseParameter@STATUS_ENABLE)">checked</s:if>/>
											<s:property value="value" />
										</label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td class="txtR">角色描述：</td>
								<td style="height:180px;"><textarea class="input_area" name="sysRolePract.roleDesc" style="height:100%;"><s:property value="sysRolePract.roleDesc" escape="false"/></textarea></td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(null)" onclick="checkAll();" class="btnG" <s:if test='loginMember==null'>disabled</s:if>>确定</a>
						<a href="querySysRolePractList.action" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
