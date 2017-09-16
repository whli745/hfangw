package pt.wzgl.pzgl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.wzgl.pzgl.pojo.NetUser;
import pt.wzgl.pzgl.pojo.NetUserCertificate;
import pt.wzgl.pzgl.pojo.vo.VCertificationCheckStauts;
import pt.wzgl.pzgl.pojo.vo.VNetUserCertificate;
import pt.wzgl.pzgl.service.INetUserCertificateService;
import pt.wzgl.pzgl.service.INetUserService;
import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;
/**
 * 在线咨询投诉管理 action
 * @author zhangh
 * @since 2014-9-23 
 */
public class NetUserAction extends BaseAction{
	
	private static final long serialVersionUID = -145706092565771021L;

	/**
	 * 分页
	 */
	private ResultPage resultPage;
	
	/**
	 * 注册用户列表
	 */
	private List<NetUser> netUserList;
	
	/**
	 * 注册用户
	 */
	private NetUser netUser;
	
	/**
	 * 注册用户Service
	 */
	private INetUserService netUserService;
	
	/**
	 * 需要重置密码的用户ID集合 
	 */
	private String[] netUserIds;
	
	/**
	 * 用户ID集合
	 */
	private String ids;
	/**
	 * 检测用户是否有需要审核的证件
	 */
	private List<VCertificationCheckStauts> checkStauts;
	/**
	 * 用户证件列表
	 */
	private List<VNetUserCertificate> certificates;
	/**
	 * 用户证件ID
	 */
	private String ucId;
	/**
	 * 审核意见类型
	 */
	private String checkType;
	/**
	 * 审核意见
	 */
	private String checkContent;
	/**
	 * 部门Service
	 */
	private ISysOrganService sysOrganService;
	/**
	 * 部门集合 
	 */
	private List<SysOrgan> organs;
	/**
	 * 存储部门
	 */
	private Map<String, String> organMap;
	/**
	 * 用户证件对象
	 */
	private NetUserCertificate userCertificate;
	
	private SysArea sysArea;
	
	private ISysAreaService sysAreaService;
	private String areaId;
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登陆用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	
	/**
	 * 当前登陆用户部门id
	 */
	private String loginUserOrgId;
	private INetUserCertificateService netUserCertificateService;//用户证件业务逻辑处理层
	
	/**
	 * 加载注册用户列表
	 * @return
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	@SuppressWarnings("unchecked")
	public String initNetUserManager(){
		try {
			resultPage = netUserService.findNetUsers(getPage(), getPageRows(),netUser);
			if (null != resultPage) {
				super.setPageParam(resultPage);
				netUserList = resultPage.getResultList();
				checkStauts = new ArrayList<VCertificationCheckStauts>();
				VCertificationCheckStauts v = null;
				List<NetUserCertificate> list = null;
				for (int i = 0; i < netUserList.size(); i++) {
					v = new VCertificationCheckStauts();
					v.setUserId(netUserList.get(i).getId());
					list = netUserCertificateService.getUserCertificationByUser(netUserList.get(i).getId());
					if( list != null && list.size() > 0 ){
						v.setIsHaveCheck("yes");//有需要审核的证件
					}
					checkStauts.add(v);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 密码重置
	 * @return
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	public void resetPassword(){
		try {
			NetUser user = null;
			String password = "123";//重置的默认密码为 : 123
			netUserIds = ids.split(",");
			for (int i = 0; i < netUserIds.length; i++) {
				if(!Common.isNullOrSpace(netUserIds[i])){
					user = netUserService.getNetUserById(netUserIds[i]);
					user.setUserPass(Common.md5(password));
					netUserService.updateNetUser(user);
				}
			}
			returnAjaxInfo("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除用户(逻辑删除)
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	public void deleteNetUser(){
		try {
			NetUser user = null;
			netUserIds = ids.split(",");
			for (int i = 0; i < netUserIds.length; i++) {
				if(!Common.isNullOrSpace(netUserIds[i])){
					user = netUserService.getNetUserById(netUserIds[i]);
					user.setUserDelStatus( BaseParameter.YES );//设置为已删除
					netUserService.updateNetUser(user);
				}
			}
			returnAjaxInfo("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看用户详细
	 * @return
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	public String selectDetailInfo(){
		try {
			netUser = netUserService.getNetUserById(ids);
			certificates = netUserCertificateService.getCertificationByUser(ids);
			if (!Common.isNullOrSpace(netUser.getUserCurrentArea())) {
				sysArea = sysAreaService.getSysAreaBytopId(netUser.getUserCurrentArea());
				
				if (sysArea == null) {
					sysArea = new SysArea();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( BaseParameter.MENU_OWNER_USER.equals(netUser.getUserType())){
			return "personal";
		}else{
			return "company";
		}
	}
	
	/**
	 * 查看用户需要审核的证件 
	 * @return
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	public String selectDetailInfoForCertification(){
		try {
			netUser = netUserService.getNetUserById(ids);
			certificates = netUserCertificateService.getCertificationByUser(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( BaseParameter.MENU_OWNER_USER.equals(netUser.getUserType())){
			return "personal";
		}else{
			return "company";
		}
	}
	
	/**
	 * 开启审核窗口 
	 * @return
	 * @createDate 2014-11-28
	 * @author Liangmh
	 */
	public String openAuditPage(){
		try {
			netUser = netUserService.getNetUserById(ids);
			 userCertificate = netUserCertificateService.getCertificationImage(ucId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 执行用户证件审核
	 * @return
	 * @createDate 2014-11-22
	 * @author Liangmh
	 */
	public void doAuditCertifiction(){
		try {
			String status = "1";//审核的时候始终标记为已审核
			if( !Common.isNullOrSpace(checkType) && checkType.equals("noPass")){
				status = "2";
			}
			netUserCertificateService.doAuditCertification(ucId, status,checkContent);
			netUser = netUserService.getNetUserById(ids);
			certificates = netUserCertificateService.getCertificationByUser(ids);
			returnAjaxInfo("success");
		} catch (Exception e) {
			try {
				returnAjaxInfo("error");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 跳转到用户证件审核
	 * @return
	 * @createDate 2014-11-22
	 * @author Liangmh
	 */
	public String netCompanyDetailForAudit(){
		return SUCCESS;
	}
	

	/**
	 * 获取分页  
	 * @return resultPage 分页  
	 */
	public ResultPage getResultPage() {
		return resultPage;
	}

	/**
	 * 设置分页
	 * @param resultPage 分页  
	 */
	public void setResultPage(ResultPage resultPage) {
		this.resultPage = resultPage;
	}
	
	/**
	 * 获取用户列表
	 * @return netUserList 注册用户列表
	 */
	public List<NetUser> getNetUserList() {
		return netUserList;
	}
	
	/**
	 * 设置注册用户列表
	 * @param netUserList 注册用户列表
	 */
	public void setNetUserList(List<NetUser> netUserList) {
		this.netUserList = netUserList;
	}
	
	/**
	 * 获取注册用户Service
	 * @return netUserService 注册用户Service
	 */
	public INetUserService getNetUserService() {
		return netUserService;
	}
	
	/**
	 * 设置注册用户Service
	 * @param netUserService 注册用户Service
	 */
	public void setNetUserService(INetUserService netUserService) {
		this.netUserService = netUserService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public NetUser getNetUser() {
		return netUser;
	}

	public void setNetUser(NetUser netUser) {
		this.netUser = netUser;
	}

	/**
	 * @return the 需要重置密码的用户ID集合
	 */
	public String[] getNetUserIds() {
		return netUserIds;
	}

	/**
	 * @param 需要重置密码的用户ID集合 the netUserIds to set
	 */
	public void setNetUserIds(String[] netUserIds) {
		this.netUserIds = netUserIds;
	}

	public List<VCertificationCheckStauts> getCheckStauts() {
		return checkStauts;
	}

	public void setCheckStauts(List<VCertificationCheckStauts> checkStauts) {
		this.checkStauts = checkStauts;
	}

	public INetUserCertificateService getNetUserCertificateService() {
		return netUserCertificateService;
	}

	public void setNetUserCertificateService(
			INetUserCertificateService netUserCertificateService) {
		this.netUserCertificateService = netUserCertificateService;
	}

	public List<VNetUserCertificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<VNetUserCertificate> certificates) {
		this.certificates = certificates;
	}

	public String getUcId() {
		return ucId;
	}

	public void setUcId(String ucId) {
		this.ucId = ucId;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public ISysOrganService getSysOrganService() {
		return sysOrganService;
	}

	public void setSysOrganService(ISysOrganService sysOrganService) {
		this.sysOrganService = sysOrganService;
	}

	public List<SysOrgan> getOrgans() {
		return organs;
	}

	public void setOrgans(List<SysOrgan> organs) {
		this.organs = organs;
	}


	public Map<String, String> getOrganMap() {
		return organMap;
	}

	public void setOrganMap(Map<String, String> organMap) {
		this.organMap = organMap;
	}

	public NetUserCertificate getUserCertificate() {
		return userCertificate;
	}

	public void setUserCertificate(NetUserCertificate userCertificate) {
		this.userCertificate = userCertificate;
	}

	/**
	 * @return the sysArea
	 */
	public SysArea getSysArea() {
		return sysArea;
	}

	/**
	 * @param sysArea the sysArea to set
	 */
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}

	/**
	 * @return the sysAreaService
	 */
	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	/**
	 * @param sysAreaService the sysAreaService to set
	 */
	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	/**
	 * @return the 当前登陆用户部门id
	 */
	public String getLoginUserOrgId() {
		return loginUserOrgId;
	}

	/**
	 * @param 当前登陆用户部门id the loginUserOrgId to set
	 */
	public void setLoginUserOrgId(String loginUserOrgId) {
		this.loginUserOrgId = loginUserOrgId;
	}
	
	/**
	 * 获取areaId  
	 * @return areaId areaId  
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 设置areaId
	 * @param areaId areaId  
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取areaName  
	 * @return areaName areaName  
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置areaName
	 * @param areaName areaName  
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 获取topAreaId  
	 * @return topAreaId topAreaId  
	 */
	public String getTopAreaId() {
		return topAreaId;
	}

	/**
	 * 设置topAreaId
	 * @param topAreaId topAreaId  
	 */
	public void setTopAreaId(String topAreaId) {
		this.topAreaId = topAreaId;
	}

	/**
	 * 获取sysAreaJson  
	 * @return sysAreaJson sysAreaJson  
	 */
	public String getSysAreaJson() {
		return sysAreaJson;
	}

	/**
	 * 设置sysAreaJson
	 * @param sysAreaJson sysAreaJson  
	 */
	public void setSysAreaJson(String sysAreaJson) {
		this.sysAreaJson = sysAreaJson;
	}

	/**
	 * 获取serialversionuid  
	 * @return serialversionuid serialversionuid  
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
