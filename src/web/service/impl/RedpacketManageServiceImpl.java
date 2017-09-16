package web.service.impl;

import util.Common;
import util.ResultPage;
import web.dao.IHfwContactsDao;
import web.dao.impl.HfwContactsDaoImpl;
import web.pojo.HfwContacts;
import web.service.IRedpacketManageService;

import java.util.Date;
import java.util.List;

/**
 * 红色领取service
 * Created by whli
 *
 * @version 2016/7/18 8:33
 */
public class RedpacketManageServiceImpl implements IRedpacketManageService {
//    private IHfwContactsDao hfwContactsDao;
    private IHfwContactsDao contactsDao;

    @Override
    public ResultPage queryHfwContactsList(HfwContacts q, int page, int pageRows) throws Exception {
        return contactsDao.queryHfwContanctsList(q,page,pageRows);
    }

    @Override
    public void saveOrUpdateHfwContacts(HfwContacts q,String h_flag) throws Exception {
        if(q != null && Common.isNullOrSpace(q.getOid())){  //增加领取人
            q.setOid(null);
            q.setStatus(1);
            q.setCreateTime(new Date());
        }else if(q != null && !Common.isNullOrSpace(q.getOid()) && h_flag != null && !"".equals(h_flag)){  //更改领取人是否领取红包
            q = contactsDao.getHfwContactsByOid(q.getOid());
            q.setStatus(Integer.valueOf(h_flag));
        }
        contactsDao.saveOrUpdateHfwContancts(q);
    }

    @Override
    public HfwContacts getHfwContactsByOid(String oid) throws Exception {
        return contactsDao.getHfwContactsByOid(oid);
    }

    @Override
    public HfwContacts getHfwContanctsByPhoneAndPacket(List<String> params) throws Exception {
        return contactsDao.getHfwContanctsByPhoneAndPacket(params);
    }

    public IHfwContactsDao getContactsDao() {
        return contactsDao;
    }

    public void setContactsDao(IHfwContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }
}
