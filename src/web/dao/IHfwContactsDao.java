package web.dao;

import util.ResultPage;
import web.pojo.HfwContacts;
import web.pojo.HousingProject;

import java.util.List;

/**
 * Created by whli
 *
 * @version 2016/7/16 16:40
 */
public interface IHfwContactsDao {
    //保存和更新领取人信息
    void saveOrUpdateHfwContancts(HfwContacts entity) throws  Exception;
    //分页查询领取人信息
    ResultPage queryHfwContanctsList(HfwContacts q, int page, int pageRows) throws Exception;
    //根据oid查询领取人信息
    HfwContacts getHfwContactsByOid(String oid) throws  Exception;
    //根据手机号和红包号查领取人信息
    HfwContacts getHfwContanctsByPhoneAndPacket(List<String> params) throws Exception;
    //根据红包删除领取人信息
    /* void delHfwContactsByPacketId(String packetId) throws Exception;*/
}
