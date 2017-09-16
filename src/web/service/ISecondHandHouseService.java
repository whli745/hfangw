package web.service;

import util.ResultPage;
import web.pojo.SecondHandHouse;

public interface ISecondHandHouseService {
	
	ResultPage querySecondHandHouseList(SecondHandHouse q,int page,int pageRows) throws Exception;
	
	void saveOrUpdateSecondHandHouse(SecondHandHouse o) throws Exception;
	
	SecondHandHouse getSecondHandHouseByOid(String oid) throws Exception;
	
	void delSecondHandHouse(String oid) throws Exception;
	
	int countTTN() throws Exception;

}
