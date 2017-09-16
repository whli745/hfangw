<%@ page contentType="text/html;charset=UTF-8"%>
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
	<%@ include file="./include/heard.inc"%>
	<s:if test="moduleList!=null&&moduleList.size()>0">
	<link href="./css/jCarouselLite.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="./js/jcarousellite.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function(){
			jQuery('#demo-03').jCarouselLite({
				btnPrev: '#prev-03',
				btnNext: '#next-03',
				circular: false,
				scroll: 5
			});	
		});
		jQuery(document).ready(function() {	
		  jQuery('#tabMenu > li').mouseover(function() {
		  	if(jQuery(this).css('font-weight')=='100'){
		    	jQuery(this).css('background-position','0 -91px');
		    }
		  }).mouseout(function() {
		  	if(jQuery(this).css('font-weight')=='100'){
		    	jQuery(this).css('background-position','0 0');
		    }
		  }).click(function(){
		    jQuery('#tabMenu > li').css('background-position','0 0');
		    jQuery('#tabMenu > li').css('font-weight','100');
		    jQuery(this).css('background-position','0 -182px');
		    jQuery(this).css('font-weight','400');
		  });
		  jQuery('#defaultClick').click();
		});
		function loadModules(id,url){
			parent.leftFrame.location.href="left.action?modulePid="+id;
			if(url.indexOf("javascript:") <0) {
				if(url.indexOf("=") > 0 ) 
				{ 
					parent.mainFrame.location.href=url+"&modulePid="+id;
				} else {
					parent.mainFrame.location.href=url+"?modulePid="+id;
				}
			}
		}
	</script>
	</s:if>
	</head>
	<body style="min-height: 91px;">
		<div class="topDiv">
			<div class="logo"></div>
			<div class=carousel>
				<s:if test="moduleList.size()>5"><a id=prev-03 class="prev disabled" href="javascript:void(0);"></a></s:if>
				<div id="demo-03" class="jCarouselLite">
					<ul id="tabMenu">
						<s:iterator value="moduleList" status="idx">
							<s:if test="moduleList.size==1">
								<li id="defaultClick"  style="display: none;background:url(./images/firstmenu/<s:property value='moduleImg'/>) no-repeat 0 0;font-weight:100; " title='<s:property value="moduleName"/>'
										onclick="loadModules('<s:property value="moduleId"/>','<s:property value="moduleUrl"/>')">
										<span class="new"></span>
								</li>
							</s:if>
							<s:else>
								<s:if test="#idx.index==0">
									<li id="defaultClick"  style="background:url(./images/firstmenu/<s:property value='moduleImg'/>) no-repeat 0 0;font-weight:100;" title='<s:property value="moduleName"/>'
										onclick="loadModules('<s:property value="moduleId"/>','<s:property value="moduleUrl"/>')">
										<span class="new"></span>
									</li>
								</s:if>
								<s:else>
									<li style="background:url(./images/firstmenu/<s:property value="moduleImg"/>) no-repeat 0 0;font-weight:100;"
										title="<s:property value="moduleName"/>"
										onclick="loadModules('<s:property value="moduleId"/>','<s:property value="moduleUrl"/>')"></li>
								</s:else>
							</s:else>
						</s:iterator>
						<s:if test="moduleList.size()==0"><li style="display:none;" ></li></s:if>
					</ul>
				</div>
				<s:if test="moduleList.size()>5"><a id="next-03" class="next" href="javascript:void(0);"></a></s:if>
			</div>
		</div>
		<div class="infoDiv">
			<div class="infoLeft">
				日期：<s:property value="currentDate" />
				用户：<s:property value="loginfo" />
			</div>
			<div class="infoCorner"></div>
			<div class="infoRight">
				<a href="javascript:void(0);" onclick="parent.location.reload()" class="refresh">刷新</a>
				<a href="javascript:void(0);" onclick="window.top.showUserInfo()" class="tongxun">个人信息</a>
				<a href="javascript:void(0);" onclick="window.top.goOut()" class="quit">退出</a>
			</div>
		</div>
	</body>
</html>
