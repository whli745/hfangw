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
<script type="text/javascript">
jQuery(document).ready(function(){
	parent.document.getElementById('infoTcontentFrame').contentWindow.location.reload();
	Dialog.close();
});
</script>
</head>
<body>
</body>
</html>
