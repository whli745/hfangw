<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				jQuery("#listTab1 tr:even").addClass("alt");
				jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
				initTableTitle('listTab1');
			});
			
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
				jQuery("#serviceType").val("");
			
			}
		
		function clearLog() {
			Dialog.confirm('清理后无法恢复，是否确定清理？',function(){ 
				myForm.action='clearSysLog.action';
				myForm.submit();
			},'','','','2');
		}
	</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
				<div class="ydlg-hd">区划选择器[双击选中]</div>
				<div class="ydlg-bd">
					<ul id="tree_area" class="ztree" ></ul>
				</div>
			</div>
			<dl class="mtab">
				<dd>
					<form id="myForm" name="myForm" action="queryLogInfoList.action" method="get">
						<ul class="inputinfo">
							<li>
								<span>所属区划：</span>
								<div style="float: left;">
									<input type="text" id="areaName" name="areaName" value="<s:property value='areaName' />" class="ipt_s" readonly="readonly" />&nbsp;<input id="m-areaTree" type="button" class="btn_select" value="选择..." style="cursor: pointer;"/>&nbsp;
									<input type="hidden" id="areaId" name="areaId" value="<s:property value='areaId' />" class="ipt_s" />
								</div>
								<span>操作用户：</span>
								<input type="text" name="userName" value="<s:property value='userName' />" class="ipt_s" />
							</li>
							<li style="margin-top: 5px;">
								<span>操作时间：</span>
								<input type="text" name="startDate" class="ipt_s Wdate" value="<s:date name='startDate' format='yyyy-MM-dd'/>" onfocus="WdatePicker()" readonly="readonly" />
								<span>至</span>
								<input type="text" name="endDate" class="ipt_s Wdate" value="<s:date name='endDate' format='yyyy-MM-dd'/>" onfocus="WdatePicker()" readonly="readonly" />
								<input type="submit" value="查询" class="btn"/>
								<input type="button" value="日志清理" class="btnFour" onclick="clearLog()"/>
							</li>
						</ul>
					</form>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1" style="margin-top: 40px;">
						<caption><p class="title">日志管理</p></caption>
						<tr>
							<td class="datatitle" style="width: 8%">序号</td>
							<td class="datatitle" style="width: 10%">所属区划</td>
							<td class="datatitle" style="width: 20%">操作人</td>
							<td class="datatitle">日志内容</td>
							<td class="datatitle" style="width: 15%">操作时间</td>
							<td class="datatitle" style="width: 15%">操作IP</td>
							<td class="datatitle" style="width: 15%">操作</td>
						</tr>			
						<s:iterator value="logInfoList" status="st">
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td><s:property value="sysArea.areaName" /></td>
								<td style="text-align: left;"><s:property value="userName" /></td>
								<td style="text-align: left;"><s:property value="actText" /></td>
								<td><s:date name="actTime" format="yyyy-MM-dd HH:mm" /></td>
								<td><s:property value="ip" /></td>
								<td>
									<a href="javascript:void(0)" onclick="Dialog.confirm('是否确定删除该日志？',function(){window.location.href='deleteLogInfo.action?oid=<s:property value='oid'/>&userName=<s:property value='[1].userName'/>&areaId=<s:property value='[1].areaId' />&areaName=<s:property value='areaName' />&serviceType=<s:property value='[1].serviceType' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />'},'','','','2')">删除</a>&nbsp;|&nbsp;
									<a href='queryLogInfo.action?oid=<s:property value="oid"/>'>查看</a>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" class="datatitle">
								<%@ include file="../../../include/pagination.inc" %>
							</td>
						</tr>
					</table>
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
