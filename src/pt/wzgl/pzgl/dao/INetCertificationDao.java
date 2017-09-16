package pt.wzgl.pzgl.dao;

import java.util.List;

import pt.wzgl.pzgl.pojo.NetCertification;

/**
 * @Description 证件数据持久化层
 *
 * @author Liangmh
 * @createDate 2014-11-21
 */
public interface INetCertificationDao {
	
	/**
	 * 根据类型查询所有证件
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-21
	 * @author Liangmh
	 */
	List<NetCertification> getAllNetCertificationByType(String certicateType) throws Exception;
	
	/**
	 * 根据ID查询证件
	 * @param certificationId
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-28
	 * @author Liangmh
	 */
	NetCertification getCertification(String certificationId) throws Exception;
}
