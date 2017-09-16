package pt.wzgl.pzgl.dao;

import java.util.List;

import pt.wzgl.pzgl.pojo.ZjblType;
import util.ResultPage;


/**
 * 证件办理分类信息 dao接口
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public interface IZjblTypeDao{
	/**
	 * 新增或者修改证件办理分类信息
	 * 
	 * @date 2016-02-15
	 * @param zjblType
	 *            证件办理分类对象
	 * @throws Exception
	 *             异常信息
	 */
	public void saveOrUpdateZjblType(ZjblType zjblType) throws Exception;

	/**
	 * 查询证件办理分类信息
	 * @date 2016-02-15
	 * @param typeId
	 *            证件办理分类Id
	 * @return
	 * @throws Exception
	 */
	public ZjblType queryZjblType(String typeId) throws Exception;

	/**
	 * 查询证件办理分类信息
	 * 
	 * @date 2016-02-15
	 */
	public ZjblType getZjblTypeByTypeCode(String typeCode) throws Exception;

	/**
	 * 通过typeIds查询 分类列表
	 * 
	 * @by yaosw
	 */
	public List<ZjblType> queryZjblTypeByIdsList(String typeIds) throws Exception;

	/**
	 * 查询所有的有效的分类信息
	 * 
	 * @author yaosw
	 * @date 2016-02-15
	 * @param delFlag
	 *            删除字段 0未删除 1已删除
	 * @return
	 * @throws Exception
	 */
	public List<ZjblType> queryAllZjblTypeList(String delFlag) throws Exception;

	/**
	 * 通过路径查询分类信息列表
	 * 
	 * @author yaosw
	 * @date 2016-02-15
	 * @param parentId
	 *            父节点Id
	 * @return
	 * @throws Exception
	 */
	public List<ZjblType> queryZjblTypeByParentId(String parentId)
			throws Exception;

	/**
	 * 根据分类编码查询下级分类列表信息
	 * 
	 * @author yaosw
	 * @date 2016-02-15
	 * @return
	 * @throws Exception
	 */
	public List<ZjblType> listZjblTypeByPCode(String typeCode) throws Exception;

	/**
	 * 根据条件分页查询分类信息
	 * @param zjblType
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryZjblTypeList(ZjblType zjblType, int page, int pageRows) throws Exception;

	/**
	 * 分类编码唯一性验证
	 * @param typeId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public boolean chkTypeCodeUnique(String typeId, String typeCode) throws Exception;
	
}
