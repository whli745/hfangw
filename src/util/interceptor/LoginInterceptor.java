package util.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 后台拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Object obj = session.get(util.BaseParameter.LOGIN_INFO);
		if (obj == null ) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}
