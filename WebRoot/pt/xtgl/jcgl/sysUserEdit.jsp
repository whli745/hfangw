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
	<base href="<%=basePath%>"/>
	<%@ include file="../../../include/heard.inc"%>
	<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>
	<script type='text/javascript'>
		function subEdit(){
			var name=document.getElementById("userName");
			if(jQuery.trim(jQuery("#userName").val())==""){
				Dialog.alertFocus("用户姓名不能为空！", 'userName');
				return false;
			}
			var pwd=document.getElementById("pwd");
			if(pwd.value.length<=0){
				Dialog.alertFocus("密码不能为空！", 'pwd');
				return false;
			}
			var userPost=document.getElementById("userPost");
			if(userPost.value==0){
				Dialog.alertFocus("请选择职务不能为空！", 'userPost');
				return false;
			}
			if(jQuery.trim(jQuery("#userPhone").val())==""){
				Dialog.alertFocus("手机号码不能为空！", 'userPhone');
				return false;
			}
			Dialog.confirm('是否确定修改？',function(){
				var user = new SysMyUser();
				user.userId="<s:property value='sysUser.userId'/>";
				user.userName=jQuery.trim(jQuery("#userName").val());
				user.userPassword=jQuery.trim(jQuery("#pwd").val());
				user.userSex=jQuery.trim(jQuery("#userSex").val());
				user.userBirthday=jQuery.trim(jQuery("#userBirthday").val());
				user.userPost=jQuery.trim(jQuery("#userPost").val());
				user.userPhone=jQuery.trim(jQuery("#userPhone").val());
				user.userTel=jQuery.trim(jQuery("#userTel").val());
				user.userEmail=jQuery.trim(jQuery("#userEmail").val());
				
				PtSynthetizeAppDwr.saveMyUserInfo(user, function(msg){
					if('1'==msg) {
						Dialog.alert("修改成功！");
					} else {
						Dialog.alert("修改失败！");
					}
				});
			});
		}
	</script>
</head>
<body style="min-height: 300px;">
	<div class="mainDiv">
		<dl class="mtab">
			<dd>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
					<tr>
						<th colspan="4"><p class="title">个人信息</p></th>
					</tr>
					<tr>
						<td width="15%" class="txtR">登录账号：</td>
						<td width="35%"><s:property value='sysUser.userCode'/></td>
						<td width="15%" class="txtR">用户名称：</td>
						<td width="35%"><input name="sysUser.userName" id="userName" class="ipt" value="<s:property value='sysUser.userName'/>" data-maxlength="100"/><font color="red">&nbsp;*</font></td>
					</tr>
					<tr>
						<td class="txtR">密码：</td>
						<td><input name="sysUser.userPassword" id="pwd" type="password" value="<s:property value='sysUser.userPassword'/>" class="ipt" data-maxlength="50"/><font color="red">&nbsp;*</font></td>
						<td class="txtR">用户状态：</td>
						<td>
							<s:iterator value="@util.BaseParameter@STATUS">
								<s:if test="key.equals(sysUser.userStatus)"><s:property value='value'/></s:if>
							</s:iterator>
						</td>
					</tr>
					<tr>
						<td class="txtR">性别：</td>
						<td>
							<s:iterator value="@util.BaseParameter@PERSON_SEX">
								<label><input type="radio" name="sysUser.userSex" id="userSex" value="<s:property value='key'/>"
								<s:if test="sysUser == null && key.equals(@util.BaseParameter@MALE)">checked</s:if>
								<s:if test="key.equals(sysUser.userSex)">checked</s:if> /><s:property value='value'/></label>
							</s:iterator>
						</td>
						<td class="txtR">出生年月：</td>
						<td><input name="sysUser.userBirthday" id="userBirthday" value='<s:date name="sysUser.userBirthday" format="yyyy-MM-dd"/>' class="ipt Wdate" onfocus="WdatePicker()"  readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="txtR">职务：</td>
						<td>
							<select name="sysUser.userPost" id="userPost" class="ipt_s">
								<option value="0">==请选择职务==</option>
								<s:iterator value="sysDictList">
									<option value="<s:property value='dictId'/>" <s:if test="sysUser.userPost == dictId">selected</s:if> ><s:property value='dictName'/></option>
								</s:iterator>
							</select>
							<font color="red">&nbsp;*</font>
						</td>
						<td class="txtR">手机：</td>
						<td><input name="sysUser.userPhone" id="userPhone" value="<s:property value='sysUser.userPhone'/>" class="ipt" data-maxlength="200"/><font color="red">&nbsp;*</font></td>
					</tr>
					<tr>
						<td class="txtR">电话：</td>
						<td><input name="sysUser.userTel" id="userTel" value="<s:property value='sysUser.userTel'/>" class="ipt" data-maxlength="20"/></td>
						<td class="txtR">邮箱：</td>
						<td><input name="sysUser.userEmail" id="userEmail" value="<s:property value='sysUser.userEmail'/>" class="ipt" data-maxlength="20"/></td>
					</tr>
					<tr>
						<td class="txtR">业务类型：</td>
						<td><s:property value="sysUser.sysArea.areaName"/></td>
						<td class="txtR">排序号：</td>
						<td><s:property value='sysUser.userSort'/></td>
					</tr>
					<tr>
						<td class="txtR">所属区划：</td>
						<td><s:property value="sysUser.sysDict.dictName"/></td>
						<td class="txtR">所属机构：</td>
						<td><s:property value="sysUser.sysOrgan.organName"/></td>
					</tr>
					<tr>
						<td class="txtR" >所属组：</td>
						<td colspan="3" id="roleTD">
							<s:iterator value="userRoleList">
								<s:property value='sysRole.roleName'/>
							</s:iterator>
						</td>
					</tr>
				</table>
				<div class="btnbarBig">
					<a href="javascript:void(0)" class="btnO" onclick="subEdit();">修改</a>
					<a href="javascript:void(0)" class="btnO" onclick="Dialog.close();">关闭</a>
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>