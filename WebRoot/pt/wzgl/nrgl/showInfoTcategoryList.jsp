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
	var treeObj = null;
	//id拿对象
	function getObj(id){
		return document.getElementById(id);
	}
	
	var setting = {
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
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	function treeClick(event, treeId, treeNode) {
		if(treeNode.id != WEBCOLUMN_ROOT_ID){
			jQuery.ajax({ 
				type: "post", 
				url: "getJSONInfoTcategory.action?RANDOM="+Math.random(), 
				data: 'columnId='+treeNode.id,
			    success: function(msg){
			    	if(msg!=''){
			    		var obj = eval('('+msg+')');
			    		getObj('columnId').value=obj.columnId;
			    		getObj('parentId').value=obj.parentId;
			    		getObj('sort').value=obj.sort;
			    		getObj('columnName').value=obj.columnName;
			    		getObj('columnPath').value=obj.columnPath;
			    		getObj('columnUrl').value=obj.columnUrl;
			    		getObj('rank').value=obj.rank;
			    		getObj('isMapService').value=obj.isMapService;
			    		getObj('columnDescribe').value=obj.columnDescribe;
			    		if(parseInt(obj.usingFlag) == 0){
			    			$("#usingFlag"+parseInt(obj.usingFlag)).attr("checked",'true');
			    		}else{
			    			$("#usingFlag"+parseInt(obj.usingFlag)).attr("checked",'true');
			    		} 
			    		getObj('rank').value = treeNode.level; 
			    		addOrModi('modi');//是否可以添加
			    	}
				}
			});
		}else{
			addOrModi('add');
			getObj('parentId').value=WEBCOLUMN_ROOT_ID;
			getObj('columnId').value='';
    		getObj('sort').value='';
    		getObj('columnName').value='';
    		getObj('columnPath').value='';
    		getObj('columnUrl').value='';
    		getObj('rank').value='';
    		getObj('columnDescribe').value='';
    		$("#usingFlag1").attr("checked",'true');
		}
	}
	//加载树
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		openTree();
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
	
	//保存、修改菜单
	function saveOrUpdateSysModule(){
		if(jQuery.trim(getObj('columnName').value)==''){
			Dialog.alert("栏目名称不能为空！");
	    	return false;
	    }
		if(jQuery.trim(jQuery("#sort").val())=="" || isNaN(jQuery.trim(jQuery("#sort").val()))){
			Dialog.alert("栏目序号不能为空且必须是数字！");
			return false;
		}
		Dialog.confirm('确定要'+(opt=='add'?'添加':'修改')+'吗？',function(){
	    	
			jQuery.ajax({
					type: "post",
		    		url: "editInfoTcategoryAjax.action",
			    	data:  {
			    		'infoTcategory.columnId' : getObj('columnId').value,
			    		'infoTcategory.parentId' : getObj('parentId').value,
			    		'infoTcategory.columnName' : getObj('columnName').value,
			    		'infoTcategory.columnPath' : getObj('columnPath').value,
			    		'infoTcategory.columnUrl' : getObj('columnUrl').value,
			    		'infoTcategory.sort' : getObj('sort').value,
			    		'infoTcategory.rank' : getObj('rank').value,
			    		'infoTcategory.columnDescribe' : getObj('columnDescribe').value,
			    		'infoTcategory.usingFlag' : $("input[name='usingFlag']:checked").val(),
			    		'infoTcategory.isMapService' : getObj('isMapService').value
			    	}, 
				    success: function(msg){
				    	if(msg!=''){
				    		if(opt=='add'){
				    			getObj('columnId').value=getObj('columnId').value+','+treeObj.getSelectedNodes()[0].id;
								var newNodes = [{id:msg,name:getObj('columnName').value}];
								newNodes = treeObj.addNodes(treeObj.getSelectedNodes()[0], newNodes);
								newNodes = treeObj.getNodeByParam("id", msg, null);
				    			getObj('columnId').value=msg;
				    			treeObj.selectNode(newNodes);
								treeClick(event,'treeDemo',newNodes);
				    		}else{
				    			var thisNode = treeObj.getNodeByParam("id", msg, null);
				    			thisNode.name = getObj('columnName').value;
				    			treeObj.updateNode(thisNode);
				    		}
				    		addOrModi('modi');
				    	}
				    	Dialog.alert('保存成功');
					}
				});
	    });
	}
	function addOrModi(type){
		getObj('addBut').style.display=(type=='add'?"none":"inline");
		getObj('delBut').style.display=(type=='add'?"none":"inline");//是否显示删除按钮
		getObj('midBut').innerHTML=(type=='add'?"<img src='./images/ico_btn_add.gif' />添加":"<img src='./images/ico_btn_modi.gif' />修改");//添加或修改
		opt=(type=='add'?'add':'modi');//标记操作状态
	}
	
	function prepareAdd(){//添加下一级菜单
		addOrModi('add');
		getObj('parentId').value=getObj('columnId').value;
		getObj('columnPath').value = getObj('columnPath').value + "." + getObj('parentId').value;
		getObj('columnId').value='';
   		getObj('columnName').value='';
   		getObj('columnUrl').value='';
   		getObj('sort').value='';
   		getObj('rank').value= parseInt(getObj('rank').value)+1;
   		getObj('columnDescribe').value='';
	}
	String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*jQuery)/g, "");
　　 }
	
	function delSysModule(){//删除菜单
		var t='';
		if(treeObj.getSelectedNodes()[0].isParent)t='该菜单下存在子菜单，删除该菜单会同时删除该菜单下所有子菜单，确定要删除吗？';
		else t='确定要删除该菜单吗？';
		Dialog.confirm(t,function(){
			jQuery.ajax({
				type: "GET",
			    url: "delInfoTcategoryAjax.action",
			    data: 'columnId='+treeObj.getSelectedNodes()[0].id,
			    success: function(msg){
			    	if(msg=='success'){
			    		var thisNode = treeObj.getNodeByParam("id", treeObj.getSelectedNodes()[0].id, null);
			    		var pNode = thisNode.getParentNode();
			    		treeObj.removeNode(thisNode);
			    		treeObj.selectNode(pNode);
						treeClick(event,'treeDemo',pNode);
						Dialog.alert('删除成功');
			    	}
				}
			});
		});
	}
</script>
</head>
<body id="mouseRight">
<dl class="mtab" style="margin-top:3px;">
	<dd>
		<div id="treeDemo" class="ztree" style="width:20%; height: 450px; border :1px solid Silver;float:left;margin-right:3px; overflow: auto; "></div>
			
		<div style="float:left; width: 78%;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
				<tr>
					<th colspan="4" style="text-align: left; padding-left: 5px; color: #023266; font-weight: bold;"><p class="title">栏目信息</p></th>
				</tr>
				<tr>
					<td class="txtR" width="15%">栏目名称：</td>
					<td width="35%">
						<input id="columnName" class="ipt" value="" data-maxlength="100"/>
						&nbsp;<font color="red">*</font>
					</td>
					<td width="15%" class="txtR">栏目序号：</td>
					<td width="35%">
						<input id="sort" class="ipt" size="20" value="" data-maxlength="5"/>
						&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td class="txtR" width="15%" >栏目地址：</td>
					<td colspan="3" ><input id="columnUrl" class="ipt" style="width:50%" data-maxlength="200"/>
						<span style="color:#999;">( 如：http://www.baidu.com )</span>
					</td>
				</tr>
				<tr>
					<td class="txtR">栏目描述：</td>
					<td colspan="3"><textarea id="columnDescribe" class="input_area" data-maxlength="255"></textarea></td>
				</tr>
				<%-- <tr>
					<td class="txtR">是否为地图服务：</td>
					<td colspan="3">
						<s:iterator value="@util.BaseParameter@YES_NO">
							<label><input type="radio" id="isMapService<s:property value='key'/>" name="isMapService" value="<s:property value='@util.BaseParameter@NO'/>"  /><s:property value="value"/></label>
						</s:iterator>
					</td>
				</tr> --%>
				<tr>
					<td class="txtR">是否启用：</td>
					<td colspan="3">
						<s:iterator value="@util.BaseParameter@STATUS">
						<label><input type="radio" id="usingFlag<s:property value='key'/>" name="usingFlag" value="<s:property value='key'/>" <s:if test="@util.BaseParameter@STATUS_ENABLE==key">checked</s:if> /><s:property value="value"/></label>
					</s:iterator>
					</td>
				</tr>
			</table>
			<div  class="btnbar">
				<div id="addBut" style="display:none;"><a href="javascript:void(0)" onclick='prepareAdd();'><img src="./images/ico_btn_add.gif" />添加下级菜单</a>&nbsp;</div>
				<a  id="midBut" href="javascript:void(0)" onclick='saveOrUpdateSysModule();' ><img src="./images/ico_btn_add.gif" />添加</a>&nbsp;
				<div id="delBut" style="display:none;"><a href="javascript:void(0)" onclick='delSysModule();'><img src="./images/ico_btn_cancel.gif" />删除</a></div>
			</div>
			<input type="hidden" id="isMapService" value="<s:property value='@util.BaseParameter@NO'/>"  />
			<input type="hidden" id="columnId" value=""/>
			<input type="hidden" id="rank" name="rank" value=""/>
			<input type="hidden" id="parentId" value="<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>"/>
			<input type="hidden" id="columnPath" value=""/>
		</div>
	</dd>
</dl>
</body>
</html>
