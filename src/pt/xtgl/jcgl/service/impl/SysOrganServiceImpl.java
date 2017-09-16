package pt.xtgl.jcgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.xtgl.jcgl.dao.ISysOrganDao;
import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.service.BaseServiceImpl;

import com.google.gson.Gson;

public class SysOrganServiceImpl extends BaseServiceImpl<SysOrgan> implements ISysOrganService {
	
	public SysOrganServiceImpl(ISysOrganDao sysOrganDao) {
		super(sysOrganDao);
		this.sysOrganDao = sysOrganDao;
	}
	private ISysOrganDao sysOrganDao;

	@Override
	public void delSysOrgan(String organId) throws Exception {
		SysOrgan sysOrgan = sysOrganDao.getSysOrganInfo(organId);
		if (null != sysOrgan) {
			sysOrgan.setDelFlag(BaseParameter.YES);
			sysOrganDao.updateSysOrgan(sysOrgan);
		}
	}

	@Override
	public void saveOrUpdateOrgan(SysOrgan sysOrgan) throws Exception {
		if (Common.isNullOrSpace(sysOrgan.getOrganId())) { // 机构主键ID不存在，新增机构
			sysOrgan.setDelFlag(BaseParameter.NO);
			if (!Common.isNullOrSpace(sysOrgan.getParentId())) { // 存在父级机构，查询父级机构的organPath后，追加添加本次organPath
				SysOrgan organ = sysOrganDao.getSysOrganInfo(sysOrgan
						.getParentId());
				sysOrgan.setOrganPath(organ.getOrganPath());
			}
			sysOrganDao.saveSysOrgan(sysOrgan);
			sysOrgan.setOrganPath(sysOrgan.getOrganPath() + "."
					+ sysOrgan.getOrganId()); // 追加organPath
			sysOrganDao.updateSysOrgan(sysOrgan);
		} else {
			sysOrganDao.updateSysOrgan(sysOrgan);
		}

	}

	@Override
	public SysOrgan getSysOrganInfo(String organId) throws Exception {
		return sysOrganDao.getSysOrganInfo(organId);
	}

	@Override
	public ResultPage findSysOrganList(String organName, String areaId,
			int page, int pageRows) throws Exception {
		return sysOrganDao.findSysOrganList(organName, areaId, page, pageRows);
	}

	@Override
	public List<SysOrgan> findSysOrganList(String areaId, String organId)
			throws Exception {
		return sysOrganDao.findSysOrganList(areaId, organId);
	}

	@Override
	public List<SysOrgan> queryAllParentSysOrganList() throws Exception {
		return sysOrganDao.queryAllParentSysOrganList();
	}

	@Override
	public String convertZtreeJsonSysOrganList(List<SysOrgan> sysOrganList) throws Exception {

		if (sysOrganList != null && sysOrganList.size() >0) {
			/*
			 *思路：将每一个对象中的值，转到map集合  [id : organId,name : organName,code : organCode,pId : parentId,isParent:是否是顶级机构] 
			 */
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			for (SysOrgan sysOrgan : sysOrganList) {
				Map<String,Object> map = new HashMap<String,Object>();
				
				map.put("id", sysOrgan.getOrganId());
				map.put("name", sysOrgan.getOrganName());
				map.put("code", sysOrgan.getOrganCode());
				map.put("pId", sysOrgan.getParentId());
				map.put("isParent", sysOrganDao.queryIsChild(sysOrgan.getOrganId()));
				
				listMap.add(map);
			}
			
			Gson gson = new Gson();
			return gson.toJson(listMap);
		}
		
		return null;
	}

	@Override
	public List<SysOrgan> queryChildOrganById(String parentId) throws Exception {
		return sysOrganDao.queryChildOrganById(parentId);
	}

	@Override
	public List<SysOrgan> queryAllSysOrganList() throws Exception {
		return sysOrganDao.queryAllSysOrganList();
	}
	
	@Override
	public List<SysOrgan> findTransactOrganList(String areaId, String organId)
			throws Exception {
		return sysOrganDao.findTransactOrganList(areaId, organId);
	}

	@Override
	public List<SysOrgan> queryAllSysOrganListByAreaId(String areaId)
			throws Exception {
		return sysOrganDao.queryAllSysOrganListByAreaId(areaId);
	}
}
