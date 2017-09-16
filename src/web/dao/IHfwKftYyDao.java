package web.dao;

import util.ResultPage;
import web.pojo.HfwKftYy;

public interface IHfwKftYyDao {
	ResultPage queryHfwKftYyList(HfwKftYy q,int type,int page,int pageRows) throws Exception;
	void saveOrUpdateHfwKftYy(HfwKftYy o) throws Exception;
	HfwKftYy getHfwKftYyByOid(String oid) throws Exception;
}
