<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="include/heard.inc"%>
<base href="<%=basePath%>" />
</head>
<body style="padding: 0;margin: 0;">

	<div class="list-results bd" style="width: 892px; padding-top: 15px;margin: 20px auto 0;">
		<div class="news-detail" style="min-height: 925px;">
			<s:property value="infoTcontent.content" escape="false" />
		</div>
		<div class="share clearfix">
			<div class="bdsharebuttonbox left">
				<a href="#" class="bds_more" data-cmd="more"></a><a href="#"
					class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#"
					class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#"
					class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#"
					class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a
					href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
			</div>
			<div class="share-r right">
				<a href="javascript:window.print();">【打印此页】</a><a
					href="javascript:common.closePage();">【关闭窗口】</a>
			</div>
		</div>
	</div>
	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
