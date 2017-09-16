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
						当前位置：<a>网站管理</a><a>配置管理</a><a>办证主题分类</a>
					</p>
				</dt>
				<dd>
					<div>
						<div style="width:30%; height: 450px; border :1px solid Silver; float:left; margin-right:3px;overflow: auto;">
							<ul id="treeDemo" class="ztree" ></ul>
						</div>
						<script type="text/javascript">
							var opt='add';
							var TYPE_ROOT_ID='<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>';
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
									url : "getZjblTypeTreeAjax.action",
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
								if(treeNode.id!=TYPE_ROOT_ID){
									jQuery.ajax({ tgetObjpe: "POST", url: "getJSONZjblType.action?RANDOM="+Math.random(), data: 'typeId='+treeNode.id,
									    success: function(msg){
									    	if(msg!=''){
									    		var type=eval('('+msg+')');
									    		getObj('typeId').value=type.typeId;
									    		getObj('parentId').value=type.parentId;
									    		getObj('typeCode').value=type.typeCode;
									    		getObj('typeName').value=type.typeName;
									    		getObj('typeMemo').value=type.typeMemo;
									    		getObj('typeLogo').value=type.typeLogo;
									    		getObj('typeUrl').value=type.typeUrl;
									    		getObj('typeSort').value=type.typeSort;
									    		if(type.usingFlag==<s:property value="@util.BaseParameter@STATUS_ENABLE"/>){
									    			getObj('usingFlag'+<s:property value="@util.BaseParameter@STATUS_ENABLE"/>).checked=true;
									    			getObj('usingFlag'+<s:property value="@util.BaseParameter@STATUS_DISABLE"/>).checked=false;
									    		}else if(type.usingFlag==<s:property value="@util.BaseParameter@STATUS_DISABLE"/>){
									    			getObj('usingFlag'+<s:property value="@util.BaseParameter@STATUS_ENABLE"/>).checked=false;
									    			getObj('usingFlag'+<s:property value="@util.BaseParameter@STATUS_DISABLE"/>).checked=true;
									    		}
									    		addOrModi('modi');//是否可以添加
									    	}
										}
									});
								}else{//增加一级分类
									addOrModi('add');
									getObj('typeId').value='';
									getObj('parentId').value=TYPE_ROOT_ID;
									getObj('typeCode').value='';
									getObj('typeName').value='';
									getObj('typeMemo').value='';
									getObj('typeLogo').value='';
									getObj('typeUrl').value='';
									getObj('typeSort').value='';
									getObj('typeCode').focus();
								}
							}
							//加载树
							jQuery(document).ready(function() {
								jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
								treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
								openTree();
							});
							function openTree(){
								var node=treeObj.getNodeByParam("id", TYPE_ROOT_ID, null);
								if(node!=null){
									treeObj.expandNode(node, true,false, true, true);
									treeObj.selectNode(node);
									treeClick(event,'treeDemo',node);
									return false;
								}else setTimeout('openTree()',50);
							}
							//保存、修改分类
							function saveOrUpdateSysModule(){
								if(jQuery.trim(getObj('typeCode').value)==''){
									Dialog.alertFocus("分类编号不能为空！",'typeCode');
							    	return false;
							    }
							    
							    if(jQuery.trim(getObj('typeName').value)==''){
							    	Dialog.alertFocus("分类名称不能为空！",'typeName');
							    	return false;
							    }
							    jQuery.ajax({
									type: "POST",
						    		url: "chkZjblTypeCodeUniqueAjax.action",
							    	data:   'typeId='+getObj('typeId').value+ //封装对象
							    			'&typeCode='+getObj('typeCode').value,
								    success: function(msg){
								    	if(msg!='' && msg =='true'){
								    		jQuery.ajax({
												type: "POST",
									    		url: "editZjblTypeAjax.action",
										    	data:   'zjblType.typeId='+getObj('typeId').value+ //封装对象
										    			'&zjblType.parentId='+getObj('parentId').value+
										    			'&zjblType.typeCode='+getObj('typeCode').value+
										    			'&zjblType.typeSort='+getObj('typeSort').value+
										    			'&zjblType.typeMemo='+getObj('typeMemo').value+
										    			'&zjblType.typeLogo='+getObj('typeLogo').value+
										    			'&zjblType.typeUrl='+getObj('typeUrl').value+
										    			'&zjblType.usingFlag='+jQuery(":radio:checked").val()+
										    			'&zjblType.typeName='+getObj('typeName').value,
											    success: function(msg){
											    	if(msg!=''){
											    		if(opt=='add'){
											    			getObj('typeId').value=getObj('typeId').value+','+treeObj.getSelectedNodes()[0].id;
															var newNodes = [{id:msg,name:getObj('typeName').value}];
															newNodes = treeObj.addNodes(treeObj.getSelectedNodes()[0], newNodes);
															newNodes = treeObj.getNodeByParam("id", msg, null);
											    			getObj('typeId').value=msg;
											    			treeObj.selectNode(newNodes);
															treeClick(event,'treeDemo',newNodes);
											    		}else{
											    			var thisNode = treeObj.getNodeByParam("id", msg, null);
											    			thisNode.name = getObj('typeName').value;
											    			treeObj.updateNode(thisNode);
											    			Dialog.alert('修改成功！');
											    		}
											    		addOrModi('modi');
											    	}
												}
											});
								    	}else{
								    		Dialog.alert('分类编码不允许重复，请录入其他分类编码');
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
							function prepareAdd(){//添加下一级分类
								addOrModi('add');
								getObj('parentId').value=getObj('typeId').value;
								getObj('typeId').value='';
						   		getObj('typeName').value='';
						   		getObj('typeCode').value='';
						   		getObj('typeSort').value='';
						   		getObj('typeMemo').value='';
						   		getObj('typeLogo').value='';
						   		getObj('typeUrl').value='';
						   		
							}
							String.prototype.trim=function(){
						　　    return this.replace(/(^\s*)|(\s*jQuery)/g, "");
						　　 }
							
							function delSysModule(){//删除分类
								var t='';
								if(treeObj.getSelectedNodes()[0].isParent)t='该分类下存在子分类，删除该分类会同时删除该分类下所有子分类，确定要删除吗？';
								else t='确定要删除该分类吗？';
								Dialog.confirm(t,function(){
									jQuery.ajax({
										type: "POST",
									    url: "delZjblTypeAjax.action",
									    data: 'typeId='+treeObj.getSelectedNodes()[0].id,
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
							<input type="hidden" id="typeId" value=""/>
							<input type="hidden" id="parentId" value="<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>"/>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
								<tr>
									<th colspan="4"><p class="title">办证主题分类信息</p></th>
								</tr>
								<tr>
									<td width="15%" class="txtR">分类编号：</td>
									<td width="35%">
										<input id="typeCode" class="ipt" size="20" value="" data-maxlength="20"/>
										&nbsp;<font color="red">*</font>
									</td>
									<td class="txtR" width="15%">分类名称：</td>
									<td width="35%">
										<input id="typeName" class="ipt" value="" data-maxlength="100"/>
										&nbsp;<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td width="15%" class="txtR">分类图标：</td>
									<td width="35%">
										<input id="typeLogo" class="ipt" size="20" value="" data-maxlength="200"/>
										&nbsp;<font color="red">*</font>
									</td>
									<td class="txtR" width="15%">分类链接：</td>
									<td width="35%">
										<input id="typeUrl" class="ipt" value="" data-maxlength="200"/>
										&nbsp;<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="txtR">是否启用：</td>
									<td>
										<s:iterator value="@util.BaseParameter@STATUS">
											<label><input type="radio" id="usingFlag<s:property value='key'/>" name="usingFlag" value="<s:property value='key'/>" <s:if test="@util.BaseParameter@STATUS_ENABLE==key">checked</s:if> /><s:property value="value"/></label>
										</s:iterator>
									</td>
									<td class="txtR">分类排序：</td>
									<td>
										<input style="width: 30px;" id="typeSort" class="ipt" value="" data-maxlength="5"/>
									</td>
								</tr>
								<tr>
									<td class="txtR">备注：</td>
									<td colspan="3"><textarea id="typeMemo" class="input_area" data-maxlength="200"></textarea></td>
								</tr>
							</table>
							<div class="btnbarBig">
								<div id="addBut" style="display:none;"><a  href="javascript:void(null)" onclick='prepareAdd();' class="btnSix" >添加下级分类</a>&nbsp;</div>
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
