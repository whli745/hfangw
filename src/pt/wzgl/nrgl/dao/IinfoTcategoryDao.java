package pt.wzgl.nrgl.dao;

import java.util.List;

import pt.wzgl.nrgl.pojo.InfoTcategory;
import util.ResultPage;

/**
 * 栏目信息管理 dao接口
 * @author Administrator
 *
 */
public interface IinfoTcategoryDao {
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
	 * 获取栏目管理对象
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @return
	 * @throws Exception
	 */
	public InfoTcategory initInfoTcategory(String columnId)throws Exception;
	
	/**
	 * 保存栏目管理对象
	 * @author shimh
	 * @date 2014-9-23
	 * @param infoTcategory
	 * @throws Exception
	 */
	public void saveInfoTcategory(InfoTcategory infoTcategory)throws Exception;
	
	/**
	 * 通过父级id查询子集栏目
	 * @author shimh
	 * @date 2014-9-23
	 * @param columnId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcategory> queryInfoTcategoryListByParentId(String columnId)throws Exception;
	
	/**
	 * 查询所有启用的栏目
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcategory> queryInfoTcategoryList()throws Exception;
	
	/**
	 * 通过顶级栏目id 得到当前和下级的栏目
	 * @author shimh
	 * @date 2014-9-24
	 * @param firstColumnId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcategory> queryInfoTcategoryLeftList(String firstColumnId) throws Exception;
	
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
