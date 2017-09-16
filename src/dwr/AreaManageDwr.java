package dwr;

import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.service.ISysAreaService;
import util.BaseParameter;
import util.base.action.BaseAction;

/**
 * 区划处理dwr
 * 
 * @author meidj
 * @date 2013-05-27
 * 
 */
@SuppressWarnings("serial")
public class AreaManageDwr extends BaseAction {
	private ISysAreaService sysAreaService;

	/**
	 * 根据区划主键ID，删除区划对象信息（逻辑删除）
	 * 
	 * @param areaId
	 *            区划ID
	 * @return 操作结果状态 1：成功；0失败;
	 */
	public String delArea(String areaId) {
		if (null == areaId || "".equals(areaId.trim())) {
			return BaseParameter.NO;
		} else {
			try {
				sysAreaService.deleteSysArea(areaId);
			} catch (Exception e) {
				error(null, e);
				return BaseParameter.NO;
			}
		}
		return BaseParameter.YES;
	}

	/**
	 * 保存或修改区划对象（若areaId区划主键ID为空，则为新增区划）
	 * 
	 * @param areaId
	 *            区划ID（如ID为null或""，则表示新增区划）
	 * @param syaArea
	 *            区划对象
	 * @return 操作结果状态,失败返回0，成功返回区划主键ID（新增时使用）
	 */
	public String updateOrSaveArea(SysArea sysArea) {
		if (null == sysArea) { // 区划实体对象为空
			return BaseParameter.NO;
		} else {
			try {
				sysAreaService.saveOrUpdateSysArea(sysArea);
			} catch (Exception e) {
				error(null, e);
				return BaseParameter.NO;
			}
		}
		return sysArea.getAreaId();
	}

	/**
	 * 根据选中区划ID查询下级区划
	 * 
	 * @param areaId
	 *            区划ID（dwr传值）
	 * @date 2013-05-27
	 * @return JOSN
	 */
	public String areaTreeList(String areaId) {
		String sysAreaJson = null;
		try {
			sysAreaJson = sysAreaService.querySysAreaListJSONByTopId(areaId);
		} catch (Exception e) {
			error(null, e);
		}
		return sysAreaJson;
	}

	/**
	 * 点击树，查询树详细信息
	 * 
	 * @param areaId
	 *            区划ID（dwr传值）
	 * @return
	 */
	public SysArea getAreaInfo(String areaId) {
		SysArea sysArea = null;
		try {
			sysArea = sysAreaService.getSysAreaBytopId(areaId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysArea;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

}
