package web.action;

import javax.servlet.http.Cookie;

import pt.wzgl.pzgl.service.INetUserService;
import sun.misc.BASE64Decoder;
import util.BaseParameter;
import util.Common;
import util.base.action.BaseAction;
import util.image.RandomValidateCode;

/**
 * 网站登录管理
 * @author chail
 * @since 2016-1-5
 */
public class WebLoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private INetUserService netUserService;
	/**
	 * 
	 */
	private String jumpUrl = "";
	/**
	 * 网站登录 用户名
	 */
	private String userName;
	/**
	 * 网站登录 密码
	 */
	private String password;
	/**
	 * 输入的验证码
	 */
	private String validateCode;;
	/**
	 * 用户登录类型 0-个人 1-企业
	 */
	private String userType;
	/**
	 * 网站登录初始化
	 * @author chail
	 * @createDate 2016-01-05
	 * @return SUCCESS
	 */
	public String initWebLogin(){
		try{
			System.out.println("初始化初始化初始化初始化登录登录登录登录登录登录登录");
//			if(!Common.checkPropsLegal(null, new String[]{loginType,jumpUrl})){
//				getCookieValues();//自动登陆
//			}else{
//				return DERROR;
//			}
		}catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	/**
	 * 用户登录
	 * @author chail
	 * @date 2015-11-28
	 */
	public void doWebUserLogin() {
		try {
			userName  = java.net.URLDecoder.decode(userName,"UTF-8");
			String message = netUserService.webCheckLogin(userName,
					password, getRequest());
			//登录成功 设置session过期时间 
			if(!Common.isNullOrSpace(message) || message.trim().equals(BaseParameter.NO)) { 
				super.getRequest().getSession().setMaxInactiveInterval(Integer.valueOf(BaseParameter.WEB_SESSION_TIMEOUT) * 5);
			}
			returnAjaxInfo(message);
		} catch (Exception e) {
			error("用户登录出错", e);
		}
	}
	/**
	 * 验证码验证
	 * 
	 * @author dusd
	 * @date 2015-11-28
	 */
	public void validatCode() {

		String code = (String) getRequest().getSession().getAttribute(
				RandomValidateCode.RANDOM_CODE_KEY);
		try {
			if (validateCode.equals(code)) {
				returnAjaxInfo("1");
			} else {
				returnAjaxInfo("0");
			}
		} catch (Exception e) {
			error(null, e);
		}
	}
	/**
	 * 获取存放于cookie中的数据，用户自动登陆
	 * @createDate 2014-11-17
	 * @author Liangmh
	 */
	private void getCookieValues(){
		try {
			userName = getCookieValue(getRequest().getCookies(), "userName", "");
			if( getCookieValue(getRequest().getCookies(), "userPwd", "").trim().length() > 0 ){
				password = new String(new BASE64Decoder().decodeBuffer(getCookieValue(getRequest().getCookies(), "userPwd", "")));
			}else {
				password = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取Cookie
	 * @param cookies
	 * @param cookieName
	 * @param defaultValue
	 * @return
	 * @createDate 2014-11-12
	 * @author Liangmh
	 */
	private String getCookieValue(Cookie[] cookies, String cookieName,
			String defaultValue) {
		String result = defaultValue;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					if(cookie.getValue()!=null){
						if(!cookie.getValue().equals("undefined")){
							return cookie.getValue();
						}
					}
				}
			}
		}
		return result;
	}
	public String getJumpUrl() {
		return jumpUrl;
	}
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public INetUserService getNetUserService() {
		return netUserService;
	}
	public void setNetUserService(INetUserService netUserService) {
		this.netUserService = netUserService;
	}
}
