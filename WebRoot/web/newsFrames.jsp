<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>最新资讯-华房网</title>
<%@ include file="include/heard.inc"%>
<base href="<%=basePath%>" />
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<%@ include file="include/top.inc"%>
			<%@ include file="include/nav_news.inc"%>
		</div>
		<!-- header end -->
		<div class="content w1190 auto">
			<div class="list-contents">

				<div class="list-results" >
					<div class="news-tabs">
						<ul class="clearfix">
							<s:iterator value="infoTcategoryList" status="idx">
								<li <s:if test="#idx.index == 0">class="current"</s:if>
								onclick="loadList('<s:property value="columnId"/>')"><s:property
										value="columnName" /></li>
							</s:iterator>
						</ul>
					</div>
					<script>
						function loadList(columnId){
							jQuery('#ifm').attr('src','newsList.action?columnId=' + columnId + '&districtId=<s:property value="districtId"/>')
						}
						/**
						 * 自适应高度
						 */
						function setWinHeight(){
							var ifm = document.getElementById("ifm");
						    var subWeb = document.frames ? document.frames["ifm"].document :
							ifm.contentDocument;
						    if(ifm != null && subWeb != null) {
						    	ifm.height = subWeb.body.scrollHeight + 20;
						    }
						}
						
						jQuery(function(){
							$("#ifm").load(function(){
							    var thisheight = $(this).contents().find(".news-cont").height() + 60;
							    $(this).height(thisheight < 500 ? 500 : thisheight);
							});
							jQuery('.news-tabs > ul > li:first').click();
						});
						
					</script>
					<iframe frameborder="0" scrolling="no" src=""
						width="100%" height="500" id="ifm" name="ifm" onload="setWinHeight()" style="padding: 0;margin: 0;"></iframe>
				</div>
					<div class="news-cont">
						<%@ include file="include/right.inc" %>
					</div>
				</div>
				<!--底部开始 -->
				<%@ include file="include/footer.inc" %>
				<!--底部结束 -->
			</div>
		</div>
</body>
</html>
