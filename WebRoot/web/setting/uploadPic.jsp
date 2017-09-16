<%@page import="pt.xtgl.jcgl.pojo.SysOrgan"%>
<%@ page import="java.io.File" %> 
<%@ page import="java.io.IOException" %> 
<%@ page import="java.io.PrintWriter" %> 
<%@ page import="java.util.Iterator" %> 
<%@ page import="java.util.List" %> 
<%@ page import="javax.servlet.ServletException" %> 
<%@ page import="javax.servlet.http.HttpServlet" %> 
<%@ page import="javax.servlet.http.HttpServletRequest" %> 
<%@ page import="javax.servlet.http.HttpServletResponse" %> 
<%@ page import="org.apache.commons.fileupload.FileItem" %> 
<%@ page import="org.apache.commons.fileupload.FileItemFactory" %> 
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %> 
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %> 
<%
	response.setContentType("text/html; charset=UTF-8");

	String rootPath = this.getServletContext().getRealPath("");  
	String fileName = request.getParameter("fileName");  
	 //上传操作  
	  FileItemFactory factory = new DiskFileItemFactory();  
	  ServletFileUpload upload = new ServletFileUpload(factory);  
	  upload.setHeaderEncoding("UTF-8");
	  
	  try{  
		List items = upload.parseRequest(request);  
	      if(null != items){  
			Iterator itr = items.iterator();  
	          while(itr.hasNext()){  
	              FileItem item = (FileItem)itr.next();
	              System.out.println(item);
	              if(item.isFormField()){  
	                 continue;  
	              }else{  
	                  String path="/web/images/";
	                  File savedFile = new File(rootPath+path,fileName); 
	                  savedFile.delete();
	                  item.write(savedFile); 
	                  
	                  response.sendRedirect("bannerPicManage.jsp");  
	              }  
	          }  
	      }  
	  }catch(Exception e){  
	      e.printStackTrace();  
	  }
  %>
