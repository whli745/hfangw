package util.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileOperation;

@SuppressWarnings("serial")
public class FileDownload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FileDownload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filepath = request.getParameter("filepath");
		//filepath = new String(filepath.getBytes("ISO-8859-1"), "GBK");
		
		if(filepath.endsWith(".jsp")||filepath.endsWith(".xml")||filepath.endsWith(".class")||filepath.contains("..")||filepath.endsWith(".properties")){
			return;
		}
		String dispname = request.getParameter("dispname");
		response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(dispname, "UTF-8"));
        
        filepath = this.getServletConfig().getServletContext().getRealPath(
				"/" + filepath);
        
        response.setContentLength((int) new File(filepath).length());
        BufferedInputStream fileInputStream;
        fileInputStream = new BufferedInputStream(new FileInputStream(filepath));
        OutputStream output = response.getOutputStream();
        FileOperation.outPut(fileInputStream, output);
        fileInputStream.close();
        output.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
