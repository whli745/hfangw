<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	<title>华房网后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<%@ include file="include/heard.inc" %>
	<link href="css/submenu.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		jQuery(document).ready(function(){
			var mmm;
			mmm=jQuery(".container").children(".acc_trigger").length;
			var H;
			H=jQuery(window).height()-27*mmm-35;
			jQuery(".block").css({height: H+"px"});
			jQuery(".acc_container:not('.acc_container:first')").hide();
			jQuery(".acc_trigger:first").addClass('active');
		    jQuery('.acc_trigger').click(function(){
			if( jQuery(this).next().is(':hidden') ) {
		            jQuery('.acc_trigger').removeClass('active').next().slideUp();
		            jQuery(this).toggleClass('active').next().slideDown();
					//alert("acc_trigger数量："+jQuery(".container").children(".acc_trigger").length);
					H=jQuery(window).height()-27*mmm-35;
					//alert("屏的值:"+H);
					jQuery(".block").css({height: H+"px"});			
			}else {
		            jQuery(this).toggleClass('active');
		            jQuery(this).next().slideUp();
	        	}
				return false;
		    });
			jQuery('.acc_container .block li a,.noSubmenu').click(function(){
				var stxt=jQuery(this).text();
				var jnav=jQuery(parent.topFrame.document.getElementById("smenu"));
				jnav.text("> " + stxt);
			});
			var wh=jQuery(window).height();
		
		});
		
		jQuery(function(){
			jQuery('.container div a').click(function(){
				var iframe = parent.document.getElementById('mainFrame');
				if(iframe && iframe.contentWindow.doQuery)
					iframe.contentWindow.doQuery('数据加载中');
			});
			
		});
	</script>
	</head>
	<body class="leftBg">
		<div class="topTitle">
			<div id="zfcg">
			<s:if test="loginMember.auditStatus.equals(@util.BaseParameter@AUDIT_NOT_SUBMIT)">
				<s:if test="loginMember.identitys.equals(@util.BaseParameter@AN_IDENTITY_5)">
					辅助评标
				</s:if>
				<s:else>
					政府采购
				</s:else>
			</s:if>
			<s:else><s:property value="sysModule.moduleName"/></s:else>
			</div>
		</div>
		<s:if test="loginMember.auditStatus.equals(@util.BaseParameter@AUDIT_NOT_SUBMIT)"><%-- 是否新注册的会员 --%>
			<s:if test="loginMember.identitys.equals(@util.BaseParameter@AN_IDENTITY_5)"><%-- 专家 --%>
				<div class="container">
					<h2 class="acc_trigger">
						<a href="javascript:void(0)"><span>个人秘书</span></a>
					</h2>
					<div class="acc_container">
					<ul class="block" style="overflow:auto;">
					<li>
						<div style="overflow: hidden; text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;">
							<img src="images/menu_icon2.gif" /> 
							<a href='getPersonInfoSysExpert.action' title="个人信息" target="mainFrame">个人信息</a>
						</div>
					</li>
					</ul>
					</div>
				</div>
			</s:if>
			<s:else><%-- 其他类型会员 --%>
				<div class="container">
					<h2 class="acc_trigger">
						<a href="javascript:void(0)"><span>企业管理</span></a>
					</h2>
					<div class="acc_container">
					<ul class="block" style="overflow:auto;">
					<li>
						<div style="overflow: hidden; text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;">
							<img src="images/menu_icon2.gif" /> 
							<s:if test="loginMember.identitys.equals(@util.BaseParameter@AN_IDENTITY_2)">
								<a href='getSysTenderDeptTempDetail.action' title="招标会员企业信息" target="mainFrame">招标会员信息</a>
							</s:if>
							<s:elseif test="loginMember.identitys.equals(@util.BaseParameter@AN_IDENTITY_3)" >
								<a href='getSysMemberProxyBymId.action' title="代理机构企业信息" target="mainFrame">代理机构企业信息</a>
							</s:elseif>
							<s:elseif test="loginMember.identitys.equals(@util.BaseParameter@AN_IDENTITY_4)" >
								<a href='getQYGLSysSuppInfo.action' title="供应商企业信息" target="mainFrame">供应商企业信息</a>
							</s:elseif>
						</div>
					</li>
					</ul>
					</div>
				</div>
			</s:else>
		</s:if>
		<s:else>
			<div class="container">
				<s:iterator var="ml" value="modules" >
					<s:iterator value="ml" status="idx">
						<s:if test='#idx.index==0'>
							<h2 class="acc_trigger">
								<a href="javascript:void(0)"><span><s:property value="moduleName"/></span></a>
							</h2>
							<div class="acc_container">
							<ul class="block" style="overflow:auto;">
						</s:if>
						<s:else>
							<li>
								<div style="overflow: hidden; text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;">
									<img src="images/menu_icon2.gif" /> 
									<a href='<s:if test="moduleUrl.split(\"=\").length>1"><s:property value="moduleUrl"/>&pointModuleId=<s:property value="moduleId"/></s:if><s:else><s:property value="moduleUrl"/>?pointModuleId=<s:property value="moduleId"/></s:else>' title="<s:property value='moduleName' />" target="mainFrame"><s:property value="moduleName" /></a>
								</div>
							</li>
						</s:else>
					</s:iterator>
					</ul>
					</div>
				</s:iterator>
			</div>
		</s:else>
	</body>
</html>
