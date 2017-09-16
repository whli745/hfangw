package web.dao;

import util.ResultPage;
import web.pojo.HousingProject;

public interface IHousingProjectDao {
	ResultPage queryHousingProjectList(HousingProject q,int page,int pageRows) throws Exception;
	void saveOrUpdateHousingProject(HousingProject hp) throws Exception;
	HousingProject getHousingProjectByOid(String oid) throws Exception;
}
