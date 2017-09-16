package util.interceptor;

import java.util.Map;

import pt.xtgl.jcgl.pojo.LoginUserInfo;
import util.BaseParameter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 网站拦截器
 * @author dusd
 * @date 2015-11-28
 */
public class WebLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Object obj = session.get(BaseParameter.WEB_LOGIN_INFO);
		if (obj == null || !(obj instanceof LoginUserInfo)) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}
