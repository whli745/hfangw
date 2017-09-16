package util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SetCharacterEncodingFilter implements Filter {
	protected String encoding = null;
	protected String ajax = null;
	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getHeader("X-Requested-With") != null
				&& req.getHeader("X-Requested-With").equalsIgnoreCase(
						"XMLHttpRequest")) {
			request.setCharacterEncoding(ajax == null ? "UTF-8" : ajax);
			request.getParameter("abc");
			/*String name = request.getParameter("sysModule.moduleName");
			System.out.println("name===" + (name == null ? "null" : name));*/
		} else {
			request.setCharacterEncoding("UTF-8");
			if ((this.ignore) || (request.getCharacterEncoding() == null)) {
				String encoding = selectEncoding(request);
				if (encoding != null) {
					request.setCharacterEncoding(encoding);
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.ajax = filterConfig.getInitParameter("ajax");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}
}