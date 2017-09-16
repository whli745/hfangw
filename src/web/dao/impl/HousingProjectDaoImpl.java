package web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
import web.dao.IHousingProjectDao;
import web.pojo.HousingProject;

public class HousingProjectDaoImpl extends BaseDaoImpl<HousingProject> implements IHousingProjectDao{

	@Override
	public ResultPage queryHousingProjectList(HousingProject q, int page,
			int pageRows) throws Exception {
		StringBuffer hql = new StringBuffer("from HousingProject hq where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(q != null){
			if(!Common.isNullOrSpace(q.getArea_id())){
				hql.append(" and hq.sysArea.areaPath like ?");
				params.add("%" + q.getArea_id() + "%");
			}
			if(!Common.isNullOrSpace(q.getProj_type())){
				hql.append(" and hq.proj_type = ?");
				params.add(q.getProj_type());
			}
			if(!Common.isNullOrSpace(q.getProj_name())){
				hql.append(" and hq.proj_name like ?");
				params.add("%" + q.getProj_name() + "%");
			}
			if(!Common.isNullOrSpace(q.getSearch_unit_price())){
				hql.append(" and hq.search_unit_price like ?");
				params.add("%" + q.getSearch_unit_price() + "%");
			}
			if(!Common.isNullOrSpace(q.getHous_type())){
				hql.append(" and hq.hous_type like ?");
				params.add("%" + q.getHous_type() + "%");
			}
			if(!Common.isNullOrSpace(q.getBuilding_type())){
				hql.append(" and hq.building_type like ?");
				params.add("%" + q.getBuilding_type() + "%");
			}
			hql.append(" and hq.del_flag = ? order by hq.attention desc");
			params.add(BaseParameter.NO);
		}
		return super.getResultPage(hql.toString(), params, page, pageRows);
	}

	@Override
	public void saveOrUpdateHousingProject(HousingProject hp) throws Exception {
		super.saveOrUpdate(hp);
	}

	@Override
	public HousingProject getHousingProjectByOid(String oid) throws Exception {
		return super.onGet(oid);
	}
	
}
