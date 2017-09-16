package pt.wzgl.pzgl.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import pt.wzgl.pzgl.dao.INetUserDao;
import pt.wzgl.pzgl.pojo.NetUser;
import pt.wzgl.pzgl.service.INetUserService;
import pt.xtgl.jcgl.pojo.NetUserLoginInfo;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

/**
 * 网上申报用户 service类
 * 
 * @author : yaosw
 * @date : 2014-09-23
 */
public class NetUserServiceImpl implements INetUserService {

	/**
	 * INetUserDao
	 */
	private INetUserDao netUserDao;

	public INetUserDao getNetUserDao() {
		return netUserDao;
	}

	public void setNetUserDao(INetUserDao netUserDao) {
		this.netUserDao = netUserDao;
	}

	@Override
	public NetUser getNetUserById(String id) throws Exception {
		return netUserDao.getNetUserById(id);
	}
	
	@Override
	public NetUser findUserByCodeAndPass(String code) throws Exception {
		return netUserDao.findUserByCodeAndPass(code);
	}

	@Override
	public void updateNetUser(NetUser netUser) throws Exception {
		netUserDao.saveOrUpdate(netUser);
	}

	@Override
	public void portraitSetting(String photoUrl, NetUser netUser)
			throws Exception {
		netUserDao.portraitSetting(photoUrl, netUser);
	}

	@Override
	public NetUser getNetUserByEmail(String userEmail) throws Exception {
		return netUserDao.getNetUserByEmail(userEmail);
	}

	@Override
	public ResultPage findNetUsers(int page, int pageRows,NetUser user) throws Exception {
		return netUserDao.findNetUsers(page, pageRows,user);
	}
	
	@Override
	public void saveNetUser(NetUser netUser) throws Exception {
		netUserDao.saveOrUpdate(netUser);
	}

	@Override
	public void saveOrUpdate(NetUser netUser) throws Exception {
		netUserDao.saveOrUpdate(netUser);
	}

	@Override
	public NetUser checkUserForUnicode(NetUser user) throws Exception {
		return netUserDao.findUserByUserParams(user);
	}

	@Override
	public String webCheckLogin(String userName, String userPass,
			HttpServletRequest request) throws Exception {
		String message = "0";// 为0时登录成功，为1时验证码不正确，为2时用户不存在，为3时用户密码不正确
		//通过用户名查询用户
		NetUser netUser = netUserDao.findUserByCodeAndPass(userName);
		if(null == netUser) {
			return "2";
		}
		userPass = Common.md5(userPass);
		if(!userPass.equals(netUser.getUserPass())) {
			return "3";
		}
		//信息存入到session中
		NetUserLoginInfo netUserLoginInfo = new NetUserLoginInfo();
		netUserLoginInfo.setUserName(netUser.getUserName());
		netUserLoginInfo.setUserOid(netUser.getId());
		netUserLoginInfo.setNetUser(netUser);
		netUserLoginInfo.setPwd(netUser.getUserPass());
		netUserLoginInfo.setSsessionId(request.getSession().getId());
		request.getSession().setAttribute( BaseParameter.WEB_LOGIN_INFO, netUserLoginInfo);
		HashMap<String, String> userMap = BaseParameter.userMap;
		userMap.put(netUser.getId(), request.getSession().getId());
		return message;
	}
}
