package pt.wzgl.pzgl.dao.impl;

import java.util.List;

import pt.wzgl.pzgl.dao.INetCertificationDao;
import pt.wzgl.pzgl.pojo.NetCertification;
import util.Common;
import util.base.dao.BaseDaoImpl;

public class NetCertificationDaoImpl extends BaseDaoImpl<NetCertification>
		implements INetCertificationDao {

	@Override
	public List<NetCertification> getAllNetCertificationByType(String certicateType)
			throws Exception {
		if( !Common.isNullOrSpace(certicateType) ){
			String hql = " from NetCertification a where a.certificationType = ? ";
			return super.queryByHql(hql, new Object[]{ certicateType });
		}
		return null;
	}

	@Override
	public NetCertification getCertification(String certificationId)
			throws Exception {
		if( !Common.isNullOrSpace(certificationId) ){
			String hql = " from NetCertification a where a.id = ? ";
			return super.getObjByHql(hql, new Object[]{ certificationId });
		}
		return null;
	}
}
