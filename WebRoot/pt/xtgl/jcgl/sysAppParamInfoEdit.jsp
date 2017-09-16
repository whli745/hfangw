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
						当前位置：<a>系统管理</a><a>基础管理</a><a>系统参数</a>
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
									url : "getSysAppParamTreeAjax.action",
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
									jQuery.ajax({ tgetObjpe: "POST", url: "getJSONSysAppParam.action?RANDOM="+Math.random(), data: 'paramId='+treeNode.id,
									    success: function(msg){
									    	if(msg!=''){
									    		var type=eval('('+msg+')');
									    		getObj('paramId').value=type.paramId;
									    		getObj('parentId').value=type.parentId;
									    		getObj('paramCode').value=type.paramCode;
									    		getObj('paramName').value=type.paramName;
									    		getObj('paramDesc').value=type.paramDesc;
									    		getObj('paramVal').value=type.paramVal;
									    		getObj('paramSort').value=type.paramSort;
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
								}else{//增加一级参数
									addOrModi('add');
									getObj('paramId').value='';
									getObj('parentId').value=TYPE_ROOT_ID;
									getObj('paramCode').value='';
									getObj('paramName').value='';
									getObj('paramDesc').value='';
									getObj('paramVal').value='';
									getObj('paramSort').value='';
									getObj('paramCode').focus();
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
							//保存、修改参数
							function saveOrUpdateSysModule(){
								if(jQuery.trim(getObj('paramCode').value)==''){
									Dialog.alertFocus("参数编码不能为空！",'paramCode');
							    	return false;
							    }
							    
							    if(jQuery.trim(getObj('paramName').value)==''){
							    	Dialog.alertFocus("参数名称不能为空！",'paramName');
							    	return false;
							    }
							    jQuery.ajax({
									type: "POST",
						    		url: "chkSysAppParamCodeUniqueAjax.action",
							    	data:   'paramId='+getObj('paramId').value+ //封装对象
							    			'&paramCode='+getObj('paramCode').value,
								    success: function(msg){
								    	if(msg!='' && msg =='true'){
								    		jQuery.ajax({
												type: "POST",
									    		url: "editSysAppParamAjax.action",
										    	data:   'sysAppParam.paramId='+getObj('paramId').value+ //封装对象
										    			'&sysAppParam.parentId='+getObj('parentId').value+
										    			'&sysAppParam.paramCode='+getObj('paramCode').value+
										    			'&sysAppParam.paramSort='+getObj('paramSort').value+
										    			'&sysAppParam.paramDesc='+getObj('paramDesc').value+
										    			'&sysAppParam.paramVal='+getObj('paramVal').value+
										    			'&sysAppParam.usingFlag='+jQuery(":radio:checked").val()+
										    			'&sysAppParam.paramName='+getObj('paramName').value,
											    success: function(msg){
											    	if(msg!=''){
											    		if(opt=='add'){
											    			getObj('paramId').value=getObj('paramId').value+','+treeObj.getSelectedNodes()[0].id;
															var newNodes = [{id:msg,name:getObj('paramName').value}];
															newNodes = treeObj.addNodes(treeObj.getSelectedNodes()[0], newNodes);
															newNodes = treeObj.getNodeByParam("id", msg, null);
											    			getObj('paramId').value=msg;
											    			treeObj.selectNode(newNodes);
															treeClick(event,'treeDemo',newNodes);
											    		}else{
											    			var thisNode = treeObj.getNodeByParam("id", msg, null);
											    			thisNode.name = getObj('paramName').value;
											    			treeObj.updateNode(thisNode);
											    			Dialog.alert('修改成功！');
											    		}
											    		addOrModi('modi');
											    	}
												}
											});
								    	}else{
								    		Dialog.alert('参数编码不允许重复，请录入其他参数编码');
								    	}
									}
								});
							}
							function addOrModi(type){
								getObj('addBut').style.display=(type=='add'?"none":"inline");
// 								getObj('delBut').style.display=(type=='add'?"none":"inline");//是否显示删除按钮
								getObj('midBut').innerHTML=(type=='add'?"添加":"修改");//添加或修改
								getObj('paramCodeTip').innerHTML=(type=='add'?"*":"参数编码不支持修改");
								getObj('paramCodeTip').color=(type=='add'?"red":"#267FD3");
								getObj('paramCode').readOnly=(type=='add'?"":"readOnly");
								opt=(type=='add'?'add':'modi');//标记操作状态
							}
							function prepareAdd(){//添加下一级参数
								addOrModi('add');
								getObj('parentId').value=getObj('paramId').value;
								getObj('paramId').value='';
						   		getObj('paramName').value='';
						   		getObj('paramCode').value='';
						   		getObj('paramSort').value='';
						   		getObj('paramDesc').value='';
						   		getObj('paramVal').value='';
							}
							String.prototype.trim=function(){
						　　    return this.replace(/(^\s*)|(\s*jQuery)/g, "");
						　　 }
							
							function delSysModule(){//删除参数
								var t='';
								if(treeObj.getSelectedNodes()[0].isParent)t='该参数下存在子参数，删除该参数会同时删除该参数下所有子参数，确定要删除吗？';
								else t='确定要删除该参数吗？';
								Dialog.confirm(t,function(){
									jQuery.ajax({
										type: "POST",
									    url: "delSysAppParamAjax.action",
									    data: 'paramId='+treeObj.getSelectedNodes()[0].id,
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
						<div style="float:left;display:inline;width:69%;">
							<input type="hidden" id="paramId" value=""/>
							<input type="hidden" id="parentId" value="<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>"/>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
								<tr>
									<th colspan="4"><p class="title">系统参数信息</p></th>
								</tr>
								<tr>
									<td width="15%" class="txtR">参数编码：</td>
									<td colspan="3">
										<input id="paramCode" title="参数编码保存后将不允许修改" class="ipt" size="20" value="" data-maxlength="50"/>
										&nbsp;<font id="paramCodeTip" color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="txtR" width="15%">参数名称：</td>
									<td colspan="3">
										<input id="paramName" class="ipt" value="" data-maxlength="100"/>
										&nbsp;<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td width="15%" class="txtR">参数值：</td>
									<td colspan="3">
										<input id="paramVal" class="ipt" size="20" value="" data-maxlength="200"/>
									</td>
								</tr>
								<tr>
									<td width="15%" class="txtR">是否启用：</td>
									<td width="35%">
										<s:iterator value="@util.BaseParameter@STATUS">
											<label><input type="radio" id="usingFlag<s:property value='key'/>" name="usingFlag" value="<s:property value='key'/>" <s:if test="@util.BaseParameter@STATUS_ENABLE==key">checked</s:if> /><s:property value="value"/></label>
										</s:iterator>
									</td>
									<td width="15%" class="txtR">参数排序：</td>
									<td width="35%">
										<input style="width: 30px;" id="paramSort" class="ipt" value="" data-maxlength="5"/>
									</td>
								</tr>
								<tr>
									<td class="txtR">描述：</td>
									<td colspan="3"><textarea id="paramDesc" class="input_area" data-maxlength="500"></textarea></td>
								</tr>
							</table>
							<div class="btnbarBig">
								<div id="addBut" style="display:none;"><a  href="javascript:void(null)" onclick='prepareAdd();' class="btnSix" >添加下级参数</a>&nbsp;</div>
								<a id="midBut" href="javascript:void(null)" onclick='saveOrUpdateSysModule();' class="btnG">添加</a>&nbsp;
<!-- 								<div id="delBut" style="display:none;"><a href="javascript:void(null)" onclick='delSysModule();' class="btnR" >删除</a></div> -->
							</div>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
