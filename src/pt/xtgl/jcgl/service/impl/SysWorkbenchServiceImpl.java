package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysWorkbenchDao;
import pt.xtgl.jcgl.pojo.SysWorkbench;
import pt.xtgl.jcgl.service.ISysWorkbenchService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

public class SysWorkbenchServiceImpl implements ISysWorkbenchService {

	private ISysWorkbenchDao sysWorkbenchDao;

	/**
	 * 删除工作台
	 */
	@Override
	public void deleteSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		sysWorkbenchDao.deleteSysWorkbench(sysWorkbench);

	}

	/**
	 * 查找工作台
	 */
	@Override
	public SysWorkbench querySysWorkbench(String workbenchId) throws Exception {
		SysWorkbench sysworkbench = sysWorkbenchDao
				.querySysWorkbench(workbenchId);
		return sysworkbench;
	}

	/**
	 * 增加工作台
	 */
	@Override
	public void saveSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		sysWorkbenchDao.saveSysWorkbench(sysWorkbench);
	}

	/**
	 * 修改工作台
	 */
	@Override
	public void updateSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		sysWorkbenchDao.updateSysWorkbench(sysWorkbench);
	}
	
	@Override
	public List queryWorkbenchList(String workbenchSystem,String modulePid) throws Exception {
		return sysWorkbenchDao.queryWorkbenchList(workbenchSystem,modulePid);
	}

	/**
	 * 新增或者修改工作台信息
	 */
	@Override
	public void editeSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		if (sysWorkbench != null
				&& Common.isNullOrSpace(sysWorkbench.getWorkbenchId())) {
			sysWorkbench.setWorkbenchId(null);
		}
		sysWorkbench.setWorkbenchDelFlag(BaseParameter.DELETE_NO);
		sysWorkbenchDao.editeSysWorkbench(sysWorkbench);
	}

	public ISysWorkbenchDao getSysWorkbenchDao() {
		return sysWorkbenchDao;
	}

	public void setSysWorkbenchDao(ISysWorkbenchDao sysWorkbenchDao) {
		this.sysWorkbenchDao = sysWorkbenchDao;
	}

	@Override
	public ResultPage querySysWorkbenchList(SysWorkbench sysWorkbench,
			String delFlag, int page, int pageRows) throws Exception {
		return sysWorkbenchDao.querySysWorkbenchList(sysWorkbench, delFlag,
				page, pageRows);
	}

	

}
