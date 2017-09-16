
function btnRole(type, btnModuleRoles, paramUrl) {
	var btnModule = ""; // 拼接的页面请求按钮
	var classA = ""; // 按钮引用的样式
	var classImg = ""; // 按钮引用的图片
	if (type == "2") { // 普通请求
		classA = "class=\"btnG\"";
	}
	if (type == "3") { // 表头请求
		classImg = "<img src=\"images/ico_btn_plus.gif\" />";
	}
	for (var i = 0; i < btnModuleRoles.length; i++) {
		if (type == btnModuleRoles[i].controlType) {
			moduleName = btnModuleRoles[i].moduleName;//菜单名称
			moduleUrl = btnModuleRoles[i].moduleUrl;//菜单地址
			moduleControl = btnModuleRoles[i].moduleControl;//控件Name
			controlType = btnModuleRoles[i].controlType;//控件类型（1、列表链接；2、普通按钮;3、表头按钮）
			isValidate = btnModuleRoles[i].isValidate;//是否需要验证（0 否 1 是）
			validateContent = btnModuleRoles[i].validateContent;//验证提示内容
			isJsRequest = btnModuleRoles[i].isJsRequest;//是否为JavaScript请求（0 否 1 是）
			if (moduleName.length == 4) {
				classA = "class=\"btnFour\"";
			}
			if (moduleName.length == 5) {
				classA = "class=\"btnFive\"";
			}
			if (moduleName.length == 6) {
				classA = "class=\"btnSix\"";
			}
			if (isJsRequest == "1") { // 为JavaScript请求
				btnModule += " <a href=\"javascript:void(0)\" onclick=\"" + moduleUrl + "\" " + classA + " >" + classImg + moduleName + "</a> |";
			} else { // 普通请求
				if (null == paramUrl) {
					paramUrl = "";
				}
				if (isValidate == "0") { // 不需要弹出框验证的请求
					btnModule += " <a href='" + moduleUrl + paramUrl + "' " + classA + ">" + classImg + moduleName + "</a> |";
				} else { // 需要弹出框验证的请求
					btnModule += " <a href=\"javascript:void(0)\" onclick='Dialog.confirm(\"" + validateContent + "\",function(){window.location.href=\"" + moduleUrl + paramUrl + "\"},\"\",\"\",\"\",\"2\")'>" + classImg + moduleName + "</a> |";
				}
			}
		}
	}
	if (btnModule.length - 1 == btnModule.lastIndexOf("|")) {
		btnModule = btnModule.substring(0, btnModule.lastIndexOf("|"));
	}
	document.write(btnModule);
}

