package util.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.beans.factory.annotation.Autowired;

import pt.xtgl.jcgl.pojo.LoginUserInfo;
import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.service.ISysModuleService;
import util.BaseParameter;

/**
 * 过滤用户请求action下功能按钮权限
 * 
 * @author meidj
 * @date 2013-05-16
 * 
 */
public class UserModuleRoleFilter extends StrutsPrepareAndExecuteFilter {
	@Autowired
	private static ISysModuleService sysModuleService;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		JSONArray jarray = null;

		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getServletPath();
		url = url.substring(1, url.length());// 取得请求action并去除“/”

		String actionReqId = BaseParameter.initActionRequest.get(url); // 从静态变量获取请求的菜单ID
		if (null != actionReqId && !"".equals(actionReqId.trim())) {// 判断请求是否存在菜单ID

			Object obj = request.getSession().getAttribute(
					BaseParameter.LOGIN_INFO);

			LoginUserInfo lu = null;

			if (obj != null) {
				lu = (LoginUserInfo) obj;
			}

			List<SysModule> ls = null;
			try {
				if(lu!=null){
					ls = sysModuleService.querySysButtonModuleListByPid(
							lu.getUser(), actionReqId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != ls) {
				jarray = new JSONArray();
				for (SysModule module : ls) {
					JSONObject jobject = new JSONObject();
					jobject.accumulate("moduleName", module.getModuleName());
					jobject.accumulate("moduleUrl", module.getModuleUrl());
					jobject.accumulate("moduleControl",
							module.getModuleControl());
					jobject.accumulate("controlType", module.getControlType());
					jobject.accumulate("isValidate", module.getIsValidate());
					jobject.accumulate("validateContent",
							module.getValidateContent());
					jobject.accumulate("isJsRequest", module.getIsJsRequest());
					jarray.add(jobject);
				}
			}
		}
		req.setAttribute("btnModuleRole", jarray == null ? "[]" : jarray);

		chain.doFilter(req, res);
	}

	public static ISysModuleService getSysModuleService() {
		return sysModuleService;
	}

	public static boolean setSysModuleService(ISysModuleService sysModuleService) {
		UserModuleRoleFilter.sysModuleService = sysModuleService;
		return true;
	}

}
