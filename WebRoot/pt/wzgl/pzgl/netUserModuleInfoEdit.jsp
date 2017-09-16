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
		<base href="<%=basePath%>"/>
		<%@ include file="../../../include/heard.inc"%>
		<script type="text/javascript" src="./js/swfobject.js"></script>
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
					<p class="position">当前位置：<a>网站管理</a><a>内容管理</a><a>用户菜单管理</a>
					</p>
				</dt>
				<dd>
					<div>
						<div style="width:30%; height: 450px; border :1px solid Silver; float:left; margin-right:3px; overflow: auto;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
						<script type="text/javascript">
							var opt='add';
							var SYSMODULE_ROOT_ID='<s:property value="@util.BaseParameter@SYSTREE_ROOT_ID"/>';
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
									url : "getNetUserModuleTreeAjax.action",
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
								if(treeNode.id!=SYSMODULE_ROOT_ID){
									jQuery.ajax({ type: "get", url: "getJSONNetUserModule.action?rand=" + Math.random(), data: 'moduleId='+treeNode.id,
									    success: function(msg){
									    	if(msg!=''){
									    		var netUserModule=eval('('+msg+')');
									    		getObj('moduleId').value=netUserModule.moduleId;
									    		getObj('parentId').value=netUserModule.parentId;
									    		getObj('moduleName').value=netUserModule.moduleName;
									    		getObj('moduleUrl').value=netUserModule.moduleUrl;
									    		getObj('moduleDesc').innerHTML=netUserModule.moduleDesc;
									    		getObj('moduleOrder').value=netUserModule.moduleOrder;
									    		getObj('validateContent').value=netUserModule.validateContent;
									    		var controlType = document.getElementsByName("controlType");
									    		for(var i = 0; i < controlType.length; i ++) {
									    			if(controlType[i].value==netUserModule.controlType) {
									    				controlType[i].checked=true;
									    				break;
									    			}
									    		}
												var isValidate = document.getElementsByName("isValidate");
												for(var i = 0; i < isValidate.length; i ++) {
									    			if(isValidate[i].value==netUserModule.isValidate) {
									    				isValidate[i].checked=true;
									    				if(isValidate[i].value == '1') {
									    					getObj("validateContentTR").style.display='inline';
									    				} else {
									    					getObj("validateContentTR").style.display='none';
									    				}
									    				break;
									    			}
									    		}
												var isJsRequest = document.getElementsByName("isJsRequest");
												for(var i = 0; i < isJsRequest.length; i ++) {
									    			if(isJsRequest[i].value==netUserModule.isJsRequest) {
									    				isJsRequest[i].checked=true;
									    				break;
									    			}
									    		}
									    		if(netUserModule.moduleType=='1'){//加载菜单类别
									    			getObj('pt').checked=true;
									    			getObj('kjidTr').style.display='none';
									    			getObj('moduleOrderTr').style.display='';//是否显示菜单排序
									    		}else{
									    			getObj('moduleOrderTr').style.display='none';
									    			getObj('gn').checked=true;
									    			getObj('kjidTr').style.display='';
									    		}
									    		getObj('moduleControl').value=netUserModule.moduleControl;
									    		if(netUserModule.moduleStatus=='0')getObj('r1').checked=true;
									    		else getObj('r2').checked=true;//是否启用
									    		if(netUserModule.moduleLevel!=1){//根据菜单级别判断
									    			getObj('moduleTypeTr').style.display='';//菜单类别是否显示
									    		}else{
									    			getObj('moduleTypeTr').style.display='none';//菜单类别
									    		}
									    		if(netUserModule.moduleLevel==4){
									    			getObj('pt').disabled=true;//根据菜单级别判断菜单类别是否禁用
									    		}else{
									    			getObj('pt').disabled=false;
									    		}
									    		if(netUserModule.moduleLevel!=2){//根据菜单级别判断菜单地址是否必填
									    			getObj('moduleUrlFont').style.display='inline';
									    		}else{
									    			getObj('moduleUrlFont').style.display='none';
									    		}
									    		getObj('moduleLevel').value=netUserModule.moduleLevel;
									    		if(treeNode.isParent){ //是否有子菜单，如有子菜单则不能修改菜单类别
									    			getObj('gn').disabled=true; 
									    		}else{
									    			getObj('gn').disabled=false;
									    		}
									    		getObj('moduleDate').value=netUserModule.moduleDate;
									    		getObj('modulePids').value=netUserModule.modulePids;
									    		addOrModi('modi');//是否可以添加
									    	}
										}
									});
								}else{//增加一级菜单
									addOrModi('add');
									getObj('parentId').value=SYSMODULE_ROOT_ID;
									getObj('moduleId').value='';
						    		getObj('moduleName').value='';
						    		getObj('moduleUrl').value='';
						    		getObj('moduleUrlFont').style.display='inline';
						    		getObj('moduleDesc').innerHTML='';
						    		getObj('moduleControl').value='';
						    		getObj('r1').checked=true;
						    		getObj('moduleTypeTr').style.display='none';
						    		getObj('moduleLevel').value=0;
						    		getObj('moduleOrder').value='';
						    		getObj('modulePids').value='';
						    		getObj('validateContent').value='';
						    		document.getElementsByName("isValidate")[0].checked=true;;
						    		
								}
							}
							//加载树
							jQuery(document).ready(function() {
								jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
								treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
								openTree();
							});
							function openTree(){
								var node=treeObj.getNodeByParam("id", SYSMODULE_ROOT_ID, null);
								if(node!=null){
									treeObj.expandNode(node, true,false, true, true);
									treeObj.selectNode(node);
									treeClick(event,'treeDemo',node);
									return false;
								}else setTimeout('openTree()',50);
							}
							//保存、修改菜单
							function saveOrUpdateNetUserModule(){
								
								if(getObj('moduleName').value.trim()==''){
									Dialog.alertFocus('菜单名称不能为空！', 'moduleName');
									return false;
								}
								if(getObj('moduleUrl').value.trim()==''&&getObj('moduleUrlFont').offsetWidth>0){//控件可见时提示
									Dialog.alertFocus('菜单地址不能为空！', 'moduleUrl');
									return false;
								}
								if(getObj('moduleControl').value.trim()==''&&getObj('moduleControl').offsetWidth>0){
									Dialog.alertFocus('控件ID不能为空！', 'moduleControl');
									return false;
								}
								if(getObj('moduleOrder').value.trim()==''&&getObj('moduleOrder').offsetWidth>0){
									Dialog.alertFocus('菜单排序不能为空！', 'moduleOrder');
									return false;
								}
								
								var controlType = document.getElementsByName("controlType");
								for(var i = 0; i < controlType.length; i++) {
									if(controlType[i].checked) {
										controlType =controlType[i].value
										break;
									}
								}
								var isValidate = document.getElementsByName("isValidate");
								if(null != isValidate && isValidate[0].checked) {
									isValidate=isValidate[0].value;
								} else {
									isValidate =isValidate[1].value
								}
								if(isValidate == "1"){
									if(getObj('validateContent').value.trim()==''){
										Dialog.alertFocus('验证提示内容不能为空！', 'validateContent');
										return false;
									}
								}
								
								var isJsRequest = document.getElementsByName("isJsRequest");
								if(null != isJsRequest && isJsRequest[0].checked) {
									isJsRequest=isJsRequest[0].value;
								} else {
									isJsRequest =isJsRequest[1].value
								}
								
								Dialog.confirm('确定要'+(opt=='add'?'增加':'修改')+'吗？',function(){
									var moduleTpe;
									if(getObj('pt').checked){
										moduleTpe="1";
										getObj('moduleControl').value='';
									}else moduleTpe="2";
									var status;
									if(getObj('r1').checked)status="0";
									else status="1";
									jQuery.ajax({
											type: "post",
								    		url: "editNetUserModuleAjax.action",
									    	data:   'netUserModule.moduleId='+getObj('moduleId').value+ //封装对象
												    '&netUserModule.parentId='+getObj('parentId').value+
												    '&netUserModule.moduleName='+getObj('moduleName').value+
												    '&netUserModule.moduleUrl='+escape(getObj('moduleUrl').value)+
												    '&netUserModule.moduleDesc='+getObj('moduleDesc').innerHTML+
												    '&netUserModule.moduleControl='+(getObj('moduleControl').offsetWidth>0?getObj('moduleControl').value:'')+
												    '&netUserModule.moduleStatus='+status+
												    '&netUserModule.moduleType='+moduleTpe+
												    '&netUserModule.controlType='+controlType+
												    '&netUserModule.isValidate='+isValidate+
												    '&netUserModule.validateContent='+getObj('validateContent').value+
												    '&netUserModule.isJsRequest='+isJsRequest+
												    '&netUserModule.moduleDate='+getObj('moduleDate').value+
												    '&netUserModule.moduleOrder='+getObj('moduleOrder').value+
												    '&netUserModule.moduleLevel='+(opt=='add'?parseInt(getObj('moduleLevel').value)+1:parseInt(getObj('moduleLevel').value))+//菜单级别
												    '',
										    success: function(msg){
										    	if(msg!=''){
										    		if(opt=='add'){
// 										    			getObj('modulePids').value=getObj('modulePids').value+','+treeObj.getSelectedNodes()[0].id;
														var newNodes = [{id:msg,name:getObj('moduleName').value}];
														newNodes = treeObj.addNodes(treeObj.getSelectedNodes()[0], newNodes);
														newNodes = treeObj.getNodeByParam("id", msg, null);
										    			getObj('moduleId').value=msg;
										    			getObj('moduleLevel').value=(opt=='add'?parseInt(getObj('moduleLevel').value)+1:parseInt(getObj('moduleLevel').value));
										    			treeObj.selectNode(newNodes);
														treeClick(event,'treeDemo',newNodes);
										    		}else{
										    			var thisNode = treeObj.getNodeByParam("id", msg, null);
										    			thisNode.name = getObj('moduleName').value;
										    			treeObj.updateNode(thisNode);
										    		}
										    		Dialog.alert("保存成功！");
										    		addOrModi('modi');
										    	}
											}
										});
								});
							}
							function addOrModi(type){
								if(getObj('moduleLevel').value==4||getObj('gn').checked)getObj('addBut').style.display='none';//最后一级菜单不可继续添加下一级菜单;功能菜单不可继续添加下级菜单
								else getObj('addBut').style.display=(type=='add'?"none":"inline");
								getObj('delBut').style.display=(type=='add'?"none":"inline");//是否显示删除按钮
								getObj('midBut').innerHTML=(type=='add'?"添加":"修改");//添加或修改
								opt=(type=='add'?'add':'modi');//标记操作状态
							}
							function prepareAdd(){//添加下一级菜单
								addOrModi('add');
								getObj('parentId').value=getObj('moduleId').value;
								getObj('moduleId').value='';
						   		getObj('moduleName').value='';
						   		getObj('moduleUrl').value='';
						   		getObj('moduleDesc').innerHTHL='';
						   		getObj('moduleDesc').value='';
						   		if(getObj('moduleLevel').value==3){
						   			getObj('pt').disabled=true;
						   			getObj('gn').checked=true;//功能菜单
						   			getObj('gn').disabled=false;
						   			getObj('gn').onclick();
						   			getObj('moduleOrderTr').style.display='none';
						   		}else{
						   			getObj('pt').checked=true;//普通菜单
						   			getObj('gn').disabled=false;
						   		}
						   		if(getObj('moduleLevel').value!=1){
						   			getObj('moduleUrlFont').style.display='inline';//菜单地址是否必填
						   		}else{
						   			getObj('moduleUrlFont').style.display='none';
						   		}
						   		getObj('moduleControl').value='';
						   		getObj('r1').checked=true;//是否启用
						   		getObj('moduleTypeTr').style.display='';
						   		getObj('moduleOrder').value='';
							}
							String.prototype.trim=function(){
						　　    return this.replace(/(^\s*)|(\s*jQuery)/g, "");
						　　 }
							function showOrHidden(id,method){//菜单类别选择交互
								getObj(id).style.display=method;
								if(method=='')getObj('moduleUrlFont').style.display='inline';//功能菜单必须填 菜单地址
								else{
									if(getObj('moduleLevel').value!=1){
						   				getObj('moduleUrlFont').style.display='inline';//菜单地址是否必填
						   			}else{
						   				getObj('moduleUrlFont').style.display='none';
						   			}								
								}
							}
							
							function isValidate(id,val){
								if(val==0) {
									getObj(id).style.display='none';//菜单地址是否必填
									getObj("validateContent").value='';
								} else {
									getObj(id).style.display='inline';//菜单地址是否必填
								}
							}
							function delNetUserModule(){//删除菜单
								var t='';
								if(treeObj.getSelectedNodes()[0].isParent)t='该菜单下存在子菜单，删除该菜单会同时删除该菜单下所有子菜单，确定要删除吗？';
								else t='确定要删除该菜单吗？';
								if(!confirm(t)){
									return false;
								}
								jQuery.ajax({
									type: "get",
								    url: "delNetUserModuleAjax.action",
								    data: 'moduleId='+treeObj.getSelectedNodes()[0].id,
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
								
							}
					</script>
						<div style="float:left;display:inline;width:66%;">
							<input type="hidden" id="moduleId" value="" />
							<input type="hidden" id="parentId" value="" />
							<input type="hidden" id="moduleLevel" value="" />
							<input type="hidden" id="moduleDate" value="" />
							<input type="hidden" id="modulePids" value="" />
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
								<tr><th colspan="2"><p class="title">菜单信息：</p></th></tr>
								<tr>
									<td width="25%" class="txtR">菜单名称：</td>
									<td><input class="ipt_s" size="40" id="moduleName" value="" data-maxlength="100"/><font color="red">&nbsp;*</font></td>
								</tr>
								<tr>
									<td class="txtR">菜单地址：</td>
									<td><input class="ipt_s" size="40" id="moduleUrl" value="" data-maxlength="100"/><font id="moduleUrlFont" color="red" style="display:none;">&nbsp;*</font></td>
								</tr>
								<tr>
									<td class="txtR">菜单描述：</td>
									<td><textarea class="input_area" id="moduleDesc" data-maxlength="200"></textarea></td>
								</tr>
								<tr id="moduleTypeTr" style="display:none;">
									<td class="txtR">菜单类别：</td>
									<td>
										<label>
											<input type="radio" id="pt" value="1" name="moduleType"onclick="showOrHidden('kjidTr','none')" checked="checked" />普通菜单
										</label>
										<label>
											<input type="radio" id="gn" value="2" name="moduleType"onclick="showOrHidden('kjidTr','')" />功能菜单
										</label>
									</td>
								</tr>
								<tbody id="kjidTr" style="display:none;">
									<tr>
										<td class="txtR">控件Name</td>
										<td><input class="ipt_s" size="40" id="moduleControl" value="" data-maxlength="50"/><font color="red">&nbsp;*</font></td>
									</tr>
									<tr>
										<td class="txtR">控件类型：</td>
										<td>
											<s:iterator value="@util.BaseParameter@CONTROL_TYPE">
												<label>
													<input type="radio" name="controlType" value="<s:property value='key'/>" <s:if test="key==1">checked="checked"</s:if>/><s:property value="value"/>
												</label>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="txtR">是否需要验证：</td>
										<td>
											<s:iterator value="@util.BaseParameter@YES_NO">
												<label>
													<input type="radio" name="isValidate" value="<s:property value='key'/>" onclick="isValidate('validateContentTR','<s:property value='key'/>')" <s:if test="key==0">checked="checked"</s:if>/><s:property value="value"/>
												</label>
											</s:iterator>
										</td>
									</tr>
									<tr id="validateContentTR" style="display: none;">
										<td class="txtR">验证提示内容：</td>
										<td><input id="validateContent" name="validateContent" class="ipt" type="text" data-maxlength="100"/><font color="red">&nbsp;*</font></td>
									</tr>
									<tr>
										<td class="txtR">是否为JavaScript请求：</td>
										<td>
											<s:iterator value="@util.BaseParameter@YES_NO">
												<label>
													<input type="radio" name="isJsRequest" value="<s:property value='key'/>" <s:if test="key==0">checked="checked"</s:if>/><s:property value="value"/>
												</label>
											</s:iterator>
										</td>
									</tr>
								</tbody>
								<tr>
									<td class="txtR">
										菜单状态：
									</td>
									<td>
										<label>
											<input type="radio" id="r1" name="moduleStatus" value="0"
												checked="checked" />
											启用
										</label>
										<label>
											<input type="radio" id="r2" name="moduleStatus" value="1" />
											不启用
										</label>
									</td>
								</tr>
								<tr id="moduleOrderTr">
									<td class="txtR">
										菜单排序：
									</td>
									<td>
										<input class="ipt_s" size="40" id="moduleOrder" value="" data-maxlength="10"/><font color="red">&nbsp;*</font>
									</td>
								</tr>
							</table>
							<div class="btnbarBig">
								<div id="addBut" style="display:none;"><a  href="javascript:void(null)"
									onclick='prepareAdd();' class="btnSix" >添加下级菜单</a>&nbsp;</div>
								<a id="midBut" href="javascript:void(null)"
									onclick='saveOrUpdateNetUserModule();' class="btnG">添加</a>&nbsp;
								<div id="delBut" style="display:none;"><a href="javascript:void(null)"
									onclick='delNetUserModule();' class="btnR" >删除</a></div>
							</div>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
