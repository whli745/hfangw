package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysHolidayDao;
import pt.xtgl.jcgl.pojo.SysHoliday;
import util.Common;
import util.DateTools;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysHolidayDaoImpl extends BaseDaoImpl<SysHoliday> implements
		ISysHolidayDao {

	@Override
	public void saveSysHoliday(SysHoliday holiday) throws Exception {
		super.save(holiday);
	}

	@Override
	public void deleteSysHoliday(String oid) throws Exception {
		String hql = "delete SysHoliday where oid=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(oid);
		super.batchEntityByHQL(hql, paramList);
	}

	@Override
	public ResultPage querySysHoliday(SysHoliday holiday,int page, int pageRows) throws Exception {
		String hql = "from SysHoliday where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();

		if(null != holiday){
			if(!Common.isNullOrSpace(holiday.getHolidayType())){
				hql +=" and holidayType=? ";
				paramList.add(holiday.getHolidayType().trim());
			}
			if(null != holiday.getHolidayDate()){
				hql +=" and holidayDate >= ? and holidayDate < ?";
				paramList.add(holiday.getHolidayDate());
				paramList.add(DateTools.addDay(holiday.getHolidayDate(), 1));
			}
		}
		hql +=" order by holidayDate desc";
		return super.getResultPage(hql, paramList, page, pageRows);
	}

	@Override
	public SysHoliday querySysHoliday(String oid) throws Exception {
		return super.onGet(oid);
	}

}
