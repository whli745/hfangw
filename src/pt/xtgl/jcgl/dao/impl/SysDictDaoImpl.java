package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysDictDao;
import pt.xtgl.jcgl.pojo.SysDict;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysDictDaoImpl extends BaseDaoImpl<SysDict> implements ISysDictDao {

	/**
	 * 删除数据字典信息
	 */
	@Override
	public void deleteSysDict(SysDict sysDict) throws Exception {
		sysDict.setDelFlag(BaseParameter.DELETE_YES);
		update(sysDict);
	}

	/**
	 * 查找数据字典信息
	 */
	@Override
	public SysDict querySysDict(String dictId) throws Exception {
		return super.onGet(dictId);
	}

	@Override
	public List<SysDict> querySysDictByIdsList(String dictIds) throws Exception {
		String hql = "from SysDict d where d.dictId in (" + dictIds + ")";
		return super.queryByHql(hql, new Object[] {});
	}

	@Override
	public SysDict getSysDictByDictCode(String dictCode) throws Exception {
		String hql = "from SysDict d where d.dictCode = '" + dictCode.trim() + "'";
		return super.getFirstObject(hql, null);
	}

	/**
	 * 保存数据字典信息
	 */
	@Override
	public void saveSysDict(SysDict sysDict) throws Exception {
		save(sysDict);
	}

	/**
	 * 修改字典信息
	 */
	@Override
	public void updateSysDict(SysDict sysDict) throws Exception {
		update(sysDict);
	}

	/**
	 * 按数据字典类型或者路径查询数据字典信息
	 */
	@Override
	public ResultPage querySysDictBydictTypeOrdictPathList(String dictType,
			String dictPath, int page, int pageRows) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysDict sd where 1=1 and delFlag=? ";
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(dictType)) {
			hql += " and sd.dictType=?";
			parmaList.add(dictType);
		}
		if (!Common.isNullOrSpace(dictPath)) {
			hql += " and sd.dictPath like ?";
			parmaList.add("%" + dictPath + "%");
		}
		return super.getResultPage(hql, parmaList, page, pageRows);
	}

	/**
	 * 新增或者修改数据字典信息
	 */
	@Override
	public void saveOrUpdateSysDict(SysDict sysDict) throws Exception {
		saveOrUpdate(sysDict);
	}

	@Override
	public ResultPage querySysDictList(String dictName, String dictType,
			String delFlag, int page, int pageRows) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysDict sd where 1=1 ";
		List<Object> parmaList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(dictType)) {
			hql += " and sd.dictType=?";
			parmaList.add(dictType);
		}
		if (!Common.isNullOrSpace(dictName)) {
			hql += " and sd.dictName like ?";
			parmaList.add("%" + dictName + "%");
		}
		if (!Common.isNullOrSpace(delFlag)) {
			hql += " and sd.delFlag =?";
			parmaList.add(delFlag);
		}
		return super.getResultPage(hql, parmaList, page, pageRows);
	}

	@Override
	public List<SysDict> querySysDictListByDictType(String dictType)
			throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysDict sd where sd.delFlag=? and sd.dictType=? order by sd.dictPath asc, sd.dictCode asc";
		return this.queryByHql(hql, new Object[] { BaseParameter.DELETE_NO,
				dictType });
	}

	@Override
	public List<SysDict> queryAllSysDictList(String delFlag) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysDict where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(delFlag)) {
			hql += " and delFlag=?";
			paramList.add(delFlag);
		}
		hql = hql + " order by dictPath asc, dictCode asc";
		return super.queryByHql(hql, paramList);
	}

	@Override
	public List<SysDict> querySysDictByParentId(String parentId)
			throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysDict sd where sd.delFlag=? ";
		if (!Common.isNullOrSpace(parentId)) {
			hql += " and parentId =?";
		}
		return super.queryByHql(hql, new Object[] { BaseParameter.DELETE_NO,
				parentId });
	}

	@Override
	public List<SysDict> querySysDictList(String dictCode) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysDict s where s.parentId = (select d.dictId from pt.xtgl.jcgl.pojo.SysDict d where d.dictCode=?) and s.delFlag=? order by dictCode asc";
		return super.queryByHql(hql, new Object[] { dictCode,
				BaseParameter.DELETE_NO });
	}

	@Override
	public List<SysDict> querySysDictListByDictPath(String dictCode)
			throws Exception {
		String hql = "select s from pt.xtgl.jcgl.pojo.SysDict s, pt.xtgl.jcgl.pojo.SysDict d where s.dictPath like '-1.'+d.dictId+'.%' and d.dictCode=?";
		return super.queryByHql(hql, new Object[] { dictCode });
	}
}
