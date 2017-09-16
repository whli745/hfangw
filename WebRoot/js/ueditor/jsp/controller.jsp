<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

	String basePath = request.getScheme() + "://" 
			+ request.getServerName() 
			+ ":" + request.getServerPort() 
			+ request.getContextPath() + "/";
		
	request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	String configString = new ActionEnter( request, rootPath ).exec();
	//替换配置在config.json中的{urlPrefix}为项目访问路径
	configString = configString.replace("{urlPrefix}", basePath);
// 	System.out.println(configString);
	out.write( configString );
	
%>