package pt.wzgl.pzgl.service;

import java.util.List;

import pt.wzgl.pzgl.pojo.ZjblType;
import util.ResultPage;

/**
 * 证件办理分类信息 service接口
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public interface IZjblTypeService{

	/**
	 * 保存或修改证件办理分类信息
	 * @param zjblType
	 * @throws Exception
	 */
	void saveOrUpdateZjblType(ZjblType zjblType)throws Exception;

	/**
	 * 删除证件办理分类信息
	 * @param zjblType
	 * @throws Exception
	 */
	void deleteZjblType(ZjblType zjblType) throws Exception;

	/**
	 * 根据主键id查找证件办理分类信息
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	ZjblType queryZjblType(String typeId) throws Exception;

	/**
	 * 根据条件分页查询证件办理分类信息
	 * @param zjblType
	 * @param page
	 * @param pageRowsMiddle
	 * @return
	 * @throws Exception
	 */
	ResultPage queryZjblTypeList(ZjblType zjblType, int page, int pageRowsMiddle) throws Exception;

	/**
	 * 根据父级id查询下级证件办理分类信息列表并转换为特定格式的json字符串
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	String getZjblTypeTreeByPid(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级证件办理分类信息列表并转换为特定格式的json字符串
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	String getZjblTypeTreeByParentCode(String typeCode) throws Exception;

	/**
	 * 根据父级id查询下级证件办理分类信息列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	List<ZjblType> queryZjblTypeByParentId(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级证件办理分类信息列表
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	List<ZjblType> queryZjblTypeList(String typeCode) throws Exception;

	/**
	 * 根据分类编码查询证件办理分类信息
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	ZjblType getZjblTypeByTypeCode(String typeCode) throws Exception;

	/**
	 * 根据id查询分类信息列表
	 * @param typeIds 分类id，多个以','分割
	 * @return
	 * @throws Exception
	 */
	List<ZjblType> queryZjblTypeByIdsList(String typeIds) throws Exception;

	/**
	 * 分类编码唯一性验证
	 * @param typeId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	boolean chkTypeCodeUnique(String typeId, String typeCode) throws Exception;
	
}
