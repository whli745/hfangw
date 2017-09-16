package pt.wzgl.pzgl.service;

import java.util.List;

import pt.wzgl.pzgl.pojo.GgfwType;
import util.ResultPage;


/**
 * 公共服务分类信息 service接口
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public interface IGgfwTypeService {

	/**
	 * 保存或修改公共服务分类信息
	 * @param ggfwType
	 * @throws Exception
	 */
	void saveOrUpdateGgfwType(GgfwType ggfwType)throws Exception;

	/**
	 * 删除公共服务分类信息
	 * @param ggfwType
	 * @throws Exception
	 */
	void deleteGgfwType(GgfwType ggfwType) throws Exception;

	/**
	 * 根据主键id查找公共服务分类信息
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	GgfwType queryGgfwType(String typeId) throws Exception;

	/**
	 * 根据条件分页查询公共服务分类信息
	 * @param ggfwType
	 * @param page
	 * @param pageRowsMiddle
	 * @return
	 * @throws Exception
	 */
	ResultPage queryGgfwTypeList(GgfwType ggfwType, int page, int pageRowsMiddle) throws Exception;

	/**
	 * 根据父级id查询下级公共服务分类信息列表并转换为特定格式的json字符串
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	String getGgfwTypeTreeByPid(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级公共服务分类信息列表并转换为特定格式的json字符串
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	String getGgfwTypeTreeByParentCode(String typeCode) throws Exception;

	/**
	 * 根据父级id查询下级公共服务分类信息列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	List<GgfwType> queryGgfwTypeByParentId(String parentId) throws Exception;

	/**
	 * 根据父级编码查询下级公共服务分类信息列表
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	List<GgfwType> queryGgfwTypeList(String typeCode) throws Exception;

	/**
	 * 根据分类编码查询公共服务分类信息
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	GgfwType getGgfwTypeByTypeCode(String typeCode) throws Exception;

	/**
	 * 根据id查询分类信息列表
	 * @param typeIds 分类id，多个以','分割
	 * @return
	 * @throws Exception
	 */
	List<GgfwType> queryGgfwTypeByIdsList(String typeIds) throws Exception;
	
	/**
	 * 分类编码唯一性验证
	 * @param typeId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	boolean chkTypeCodeUnique(String typeId, String typeCode) throws Exception;

}
