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
	jQuery(".dataTab tr:not(first,second)").hover(function() {
		jQuery(this).addClass("over")
	}, function() {
		jQuery(this).removeClass("over")
	});
	initTableTitle('listTab1');
})
</script>
<script type="text/javascript">
function delInfoTcontent(contentId){
	Dialog.confirm('是否确定删除？',function(){
     		jQuery.ajax({ 
				type: "POST", 
				url: "delInfoTcontent.action?RANDOM="+Math.random(), 
				data: {
					'contentId' : contentId
				},
			    success: function(msg){
			    	if(msg!=''){
			    		$("#queryForm").submit();
			    	}
				}
			});
     	});
}
</script>
<script type="text/javascript">
	function using(contentId, usingFlag) {
		var message = "启用？";
		if(usingFlag == 1) message = "禁用？";
		var toUsingFalg = usingFlag == 1 ? 0 :1;
		Dialog.confirm('是否确定'+message, function() {
			location.href = "usingInfoTcontent.action?toUsingFlag="+toUsingFalg+"&contentId="+ contentId;
		});
	}
</script>

<script type="text/javascript">

//批量启用/禁用
function batchEnable(usingFlag){

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
	var message = '是否确定批量启用？';
	if(usingFlag == 0)message='是否确定批量禁用？';
	if(usingFlag == 2)message='是否确定批量删除？';
	Dialog.confirm(message,function(){
		document.getElementById('contentId').value = contentId;
		location.href = 'batchEnableInfoTcontent.action?usingFlag='+usingFlag+"&contentId="+contentId;
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


//打开添加页面 
 function openAddPage(stepId) {
	var bodyWidth=parent.document.body.clientWidth;//网页可见区域宽
    var bodyHeight=parent.document.documentElement.clientHeight;//网页可见区域高
	var width = bodyWidth - 15;
  	var height = bodyHeight - 15;
	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Title = "添加";
	diag.URL = "editInfoTcontent.action?columnId="+$("#columnId").val();
	diag.show();
} 


function reSubmitForm(){
	$("#queryForm").submit();//重新加载
}

//打开编辑页面 
function openEditPage(contentId,isMapSearch){

	var bodyWidth=parent.document.body.clientWidth;//网页可见区域宽
    var bodyHeight=parent.document.documentElement.clientHeight;//网页可见区域高
    
	var width = bodyWidth*0.99;
  	var height = bodyHeight*0.99;
  	var src = "";
  	if( isMapSearch == '1' ){
  		src = "editMapResultTcontent.action?contentId="+contentId;
  	}else if( isMapSearch == "0" ){
  		src = "editBszn.action?contentId="+contentId;
  	}else{
  		src = "editInfoTcontent.action?contentId="+contentId;
  	}
  	
  	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Title = "编辑";
	diag.URL = src;
	diag.show();
	
}
function openViewPage(contentId,isMapSearch){
	var bodyWidth=parent.document.body.clientWidth;//网页可见区域宽
    var bodyHeight=parent.document.documentElement.clientHeight;//网页可见区域高
    
	var width = bodyWidth *0.99;
  	var height = bodyHeight *0.99;
  	
  	var src = "";
  	if( isMapSearch == '1' ){
  		src = "editMapResultTcontent.action?contentId="+contentId;
  	}else if( isMapSearch == "0" ){
  		src = "editBszn.action?contentId="+contentId + "&pageType=view";
  	}else{
  		src = "getInfoTcontentDetial.action?contentId="+contentId
  	}
	
	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Title = "查看";
	diag.URL = src;
	diag.show();
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

function doSubmit(){
	document.getElementById('queryForm').submit();
}
</script>		
</head>
<body>
    
<div class="mainDiv">
	<form action="queryInfoTcontentList.action" name="queryForm" id="queryForm"  method='get'>
		<input type='hidden' name='columnId' id='columnId' value="<s:property value='columnId'/>"/>
		<div class="infoSearch">
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
			   <a href="javascript:void(0)" onclick="doSubmit();" id="query_btn"><img src="images/ico_btn_search.gif"/>查询</a>
			   	<s:if test='infoTcategoryList != null && infoTcategoryList.size()!=0'>
			   	</s:if>
			   	<s:else>
			   		<a href="javascript:void(0);" onclick='openAddPage();'><img src="images/ico_btn_add.gif"/>添加</a>
			   	</s:else>
			   <a href="javascript:void(0)" onclick="batchEnable(1);"><img src="images/ico_btn_add.gif"/>批量启用</a>
			   <a href="javascript:void(0)" onclick="batchEnable(0);"><img src="images/ico_btn_add.gif"/>批量禁用</a>
			   <a href="javascript:void(0)" onclick="batchEnable(2);"><img src="images/ico_btn_add.gif"/>批量删除</a>
			</div>
		</div>
	</form>
	<dl class="mtab" style="width:100%">
        <dd id="t1">
        	<input type="hidden" id="contentId" name="contentId" />
        	<input type="hidden" id="columnId" name="infoTcontent.columnId" value="<s:property value='infoTcontent.columnId'/>" />
			<input type="hidden" id="columnId" name="columnId" value="<s:property value='columnId'/>" />
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab"  id="listTab1">
				<tr>
					<th style="width: 6%">序号</th>
					<th class="datatitle" width="5%"><input type="checkbox" id="checkAll" onclick="checkAll();" /></th>
					<th style="width: 10%">所属区划</th>
					<th>内容(单位)名称</th>
					<th style="width: 10%">所属栏目</th>
					<th style="width: 15%">发布时间</th>
					<th style="width: 6%">状态</th>
					<th style="width: 10%">审核状态</th>
					<th style="width: 20%">操作</th>
				</tr>
			    <s:iterator value="infoTcontentList" status="index">
					<tr>
						<td class='box'><s:property value="#index.count" /></td>
						<td><input type="checkbox" id="contentIds" name="contentIds" value="<s:property value='contentId'/>" /></td>
						<td><s:property value="sysArea.areaName" /></td>
						<td style="text-align: left; padding-left: 5px;">
							<s:if test="contentMainTitle==null||contentMainTitle.equals('')">
								<s:property value="departmenName" />
							</s:if>
							<s:else>
								<s:property value="contentMainTitle" />
							</s:else>
						</td>
						<td><s:property value="infoTcategory.columnName" /></td>
						<td><s:date name="issurDate" format="yyyy-MM-dd HH:mm" /></td>
						<td><s:if test="usingFlag==1">启用</s:if><s:elseif test="usingFlag==0">禁用</s:elseif></td>
						<td><s:if test="checkFlag==0">待审核</s:if><s:elseif test="checkFlag==1">审核通过</s:elseif><s:elseif test="checkFlag==2">审核不通过</s:elseif><s:else>无需审核</s:else></td>
						<td>
							<a href="javascript:void(0);" onclick="openEditPage('<s:property value="contentId"/>','<s:property value="isMapSearch"/>')">修改</a>&nbsp;|
							<a href="javascript:void(0);" onclick="using('<s:property value="contentId"/>','<s:property value="usingFlag"/>');"><s:if test="usingFlag==0">启用</s:if><s:elseif test="usingFlag==1">禁用</s:elseif></a>&nbsp;|
							<a href="javascript:void(0);"  onclick="delInfoTcontent('<s:property value='contentId' />')">删除</a>&nbsp;|
							<a href="javascript:void(0);" onclick="openViewPage('<s:property value="contentId"/>','<s:property value="isMapSearch"/>');">查看</a>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="9" class="datatitle">
						<%@ include file="../../../include/pagination.inc"%>
					</td>
				</tr>
			</table>
		</dd>
	</dl>
</div>
<script>
	var btnIds = ['m-areaTree']; //按钮名称
	var tanchuDivs = ['tree-area']; //展示div名称
	for ( var i = 0; i < btnIds.length; i++) {
		var td = new HelloWorld(btnIds[i], tanchuDivs[i], true);
		YAHOO.ext.EventManager.onDocumentReady(td.init, td, true);
	}
	
</script>
</body>
</html>
