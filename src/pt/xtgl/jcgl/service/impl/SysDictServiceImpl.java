package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysDictDao;
import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.Common;
import util.EscapeUnescape;
import util.ResultPage;
import util.base.service.BaseServiceImpl;

public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements ISysDictService {
	
	public SysDictServiceImpl(ISysDictDao sysDictDao) {
		super(sysDictDao);
		this.sysDictDao = sysDictDao;
	}

	private ISysDictDao sysDictDao;

	/**
	 * 删除数据字典信息
	 */
	@Override
	public void deleteSysDict(SysDict sysDict) throws Exception {
		sysDictDao.deleteSysDict(sysDict);
	}                           

	/**
	 * 查找数据字典信息
	 */
	@Override
	public SysDict querySysDict(String dictId) throws Exception {
		SysDict sysDict = sysDictDao.querySysDict(dictId);
		return sysDict;
	}
	
	

	@Override
	public SysDict getSysDictByDictCode(String dictCode) throws Exception {
		return sysDictDao.getSysDictByDictCode(dictCode);
	}

	@Override
	public List<SysDict> querySysDictByIdsList(String dictIds) throws Exception {
		return sysDictDao.querySysDictByIdsList(dictIds);
	}

	/**
	 * 保存数据字典信息
	 */
	@Override
	public void saveSysDict(SysDict sysDict) throws Exception {
		sysDictDao.saveSysDict(sysDict);
	}

	/**
	 * 修改字典信息
	 */
	@Override
	public void updateSysDict(SysDict sysDict) throws Exception {
		sysDictDao.updateSysDict(sysDict);
	}

	/**
	 * 按数据字典类型或者路径查询数据字典信息列表
	 */
	public ResultPage querySysDictBydictTypeOrdictPathList(String dictType,
			String dictPath, int page, int pageRows) throws Exception {
		ResultPage SysDictList = sysDictDao
				.querySysDictBydictTypeOrdictPathList(dictType, dictPath, page,
						pageRows);
		return SysDictList;
	}

	/**
	 * 新增或修改数据字典信息
	 */
	@Override
	public void saveOrUpdateSysDict(SysDict sysDict) throws Exception {
		if (Common.isNullOrSpace(sysDict.getDictId())) {
			sysDict.setDictId(null);
		}
		// 添加一级时，父ID设置为null
		/*
		 * if(sysDict.getParentId() != null && (sysDict.getParentId().equals("")
		 * || sysDict.getParentId().equals("-1"))) { sysDict.setParentId(null);
		 * }
		 */

		sysDict.setDelFlag(BaseParameter.DELETE_NO);
		sysDictDao.saveOrUpdateSysDict(sysDict);
		SysDict parentSysDict = sysDictDao.querySysDict(sysDict.getParentId());
		if (parentSysDict != null) {
			sysDict.setDictPath(parentSysDict.getDictPath()
					+ sysDict.getDictId() + ".");
			sysDictDao.saveOrUpdateSysDict(sysDict);
		} else {
			sysDict.setDictPath("-1.");
		}
	}

	/**
	 * 查询数据字典信息列表
	 */
	@Override
	public ResultPage querySysDictList(String dictName, String dictType,
			String delFlag, int page, int pageRows) throws Exception {
		ResultPage rp = sysDictDao.querySysDictList(dictName, dictType,
				delFlag, page, pageRows);
		return rp;
	}

	@Override
	public List<SysDict> querySysDictListByDictType(String dictType)
			throws Exception {
		return null;
	}

	/**
	 * 使用树展示字典信息
	 * 
	 * @param dictType
	 *            展示字典的类别
	 */
	public String getSysDictTree(String dictType) throws Exception {
		List<SysDict> dictList = sysDictDao
				.querySysDictListByDictType(dictType);
		String sysDictListJson;
		if (!Common.collectionIsNullOrSpace(dictList)) {
			sysDictListJson = "[]";
		} else {
			sysDictListJson = "[";
			for (SysDict dict : dictList) {
				sysDictListJson += "{code:'"
						+ dict.getDictId()
						+ "', name:'"
						+ EscapeUnescape.escape(dict.getDictName())
						+ "', parentID:'"
						+ ((dict.getParentId() == null || dict.getParentId()
								.trim().equals("-1")) ? "" : dict.getParentId())
						+ "'},";
			}
			sysDictListJson = sysDictListJson.substring(0,
					sysDictListJson.length() - 1);
			sysDictListJson += "]";
		}
		return sysDictListJson;
	}

	public boolean isCurrentModuleHaveChildModules(String parentId)
			throws Exception {
		List<SysDict> list = sysDictDao.querySysDictByParentId(parentId);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getSysDictTreeByPid(String parentId) throws Exception {
		String sysDictJson;
		if (parentId == null) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			sysDictJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.SYSDICT_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return sysDictJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<SysDict> sysDictList = sysDictDao.querySysDictByParentId(parentId);
		if (sysDictList == null) {
			sysDictJson = "[]";
		} else {
			tmp.append("[");
			for (SysDict sysDict : sysDictList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysDict.getDictId());
				tmp.append("{id:'")
						.append(sysDict.getDictId())
						.append("', name:'")
						.append(sysDict.getDictName())
						.append("', pId:'")
						.append((sysDict.getParentId() == null ? "" : sysDict
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			sysDictJson = tmp.toString();
			if (sysDictList.size() > 0)
				sysDictJson = sysDictJson
						.substring(0, sysDictJson.length() - 1);
			sysDictJson += "]";
		}
		return sysDictJson;
	}

	public String getSysDictTreeByCode(String code) throws Exception {
		String sysDictJson;
		StringBuffer tmp = new StringBuffer();
		List<SysDict> sysDictList = sysDictDao.querySysDictList(code);
		if (sysDictList == null) {
			sysDictJson = "[]";
		} else {
			tmp.append("[");
			for (SysDict sysDict : sysDictList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysDict.getDictId());
				tmp.append("{id:'")
						.append(sysDict.getDictId())
						.append("', name:'")
						.append(sysDict.getDictName())
						.append("', pId:'")
						.append((sysDict.getParentId() == null ? "" : sysDict
								.getParentId()))
						.append("', isParent: " + isChildNode + ",code:'"
								+ sysDict.getDictCode() + "'},");
			}
			sysDictJson = tmp.toString();
			if (sysDictList.size() > 0)
				sysDictJson = sysDictJson
						.substring(0, sysDictJson.length() - 1);
			sysDictJson += "]";
		}
		return sysDictJson;
	}

	@Override
	public List<SysDict> querySysDictList(String dictCode) throws Exception {
		return sysDictDao.querySysDictList(dictCode);
	}

	@Override
	public List<SysDict> querySysDictListByDictPath(String dictCode)
			throws Exception {
		return sysDictDao.querySysDictListByDictPath(dictCode);
	}

	@Override
	public String getSysDictTreeOnceByCode(String dictCode) throws Exception {
		String sysDictJson;
		StringBuffer tmp = new StringBuffer();
		List<SysDict> sysDictList = sysDictDao
				.querySysDictListByDictPath(dictCode);
		if (sysDictList == null) {
			sysDictJson = "[]";
		} else {
			tmp.append("[");
			for (SysDict sysDict : sysDictList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysDict.getDictId());
				tmp.append("{id:'")
						.append(sysDict.getDictId())
						.append("', name:'")
						.append(sysDict.getDictName())
						.append("', pId:'")
						.append((sysDict .getParentId() == null ? "" : sysDict
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			sysDictJson = tmp.toString();
			if (sysDictList.size() > 0)
				sysDictJson = sysDictJson
						.substring(0, sysDictJson.length() - 1);
			sysDictJson += "]";
		}
		return sysDictJson;
	}

	@Override
	public List<SysDict> querySysDictByParentId(String pid) throws Exception {
		return sysDictDao.querySysDictByParentId(pid);
	}
}
