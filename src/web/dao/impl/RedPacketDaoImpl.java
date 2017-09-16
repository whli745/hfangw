package web.dao.impl;

import util.base.dao.BaseDaoImpl;
import web.dao.IRedPacketDao;
import web.pojo.RedPacket;

/**
 * Created by whli
 *
 * @version 2016/7/16 16:56
 */
 public class RedPacketDaoImpl extends BaseDaoImpl<RedPacket> implements IRedPacketDao {
    @Override
    public void saveOrUpdateRedPacket(RedPacket entity) throws Exception {
        super.saveOrUpdate(entity);
    }

    @Override
    public void delAllRedPacket(String hql,String houseId) throws Exception {
        super.batchEntityByHQL(hql, new Object[]{houseId});
    }
}
