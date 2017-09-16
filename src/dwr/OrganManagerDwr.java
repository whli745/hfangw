package dwr;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.Common;
import util.base.action.BaseAction;

/**
* @ClassName: OrganManagerDwr 
* @Description: 机构查询使用的DWR 
* @author chengcheng
* @date 2015年11月16日 
*
 */
public class OrganManagerDwr extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 机构Service接口
	 */
	private ISysOrganService sysOrganService;

	/**
	 * 
	* @Title: queryChildOrganById 
	* @Description: 根据父ID查询子机构
	* @author:	chengcheng 
	* @date:	2015年11月16日 
	* @param: @return
	* @return: String    返回类型 
	* @throws
	 */
	public String queryChildOrganById (String parentId) {
		
		try {
			if (!Common.isNullOrSpace(parentId)) {
				List<SysOrgan> listOrgan = sysOrganService.queryChildOrganById(parentId);
				
				return sysOrganService.convertZtreeJsonSysOrganList(listOrgan);
			}
		} catch (Exception e) {
			error("根据父Id查询子机构异常！", e);
			return null;
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: queryChildOrganByAreaId 
	* @Description:通过区划查询及机构树
	* @author:	chengcheng 
	* @date:	2015年11月24日 
	* @param: @param areaId
	* @param: @return
	* @return: String    返回类型 
	* @throws
	 */
	public String queryChildOrganByAreaId (String areaId) {
		try {
			if (!Common.isNullOrSpace(areaId)) {
				List<SysOrgan> list = sysOrganService.findTransactOrganList(areaId, null);
				return sysOrganService.convertZtreeJsonSysOrganList(list);
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ISysOrganService getSysOrganService() {
		return sysOrganService;
	}

	public void setSysOrganService(ISysOrganService sysOrganService) {
		this.sysOrganService = sysOrganService;
	}
	
}
