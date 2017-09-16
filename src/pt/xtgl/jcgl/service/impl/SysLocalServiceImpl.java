package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysLocalDao;
import pt.xtgl.jcgl.pojo.SysLocal;
import pt.xtgl.jcgl.service.ISysLocalService;
import util.base.service.BaseServiceImpl;

/**
 * 地区 service类
 * 
 * @author : dusd
 * @date : 2015-11-13
 */
public class SysLocalServiceImpl extends BaseServiceImpl<SysLocal> implements ISysLocalService {

	/**
	 * 地区 DAO
	 */
	private ISysLocalDao sysLocalDao;

	public ISysLocalDao getSysLocalDao() {
		return sysLocalDao;
	}

	public void setSysLocalDao(ISysLocalDao sysLocalDao) {
		this.sysLocalDao = sysLocalDao;
	}

	@Override
	public String getSysLocalListToJsonToCity(String topId) throws Exception {
		List<SysLocal> sysLocals = sysLocalDao.getSysLocalListToJsonToCity(topId);
		String sysLocalJson;
		StringBuffer tmp = new StringBuffer();
		if (sysLocals == null) {
			sysLocalJson = "[]";
		} else {
			tmp.append("[");
			for (SysLocal sysLocal : sysLocals) {
				tmp.append("{id:'")
						.append(sysLocal.getLocalId())
						.append("', name:'")
						.append(sysLocal.getLocalName())
						.append("', pId:'")
						.append("', isParent: " + false+ "},");
			}
			sysLocalJson = tmp.toString();
			if (sysLocals.size() > 0)
				sysLocalJson = sysLocalJson
						.substring(0, sysLocalJson.length() - 1);
			sysLocalJson += "]";
		}
		return sysLocalJson;
	}

	@Override
	public String getSysLocalListToProvince() throws Exception {
		List<SysLocal> sysLocals = sysLocalDao.getSysLocalListToProvince();
		String sysLocalJson;
		StringBuffer tmp = new StringBuffer();
		if (sysLocals == null) {
			sysLocalJson = "[]";
		} else {
			tmp.append("[");
			for (SysLocal sysLocal : sysLocals) {
				tmp.append("{id:'")
						.append(sysLocal.getLocalId())
						.append("', name:'")
						.append(sysLocal.getLocalName())
						.append("', pId:'")
						.append("', isParent: " + true+ "},");
			}
			sysLocalJson = tmp.toString();
			if (sysLocals.size() > 0)
				sysLocalJson = sysLocalJson
						.substring(0, sysLocalJson.length() - 1);
			sysLocalJson += "]";
		}
		return sysLocalJson;
	}
}
