package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysAppParam;
import util.ResultPage;

/**
 * 系统参数信息 service接口
 * 
 * @author : yaosw
 * @date : 2016-02-19
 */
public interface ISysAppParamService{

	/**
	 * 保存或修改系统参数信息
	 * @param sysAppParam
	 * @throws Exception
	 */
	void saveOrUpdateSysAppParam(SysAppParam sysAppParam)throws Exception;

	/**
	 * 删除系统参数信息
	 * @param sysAppParam
	 * @throws Exception
	 */
	void deleteSysAppParam(SysAppParam sysAppParam) throws Exception;

	/**
	 * 根据主键id查找系统参数信息
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	SysAppParam querySysAppParam(String paramId) throws Exception;

	/**
	 * 根据条件分页查询系统参数信息
	 * @param sysAppParam
	 * @param page
	 * @param pageRowsMiddle
	 * @return
	 * @throws Exception
	 */
	ResultPage querySysAppParamList(SysAppParam sysAppParam, int page, int pageRowsMiddle) throws Exception;

	/**
	 * 根据父级id查询下级系统参数信息列表并转换为特定格式的json字符串
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	String getSysAppParamTreeByPid(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级系统参数信息列表并转换为特定格式的json字符串
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	String getSysAppParamTreeByParentCode(String paramCode) throws Exception;

	/**
	 * 根据父级id查询下级系统参数信息列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	List<SysAppParam> querySysAppParamByParentId(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级系统参数信息列表
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	List<SysAppParam> querySysAppParamList(String paramCode) throws Exception;

	/**
	 * 根据参数编码查询系统参数信息
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	SysAppParam getSysAppParamByParamCode(String paramCode) throws Exception;

	/**
	 * 根据id查询参数信息列表
	 * @param paramIds 参数id，多个以','分割
	 * @return
	 * @throws Exception
	 */
	List<SysAppParam> querySysAppParamByIdsList(String paramIds) throws Exception;

	/**
	 * 参数编码唯一性验证
	 * @param paramId
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	boolean chkParamCodeUnique(String paramId, String paramCode) throws Exception;
	
}
