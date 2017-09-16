package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysArea;
import util.base.service.IBaseService;

/**
 * 功能：区划service
 * 
 * @author xuhw
 * @date 2013-04-02
 * @modify：meidj 2013-05-22（更换成树的方式展示）
 */
public interface ISysAreaService extends IBaseService<SysArea> {

	/**
	 * 本级区划对象
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 区划json集合
	 * @throws Exception
	 */
	public String querySysAreaJSONByAreaId(String areaId) throws Exception;

	/**
	 * 根据父级区划查询区划树
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 区划json集合
	 * @throws Exception
	 */
	public String querySysAreaListJSONByTopId(String areaId) throws Exception;

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
	 * 修改或增加区划信息（areaId区划主键ID为null或""时，则新增区划）
	 * 
	 * @param sysArea
	 *            区划实体对象
	 * @throws Exception
	 */
	public void saveOrUpdateSysArea(SysArea sysArea) throws Exception;

	/**
	 * 功能：删除区划信息
	 * 
	 * @author xuhw
	 * @date 2013-04-02
	 * @param
	 * @throws Exception
	 */
	public void deleteSysArea(String areaId) throws Exception;

	/**
	 * 获取顶级区划信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public SysArea getSysAreaTop() throws Exception;
	
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
	 * 根据父级区划查询下面所有市级区划
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 区划json集合
	 * @throws Exception
	 */
	public String querySysAreaListToCity(String areaId) throws Exception;
	
	List<SysArea> querySysAreaListByTopId(String areaId) throws Exception;

}
