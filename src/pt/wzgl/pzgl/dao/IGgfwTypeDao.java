package pt.wzgl.pzgl.dao;

import java.util.List;

import pt.wzgl.pzgl.pojo.GgfwType;
import util.ResultPage;
import util.base.dao.IBaseDao;

/**
 * 公共服务分类信息 dao接口
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public interface IGgfwTypeDao extends IBaseDao<GgfwType> {

	/**
	 * 新增或者修改公共服务分类信息
	 * 
	 * @date 2016-02-15
	 * @param ggfwType
	 *            公共服务分类对象
	 * @throws Exception
	 *             异常信息
	 */
	public void saveOrUpdateGgfwType(GgfwType ggfwType) throws Exception;

	/**
	 * 查询公共服务分类信息
	 * @date 2016-02-15
	 * @param typeId
	 *            公共服务分类Id
	 * @return
	 * @throws Exception
	 */
	public GgfwType queryGgfwType(String typeId) throws Exception;

	/**
	 * 查询公共服务分类信息
	 * 
	 * @date 2016-02-15
	 */
	public GgfwType getGgfwTypeByTypeCode(String typeCode) throws Exception;

	/**
	 * 通过typeIds查询 分类列表
	 * 
	 * @by yaosw
	 */
	public List<GgfwType> queryGgfwTypeByIdsList(String typeIds) throws Exception;

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
	public List<GgfwType> queryAllGgfwTypeList(String delFlag) throws Exception;

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
	public List<GgfwType> queryGgfwTypeByParentId(String parentId)
			throws Exception;

	/**
	 * 根据分类编码查询下级分类列表信息
	 * 
	 * @author yaosw
	 * @date 2016-02-15
	 * @return
	 * @throws Exception
	 */
	public List<GgfwType> listGgfwTypeByPCode(String typeCode) throws Exception;

	/**
	 * 根据条件分页查询分类信息
	 * @param ggfwType
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryGgfwTypeList(GgfwType ggfwType, int page, int pageRows)throws Exception;
	
	/**
	 * 分类编码唯一性验证
	 * @param typeId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public boolean chkTypeCodeUnique(String typeId, String typeCode) throws Exception;
}
