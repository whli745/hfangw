<%@ page contentType="text/html;charset=GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../../include/heard.inc"%>
<link rel="stylesheet" href="./js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="./js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<%@ include file="../../include/xyTree.inc"%>
<script language="javascript" src="./js/xyTree/js/myDialog.js"></script>
<SCRIPT type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "treeTest.action",
			autoParam : [ "id=parentId" ],
			dataFilter : filter
		},
		callback : {
			onClick : treeClick
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
		alert(treeNode.id + ":" + treeNode.name);
		document.getElementById('mouca').onclick();
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);

		var btnIds = [ 'mouca', 'mouca1' ]; //按钮名称
		var tanchuDivs = [ 'tree-dlg', 'tree-dlg1' ]; //展示div名称
		for ( var i = 0; i < btnIds.length; i++) {
			var td = new TreeDialog(btnIds[i], tanchuDivs[i]);
			td.init();
		}
	});
</SCRIPT>
</head>

<body>

	<!-- 例子代码开始 -->
	<input type="button" id="mouca" value="测试树1" />
	<!-- 这里的div不可少 对话框的创建以此为据 -->
	<div id="tree-dlg"
		style="visibility: hidden; position: absolute; top: 0px;">
		<!-- 弹出树的标题 -->
		<div class="ydlg-hd">选择框</div>
		<!-- 加载树的内容 -->
		<div class="ydlg-bd" id="myTree">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree" style="width: 100%; height: 300px;"></ul>
				</div>
			</div>
		</div>
	</div>

	<br />
	<br />
	<br />
	<br />
	<br />
	<input type="button" id="mouca1" value="测试树2" />
	<!-- 这里的div不可少 对话框的创建以此为据 -->
	<div id="tree-dlg1"
		style="visibility: hidden; position: absolute; top: 0px;">
		<!-- 弹出树的标题 -->
		<div class="ydlg-hd">选择框</div>
		<!-- 加载树的内容 -->
		<div class="ydlg-bd" id="myTree1" style="overflow: auto;"></div>
	</div>
	<!-- 例子代码结束  -->
</body>
</html>
