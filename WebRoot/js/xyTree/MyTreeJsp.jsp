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

		var btnIds = [ 'mouca', 'mouca1' ]; //��ť����
		var tanchuDivs = [ 'tree-dlg', 'tree-dlg1' ]; //չʾdiv����
		for ( var i = 0; i < btnIds.length; i++) {
			var td = new TreeDialog(btnIds[i], tanchuDivs[i]);
			td.init();
		}
	});
</SCRIPT>
</head>

<body>

	<!-- ���Ӵ��뿪ʼ -->
	<input type="button" id="mouca" value="������1" />
	<!-- �����div������ �Ի���Ĵ����Դ�Ϊ�� -->
	<div id="tree-dlg"
		style="visibility: hidden; position: absolute; top: 0px;">
		<!-- �������ı��� -->
		<div class="ydlg-hd">ѡ���</div>
		<!-- ������������ -->
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
	<input type="button" id="mouca1" value="������2" />
	<!-- �����div������ �Ի���Ĵ����Դ�Ϊ�� -->
	<div id="tree-dlg1"
		style="visibility: hidden; position: absolute; top: 0px;">
		<!-- �������ı��� -->
		<div class="ydlg-hd">ѡ���</div>
		<!-- ������������ -->
		<div class="ydlg-bd" id="myTree1" style="overflow: auto;"></div>
	</div>
	<!-- ���Ӵ������  -->
</body>
</html>
