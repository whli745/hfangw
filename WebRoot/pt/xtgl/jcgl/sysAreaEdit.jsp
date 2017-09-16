<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
	<script type='text/javascript' src='dwr/interface/AreaManageDwr.js'></script>
	<script language="javascript" defer="defer">
		var zTree1;
		var zNodes =<s:property value="sysAreaJson" escape="false"/>;
		// 控制按钮显示
		jQuery('#add').hide();
		var setting = {
			expandSpeed: "",
			callback: {
				beforeExpand: zTreeBeforeExpand,//展开树
				beforeCollapse: zTreeBeforeCollapse,//合并数
				onClick : treeOnClick//点击树的节点
			}
		};
	
		jQuery(document).ready(function(){
			zTree1 = jQuery.fn.zTree.init(jQuery('#tree'), setting, zNodes);
			var nodes = zTree1.getNodes();
			zTreeBeforeExpand('tree', nodes[0]);
			treeOnClick(event,'tree',nodes[0]);
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
			AreaManageDwr.getAreaInfo(treeNode.id, function(area){//调用区划DWR返回区划对象
				jQuery('#areaId').val(area.areaId);
				jQuery('#areaCode').val(jQuery.trim(area.areaCode));
				jQuery('#areaName').val(jQuery.trim(area.areaName));
				jQuery('#areaLevel').val(area.areaLevel);
				jQuery('#areaSort').val(jQuery.trim(area.areaSort));
				//以下为增加下一级区划使用
				jQuery('#parenId').val(jQuery.trim(area.areaId));
				jQuery('#parentCode').val(jQuery.trim(area.areaCode));
				jQuery('#parentLevel').val(jQuery.trim(area.areaLevel));
				jQuery('#parentPath').val(jQuery.trim(area.areaPath));
				// 控制按钮显示
				jQuery('#add').hide();
				jQuery('#next').show();
				jQuery('#modify').show();
				jQuery('#delete').show();
			});
		}
		
		function updateArea() {//保存与修改区划
		    var sysArea = new SysArea();
		    sysArea.areaId=jQuery.trim(jQuery('#areaId').val());
		    sysArea.areaCode=jQuery.trim(jQuery('#areaCode').val());
		    sysArea.areaName=jQuery.trim(jQuery('#areaName').val());
		    sysArea.areaLevel=jQuery('#areaLevel').val();
		    sysArea.areaSort=jQuery.trim(jQuery('#areaSort').val());
			
			Dialog.confirm('是否确定修改该区划？',function(){ // 弹出提示框，确认是否进行此操作
				AreaManageDwr.updateOrSaveArea(sysArea, function(msg){
					if(msg != '0') { // 修改成功
						var thisNode = zTree1.getSelectedNodes(); // 当前节点
						thisNode[0].name = jQuery.trim(jQuery('#areaName').val()); //给当前节点重命名
						zTree1.updateNode(thisNode[0]); //更新节点对象信息
					}
					this.top.ifm.frames["mainFrame"].name = "mainFrame";
				});
			});
		}
		
		function delArea() { // 删除区划
			var thisNode = zTree1.getSelectedNodes();// 当前节点
			if(thisNode[0].getPreNode()== null && thisNode[0].getParentNode() ==null && thisNode[0].getNextNode() ==null) {
				Dialog.alert("顶级区划不能删除！");
				return;
			}
			if(!thisNode[0].isParent) {
				Dialog.confirm('是否确定删除该区划？',function(){ // 弹出警告框，确认是否进行此操作
					AreaManageDwr.delArea(jQuery('#areaId').val(), function(msg){
						if(msg == "1") { // 删除成功
							var parentNode = thisNode[0].getPreNode(); // 当前节点的临近上一节点
							if(null == parentNode) {
								parentNode = thisNode[0].getParentNode(); // 当前节点的父节点
							}
							zTree1.removeNode(thisNode[0]); //移出当前节点
							zTree1.selectNode(parentNode); // 定位父节点
							treeOnClick(event,'tree',parentNode); // 重新调用鼠标点击事件
						}
					});
				},'','','','2');
			} else {
				Dialog.alert("区划目录不能删除！");
			}
		}
		
		function addNextArea() { // 开始新增下级区划
			var thisNode = zTree1.getSelectedNodes();// 当前节点
			if(null == thisNode || '' == thisNode) {
				Dialog.alert("请选择一个区划！");
			} else {
				jQuery('#areaId').val('');
				jQuery('#areaCode').val('');
				jQuery('#areaName').val('');
				jQuery('#areaSort').val('');
				var areaLevel = jQuery('#areaLevel').val();
				if(areaLevel > 4) {
					areaLevel = 4;
				} else {
					areaLevel ++;
				}
				jQuery('#areaLevel').val(areaLevel);
				jQuery('#add').show();
				jQuery('#next').hide();
				jQuery('#modify').hide();
				jQuery('#delete').hide();
			}
		}
		
		function addArea() { // 添加区划
			if(jQuery.trim(jQuery('#areaCode').val()) == '') {
				Dialog.alertFocus("区划代码不能为空！","areaCode")
				return false;
			}
			if(jQuery.trim(jQuery('#areaName').val()) == '') {
				Dialog.alertFocus("区划名称不能为空！","areaName")
				return false;
			}
			if(jQuery.trim(jQuery('#areaLevel').val()) == '0') {
				Dialog.alertFocus("请选择区划级别！","areaLevel")
				return false;
			}
		
			Dialog.confirm('是否确定新增该区划？',function(){ // 弹出提示框，确认是否进行此操作
				var sysArea = new SysArea();
				sysArea.topId=jQuery.trim(jQuery('#parenId').val());
			    sysArea.areaCode=jQuery.trim(jQuery('#areaCode').val());
			    sysArea.areaName=jQuery.trim(jQuery('#areaName').val());
			    sysArea.parentCode=jQuery.trim(jQuery('#parentCode').val());
			    sysArea.areaLevel=jQuery('#areaLevel').val();
			    sysArea.areaSort=jQuery.trim(jQuery('#areaSort').val());
			    var parentPath = jQuery.trim(jQuery('#parentPath').val());
			    sysArea.areaPath=parentPath;
			    
			    AreaManageDwr.updateOrSaveArea(sysArea, function(msg){
			    	if(msg != '0') { // 新增节点成功
			    		var newNode = [{id:msg,name:sysArea.areaName,pId:sysArea.topId,isParent:false}];
			    		newNode = zTree1.addNodes(zTree1.getSelectedNodes()[0],newNode);
			    		newNode = zTree1.getNodeByParam("id", msg, null);
			    		zTree1.selectNode(newNode);
			    		treeOnClick(event,'tree',newNode);
			    	}
			    	jQuery('#add').hide();
					jQuery('#next').show();
					jQuery('#modify').show();
					jQuery('#delete').show();
					this.top.ifm.frames["mainFrame"].name = "mainFrame";
			    });	
			});
		}
	</script>
</head>

<body>
	<div class="mainDiv">
		<dl class="mtab">
			<dt>
				<p class="position">
					当前位置：<a>系统管理</a><a>基础管理</a><a>区划管理</a>
				</p>
			</dt>
			<dd>
				<div>
					<div id="tree" class="ztree" style="width:30%; height: 450px; border :1px solid Silver;float:left;margin-right:3px; overflow: auto; "></div>
					<div style="float:left; width: 66%;">
						<input type="hidden" id="areaId"/>
						<input type="hidden" id="parenId"/>
						<input type="hidden" id="parentCode"/>
						<input type="hidden" id="parentLevel"/>
						<input type="hidden" id="parentPath"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
							<tr>
								<th colspan="4"><p class="title">区划信息</p></th>
							</tr>
							<tr>
								<td width="15%" class="txtR">区划代码：</td>
								<td width="35%" ><input id="areaCode" class="ipt" data-maxlength="20"/><font color="red">&nbsp;*</font></td>
						        <td width="15%" class="txtR">区划名称：</td>
						        <td width="35%" ><input id="areaName" class="ipt" data-maxlength="100"/><font color="red">&nbsp;*</font></td>		
						    </tr>
						    <tr>
								<td width="15%" class="txtR">区划级别：</td>
								<td width="35%">
									<select class="ipt" id="areaLevel" name="areaLevel">
										<option value="0" selected="selected">==请选择区划级别==</option>
										<s:iterator value="@util.BaseParameter@AREA_LEVEL">
											<option value="<s:property value='key'/>"><s:property value='value' /></option>
										</s:iterator>
									</select><font color="red">&nbsp;*</font>
								</td>
								<td width="15%" class="txtR">序号：</td>
								<td width="35%"><input id="areaSort" class="ipt" data-maxlength="10"/></td>
						    </tr>
						</table>
						<div class="btnbarBig">
							<a id="add" href="javascript:void(0)" onclick='addArea();' class="btnG">添加</a>&nbsp;
							<a id="next" href="javascript:void(0)" onclick='addNextArea()' class="btnSix">添加下级区划</a>&nbsp;
							<a id="modify" href="javascript:void(0)" onclick='updateArea();' class="btnG">修改</a>&nbsp;
							<a id="delete" href="javascript:void(0)" onclick='delArea();' class="btnR">删除</a>
						</div>
					</div>
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>
