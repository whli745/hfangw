<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="../../../include/heard.inc"%>
<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery("#listTab1 tr:even").addClass("alt");
	jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
	initTableTitle('listTab1');
});

//全选
function checkAll(){
	var checkFlag = document.getElementById('checkAll').checked;
	var arrContentIds = document.getElementsByName('netUserIds');
	var len = arrContentIds.length;
	if(len <= 0) return;
	for(var i=0;i<len;i++) {
		arrContentIds[i].checked=checkFlag;
	}
}

//密码重置
function doResetPassword(){
	var index = 0;
	var id = "";
	for(var i = 0; i < jQuery("input[name='netUserIds']").length; i++){
		if( jQuery(jQuery("input[name='netUserIds']")[i]).is(':checked')){
			index ++;
			if( i == jQuery("input[name='netUserIds']").length - 1)
				id += jQuery(jQuery("input[name='netUserIds']")[i]).val();
			else
				id += jQuery(jQuery("input[name='netUserIds']")[i]).val() + ",";
		}
	}
	if( index == 0 ){
		Dialog.alert('您没有选中任何数据!');
	}else{
		Dialog.confirm('确定要重置密码么？',function(){
			jQuery.ajax({
        		url : "resetPassword.action",
        		dataType : "html",
        		data : {
        			ids : id
        		},
        		success : function(strValue){
        			if( strValue == "success" ){
        				Dialog.alert("密码重置成功");
        			}
        		}
        	});
		});
	}
}

//删除
function doDeleteUser(){
	var index = 0;
	var id = "";
	for(var i = 0; i < jQuery("input[name='netUserIds']").length; i++){
		if( jQuery(jQuery("input[name='netUserIds']")[i]).is(':checked')){
			index ++;
			if( i == jQuery("input[name='netUserIds']").length - 1)
				id += jQuery(jQuery("input[name='netUserIds']")[i]).val();
			else
				id += jQuery(jQuery("input[name='netUserIds']")[i]).val() + ",";
		}
	}
	if( index == 0 ){
		Dialog.alert('您没有选中任何数据!');
	}else{
		Dialog.confirm('确定要删除该用户吗？',function(){
			jQuery.ajax({
        		url : "deleteNetUser.action",
        		dataType : "html",
        		data : {
        			ids : id
        		},
        		success : function(strValue){
        			Dialog.alert("删除成功!");
        			location.href = "initNetUserManager.action";
        		}
        	});
		});
	}
}

//查看详细信息
function selectDetailInfo( id ){
	location.href = "selectDetailInfo.action?ids=" + id;
}

//用户证件审核
function certificationAudit(userId){
	location.href = "selectDetailInfoForCertification?ids=" + userId;
}
</script>
</head>
<body>
<div class="mainDiv">
	<form id='form1' name="form1" action="initNetUserManager.action" method="post">
	<div class="infoSearch">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTab">
			<tbody>
				<tr>
					<td width="15%" class="txtR">用户名 ：</td>
					<td width="35%">
						<input type="text" name="netUser.userName" value="<s:property value='netUser.userName'/>" class="ipt_s" />
					</td>
					<td width="15%" class="txtR">登录名 ：</td>
					<td width="35%">
						<input type="text" name="netUser.userLogin" value="<s:property value='netUser.userLogin'/>" class="ipt_s" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btnbar">
		    <a href="javascript:document.getElementById('form1').submit();"><img src="images/ico_btn_search.gif"/>搜索</a>
		    <a href="javascript:doResetPassword();"><img src="images/ico_btn_refresh.gif"/>密码重置</a>
		    <a href="javascript:doDeleteUser();"><img src="images/ico_btn_close.gif"/>删除</a> 
		</div>
	</div>
	</form>
	<dl class="mtab" style="width:100%">
        <dd id="t1">
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
				<tr>
					<th width="5%">序号</th>
					<th width="5%"><input type="checkbox" id="checkAll" onclick="checkAll();" /></th>
					<th width="10%">登录名</th>
					<th width="15%">用户名</th>
					<th width="10%">联系电话</th>
					<th width="15%">电子邮箱</th>
					<th width="12%">用户注册日期</th>
					<th width="15%">联系地址</th>
					<th>操作</th>
				</tr>
				<s:iterator value="netUserList" status="index">
					<tr>
						<td class='box'><s:property value="#index.count" /></td>
						<td><input type="checkbox" name="netUserIds" value='<s:property value="id" />'/></td>
						<td><s:property value="userLogin" /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="userTel" /></td>
						<td style="text-align: left;"><s:property value="userEmail" /></td>
						<td><s:date name="userCreatedate" format="yyyy-MM-dd HH:mm"/></td>
						<td><s:property value="userAddress" /></td>
						<td>
							<a href="javascript:selectDetailInfo('<s:property value="id" />');">查看</a>
							<s:iterator value="checkStauts" status="st">
								<s:if test="id.equals(userId) && \"yes\".equals(isHaveCheck)">
									| <a href="javascript:certificationAudit('<s:property value="id" />');">证件审核</a>
								</s:if>
							</s:iterator>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="9" class="datatitle" >
						<%@ include file="../../../include/pagination.inc"%>
					</td>
				</tr>
			</table>
		</dd>
	</dl>
</div>
</body>
</html>
