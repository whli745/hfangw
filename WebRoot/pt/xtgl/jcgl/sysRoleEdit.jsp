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
		<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
		<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
		<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
		<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		<script type='text/javascript' src='dwr/interface/AreaManageDwr.js'></script>
		<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>
		<script type="text/javascript">
			function checkAll(){
				if(jQuery.trim(jQuery("#roleName").val())==""){
					Dialog.alertFocus('角色名称不能为空！', 'roleName');
					return false;
				 }
				 if(jQuery.trim(jQuery("#areaName").val())==""){
					Dialog.alertFocus('请选择所属区划！', 'areaName');
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
		
		//区划树
		var zTree1;
		var setting;
	    var isGetNodes = false;
		var zNodes =<s:property value="sysAreaJson" escape="false"/>;
		// 控制按钮显示
		setting = {
			expandSpeed: "",
			callback: {
				beforeExpand: zTreeBeforeExpand,//展开树
				beforeCollapse: zTreeBeforeCollapse,//合并数
				onClick: treeOnClick,//点击树的节点
				onDblClick: treeOnDblClick
			},
		};
	
		jQuery(document).ready(function(){
			zTree1 = jQuery.fn.zTree.init(jQuery('#tree_area'), setting, zNodes);
			var nodes = zTree1.getNodes();
			zTree1.selectNode(nodes[0]);
			zTree1.expandNode(nodes[0], true, null, null, true);
		});
		var isGetNodes = false;
		var curParentNode = null;
		function zTreeBeforeExpand(treeId, treeNode) {
			if (!isGetNodes && treeNode.isParent && (!treeNode.nodes || treeNode.nodes.length==0)) {
				curParentNode = treeNode;
				isGetNodes = true;
				var id = curParentNode.id;
			    AreaManageDwr.areaTreeList(id, function(msg){//调用区划DWR返回区划Json
				if (!msg || msg.length == 0) {
						isGetNodes = false;
						return;
					}
					var newNodes = "";
					try {
						newNodes = eval("(" + msg + ")");
					} catch(err) {}
					if (newNodes && newNodes != "") {
						zTree1.addNodes(curParentNode, newNodes, true);
						zTree1.updateNode(curParentNode);
						zTree1.selectNode(zTree1.getNodeByParam("id", curParentNode.id, null));
						zTree1.expandNode(curParentNode);
						isGetNodes = false;
					}
			});
				return false;
			}
			return true;
		}
		
		function zTreeBeforeCollapse(treeId, treeNode) { // 合并树
			zTree1.removeChildNodes(treeNode);
			treeNode.isParent=true;
			zTree1.expandNode(treeNode);
		}
		
		function treeOnClick(event, treeId, treeNode) {//点击树的节点
			if(!treeNode.open) {//展开
				zTreeBeforeExpand(treeId, treeNode);
			} else {//合并 
				zTreeBeforeCollapse(treeId, treeNode);
				zTree1.expandNode(treeNode);
			}
		}
		
		function treeOnDblClick(event, treeId, treeNode) {//双击节点完成选择
			jQuery("#areaId").val(treeNode.id)
			jQuery("#areaName").val(treeNode.name)
			jQuery("#m-areaTree").click();
		}
	</script>
	</head>

	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysRoleList.action">角色管理</a><a><s:if test="sysRole==null">添加</s:if><s:else>修改</s:else></a>
					</p>
					<a href="querySysRoleList.action?areaId=<s:property value='areaId' />&areaName=<s:property value='areaName' />" class="back">返回</a>
				</dt>
				<dd>
					<form name="form1" action="editSysRole.action" method="post">
						<input type="hidden" name="moduleIds" id="moduleIds" />
						<input type="hidden" name="sysRole.roleId"	value="<s:property value="sysRole.roleId"/>" />
						<input type="hidden" name="areaId" value="<s:property value='areaId'/>"/>
						<input type="hidden" name="areaName" value="<s:property value='areaName'/>"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">
									<s:if test="sysRole==null">添加</s:if><s:else>修改</s:else>角色	</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">角色名称：</td>
								<td width="40%" >
									<input id="roleName" class="ipt" size="40"	name="sysRole.roleName" 
										value="<s:property value="sysRole.roleName"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
								<td width="15%" class="txtR" rowspan="4">拥有模块：</td>
								<td rowspan="6" width="30%" valign="top" style="padding-top: 10px;">
									<ul id="treeDemo" class="ztree"	style="width:320px; overflow:auto; height: 300px;" ></ul>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属区划：</td>
								<td>
									<input type="text" class="input_text" id="areaName" value="<s:property value='sysRole.sysArea.areaName'/>" readonly="readonly"/><font color="red">&nbsp;*</font>
						        	<input type="hidden" class="input_text" id="areaId" name="sysRole.areaId" value="<s:property value='sysRole.areaId'/>"/>
						        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
									<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
										<div class="ydlg-hd">区划选择器[双击选中]</div>
										<div class="ydlg-bd">
											<ul id="tree_area" class="ztree" ></ul>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="txtR">角色状态：</td>
								<td>
									<s:iterator value="@util.BaseParameter@STATUS" >
										<label>
											<input type="radio" name="sysRole.roleStatus" value="<s:property value="key"/>"
												<s:if test="key.equals(sysRole.roleStatus)">checked</s:if> 
												<s:if test="sysRole==null&&key.equals(@util.BaseParameter@STATUS_ENABLE)">checked</s:if>/>
											<s:property value="value" />
										</label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td class="txtR">角色描述：</td>
								<td style="height:230px;">
									<textarea style="height: 220px;" class="input_area" id="roleDesc" name="sysRole.roleDesc" data-maxlength="100"><s:property value="sysRole.roleDesc" escape="false"/></textarea></td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(null)" onclick="checkAll();" class="btnG">确定</a>
						<a href="querySysRoleList.action?areaId=<s:property value='areaId' />&areaName=<s:property value='areaName' />" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
		<script type="text/javascript">
			var btnIds = ['m-areaTree']; //按钮名称
			var tanchuDivs = ['tree-area']; //展示div名称
			for ( var i = 0; i < btnIds.length; i++) {
				var td = new HelloWorld(btnIds[i], tanchuDivs[i]);
				YAHOO.ext.EventManager.onDocumentReady(td.init, td, true);
			}
		</script>
	</body>
</html>
