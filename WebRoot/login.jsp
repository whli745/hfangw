<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>华房网后台管理系统</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />  
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<noscript>
	<meta http-equiv="refresh" content="0;url=openJs.htm"/>
</noscript> 
<style type="text/css">
	*{font-size:9pt;border:0;margin:0;padding:0;}
	body{font-family:'微软雅黑'; margin:0 auto;min-width:980px;}
	ul{display:block;margin:0;padding:0;list-style:none;}
	li{display:block;margin:0;padding:0;list-style: none;}
	img{border:0;}
	dl,dt,dd,span{margin:0;padding:0;display:block;}
	a,a:focus{text-decoration:none;color:#000;outline:none;blr:expression(this.onFocus=this.blur());}
	a:hover{color:#00a4ac;text-decoration:none;}
	table{border-collapse:collapse;border-spacing: 0;}
	cite{font-style:normal;}
	h2{font-weight:normal;}
	
	/*cloud*/
	
	#mainBody {width:100%;height:100%;position:absolute;z-index:-1;}
	.cloud {position:absolute;top:0px;left:0px;width:100%;height:100%;background:url(./images/login/images/cloud.png) no-repeat;z-index:1;opacity:0.5;}
	#cloud2 {z-index:2;}
	
	/*login*/
	.logintop{height:47px; position:absolute; top:0; background:url(./images/login/images/loginbg1.png) repeat-x;z-index:100; width:100%;}
	.logintop span{color:#fff; line-height:47px; background:url(./images/login/images/loginsj.png) no-repeat 21px 18px; text-indent:44px; color:#afc5d2; float:left;}
	.logintop ul{float:right; padding-right:30px;}
	.logintop ul li{float:left; margin-left:20px; line-height:47px;}
	.logintop ul li a{color:#afc5d2;}
	.logintop ul li a:hover{color:#fff;}
	.loginbody{background:url(./images/login/images/loginbg3.png) no-repeat center center; width:100%; height:585px; overflow:hidden; position:absolute; top:47px;}
	.systemlogo{background: url(./images/login/images/loginname.png); width:660px; height:51px; margin:0 auto; margin-top:70px;}
	.loginbox{width:692px; height:336px; background:url(./images/login/images/logininfo.png) no-repeat; margin:20px auto 0; position:relative;}
	.loginbox ul{margin-top:70px; margin-left:285px;}
	.loginbox ul li{margin-bottom:15px;}
	.loginbox ul li label{color:#687f92; padding-left:25px;}
	.loginbox ul li label a{color:#687f92;}
	.loginbox ul li label a:hover{color:#3d96c9;}
	.loginbox ul li label input{margin-right:5px;}
	.loginuser{width:299px; height:48px; background:url(./images/login/images/loginuser.png) no-repeat; border:none; line-height:48px; padding-left:44px; font-size:14px; font-weight:bold;}
	.loginpwd{width:299px; height:48px; background:url(./images/login/images/loginpassword.png) no-repeat; border:none;line-height:48px; padding-left:44px; font-size:14px; color:#90a2bc;}
	.verification{width:299px; height:48px;background:url(./images/login/images/verification.png) no-repeat; border:none;line-height:48px; padding-left:44px; font-size:14px; color:#90a2bc;}
	.verification_img{position:absolute; left:485px; top:206px; cursor: pointer;}
	.loginbtn{width:111px;height:35px; background:url(./images/login/images/buttonbg.png) repeat-x; font-size:14px; font-weight:bold; color:#fff;cursor:pointer; line-height:35px;}
	.loginbm{height:50px; line-height:50px; text-align:center; background:url(./images/login/images/loginbg2.png) repeat-x;position:absolute; bottom:0; width:100%; color:#0b3a58;}
	.loginbm a{font-weight:bold;color:#0b3a58;}
	.loginbm a:hover{color:#fff;}
	
	.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(js/zDialog/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
	.buttonStyle:hover{background:url(js/zDialog/images/buticon.gif) no-repeat left -23px; cursor: pointer;}
</style>
<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="./js/layer/layer.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script>
	jQuery(function(){
		jQuery('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		jQuery(window).resize(function(){
			jQuery('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })
	});
	if (window != top) 
		top.location.href = location.href; 

	function addCookie(objName,objValue,objHours){//添加cookie
		var str = objName + "=" + escape(objValue);
		if(objHours > 0){//为0时不设定过期时间，浏览器关闭时cookie自动消失
		var date = new Date();
		var ms = objHours*3600*1000;
		date.setTime(date.getTime() + ms);
		str += "; expires=" + date.toGMTString();
		}
		document.cookie = str;
	}
	function login() {
		var userCode = jQuery.trim(document.getElementById("userCode").value);
		var password = jQuery.trim(document.getElementById("password").value);
		var yzm = jQuery.trim(document.getElementById("yzm").value);
		if(userCode == "") {
			common.alertFocus('请输入登录名!',"userCode");
			return false;
		}
		if(password == "") {
			common.alertFocus('请输入密码!','password');
			return false;
		}
		if(yzm == "") {
			common.alertFocus('请输入验证码!','yzm');
			return false;
		}
		jQuery.ajax({
			type: "POST",
			url: "doLogin.action",
			data: 'userCode='+userCode+'&password='+password + '&yzm=' + yzm,
			success: function(msg){
				if(msg!=''){
					if(msg == '2') {
						common.alertFocus('登录名不存在！',"userCode");
					} else if(msg == '1') {
						common.alertFocus('验证码不正确！',"userCode");
					}  else if(msg == '3') {
						common.alertFocus('用户被禁用！',"userCode");
					} else if(msg == '4') {
						common.alertFocus('密码不正确！','password');
					} else if(msg=='5'){
						common.alertFocus('该用户已被删除！',"userCode");
					} else if(msg=='register'){
						location.href = "login.action";
					} else {
						location.href = "doLoginSuccess.action";
					}
				}
			}
		});
	}
	function refresh(obj) {        
		obj.src = "imageServlet?r="+Math.random();    
	}
	//回车键 监听
	jQuery(function(){
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	jQuery('#loginBtn').click();
		    	return false;
		     }
		}
		jQuery('#userCode').val(getCookie('username'));
	});   
	function getCookie(objName){//获取指定名称的cookie的值
		var arrStr = document.cookie.split("; ");
		for(var i = 0;i < arrStr.length;i ++){
			var temp = arrStr[i].split("=");
			if(temp[0] == objName) return unescape(temp[1]);
		} 
		return null;
	}
	
	$(document).ready(function() {
		changeCode();
		$("#checkImg").bind("click", changeCode);
	});
	function changeCode() {
		$("#checkImg").attr("src", "imageServlet?r=" + Math.random());
	}
	
	jQuery(function(){
		var browser=navigator.appName;
		var b_version=navigator.appVersion;
		var version=b_version.split(";"); 
		var trim_Version=version[1].replace(/[ ]/g,""); 
		if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0") 
		{ 
			Dialog.alert('您使用的IE版本过低，请先进行升级！'); 
		}
	})
</script> 
</head>
<body style="background-color:#007ACC; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
	<div class="logintop">    
		<span>欢迎访问华房网后台管理系统</span>
		<ul>
			<li><a href="<%=basePath %>">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<span class="systemlogo"></span>   
		<div class="loginbox">
			<ul>
				<li><input id="userCode" name="username" type="text" class="loginuser" maxlength="20" /></li>
				<li><input id="password" name="password" type="password" autocomplete="off" class="loginpwd" maxlength="32" /></li>
	            <li><input id="yzm" name="yzm" type="text" class="verification" value="" maxlength="6" onclick="JavaScript:this.value=''" /><label class="verification_img"><a><img id="checkImg" title="点击更换" width="90" height="28" src="" />	</a></label></li>	
				<li><input id="loginBtn" type="button" class="loginbtn" value="登录" onclick="login()" /><%-- <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label> --%></li>
			</ul>
		</div>
	</div>
	<div class="loginbm">合肥华房网络科技有限公司版权所有</div>  
</body>
</html>