package pt.xtgl.jcgl.dao;

import pt.xtgl.jcgl.pojo.SysNotice;
import util.ResultPage;

public interface ISysNoticeDao {
	/**
	 * 保存公告信息 add by wanghw date 2013-04-03
	 */
	public void saveSysNotice(SysNotice sysNotice) throws Exception;

	/**
	 * 删除公告信息 add by wanghw date 2013-04-03
	 * 
	 * @param sysNotice
	 *            公告对象
	 * @throws Exception
	 */
	public void deleteSysNotice(SysNotice sysNotice) throws Exception;

	/**
	 * 修改公告信息 add by wanghw date 2013-04-03
	 * 
	 * @param sysNotice
	 *            公告对象
	 * @throws Exception
	 */
	public void updateSysNotice(SysNotice sysNotice) throws Exception;

	/**
	 * 查找公告信息 add by wanghw date 2013-04-03
	 * 
	 * @param noticeId
	 *            公告对象Id
	 * @return
	 * @throws Exception
	 */
	public SysNotice querySysNotice(String noticeId) throws Exception;

	/**
	 * 查询所有的公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param noticeTitle
	 *            公告对象标题
	 * @param noticeType
	 *            公告对象类型
	 * @param areaId
	 *            所属区划
	 * @param serviceType
	 *            所属业务类型
	 * @param page
	 *            列表的页数
	 * @param pageRows
	 *            列表显示的最大行数
	 * @return
	 * @throws Exception
	 */
	public ResultPage querySysNoticeList(String noticeTitle, String noticeType,
			String areaId, String serviceType, int page, int pageRows)
			throws Exception;

	/**
	 * TOP系统公告
	 * 
	 * @param noticeTitle
	 *            公告标题
	 * @param areaId
	 *            所属区划
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页条数
	 * @return ResultPage
	 * @throws Exception
	 */
	public ResultPage querySysNoticeList(String noticeTitle, String areaId,
			int page, int pageRows) throws Exception;

	/**
	 * 修改或新增公告信息
	 * 
	 * @author wanghw
	 * @date 2013-4-21
	 * @param sysNotice
	 *            公告对象
	 * @throws Exception
	 *             抛出异常信息
	 */
	public void saveOrUpdateSysNotice(SysNotice sysNotice) throws Exception;
	
	/**
	 * 
	  * 
	  * 功能：政府采购下面公告查询，（按照标题进行查询）
	  * @author:xuhw
	  * @Email: xuhaiwei@zhuofansoft.com
	  * @date:  2013-6-25
	  * @return
	  * @modify：
	    @param noticeTitle 公告标题
	    @param areaId 区划id
	    @param serviceType 业务类型
	    @param page
	    @param pageRows
	    @return
	    @throws Exception
	 */
	public ResultPage queryNoticeList(String noticeTitle,String areaId, String serviceType, int page, int pageRows)
	throws Exception;
	
	/**
	 * 
	  * 
	  * 功能：建设工程下面公告查询，（按照标题进行查询）
	  * @author:xuhw
	  * @Email: xuhaiwei@zhuofansoft.com
	  * @date:  2013-6-25
	  * @return
	  * @modify：
	    @param noticeTitle 公告标题
	    @param areaId 区划id
	    @param serviceType 业务类型
	    @param page
	    @param pageRows
	    @return
	    @throws Exception
	 */
	public ResultPage queryJsNoticeList(String noticeTitle, String areaId,
			String serviceType, int page, int pageRows) throws Exception;
}
