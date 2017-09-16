package pt.wzgl.nrgl.service;

import java.util.List;

import pt.wzgl.nrgl.pojo.InfoTcategory;
import util.ResultPage;

/**
 * 栏目信息管理service接口
 * @author Administrator
 *
 */
public interface IInfoTcategoryService {

	/**
	 * 查询栏目信息
	 * @author shimh
	 * @date 2014-9-23
	 * @param infoTcategory
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryInfoTcategoryList(InfoTcategory infoTcategory,
			int page,int pageRows)throws Exception;
	
	/**
	 * 通过主键得到栏目管理对象
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @return
	 * @throws Exception
	 */
	public InfoTcategory initInfoTcategory(String columnId)throws Exception;
	
	/**
	 * 保存添加或修改的栏目信息
	 * @author shimh
	 * @date 2014-9-23
	 * @param infoTcategory
	 * @param userId
	 * @throws Exception
	 */
	public void saveInfoTcategory(InfoTcategory infoTcategory,String userId)throws Exception;
	
	/**
	 * 改变栏目信息状态
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @param toUsingFlag
	 * @throws Exception
	 */
	public void usingInfoTcategory(String columnId, String toUsingFlag) throws Exception;
	
	/**
	 * 删除栏目管理对象
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @throws Exception
	 */
	public void delInfoTcategory(String columnId)throws Exception;
	
	/**
	 * 通过父级id查询子集栏目
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcategory> queryInfoTcategoryListByParentId(String columnId) throws Exception;
	
	/**
	 * 通过parentId得到parentName
	 * @author shimh
	 * @date 2014-9-23
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public String getParentNameByParentId(String parentId)throws Exception;
	
	/**
	 * 查询栏目json
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 * @throws Exception
	 */
	public String infoTcategoryJson()throws Exception;
	
	/**
	 * 通过parentId得到当前栏目的子栏目的JSON
	 * @author shimh
	 * @date 2014-9-24
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public String getInfoTcategoryLeftListByPid(String parentId)throws Exception;
	
	/**
	 * 新增/修改栏目
	 * @author shimh
	 * @date 2014-9-24
	 * @param infoTcategory
	 * @param userId
	 * @throws Exception
	 */
	public void saveOrUpdateInfoTcategory(InfoTcategory infoTcategory,String userId)throws Exception;
	
	
	/**
	 * 是否有下级栏目
	 * @author shimh
	 * @date 2014-9-28
	 * @param columnId
	 * @return
	 */
	public boolean hasNextInfoTcategory(String columnId)throws Exception;
	
	/**
	 * 通过级联路径查询栏目list
	 * @author shimh
	 * @date 2014-10-11
	 * @param columnId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcategory> queryInfoTcategoryListByColumnPath(String columnId)throws Exception;
	
	/**
	 * 查询所有通知公告
	 * @param columnName
	 * @return
	 * @throws Exception
	 * @createDate 2015-3-6
	 * @author Liangmh
	 */
	public List<InfoTcategory> getTZGGByInfoTcategoryName(String columnName) throws Exception;
}
