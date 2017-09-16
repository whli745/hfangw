package web.service.impl;

import java.util.Date;

import util.BaseParameter;
import util.Common;
import util.ResultPage;
import web.dao.ISecondHandHouseDao;
import web.pojo.SecondHandHouse;
import web.service.ISecondHandHouseService;

public class SecondHandHouseServiceImpl implements ISecondHandHouseService {
	
	private ISecondHandHouseDao secondHandHouseDao;
	
	@Override
	public ResultPage querySecondHandHouseList(SecondHandHouse q, int page,
			int pageRows) throws Exception {
		return secondHandHouseDao.querySecondHandHouseList(q, page, pageRows);
	}

	@Override
	public void saveOrUpdateSecondHandHouse(SecondHandHouse o) throws Exception {
		if(Common.isNullOrSpace(o.getOid())){
			o.setOid(null);
			o.setDel_flag(BaseParameter.NO);
			o.setRefe_month_for(Common.getInventoryNumber());
		}
		o.setLatest_edit_date(new Date());
		secondHandHouseDao.saveOrUpdateSecondHandHouse(o);
	}

	@Override
	public SecondHandHouse getSecondHandHouseByOid(String oid) throws Exception {
		return secondHandHouseDao.getSecondHandHouseByOid(oid);
	}

	@Override
	public void delSecondHandHouse(String oid) throws Exception {
		SecondHandHouse s = secondHandHouseDao.getSecondHandHouseByOid(oid);
		s.setDel_flag(BaseParameter.YES);
		secondHandHouseDao.saveOrUpdateSecondHandHouse(s);
	}
	
	@Override
	public int countTTN() throws Exception {
		return secondHandHouseDao.countTTN();
	}

	public ISecondHandHouseDao getSecondHandHouseDao() {
		return secondHandHouseDao;
	}

	public void setSecondHandHouseDao(ISecondHandHouseDao secondHandHouseDao) {
		this.secondHandHouseDao = secondHandHouseDao;
	}

}
