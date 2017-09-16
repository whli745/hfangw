<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="../../../include/heard.inc"%>
<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
<script language="javascript">
	function checkform() {
		var areaCode = document.getElementById("areaCode");
		if (jQuery.trim(areaCode.value).length == 0) {
			Dialog.alertFocus("区划代码不能为空！", 'areaCode');
			return false;
		}
		var areaName = document.getElementById("areaName");
		if (jQuery.trim(areaName.value).length == 0) {
			Dialog.alertFocus("区划名称不能为空！", 'areaName');
			return false;
		}
		Dialog.confirm('是否确定修改？',function(){
			document.form1.submit();
		});
	}
</script>
</head>
<body id="mouseRight">
	<div class="mainDiv">
		<dl class="mtab">
			<dt>
				<p class="position">
					当前位置：<a>系统管理</a><a>基础管理</a><a>顶级区划</a>
				</p>
			</dt>
			<dd>
			 <form id="form1" name="form1" method="post" action="editAreaTop.action">
				<input type="hidden" name="sysArea.areaId"   value="<s:property value='sysAreaTop.areaId'/>" /> 
				<input type="hidden" name="sysArea.topId"    value="<s:property value='sysAreaTop.topId'/>" />
				<input type="hidden" name="sysArea.areaLevel"  value="<s:property value='sysAreaTop.areaLevel'/>" /> 
				<input type="hidden" name="sysArea.parentCode" value="<s:property value='sysAreaTop.parentCode'/>" /> 
				<input type="hidden" name="sysArea.DelFlag"    value="<s:property value='sysAreaTop.DelFlag'/>" /> 
				<input type="hidden" name="sysArea.areaSort"    value="<s:property value='sysAreaTop.areaSort'/>" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
					<tr>
						<th colspan=4><p class="title">修改区划</p></th>
					</tr>
					<tr>
						<td width="15%" class="txtR">区划代码：</td>
						<td width="35%">
							<input class="ipt"  id="areaCode" name="sysArea.areaCode" value='<s:property value="sysAreaTop.areaCode" />' />
						</td>
						<td width="15%" class="txtR">区划名称：</td>
						<td width="35%">
							<input class="ipt" id="areaName" name="sysArea.areaName" value='<s:property value="sysAreaTop.areaName" />' />
						</td>
					</tr>
				</table>
				<div class="btnbarBig">
					<script type="text/javascript">btnRole('2',<s:property value="#request.btnModuleRole" escape="false"/>)</script>
				</div>
			</form>
		</dd>
		</dl>
	</div>
</body>
</html>
