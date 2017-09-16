package web.service;

import util.ResultPage;
import web.pojo.HfwContacts;
import web.pojo.HfwKftYy;

import java.util.List;

/**
 * Created by whli
 *
 * @version 2016/7/16 15:37
 */
public interface IRedpacketManageService {

    ResultPage queryHfwContactsList(HfwContacts q, int page, int pageRows) throws Exception;

    void saveOrUpdateHfwContacts(HfwContacts q,String h_flag) throws Exception;

    HfwContacts getHfwContactsByOid(String oid) throws  Exception;

    HfwContacts getHfwContanctsByPhoneAndPacket(List<String> params) throws  Exception;
}
