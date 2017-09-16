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
		<!-- ueditor start -->
		<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
		<link rel="stylesheet" type="text/css" href="./js/ueditor/ueditor.css" />
		<!-- ueditor end -->
		<script>
			function onloadDate(){
				chkradio();
			}
			
			function chkradio(){
				var chkObjs = document.getElementsByName("sysNotice.noticeDateType");
				for(var i=0;i<chkObjs.length;i++){
		                if(chkObjs[i].checked){
			                 if(chkObjs[i].value=='<s:property value='@util.BaseParameter@NOTICE_DATE_TYPE_1'/>'){
			                	 document.getElementById("endDate").style.display="none";
			                	 document.getElementById("datas").value="0";
			                 }else{
			                	 document.getElementById("endDate").style.display="";
			                	 document.getElementById("datas").value="1";
			                 }
		                }
	            }
			}
			
			///<summary>获得字符串实际长度，中文2，英文1</summary>
		    ///<param name="str">要获得长度的字符串</param>
			function getLen(str) {
			    var realLength = 0, len = str.length, charCode = -1;
			    for (var i = 0; i < len; i++) {
			        charCode = str.charCodeAt(i);
			        if (charCode >= 0 && charCode <= 128) realLength += 1;
			        else realLength += 2;
			    }
			    return realLength;
			};
			
			var ueditor = null;
			jQuery(document).ready(function() {//加载web编辑器
				ueditor= UE.ui.Editor({
					initialFrameHeight: 300,
				});
				ueditor.render('noticeMemo');
			});
			function check(){
				jQuery("#noticeTitle").val(jQuery.trim(jQuery("#noticeTitle").val()));
				var noticeTitle = jQuery("#noticeTitle").val();
				if(noticeTitle == ""){
					Dialog.alertFocus("公告标题不能为空！", 'noticeTitle');
					return false;
				}
				
				var lenOfNoticeTitle = getLen(noticeTitle);
				if (lenOfNoticeTitle > 200) {
					Dialog.alertFocus("公告标题的长度不能超过200个字节！", 'noticeTitle');
					return false;
				}
				
				var datas = document.getElementById("datas").value;
				if(datas == '1'){
					var endDate=document.getElementById("noticeEndDate");
					if(endDate.value.length<=0){
						Dialog.alertFocus("公告失效日期不能为空！", 'noticeEndDate');
						return false;
					}
				}
				if(jQuery.trim(jQuery("#areaName").val())==""){
					Dialog.alertFocus("请选择所属区划！", 'areaName');
					return false;
				}
				/* if(jQuery.trim(jQuery("#ywType").val())=="0"){
					Dialog.alertFocus("请选择业务类型！", 'ywType');
					return false;
				} */
				if(jQuery.trim(ueditor.getContent())==""){
					Dialog.alertFocus("公告内容不能为空！", 'noticeMemo');
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
			}
		</script>
	</head>
	<body onload="onloadDate();" id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysNoticeList.action">公告管理</a><a><s:if test='sysNotice!=null'>修改</s:if><s:else>新增</s:else></a>
					</p>
					<a href="javascript:void(0)" class="back" onclick="back()">返回</a>
				</dt>
				<dd>
					<form name="myForm" method="post" action="editSysNotice.action" enctype="multipart/form-data">
						<input name="sysNotice.noticeId" type="hidden" value="<s:property value='sysNotice.noticeId'/>"/>
						<input name="sysNotice.delFlag" type="hidden" value="<s:property value='sysNotice.delFlag'/>"/>
						<input name="sysNotice.noticeAtta" type="hidden" value="<s:property value='sysNotice.noticeAtta'/>"/>
						<input name="sysNotice.insertDate" type="hidden" value="<s:property value='sysNotice.insertDate'/>"/>
						<input id="datas" type="hidden"  />
						<input type="hidden" name="areaId" value="<s:property value='areaId'/>"/>
						<input type="hidden" name="areaName" value="<s:property value='areaName'/>"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
							<tr>
								<th colspan="4"><p class="title"><s:if test='sysNotice!=null'>修改</s:if><s:else>新增</s:else></p></th>
							</tr>
							<tr>
								<td width="15%" class="txtR">公告标题：</td>
								<td width="35%"><input name="sysNotice.noticeTitle" id="noticeTitle" class="ipt" value="<s:property value='sysNotice.noticeTitle'/>" data-maxlength="255"/><font color="red">&nbsp;*</font></td>
								<td width="15%" class="txtR">公告类型：</td>
								<td width="35%">
									<s:iterator value="@util.BaseParameter@NOTICE_TYPE">
										<label><input type="radio" name="sysNotice.noticeType" value="<s:property value='key'/>"<s:if test="sysNotice==null&& key==@util.BaseParameter@NOTICE_TYPE_1">checked</s:if> <s:if test="key==sysNotice.noticeType">checked</s:if>/><s:property value='value'/></label>	
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属区划：</td>
								<td colspan="3">
									<input type="text" class="ipt_s" style="width: 20%;" id="areaName" value="<s:property value='sysNotice.sysArea.areaName'/>" readonly="readonly"/><font color="red">&nbsp;*</font>
						        	<input type="hidden" class="input_text" id="areaId" name="sysNotice.areaId" value="<s:property value='sysNotice.areaId'/>"/>
						        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
									<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
										<div class="ydlg-hd">区划选择器[双击选中]</div>
										<div class="ydlg-bd">
											<ul id="tree_area" class="ztree" ></ul>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="txtR">公告时间类型：</td>
								<td colspan="3">
										<s:iterator value="@util.BaseParameter@NOTICE_DATE_TYPE">
											<label><input type="radio" name="sysNotice.noticeDateType"  onclick="chkradio();" value="<s:property value='key'/>" <s:if test="sysNotice==null&& key==@util.BaseParameter@NOTICE_DATE_TYPE_1">checked</s:if> <s:if test="key==sysNotice.noticeDateType"> checked</s:if> /><s:property value='value'/></label>
										</s:iterator>
								</td>
								<%-- <td class="txtR">业务类型：</td>
								<td  width="35%">
									<select name="sysNotice.ywType" id="ywType" class="ipt_s">
									<option value="0">==请选择业务类型==</option>
									<s:iterator value="paramServiceTypeList">
										<option value="<s:property value='dictId'/>"   <s:if test="sysNotice.ywType.equals(dictId)" >selected="selected"</s:if>><s:property value='dictName'/></option>
									</s:iterator>
									</select><font color="red">&nbsp;*</font>
								</td> --%>
							</tr>
							<tr id="endDate" style="display: none;">
								<td class="txtR">公告失效日期：</td>
								<td colspan="3">
									<input size="40" name="sysNotice.noticeEndDate" id="noticeEndDate" value="<s:date name='sysNotice.noticeEndDate' format='yyyy-MM-dd'/>" class="ipt_s Wdate" onfocus="WdatePicker()" readonly /><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">附件上传：</td>
								<td width="85%" colspan="3">
								<s:if test="sysNotice.noticeAtta!=null&&sysNotice.noticeAtta!=''">
								<p><a href="FileDownload?filepath=<s:property value='sysNotice.noticeAtta'/>">点击下载</a></p></s:if>
									<input class="input_text" type="text" id="txt" name="txt" />
									<input type="button" value="上传..." class="btn_select" />
									<input class="input_file" size="30" name="noticefile" type="file" onchange="txt.value=this.value" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td class="txtR">公告内容：</td>
								<td colspan="3" >
									<!-- 加载编辑器的容器 -->
									<script id="noticeMemo" name="sysNotice.noticeMemo" type="text/plain" style="height:250px;width:100%;margin:0 auto;">
										<s:property value='sysNotice.noticeMemo' escape='false'/>
    					 	 		</script>
								</td>
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
		</script>
	</body>
</html>
