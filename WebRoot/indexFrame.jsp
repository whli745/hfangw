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
  </head>
	<frameset rows="134,*,28" frameborder="no" border="0" framespacing="0">
	  <frame src="top.action?instantMessagingUrl=<s:property value="instantMessagingUrl"/>" name="topFrame" scrolling="No" noresize="noresize" id="topFrame"  />
	  <frameset cols="168,*" id="aa" frameborder="no" border="0" framespacing="0">
			<frame src="left.html" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame"  />
			<frameset cols="0,*" frameborder="no" border="0" framespacing="0">
			  <frame src="left1.html" name="leftFrame1" scrolling="No" noresize="noresize" id="leftFrame1"  />
			<frameset cols="7,*" frameborder="no" border="0" framespacing="0">
			  <frame src="left2.html" name="leftFrame2" scrolling="No" noresize="noresize" id="leftFrame2"  />
			<frameset cols="*,13" frameborder="no" border="0" framespacing="0">
			<frame src="welcome.jsp" name="mainFrame" id="mainFrame"  />
			<frame src="right.html" name="rightFrame" scrolling="No" noresize="noresize" id="rightFrame" />
		</frameset>
		</frameset>
		</frameset>
	  </frameset>
	  <frame src="footer.html" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame"  />
	</frameset>
	<noframes>
	<body>
	</body>
	</noframes>
</html>