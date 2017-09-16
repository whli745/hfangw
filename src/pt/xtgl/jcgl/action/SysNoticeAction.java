package pt.xtgl.jcgl.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysNotice;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import pt.xtgl.jcgl.service.ISysNoticeService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 公告管理
 * 
 * @author wanghw
 * @modify meidj 2013-06-06 增加多级区划、业务类型
 * @modify meidj 2013-06-25 新增TOP系统公告查询
 */
@SuppressWarnings( { "unchecked", "serial" })
public class SysNoticeAction extends BaseAction {
	private ISysNoticeService sysNoticeService;
	private ISysDictService sysDictService;// 数据字典service
	private ISysAreaService sysAreaService;
	private SysNotice sysNotice;
	private List<SysNotice> sysNoticeList;
	private String noticeId;// 公告编号Id
	private String noticeTitle;// 公告标题
	private String noticeMemo;// 公告内容
	private String noticeAtta;// 公告附件
	private String noticeDateType;// 公告时间类型 1指有时间限制 2指无时间限制 默认为1
	private Date noticeEndDate;// 公告失效日期
	private String noticeType;// 公告类型 内外网
	private String delFlag;// 删除状态 默认未删除
	private String userId;// 发布公告人的用户Id
	private Date insertDate;// 发布公告的时间
	private List<SysDict> paramServiceTypeList;// 业务类型list
	private File noticefile;
	private String noticefileFileName;
	private String dictName;// 系统字典名称
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private String serviceType;// 业务类型

	/**
	 * 提交增加或修改公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String editSysNotice() {
		try {
			if (null != sysNotice) {
				SysUser user = super.getLoginUser();
				sysNotice.setUserId(user.getUserId());
				sysNoticeService.saveOrUpdateSysNotice(sysNotice,
						noticefileFileName, noticefile);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 增加更新公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String initSysNotice() {
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
			if (null != noticeId) {
				sysNotice = sysNoticeService.querySysNotice(noticeId);
			}
			paramServiceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询公告信息 add by wanghw date 2013-04-03
	 * 
	 * @return
	 */
	public String querySysNotice() {
		try {
			if (noticeId != null) {
				sysNotice = sysNoticeService.querySysNotice(noticeId);
			}
		} catch (Exception e) {
			error(null, e);
		}

		return SUCCESS;
	}

	/**
	 * 显示公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String getSysNoticeInfo() {
		try {
			if (noticeId != null) {
				sysNotice = sysNoticeService.querySysNotice(noticeId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询所有的公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String querySysNoticeList() {
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
			paramServiceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
			ResultPage rs = sysNoticeService.querySysNotice(noticeTitle,
					noticeType, areaId, serviceType, getPage(), getPageRows());
			super.setPageParam(rs);
			sysNoticeList = rs.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	/**
	 * 查询所有的公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String querySysNoticeListgzt() {
		try {
			paramServiceTypeList = sysDictService
			.querySysDictList(BaseParameter.SERVICETYPE);
			ResultPage rs = sysNoticeService.querySysNotice(noticeTitle,
					noticeType, areaId, serviceType, getPage(), BaseParameter.PAGE_ROWS_MIN);
			super.setPageParam(rs);
			sysNoticeList = rs.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * TOP进入查看系统公告框架页面（IE自带弹出窗口，页面操作必须用IFRAME嵌套才能操作）
	 * 
	 * @author meidj 2013-06-25
	 */
	public String sysTOPNoticeInto() {
		return SUCCESS;
	}

	/**
	 * TOP系统公告查询
	 * 
	 * @author meidj 2013-06-25
	 */
	public String queryTOPSysNoticeList() {
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
			ResultPage rs = sysNoticeService.querySysNotice(noticeTitle,
					areaId, getPage(), getPageRows()); // 每页显示10条
			super.setPageParam(rs);
			sysNoticeList = rs.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * TOP公告信息查看
	 * 
	 * @author meidj 2013-06-25
	 */
	public String getSysTOPNoticeInfo() {
		try {
			if (noticeId != null) {
				sysNotice = sysNoticeService.querySysNotice(noticeId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除公告信息 add by wanghw date 2013-04-03
	 * 
	 */
	public String deleteSysNotice() {
		try {
			if (null != noticeId) {
				sysNoticeService.deleteSysNotice(noticeId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 功能：获取字典名称
	 * 
	 * @author:xuhw
	 * @Email: xuhaiwei@zhuofansoft.com
	 * @date: 2013-5-9
	 * @param dictId
	 * @modify：
	 */
	public String getDictName(String dictId) {
		try {
			dictName = sysDictService.querySysDict(dictId).getDictName();
		} catch (Exception e) {
			error(null, e);
		}

		return dictName;
	}

	public SysNotice getSysNotice() {
		return sysNotice;
	}

	public void setSysNotice(SysNotice sysNotice) {
		this.sysNotice = sysNotice;
	}

	public ISysNoticeService getSysNoticeService() {
		return sysNoticeService;
	}

	public void setSysNoticeService(ISysNoticeService sysNoticeService) {
		this.sysNoticeService = sysNoticeService;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeMemo() {
		return noticeMemo;
	}

	public void setNoticeMemo(String noticeMemo) {
		this.noticeMemo = noticeMemo;
	}

	public String getNoticeAtta() {
		return noticeAtta;
	}

	public void setNoticeAtta(String noticeAtta) {
		this.noticeAtta = noticeAtta;
	}

	public String getNoticeDateType() {
		return noticeDateType;
	}

	public void setNoticeDateType(String noticeDateType) {
		this.noticeDateType = noticeDateType;
	}

	public Date getNoticeEndDate() {
		return noticeEndDate;
	}

	public void setNoticeEndDate(Date noticeEndDate) {
		this.noticeEndDate = noticeEndDate;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public List<SysNotice> getSysNoticeList() {
		return sysNoticeList;
	}

	public void setSysNoticeList(List<SysNotice> sysNoticeList) {
		this.sysNoticeList = sysNoticeList;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public List<SysDict> getParamServiceTypeList() {
		return paramServiceTypeList;
	}

	public void setParamServiceTypeList(List<SysDict> paramServiceTypeList) {
		this.paramServiceTypeList = paramServiceTypeList;
	}

	public File getNoticefile() {
		return noticefile;
	}

	public void setNoticefile(File noticefile) {
		this.noticefile = noticefile;
	}

	public String getNoticefileFileName() {
		return noticefileFileName;
	}

	public void setNoticefileFileName(String noticefileFileName) {
		this.noticefileFileName = noticefileFileName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
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

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}
