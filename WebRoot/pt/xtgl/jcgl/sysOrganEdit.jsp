<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
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
	<script language="javascript">
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
		
		function queryOrganList(areaId, organId) { // 联动查询区划下所有机构
			PtSynthetizeAppDwr.queryOrganByAreaId(areaId, organId, function(sysOrgan){
				var obj=document.getElementById('parentId');
				if(null == sysOrgan || "" == sysOrgan) {
					obj.options.length=1;
				} else {
					for(var i = 0; i < sysOrgan.length; i++) {
						var opt = new Option(sysOrgan[i].organName, sysOrgan[i].organId)
						if("<s:property value='sysOrgan.parentId'/>"==opt.value) {
							opt.selected='selected';
						}
						obj.options.add(opt);
					}
				}
			});
		}
		
		function checkForm() { // 验证表单数据提交
			if(jQuery.trim(jQuery('#organCode').val()) == '') {
				jQuery('#organCode').val('');
				Dialog.alertFocus("机构代码不能为空！","organCode");
				return false;
			}
			if(jQuery.trim(jQuery('#organName').val()) == '') {
				jQuery('#organName').val('');
				Dialog.alertFocus("机构名称不能为空！","organName");
				return false;
			}
			if(jQuery.trim(jQuery('#organFullName').val()) == '') {
				jQuery('#organFullName').val('');
				Dialog.alertFocus("机构全称不能为空！","organFullName");
				return false;
			}
			if(jQuery.trim(jQuery('#organTel').val()) == '') {
				jQuery('#organTel').val('');
				Dialog.alertFocus("联系电话不能为空！","organTel");
				return false;
			}
			if(jQuery.trim(jQuery('#organAddr').val()) == '') {
				jQuery('#organAddr').val('');
				Dialog.alertFocus("联系地址不能为空！","organAddr");
				return false;
			}
			if(jQuery.trim(jQuery('#areaName').val()) == '') {
				jQuery('#areaName').val('');
				Dialog.alertFocus("请选择所属区划！","areaName");
				return false;
			}
			Dialog.confirm('是否确定该操作？',function(){
				document.myForm.submit();
			});
		}
	</script>
</head>

<body id="mouseRight">
<div class="mainDiv">
  	<dl class="mtab">
    	<dt>
         <p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysOrganList.action">机构管理</a><a><s:if test="organId == null || organId == ''">新增</s:if><s:else>修改</s:else></a></p>
         <a href="querySysOrganList.action?areaId=<s:property value='areaId' />" class="back">返回</a>
        </dt>
    <dd>
	<form name="myForm" method="post" action="saveOrUpdateOrgan.action">
		<input type=hidden name="areaId" value="<s:property value='areaId'/>" />
		<input type=hidden name="sysOrgan.organId" value="<s:property value='organId'/>" />
		<input type=hidden name="sysOrgan.delFlag" value="<s:property value='sysOrgan.delFlag'/>" />
		<input type=hidden name="sysOrgan.organPath" value="<s:property value='sysOrgan.organPath'/>" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
			<tr><th colspan=4><p class="title"><s:if test="organId == null || organId == ''">新增</s:if><s:else>修改</s:else>机构</p></th></tr>	
			<tr>
				<td width="15%" class="txtR">机构代码：</td>
				<td width="35%">
					<input class="ipt" id="organCode" name="sysOrgan.organCode" 
						value="<s:property value='sysOrgan.organCode'/>" data-maxlength="50"/><font color="red">&nbsp;*</font></td>
		        <td width="15%" class="txtR">机构简码：</td>
		        <td width="35%">
		        	<input class="ipt" id="organSimpCode" name="sysOrgan.organSimpCode" 
		        		value="<s:property value='sysOrgan.organSimpCode'/>" data-maxlength="50"/></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">机构名称：</td>
		        <td width="35%">
		        	<input class="ipt" id="organName" name="sysOrgan.organName" 
		        		value="<s:property value='sysOrgan.organName'/>" data-maxlength="100" onkeyup="jQuery('#organFullName').val(this.value)" /><font color="red">&nbsp;*</font></td>
		        <td width="15%" class="txtR">机构全称：</td>
		        <td width="35%">
		        	<input class="ipt" id="organFullName" name="sysOrgan.organFullName" 
		        		value="<s:property value='sysOrgan.organFullName'/>" data-maxlength="100"/><font color="red">&nbsp;*</font></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">联系电话：</td>
		        <td width="35%">
		        	<input class="ipt" id="organTel" name="sysOrgan.organTel" 
		        		value="<s:property value='sysOrgan.organTel'/>" data-maxlength="20"/><font color="red">&nbsp;*</font></td>
		        <td width="15%" class="txtR">联系地址：</td>
		        <td width="35%">
		        	<input class="ipt" id="organAddr" name="sysOrgan.organAddr" 
		        	value="<s:property value='sysOrgan.organAddr'/>" data-maxlength="200"/><font color="red">&nbsp;*</font></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">所属区划：</td>
		        <td width="35%">
		        	<input type="text" class="input_text" id="areaName" value="<s:property value='sysOrgan.sysArea.areaName'/>" readonly="readonly"/><font color="red">&nbsp;*</font>
		        	<input type="hidden" class="input_text" id="areaId" name="sysOrgan.areaId" value="<s:property value='sysOrgan.areaId'/>"/>
		        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
		        	<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
						<div class="ydlg-hd">区划选择器[双击选中]</div>
						<div class="ydlg-bd">
							<ul id="tree_area" class="ztree" ></ul>
						</div>
					</div>
		        </td>
		        <td width="15%" class="txtR">上级机构：</td>
		        <td width="35%">
		        	<select id="parentId" name="sysOrgan.parentId" class="ipt">
		        		<option value="">==请选择==</option>
		        	</select>
		        </td>
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">启用状态：</td>
		        <td width="35%">
		        	<s:iterator value="@util.BaseParameter@STATUS">
						<label><input type="radio" name="sysOrgan.organFlag" value="<s:property value='key'/>" <s:if test="key==0 || key==sysOrgan.organFlag">checked="checked"</s:if>/><s:property value="value"/></label>
					</s:iterator>
		        </td>
		        <td width="15%" class="txtR">排序号：</td>
		        <td width="35%">
		        	<input class="ipt" id="organSort" name="sysOrgan.organSort" 
		        		value="<s:property value='sysOrgan.organSort'/>" data-maxlength="10"/></td>		
		    </tr>
		</table>         
		<div class="btnbarBig">
			<a href="#" class="btnG" onclick="checkForm();">确定</a>
			<a href="querySysOrganList.action?areaId=<s:property value='areaId' />" class="btnO">返回</a>
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
	if(jQuery.trim("<s:property value='sysOrgan.areaId'/>") != '') {
		queryOrganList("<s:property value='sysOrgan.areaId'/>","<s:property value='organId'/>");
	}
</script>
</body>
</html>
