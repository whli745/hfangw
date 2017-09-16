package pt.xtgl.jcgl.service.impl;

import pt.xtgl.jcgl.dao.ISysHolidayDao;
import pt.xtgl.jcgl.pojo.SysHoliday;
import pt.xtgl.jcgl.service.ISysHolidayService;
import util.ResultPage;

public class SysHolidayServiceImpl implements ISysHolidayService {
	private ISysHolidayDao sysHolidayDao;

	@Override
	public void saveSysHoliday(SysHoliday holiday) throws Exception {
		sysHolidayDao.saveSysHoliday(holiday);
	}

	@Override
	public void deleteSysHoliday(String oid) throws Exception {
		sysHolidayDao.deleteSysHoliday(oid);
	}

	@Override
	public ResultPage querySysHoliday(SysHoliday holiday,int page, int pageRows) throws Exception {
		return sysHolidayDao.querySysHoliday(holiday,page, pageRows);
	}

	public ISysHolidayDao getSysHolidayDao() {
		return sysHolidayDao;
	}

	public void setSysHolidayDao(ISysHolidayDao sysHolidayDao) {
		this.sysHolidayDao = sysHolidayDao;
	}

	@Override
	public SysHoliday querySysHoliday(String oid) throws Exception {
		return sysHolidayDao.querySysHoliday(oid);
	}

}
