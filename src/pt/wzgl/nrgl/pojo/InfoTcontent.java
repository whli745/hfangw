package pt.wzgl.nrgl.pojo;

import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.pojo.SysUser;
import util.HtmlUtil;
import util.base.BaseObject;
import web.pojo.RedPacket;
/**
 * 内容发布管理实体
 * @author Administrator
 *
 */
public class InfoTcontent extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	private java.lang.String contentId; // 主键
	private java.lang.String columnId; // 所属栏目
	private java.lang.String contentMainTitle; // 新闻主标题
	private java.lang.String contentSubTitle; // 新闻副标题
	private java.lang.String issueOrgan; // 发布单位
	private java.lang.String issueUsername; // 发布人
	private java.lang.String contentAbstract; // 新闻简介
	private java.lang.String content; // 新闻内容
	private java.util.Date issurDate; // 发布时间
	private java.util.Date loseDate; // 失效时间
	private java.lang.String needCheck;//是否需要审核 0-不需要 1-需要
	private java.lang.String checkFlag; // 是否审核(0:未审核 1: 审核通过 2: 审核不通过)
	private List<String> checkFlags; // 用于封装前台页面  复选框勾选情况
	private java.lang.String usingFlag; // 是否启用(0: 禁用 1: 启用)
	private java.lang.String delFlag; // 是否删除(0: 不删除 1: 删除)
	private java.lang.String createBy; // 创建人
	private java.util.Date createDate; // 创建时间
	private String checkBy;// 审核人
	private Date checkDate;// 审核时间
	private String checkOpinion;// 审核意见
	private Integer sort; // 排序
	private String attaId; // 附件ID
	private String isPicVideo;//是否是图片或视频新闻（0：普通新闻，1：图片新闻  2：视频新闻）
	
	private String jumpUrlFlag;// 是否绑定第三方固有连接
	private String jumpUrl;// 要跳转的url

	private InfoTcategory infoTcategory;// 栏目对象 webColumnId
	private SysUser sysUser; // 用户对象 createBy
	private FileAtta fileAtta; // 附件对象 attaId

	private String isTop; // 是否置顶(0:不置顶 1：置顶)
	private String recommend; // 推荐(0:不推荐 1：推荐)
	private Integer visitorVolume; // 初始化访问量
	
	private String tmpValue;//临时字段
	private String backGround;//临时字段
	private String linkUrl;//图片链接地址
	private String areaId;//区划id
	private SysArea sysArea;//区划对象 areaId
	
	/***********  地图检索结果字段配置 ***************/
	private String themeId;//父级分类ID
	private String departmenName;//单位名称
	private String zipCode;//邮政编码
	private String workDate;//工作时间
	private String contactPhone;//联系电话
	private String contactPerson;//联系人
	private String complaintsPhone;//投诉电话 
	private String belongArea;//所属区划
	private String address;//地址 
	private String introduction;//简介
	private String lng;//经度
	private String lat;//纬度
	private String departmentSite;//单位网站
	private String isMapSearch;//是否是地图检索服务
	private String bmServiceId;//便民服务办事指南ID
	
	
	private List<RedPacket> packetList;
	
	
	public InfoTcontent() {
		super();
	}
	
	public InfoTcontent(String contentId,String columnId, String contentMainTitle,Date issurDate) {
		this.contentId = contentId;
		this.columnId = columnId;
		this.contentMainTitle = contentMainTitle;
		this.issurDate = issurDate;
	}
	
	public java.lang.String getContentId() {
		return contentId;
	}
	public void setContentId(java.lang.String contentId) {
		this.contentId = contentId;
	}
	public java.lang.String getColumnId() {
		return columnId;
	}
	public void setColumnId(java.lang.String columnId) {
		this.columnId = columnId;
	}
	public java.lang.String getContentMainTitle() {
		return contentMainTitle;
	}
	public void setContentMainTitle(java.lang.String contentMainTitle) {
		this.contentMainTitle = contentMainTitle;
	}
	public java.lang.String getContentSubTitle() {
		return contentSubTitle;
	}
	public void setContentSubTitle(java.lang.String contentSubTitle) {
		this.contentSubTitle = contentSubTitle;
	}
	public java.lang.String getIssueOrgan() {
		return issueOrgan;
	}
	public void setIssueOrgan(java.lang.String issueOrgan) {
		this.issueOrgan = issueOrgan;
	}
	public java.lang.String getIssueUsername() {
		return issueUsername;
	}
	public void setIssueUsername(java.lang.String issueUsername) {
		this.issueUsername = issueUsername;
	}
	public java.lang.String getContentAbstract() {
		return contentAbstract;
	}
	public void setContentAbstract(java.lang.String contentAbstract) {
		this.contentAbstract = contentAbstract;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.util.Date getIssurDate() {
		return issurDate;
	}
	public void setIssurDate(java.util.Date issurDate) {
		this.issurDate = issurDate;
	}
	public java.util.Date getLoseDate() {
		return loseDate;
	}
	public void setLoseDate(java.util.Date loseDate) {
		this.loseDate = loseDate;
	}
	public java.lang.String getNeedCheck() {
		return needCheck;
	}
	public void setNeedCheck(java.lang.String needCheck) {
		this.needCheck = needCheck;
	}
	public java.lang.String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(java.lang.String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public List<String> getCheckFlags() {
		return checkFlags;
	}
	public void setCheckFlags(List<String> checkFlags) {
		this.checkFlags = checkFlags;
	}
	public java.lang.String getUsingFlag() {
		return usingFlag;
	}
	public void setUsingFlag(java.lang.String usingFlag) {
		this.usingFlag = usingFlag;
	}
	public java.lang.String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(java.lang.String delFlag) {
		this.delFlag = delFlag;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckOpinion() {
		return checkOpinion;
	}
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAttaId() {
		return attaId;
	}
	public void setAttaId(String attaId) {
		this.attaId = attaId;
	}
	public String getIsPicVideo() {
		return isPicVideo;
	}
	public void setIsPicVideo(String isPicVideo) {
		this.isPicVideo = isPicVideo;
	}
	public InfoTcategory getInfoTcategory() {
		return infoTcategory;
	}
	public void setInfoTcategory(InfoTcategory infoTcategory) {
		this.infoTcategory = infoTcategory;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public FileAtta getFileAtta() {
		return fileAtta;
	}
	public void setFileAtta(FileAtta fileAtta) {
		this.fileAtta = fileAtta;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public Integer getVisitorVolume() {
		return visitorVolume;
	}
	public void setVisitorVolume(Integer visitorVolume) {
		this.visitorVolume = visitorVolume;
	}
	public String getTmpValue() {
		return tmpValue;
	}
	public void setTmpValue(String tmpValue) {
		this.tmpValue = tmpValue;
	}
	public String getBackGround() {
		return backGround;
	}
	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public SysArea getSysArea() {
		return sysArea;
	}
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}
	public String getJumpUrlFlag() {
		return jumpUrlFlag;
	}
	public void setJumpUrlFlag(String jumpUrlFlag) {
		this.jumpUrlFlag = jumpUrlFlag;
	}
	public String getJumpUrl() {
		return jumpUrl;
	}
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	public String getDepartmenName() {
		return departmenName;
	}
	public void setDepartmenName(String departmenName) {
		this.departmenName = departmenName;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getComplaintsPhone() {
		return complaintsPhone;
	}
	public void setComplaintsPhone(String complaintsPhone) {
		this.complaintsPhone = complaintsPhone;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getDepartmentSite() {
		return departmentSite;
	}
	public void setDepartmentSite(String departmentSite) {
		this.departmentSite = departmentSite;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getIsMapSearch() {
		return isMapSearch;
	}
	public void setIsMapSearch(String isMapSearch) {
		this.isMapSearch = isMapSearch;
	}
	
	public String getBmServiceId() {
		return bmServiceId;
	}
	public void setBmServiceId(String bmServiceId) {
		this.bmServiceId = bmServiceId;
	}
	
	public String getContentId_(){
		return HtmlUtil.getTextFromHtml(this.getContent());
	}

	public List<RedPacket> getPacketList() {
		return packetList;
	}

	public void setPacketList(List<RedPacket> packetList) {
		this.packetList = packetList;
	}
}
