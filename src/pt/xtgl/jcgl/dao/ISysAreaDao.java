package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysArea;
import util.base.dao.IBaseDao;

/**
 * 功能：区划dao
 * 
 * @author xuhw
 * @date 2013-04-02
 * @modify：
 */
public interface ISysAreaDao extends IBaseDao<SysArea> {

	/**
	 * 根据父级区划查询下级区划列表
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 区划json集合
	 * @throws Exception
	 */
	public List<SysArea> querySysAreaByPid(String areaId) throws Exception;

	/**
	 * 根据父级区划查询区划树
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 区划集合
	 * @throws Exception
	 */
	public List<SysArea> querySysAreaByAreaPath(String areaId) throws Exception;

	/**
	 * 功能：新增区划信息
	 * 
	 * @author xuhw
	 * @date 2013-04-02
	 * @param sysArea
	 * @throws Exception
	 */
	public void saveSysArea(SysArea sysArea) throws Exception;

	/**
	 * 功能：修改区划信息
	 * 
	 * @author xuhw
	 * @date 2013-04-02
	 * @param sysArea
	 * @throws Exception
	 */
	public void updateSysArea(SysArea sysArea) throws Exception;

	/**
	 * 功能：根据areaId查询区划信息
	 * 
	 * @author xuhw
	 * @date 2013-04-02
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public SysArea getSysAreaBytopId(String areaId) throws Exception;

	/**
	 * 获取顶级区划信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public SysArea getSysAreaTop() throws Exception;

}
