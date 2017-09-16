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
<title>合肥华房网络科技有限公司</title>
<%@ include file="include/heard.inc"%>
<base href="<%=basePath%>" />
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<%@ include file="include/top.inc"%>
			<%@ include file="include/nav_detail.inc"%>
		</div>
		<!-- header end -->
		<div class="content w1190 auto">
			<div class="list-contents">
				<div class="list-results">
					<div class="news-tabs">
						<ul class="clearfix">
							<s:iterator value="infoTcategoryList_2" status="idx">
								<li <s:if test="#idx.index == 0">class="current"</s:if>
								id="<s:property value='columnId'/>"
								onclick="loadList('<s:property value="columnId"/>')"><s:property
										value="columnName" /></li>
							</s:iterator>
						</ul>
					</div>
					<script>
						function loadList(columnId){
							jQuery('#ifm').attr('src','newsAboutUsDetail.action?columnId=' + columnId)
						}
						
						jQuery(function(){
							$("#ifm").load(function(){
							    var thisheight = $(this).contents().find(".list-results").height()+50;
							    $(this).height(thisheight < 500 ? 500 : thisheight);
							});

							jQuery('.news-tabs > ul > li[id="<s:property value='columnId'/>"]').click();
						});
						
					</script>
					<iframe frameborder="0" scrolling="no" src=""
						width="100%" id="ifm" name="ifm" style="padding: 0;margin: 0;border: 0;"></iframe>
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
