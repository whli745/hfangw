package util.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import util.BaseParameter;

public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		// 系统运行模式
		String runMode = config.getInitParameter("run_mode");
		BaseParameter.runMode = runMode == null ? BaseParameter.runMode
				: runMode;
		BaseParameter.encoding = config.getInitParameter("encoding");
		BaseParameter.ajaxEncoding = config.getInitParameter("ajax");

		//网站用户 session过期时间
		BaseParameter.WEB_SESSION_TIMEOUT = config.getInitParameter("WEB_SESSION_TIMEOUT");
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
