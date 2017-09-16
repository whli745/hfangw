package web.dao.impl;

import util.base.dao.BaseDaoImpl;
import web.dao.IHousingProjectPriceDao;
import web.pojo.HousingProjectPrice;

public class HousingProjectPriceDaoImpl extends BaseDaoImpl<HousingProjectPrice> implements IHousingProjectPriceDao {

	@Override
	public void saveOrUpdateHousingProjectPrice(HousingProjectPrice price)
			throws Exception {
		super.saveOrUpdate(price);
	}

	@Override
	public void delAllHousingProjectPrice(String housing_proj_id) throws Exception {
		String hql = "delete from HousingProjectPrice p where p.housing_proj_id = ?";
		super.batchEntityByHQL(hql, new Object[]{housing_proj_id});
	}
	
	
	
}
