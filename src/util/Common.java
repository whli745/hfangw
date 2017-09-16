package util;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import pt.xtgl.jcgl.pojo.LoginUserInfo;
import pt.xtgl.jcgl.pojo.SysAppParam;
import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAppParamService;
import pt.xtgl.jcgl.service.ISysAreaService;
import web.service.ISecondHandHouseService;

import com.opensymphony.xwork2.ActionContext;

public class Common {
	
	public static SessionFactory getSpringSessionFactory() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		return (SessionFactory)WebApplicationContextUtils.getWebApplicationContext(
				servletContext).getBean("sessionFactory");
	}
	
	public static Object getService(String serviceName) {
		return (Object) WebApplicationContextUtils.getWebApplicationContext(
				ServletActionContext.getServletContext()).getBean(serviceName);
	}

	public static Object getService(ServletContext sc, String serviceName) {
		return (Object) WebApplicationContextUtils.getWebApplicationContext(sc)
				.getBean(serviceName);
	}

	public static String md5(String piStr) {
		String encodeStr = "";
		byte digesta[] = (byte[]) null;
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(piStr.getBytes());
			digesta = alg.digest();
			encodeStr = byte2hex(digesta);
		} catch (Exception exception) {
		}
		return encodeStr;
	}

	private static String byte2hex(byte piByte[]) {
		String reStr = "";
		for (int i = 0; i < piByte.length; i++) {
			int v = piByte[i] & 0xff;
			if (v < 16)
				reStr = reStr + "0";
			reStr = reStr + Integer.toString(v, 16).toLowerCase();
		}

		return reStr;
	}

	public static String iso2GB(String str) {
		try {
			if (str != null)
				str = new String(str.getBytes("ISO-8859-1"), "GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String gb2ISO(String str) {
		try {
			if (str != null)
				str = new String(str.getBytes("GB2312"), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 判断参数是否为null或空字符串
	 * 
	 * @param str
	 *            需判断字符串
	 * @return 为null或空字符串返回true，否则返回false
	 */
	public static boolean isNullOrSpace(String str) {
		return str == null || str.trim().length() < 1;
	}

	/**
	 * 检测输入字符串是否是整数
	 * 
	 * @param String
	 *            , 待检查的字符串
	 * @return boolean, true表示是整数，否则为false
	 */
	public static boolean checkNumber(String piStr) {
		boolean retBoolean = true;
		if (piStr == null) {
			retBoolean = false;
		} else {
			for (int i = 0; i < piStr.length(); i++) {
				if (!Character.isDigit(piStr.toCharArray()[i])) {
					retBoolean = false;
					break;
				}
			}
		}
		return retBoolean;
	}

	/**
	 * 检测EMail是否合法函数
	 * 
	 * @author lixia
	 */
	public static boolean chkEmail(String strEmail) {
		if (strEmail.equals(null)) {
			return false;
		}
		if ((strEmail.indexOf("@") == -1) || (strEmail.indexOf(".") == -1)) {
			return false;
		}
		if (strEmail.indexOf("\\") > -1) {
			return false;
		}
		if (strEmail.indexOf("/") > -1) {
			if (strEmail.indexOf("'") > -1) {
				return false;
			}
		}
		if (strEmail.indexOf("!") > -1) {
			return false;
		}
		if ((strEmail.indexOf(",") > -1) || (strEmail.indexOf(";") > -1)) {
			return false;
		}
		if (strEmail.indexOf("?") > -1) {
			return false;
		}
		return true;
	}

	/**
	 * 使用给定的字串替换源字符串中指定的字串。
	 * 
	 * @param mainString
	 *            源字符串
	 * @param oldString
	 *            被替换的字串
	 * @param newString
	 *            替换字串
	 * @return String
	 */
	public final static String replace(final String mainString,
			final String oldString, final String newString) {
		if (mainString == null)
			return null;
		int i = mainString.lastIndexOf(oldString);
		if (i < 0)
			return mainString;
		StringBuffer mainSb = new StringBuffer(mainString);
		while (i >= 0) {
			mainSb.replace(i, i + oldString.length(), newString);
			i = mainString.lastIndexOf(oldString, i - 1);
		}
		return mainSb.toString();
	}

	/**
	 * 去掉字符串的'和“
	 * 
	 * @param s
	 * @return
	 */
	public final static String safeStr(String s) {
		String ret = replace(s, "'", "");
		ret = replace(ret, "\"", "");
		return ret;
	}

	public final static String getResultCols(List result, String strSplit) {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < result.size(); i++) {
			ret.append(result.get(i).toString());
			ret.append(strSplit);
		}
		if (ret != null && ret.length() > 0)
			return ret.substring(0, ret.length() - strSplit.length());
		else
			return ret.toString();
	}

	/**
	 * 判断集合是否为null，包含List、Set等集合
	 * 
	 * @param collection
	 *            需要判断的集合
	 * @return 为null或空字符串返回true，否则返回false
	 */
	public static boolean collectionIsNullOrSpace(Collection collection) {
		if (collection == null || collection.isEmpty()
				|| collection.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 去掉数据字典中的所属业务中的交易平台业务
	 * 
	 * @param sysDictList
	 * @return
	 */
	public static List<SysDict> listRemoveObject(List<SysDict> sysDictList) {
		List<SysDict> sysDictListNew = new ArrayList<SysDict>();
		if (Common.collectionIsNullOrSpace(sysDictList)) {
			for (SysDict sysDict : sysDictList) {
				if (!"交易平台".equals(sysDict.getDictName())) {
					sysDictListNew.add(sysDict);
				}
			}
		}
		return sysDictListNew;
	}

	/**
	 * 
	 * 功能 ：获取从当前年向上拉10年
	 * 
	 * @author chj
	 * @date 2013-6-8下午01:52:06
	 * @param yearCount
	 * @return
	 */
	public static List<String> getYearList(int yearCount) {
		List<String> yearList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		yearList.add("" + curYear);
		for (int i = 1; i < yearCount; i++) {
			yearList.add("" + (curYear - i));
		}
		return yearList;
	}

	/**
	 * 
	 * 功能 ：获取12个月份
	 * 
	 * @author xuhw
	 * @date 2013-06-14
	 * @param
	 * @return
	 */
	public static List<String> getMonthList() {
		List<String> monthList = new ArrayList<String>();

		for (int i = 1; i < 13; i++) {
			monthList.add("" + i);
		}
		return monthList;
	}

	/**
	 * 
	 * 功能 ：将含有百分号的利率转换成小树利率
	 * 
	 * @author chj
	 * @date 2013-6-14上午10:32:17
	 * @param oldRate
	 * @return 小数
	 */
	public static String percentageToDecimal(String oldRate) {
		String newRate = "";
		if (!Common.isNullOrSpace(oldRate)) {
			float result = new Float(oldRate.substring(0, oldRate.indexOf("%"))) / 100;
			newRate = "" + result;
		}
		return newRate;
	}

	/**
	 * 
	 * 功能 ：将小数转换为百分数
	 * 
	 * @author chj
	 * @date 2013-6-14上午11:17:51
	 * @param oldRate
	 * @return 百分数
	 */
	public static String decimalToPercentage(String oldRate) {
		String newRate = "";
		if (!Common.isNullOrSpace(oldRate)) {
			float result = new Float(oldRate) * 100;
			newRate = "" + result + "%";
		}
		return newRate;
	}
	
	/**
	 * 功能：将中文字符进行 URL 编码
	 * @param s
	 * @param encoder
	 * @return
	 */
	public static String toStringByEncoder(String s, String encoder) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c >= 0 && c <= 255) {

				sb.append(c);
				

			} else {
				byte[] b;

				try {

					b = String.valueOf(c).getBytes(encoder);

				} catch (Exception ex) {

					System.out.println(ex);

					b = new byte[0];

				}

				for (int j = 0; j < b.length; j++) {

					int k = b[j];

					if (k < 0)

						k += 256;

					sb.append("%" + Integer.toHexString(k).toUpperCase());

				}

			}

		}

		return sb.toString();

	}
	
	/**
	 * 
	* @Title: getLogUser 
	* @Description: 获取当前登录的用户
	* @author:	chengcheng 
	* @date:	2015年11月16日 
	* @param: @return
	* @return: SysUser    返回类型 
	* @throws
	 */
	public static SysUser getLogUser () {
		Object obj = ActionContext.getContext().getSession().get(util.BaseParameter.LOGIN_INFO);
		if (obj == null) {
			return null;
		}
		return ((LoginUserInfo) obj).getUser();
	}
	
	/**
	 * 检测值是否包含特殊字符
	 * 
	 * @author meidj
	 * @param obj
	 *            对象值（pojo实体类）
	 * @param PropsStrs
	 *            字符串值集合
	 * @return true包含，false不包含
	 * @throws Exception
	 */
	public static Boolean checkPropsLegal(Object obj, String[] propsStrs)
			throws Exception {
		Boolean retBool = false;
		String[] specialChar = new String[] { "|", "&", ";", "$", "%", "@",
				"+", ",", ".", "'", "\\", "\'", "\"", "()",")", "<>", "cr", "lf",
				"bs", "script", "document", "eval", "open", "sysopen",
				"system", "t.g", "select", "insert", "update", "delete",
				"alter", "drop", "exec" };
		// 对象属性值
		if (null != obj) {
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				if (method.getName().toLowerCase().indexOf("get") == 0) {
					Object value = method.invoke(obj, null);
					if (null != value
							&& !value.toString().toLowerCase().trim()
									.contains("class")) {
						for (String sc : specialChar) {
							if (value.toString().toLowerCase().contains(sc)) {
								retBool = true;
								break;
							}
						}
					}
				}
			}
		}
		// 字符串值集合
		if (null != propsStrs && propsStrs.length > 0) {
			for (String props : propsStrs) {
				if (!Common.isNullOrSpace(props)) {
					for (String sc : specialChar) {
						if (props.toLowerCase().contains(sc)) {
							retBool = true;
							break;
						}
					}
				}
			}
		}
		return retBool;
	}
	
	/**
	 * 
	* @Title: queryAreaJsonByLogUser 
	* @Description: 根据当前登录的用户查询出所属区划JSON数据,在页面中ztree中使用
	* @author:	chengcheng 
	* @date:	2015年11月24日 
	* @param: @return
	* @return: String    返回类型 
	* @throws
	 */
	public static String queryAreaJsonByLogUser () {
		try {
			SysUser logUser = Common.getLogUser();
			
			if (logUser != null && !Common.isNullOrSpace(logUser.getAreaId())) {
				ISysAreaService sysAreaService = (ISysAreaService) Common.getService("sysAreaService");
				return sysAreaService.querySysAreaJSONByAreaId(logUser.getAreaId());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 给list添加null
	 * @author dusd
	 * @date 2015-12-25
	 * @param list
	 * @param addNum
	 * @return
	 */
	public static <T> List<T> addNullToList(
			List<T> list, int addNum) {
		if(list == null || addNum <= 0)
			return list;
		for (int i = 0; i < addNum; i++) {
			list.add(null);
		}
		return list;
	} 
	
	/**
	 * 获取附件完整路径(绝对路径)
	 * @param filePath : 保存在物理表里的路径
	 * @return
	 * @createDate 2014-12-4
	 * @author Liangmh
	 */
	public static String getAttachmentRealPath(String filePath){
		String attaPath="";
		try {
			attaPath = SysAppParamUtil.getParamValByParamId(BaseParameter.ATTAPATH);
		} catch (Exception e) {
			attaPath="";
			e.printStackTrace();
		}
		if( !isNullOrSpace(attaPath) ){
			String dir = null;
			if( attaPath.indexOf(":") >= 0 ){
				dir = attaPath + filePath;
				return dir.replace("\\", "/");
			}else{
				dir =  getCurSerRealPath()+ attaPath + filePath;
				return dir.replace("\\", "/");
			}
		}
		return null;
	}
	
	/**
	 * 获取当前项目在服务器上的绝对地址
	 * @return
	 */
	public static String getCurSerRealPath(){
		String classPath = new Common().getClass().getResource("/").getPath();
		return classPath.substring(1, classPath.indexOf("WEB-INF"));
	}
	
	/**
	 * 根据访问ip，判断关注度
	 */
	public static boolean chkAttentionRecord(String _ip){
		Set<String> s = BaseParameter.attentionRecordSet;
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Set<String> ss = new HashSet<String>(s);
		for (Iterator<String> i = ss.iterator();i.hasNext();) {
			String rd = i.next();
			if(!rd.contains(today)){
				s.remove(rd);
			}
		}
		if(s.contains(today + _ip)){
			return false;
		}else{
			s.add(today + _ip);
			return true;
		}
	}
	
	public static int  calculateQueryPage(String uuid,int totalPage){
		String random = md5(uuid + totalPage);
		int b = 0;
		for(int a = 0;a<31;a++){
			b = a + 1;
			String c = random.substring(a, b);
			try {
				Integer d = Integer.parseInt(c);
				if(d > 0 && d <= totalPage){
					return d;
				}
			} catch (Exception e) {
				continue;
			}
		}
		return 1;
	}
	
	public static String getInventoryNumber() throws Exception{
		int a = 470527290;
		ISecondHandHouseService s = (ISecondHandHouseService)getService("secondHandHouseService");
		int b = s.countTTN();
		return a + b + "";
	}
	
	/**
	 * 获取站点列表
	 * @author zhujj
	 * @date 2016-7-9 上午10:29:17
	 *
	 */
	public static List<SysArea> getAreaSiteList() {
		try {
			ISysAreaService sysAreaService = (ISysAreaService) Common.getService("sysAreaService");
			return sysAreaService.querySysAreaListByTopId(BaseParameter.web_site_Id);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取全站检索关键字
	 * @author zhujj
	 * @date 2016-7-9 上午10:40:47
	 *
	 */
	public static String getSearchKeywordsHtml(){
		try{
			ISysAppParamService sysAppParamService = (ISysAppParamService) Common.getService("sysAppParamService");
			SysAppParam p = sysAppParamService.getSysAppParamByParamCode("SSGJZ");
			StringBuffer html = new StringBuffer("&nbsp;&nbsp;");
			if(p != null&&p.getParamVal() != null){
				String[] keywords = p.getParamVal().split(",");
				for(int i = 0;i<keywords.length;i++){
					html.append("<a href='javascript:void(0)' onclick='doSch_(this);'>" + keywords[i] + "</a>&nbsp;&nbsp;");
				}
			}
			return html.toString();
		}catch (Exception e) {
			return "";
		}
	}
}
