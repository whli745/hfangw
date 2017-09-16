package pt.wzgl.pzgl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.wzgl.pzgl.dao.INetUserModuleDao;
import pt.wzgl.pzgl.pojo.NetUserModule;
import util.BaseParameter;
import util.base.dao.BaseDaoImpl;

public class NetUserModuleDaoImpl extends BaseDaoImpl<NetUserModule> implements
		INetUserModuleDao {
	List<Object> objs = null;

	@Override
	public List<NetUserModule> queryAllNetUserModuleList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from NetUserModule m where m.moduleStatus = ? "
				+ "and m.delFlag= ? order by m.moduleOrder";
		objs.add(util.BaseParameter.STATUS_ENABLE);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<NetUserModule> queryFirstNetUserModuleList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from NetUserModule m where m.moduleLevel = ? and m.delFlag= ? order by m.moduleOrder";
		objs.add(1);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);

	}

	@Override
	public NetUserModule getNetUserModuleByOid(String oid) throws Exception {
		return (NetUserModule) super.onGet(oid);
	}

	@Override
	public void saveOrUpdateNetUserModule(NetUserModule netUserModule) throws Exception {
		netUserModule.setUpdateTime(new Date());
		super.saveOrUpdate(netUserModule);
	}

	@Override
	public List<NetUserModule> queryNetUserModuleListByPid(String modulePid)
			throws Exception {
		String hql = "from NetUserModule m where m.parentId= ? and m.delFlag = ? order by m.moduleOrder asc ";
		return super.queryByHql(hql,
				new Object[] { modulePid,util.BaseParameter.DELETE_NO });
	}

	@Override
	public List<NetUserModule> queryAllModuleByTypeList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from NetUserModule m where m.moduleStatus = ? "
				+ "and m.delFlag= ? and m.moduleId in (select sm.parentId from NetUserModule sm where sm.moduleType='2')";
		objs.add(util.BaseParameter.STATUS_ENABLE);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<NetUserModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception {
		String hql = "from NetUserModule s where s.delFlag=? and s.parentId=? order by s.moduleOrder";
		return super.queryByHql(hql, new Object[] { BaseParameter.DELETE_NO,
				moduleUrl });
	}

}
