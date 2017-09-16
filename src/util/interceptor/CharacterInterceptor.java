package util.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import util.BaseParameter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CharacterInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3495342446019147166L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equalsIgnoreCase(
						"XMLHttpRequest")) {
			request.setCharacterEncoding(BaseParameter.ajaxEncoding);
		} else {
			request.setCharacterEncoding(BaseParameter.encoding);
		}
		return invocation.invoke();
	}
}
