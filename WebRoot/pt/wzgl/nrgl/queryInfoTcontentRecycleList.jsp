<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
<script type="text/javascript">
	
jQuery(document).ready(function() {
	jQuery("#listTab1 tr:even").addClass("alt");
	jQuery(".listTab tr:not(first,second)").hover(function() {
		jQuery(this).addClass("over")
	}, function() {
		jQuery(this).removeClass("over")
	});
	initTableTitle('listTab1');
})



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
		//onClick: treeOnClick,//点击树的节点
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
}


//彻底删除
function shiftDelete(contentId){
	Dialog.confirm('是否确定从回收站中删除此项，删除后不可恢复!',function(){
   		window.location.href="shiftDeleteInfoTcontent.action?contentId="+contentId;
   	});
}
//还原
function restore(contentId){
	Dialog.confirm('是否确定从回收站中还原？<br/>注：还原后的新闻默认为禁用状态',function(){
 		window.location.href="restoreInfoTcontent.action?contentId="+contentId;
 	});
}

//批量还原
function batchRestore(){
	//判断是否有选中新闻
	var checkFlag = false;
	var arrContentIds = document.getElementsByName('contentIds');
	var len = arrContentIds.length;
	var contentId = "";
	for(var i=0;i<len;i++) {
		if(arrContentIds[i].checked) {
			contentId += arrContentIds[i].value+",";
			checkFlag = true;
		} 
	}
	
	if(!checkFlag) {
		Dialog.alertFocus("请至少选择一条新闻！");
		return false;
	}
	Dialog.confirm('是否确定批量还原？<br/>注：还原后的新闻默认为禁用状态',function(){
		document.getElementById('contentId').value = contentId;
 		location.href= 'batchRestoreInfoContent.action?contentId='+contentId;
 	});
}

//全选
function checkAll(){
	var checkFlag = document.getElementById('checkAll').checked;
	var arrContentIds = document.getElementsByName('contentIds');
	var len = arrContentIds.length;
	if(len <= 0) return;
	for(var i=0;i<len;i++) {
		arrContentIds[i].checked=checkFlag;
	}
}

function emptyRecycle(){
	Dialog.confirm('是否确定清空回收站？<br/>注：清空后不可恢复',function(){
 		location.href="emptyRecycleInfoTcontent.action";
 	});
}
</script>
</head>
<body>
    
<div class="mainDiv">
	<form action="queryInfoTcontentRecycleList.action" name="queryForm" id="queryForm"  method='get'>
		<div class="infoSearch">
			<input type="hidden" name='contentId' id='contentId'/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTab">
				<tbody>
					<tr>
						<td width="15%" class="txtR">区划选择：</td>
						<td width="35%">
							<input type="text" id="areaName" name="areaName" value="<s:property value='areaName' />" class="ipt_s" readonly="readonly" />
							<input id="m-areaTree" type="button" class="btn_select" value="选择..." style="cursor: pointer;"/>&nbsp;
							<input type="hidden" id="areaId" name="areaId" value="<s:property value='areaId' />" class="ipt_s" />
							<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
								<div class="ydlg-hd">区划选择器[双击选中]</div>
								<div class="ydlg-bd">
									<ul id="tree_area" class="ztree" ></ul>
								</div>
							</div> 
						</td>
						<td width="15%" class="txtR">内容主标题：</td>
						<td width="35%">
							<input type="text" name="contentMainTitle" value="<s:property value='contentMainTitle' />" class="ipt_s" />
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btnbar">
			    <a href="javascript:void(0)" onclick="document.getElementById('queryForm').submit();"><img src="images/ico_btn_search.gif"/>搜索</a>
			   <a href="javascript:void(0);" onclick="batchRestore();"><img src="images/ico_btn_add.gif"/>批量还原</a>
			   <a href="javascript:void(0);" onclick='emptyRecycle();'><img src="images/ico_btn_add.gif"/>清空</a>
			</div>
		</div>
	</form>
	<dl class="mtab" style="width:100%">
        <dd id="t1">
        	<input type="hidden" id="contentId" name="contentId" />
        	<input type="hidden" id="columnId" name="infoTcontent.columnId" value="<s:property value='infoTcontent.columnId'/>" />
			<input type="hidden" id="columnId" name="columnId" value="<s:property value='columnId'/>" />
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
				<tr>
					<th style="width: 6%">序号</th>
					<th class="datatitle" width="6%"><input type="checkbox" id="checkAll" onclick="checkAll();" /></th>
					<th style="width: 10%">所属区划</th>
					<th>内容主标题</th>
					<th style="width: 10%">所属栏目</th>
					<th style="width: 10%">发布时间</th>
					<th style="width: 10%">启用状态</th>
					<th style="width: 10%">审核状态</th>
					<th style="width: 22%">操作</th>
				</tr>
			    <s:iterator value="infoTcontentList" status="index">
					<tr>
						<td class='box'><s:property value="#index.count" /></td>
						<td><input type="checkbox" id="contentIds" name="contentIds" value="<s:property value='contentId'/>" /></td>
						<td><s:property value="sysArea.areaName" /></td>
						<td style="text-align: left; padding-left: 5px;"><s:property value="contentMainTitle" /></td>
						<td><s:property value="infoTcategory.columnName" /></td>
						<td><s:date name="issurDate" format="yyyy-MM-dd" /></td>
						<td><s:if test="usingFlag==1">启用</s:if><s:elseif test="usingFlag==0">禁用</s:elseif></td>
						<td><s:if test="checkFlag==0">待审核</s:if><s:elseif test="checkFlag==1">审核通过</s:elseif><s:elseif test="checkFlag==2">审核不通过</s:elseif></td>
						<td>
							<a href="javascript:void(0)" onclick="restore('<s:property value="contentId"/>');">还原</a>&nbsp;|
							<a href="javascript:void(0)"  onclick="shiftDelete('<s:property value='contentId' />')">彻底删除</a>&nbsp;|
							<a href="getInfoTcontentRecycleDetial.action?contentId=<s:property value='contentId'/>">查看</a>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="9" class="datatitle" >
						<%@ include file="../../../include/pagination.inc"%>
					</td>
				</tr>
			</table>
		</dd>
	</dl>
</div>
</body>
<script type="text/javascript">
	var btnIds = ['m-areaTree']; //按钮名称
	var tanchuDivs = ['tree-area']; //展示div名称
	for ( var i = 0; i < btnIds.length; i++) {
		var td = new HelloWorld(btnIds[i], tanchuDivs[i]);
		YAHOO.ext.EventManager.onDocumentReady(td.init, td, true);
	}
</script>
</html>
