package pt.wzgl.nrgl.action;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lucene.LuceneUtil;
import net.sf.json.JSONObject;
import pt.wzgl.nrgl.pojo.FileAtta;
import pt.wzgl.nrgl.pojo.InfoTcategory;
import pt.wzgl.nrgl.pojo.InfoTcontent;
import pt.wzgl.nrgl.service.IFileAttaService;
import pt.wzgl.nrgl.service.IInfoTcategoryService;
import pt.wzgl.nrgl.service.IInfoTcontentService;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;
import web.pojo.RedPacket;

/**
 * 栏目信息管理 action
 * @author Administrator
 *
 */
public class ContentManagerAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IInfoTcategoryService infoTcategoryService;//栏目信息管理service
	private InfoTcategory infoTcategory;//栏目信息管理实体
	private List<InfoTcategory> infoTcategoryList;//栏目信息list
	private String columnId;//栏目信息主键
	private Integer rank;//栏目信息级别
	private String toUsingFlag;//栏目信息是否启用标志
	private String parentName;//父级name
	private String parentId;//父级id
	private String infoTcategoryJson;//栏目的Json对象
	/** 索引器 */
	private LuceneUtil luceneUtil;
	
	private ISysAreaService sysAreaService;//区划service
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象

	private IInfoTcontentService infoTcontentService;//内容发布信息管理service
	private String contentMainTitle;//内容发布主标题
	private List<InfoTcontent> infoTcontentList;//内容发布信息list
	private String columnName;//栏目名称
	private String contentId;//内容发布id
	private InfoTcontent infoTcontent = new InfoTcontent();// 内容发布对象
	private IFileAttaService fileAttaService;//内容发布上传附件对象
	private File atta;// 导入附件
	private String attaFileName;// 导入附件名称
	private String usingFlag;//批量启用、禁用或删除
	private String checkFlag;//审核结果
	private String checkOpinion;//审核意见
	private String districtId;//区划id 
	private Integer nowCount;// 当前条数
	private int nowPage;// 当前页
	private String jumpUrl;	// 要跳转到的网页地址
	
	private List<RedPacket> packetList;  //红包信息list
	
	/** 页面跳转类型 */
	private String pageType;
	
	/**
	 * 创建索引Action
	 * @createDate 2016-1-5 下午2:45:07 
	 * @author Liangmh
	 */
	public void createLucene(){
		try {
			//生成新闻发布索引
			luceneUtil.generateWebNewsInfoIndex(getRequest());
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * ajax 删除信息
	 * @author shimh
	 * @date 2014-9-24
	 */
	public void delWebColumnAjax(){
		try {
			infoTcategoryService.delInfoTcategory(columnId);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * ajax添加修改栏目目录
	 * @author shimh
	 * @date 2014-9-24
	 */
	public void editInfoTcategoryAjax(){
		try {
			if(infoTcategory!=null){
				infoTcategoryService.saveOrUpdateInfoTcategory(infoTcategory, super.getLoginUser().getUserId());
				super.returnAjaxInfo(infoTcategory.getColumnId());
			}
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * ajax查询数据字典目录并转换成JSON对象传输到前台
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	public String getJSONInfoTcategory(){
		try {
			InfoTcategory infoTcategory=infoTcategoryService.initInfoTcategory(columnId);
			//将javaBean转换成JSON对象
			JSONObject jInfoTcategory=JSONObject.fromObject(infoTcategory);
			super.returnAjaxInfo(jInfoTcategory.toString());
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}
	
	/**
	 * 异步树加载
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	public String getInfoTcategoryTreeAjax(){
		try {
			String JSONStr=infoTcategoryService.getInfoTcategoryLeftListByPid(parentId);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}
	
	/**
	 * 栏目管理页面
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	public String showInfoTcategoryList(){
		try {
			infoTcategoryJson=infoTcategoryService.getInfoTcategoryLeftListByPid(parentId);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询栏目信息list
	 * @author shimh
	 * @date 2014-9-23
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryInfoTcategoryList(){
		try {
			if(infoTcategory == null) infoTcategory = new InfoTcategory();
			if(infoTcategory.getRank() == null) infoTcategory.setRank(1);
			if(Common.isNullOrSpace(infoTcategory.getParentId())) infoTcategory.setParentId("-1");
			if(rank == null) rank = 1;
			//得到栏目对象
			parentName = infoTcategoryService.getParentNameByParentId(infoTcategory.getParentId());
			
			ResultPage rp =	infoTcategoryService.queryInfoTcategoryList(infoTcategory, 
					getPage(), getPageRows());
			infoTcategoryList = rp.getResultList();
			super.setPageParam(rp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化添加或修改栏目信息页面
	 * @author shimh
	 * @date 2014-9-23
	 * @return
	 */
	public String initInfoTcategory(){
		try {
			infoTcategory = infoTcategoryService.initInfoTcategory(columnId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 保存添加或修改的栏目信息
	 * @author shimh
	 * @date 2014-9-23
	 * @return
	 */
	public String saveInfoTcategory(){
		try {
			infoTcategoryService.saveInfoTcategory(infoTcategory,super.getLoginUser().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改栏目是否启用状态
	 * @author shimh
	 * @date 2014-9-23
	 * @return
	 */
	public String usingInfoTcategory(){
		try {
			infoTcategoryService.usingInfoTcategory(columnId,toUsingFlag);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除栏目
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	public void delInfoTcategoryAjax() {
		try {
			infoTcategoryService.delInfoTcategory(columnId);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * 查询内容发布信息list
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryInfoTcontentList(){
		
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				if (null == user.getSysArea()) {
					areaName = sysAreaService.getSysAreaTop().getAreaName();
				} else {
					areaName = user.getSysArea().getAreaName();
				}
			} else {
				areaName = sysAreaService.getSysAreaBytopId(areaId)
						.getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			
			if(Common.isNullOrSpace(columnId))
				columnId = "-1";
			if(Common.isNullOrSpace(infoTcontent.getColumnId()))
				infoTcontent.setColumnId(columnId);
			infoTcategoryList = infoTcategoryService.queryInfoTcategoryListByParentId(columnId);
			
			ResultPage rp = infoTcontentService.queryInfoTcontentList(BaseParameter.NO,BaseParameter.MRXJQH,areaId,
					contentMainTitle,infoTcontent, getPage(), getPageRows());
			infoTcontentList = rp.getResultList();//内容发布信息list
			super.setPageParam(rp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 内容发布页面
	 * @author shimh
	 * @date 2014-9-24
	 * @return
	 */
	public String initInfoTcontent(){
		try {
			if(BaseParameter.YES.equals(BaseParameter.LMSMRZK)){
				infoTcategoryJson = infoTcategoryService.infoTcategoryJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化添加或删除内容发布页面
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String editInfoTcontent(){
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				if (null == user.getSysArea()) {
					areaName = sysAreaService.getSysAreaTop().getAreaName();
				} else {
					areaName = user.getSysArea().getAreaName();
				}
			} else {
				areaName = sysAreaService.getSysAreaBytopId(areaId)
						.getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			
			if (!Common.isNullOrSpace(contentId)) {
				infoTcontent = infoTcontentService.getInfoTcontent(contentId);
			}
			if(!Common.isNullOrSpace(columnId)){
				infoTcontent.setInfoTcategory(this.infoTcategoryService.initInfoTcategory(columnId));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 保存内容发布信息
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String saveOrUpdateInfoTcontent(){
		try {
			//文件上传 并 返回上传 对象 
			FileAtta fileAtta = fileAttaService.uploadFile(atta, attaFileName, "attas/");
			if( infoTcontent != null ){
				if( infoTcontent.getThemeId() == null || infoTcontent.getThemeId().equals("0") ){
					infoTcontent.setThemeId("NULL");
					infoTcontent.setIsMapSearch(BaseParameter.COMMON_NEW);//不是地图检索服务
				}
			}
			infoTcontentService.saveInfoTcontent(infoTcontent, fileAtta, super.getLoginUser().getUserId());
			infoTcontentService.delAllRedPacket(infoTcontent.getContentId());
			//添加红包
			for(int i=0;i<packetList.size();i++){
				RedPacket packet = packetList.get(i);
				if(packet == null || packet.getPacketName()==null || "".equals(packet.getPacketName())) continue;
				
				packet.setOid(null);
				packet.setNews(infoTcontent);
				packet.setIsDel(0);
				packet.setCreateTime(new Date());
				infoTcontentService.saveOrUpdateRedPacket(packet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除对象
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String delInfoTcontent(){
		try {
			infoTcontentService.deleteInfoTcontent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查看发布内容详细页面
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String getInfoTcontentDetial(){
		try {
			infoTcontent = infoTcontentService.getInfoTcontent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改内容发布是否启用
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String usingInfoTcontent(){
		try {
			infoTcontentService.usingInfoTcontent(contentId,toUsingFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 从内容发布管理中批量 启用、禁用状态
	 * @author shimh
	 * @date 2014-9-25
	 * @return
	 */
	public String batchEnableInfoTcontent(){
		try {
			infoTcontentService.batchEnableInfoTcontent(contentId,usingFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化发布内容审核页面
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String initAuditInfoTcontent(){
		try {
			if(BaseParameter.LMSMRZK.equals(BaseParameter.YES)){
				infoTcategoryJson = infoTcategoryService.infoTcategoryJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询回收站的发布内容
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryInfoTcontentRecycleList(){
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				if (null == user.getSysArea()) {
					areaName = sysAreaService.getSysAreaTop().getAreaName();
				} else {
					areaName = user.getSysArea().getAreaName();
				}
			} else {
				areaName = sysAreaService.getSysAreaBytopId(areaId)
						.getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			ResultPage rp = infoTcontentService.queryInfoTcontentList(BaseParameter.YES,BaseParameter.MRXJQH, areaId,
					contentMainTitle, infoTcontent, getPage(), getPageRows());
			infoTcontentList = rp.getResultList();
			super.setPageParam(rp);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 彻底删除发布内容
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String shiftDeleteInfoTcontent(){
		try {
			infoTcontentService.shiftDeleteInfoTcontent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 *从回收站中还原发布内容
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String restoreInfoTcontent(){
		try {
			infoTcontentService.restoreInfoTcontent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 从回收站中批量还原
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String batchRestoreInfoContent(){
		try {
			infoTcontentService.batchRestoreInfoContent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 清空回收站
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String emptyRecycleInfoTcontent(){
		try {
			infoTcontentService.emptyRecycleInfoTcontent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询需要审核的内容发布信息
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryAuditInfoTcontentList(){
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				if (null == user.getSysArea()) {
					areaName = sysAreaService.getSysAreaTop().getAreaName();
				} else {
					areaName = user.getSysArea().getAreaName();
				}
			} else {
				areaName = sysAreaService.getSysAreaBytopId(areaId)
						.getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			
			if(Common.isNullOrSpace(columnId)){
				columnName = "栏目";
			}else{
				if(Common.isNullOrSpace(infoTcontent.getColumnId()))
					infoTcontent.setColumnId(columnId);
				columnName = infoTcategoryService.initInfoTcategory(columnId).getColumnName();
			}
			
			List<String> flags = new ArrayList<String>();
			flags.add(BaseParameter.YES);
			flags.add(BaseParameter.NO);
			infoTcontent.setCheckFlags(flags);
			ResultPage rp = infoTcontentService.queryInfoTcontentList(BaseParameter.NO,BaseParameter.MRXJQH,areaId,
					contentMainTitle,infoTcontent, getPage(), getPageRows());
			infoTcontentList = rp.getResultList();//内容发布信息list
			super.setPageParam(rp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化审核页面
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String auditInfoTcontent(){
		try {
			infoTcontent = infoTcontentService.getInfoTcontent(contentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 保存发布内容审核信息
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public void addOrUpdateAuditInfoContent(){
		try {
			infoTcontentService.addOrUpdateAuditInfoContent(contentId,checkFlag,
					checkOpinion,super.getLoginUser().getUserId());
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量审核通过或不通过操作
	 * @author shimh
	 * @date 2014-9-26
	 * @return
	 */
	public String batchAuditInfoTcontent(){
		try {
			infoTcontentService.batchAuditInfoTcontent(contentId,checkFlag,super.getLoginUser().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @author liulx
	 * @date 2014-10-31
	 * @return
	 */
	public String getcategoryContent() {
		try {
			infoTcontentList = infoTcontentService.getInfoTcontentList(columnId, areaId);
			if (infoTcontentList != null && infoTcontentList.size() == 1 && infoTcontentList.get(0).getJumpUrlFlag().equals(BaseParameter.YES)) {
				jumpUrl = infoTcontentList.get(0).getJumpUrl(); 
				return "jump";
			} else {
				districtId = super.getLoginUser().getAreaId();
				infoTcategoryList = new ArrayList<InfoTcategory>();
				infoTcategory = infoTcategoryService.initInfoTcategory(columnId);
				infoTcategoryList.add(infoTcategory);
			}
			
			return "showList";
		} catch (Exception e) {
			e.printStackTrace();
			return "showList";
		}
	}

	public IInfoTcategoryService getInfoTcategoryService() {
		return infoTcategoryService;
	}

	public void setInfoTcategoryService(IInfoTcategoryService infoTcategoryService) {
		this.infoTcategoryService = infoTcategoryService;
	}

	public InfoTcategory getInfoTcategory() {
		return infoTcategory;
	}

	public void setInfoTcategory(InfoTcategory infoTcategory) {
		this.infoTcategory = infoTcategory;
	}

	public List<InfoTcategory> getInfoTcategoryList() {
		return infoTcategoryList;
	}

	public void setInfoTcategoryList(List<InfoTcategory> infoTcategoryList) {
		this.infoTcategoryList = infoTcategoryList;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getToUsingFlag() {
		return toUsingFlag;
	}

	public void setToUsingFlag(String toUsingFlag) {
		this.toUsingFlag = toUsingFlag;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getInfoTcategoryJson() {
		return infoTcategoryJson;
	}

	public void setInfoTcategoryJson(String infoTcategoryJson) {
		this.infoTcategoryJson = infoTcategoryJson;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
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

	public IInfoTcontentService getInfoTcontentService() {
		return infoTcontentService;
	}

	public void setInfoTcontentService(IInfoTcontentService infoTcontentService) {
		this.infoTcontentService = infoTcontentService;
	}

	public String getContentMainTitle() {
		return contentMainTitle;
	}

	public void setContentMainTitle(String contentMainTitle) {
		this.contentMainTitle = contentMainTitle;
	}

	public List<InfoTcontent> getInfoTcontentList() {
		return infoTcontentList;
	}

	public void setInfoTcontentList(List<InfoTcontent> infoTcontentList) {
		this.infoTcontentList = infoTcontentList;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public InfoTcontent getInfoTcontent() {
		return infoTcontent;
	}

	public void setInfoTcontent(InfoTcontent infoTcontent) {
		this.infoTcontent = infoTcontent;
	}

	public IFileAttaService getFileAttaService() {
		return fileAttaService;
	}

	public void setFileAttaService(IFileAttaService fileAttaService) {
		this.fileAttaService = fileAttaService;
	}

	public File getAtta() {
		return atta;
	}

	public void setAtta(File atta) {
		this.atta = atta;
	}

	public String getAttaFileName() {
		return attaFileName;
	}

	public void setAttaFileName(String attaFileName) {
		this.attaFileName = attaFileName;
	}

	public String getUsingFlag() {
		return usingFlag;
	}

	public void setUsingFlag(String usingFlag) {
		this.usingFlag = usingFlag;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Integer getNowCount() {
		return nowCount;
	}

	public void setNowCount(Integer nowCount) {
		this.nowCount = nowCount;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * 获取  luceneUtil
	 * @return the luceneUtil
	 */
	public LuceneUtil getLuceneUtil() {
		return luceneUtil;
	}

	/**
	 * 设置  luceneUtil
	 * @param luceneUtil the luceneUtil to set
	 */
	public void setLuceneUtil(LuceneUtil luceneUtil) {
		this.luceneUtil = luceneUtil;
	}

	public List<RedPacket> getPacketList() {
		return packetList;
	}

	public void setPacketList(List<RedPacket> packetList) {
		this.packetList = packetList;
	}
	
}
