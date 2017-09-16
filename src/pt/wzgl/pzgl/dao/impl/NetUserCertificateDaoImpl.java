package pt.wzgl.pzgl.dao.impl;

import java.util.List;

import pt.wzgl.pzgl.dao.INetUserCertificateDao;
import pt.wzgl.pzgl.pojo.NetUserCertificate;
import util.BaseParameter;
import util.Common;
import util.base.dao.BaseDaoImpl;

public class NetUserCertificateDaoImpl extends BaseDaoImpl<NetUserCertificate>
		implements INetUserCertificateDao {

	@Override
	public void saveOrUpdate(NetUserCertificate certificate) throws Exception {
		super.saveOrUpdate(certificate);// TODO saveOrUpdate
	}

	@Override
	public List<NetUserCertificate> getCertificationByType(String typeId)
			throws Exception {
		if( !Common.isNullOrSpace(typeId) ){
			String hql = " from NetUserCertificate a where a.certificationType = ? ";
			return super.queryByHql(hql, new Object[]{ typeId });
		}
		return null;
	}

	@Override
	public List getCertificationByUser(String netUserId)
			throws Exception {
		if( !Common.isNullOrSpace(netUserId) ){
			String hql = " from NetUserCertificate a,NetCertification b where a.userId = ? and a.certificateID = b.id";
			return super.queryByHql(hql, new Object[]{ netUserId });
		}
		return null;
	}

	@Override
	public NetUserCertificate getCertificationImage(String certificationId)
			throws Exception {
		if( !Common.isNullOrSpace(certificationId) ){
			String hql = " from NetUserCertificate a where a.id = ? ";
			return super.getObjByHql(hql, new Object[]{ certificationId });
		}
		return null;
	}

	@Override
	public List<NetUserCertificate> getUserCertificationByUser(String userId)
			throws Exception {
		if( !Common.isNullOrSpace(userId) ){
			String hql = " from NetUserCertificate a where a.userId = ? and a.certificationStatus = ? ";
			return super.queryByHql(hql, new Object[]{ userId,BaseParameter.NO });
		}
		return null;
	}

	@Override
	public void doAuditCertification(String ucId, String status, String content)
			throws Exception {
		String sql = "update NET_USER_CERTIFICATE set CERTIFICATION_STATUS = '" + status + "',AUDIT_CONTENT = '" + content + "'" +
				" where id = '" + ucId + "';";
		super.executeUpdateSql(sql);
	}

	@Override
	public NetUserCertificate getNetUserCertificationIsExsit(
			String certificationId, String userId) throws Exception {
		if( !Common.isNullOrSpace(certificationId) && !Common.isNullOrSpace(userId) ){
			String hql = " from NetUserCertificate a where a.certificateID = ? and a.userId = ?";
			return super.getFirstObject(hql, new Object[]{certificationId,userId});
		}
		return null;
	}
}
