<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<%@ include file="../../../include/heard.inc"%>
<script type="text/javascript">
function check(){
	if(jQuery.trim(jQuery("#columnName").val())==""){
		Dialog.alertFocus("栏目名称不能为空！", 'columnName');
		return false;
	}
	if(jQuery.trim(jQuery("#sort").val())=="" || isNaN(jQuery.trim(jQuery("#sort").val()))){
		Dialog.alertFocus("排序不能为空且必须是数字！", 'sort');
		return false;
	}
	common.confirm('是否确定此操作？',function(){
		document.myForm.submit();
	});
}
</script>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
    	<dt>
        	<p class="notice"><s:if test="infoTcategory==null">添加</s:if><s:else>修改</s:else>栏目</p>
        </dt>
		<dd>
			<form name="myForm" id="myForm" method="post" action="saveInfoTcategory.action">
				<input type='hidden' id="columnId" name="infoTcategory.columnId" value="<s:property value='infoTcategory.columnId'/>"/>
				<input type='hidden' id="parentId" name="infoTcategory.parentId" value="<s:property value='parentId'/>"/>
				<input type='hidden' id="rank" name="infoTcategory.rank" value="<s:property value='rank'/>"/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
					<tr>
						<th colspan="4"><p class="title" style="text-align: left;padding-left: 10px; font-weight: bold;">栏目信息</p></th>
					</tr>
					<tr>
						<td width="15%" class="txtR">栏目名称：</td>
						<td width="35%">
							<input name="infoTcategory.columnName" id="columnName" class="ipt" value="<s:property value='infoTcategory.columnName'/>" /><font color="red">&nbsp;*</font>
						</td>
						<td width="15%" class="txtR">排序：</td>
						<td width="35%">
							<input name="infoTcategory.sort" id="sort" class="ipt" value="<s:property value='infoTcategory.sort'/>" /><font color="red">&nbsp;*</font>
						</td>
					</tr>
					<tr>
						<td width="15%" class="txtR">栏目描述：</td>
						<td colspan="3" width="35%">
							<textarea rows="4" cols="126" id="columnDescribe" name="infoTcategory.columnDescribe"  class="input_area"><s:property value='infoTcategory.columnDescribe'/></textarea>
						</td>
					</tr>
				</table>
				<div class="btnbar">
					<s:if test="infoTcategory==null"><a href="javascript:void(0)" onclick="check();"><img src="./images/ico_btn_add.gif" />添加</a></s:if>
					<s:else><a href="javascript:void(0)" onclick="check();"><img src="images/ico_btn_modi.gif" />修改</a></s:else>
					<a href="javascript:void(0)" onclick="back()"><img src="images/ico_btn_back.gif" />返回</a>
        		</div>
			</form>
		</dd>
	</dl>
</div>
</body>
</html>
