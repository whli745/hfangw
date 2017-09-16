package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysAppParam;
import util.ResultPage;


/**
 * 系统参数信息 dao接口
 * 
 * @author : yaosw
 * @date : 2016-02-19
 */
public interface ISysAppParamDao{
	/**
	 * 新增或者修改系统参数信息
	 * 
	 * @date 2016-02-19
	 * @param sysAppParam
	 *            系统参数对象
	 * @throws Exception
	 *             异常信息
	 */
	public void saveOrUpdateSysAppParam(SysAppParam sysAppParam) throws Exception;

	/**
	 * 查询系统参数信息
	 * @date 2016-02-19
	 * @param paramId
	 *            系统参数Id
	 * @return
	 * @throws Exception
	 */
	public SysAppParam querySysAppParam(String paramId) throws Exception;

	/**
	 * 查询系统参数信息
	 * 
	 * @date 2016-02-19
	 */
	public SysAppParam getSysAppParamByParamCode(String paramCode) throws Exception;

	/**
	 * 通过paramIds查询 系统参数列表
	 * 
	 * @by yaosw
	 */
	public List<SysAppParam> querySysAppParamByIdsList(String paramIds) throws Exception;

	/**
	 * 查询所有的有效的系统参数信息
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @param delFlag
	 *            删除字段 0未删除 1已删除
	 * @return
	 * @throws Exception
	 */
	public List<SysAppParam> queryAllSysAppParamList(String delFlag) throws Exception;

	/**
	 * 通过路径查询系统参数信息列表
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @param parentId
	 *            父节点Id
	 * @return
	 * @throws Exception
	 */
	public List<SysAppParam> querySysAppParamByParentId(String parentId)
			throws Exception;

	/**
	 * 根据系统参数编码查询下级系统参数列表信息
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @return
	 * @throws Exception
	 */
	public List<SysAppParam> listSysAppParamByPCode(String paramCode) throws Exception;

	/**
	 * 根据条件分页查询系统参数信息
	 * @param sysAppParam
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage querySysAppParamList(SysAppParam sysAppParam, int page, int pageRows) throws Exception;

	/**
	 * 系统参数编码唯一性验证
	 * @param paramId
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	public boolean chkParamCodeUnique(String paramId, String paramCode) throws Exception;
	
}
