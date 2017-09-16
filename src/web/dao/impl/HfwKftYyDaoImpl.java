package web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
import web.dao.IHfwKftYyDao;
import web.pojo.HfwKftYy;

public class HfwKftYyDaoImpl extends BaseDaoImpl<HfwKftYy> implements IHfwKftYyDao {

	@Override
	public ResultPage queryHfwKftYyList(HfwKftYy q,int type,int page,int pageRows) throws Exception {
		StringBuffer hql = new StringBuffer("from HfwKftYy y where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(q != null){
			if(!Common.isNullOrSpace(q.getLxxm())){
				hql.append(" and y.lxxm like ?");
				params.add("%" + q.getLxxm() + "%");
			}
			if(!Common.isNullOrSpace(q.getArea_id())){
				hql.append(" and y.sysArea.areaPath like ?");
				params.add("%" + q.getArea_id() + "%");
			}
			if(!Common.isNullOrSpace(q.getKft_id())){
				hql.append(" and y.kft_id = ?");
				params.add(q.getKft_id());
			}
			if(!Common.isNullOrSpace(q.getHandle_flag())){
				if(BaseParameter.DATA_FILTER_2.equals(q.getHandle_flag())){
					hql.append(" and y.handle_flag = ?");
					params.add(BaseParameter.NO);
				}else if(BaseParameter.DATA_FILTER_3.equals(q.getHandle_flag())){
					hql.append(" and y.handle_flag = ?");
					params.add(BaseParameter.YES);
				}
			}
			if(q.getYy_type() != null){
				if(q.getYy_type() == 1){
					hql.append(" and y.kft_id is not null");
				}else{
					hql.append(" and y.kft_id is null");
				}
			}else{
				hql.append(" and y.kft_id is null");
			}
			if(type == 1){//平台组织
				if(!Common.isNullOrSpace(q.getProj_name())){
					hql.append(" and y.hfwKft.housingProject.proj_name like ?");
					params.add("%" + q.getProj_name() + "%");
				}
				if(q.getQ_s_date() != null){
					hql.append(" and y.hfwKft.bm_end_time >= ?");
					params.add(q.getQ_s_date());
				}
				if(q.getQ_e_date() != null){
					hql.append(" and y.hfwKft.bm_end_time <= ?");
					params.add(q.getQ_e_date());
				}
			}else{
				if(!Common.isNullOrSpace(q.getProj_name())){
					hql.append(" and y.proj_name like ?");
					params.add("%" + q.getProj_name() + "%");
				}
				if(q.getQ_s_date() != null){
					hql.append(" and y.yysj >= ?");
					params.add(q.getQ_s_date());
				}
				if(q.getQ_e_date() != null){
					hql.append(" and y.yysj <= ?");
					params.add(q.getQ_e_date());
				}
			}
			
		}
		return super.getResultPage(hql.toString(), params, page, pageRows);
	}

	@Override
	public void saveOrUpdateHfwKftYy(HfwKftYy o) throws Exception {
		super.saveOrUpdate(o);
	}

	@Override
	public HfwKftYy getHfwKftYyByOid(String oid) throws Exception {
		return super.onGet(oid);
	}
	
}
