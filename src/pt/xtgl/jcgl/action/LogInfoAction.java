package pt.xtgl.jcgl.action;

import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.pojo.Loginfo;
import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ILogActionDescService;
import pt.xtgl.jcgl.service.ILogService;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 系统日志管理
 * 
 * @author wanghw
 * @modify meidj 2013-06-08 添加多级区划支持和日志清理功能
 * 
 */
@SuppressWarnings("serial")
public class LogInfoAction extends BaseAction {
	private ILogActionDescService logActionDescService;
	private ILogService logService;
	private ISysAreaService sysAreaService;
	private ISysDictService sysDictService;// 数据字典service
	private LogActionDesc logActionDesc;
	private List<LogActionDesc> logActionDescList;
	private List<Loginfo> logInfoList;
	private List<SysDict> serviceTypeList;
	private LogActionDesc sqlObj;
	private Loginfo logInfo;
	private String oid;
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private String serviceType;
	private String roleId;
	private String userName;
	private Date startDate;
	private Date endDate;

	/**
	 * 修改或添加系统功能描述对象
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	public String initLogActionDesc() {
		try {
			if (oid != null) {
				logActionDesc = logActionDescService.queryLogActionDesc(oid);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 提交修改或添加系统功能描述信息
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	public String editLogActionDesc() {
		try {
			logActionDescService.saveLogActionDesc(logActionDesc);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除系统功能描述信息
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	public String deleteLogActionDesc() {
		try {
			logActionDesc = logActionDescService.queryLogActionDesc(oid);
			logActionDescService.deleteLogActionDesc(logActionDesc);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询系统功能描述列表
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryLogActionDescList() {
		try {
			ResultPage rp = logActionDescService.queryLogActionDescList(sqlObj,
					getPage(), BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			logActionDescList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询日志信息列表
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryLogInfoList() {
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
			ResultPage rp = logService.queryLogInfoList(areaId, 
					roleId, userName, startDate, endDate, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			logInfoList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 日志清理
	 * 
	 * @author meidj 2013-06-08
	 */
	public String clearSysLog() {
		try {
			logService.clearLog(areaId,  roleId, userName,
					startDate, endDate);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除日志信息
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @return
	 */
	public String deleteLogInfo() {
		try {
			logInfo = logService.queryLoginfoByOid(oid);
			logService.deleteLogInfo(logInfo);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查看日志信息
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @modi zhujj
	 */
	public String queryLogInfo() {
		try {
			if (oid != null) {
				logInfo = logService.queryLoginfoByOid(oid);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public List<LogActionDesc> getLogActionDescList() {
		return logActionDescList;
	}

	public void setLogActionDescList(List<LogActionDesc> logActionDescList) {
		this.logActionDescList = logActionDescList;
	}

	public LogActionDesc getLogActionDesc() {
		return logActionDesc;
	}

	public void setLogActionDesc(LogActionDesc logActionDesc) {
		this.logActionDesc = logActionDesc;
	}

	public LogActionDesc getSqlObj() {
		return sqlObj;
	}

	public void setSqlObj(LogActionDesc sqlObj) {
		this.sqlObj = sqlObj;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public ILogActionDescService getLogActionDescService() {
		return logActionDescService;
	}

	public void setLogActionDescService(
			ILogActionDescService logActionDescService) {
		this.logActionDescService = logActionDescService;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public List<Loginfo> getLogInfoList() {
		return logInfoList;
	}

	public void setLogInfoList(List<Loginfo> logInfoList) {
		this.logInfoList = logInfoList;
	}

	public Loginfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(Loginfo logInfo) {
		this.logInfo = logInfo;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public List<SysDict> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<SysDict> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTopAreaId() {
		return topAreaId;
	}

	public void setTopAreaId(String topAreaId) {
		this.topAreaId = topAreaId;
	}

	public String getSysAreaJson() {
		return sysAreaJson;
	}

	public void setSysAreaJson(String sysAreaJson) {
		this.sysAreaJson = sysAreaJson;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
