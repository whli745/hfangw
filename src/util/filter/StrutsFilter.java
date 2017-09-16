package util.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class StrutsFilter extends StrutsPrepareAndExecuteFilter {
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		// 不过滤的url
		String url = request.getRequestURI();
		if (url.contains("ueditor/jsp/imageUp.jsp")
				|| url.contains("imageServlet") || url.contains("FileDownload")
				|| url.contains("WebService")) {
			chain.doFilter(req, res);
		} else {
			super.doFilter(req, res, chain);
		}
	}

}
