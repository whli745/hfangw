function getTreeNodeById(content, pid) {
	var arr = content.tree.treeArray;
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].code) {
			if (arr[i].code == pid) {
				return arr[i];
			}
		}
	}
	return null;
}
function createTreeNode(jsonTreeString, Content) {
	for(var i = 0; i < jsonTreeString.length; i++) {
		node1 = new xyTree.NodeNormal(jsonTreeString[i].name);
		node1.parentID = jsonTreeString[i].pId;
		node1.code = jsonTreeString[i].id;
		node1.name = unescape(jsonTreeString[i].name);

		if(node1.parentID == '') {
			Content.add(node1); 
		} else {
			nodeTmp = getTreeNodeById(Content, node1.parentID);
			if (nodeTmp)
				nodeTmp.add(node1);
		}
	}
}
function TreeDialog(btnId, tanchuDiv) {
	var dialog;
	var treeDialog = new Object;
	treeDialog.btnId = btnId;
	treeDialog.tanchuDiv = tanchuDiv;
	treeDialog.init = function() {
		var tmpBtn = document.getElementById(treeDialog.btnId);
		var tmpTanchuDiv = document.getElementById(treeDialog.tanchuDiv);
		tmpBtn.onclick = function() {
			if (!dialog) {
				var tx, ty;
				ty = tmpBtn.clientTop + tmpBtn.scrollTop + tmpBtn.offsetTop;
				tx = tmpBtn.clientLeft + tmpBtn.scrollLeft + tmpBtn.offsetLeft
						+ tmpBtn.offsetWidth;
				dialog = new YAHOO.ext.BasicDialog(tmpTanchuDiv.id, {
					x : tx,
					y : ty,
					autoTabs : true,
					width : 250,
					height : 350,
					shadow : true,
					minWidth : 100,
					minHeight : 250,
					proxyDrag : true
				});
				dialog.addKeyListener(27, dialog.hide, dialog);
			}
			if (!dialog.isVisible()) {
				dialog.show();
			} else {
				dialog.hide();
			}
		}
	}
	return treeDialog;
}