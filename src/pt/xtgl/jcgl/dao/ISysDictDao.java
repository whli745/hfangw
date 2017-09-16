package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import util.ResultPage;
import util.base.dao.IBaseDao;

public interface ISysDictDao extends IBaseDao<SysDict> {
	/**
	 * 新增或者修改数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-11
	 * @param sysDict
	 *            数据字典对象
	 * @throws Exception
	 *             异常信息
	 */
	public void saveOrUpdateSysDict(SysDict sysDict) throws Exception;

	/**
	 * 保存数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 *            数据字典对象
	 * @throws Exception
	 */
	public void saveSysDict(SysDict sysDict) throws Exception;

	/**
	 * 删除数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 *            数据字典对象
	 * @throws Exception
	 */
	public void deleteSysDict(SysDict sysDict) throws Exception;

	/**
	 * 修改数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 *            数据字典对象
	 * @throws Exception
	 */
	public void updateSysDict(SysDict sysDict) throws Exception;

	/**
	 * 查询数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param dictId
	 *            数据字典Id
	 * @return
	 * @throws Exception
	 */
	public SysDict querySysDict(String dictId) throws Exception;

	/**
	 * 查询数据字典信息
	 * 
	 * @date 2013-09
	 */
	public SysDict getSysDictByDictCode(String dictCode) throws Exception;

	/**
	 * 通过dictIds查询 字典列表
	 * 
	 * @by zhujj
	 */
	public List<SysDict> querySysDictByIdsList(String dictIds) throws Exception;

	/**
	 * 按数据字典类型或者路径查询数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param dictType
	 *            数据字典类型
	 * @param dictPath
	 *            数据字典路径
	 * @throws Exception
	 */
	public ResultPage querySysDictBydictTypeOrdictPathList(String dictType,
			String dictPath, int page, int pageRows) throws Exception;

	/**
	 * 查询数据字典列表
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param dictName
	 *            数据字典名称
	 * @param dictType
	 *            数据字典类型
	 * @param delFlag
	 *            是否有效标识
	 * @param page
	 *            列表页数
	 * @param pageRows
	 *            列表显示的最大行数
	 * @return
	 */
	public ResultPage querySysDictList(String dictName, String dictType,
			String delFlag, int page, int pageRows) throws Exception;

	/***************************************************************************
	 * 根据字典类型读取字典信息
	 * 
	 * @author gaolh
	 * @param dictType
	 *            字典类型
	 * @return 字典信息列表
	 * @throws Exception
	 */
	public List<SysDict> querySysDictListByDictType(String dictType)
			throws Exception;

	/**
	 * 查询所有的有效的字典信息
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 * @param delFlag
	 *            删除字段 0未删除 1已删除
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> queryAllSysDictList(String delFlag) throws Exception;

	/**
	 * 通过路径查询字典信息列表
	 * 
	 * @author wanghw
	 * @date 2013-5-5
	 * @param parentId
	 *            父节点Id
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> querySysDictByParentId(String parentId)
			throws Exception;

	/**
	 * 根据字典编码查询下级字典列表信息
	 * 
	 * @author wanghw
	 * @date 2013-5-6
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> querySysDictList(String dictCode) throws Exception;

	/**
	 * 功能 ：通过字典存储路径查找字典list用于一次性加载树
	 * 
	 * @author chj
	 * @date 2013-5-12下午04:23:22
	 * @param dictCode
	 *            字典编码
	 * @return 字典集合
	 */
	public List<SysDict> querySysDictListByDictPath(String dictCode)
			throws Exception;
}
