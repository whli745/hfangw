package util.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.pojo.LoginUserInfo;
import pt.xtgl.jcgl.pojo.Loginfo;
import pt.xtgl.jcgl.service.ILogActionDescService;
import pt.xtgl.jcgl.service.ILogService;
import util.BaseParameter;
import util.Common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginfoInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ILogService logService;
	/**
	 * 系统功能描述service
	 */
	private ILogActionDescService logActionDescService;

	public static Map<String, LogActionDesc> logMap = new HashMap<String, LogActionDesc>();

	@Override
	public String intercept(ActionInvocation invocation) {
		try {
			// 调用的uri
			HttpServletRequest request = ServletActionContext.getRequest();
			// action 名称
			String uri = request.getRequestURI();
			if (uri.contains("/")) {
				uri = uri.substring(uri.lastIndexOf('/') + 1);
			}
			if(Common.isNullOrSpace(uri))
				return invocation.invoke();
			LogActionDesc logActionDesc = logMap.get(uri);
			if (logActionDesc == null) {// 不存在
				if(!uri.startsWith("get") && !uri.startsWith("init")
						&& !uri.startsWith("list") && !uri.startsWith("query") 
						&& !uri.startsWith("show")){//过滤掉查询类的请求
					logActionDesc = logService.getLogActionDescByUrl(uri);
					if (logActionDesc == null) {
						logActionDesc = new LogActionDesc(uri, "未定义", "未定义",
								BaseParameter.YES);
						logActionDescService.save(logActionDesc);
					}
					logMap.put(uri, logActionDesc);
				}
			}
			// 需要记录日志
			if (logActionDesc != null
					&& logActionDesc.getUseFlag().equals(BaseParameter.NO)) {
				Object obj = ActionContext.getContext().getSession()
						.get(BaseParameter.LOGIN_INFO);
				LoginUserInfo loginUserInfo = null;// 平台用户
				Integer userType = 0;
				String userName = "";
				String userOid = "";
				String organId = "";
				String areaId = null;
				if (obj == null) {

				} else if (obj instanceof LoginUserInfo) {// 平台用户
					loginUserInfo = (LoginUserInfo) obj;
					userType = 1;
					userOid = loginUserInfo.getUserOid();
					userName = loginUserInfo.getUserName();
					areaId = loginUserInfo.getUser().getAreaId();
				}

				Loginfo loginfo = new Loginfo(userType, 0,
						logActionDesc.getActionDesc(), userName, userOid,
						organId, new Date(), ServletActionContext.getRequest()
						.getRemoteAddr(), "", areaId);
				loginfo.setActionUrl(this.buildOriginalURL(request, uri));
				logService.saveOrUpdate(loginfo);
			}

			return invocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return invocation.invoke();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 访问全路径
	 * 
	 * @author dusd
	 * @date 2015-12-9
	 * @param request
	 * @return
	 */
	private String buildOriginalURL(HttpServletRequest request, String uri) {
		StringBuffer originalURL = new StringBuffer(uri);
		@SuppressWarnings("rawtypes")
		Map parameters = request.getParameterMap();
		if (parameters != null && parameters.size() > 0) {
			originalURL.append("?");
			for (@SuppressWarnings("rawtypes")
			Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String[] values = (String[]) parameters.get(key);
				for (int i = 0; i < values.length; i++) {
					originalURL.append(key).append("=").append(values[i])
							.append("&");
				}
			}
		}
		String url = originalURL.toString();
		if(url.indexOf("&") > 0)
			url = url.substring(0, url.lastIndexOf("&"));
		return url;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public ILogActionDescService getLogActionDescService() {
		return logActionDescService;
	}

	public void setLogActionDescService(
			ILogActionDescService logActionDescService) {
		this.logActionDescService = logActionDescService;
	}
}
