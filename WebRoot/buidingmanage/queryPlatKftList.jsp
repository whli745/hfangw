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
		
			function markHandle(oid,flag){
				var t = '确定标记为已处理吗？';
				if(flag == 0){
					t = '确定标记为未处理吗？';
				}
				common.confirm(t,function(){
					jQuery.ajax({
						type: "POST",
			    		url: "markHandledAjax.action?random=" + Math.random(),
				    	data: 'oid=' + oid + '&h_flag=' + flag,
					    success: function(msg){
					    	if(msg!=''){
					    		location.reload();
					    	}
						}
					});
				},50);
			}
	</script>
	</head>
	<body>
		<div class="mainDiv">
			<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
				<div class="ydlg-hd">区划选择器[双击选中]</div>
				<div class="ydlg-bd">
					<ul id="tree_area" class="ztree" ></ul>
				</div>
			</div>
			<dl class="mtab">
				<dd>
					<form id="myForm" name="myForm" action="queryPlatKftList.action" method="get">
						<ul class="inputinfo">
							<li>
								<span>所属区划：</span>
								<div style="float: left;">
									<input type="text" id="areaName" name="areaName" value="<s:property value='areaName' />" class="ipt_s" readonly="readonly" />&nbsp;<input id="m-areaTree" type="button" class="btn_select" value="选择..." style="cursor: pointer;"/>&nbsp;
									<input type="hidden"  name="q.area_id" value="<s:property value='q.area_id' />" class="ipt_s" />
									<input type="hidden" id="areaId" name="areaId" value="<s:property value='areaId' />" class="ipt_s" />
								</div>
								<span>楼盘名称：</span>
								<input type="text" name="q.proj_name" value="<s:property value='q.proj_name' />" class="ipt_s" />
								<span>联系姓名：</span>
								<input type="text" name="q.lxxm" value="<s:property value='q.lxxm' />" class="ipt_s" />
							</li>
							<li style="margin-top: 5px;">
								<span>报名截止时间：</span>
								<input type="text" name="q.q_s_date" class="ipt_s Wdate" value="<s:date name='q.q_s_date' format='yyyy-MM-dd'/>" onfocus="WdatePicker()" readonly="readonly" />
								<span>至</span>
								<input type="text" name="q.q_e_date" class="ipt_s Wdate" value="<s:date name='q.q_e_date' format='yyyy-MM-dd'/>" onfocus="WdatePicker()" readonly="readonly" />
								<input type="submit" value="查询" class="btn"/>
								<div id="data_filter_div" style="text-align:left;margin-top: 2px;">
									<label style="margin-left: 2px;"><input type="radio" name="q.handle_flag" value="<s:property value='@util.BaseParameter@DATA_FILTER_1'/>"
									<s:if test="q.handle_flag eq @util.BaseParameter@DATA_FILTER_1">checked</s:if>/>&nbsp;全部</label>
									<label style="margin-left: 2px;"><input type="radio" name="q.handle_flag" value="<s:property value='@util.BaseParameter@DATA_FILTER_2'/>"
									<s:if test="q.handle_flag eq @util.BaseParameter@DATA_FILTER_2">checked</s:if>/>&nbsp;未处理</label>
									<label style="margin-left: 2px;"><input type="radio" name="q.handle_flag" value="<s:property value='@util.BaseParameter@DATA_FILTER_3'/>"
									<s:if test="q.handle_flag eq @util.BaseParameter@DATA_FILTER_3">checked</s:if>/>&nbsp;已处理</label>  
									<script>
										$(function(){
											$('#data_filter_div label').click(function(){
											   $("#myForm").submit();
											});
										});
									</script>
								</div>
							</li>
						</ul>
					</form>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1" style="margin-top: 40px;">
						<caption><p class="title">预约列表</p></caption>
						<tr>
							<td class="datatitle" style="width: 8%">序号</td>
							<td class="datatitle" style="width: 10%">所属区划</td>
							<td class="datatitle" style="width: 20%">楼盘名称</td>
							<td class="datatitle">联系人</td>
							<td class="datatitle" style="width: 15%">联系电话</td>
							<td class="datatitle" style="width: 15%">预约时间</td>
							<td class="datatitle" style="width: 10%">状态</td>
							<td class="datatitle" style="width: 15%">操作</td>
						</tr>			
						<s:iterator value="rp.resultList" status="st">
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td><s:property value="sysArea.areaName" /></td>
								<td style="text-align: left;"><s:property value="hfwKft.housingProject.proj_name" /></td>
								<td style="text-align: left;"><s:property value="lxxm" /></td>
								<td><s:property value="phone" /></td>
								<td><s:date name="yysj" format="yyyy-MM-dd HH:mm" /></td>
								<td>
									<s:if test='handle_flag.equals("0")'>
										<span style="color:red;">未处理</span>
									</s:if>
									<s:else>
										<span style="color:blue;">已处理</span>
									</s:else>
								</td>
								<td>
									<s:if test='handle_flag.equals("0")'>
										<a href='javascript:markHandle("<s:property value='oid' />",1)'><span style="color:red;">标记为已处理</span></a>
									</s:if>
									<s:else>
										<a href='javascript:markHandle("<s:property value='oid' />",0)'><span style="color:blue;">标记为未处理</span></a>
									</s:else>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="8" class="datatitle">
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
