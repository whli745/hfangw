<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="../../../include/heard.inc"%>
		<base href="<%=basePath%>"/>
		<script type="text/javascript" src="./js/jquery.uploadify.v2.0.3.js"></script>
		<script type="text/javascript" src="./js/swfobject.js"></script>
		<link rel="stylesheet" href="./css/uploadify.css" type="text/css"></link>
		<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		<script type="text/javascript" src="js/zDialog/zDialog.js"></script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a>数据字典管理</a>
					</p>
				</dt>
				<dd>
					<div>
						<div style="width:30%; height: 450px; border :1px solid Silver; float:left; margin-right:3px;overflow: auto;">
							<ul id="treeDemo" class="ztree" ></ul>
						</div>
						<script type="text/javascript">
							var opt='add';
							var SYSDICT_ROOT_ID='<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>';
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
									url : "getSysDictTreeAjax.action",
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
								if(treeNode.id!=SYSDICT_ROOT_ID){
									jQuery.ajax({ tgetObjpe: "POST", url: "getJSONSysDict.action?RANDOM="+Math.random(), data: 'dictId='+treeNode.id,
									    success: function(msg){
									    	if(msg!=''){
									    		var dict=eval('('+msg+')');
									    		getObj('dictId').value=dict.dictId;
									    		getObj('parentId').value=dict.parentId;
									    		getObj('dictCode').value=dict.dictCode;
									    		getObj('dictName').value=dict.dictName;
									    		getObj('dictMemo').value=dict.dictMemo;
									    		if(dict.statusFlag==<s:property value="@util.BaseParameter@STATUS_ENABLE"/>){
									    			getObj('statusFlag'+<s:property value="@util.BaseParameter@STATUS_ENABLE"/>).checked=true;
									    			getObj('statusFlag'+<s:property value="@util.BaseParameter@STATUS_DISABLE"/>).checked=false;
									    		}else if(dict.statusFlag==<s:property value="@util.BaseParameter@STATUS_DISABLE"/>){
									    			getObj('statusFlag'+<s:property value="@util.BaseParameter@STATUS_ENABLE"/>).checked=false;
									    			getObj('statusFlag'+<s:property value="@util.BaseParameter@STATUS_DISABLE"/>).checked=true;
									    		}
									    		addOrModi('modi');//是否可以添加
									    	}
										}
									});
								}else{//增加一级菜单
									addOrModi('add');
									getObj('dictId').value='';
									getObj('parentId').value=SYSDICT_ROOT_ID;
									getObj('dictCode').value='';
									getObj('dictName').value='';
									getObj('dictMemo').value='';
									getObj('dictCode').focus();
								}
							}
							//加载树
							jQuery(document).ready(function() {
								jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
								treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
								openTree();
							});
							function openTree(){
								var node=treeObj.getNodeByParam("id", SYSDICT_ROOT_ID, null);
								if(node!=null){
									treeObj.expandNode(node, true,false, true, true);
									treeObj.selectNode(node);
									treeClick(event,'treeDemo',node);
									return false;
								}else setTimeout('openTree()',50);
							}
							//保存、修改菜单
							function saveOrUpdateSysModule(){
								if(jQuery.trim(getObj('dictCode').value)==''){
									Dialog.alertFocus("字典编号不能为空！",'dictCode');
							    	return false;
							    }
							    
							    if(jQuery.trim(getObj('dictName').value)==''){
							    	Dialog.alertFocus("字典名称不能为空！",'dictName');
							    	return false;
							    }
								jQuery.ajax({
										type: "POST",
							    		url: "editSysDictAjax.action",
								    	data:   'sysDict.dictId='+getObj('dictId').value+ //封装对象
								    			'&sysDict.parentId='+getObj('parentId').value+
								    			'&sysDict.dictCode='+getObj('dictCode').value+
								    			'&sysDict.dictMemo='+getObj('dictMemo').value+
								    			'&sysDict.statusFlag='+jQuery(":radio:checked").val()+
								    			'&sysDict.dictName='+getObj('dictName').value,
									    success: function(msg){
									    	if(msg!=''){
									    		if(opt=='add'){
									    			getObj('dictId').value=getObj('dictId').value+','+treeObj.getSelectedNodes()[0].id;
													var newNodes = [{id:msg,name:getObj('dictName').value}];
													newNodes = treeObj.addNodes(treeObj.getSelectedNodes()[0], newNodes);
													newNodes = treeObj.getNodeByParam("id", msg, null);
									    			getObj('dictId').value=msg;
									    			treeObj.selectNode(newNodes);
													treeClick(event,'treeDemo',newNodes);
									    		}else{
									    			var thisNode = treeObj.getNodeByParam("id", msg, null);
									    			thisNode.name = getObj('dictName').value;
									    			treeObj.updateNode(thisNode);
									    			Dialog.alert('修改成功！');
									    		}
									    		addOrModi('modi');
									    	}
										}
									});
							}
							function addOrModi(type){
								getObj('addBut').style.display=(type=='add'?"none":"inline");
								getObj('delBut').style.display=(type=='add'?"none":"inline");//是否显示删除按钮
								getObj('midBut').innerHTML=(type=='add'?"添加":"修改");//添加或修改
								opt=(type=='add'?'add':'modi');//标记操作状态
							}
							function prepareAdd(){//添加下一级菜单
								addOrModi('add');
								getObj('parentId').value=getObj('dictId').value;
								getObj('dictId').value='';
						   		getObj('dictName').value='';
						   		getObj('dictCode').value='';
						   		getObj('dictMemo').value='';
						   		
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
										type: "POST",
									    url: "delSysDictAjax.action",
									    data: 'dictId='+treeObj.getSelectedNodes()[0].id,
									    success: function(msg){
									    	if(msg=='success'){
									    		var thisNode = treeObj.getNodeByParam("id", treeObj.getSelectedNodes()[0].id, null);
									    		var pNode = thisNode.getParentNode();
									    		treeObj.removeNode(thisNode);
									    		treeObj.selectNode(pNode);
												treeClick(event,'treeDemo',pNode);
									    	}
										}
									});
								});
							}
					</script>
						<div style="float:left;display:inline;width:66%;">
							<input type="hidden" id="dictId" value=""/>
							<input type="hidden" id="parentId" value="<s:property value="@util.BaseParameter@SYSDICT_ROOT_ID"/>"/>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
								<tr>
									<th colspan="4"><p class="title">数据字典信息</p></th>
								</tr>
								<tr>
									<td width="15%" class="txtR">字典编号：</td>
									<td width="35%">
										<input id="dictCode" class="ipt" size="20" value="" data-maxlength="50"/>
										&nbsp;<font color="red">*</font>
									</td>
									<td class="txtR" width="15%">字典名称：</td>
									<td width="35%">
										<input id="dictName" class="ipt" value="" data-maxlength="100"/>
										&nbsp;<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="txtR">是否启用：</td>
									<td colspan="3">
										<s:iterator value="@util.BaseParameter@STATUS">
											<label><input type="radio" id="statusFlag<s:property value='key'/>" name="statusFlag" value="<s:property value='key'/>" <s:if test="@util.BaseParameter@STATUS_ENABLE==key">checked</s:if> /><s:property value="value"/></label>
										</s:iterator>
									</td>
								</tr>
								<tr>
									<td class="txtR">备注：</td>
									<td colspan="3"><textarea id="dictMemo" class="input_area" data-maxlength="255"></textarea></td>
								</tr>
							</table>
							<div class="btnbarBig">
								<div id="addBut" style="display:none;"><a  href="javascript:void(null)" onclick='prepareAdd();' class="btnSix" >添加下级菜单</a>&nbsp;</div>
								<a id="midBut" href="javascript:void(null)" onclick='saveOrUpdateSysModule();' class="btnG">添加</a>&nbsp;
								<div id="delBut" style="display:none;"><a href="javascript:void(null)" onclick='delSysModule();' class="btnR" >删除</a></div>
							</div>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
