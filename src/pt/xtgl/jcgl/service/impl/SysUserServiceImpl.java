package pt.xtgl.jcgl.service.impl;

import pt.xtgl.jcgl.dao.ISysUserDao;
import pt.xtgl.jcgl.dao.ISysUserRoleDao;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.pojo.SysUserRole;
import pt.xtgl.jcgl.service.ISysUserService;
import util.BaseParameter;
import util.Common;
import util.DateTools;
import util.ResultPage;
import util.base.service.BaseServiceImpl;
import dwr.vo.SysMyUser;

public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements
		ISysUserService {

	public SysUserServiceImpl(ISysUserDao sysUserDao,
			ISysUserRoleDao sysUserRoleDao) {
		super(sysUserDao);
		this.sysUserDao = sysUserDao;
		this.sysUserRoleDao = sysUserRoleDao;
	}

	private ISysUserDao sysUserDao;
	private ISysUserRoleDao sysUserRoleDao;

	@Override
	public SysUser findUserByCodeAndPass(String code) throws Exception {
		return sysUserDao.findUserByCodeAndPass(code);
	}

	public String checkLogin(String sysyzm, String yzm, SysUser user,
			String password) throws Exception {
		String message = "";// 为0时登录成功，为1时验证码不正确，为2时用户不存在，为3时用户被禁用，为4时用户密码不正确
		sysyzm = yzm.toLowerCase();
		// if (sysyzm != null && sysyzm.toLowerCase().equals(yzm)) {//去掉验证码验证
		// 读取用户是否存在
		if (user != null) {
			// 判断该用户是否被禁用
			if (BaseParameter.DELETE_YES.equals(user.getDelFlag())) {
				// 已删除
				return "5";
			} else {
				// 判断该用户是否被禁用
				if (BaseParameter.STATUS_ENABLE.equals(user.getUserStatus())) {
					// 判断用户密码是否正确
					if (user.getUserPassword().equals(Common.md5(password))) {
						message = "0";
					} else {
						message = "4";
					}
				} else {
					message = "3";
				}
			}
		} else {
			message = "2";
		}
		// } else {//去掉验证码验证
		// message = "1";
		// }
		return message;
	}

	public String checkLogin(SysUser user, String password) throws Exception {
		String message = "";// 为0时登录成功，为1时验证码不正确，为2时用户不存在，为3时用户被禁用，为4时用户密码不正确
		// 读取用户是否存在
		if (user != null) {
			// 判断该用户是否被禁用
			if (BaseParameter.DELETE_YES.equals(user.getDelFlag())) {
				// 已删除
				return "5";
			} else {
				// 判断该用户是否被禁用
				if (BaseParameter.STATUS_ENABLE.equals(user.getUserStatus())) {
					// 判断用户密码是否正确
					if (user.getUserPassword().equals(Common.md5(password))) {
						message = "0";
					} else {
						message = "4";
					}
				} else {
					message = "3";
				}
			}
		} else {
			message = "2";
		}
		return message;
	}

	@Override
	public void deleteSysUser(String userId) throws Exception {
		SysUser sysUser = sysUserDao.querySysUser(userId);
		if (null != sysUser) {
			sysUserDao.deleteSysUser(sysUser);
		}
	}

	@Override
	public SysUser querySysUser(String userId) throws Exception {

		SysUser sysUser = sysUserDao.querySysUser(userId);
		return sysUser;
	}

	@Override
	public void saveSysUser(SysUser sysUser) throws Exception {
		sysUserDao.saveSysUser(sysUser);
		// 更新序列化主键到系统用户表中
		sysUserDao.updateSysUser(sysUser);
	}

	@Override
	public void updateSysUser(SysUser sysUser) throws Exception {
		sysUserDao.updateSysUser(sysUser);
	}

	@Override
	public void updateMyUserInfo(SysMyUser sysUser) throws Exception {
		SysUser user = sysUserDao.querySysUser(sysUser.getUserId());
		user.setUserName(sysUser.getUserName());
		user.setUserPassword(sysUser.getUserPassword());
		user.setUserSex(sysUser.getUserSex());
		user.setUserBirthday(DateTools.strToDate(sysUser.getUserBirthday(),
				"yyyy-mm-dd"));
		user.setUserPost(sysUser.getUserPost());
		user.setUserPhone(sysUser.getUserPhone());
		user.setUserTel(sysUser.getUserTel());
		user.setUserEmail(sysUser.getUserEmail());
		sysUserDao.updateSysUser(user);
	}

	@Override
	public ResultPage querySysUserList(String areaId, String userName,
			String organId, String serviceType, int page, int pageRows)
			throws Exception {
		ResultPage sysUserList = sysUserDao.querySysUserList(areaId, userName,
				organId, serviceType, page, pageRows);
		return sysUserList;
	}

	@Override
	public void saveOrUpdateSysUser(SysUser sysUser, String websiteIds)
			throws Exception {
		if (!Common.isNullOrSpace(sysUser.getUserId())) {
			sysUserDao.updateSysUser(sysUser);
		} else {
			sysUserDao.saveSysUser(sysUser);
			// 更新序列化主键到系统用户表中
			sysUserDao.updateSysUser(sysUser);
		}
		if (!Common.isNullOrSpace(sysUser.getRoleId())) { // 判断用户是否已经选择角色
			// 删除用户以前分配的角色
			sysUserRoleDao.deleteRoleByUserId(sysUser.getUserId());
			String role[] = sysUser.getRoleId().split(",");
			for (String roleId : role) {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserId(sysUser.getUserId());
				userRole.setRoleId(roleId.trim());
				sysUserRoleDao.saveUserRole(userRole);
			}
		}

	}

}
