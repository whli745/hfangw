package web.dao;

import web.pojo.HousingProjectPrice;

public interface IHousingProjectPriceDao {
	void saveOrUpdateHousingProjectPrice(HousingProjectPrice price) throws Exception;
	
	void delAllHousingProjectPrice(String housing_proj_id)throws Exception;
}
