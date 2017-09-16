package web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
import web.dao.IHfwRealtyConsultantDao;
import web.pojo.HfwRealtyConsultant;

public class HfwRealtyConsultantDaoImpl extends BaseDaoImpl<HfwRealtyConsultant> implements IHfwRealtyConsultantDao {

	@Override
	public ResultPage queryHfwRealtyConsultantList(HfwRealtyConsultant q,
			int page, int pageRows) throws Exception {
		StringBuffer hql = new StringBuffer("from HfwRealtyConsultant c where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(q != null){
			if(!Common.isNullOrSpace(q.getCons_name())){
				hql.append(" and q.cons_name like ?");
				params.add(q.getCons_name());
			}
		}
		hql.append(" order by c.sort");
		return super.getResultPage(hql.toString(), params, page, pageRows);
	}

	@Override
	public void delHfwRealtyConsultant(String oid) throws Exception {
		super.delete(oid);
	}

	@Override
	public HfwRealtyConsultant gethfwRealtyConsultantByOid(String oid)
			throws Exception {
		return super.onGet(oid);
	}

	@Override
	public void saveOrUpdateHfwRealtyConsultant(
			HfwRealtyConsultant hfwRealtyConsultant) throws Exception {
		super.saveOrUpdate(hfwRealtyConsultant);
	}
	
	
}
