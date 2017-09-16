<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>文件上传工具V2.1</title>

<link href="${pageContext.request.contextPath}/swfupload/css/swfupload-default.css" rel="stylesheet"type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/handlers.js"></script>

<script type="text/javascript">
	var contextPath="${pageContext.request.contextPath}";
	function startLoad(){
		var url=contextPath+"/servlet/FileUploadServlet.htm"; //处理上传的servlet
		var sizeLimit="1 MB";// 文件的大小  注意: 中间要有空格
		var types="*.jpg;*.jpeg;*.gif"; //注意是 " ; " 分割 
		var typesdesc="web iamge file"; //这里可以自定义
		var uploadLimit=20;  //上传文件的 个数
		initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit);
	}
</script>
</head>
<body>
<center>
<input type="button" onclick="startLoad()" value="批量图片上传"/></center>
</body>
</html>