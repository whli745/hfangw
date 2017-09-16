package web.dao;

import util.ResultPage;
import web.pojo.SecondHandHouse;

public interface ISecondHandHouseDao {
	
	ResultPage querySecondHandHouseList(SecondHandHouse q,int page,int pageRows) throws Exception;
	
	void saveOrUpdateSecondHandHouse(SecondHandHouse o) throws Exception;
	
	SecondHandHouse getSecondHandHouseByOid(String oid) throws Exception;
	
	int countTTN() throws Exception;
	
}
