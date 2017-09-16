package pt.xtgl.jcgl.action;

import java.util.HashMap;

import pt.xtgl.jcgl.pojo.LoginUserInfo;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysUserService;
import util.BaseParameter;
import util.base.action.BaseAction;
import util.servlet.InfoCache;

import com.opensymphony.xwork2.Action;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISysUserService sysUserService;
	private SysUser user;
	private String userCode;
	private String password;
	private String yzm;
	private String ret;
	private String modulePid;
	private String custName;

	/**
	 * 显示登录界面
	 */
	public String login() throws Exception {
		return SUCCESS;
	}

	// 登录验证
	public void doLogin() {
		try {
			if(!super.getRequest().getSession().getAttribute("RANDOMVALIDATECODEKEY").toString().equals(yzm)){
				super.returnAjaxInfo("1");
				return ;
			}
			// 根据用户名称查询用户
			user = sysUserService.findUserByCodeAndPass(userCode);
			// 登录验证
			String message = sysUserService.checkLogin(user, password);
			// “0”表示登录成功
			if (message.equals("0")) {
				LoginUserInfo loginfo = new LoginUserInfo();
				loginfo.setUserName(user.getUserName());
				loginfo.setUserOid(user.getUserId());
				loginfo.setUser(user);
				loginfo.setPwd(password);
				loginfo.setSsessionId(getRequest().getSession().getId());
				getRequest().getSession().setAttribute(
						BaseParameter.LOGIN_INFO, loginfo);
				HashMap<String, String> userMap = BaseParameter.userMap;
				userMap.put(user.getUserId(), getRequest().getSession().getId());
			}
			// 是否ajax实现，页面无刷新
			super.returnAjaxInfo(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getInfo(String id) {
		return InfoCache.getInstance("INFO").getSetInfo(id);
	}

	public String doLoginSuccess() {
		// 读取session中保存的用户信息
		user = super.getLoginUser();
		if (user != null) { // 如果已经存在登录信息，直接跳转到主页面
			return SUCCESS;
		}
		return Action.LOGIN;
	}

	public String loginOut() {
		try {
			getRequest().getSession().removeAttribute(BaseParameter.LOGIN_INFO);
		} catch (Exception e) {
			return ret;
		}
		return ret;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getModulePid() {
		return modulePid;
	}

	public void setModulePid(String modulePid) {
		this.modulePid = modulePid;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getHdno() {
		return com.zhuofan.auto.BaseParameter.getHdno();
	}

}
