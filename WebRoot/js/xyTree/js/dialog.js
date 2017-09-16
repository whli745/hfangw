/*
 * yui-ext
 * Copyright(c) 2006, Jack Slocum.
 */
// create the HelloWorld application (single instance)
var HelloWorld = function(butId, divId, wd, ht) {
	// everything in this space is private and only accessible in the HelloWorld
	// block
	var dialog;
	// define some private variables
	var moucaheBtn;
	var toggleTheme = function() {
		getEl(document.body, true).toggleClass('ytheme-gray');
	};

	// return a public interface
	return {
		init : function() {
			moucaheBtn = getEl(butId);
			if(!moucaheBtn)return;
			// attach to click event
			moucaheBtn.on('click', this.showDialog, this, true);

			// getEl('theme-btn').on('click', toggleTheme);
			// 加载对话框，消除页面左右滚动条
			this.showDialog();
		},

		showDialog : function() {
			if (!dialog) { // lazy initialize the dialog and only create it
							// once
				var tmpBtn = document.getElementById(butId);
				var tx, ty;
				ty = tmpBtn.clientTop + tmpBtn.scrollTop + tmpBtn.offsetTop;
				tx = tmpBtn.clientLeft + tmpBtn.scrollLeft + tmpBtn.offsetLeft
						+ tmpBtn.offsetWidth;
				var tmpBtn1 = tmpBtn;
				// 循环读取当前对象的父级对象，最终获取当前对象的绝对位置
				while (tmpBtn1 = tmpBtn1.offsetParent) {
					tx += tmpBtn1.offsetLeft;
					ty += tmpBtn1.offsetTop;
				}

				dialog = new YAHOO.ext.BasicDialog(divId, {
					modal : false,
					x : tx,
					y : ty,
					autoTabs : true,
					width : 200,
					height : 300,
					shadow : true,
					minWidth : 100,
					minHeight : 250,
					proxyDrag : true
				});
				dialog.addKeyListener(27, dialog.hide, dialog);
				// dialog.addButton('关闭', dialog.hide, dialog);
			}
			// getEl(document.body, true).toggleClass('');
			else if (!dialog.isVisible()) {
				dialog.show(moucaheBtn.dom);
			} else {
				dialog.hide();
			}
		}
	};
};

// uusing onDocumentReady instead of window.onload initializes the application
// when the DOM is ready, without waiting for images and other resources to load

