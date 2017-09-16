package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysAreaDao;
import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.service.ISysAreaService;
import util.BaseParameter;
import util.Common;
import util.base.service.BaseServiceImpl;

public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements ISysAreaService {
	
	public SysAreaServiceImpl(ISysAreaDao sysAreaDao) {
		super(sysAreaDao);
		this.sysAreaDao = sysAreaDao;
	}
	private ISysAreaDao sysAreaDao;

	@Override
	public void deleteSysArea(String areaId) throws Exception {
		SysArea sysArea = this.getSysAreaBytopId(areaId);
		sysArea.setDelFlag(BaseParameter.DELETE_YES);
		sysAreaDao.updateSysArea(sysArea);
	}

	@Override
	public void saveOrUpdateSysArea(SysArea sysArea) throws Exception {
		if (null != sysArea.getAreaId() && !"".equals(sysArea.getAreaId())) {// 区划存在，修改区划信息
			SysArea area = sysAreaDao.getSysAreaBytopId(sysArea.getAreaId());
			area.setAreaCode(sysArea.getAreaCode());
			area.setAreaName(sysArea.getAreaName());
			area.setAreaLevel(sysArea.getAreaLevel());
			area.setAreaSort(sysArea.getAreaSort());
			sysAreaDao.updateSysArea(area);
		} else { // 区划不存在，新增区划
			sysArea.setDelFlag(BaseParameter.NO);
			sysAreaDao.saveSysArea(sysArea);
			sysArea.setAreaPath(sysArea.getAreaPath()+ sysArea.getAreaId()+".");
			sysAreaDao.update(sysArea);
		}
	}

	@Override
	public String querySysAreaJSONByAreaId(String areaId) throws Exception {
		SysArea sysArea = sysAreaDao.getSysAreaBytopId(areaId);
		StringBuffer tmp = new StringBuffer();
		if (null != sysArea) {
			tmp.append("[{id:'").append(sysArea.getAreaId())
					.append("', name:'").append(sysArea.getAreaName()).append(
					"',code:'").append(sysArea.getAreaCode()).append(
							"', pId:'").append(sysArea.getTopId().trim())
					.append("', isParent: " + isHaveArea(areaId) + "}]");
		} else {
			tmp.append("[]");
		}
		return tmp.toString();
	}

	@Override
	public String querySysAreaListJSONByTopId(String areaId) throws Exception {
		String sysAreaJson;
		StringBuffer tmp = new StringBuffer();
		List<SysArea> AreaList = sysAreaDao.querySysAreaByPid(areaId);
		if (!Common.collectionIsNullOrSpace(AreaList)) {
			sysAreaJson = "[]";
		} else {// 拼接区划json
			tmp.append("[");
			for (SysArea syaArea : AreaList) {
				tmp.append("{id:'").append(syaArea.getAreaId()).append(
						"', name:'").append(syaArea.getAreaName()).append(
						"',code:'").append(syaArea.getAreaCode()).append(
						"', pId:'").append(syaArea.getTopId().trim()).append(
						"', isParent: " + isHaveArea(syaArea.getAreaId())
								+ "},");
			}
			sysAreaJson = tmp.toString();
			if (AreaList.size() > 0) {// 去掉最后一个,
				sysAreaJson = sysAreaJson
						.substring(0, sysAreaJson.length() - 1);
			}
			sysAreaJson += "]";
		}
		return sysAreaJson;
	}

	/**
	 * 判断是否存在下级区划
	 * 
	 * @param areaId
	 *            区划ID
	 * @return
	 * @throws Exception
	 */
	private boolean isHaveArea(String areaId) throws Exception {
		List<SysArea> list = sysAreaDao.querySysAreaByPid(areaId);
		if (Common.collectionIsNullOrSpace(list)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public SysArea getSysAreaBytopId(String areaId) throws Exception {
		return sysAreaDao.getSysAreaBytopId(areaId);
	}

	@Override
	public SysArea getSysAreaTop() throws Exception {
		return sysAreaDao.getSysAreaTop();
	}
	
	@Override
	public List<SysArea> querySysAreaByAreaPath(String areaId) throws Exception {
		return sysAreaDao.querySysAreaByAreaPath(areaId);
	}

	@Override
	public String querySysAreaListToCity(String areaId) throws Exception {
		String sysAreaJson;
		StringBuffer tmp = new StringBuffer();
		List<SysArea> AreaList = sysAreaDao.querySysAreaByPid(areaId);
		if (!Common.collectionIsNullOrSpace(AreaList)) {
			sysAreaJson = "[]";
		} else {// 拼接区划json
			tmp.append("[");
			for (SysArea syaArea : AreaList) {
				tmp.append("{id:'").append(syaArea.getAreaId()).append(
						"', name:'").append(syaArea.getAreaName()).append(
						"',code:'").append(syaArea.getAreaCode()).append(
						"', pId:'").append(syaArea.getTopId().trim()).append(
						"', isParent: " + false
								+ "},");
			}
			sysAreaJson = tmp.toString();
			if (AreaList.size() > 0) {// 去掉最后一个,
				sysAreaJson = sysAreaJson
						.substring(0, sysAreaJson.length() - 1);
			}
			sysAreaJson += "]";
		}
		return sysAreaJson;
	}
	
	@Override
	public List<SysArea> querySysAreaListByTopId(String areaId)
			throws Exception {
		List<SysArea> areaList = sysAreaDao.querySysAreaByPid(areaId);
		return areaList;
	}

}
