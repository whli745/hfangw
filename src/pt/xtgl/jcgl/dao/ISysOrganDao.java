package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysOrgan;
import util.ResultPage;
import util.base.dao.IBaseDao;

/**
 * 机构持久化
 * 
 * @author meidj
 * @date 2013-05-22
 * 
 */
public interface ISysOrganDao extends IBaseDao<SysOrgan> {

	/**
	 * 保存机构信息
	 * 
	 * @param sysOrgan
	 *            机构实体对象
	 * @throws Exception
	 */
	public void saveSysOrgan(SysOrgan sysOrgan) throws Exception;

	/**
	 * 修改更新机构信息
	 * 
	 * @param sysOrgan
	 *            机构实体对象
	 * @throws Exception
	 */
	public void updateSysOrgan(SysOrgan sysOrgan) throws Exception;

	/**
	 * 根据机构主键查询机构对象
	 * 
	 * @param organId
	 *            机构主键
	 * @return 机构实体对象
	 * @throws Exception
	 */
	public SysOrgan getSysOrganInfo(String organId) throws Exception;

	/**
	 * 查询机构列表（分页查询）
	 * 
	 * @param organName
	 *            机构名称
	 * @param areaId
	 *            所属区划
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页显示条数
	 * @return ResultPage
	 * @throws Exception
	 */
	public ResultPage findSysOrganList(String organName, String areaId,
			int page, int pageRows) throws Exception;

	/**
	 * 查询机构列表，若机构主键存在，则记录不包含本机构（所有记录）
	 * 
	 * @param areaId
	 *            所属区划
	 * @param organId
	 *            机构ID
	 * @return List<SysOrgan> 机构列表
	 * @throws Exception
	 */
	public List<SysOrgan> findSysOrganList(String areaId, String organId)
			throws Exception;
	
	/**
	 * 
	* @Title: queryAllSysOrganList 
	* @Description:查询全部的机构List集合
	* @author:	chengcheng 
	* @date:	2015年11月13日 
	* @param: @return
	* @param: @throws Exception
	* @return: List<SysOrgan>    返回类型 
	* @throws
	 */
	public List<SysOrgan> queryAllParentSysOrganList () throws Exception;
	
	/**
	 * 
	* @Title: queryIsChild 
	* @Description:查询这个机构是否有下级机构
	* @author:	chengcheng 
	* @date:	2015年11月13日 
	* @param: @param organId
	* @param: @return
	* @param: @throws Exception
	* @return: boolean    返回类型 
	* @throws
	 */
	public boolean queryIsChild (String organId) throws Exception;
	
	/**
	 * 
	* @Title: queryChildOrganById 
	* @Description:根据父ID查询子机构
	* @author:	chengcheng 
	* @date:	2015年11月16日 
	* @param: @param Id
	* @param: @return
	* @param: @throws Exception
	* @return: List<SysOrgan>    返回类型 
	* @throws
	 */
	public List<SysOrgan> queryChildOrganById (String parentId) throws Exception;
	/**
	 * 查询事项办理单位列表，若机构主键存在，则记录不包含本机构（所有记录）
	 * 
	 * @param areaId
	 *            所属区划
	 * @param organId
	 *            机构ID
	 * @return List<SysOrgan> 机构列表
	 * @throws Exception
	 */
	public List<SysOrgan> findTransactOrganList(String areaId, String organId)
			throws Exception;
	
	/**
	 * 
	* @Title: queryAllSysOrganList 
	* @Description:查询全部的机构
	* @author:	chengcheng 
	* @date:	2015年11月20日 
	* @param: @return
	* @param: @throws Exception
	* @return: List<SysOrgan>    返回类型 
	* @throws
	 */
	public List<SysOrgan> queryAllSysOrganList () throws Exception;
	
	/**
	 * 通过areid获取organ集合
	 * @author yaozy
	 * @date 2015-11-27
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<SysOrgan> queryAllSysOrganListByAreaId(String areaId) throws Exception;
}
