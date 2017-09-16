package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysModuleDao;
import pt.xtgl.jcgl.dao.ISysRoleDao;
import pt.xtgl.jcgl.dao.ISysRoleModuleDao;
import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.pojo.SysRoleModule;
import pt.xtgl.jcgl.service.ISysRoleService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.service.BaseServiceImpl;

public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements
		ISysRoleService {

	public SysRoleServiceImpl(ISysRoleDao sysRoleDao,
			ISysRoleModuleDao sysRoleModuleDao, ISysModuleDao sysModuleDao) {
		super(sysRoleDao);
		this.sysRoleDao = sysRoleDao;
		this.sysRoleModuleDao = sysRoleModuleDao;
		this.sysModuleDao = sysModuleDao;
	}

	private ISysRoleDao sysRoleDao;
	private ISysRoleModuleDao sysRoleModuleDao;
	private ISysModuleDao sysModuleDao;

	@Override
	public ResultPage querySysRoleList(String roleName, String optId,
			String areaId, int page, int pageRows) throws Exception {
		return sysRoleDao.querySysRoleList(roleName, optId, areaId, page,
				pageRows);
	}

	@Override
	public List<SysRole> querySysRoleList(String areaId, String serviceType)
			throws Exception {
		return sysRoleDao.querySysRoleList(areaId, serviceType);
	}

	@Override
	public List<SysRole> querySysRoleByIdsList(String roleIds) throws Exception {
		return sysRoleDao.querySysRoleByIdsList(roleIds);
	}

	@Override
	public List<SysRole> querySysRoleList(String areaId) throws Exception {
		return sysRoleDao.querySysRoleList(areaId);
	}

	@Override
	public void saveOrUpdateSysRole(SysRole sysRole) throws Exception {
		sysRoleDao.saveOrUpdateSysRole(sysRole);
	}

	@Override
	public void saveOrUpdateSysRole(SysRole sysRole, String moduleIds)
			throws Exception {
		if (Common.isNullOrSpace(sysRole.getRoleId())) {
			sysRole.setRoleId(null);
		}
		sysRole.setDelFlag(BaseParameter.DELETE_NO);
		// 保存
		sysRoleDao.saveOrUpdateSysRole(sysRole);
		// 删除
		List rmList = sysRoleModuleDao.querySysModuleRoleList(sysRole
				.getRoleId());
		for (int i = 0; i < rmList.size(); i++) {
			SysRoleModule srm = (SysRoleModule) rmList.get(i);
			sysRoleModuleDao.delSysRoleModule(srm.getOid());
		}
		// 保存
		SysRoleModule rm = null;
		if (moduleIds != null && !moduleIds.equals("")) {
			String[] mids = moduleIds.split(";");
			for (int i = 0; i < mids.length; i++) {
				if (!mids[i].equals("-1")) {// -1 对应全选
					rm = new SysRoleModule();
					rm.setModuleId(mids[i]);
					rm.setRoleId(sysRole.getRoleId());
					sysRoleModuleDao.saveSysRoleModule(rm);
				}
			}
		}
	}

	@Override
	public String createRoleModuleJSONString(String roleId, String disabled)
			throws Exception {
		List modules = sysModuleDao.queryAllSysModuleList();
		List roleModules = sysRoleModuleDao.querySysModuleRoleList(roleId);
		StringBuffer sb = new StringBuffer();
		int len = modules.size();
		int lenb = roleModules.size();
		for (int i = 0; i < len; i++) {
			SysModule sm = (SysModule) modules.get(i);
			String trid = (String) sm.getModuleId();
			boolean flag = false;
			for (int j = 0; j < lenb; j++) {
				if (((SysRoleModule) roleModules.get(j)).getModuleId().equals(
						trid)) {
					flag = true;
					break;
				}
			}
			sb.append("{id:\"" + sm.getModuleId() + "\", pId:\""
					+ sm.getParentModuleId().trim() + "\", name:\""
					+ sm.getModuleName() + "\", checked:"
					+ ((flag) ? "true" : "false") + ", chkDisabled:" + disabled
					+ " ,open:" + flag + ",mdisp:\"" + sm.getModuleDesc()
					+ "\"}");
			if (i != len - 1)
				sb.append(",");
		}
		if (!"true".equals(disabled)) {// true--对应查看
			sb.append(",{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',pId:'',name:'全选',checked:'false',open:'true'}");
		}
		return sb.toString();
	}

	@Override
	public String createModuleJSONString() throws Exception {
		List modules = sysModuleDao.queryAllSysModuleList();
		StringBuffer sb = new StringBuffer();
		int len = modules.size();
		for (int i = 0; i < len; i++) {
			SysModule m = (SysModule) modules.get(i);
			sb.append("{id:\"" + m.getModuleId().trim() + "\", pId:\""
					+ m.getParentModuleId().trim() + "\", name:\""
					+ m.getModuleName() + "\", open:false}");
			if (i != len - 1)
				sb.append(",");
		}
		sb.append(",{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
				+ "',pId:'',name:'全选',checked:'false',open:'true'}");
		return sb.toString();
	}

	@Override
	public SysRole getSysRoleByRoleId(String roleId) throws Exception {
		return sysRoleDao.getSysRoleById(roleId);
	}

	@Override
	public void delSysRole(String roleId) throws Exception {
		SysRole sysRole = sysRoleDao.getSysRoleById(roleId);
		sysRole.setDelFlag(util.BaseParameter.DELETE_YES);
		sysRoleDao.saveOrUpdateSysRole(sysRole);
	}

	@Override
	public List<SysRole> querySysRoleAll() throws Exception {
		return sysRoleDao.querySysRoleAll();
	}

	/**
	 * 根据区划id取得本级区划的角色配置
	 * 
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> querySysRoleAllByAreaId(String areaId)
			throws Exception {
		return sysRoleDao.querySysRoleAllByAreaId(areaId);
	}

}
