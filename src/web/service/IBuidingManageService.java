package web.service;

import util.ResultPage;
import web.pojo.*;

public interface IBuidingManageService {

	ResultPage queryHousingProjectList(HousingProject q,int page,int pageRows) throws Exception;
	void saveOrUpdateHousingProject(HousingProject hp) throws Exception;
	void saveOrUpdateHousingProject(HousingProject hp,HfwKft hfwKft) throws Exception;
	HousingProject getHousingProjectByOid(String oid) throws Exception;
	
	void saveOrUpdateHfwKft(HfwKft hfwKft) throws Exception;
	
	/**
	 * @param q
	 * @param type 1-平台组织，2-自主预约
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	ResultPage queryHfwKftYyList(HfwKftYy q,int type,int page,int pageRows) throws Exception;
	
	void saveOrUpdateHfwKftYy(HfwKftYy o) throws Exception;
	
	ResultPage queryHfwRealtyConsultantList(HfwRealtyConsultant q,int page,int pageRows) throws Exception;
	
	void delHfwRealtyConsultant(String oid) throws Exception;
	
	HfwRealtyConsultant gethfwRealtyConsultantByOid(String oid) throws Exception;
	
	void saveOrUpdateHfwRealtyConsultant(HfwRealtyConsultant hfwRealtyConsultant) throws Exception;
	
	void saveOrUpdateHousingProjectPrice(HousingProjectPrice price) throws Exception;

	void  saveOrUpdateRedPacket(RedPacket packet) throws  Exception; //保存或更新红包

	void delAllRedPacket(String houseId) throws Exception; //根据户型id删除所有红包
	
	void delAllHousingProjectPrice(String housing_proj_id)throws Exception;
	
	HfwKftYy getHfwKftYyByOid(String oid) throws Exception;
}
