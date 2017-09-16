package pt.wzgl.nrgl.dao;

import java.util.List;

import pt.wzgl.nrgl.pojo.InfoTcontent;
import util.ResultPage;

/**
 * 发布内容信息管理 dao 接口
 * @author Administrator
 *
 */
public interface IinfoTcontentDao {
	/**
	 * 查询发布内容信息  分页
	 * @author shimh
	 * @date 2014-9-24
	 * @param delFlag
	 * @param areaId
	 * @param contentMainTitle
	 * @param infoTcontent
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryInfoTcontentList(String delFlag,String districtFlag,String areaId,String contentMainTitle,
			InfoTcontent infoTcontent,int page,int pageRows)throws Exception;
	
	/**
	 * 通过id获取发布内容对象
	 * @author shimh
	 * @date 2014-9-25
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public InfoTcontent getInfoTcontent(String contentId)throws Exception;
	
	/**
	 * 保存发布内容信息
	 * @author shimh
	 * @date 2014-9-25
	 * @param infoTcontent
	 * @throws Exception
	 */
	public void saveInfoTcontent(InfoTcontent infoTcontent)throws Exception;
	
	/**
	 * 彻底删除发布内容信息
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentId
	 * @throws Exception
	 */
	public void shiftDeleteInfoTcontent(String contentId)throws Exception;
	
	/**
	 * 清空回收站
	 * @author shimh
	 * @date 2014-9-26
	 * @throws Exception
	 */
	public void emptyRecycleInfoTcontent()throws Exception;

	/**
	 * 获取所有的发布内容
	 * @author liulx
	 * @date 2014-10-17
	 * @return
	 * @throws Exception 
	 */
	public List<InfoTcontent> getAllInfoTcontents() throws Exception;
	
	/**
	 * 根据栏目id，区划id 找到对应的内容发布信息
	 * @author liulx
	 * @date 2014-10-31
	 * @param columnId
	 * @param areaId
	 * @return
	 * @throws Exception 
	 */
	public List<InfoTcontent> getInfoTcontentList(String columnId, String areaId) throws Exception;
	/**
	 * 根据栏目id，区划id 找到对应的内容发布信息(地图检索服务)
	 * @date 2014-10-31
	 * @param columnId
	 * @param areaId
	 * @return
	 * @throws Exception 
	 */
	public ResultPage getInfoTcontentListForMap(String columnId, String areaId,int page,int pageRows) throws Exception;
	
	public ResultPage getInfoTcontentPage(String columnId, String areaId,int page,int pageRows) throws Exception;
	public List<InfoTcontent> getInfoTcontentPage(List<String> tcs, String areaId) throws Exception;
	
	/**
	 * 根据查询条件查询InfoTcontent list
	 * @author liulx
	 * @date 2014-10-31
	 * @param infoTcontent
	 * @return
	 * @throws Exception 
	 */
	public List<InfoTcontent> getInfoTcontentList(InfoTcontent infoTcontent) throws Exception;
	
	/**
	 * 通过栏目id和区划id查询发布内容list
	 * @author shimh
	 * @date 2014-10-31
	 * @param delFlag
	 * @param areaId
	 * @param columnId
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryInfoTcontentList(String delFlag,String areaId,
			String columnId,int page,int pageRows)throws Exception;
	
	/**
	 * 上一篇 下一篇
	 * @author shimh
	 * @date 2014-10-31
	 * @param infoTcontent
	 * @param areaId
	 * @param columnId
	 * @param nowCount
	 * @param lnFlag
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryLastInfoTcontentList(InfoTcontent infoTcontent,String areaId,
			String columnId,Integer nowCount, String lnFlag,
			int page,int pageRows)throws Exception;
	
	/**
	 * 根据条件查询新闻
	 * @author liulx
	 * @date 2014-11-26
	 * @param infoTcontent
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryInfoTcontentList(InfoTcontent infoTcontent, int page, int pageRows) throws Exception;
	
	/**
	 * 根据栏目编号获取附件不为空的新闻
	 * @author gaolh
	 * @since 2014-12-3
	 * @param columnId 栏目编号
	 * @param areaId 区划
	 * @param page 页码
	 * @param pageRows 页数
	 * @return 
	 * @throws Exception 
	 */
	public ResultPage getInfoTcontentAttaPage(String columnId, String areaId,
			int page, int pageRows) throws Exception;
	
	/**
	 * 首页地图服务本地检索
	 * @param themeId: 所属主题
	 * @param areaId：所属区划
	 * @param page
	 * @param pageRows
	 * @param name：本地检索关键字
	 * @return
	 * @throws Exception
	 * @createDate 2014-12-11
	 * @author Liangmh
	 */
	public ResultPage getMapLocalSearch(String themeId,String areaId,int page,int pageRows,String name) throws Exception;
	
	/**
	 * 首页地图服务本地检索
	 * @param themeId：所属主题
	 * @param areaId:所属区划
	 * @param name：本地检索关键字
	 * @return
	 * @throws Exception
	 * @createDate 2014-12-11
	 * @author Liangmh
	 */
	public List<InfoTcontent> getMapLocalSearch(String themeId,String areaId, String name) throws Exception;
	
	/**
	 * 获取信息内容列表：注，该方法只获取部分字段
	 * @param columnId
	 * @param areaId
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	public ResultPage getInfoTcontentsBycolumnIdAndDictId(InfoTcontent infoTcontent,String columnId, String areaId,int page, int pageRows) throws Exception;

	/**
	 * 网站首页获取曝光台信息
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcontent> getExposureTableToWebIndex(String districtId) throws Exception;
	
	/**
	 * 网站首页新闻动态
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public List<InfoTcontent> getNewsDynamicToWebIndex(InfoTcontent infoTcontent,String districtId,Integer topNumber,String TcontentType) throws Exception;
	
	/**
	* @Title: getInfoTcontentToWebListByPage 
	* @Description:全局搜索
	* @author:	yaozhiyuan 
	* @date:	2015年12月14日 
	* @throws
    */
	public List<InfoTcontent> getInfoTcontentToWebListByPage(int topNumber,String infoTcontentParentId,String searchKeyWord) throws Exception;
	
	/**
	* @Title: getInfoTcontentBySearch 
	* @Description:全局搜索
	* @author:	yaozhiyuan 
	* @date:	2015年12月14日 
	* @throws
    */
	public ResultPage getInfoTcontentBySearch(InfoTcontent infoTcontent,int page,int pageRows) throws Exception;
}
