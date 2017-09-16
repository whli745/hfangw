<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<%@ include file="../../../include/heard.inc"%>
<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
<script type="text/javascript">
	function edit() {
		var oldPassword=jQuery('#oldPassword');
		var userPassword=jQuery('#userPassword');
		var confirmPassword=jQuery('#confirmPassword');
		jQuery.ajax({
			type: 'POST',
		    url: 'checSysUserPassword.action',
		    data:{
		    	userPassword:oldPassword.val()
		    }, 
		    dataType:'text',
		    success: function(msg){
		    	if(msg=='0'){
		    		Dialog.alertFocus('原密码输入错误，请重新输入！', 'oldPassword');
		    	}else{
		    		if(jQuery.trim(userPassword.val())==''){
		    			Dialog.alertFocus("新密码不能为空！", 'userPassword');
		    			return false;
		    		}
		    		if(jQuery.trim(confirmPassword.val())==''){
		    			Dialog.alertFocus("确认密码不能为空！", 'confirmPassword');
		    			return false;
		    		}
		    		if(jQuery.trim(userPassword.val())!=jQuery.trim(confirmPassword.val())){
		    			Dialog.alertFocus("两次输入密码不一致！", 'userPassword');
		    			return false;
		    		}
		    		Dialog.confirm('是否确定修改？',function(){
						jQuery("#updatePasswordForm").submit();
					});
		    	}
			}
		});
	}
</script>
</head>
<body id="mouseRight">
	<div class="mainDiv">
		<dl class="mtab">
			<dt>
				<p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a>修改密码</a></p>
			</dt>
			<dd>
				<form id="updatePasswordForm" name="updatePasswordForm" action="editSysUserPassword.action" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr><th colspan="2"><p class="title">修改密码</p></th></tr>	 
						<tr>
							<td width="15%" class="txtR">原密码：</td>
							<td width="35%" ><input type="password" class="ipt_s" id="oldPassword" name="oldPassword" /></td>
						</tr>
						<tr>
							<td width="15%" class="txtR">新密码：</td><td width="35%" ><input id="userPassword" type="password" class="ipt_s" name="userPassword"/></td>
						</tr>
						<tr>
							<td width="15%" class="txtR">新密码确认：</td><td width="35%"><input id="confirmPassword"  name="confirmPassword" type="password" class="ipt_s"/></td>
						</tr>
					</table>
				</form>
				<div class="btnbarBig">
					<script type="text/javascript">btnRole('2',<s:property value="#request.btnModuleRole" escape="false"/>)</script>
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>