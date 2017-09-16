package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import pt.xtgl.jcgl.pojo.SysDict;

import com.zhuofan.auto.OrderInfo;

/**
 * 系统一些参数信息类
 * 
 */
final public class BaseParameter {
	public static final String DesKey = "www.zhuofansoft.com";
	public static Map<String, String> initActionRequest;
	public static String PROJ_TYPE_1 = "1";//最新楼盘
	public static String PROJ_TYPE_2 = "2";//团购楼盘
	
	public static Set<String> attentionRecordSet = new HashSet<String>();
	 
	// 系统运行模式，默认为运行模式
	public static String runMode = "run";
	public static String encoding = "UTF-8";
	public static String ajaxEncoding = "UTF-8";
	public static final String RUN_MODE = "run";
	public static String RECORD_IP;
	public static String RECORD_PORT;
	public static final String RUN_MODE_DEBUG = "debug";
	private static long timeout = 1000000; // 点击的最大间隔
	private static int pageRowsSmall = 10; // 每页显示的记录数---较小的
	private static int pageRowsMiddle = 15; // 每页显示的记录数---较大的
	private static int pageRowsBig = 20; // 每页显示的记录数---最大的
	public static final int PAGE_ROWS_MIN = 8;// 工作台显示的行数

	private static String productName = "卓繁标准平台"; // 产品名称
	public static final String LOGIN_INFO = "LOGIN_INFO";
	public static final String WEB_LOGIN_INFO = "WEB_LOGIN_INFO";
	
	public static final String HEFEI_AREA_ID = "402880815399823b01539992bfcb0011";
	public static String DICT_HXFL = "HXFL";//户型分类
	public static String DICT_JZLX = "JZLX";//建筑类型
	public static String DICT_RXBQ = "RXBQ";//热销标签
	public static String DICT_LPDJ = "LPDJ";//楼盘单价
	public static String DICT_SLZT = "SLZT";//售楼状态
	
	public static List<SysDict> hxflDictList;//户型分类
	public static List<SysDict> jzlxDictList;//建筑类型
	public static List<SysDict> rxbqDictList;//热销标签
	public static List<SysDict> lpdjDictList;//楼盘单价
	public static List<SysDict> slztDictList;//售楼状态
	
	public static String DICT_ESF_SJ = "ESF_SJ";//售价
	public static String DICT_ESF_MJ = "ESF_MJ";//面积
	public static String DICT_ESF_FX = "ESF_FX";//房型
	public static String DICT_ESF_JZND = "ESF_JZND";//建造年代
	public static String DICT_ESF_LC = "ESF_LC";//楼层
	public static String DICT_ESF_FWLX = "ESF_FWLX";//房屋类型
	
	public static List<SysDict> esfsjDictList;//售价
	public static List<SysDict> esfmjDictList;//面积
	public static List<SysDict> esffxDictList;//房型
	public static List<SysDict> esfjzndDictList;//建造年代
	public static List<SysDict> esflcDictList;//楼层
	public static List<SysDict> esffwlxDictList;//房屋类型
	
	
	public static String DICT_ID_QFZS = "4028808153fb8ea90153fdce5fcf0003";
	public static String DICT_ID_XFZS = "4028808153fb8ea90153fdce9b350004";
	public static String DICT_ID_DS = "4028808153fb8ea90153fdcedea50005";
	public static String DICT_ID_SQ = "4028808153fb8ea90153fdcf0f010006";
	
	public static final String SYSTREE_ROOT_ID = "-1";// 属性菜单根目录ID
	public static final String SYSMODULE_ROOT_NAME = " 惠民平台系统菜单";
	public static final String SYSDICT_ROOT_NAME = "数据字典目录";
	public static final String SYSAPPPARAM_ROOT_NAME = "系统参数目录";
	public static final String ZJBL_TYPE_ROOT_NAME = "办证主题分类目录";
	public static final String GGFW_TYPE_ROOT_NAME = "服务主题分类目录";
	public static final String NET_USERMODULE_ROOT_NAME = "用户中心菜单";
	public static final String SYSTEM_NAME = "惠民一站通服务平台";

	/** 用户证件类型 第一类*/
	public static final String USER_CERTIFICATION_TYPE_FIRST = "1";
	/** 用户证件类型 第二类*/
	public static final String USER_CERTIFICATION_TYPE_SECOND = "2";
	/** 用户证件类型 第三类*/
	public static final String USER_CERTIFICATION_TYPE_THIRD = "3";
	/** 用户证件类型 个人证件*/
	public static final String USER_CERTIFICATION_TYPE_GEREN = "0";
	/** 支持上传的文件类型*/
	public static final String ZCYHGL_FILETYPE = "402882a1530cd07d01530cdd2e750005";
	/** 支持上传的文件大小*/
	public static final String ZCYHGL_MAXSIZE = "402882a1530cd07d01530cde074e0006";
	/** 证件文件 存放所在的 文件夹*/
	public static final String ZCYHGL_FILEDIR = "402882a1530cd07d01530cdbc92c0004";
	/** 附件存放位置 */
	public static final String ATTAPATH = "402882a1530cd07d01530d023d780008";
	
	public static final String SYSAREAID = "20130419093149037403366665913142";
	public static final String MAIL_SEND_YJFWQDXZ="4028829352f81bad0152f846dd1f0006";//邮件发送人的邮件服务器地址
	public static final String MAIL_SEND_FSRYHM="4028829352f81bad0152f847efff0007";//邮件发送人的用户名
	public static final String MAIL_SEND_FSRMM="4028829352f81bad0152f848d1640008";//邮件发送人的密码
	public static final String MAIL_SEND_YJDZ="4028829352f81bad0152f84967630009";//邮件发送人的邮件地址
	public static final String MAIL_SEND_XMFWDZ="4028829352f81bad0152f86e4d13000a";//系统（项目）访问地址
	
	public static final String dbType = "mysql";
	
	public static String ISADMIN = "1";

	public static HashMap<String, String> userMap = new HashMap<String, String>();// 记录用户登录sessionid
	// 存放配置的信息
	public static Map<String, String> configMap = new HashMap<String, String>();
	// 站点id
	public static String web_site_Id = "20130419093149037845634429844034";
	/**
	 * 网站用户session过期时间 web.xml中设置 单位分钟
	 */
	public static String WEB_SESSION_TIMEOUT = "180";
	/**
	 * 项目附件存放路径
	 */
	public static String XMFJCFLJ = "E://zjgl";

	/**
	 * 公告上传附件路径
	 */
	public static String NOTICE_FILE_ATTA = "notice/fileAtta/";

	/**
	 * 工作台模块分类 0、默认工作台；1、一般工作台
	 */
	public static final Map<String, String> WORK_BENCH;
	/**
	 * 默认工作台
	 */
	public static final String WORK_BENCH_DEFAULT = "0";
	/**
	 * 一般工作台
	 */
	public static final String WORK_BENCH_COMMON = "1";

	/**
	 * 工作台排版方式 1、一行一个；2、一行两个
	 */
	public static final Map<String, String> WORK_TYPESETTING;

	/**
	 * 工作台排版方式:一行一个
	 */
	public static final String WORK_TYPESETTING_1 = "1";

	/**
	 * 工作台排版方式:一行两个
	 */
	public static final String WORK_TYPESETTING_2 = "2";
	/**
	 * 公告类型 1：内网 2：外网 3：内/外网
	 */
	public static final Map<String, String> NOTICE_TYPE;
	/**
	 * 公告类型 1-内网
	 */
	public static final String NOTICE_TYPE_1 = "1";
	/**
	 * 公告类型 2-外网
	 */
	public static final String NOTICE_TYPE_2 = "2";
	/**
	 * 公告类型 3-内/外网
	 */
	public static final String NOTICE_TYPE_3 = "3";

	/**
	 * 超级管理员用户
	 */
	public static String ADMIN = "1";

	/**
	 * 性别
	 */
	public static final Map<String, String> PERSON_SEX;
	/**
	 * 男
	 */
	public static final String MALE = "0";
	/**
	 * 女
	 */
	public static final String FEMALE = "1";

	/**
	 * 级别 1、省、自治区、直辖市、特别行政区；2、地级市、盟；3、县、区、县级市、旗；4、乡镇、街道
	 */
	public static final Map<String, String> AREA_LEVEL;
	/**
	 * 省、自治区、直辖市、特别行政区
	 */
	public static final String AREA_LEVEL_1 = "1";
	/**
	 * 地级市、盟
	 */
	public static final String AREA_LEVEL_2 = "2";
	/**
	 * 县、区、县级市、旗
	 */
	public static final String AREA_LEVEL_3 = "3";
	/**
	 * 乡镇、街道
	 */
	public static final String AREA_LEVEL_4 = "4";

	/**
	 * 已删除标识
	 */
	public static final String DELETE_YES = "1";
	/**
	 * 未删除标识
	 */
	public static final String DELETE_NO = "0";
	/**
	 * 删除状态
	 */
	public static final Map<String, String> DELETE_STATUS;

	/**
	 * 启用禁用状态 0、启用；1、禁用
	 */
	public static final Map<String, String> STATUS;
	/**
	 * 启用
	 */
	public static final String STATUS_ENABLE = "0";
	/**
	 * 禁用
	 */
	public static final String STATUS_DISABLE = "1";

	/**
	 * 审核状态 0、未提交审核；1、已提交审核；2、审核通过；4、审核不通过
	 */
	public static final Map<String, String> AUDIT_MARK;
	/**
	 * 未提交审核
	 */
	public static final String AUDIT_NOT_SUBMIT = "0";
	/**
	 * 已提交审核、未审核状态
	 */
	public static final String AUDIT_SUBMIT = "1";
	/**
	 * 审核通过
	 */
	public static final String AUDIT_PASS = "2";
	/**
	 * 审核未通过
	 */
	public static final String AUDIT_NOT_PASS = "4";

	/**
	 * 审核状态 1、审核通过；2、审核未通过
	 */
	public static final Map<String, String> AUDIT_STATUS;
	/**
	 * 审核操作 1、同意；2、不同意
	 */
	public static final Map<String, String> AUDIT_OPT;
	/**
	 * 审核通过
	 */
	public static final String AUDIT_STATUS_2 = "2";
	/**
	 * 审核未通过
	 */
	public static final String AUDIT_STATUS_4 = "4";

	/**
	 * 预审状态 0、未预审；1、合格；2、不合格
	 */
	public static final Map<String, String> PRE_TRIAL_STATUS;
	/**
	 * 未预审
	 */
	public static final String PRE_TRIAL_STATUS_1 = "1";
	/**
	 * 合格
	 */
	public static final String PRE_TRIAL_STATUS_2 = "2";
	/**
	 * 不合格
	 */
	public static final String PRE_TRIAL_STATUS_3 = "3";

	/**
	 * 是
	 */
	public static final String YES = "1";
	/**
	 * 否
	 */
	public static final String NO = "0";
	/**
	 * 是、否 1-是，0-否
	 */
	public static final Map<String, String> YES_NO;

	public static final Map<String, String> NOTICE_DATE_TYPE;
	public static final String NOTICE_DATE_TYPE_1 = "1";
	public static final String NOTICE_DATE_TYPE_2 = "2";
	/**
	 * 0：正常日志信息
	 */
	public static final Integer LOG_TYPE_0 = 0;
	/**
	 * 1：错误日志信息
	 */
	public static final Integer LOG_TYPE_1 = 1;

	/**
	 * 菜单按钮类型 1、列表链接；2、普通按钮;3、表头按钮
	 */
	public static final Map<String, String> CONTROL_TYPE;
	public static final String CONTROL_TYPE_1 = "1";
	public static final String CONTROL_TYPE_2 = "2";
	public static final String CONTROL_TYPE_3 = "3";

	/**
	 * 按部门
	 */
	public static final String ORGAN_TYPE = "0";

	// 顶级区划主键
	public static String TOP_DISTRICT_CODE = "-1";

	// 顶级区划主键
	public static String TOP_DISTRICT_ID = "20130419093149037845634429844034";
	/**
	 * 区划集合
	 */
	public static String DISTRICT_HTML_FRAGMENT;
	/**
	 * 镇、村、街道、苏木集合
	 */
	public static Map<String, String> DISTRICT_CHILDREN_HTML_FRAGMENT;
	/**
	 * 区划名称map key - 区划id value - 区划名称
	 */
	public static Map<String, String> DISTRICT_NAME_MAP;
	/**
	 * 机构集合 key - 区划id value - html片段
	 */
	public static Map<String, String> ORGAN_HTML_FRAGMENT_MAP;

	/**
	 * 菜单拥有者
	 */
	public static final Map<String, String> MENU_OWNER = new TreeMap<String, String>();

	/**
	 * 个人
	 */
	public static final String MENU_OWNER_USER = "1";
	/**
	 * 企业
	 */
	public static final String MENU_OWNER_BUSINESSER = "2";
	/**
	 * 共用
	 */
	public static final String MENU_OWNER_COMMON = "3";
	/**
	 * 上级区划是否默认查看下级区划发布的内容
	 */
	public static String MRXJQH = "1";
	/**
	 * 栏目树系统参数 同步异步标志（同步为1 异步为0）
	 */
	public static String LMSMRZK = YES;// 栏目树是否默认展开
	/**
	 * 普通新闻
	 */
	public static final String COMMON_NEW = "2";
	/**
	 * 曝光台顶层父ID
	 */
	public static final String EXPOSURETABLE = "402881fd51605337015160579a570003";
	/**
	 * 网站首页曝光台显示数据条数
	 */
	public static final Integer WEB_EXPOSURETABLECOUNT = 12;
	/**
	 * 新闻动态顶层父ID
	 */
	public static final String NEWSDYNAMIC = "402881fd5166b4ed015167155eec0002";
	public static final String WEBCOLUMN_ROOT_ID = "-1";// 属性菜单根目录ID
	public static final String WEBCOLUMN_ROOT_NAME = "全部栏目";
	
	public static final String LUCENE_INDEX_POS = "lucene/index";//LUCENE索引文件位置 
	public static final String LUCENE_INDEX_INFOTCONTENT = "/info_tcontent";

	/**
	 * 加班
	 */
	public static final String HOLIDAY_TYPE_W = "W";

	/**
	 * 休息
	 */
	public static final String HOLIDAY_TYPE_H = "H";

	public static final Map<String, String> HOLIDAY_TYPE;
	
	public static final String DATA_FILTER_1 = "1";
	public static final String DATA_FILTER_2 = "2";
	public static final String DATA_FILTER_3 = "3";
	public static final String DATA_FILTER_4 = "4";
	public static final String DATA_FILTER_5 = "5";
	public static final String DATA_FILTER_6 = "6";
	
	static {
		HOLIDAY_TYPE = new TreeMap<String, String>();
		HOLIDAY_TYPE.put(HOLIDAY_TYPE_H, "休息");
		HOLIDAY_TYPE.put(HOLIDAY_TYPE_W, "加班");

		CONTROL_TYPE = new TreeMap<String, String>();
		CONTROL_TYPE.put(CONTROL_TYPE_1, "列表链接");
		CONTROL_TYPE.put(CONTROL_TYPE_2, "普通按钮");
		CONTROL_TYPE.put(CONTROL_TYPE_3, "表头按钮");

		YES_NO = new TreeMap<String, String>();
		YES_NO.put(YES, "是");
		YES_NO.put(NO, "否");

		AUDIT_STATUS = new TreeMap<String, String>();
		AUDIT_STATUS.put(AUDIT_STATUS_2, "审核通过");
		AUDIT_STATUS.put(AUDIT_STATUS_4, "审核未通过");

		AUDIT_OPT = new TreeMap<String, String>();
		AUDIT_OPT.put(AUDIT_STATUS_2, "同意");
		AUDIT_OPT.put(AUDIT_STATUS_4, "不同意");

		PERSON_SEX = new TreeMap<String, String>();
		PERSON_SEX.put(MALE, "男");
		PERSON_SEX.put(FEMALE, "女");

		STATUS = new TreeMap<String, String>();
		STATUS.put(STATUS_ENABLE, "启用");
		STATUS.put(STATUS_DISABLE, "禁用");

		PRE_TRIAL_STATUS = new TreeMap<String, String>();
		PRE_TRIAL_STATUS.put(PRE_TRIAL_STATUS_1, "1");
		PRE_TRIAL_STATUS.put(PRE_TRIAL_STATUS_2, "2");
		PRE_TRIAL_STATUS.put(PRE_TRIAL_STATUS_3, "3");

		AUDIT_MARK = new TreeMap<String, String>();
		AUDIT_MARK.put(AUDIT_NOT_SUBMIT, "未提交审核");
		AUDIT_MARK.put(AUDIT_SUBMIT, "已提交审核");
		AUDIT_MARK.put(AUDIT_PASS, "审核已通过");
		AUDIT_MARK.put(AUDIT_NOT_PASS, "审核未通过");

		DELETE_STATUS = new TreeMap<String, String>();
		DELETE_STATUS.put(DELETE_NO, "未删除");
		DELETE_STATUS.put(DELETE_YES, "已删除");

		AREA_LEVEL = new TreeMap<String, String>();
		AREA_LEVEL.put(AREA_LEVEL_1, "省、自治区、直辖市、特别行政区");
		AREA_LEVEL.put(AREA_LEVEL_2, "地级市、盟");
		AREA_LEVEL.put(AREA_LEVEL_3, "县、区、县级市、旗");
		AREA_LEVEL.put(AREA_LEVEL_4, "乡镇、街道");

		WORK_BENCH = new TreeMap<String, String>();
		WORK_BENCH.put(WORK_BENCH_COMMON, "一般工作台");
		WORK_BENCH.put(WORK_BENCH_DEFAULT, "默认工作台");

		WORK_TYPESETTING = new TreeMap<String, String>();
		WORK_TYPESETTING.put(WORK_TYPESETTING_1, "一行一个");
		WORK_TYPESETTING.put(WORK_TYPESETTING_2, "一行两个");

		NOTICE_TYPE = new TreeMap<String, String>();
		NOTICE_TYPE.put(NOTICE_TYPE_1, "内网");
		NOTICE_TYPE.put(NOTICE_TYPE_2, "外网");
		NOTICE_TYPE.put(NOTICE_TYPE_3, "内/外网");

		NOTICE_DATE_TYPE = new TreeMap<String, String>();
		NOTICE_DATE_TYPE.put(NOTICE_DATE_TYPE_1, "无限制");
		NOTICE_DATE_TYPE.put(NOTICE_DATE_TYPE_2, "时间限制");

		// 菜单拥有者
		MENU_OWNER.put(MENU_OWNER_USER, "中介机构");
		MENU_OWNER.put(MENU_OWNER_BUSINESSER, "项目单位");
		MENU_OWNER.put(MENU_OWNER_COMMON, "共用");
	}
	
	/**
	 * 业务类型
	 */
	public static String SERVICETYPE = "PTXG_YWLX";
	/**
	 * 行政职务分类
	 */
	public static String DUTYTYPE = "HYXG_ZWFL";
	/**
	 * 身份类型
	 */
	public static String IDENTITYTYPE = "PTXG_SFLX";

	/**
	 * 获取所在区域
	 */
	public static String getDistrict() {
		if (configMap != null) {
			if (configMap.get("district") != null) {
				return configMap.get("district");
			}
		}
		return "";
	}

	/**
	 * 
	 * 图片格式
	 */
	public static String[] ARR_PHOTO_TYPE = { "jpeg", "png", "gif", "jpg",
			"bmp", "tiff", "bmp", "JPEG", "PNG", "GIF", "JPG", "BMP", "TIFF",
			"BMP" };
	/**
	 * 常见的非图片可上传格式
	 */
	public static String[] ARR_OFFICE_TYPE = { "txt", "docx", "doc", "xlsx",
			"xls", "pptx", "ppt", "pdf", "swf" };
	/**
	 * 常见的压缩格式
	 */
	public static String[] ARR_COMPRESS_TYPE = { "zip", "rar" };

	private static OrderInfo orderInfo;

	public static OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public static void setOrderInfo(OrderInfo orderInfo) {
		BaseParameter.orderInfo = orderInfo;
	}

	public static String CHECK_MESSAGE_NO = "审核不通过";

	public static long getTimeout() {
		return timeout;
	}

	public static void setTimeout(long timeout) {
		BaseParameter.timeout = timeout;
	}

	public static int getPageRowsSmall() {
		return pageRowsSmall;
	}

	public static void setPageRowsSmall(int pageRowsSmall) {
		BaseParameter.pageRowsSmall = pageRowsSmall;
	}

	public static int getPageRowsMiddle() {
		return pageRowsMiddle;
	}

	public static void setPageRowsMiddle(int pageRowsMiddle) {
		BaseParameter.pageRowsMiddle = pageRowsMiddle;
	}

	public static int getPageRowsBig() {
		return pageRowsBig;
	}

	public static void setPageRowsBig(int pageRowsBig) {
		BaseParameter.pageRowsBig = pageRowsBig;
	}

	public static String getProductName() {
		return productName;
	}

	public static void setProductName(String productName) {
		BaseParameter.productName = productName;
	}

	public static String getDbType() {
		return dbType;
	}

	public static Map<String, String> getInitActionRequest() {
		return initActionRequest;
	}

	public static void setInitActionRequest(
			Map<String, String> initActionRequest) {
		BaseParameter.initActionRequest = initActionRequest;
	}
}