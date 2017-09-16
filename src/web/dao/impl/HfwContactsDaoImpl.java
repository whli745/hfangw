package web.dao.impl;

import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
import web.dao.IHfwContactsDao;
import web.pojo.HfwContacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whli
 *
 * @version 2016/7/16 17:00
 */
public class HfwContactsDaoImpl extends BaseDaoImpl<HfwContacts> implements IHfwContactsDao {
    //保存或更新红包领取人信息
    @Override
    public void saveOrUpdateHfwContancts(HfwContacts entity) throws Exception {
        super.saveOrUpdate(entity);
    }

    //分布查询所有红包领取人信息
    @Override
    public ResultPage queryHfwContanctsList(HfwContacts q, int page, int pageRows) throws Exception {
        StringBuffer hql = new StringBuffer("from HfwContacts hq where 1=1");
        List<Object> params = new ArrayList<Object>();
        if(q != null){
            if(!Common.isNullOrSpace(q.getLinkMan())){
                hql.append(" and hq.linkMan like ?");
                params.add("%" + q.getLinkMan() + "%");
            }
            if(!Common.isNullOrSpace(q.getLinkPhone())){
                hql.append(" and hq.linkPhone like ?");
                params.add("%" + q.getLinkPhone() + "%");
            }
            if(q.getStatus() != null && q.getStatus()>0){
                hql.append(" and hq.status = ?");
                params.add( q.getStatus());
            }
        }
        return super.getResultPage(hql.toString(), params, page, pageRows);
    }

    @Override
    public HfwContacts getHfwContactsByOid(String oid) throws Exception {
        return super.onGet(oid);
    }

    @Override
    public HfwContacts getHfwContanctsByPhoneAndPacket(List<String> params) throws Exception {
        String hql = "from HfwContacts where linkPhone = ? and packetId = ?";
        return super.getObjByHql(hql,params);
    }

	/*@Override
	public void delHfwContactsByPacketId(String packetId) throws Exception {
		String hql = "delete from HfwContacts where packetId = ?";
		super.batchEntityByHQL(hql,new Object[]{packetId});
	}*/
}
