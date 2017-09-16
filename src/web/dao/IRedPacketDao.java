package web.dao;

import web.pojo.RedPacket;

/**
 * Created by whli
 *
 * @version 2016/7/16 16:39
 */
public interface IRedPacketDao {
    void saveOrUpdateRedPacket(RedPacket entity) throws  Exception;  //保存或更新红包

    void delAllRedPacket(String hql,String houseId)throws Exception; //删除所有红包
}
