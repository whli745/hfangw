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
		<script>
		function onReset(){
			if(confirm("您确定要重置数据信息吗！"))
			document.myForm.reset();
		}
		</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a href="sysUserInfoList.html">用户管理</a><a>修改用户</a>
					</p>
					<a class="back" onclick="history.go(-1)">返回</a>
				</dt>
				<dd>
					<form name="myForm" method="post" action="updateSysUser.action">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
							<tr>
								<th colspan="4"><p class="title">修改用户</p></th>
							</tr>
							<tr>
								<td width="15%" class="txtR">用户登录账号：</td>
								<td width="35%"><input name="userCode" class="ipt_s" size="40" value="<s:property value="sysUser.userCode"/>" /></td>
								<td width="15%" class="txtR">用户名称：</td>
								<td width="35%"><input name="userName" class="ipt_s" size="40" value="<s:property value="sysUser.userName"/>" /></td>
							</tr>
							<tr>
								<td class="txtR">性别：</td>
								<td>
									<input name="userSex" size="40" type="radio" <s:if test="sysUser.userSex.equals('男')">checked</s:if> value="男" />男
									<input name="userSex" size="40" type="radio" <s:if test="sysUser.userSex.equals('女')">checked</s:if> value="女" />女
								</td>
								<td class="txtR">出生年月：</td>
								<td>
									<input name="userBirthday" class="ipt_s Wdate" onFocus="WdatePicker()" readonly size="40" value="<s:property value="sysUser.userBirthday"/>" />
								</td>
							</tr>
							<tr>
								<td class="txtR">职务：</td>
								<td>
									<input name="userPost" class="ipt_s" size="40" value="<s:property value="sysUser.userPost"/>" />
								</td>
								<td class="txtR">手机：</td>
								<td>
									<input name="userPhone" class="ipt_s" size="40" value="<s:property value="sysUser.userPhone"/>" />
								</td>
							</tr>
							<tr>
								<td class="txtR">电话：</td>
								<td>
									<input name="userTel" class="ipt_s" size="40" value="<s:property value="sysUser.userTel"/>" />
								</td>
								<td class="txtR">邮箱：</td>
								<td>
									<input name="userEmail" class="ipt_s" size="40" value="<s:property value="sysUser.userEmail"/>" />
								</td>
							</tr>
							<tr>
								<td class="txtR">所属机构：</td>
								<td>
									<select class="xiala">
										<option value="0">--请选择所属机构--</option>
										<option value="1" selected="selected">发改委</option>
									</select>
								</td>
								<td class="txtR">序号：</td>
								<td>
									<input name="userSort" class="ipt_s" size="40" value="<s:property value="sysUser.userSort"/>" />
								</td>
							</tr>
						</table>
						<div class="btnbarBig">
							<a class="btnG" onclick="submit()">确定</a>
							<a class="btnO" onclick="history.back(-1)">返回</a>
						</div>
					</form>
				</dd>
			</dl>
		</div>  
	</body>
</html>
