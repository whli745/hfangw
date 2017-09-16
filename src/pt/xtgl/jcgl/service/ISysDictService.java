package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import util.ResultPage;
import util.base.service.IBaseService;

public interface ISysDictService extends IBaseService<SysDict> {
	/**
	 * 新增或者修改数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-11
	 * @param sysDict
	 * @throws Exception
	 */
	public abstract void saveOrUpdateSysDict(SysDict sysDict) throws Exception;

	/**
	 * 保存数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 * @throws Exception
	 */
	public abstract void saveSysDict(SysDict sysDict) throws Exception;

	/**
	 * 删除数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 * @throws Exception
	 */
	public abstract void deleteSysDict(SysDict sysDict) throws Exception;

	/**
	 * 修改数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param sysDict
	 * @throws Exception
	 */
	public abstract void updateSysDict(SysDict sysDict) throws Exception;

	/**
	 * 查询数据字典信息
	 * 
	 * @author wangnhw
	 * @date 2013-04-10
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	public abstract SysDict querySysDict(String dictId) throws Exception;

	/**
	 * 查询数据字典信息
	 * 
	 * @date 2013-09
	 */
	public SysDict getSysDictByDictCode(String dictCode) throws Exception;

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
	 * @param dictType
	 * @param delFlag
	 * @param page
	 * @param pageRows
	 * @return
	 */
	public ResultPage querySysDictList(String dictName, String dictType,
			String delFlag, int page, int pageRows) throws Exception;

	/**
	 * 通过dictIds查询 字典列表
	 * 
	 * @by zhujj
	 */
	public List<SysDict> querySysDictByIdsList(String dictIds) throws Exception;

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
	 * 使用树展示字典信息
	 * 
	 * @param dictType
	 *            展示字典的类别
	 * @author gaolh
	 * @date 2013-4-18
	 */
	public String getSysDictTree(String dictType) throws Exception;

	/**
	 * 加载树节点
	 * 
	 * @author wanghw
	 * @date 2013-5-5
	 * @param dictPath
	 *            字典路径
	 * @return
	 * @throws Exception
	 */
	public String getSysDictTreeByPid(String parentId) throws Exception;

	/**
	 * 加载树节点
	 * 
	 * @author zhujj
	 * @date 2013-5
	 * @param dictPath
	 *            字典路径
	 */
	public String getSysDictTreeByCode(String code) throws Exception;

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
	 * 功能 ：根据字典编码一次性加载字典树
	 * 
	 * @author chj
	 * @date 2013-5-12下午02:51:53
	 * @param dictCode
	 *            字典编码
	 * @return
	 */
	public String getSysDictTreeOnceByCode(String dictPath) throws Exception;

	/**
	 * 功能 ：根据字典编码取得此编码下面的子代码的列表
	 * 
	 * @param dictCode
	 * @return List<SysDict>
	 * @throws Exception
	 */
	public List<SysDict> querySysDictListByDictPath(String dictCode)
			throws Exception;

	/**
	 * 通过父级id查询子集
	 * 
	 * @author dusd
	 * @date 2013-9-16
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> querySysDictByParentId(String pid) throws Exception;
}
