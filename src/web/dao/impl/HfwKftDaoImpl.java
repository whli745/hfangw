package web.dao.impl;

import util.base.dao.BaseDaoImpl;
import web.dao.IHfwKftDao;
import web.pojo.HfwKft;

public class HfwKftDaoImpl extends BaseDaoImpl<HfwKft> implements IHfwKftDao {

	@Override
	public void saveOrUpdateHfwKft(HfwKft hfwKft) throws Exception {
		super.saveOrUpdate(hfwKft);
	}

}
