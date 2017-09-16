<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="infoTcontent.contentMainTitle"/>-华房网</title>
<base href="<%=basePath%>" />
<%@ include file="include/heard.inc"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>web/js/nifty/css/component.css" />
<script type="text/javascript">
	jQuery(function () {  
		jQuery(window).scroll(function () {  
	        var top = jQuery(window).scrollTop() + 200;  
	        var left = jQuery(window).scrollLeft() + 320;  
	        jQuery("#editInfo").animate({ "top": top }, 0);  //方式一  效果比较理想  
	    });
		jQuery('#yzm').click(function(){
			jQuery("#yzm").attr("src", "imageServlet?r=" + Math.random());
		});
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	jQuery('#saveBtn').click();
		    	return false;
		     }
		}
		jQuery('.mainDiv input').click(function(){
			layer.closeAll();
		});
		jQuery('.md-overlay').click(function(){
			layer.closeAll();
		});
	});
	
	function toSave(){
		var linkman = jQuery.trim(document.getElementById("linkMan").value);
		var linkPhone = jQuery.trim(document.getElementById("linkPhone").value);
		var yzm = jQuery.trim(document.getElementById("yzmText").value);
		if (!jQuery("#tipchk").is(':checked')) {
			common.alert("请先阅读并同意《华房网红包服务协议》");
			return false;
		}
		if (linkman == "" || linkman == '请填写您的姓名') {
			common.tips('请填写您的姓名！', "#linkMan",3);
			return false;
		}
		if (linkPhone == "" || linkPhone == '请输入您的手机号') {
			common.tips('请输入您的手机号！', '#linkPhone',3);
			return false;
		}
		var telReg = !!jQuery('#linkPhone').val().match(/^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/);
		if (!telReg) {
			common.tips('您的手机号码填写错误！', '#linkPhone',3);
			return false;
		}
		if (yzm == "" || yzm == '请输入验证码') {
			common.tips('请输入验证码！', '#yzmText',1);
			return false;
		}
		jQuery.ajax({
			type: "POST",
			url: "hfwContactsEdit.action?r=" + Math.random(),
			data: jQuery("#packetForm").serialize(),
			success: function (msg) {
				if (msg != '') {
					if (msg == '1') {
						common.alertFocus('验证码不正确！', "userCode");
					}else if (msg == '2') {
						common.alert("每个手机号只能领取一次！");
					} else if (msg == '3') {
						common.alert("领取成功！",function(){
							jQuery("#linkMan").val("请填写您的姓名");
							jQuery("#linkPhone").val("请输入您的手机号");
							jQuery("#yzmText").val("请输入验证码");
							jQuery("#close_img").click();
						},1);
					}
				}
			}
		});
	}
	
	/* document.getElementById("saveBtn").addEventListener("KeyUp",function(){
		if (event.keyCode == "13") {
			//回车执行领取
			toSave();
		}

	}); */
</script>
</head>
<body>
	<s:if test="infoTcontent.packetList.size() >0 && !''.equals(infoTcontent.packetList[0].packetName) && !'敬请期待'.equals(infoTcontent.packetList[0].packetName)">
		<div id="editInfo" class="md-trigger" data-modal="modal-11" title="点击领取" style="right:0;position:absolute;top:200px;width: 190px;z-index: 9999;text-align:center;cursor: pointer;">
				<img src="<%=basePath%>web/images/f774ec0f316648d433da15749fd641723.png" style="width: 55%;"/>
				<div style="color: #BC2414;font-size: 14px;font-weight: bold;text-align: center;"><s:property value="infoTcontent.packetList[0].packetName" /></div>
			</div>
			<div class="md-modal md-effect-11" id="modal-11" style="width: 400px;">
				<img id="close_img" src="<%=basePath%>web/images/delete.png" class="md-close" />
				<div class="md-content">
					<h3>申请华房网专属优惠</h3>
					<div class="mainDiv">
						<form name="packetForm" id="packetForm">
							<input type="hidden" value="<s:property value="infoTcontent.packetList[0].oid"/>" name="q.packet.oid" id="packetId"/>
							<input type="text" class="rp_ipt" name="q.linkMan" id="linkMan"
								   value="请填写您的姓名" onfocus="if(this.value=='请填写您的姓名')this.value=''" onblur="if(this.value==''){this.value='请填写您的姓名'}"/>
							<input type="text" class="rp_ipt" name="q.linkPhone" id="linkPhone"
								   value="请输入您的手机号" onfocus="if(this.value=='请输入您的手机号')this.value=''" onblur="if(this.value==''){this.value='请输入您的手机号'}"/>
							<input type="text" class="rp_ipt" style="width: 200px;" name="yzm" id="yzmText"
								   value="请输入验证码" onfocus="if(this.value=='请输入验证码')this.value=''" onblur="if(this.value==''){this.value='请输入验证码'}"/>
							<img id="yzm" src="imageServlet"/>
						</form>
						<button id="saveBtn" onclick="toSave();">立即领取优惠卷</button>
						<ul>
							<li style="text-align: center;"><label><input id="tipchk" type="checkbox" checked="checked"/>&nbsp;我已阅读并同意《华房网红包服务协议》</label></li>
							<li>华房网郑重承诺：</li>
							<li>1、华房网提供的服务均免费，部分楼盘开盘活动，会在认购时用服务费抵扣房款。</li>
							<li>2、充分尊重用户意愿，提供一对一温馨服务，免受传统中介骚扰之苦。</li>
							<li>3、保证用户信息安全，多重防护措施严格保护用户信息。</li>
						</ul>
					</div>
				</div>
			</div>
	</s:if>
 <div class="wrapper">
    <!--头部开始 -->
  <div class="header">
  	<%@ include file="include/top.inc"%>
	<%@ include file="include/nav_news.inc"%>
  </div>
<!-- header end -->
    <div class="content w1190 auto">
      <div class="list-contents">
        <div class="list-results bd">
          <div class="news-detail">
            <div class="news-detail-tit">
              <h2><s:property value="infoTcontent.contentMainTitle"/></h2>
              <div class="autor"><span>时间：<s:date name="infoTcontent.createDate" format="yyyy-MM-dd"/></span> <span>作者：<s:property value="infoTcontent.issueUsername" escape="false"/></span><span>来源：<s:property value="infoTcontent.issueOrgan" escape="false"/></span></div>
            </div>
            <div class="news-document">
            	<s:property value="infoTcontent.content" escape="false"/>
            </div>
          </div>
          <div class="share clearfix">
            <div class="bdsharebuttonbox left"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
            <div class="share-r right"><a href="javascript:window.print();">【打印此页】</a><a href="javascript:common.closePage();">【关闭窗口】</a></div>
          </div>
        </div>
        <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
        <%@ include file="include/right.inc" %>
      </div>
    </div>
     <!--底部开始 -->
     <%@ include file="include/footer.inc" %>
    <!--底部结束 -->
  </div>  
  <div class="md-overlay"></div>
    <script src="<%=basePath%>web/js/nifty/js/classie.js"></script>
	<script src="<%=basePath%>web/js/nifty/js/modalEffects.js"></script>    
</body>
</html>
