package pt.xtgl.jcgl.dao;

import java.util.Date;

import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.pojo.Loginfo;
import util.ResultPage;
import util.base.dao.IBaseDao;

public interface ILogDao extends IBaseDao<Loginfo> {

	// 功能描述列表
	public ResultPage getLogActionDescPages(String actionUrl,
			String actionDesc, int page, int pageRows) throws Exception;

	// 查询功能描述对象
	public LogActionDesc getLogActionDescByUrl(String actionUrl)
			throws Exception;

	// 查询功能描述对象
	public LogActionDesc getLogActionDescByOid(String oid) throws Exception;

	/**
	 * 查看日志信息
	 * 
	 * @param discode
	 *            区划代码
	 * @param type
	 *            日志内容
	 * @param userName
	 *            日志操作人
	 * @param actText
	 *            日志内容
	 * @param actTimeFrom
	 *            日志开始时间
	 * @param actTimeTo
	 *            日志结束时间
	 * @param ip
	 *            访问地址
	 * @return
	 * @throws Exception
	 */
	public ResultPage getLogInfoList(String discode, Integer type,
			String userName, String actText, String actTimeFrom,
			String actTimeTo, String ip, int page, int pagerows)
			throws Exception;

	// 对象操作
	public void insertObject(Loginfo loginfo) throws Exception;

	/**
	 * 删除日志
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param loginfo日志对象
	 * @throws Exception
	 */
	public void deleteLogInfo(Loginfo loginfo) throws Exception;

	/**
	 * 通过Oid查询日志信息
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param oid日志信息Id
	 * @return
	 * @throws Exception
	 */
	public Loginfo queryLoginfoByOid(String oid) throws Exception;

	/**
	 * 查询操作日志列表
	 * 
	 * @param areaId
	 *            所属区划
	 * 
	 * @param serviceType
	 *            所属业务类型
	 * @param roleId
	 *            所属角色
	 * @param userName
	 *            操作人名称
	 * @param startDate
	 *            查询日志开始时间
	 * @param endDate
	 *            查询日志结束时间
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页行数
	 * @return ResultPage
	 * @throws Exception
	 */
	public ResultPage queryLogInfoList(String areaId, String roleId,
			String userName, Date startDate, Date endDate, int page,
			int pageRows) throws Exception;

	/**
	 * 查询操作日志列表
	 * 
	 * @param areaId
	 *            所属区划
	 * 
	 * @param serviceType
	 *            所属业务类型
	 * @param roleId
	 *            所属角色
	 * @param userName
	 *            操作人名称
	 * @param startDate
	 *            查询日志开始时间
	 * @param endDate
	 *            查询日志结束时间
	 */
	public void clearLog(String areaId, String roleId, String userName,
			Date startDate, Date endDate) throws Exception;
}
