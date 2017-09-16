<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="../../../include/heard.inc"%>
<%@ include file="../../../include/zTree.inc"%>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
var opt='add';
var WEBCOLUMN_ROOT_ID='<s:property value="@util.BaseParameter@WEBCOLUMN_ROOT_ID"/>';
var WEBCOLUMN_ZXKX_ID='402881fd515b4f9b01515b58cf050002';	
var treeObj = null;
//id拿对象
function getObj(id){
	return document.getElementById(id);
}

var syncType = "<s:property value='@util.BaseParameter@LMSMRZK'/>";
var setting;
var zNodes;
if(syncType == "<s:property value='@util.BaseParameter@YES'/>"){
	var zNodes =[<s:property value="infoTcategoryJson" escape="false"/>];
	// 控制按钮显示
	setting = {
		data: {simpleData: {enable: true}},
		callback: {
			onClick : treeClick
		},
		view: {fontCss : {color:"black"}}
	};
}else{
	setting = {
		treeId:'',
		treeObj:null,
		async : {
			enable : true,
			url : "getInfoTcategoryTreeAjax.action",
			autoParam : [ "id=parentId" ],
			dataFilter : filter
		},
		callback : {
			onClick : treeClick
		},
		view: {
			dblClickExpand: true 
		}
	};
}


function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

function treeClick(event, treeId, treeNode) {
	if(treeNode.id!=WEBCOLUMN_ROOT_ID){
		jQuery.ajax({ 
			type: "POST", 
			url: "getJSONInfoTcategory.action?RANDOM="+Math.random(), 
			data: 'columnId='+treeNode.id,
		    success: function(msg){
		    	if(msg!=''){
		    		var obj = eval('('+msg+')');
		    		$(window.frames["infoTcontentFrame"].document).find("input[id=columnId]").val(obj.columnId);
		    		$(window.frames["infoTcontentFrame"].document).find("form[id=queryForm]").submit();
		    	}
			}
		});
	}else{
	}
}
//加载树
jQuery(document).ready(function() {
	if(syncType==1){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting,zNodes);
	}else{
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	
	treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	if(syncType==0){
		openTree();
	}
	//栏目列表div高度自适应
	$('#treeDemo').height(Math.min($(window).height(),$(document).height()) - 20);
	
	var node=treeObj.getNodeByParam("id", WEBCOLUMN_ZXKX_ID, null);
	treeObj.selectNode(node);
	treeClick(event,'treeDemo',node);
});

function openTree(){
	var node=treeObj.getNodeByParam("id", WEBCOLUMN_ROOT_ID, null);
	if(node!=null){
		treeObj.expandNode(node, true,false, true, true);
		treeObj.selectNode(node);
		treeClick(event,'treeDemo',node);
		return false;
	}else setTimeout('openTree()',50);
}

String.prototype.trim=function(){
  return this.replace(/(^\s*)|(\s*jQuery)/g, "");
}

function setWinHeight(){
    var win = document.getElementById("infoTcontentFrame");
    if(document.getElementById){
       if (win && !window.opera){
            if (win.contentDocument && win.contentDocument.body.scrollHeight){
                win.height = win.contentDocument.body.scrollHeight + 10;
            }else if(win.Document&&win.Document.body.scrollHeight){
            	win.height = win.Document.body.scrollHeight + 10; 
            } 
       }        
    }
}
</script>
</head>
<body>
<dl class="mtab" style="margin-top:3px;">
	<dd>
		<div id="treeDemo" class="ztree" style="width:15%; height: 450px; border :1px solid Silver;float:left;margin-right:3px; overflow: auto; "></div>
		<iframe id="infoTcontentFrame"  onload="setWinHeight()" name="infoTcontentFrame" src="queryInfoTcontentList.action?columnId=<s:property value='columnId'/>" frameborder="0" style="float:left;display:inline;width:83%;"></iframe>
	</dd>
</dl>
</body>
</html>
