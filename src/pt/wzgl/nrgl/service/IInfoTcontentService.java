package pt.wzgl.nrgl.service;

import java.util.List;

import pt.wzgl.nrgl.pojo.FileAtta;
import pt.wzgl.nrgl.pojo.InfoTcontent;
import util.ResultPage;
import util.base.service.IBaseService;
import web.pojo.RedPacket;

/**发布内容信息管理 service 接口
  * @Title 
  * @author yaozy 
  * @date 2015-12-3
  */
public interface IInfoTcontentService extends IBaseService<InfoTcontent> {

	/**
	 * 查询发布内容信息  分页
	 * @author shimh
	 * @date 2014-9-24
	 * @param delFlag 回收站
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
	 * 通过id 获取发布内容对象
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
	 * @param fileAtta
	 * @param userId
	 * @throws Exception
	 */
	public void saveInfoTcontent(InfoTcontent infoTcontent,FileAtta fileAtta,String userId)throws Exception;
	
	/**
	 * 通过主键id 删除对象
	 * @author shimh
	 * @date 2014-9-25
	 * @param contentId
	 * @throws Exception
	 */
	public void deleteInfoTcontent(String contentId)throws Exception;
	
	/**
	 * 
	 * @author shimh
	 * @date 2014-9-25
	 * @param contentId
	 * @param toUsingFlag
	 * @throws Exception
	 */
	public void usingInfoTcontent(String contentId, String toUsingFlag) throws Exception;
	
	/**
	 * 批量启用或禁用发布内容
	 * @author shimh
	 * @date 2014-9-25
	 * @param contentIds
	 * @param toUsingFlag
	 * @throws Exception
	 */
	public void batchEnableInfoTcontent(String contentIds,String toUsingFlag)throws Exception;
	
	/**
	 * 通过主键彻底删除发布内容
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentId
	 * @throws Exception
	 */
	public void shiftDeleteInfoTcontent(String contentId)throws Exception;
	
	/**
	 * 通过主键还原已删除的发布内容并且设为禁用
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentId
	 * @throws Exception
	 */
	public void restoreInfoTcontent(String contentId)throws Exception;
	
	/**
	 * 从回收站中批量还原
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentIds
	 * @throws Exception
	 */
	public void batchRestoreInfoContent(String contentIds)throws Exception;
	
	/**
	 * 清空回收站
	 * @author shimh
	 * @date 2014-9-26
	 * @throws Exception
	 */
	public void emptyRecycleInfoTcontent()throws Exception;
	
	/**
	 * 保存内容发布审核信息
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentId
	 * @param checkFlag
	 * @param checkOpinion
	 * @param userId
	 * @throws Exception
	 */
	public void addOrUpdateAuditInfoContent(String contentId,String checkFlag,
			String checkOpinion,String userId)throws Exception;
	
	/**
	 * 批量审核通过、不通过
	 * @author shimh
	 * @date 2014-9-26
	 * @param contentIds
	 * @param checkFlag
	 * @param userId
	 * @throws Exception
	 */
	public void batchAuditInfoTcontent(String contentIds, String checkFlag,
			String userId)throws Exception;
	
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
	public ResultPage getInfoTcontentPage(String columnId, String areaId,int page,int pageRows) throws Exception;
	
	public List<InfoTcontent> getInfoTcontentPage(List<String> tcs, String areaId) throws Exception;
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
	public ResultPage queryInfoTcontentList(String delFlag,String areaId,String columnId,
			int page,int pageRows)throws Exception;
	
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
	 * 保存或修改新闻
	 * @author liulx
	 * @date 2014-11-20
	 * @param infoTcontent
	 * @throws Exception 
	 */
	public void saveOrUpdateInfoTcontent(InfoTcontent infoTcontent) throws Exception;
	
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
	 * @return：检索结果JSON
	 * @throws Exception
	 * @createDate 2014-12-11
	 * @author Liangmh
	 */
	public String getMapLocalSearch(String themeId, String areaId, int page,
			int pageRows, String name) throws Exception ;
	
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
	public String getMapLocalSearch(String themeId, String areaId, String name) throws Exception ;
	
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
	* @Description:分布全局搜索
	* @author:	yaozhiyuan 
	* @date:	2015年12月14日 
	* @throws
    */
	public ResultPage getInfoTcontentBySearch(InfoTcontent infoTcontent,int page,int pageRows) throws Exception;
	
	void  saveOrUpdateRedPacket(RedPacket packet) throws  Exception; //保存或更新红包

	void delAllRedPacket(String houseId) throws Exception; //根据户型id删除所有红包
}
