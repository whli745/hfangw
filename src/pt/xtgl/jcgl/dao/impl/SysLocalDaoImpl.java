package pt.xtgl.jcgl.dao.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysLocalDao;
import pt.xtgl.jcgl.pojo.SysLocal;
import util.base.dao.BaseDaoImpl;

/**
 * 地区 dao类
 * 
 * @author : dusd
 * @date : 2015-11-13
 */
public class SysLocalDaoImpl extends BaseDaoImpl<SysLocal> implements ISysLocalDao {

	@Override
	public List<SysLocal> getSysLocalListToJsonToCity(String topId) throws Exception {
		String hql = "FROM pt.xtgl.jcgl.pojo.SysLocal s where s.topId = " + topId;
		return super.queryByHql(hql);
	}

	@Override
	public List<SysLocal> getSysLocalListToProvince() throws Exception {
		String hql = "FROM pt.xtgl.jcgl.pojo.SysLocal s where s.localLevel = 1";
		return super.queryByHql(hql);
	}

}
