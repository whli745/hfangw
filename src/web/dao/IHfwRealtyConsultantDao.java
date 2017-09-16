package web.dao;

import util.ResultPage;
import web.pojo.HfwRealtyConsultant;

public interface IHfwRealtyConsultantDao {
	ResultPage queryHfwRealtyConsultantList(HfwRealtyConsultant q,int page,int pageRows) throws Exception;
	
	void delHfwRealtyConsultant(String oid) throws Exception;
	
	HfwRealtyConsultant gethfwRealtyConsultantByOid(String oid) throws Exception;
	
	void saveOrUpdateHfwRealtyConsultant(HfwRealtyConsultant hfwRealtyConsultant) throws Exception;
}
