<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	  <base href="<%=basePath%>"/>
	  <title>华房网后台管理系统</title>
	  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<%@ include file="include/heard.inc" %>
	<style>
		html,body{ margin:0px; height:100%; border: 0;padding: 0;margin: 0;width: 100%;overflow: hidden;}
		.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(js/zDialog/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
		.buttonStyle:hover{background:url(js/zDialog/images/buticon.gif) no-repeat left -23px; cursor: pointer;}
	</style>
	<script>
		function goOut() {
			Dialog.confirm('是否确定退出？',function(){
				doQuery('退出登录中');
				setTimeout(redirect,500);//解决遮罩确实问题 by zhujj 20151215
			});
		}
		function redirect(){
			location.href='loginOut.action?ret=sys&random=' + Math.random();
		}
		function showUserInfo(){
			Dialog.open({URL:"editMyUserInfo.action",Top:'30%',Height:320});
		}
	</script>
	</head>
	<body>
		<iframe name="ifm" src="indexFrame.action" width="100%" height="100%" frameborder="0" scrolling="no" style="width: 100%;height: 100%;margin: 0;padding: 0;"></iframe>
	</body>
</html>