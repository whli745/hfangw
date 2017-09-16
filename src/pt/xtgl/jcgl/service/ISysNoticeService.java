package pt.xtgl.jcgl.service;

import java.io.File;

import pt.xtgl.jcgl.pojo.SysNotice;
import util.ResultPage;

public interface ISysNoticeService {

	/**
	 * 保存公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param sysNotice
	 *            公告对象
	 * @throws Exception
	 *             异常信息
	 */
	public void saveSysNotice(SysNotice sysNotice) throws Exception;

	/**
	 * 删除公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param noticeId
	 *            公告Id
	 * @throws Exception
	 *             异常信息
	 */
	public void deleteSysNotice(String noticeId) throws Exception;

	/**
	 * 修改公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param sysNotice
	 *            公告对象
	 * @throws Exception
	 *             异常信息
	 */
	public void updateSysNotice(SysNotice sysNotice) throws Exception;

	/**
	 * 查询公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param noticeId
	 *            公告的Id
	 * @return
	 * @throws Exception
	 *             抛出异常信息
	 */
	public SysNotice querySysNotice(String noticeId) throws Exception;

	/**
	 * 查询所有的公告信息 add by wanghw date 2013-04-07
	 * 
	 * @param noticeTitle
	 *            公告的标题
	 * @param noticeType
	 *            公告的类型
	 * @param areaId
	 *            所属区划
	 * @param serviceType
	 *            所属业务类型
	 * @param page
	 *            返回list列表的页数
	 * @param pageRows
	 *            返回list列表的行数
	 * @return ResultPage
	 * @throws Exception
	 *             抛出异常
	 */
	public ResultPage querySysNotice(String noticeTitle, String noticeType,
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
	public ResultPage querySysNotice(String noticeTitle, String areaId,
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
	public void saveOrUpdateSysNotice(SysNotice sysNotice,
			String noticefileFileName, File noticefile) throws Exception;
	

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
