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
		<script>
			function save(){
				var workbenchName=jQuery('#workbenchName');
			    var workbenchUrl=jQuery('#workbenchUrl');
			    var workbenchSystem=jQuery('#workbenchSystem');
				var workbenchModule = jQuery('#workbenchModule');
				
				if(jQuery.trim(workbenchName.val())==''){
					Dialog.alertFocus("模块名称不能为空！",'workbenchName');
					return false;
				}
				if(jQuery.trim(workbenchUrl.val())==''){
					Dialog.alertFocus("请求地址不能为空！",'workbenchUrl');
					return false;
				}
				if(workbenchSystem.val()==0){
					Dialog.alertFocus("所属角色不能为空！",'workbenchSystem');
					return false;
				}
				
				// 所属菜单
				if(!workbenchModule.val()){
					Dialog.alertFocus("所属菜单不能为空！",'workbenchModule');
					return false;
				}
				var roles = document.getElementsByName("sysWorkbench.workbenchSystem");
				var flag = false;
				for(var i = 0; i < roles.length; i++) {
					if(roles[i].checked) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					Dialog.alert("请选择角色！");
					return false;
				}
				
				var checkBoxs = document.getElementsByName("sysWorkbench.workbenchSystem");
				var roleNames = '';
				for(var i = 0; i < checkBoxs.length; i++) {
					if(checkBoxs[i].checked) {
						roleNames += checkBoxs[i].id + ", ";
					}
				}
				document.getElementById("sysWorkbench.systemName").value=roleNames.substring(0,roleNames.length-2);
				
				// 排序号
				var orderBy = document.getElementById("orderBy").value;
				if (orderBy && !/^\d+$/.test(orderBy)) {
					Dialog.alert("排序号必须为整数！");
					return false;
				}
				
				Dialog.confirm("确定提交吗？",function(){
					document.myForm.submit();
				});
			}
			
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
				}
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
				queryRoleList(treeNode.id);
			}
			
			function queryRoleList(areaId) {
			// 联动查询区划下所有权限组
			PtSynthetizeAppDwr.queryRoleByAreaId(areaId, function(sysRole) {
				var selRoles = "<s:property value='sysWorkbench.workbenchSystem'/>";
				var selRoleList = '';
				if(null != selRoles && "" != selRoles) {
					selRoleList = selRoles.split(",");
				}
				var roles = '';
				for(var i = 0; i < sysRole.length; i++) {
					if(selRoleList == '') {
						roles += '<div style="margin-top: 5px;float:left; width:160px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" id="'+sysRole[i].roleName+'" name="sysWorkbench.workbenchSystem" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
					} else {
						var flag = '0';
						for(var j = 0; j < selRoleList.length; j++) {
							if(jQuery.trim(selRoleList[j]) == sysRole[i].roleId) {
								roles += '<div style="margin-top: 5px;float:left; width:160px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" id="'+sysRole[i].roleName+'" checked="checked" name="sysWorkbench.workbenchSystem" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
								flag = '1';
							}
						}
						if(flag == '0') {
							roles += '<div style="margin-top: 5px;float:left; width:160px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" id="'+sysRole[i].roleName+'" name="sysWorkbench.workbenchSystem" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
						}
					}
				}
				jQuery("#roleTD").html(roles);
			});
		}
	</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysWorkbenchList.action">自定义界面</a><a><s:if test="sysWorkbench!=null">修改</s:if><s:else>新增</s:else></a>
					</p>
					<a href="javascript:history.back()" class="back">返回</a>
				</dt>
				<dd>
					<form name="myForm" action="editSysWorkbench.action" method="post">
						<input type="hidden" name="sysWorkbench.workbenchId" value="<s:property value='sysWorkbench.workbenchId'/>" />
						<input type="hidden" id="sysWorkbench.systemName" name="sysWorkbench.systemName" value="<s:property value='sysWorkbench.systemName'/>" />
						<input type="hidden" name="areaId" value="<s:property value='areaId'/>"/>
						<input type="hidden" name="areaName" value="<s:property value='areaName'/>"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
							<tr>
								<th colspan="4"><p class="title"><s:if test='sysWorkbench!=null'>修改</s:if><s:else>新增</s:else></p></th>
							</tr>
							<tr>
								<td class="txtR" style="width: 15%;">模块名称：</td>
								<td class="txtL" style="width: 35%;">
									<input id="workbenchName" name="sysWorkbench.workbenchName" class="ipt" type="text" 
										value="<s:property value='sysWorkbench.workbenchName'/>" data-maxlength="100"/>
									<font color="red">*</font>
								</td>
								<td class="txtR" style="width: 15%;">请求地址：</td>
								<td class="txtL" style="width: 35%;">
									<input id="workbenchUrl" name="sysWorkbench.workbenchUrl" class="ipt" type="text" 
										value="<s:property value="sysWorkbench.workbenchUrl"/>" data-maxlength="150"/>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属区划：</td>
								<td >
									<input type="text" class="ipt_s" id="areaName" value="<s:property value='sysWorkbench.sysArea.areaName'/>" readonly="readonly"/><font color="red">&nbsp;*</font>
						        	<input type="hidden" class="input_text" id="areaId" name="sysWorkbench.areaId" value="<s:property value='sysWorkbench.areaId'/>"/>
						        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
									<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
										<div class="ydlg-hd">区划选择器[双击选中]</div>
										<div class="ydlg-bd">
											<ul id="tree_area" class="ztree" ></ul>
										</div>
									</div>
								</td>
								<td class="txtR">排版形式：</td>
								<td>
									<s:iterator value="@util.BaseParameter@WORK_TYPESETTING">
										<label><input type="radio" name="sysWorkbench.workbenchTypesetting" value="<s:property value='key'/>"    <s:if test="sysWorkbench == null && @util.BaseParameter@WORK_TYPESETTING_2 ==key">checked</s:if>  <s:if test="sysWorkbench.workbenchTypesetting==key">checked</s:if>/><s:property value='value'/></label>
									</s:iterator>
									
									<font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属角色：</td>
								<td id="roleTD" colspan="3"></td>
							</tr>
							<tr>
								<td class="txtR">所属菜单：</td>
								<td colspan="3">
									<select class="ipt_s" name="sysWorkbench.workbenchModule" id="workbenchModule">
										<option value="">==请选择==</option>
										<s:iterator value="moudleList">
											<option value='<s:property value="moduleId"/>' <s:if test='sysWorkbench.workbenchModule.equals(moduleId)'>selected</s:if>><s:property value='moduleName'/></option>
										</s:iterator>
									</select>
									<font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">是否默认：</td>
								<td>
									<s:iterator value="@util.BaseParameter@WORK_BENCH">
										<label><input type="radio" name="sysWorkbench.workbenchDefault" value="<s:property value='key'/>" <s:if test="sysWorkbench==null&&@util.BaseParameter@WORK_BENCH_DEFAULT==key">checked</s:if><s:elseif test="sysWorkbench.workbenchDefault==key">checked</s:elseif> /><s:property value='value'/></label>
									</s:iterator>
								</td>
								<td class="txtR">状态：</td>
								<td>
									<s:iterator value="@util.BaseParameter@STATUS">
										<label><input type="radio" name="sysWorkbench.workbenchUseFlag" value="<s:property value='key'/>" <s:if test="sysWorkbench==null&&@util.BaseParameter@STATUS_ENABLE==key">checked</s:if><s:elseif test="sysWorkbench.workbenchUseFlag==key">checked</s:elseif> /><s:property value='value'/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td class="txtR">排序号：</td>
								<td colspan="3">
									<input type="text" name="sysWorkbench.orderBy" id="orderBy" class="ipt_s" value="<s:property value='sysWorkbench.orderBy'/>" data-maxlength="10"/>
								</td>
							</tr>
						</table>
						<div class="btnbarBig">
							<a href="javascript:void(0)" class="btnG" onclick="save()">确定</a>
							<a href="javascript:history.back()" class="btnO">返回</a>
						</div>
					</form>
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
			if(jQuery.trim("<s:property value='sysWorkbench.areaId'/>") != '') {
				queryRoleList("<s:property value='sysWorkbench.areaId'/>");
			}
		</script>
	</body>
</html>
