function findObj(theObj, theDoc) {
	var p, i, foundObj;
	if (!theDoc)
		theDoc = document;
	if ((p = theObj.indexOf("?")) > 0 && parent.frames.length) {
		theDoc = parent.frames[theObj.substring(p + 1)].document;
		theObj = theObj.substring(0, p);
	}
	if (!(foundObj = theDoc[theObj]) && theDoc.all)
		foundObj = theDoc.all[theObj];
	for (i = 0; !foundObj && i < theDoc.forms.length; i++)
		foundObj = theDoc.forms[i][theObj];
	for (i = 0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
		foundObj = findObj(theObj, theDoc.layers[i].document);
	if (!foundObj && document.getElementById)
		foundObj = document.getElementById(theObj);
	return foundObj;
}

// 添加一个列
count = 1;
function AddNewColumn() {
	var txtTDLastIndex = findObj("txtTDLastIndex", document);
	var columnID = parseInt(txtTDLastIndex.value);

	var tab = document.getElementById("tab");
	var rowLength = tab.rows.length;
	var columnLength = tab.rows[1].cells.length;

	for ( var i = 0; i < rowLength; i++) {
		if (i == 0) {
			var oTd = tab.rows[0].insertCell(columnLength);
			oTd.innerHTML = "<div align='center' style='width:40px'><a href='javascript:' onclick=\"DeleteSignColumn("
					+ (++columnID) + ")\">删除</a></div>";
		} else if (i == 1) {// 第一列:序号
			var oTd = tab.rows[1].insertCell(columnLength);
			oTd.innerHTML = "<div style='background: #D3E6FE;width=100%;'>"
					+ (++count) + "</div>";
		} else if (i > 1) {
			var oTd = tab.rows[i].insertCell(columnLength);
			oTd.id = "column" + columnID;
			oTd.innerHTML = "<textarea id=''  rows='4' style='width:150; height:40px;'></textarea>";
		}
	}

}


// 删除指定列
function DeleteSignColumn(columnId) {
	var tab = document.getElementById("tab");
	var columnLength = tab.rows[1].cells.length;

	// 删除指定单元格
	for ( var i = 0; i < tab.rows.length; i++) {
		tab.rows[i].deleteCell(columnId);
	}

	// 重新排列序号，如果没有序号，这一步省略
	var column = columnLength - 4;

	for ( var j = 1; j < column; j++) {
		tab.rows[1].cells[j + 3].innerHTML = "<div style='background: #D3E6FE;width=100%;'>"
				+ j + "</div>";
	}

	--count;
}

// 清空列表
function ClearAllSign() {
	// if (confirm('确定要清空所有吗？')) {
	index = 0;
	var tab = findObj("tab", document);
	var rowscount = tab.rows.length;

	// 循环删除行,从最后一行往前删除
	for (i = rowscount - 1; i > 1; i--) {
		tab.deleteRow(i);
	}

	// 重置最后行号为1
	var txtTRLastIndex = findObj("txtTRLastIndex", document);
	txtTRLastIndex.value = "1";

	// 预添加一行
	AddNewRow();
	// }
}
