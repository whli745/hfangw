package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.image.ImageMarkLogoByIcon;


@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

	private static final int UPLOAD_SUCCSSS=0;    // "上传文件成功！", 
	private static final int UPLOAD_FAILURE=1;    // "上传文件失败！"), 
	private static final int UPLOAD_TYPE_ERROR=2; // "上传文件类型错误！"), 
	private static final int UPLOAD_OVERSIZE=3;   // "上传文件过大！"),
	private static final int UPLOAD_ZEROSIZE=4;   // "上传文件为空！"),
	private static final int UPLOAD_NOTFOUND=5;   // "上传文件路径错误！")

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		 String rootPath = request.getParameter("rootPath");  
	
//		 String param = request.getParameter("param");
		 
		 if(rootPath == null) rootPath = "";  
		    rootPath = rootPath.trim();  
		 if(rootPath.equals("")){  
			 rootPath = this.getServletContext().getRealPath("");  
		 }  
		
		 //上传操作  
		  FileItemFactory factory = new DiskFileItemFactory();  
		  ServletFileUpload upload = new ServletFileUpload(factory);  
		  upload.setHeaderEncoding("UTF-8");  
		  try{  
		    @SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);  
		      if(null != items){  
		        @SuppressWarnings("rawtypes")
				Iterator itr = items.iterator();  
		          while(itr.hasNext()){  
		              FileItem item = (FileItem)itr.next();
		              if(item.isFormField()){  
		                 continue;  
		              }else{  
		                   //以当前精确到秒的日期为上传的文件的文件名  
		                  String path="/swfupload/files";
		                  String fileName = generateFileName(item.getName());
		                  File savedFile = new File(rootPath+path,fileName);  
		                  item.write(savedFile); 
		                  
		                  // 给图片添加水印
		          		  ImageMarkLogoByIcon.markImageByIcon(rootPath + "/logo.png", savedFile.getAbsolutePath(), savedFile.getAbsolutePath(),
		          				null);
		                  
		                  out.print("{status:" + FileUploadServlet.UPLOAD_SUCCSSS + ",message:'"+path+"/"+fileName+"'}");
		              }  
		          }  
		      }  
		  }catch(Exception e){  
		      e.printStackTrace();  
		  }
			
	}

	 /** 
     * new文件名= 时间 + 全球唯一编号 
     * @param fileName old文件名 
     * @return new文件名 
     */  
    private String generateFileName(String fileName) {  
        String uuid=UUID.randomUUID().toString();  
        int position = fileName.lastIndexOf(".");     
        String extension = fileName.substring(position);     
        return uuid + extension;     
    }  
    
    
}
