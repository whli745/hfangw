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
		function onReset(){
			Dialog.confirm('您确定要重置数据信息吗？',function(){
				jQuery("#roleTD").html('');
				document.myForm.reset();
			},'','','','2');
		}  
		function check(){
			if(jQuery.trim(jQuery("#userCode").val())==""){
				Dialog.alertFocus("用户登录账户不能为空！", 'userCode');
				return false;
			}
			var name=document.getElementById("userName");
			if(jQuery.trim(jQuery("#userName").val())==""){
				Dialog.alertFocus("用户姓名不能为空！", 'userName');
				return false;
			}
			var pwd=document.getElementById("pwd");
			if(pwd.value.length<=0){
				Dialog.alertFocus("密码不能为空！", 'pwd');
				return false;
			}
			var userPost=document.getElementById("userPost");
			if(userPost.value==0){
				Dialog.alertFocus("请选择职务不能为空！", 'userPost');
				return false;
			}
			if(jQuery.trim(jQuery("#userPhone").val())==""){
				Dialog.alertFocus("手机号码不能为空！", 'userPhone');
				return false;
			}
			if(jQuery.trim(jQuery("#areaName").val())==""){
				Dialog.alertFocus("请选择所属区划！", 'areaName');
				return false;
			}
			if(jQuery.trim(jQuery("#parentId").val())==""){
				Dialog.alertFocus("请选择所属机构！", 'parentId');
				return false;
			}
			
			var roles = document.getElementsByName("sysUser.roleId");
			var flag = false;
			for(var i = 0; i < roles.length; i++) {
				if(roles[i].checked) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				Dialog.alert("请选择所属组！");
				return false;
			}
			Dialog.confirm('是否确定此操作？',function(){
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
			queryOrganList(treeNode.id);
		}
		
		function queryOrganList(areaId, organId) {
			// 联动查询区划下所有机构
			PtSynthetizeAppDwr.queryOrganByAreaId(areaId, null, function(sysOrgan){
				var obj=document.getElementById('parentId');
				obj.options.length=1;
				for(var i = 0; i < sysOrgan.length; i++) {
					var opt = new Option(sysOrgan[i].organName, sysOrgan[i].organId)
					if("<s:property value='sysUser.organId'/>"==opt.value) {
						opt.selected='selected';
					}
					obj.options.add(opt);
				}
			});
			// 联动查询区划下所有权限组
			PtSynthetizeAppDwr.queryRoleByAreaId(areaId, function(sysRole) {
				var roles = '';
				for(var i = 0; i < sysRole.length; i++) {
					<s:if test="null == userRoleList || userRoleList.size==0">
						roles += '<div style="margin-top: 5px;float:left; width:90px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" name="sysUser.roleId" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
					</s:if>
					<s:else>
						var flag = '0';
						<s:iterator value="userRoleList">
							if("<s:property value='roleId'/>" == sysRole[i].roleId) {
								roles += '<div style="margin-top: 5px;float:left; width:90px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" checked="checked" name="sysUser.roleId" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
								flag = '1';
							}
						</s:iterator>
						if(flag == '0') {
							roles += '<div style="margin-top: 5px;float:left; width:90px; overflow: hidden; text-overflow:ellipsis; cursor:pointer;" title="'+sysRole[i].roleName+'"><nobr><label><input type="checkbox" name="sysUser.roleId" value="'+sysRole[i].roleId+'"/>'+sysRole[i].roleName+'</label></nobr></div>';
						}
					</s:else>
				}
				jQuery("#roleTD").html(roles);
			});
		}
	</script>
</head>
<body id="mouseRight">
	<div class="mainDiv" style="line-height: ">
		<dl class="mtab">
			<dt>
				<p class="position">
					当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysUserList.action">用户管理</a><a><s:if test='sysUser!=null'>修改</s:if><s:else>新增</s:else></a>
				</p>
				<a href="javascript:void(0)" class="back" onclick="back()">返回</a>
			</dt>
			<dd>
				<form name="myForm" id="myForm" method="post" action="initSysUser.action" >
					<input type="hidden" name="userId" value="<s:property value='userId'/>"/>
					<input type="hidden" name="sysUser.userId" value="<s:property value='userId'/>"/>
					<input type="hidden" name="sysUser.serialId" value="<s:property value='sysUser.serialId'/>"/>
					<input type="hidden" name="sysUser.delFlag" value="<s:property value='sysUser.delFlag'/>"/>
					<input type="hidden" name="areaId" value="<s:property value='areaId'/>"/>
					<input type="hidden" name="areaName" value="<s:property value='areaName'/>"/>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr>
							<th colspan="4"><p class="title"><s:if test='sysUser!=null'>修改</s:if><s:else>新增</s:else></p></th>
						</tr>
						<tr>
							<td width="15%" class="txtR">用户登录账号：</td>
							<td width="35%"><input name="sysUser.userCode" id="userCode" class="ipt" value="<s:property value='sysUser.userCode'/>" data-maxlength="50"/><font color="red">&nbsp;*</font></td>
							<td width="15%" class="txtR">用户名称：</td>
							<td width="35%"><input name="sysUser.userName" id="userName" class="ipt" value="<s:property value='sysUser.userName'/>" data-maxlength="100"/><font color="red">&nbsp;*</font></td>
						</tr>
						<tr>
							<td class="txtR">密码：</td>
							<td><input name="sysUser.userPassword" id="pwd" type="password" value="<s:property value='sysUser.userPassword'/>" class="ipt" data-maxlength="50"/><font color="red">&nbsp;*</font></td>
							<td class="txtR">用户状态：</td>
							<td>
								<s:iterator value="@util.BaseParameter@STATUS">
									<label><input type="radio" name="sysUser.userStatus" id="userStatus" value="<s:property value='key'/>"
									<s:if test="sysUser == null && key.equals(@util.BaseParameter@STATUS_ENABLE)">checked</s:if>
									<s:if test="key.equals(sysUser.userStatus)">checked</s:if> /><s:property value='value'/></label>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td class="txtR">性别：</td>
							<td>
								<s:iterator value="@util.BaseParameter@PERSON_SEX">
									<label><input type="radio" name="sysUser.userSex" id="userSex" value="<s:property value='key'/>"
									<s:if test="sysUser == null && key.equals(@util.BaseParameter@MALE)">checked</s:if>
									<s:if test="key.equals(sysUser.userSex)">checked</s:if> /><s:property value='value'/></label>
								</s:iterator>
							</td>
							<td class="txtR">出生年月：</td>
							<td><input name="sysUser.userBirthday" id="userBirthday" value='<s:date name="sysUser.userBirthday" format="yyyy-MM-dd"/>' class="ipt Wdate" onfocus="WdatePicker()"  readonly="readonly"/></td>
						</tr>
						<tr>
							<td class="txtR">职务：</td>
							<td>
								<select name="sysUser.userPost" id="userPost" class="ipt_s">
									<option value="0">==请选择职务==</option>
									<s:iterator value="sysDictList">
										<option value="<s:property value='dictId'/>" <s:if test="sysUser.userPost == dictId">selected</s:if> ><s:property value='dictName'/></option>
									</s:iterator>
								</select>
								<font color="red">&nbsp;*</font>
							</td>
							<td class="txtR">手机：</td>
							<td><input name="sysUser.userPhone" id="userPhone" value="<s:property value='sysUser.userPhone'/>" class="ipt" data-maxlength="200"/><font color="red">&nbsp;*</font></td>
						</tr>
						<tr>
							<td class="txtR">电话：</td>
							<td><input name="sysUser.userTel" id="userTel" value="<s:property value='sysUser.userTel'/>" class="ipt" data-maxlength="20"/></td>
							<td class="txtR">邮箱：</td>
							<td><input type="text" name="sysUser.userEmail" id="userEmail" value="<s:property value='sysUser.userEmail'/>" class="ipt" data-maxlength="20"/></td>
						</tr>
						<tr>
							<td class="txtR">序号：</td>
							<td colspan="3"><input id="userSort" name="sysUser.userSort" value="<s:property value='sysUser.userSort'/>" class="ipt" style="width:100px;" data-maxlength="50"/></td>
						</tr>
						<tr>
							<td class="txtR">所属区划：</td>
							<td>
								<input type="text" class="input_text" id="areaName" value="<s:property value='sysUser.sysArea.areaName'/>" readonly="readonly"/><font color="red">&nbsp;*</font>
					        	<input type="hidden" class="input_text" id="areaId" name="sysUser.areaId" value="<s:property value='sysUser.areaId'/>"/>
					        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
								<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
									<div class="ydlg-hd">区划选择器[双击选中]</div>
									<div class="ydlg-bd">
										<ul id="tree_area" class="ztree" ></ul>
									</div>
								</div>
							</td>
							<td class="txtR">所属机构：</td>
							<td>
								<select class="ipt" id="parentId" name="sysUser.organId">
									<option value="">==请选择==</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="txtR" >所属组：</td>
							<td colspan="3" id="roleTD"></td>
						</tr>
					</table>
					<div class="btnbarBig">
						<a href="javascript:void(0)" class="btnG" onclick="check()">确定</a>
						<a href="javascript:void(0)" class="btnO" onclick="back()">返回</a>
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
		if(jQuery.trim("<s:property value='sysUser.areaId'/>") != '') {
			queryOrganList("<s:property value='sysUser.areaId'/>",null);
		}
	</script>
	<script type="text/javascript">
			var settingRoles = {
					check: {
						enable: true,
						chkboxType: { "Y": "", "N": "" }
					},
				data: {simpleData: {enable: true}},
				view: {fontCss : {color:"black"}}
			};
			
			var zNodesRoles =[<s:property value="websiteJson" escape="false"/>];
			
			jQuery(document).ready(function(){
				jQuery.fn.zTree.init(jQuery("#treeDemo"), settingRoles, zNodesRoles);
			});
</script>
</body>
</html>