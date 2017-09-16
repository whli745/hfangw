package web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
import web.dao.ISecondHandHouseDao;
import web.pojo.SecondHandHouse;

public class SecondHandHouseDaoImpl extends BaseDaoImpl<SecondHandHouse> implements ISecondHandHouseDao {

	@Override
	public ResultPage querySecondHandHouseList(SecondHandHouse q, int page,
			int pageRows) throws Exception {
		StringBuffer hql = new StringBuffer("from SecondHandHouse h where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(q != null){
			if(!Common.isNullOrSpace(q.getOid())){
				hql.append(" and h.oid != ?");
				params.add(q.getOid());
			}
			if(!Common.isNullOrSpace(q.getArea_id())){
				hql.append(" and h.sysArea.areaPath like ?");
				params.add("%" + q.getArea_id() + "%");
			}
			if(!Common.isNullOrSpace(q.getHouse_name())){
				hql.append(" and h.house_name like ?");
				params.add("%" + q.getHouse_name().trim() + "%");
			}
			if(!Common.isNullOrSpace(q.getBuilding_age_dict_id())){
				hql.append(" and h.building_age_dict_id = ?");
				params.add(q.getBuilding_age_dict_id());
			}
			if(!Common.isNullOrSpace(q.getFloor_dict_id())){
				hql.append(" and h.floor_dict_id = ?");
				params.add(q.getFloor_dict_id());
			}
			if(!Common.isNullOrSpace(q.getHous_type_dict_id())){
				hql.append(" and h.hous_type_dict_id = ?");
				params.add(q.getHous_type_dict_id());
			}
			if(!Common.isNullOrSpace(q.getAcreage_dict_id())){
				hql.append(" and h.acreage_dict_id = ?");
				params.add(q.getAcreage_dict_id());
			}
			if(!Common.isNullOrSpace(q.getBuilding_type_dict_id())){
				hql.append(" and h.building_type_dict_id = ?");
				params.add(q.getBuilding_type_dict_id());
			}
			if(!Common.isNullOrSpace(q.getSelling_price_dict_id())){
				hql.append(" and h.selling_price_dict_id = ?");
				params.add(q.getSelling_price_dict_id());
			}
		}
		hql.append(" and h.del_flag = ?");
		params.add(BaseParameter.NO);
		hql.append(" order by h.latest_edit_date desc");
		return super.getResultPage(hql.toString(), params, page, pageRows);
	}

	@Override
	public void saveOrUpdateSecondHandHouse(SecondHandHouse o) throws Exception {
		super.saveOrUpdate(o);
	}

	@Override
	public SecondHandHouse getSecondHandHouseByOid(String oid) throws Exception {
		return super.onGet(oid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countTTN() throws Exception {
		String sql = "select count(h.oid) as COUNT from T_SECOND_HAND_HOUSE h ";
		List<Map<String,Object>> l = super.executeQuerySql(sql,new Object[]{});
		String n = l.size() > 0 ? l.get(0).get("COUNT").toString() : "0";
		return Integer.parseInt(n);
	}


}
