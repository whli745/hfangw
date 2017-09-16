package pt.wzgl.pzgl.dao;

import java.util.List;

import pt.wzgl.pzgl.pojo.NetUserCertificate;

/**
 * @Description 用户证件数据持久层
 *
 * @author Liangmh
 * @createDate 2014-11-20
 */
public interface INetUserCertificateDao {
	
	/**
	 * 新增或修改
	 * @param certificate：证件对象
	 * @createDate 2014-11-20
	 * @author Liangmh
	 */
	void saveOrUpdate(NetUserCertificate certificate) throws Exception;
	
	/**
	 * 根据证件类型查询证件
	 * @param typeId：类型
	 * @return
	 * @createDate 2014-11-20
	 * @author Liangmh
	 */
	List<NetUserCertificate> getCertificationByType(String typeId) throws Exception;
	
	/**
	 * 查询用户的上传的证件
	 * @param netUserId：用户ID
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-20
	 * @author Liangmh
	 */
	List getCertificationByUser(String netUserId) throws Exception;
	
	/**
	 * 查询证件照片，提供预览
	 * @param certificationId：证件ID
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-20
	 * @author Liangmh
	 */
	NetUserCertificate getCertificationImage(String certificationId) throws Exception;
	
	/**
	 * 根据用户查询用户的
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-22
	 * @author Liangmh
	 */
	List<NetUserCertificate> getUserCertificationByUser(String userId) throws Exception;
	
	/**
	 * 执行用户证件审核 
	 * @param ucId
	 * @param status
	 * @param content
	 * @throws Exception
	 * @createDate 2014-11-22
	 * @author Liangmh
	 */
	void doAuditCertification(String ucId,String status,String content) throws Exception;
	
	/**
	 * 检测证件是否存在，用户不能重复添加证件
	 * @param certificationId：证件编号
	 * @param userId：用户编号
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-25
	 * @author Liangmh
	 */
	NetUserCertificate getNetUserCertificationIsExsit(String certificationId,String userId) throws Exception;
	
	/**
	 * 删除(物理删除)
	 * @param cate
	 * @throws Exception
	 * @createDate 2014-11-28
	 * @author Liangmh
	 */
	void delete(NetUserCertificate cate) throws Exception;
}
