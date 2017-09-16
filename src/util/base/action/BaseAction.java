package util.base.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import pt.xtgl.jcgl.pojo.LoginUserInfo;
import pt.xtgl.jcgl.pojo.Loginfo;
import pt.xtgl.jcgl.pojo.NetUserLoginInfo;
import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ILogService;
import pt.xtgl.jcgl.service.ISysAreaService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component
@SuppressWarnings({ "serial", "unchecked" })
public abstract class BaseAction extends ActionSupport {
	@Resource
	private ILogService logService;

	public BaseAction() {
		page = 1;
		DERROR = "derror";
		DSUCCESS = "dsuccess";
	}

	@SuppressWarnings("rawtypes")
	List pages = new ArrayList();

	@SuppressWarnings("rawtypes")
	public List getPages() {
		return pages;
	}

	@SuppressWarnings("rawtypes")
	public void setPages() {
		if (maxPage_ == 0) {
			Hashtable ht = new Hashtable();
			ht.put("URL", getRequestURL(0));
			ht.put("page", new Integer(0));
			pages.add(ht);
		}
		for (int i = 0; i < maxPage_; i++) {
			Hashtable ht = new Hashtable();
			ht.put("URL", getRequestURL(i + 1));
			ht.put("page", new Integer(i + 1));
			pages.add(ht);
		}
	}

	public boolean getZeroPage() {
		return maxPage_ <= 0;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int i) {
		page = i;
	}

	public int getMaxPage() {
		return maxPage_;
	}

	public String getNextRequestURL() {
		return getRequestURL(page + 1);
	}

	public String getPreRequestURL() {
		return getRequestURL(page - 1);
	}

	public String getPageURL_() {
		return pageCondition_;
	}

	public String getPageCondition_() {
		return pageCondition_;
	}

	public void setPageCondition_(String pageCondition_) {
		this.pageCondition_ = pageCondition_;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public static final String convertBR(String piStr) {
		piStr = Common.replace(piStr, "\r\n", "\n");
		piStr = Common.replace(piStr, "\n", "<br>");
		return piStr;
	}

	protected int getRealLength(String str) {
		if (str == null)
			return 0;
		else
			return Common.gb2ISO(str).length();
	}

	protected void setMaxPage(int maxpage) {
		if (getPage() > maxpage)
			setPage(maxpage);
		maxPage_ = maxpage;
	}

	private String getRequestURL(int npage) {
		try {
			requestURL = ServletActionContext.getRequest().getRequestURI()
					.toString();
			String querys = ServletActionContext.getRequest().getQueryString();
			if (querys != null)
				querys = new String(querys.getBytes("ISO-8859-1"));

			if (pageCondition_ == null || pageCondition_.trim().length() < 1) {
				if (querys == null)
					requestURL = (new StringBuilder(String.valueOf(requestURL)))
							.append("?page=").append(npage).toString();
				else if (querys.indexOf("page=") >= 0)
					requestURL = (new StringBuilder(String.valueOf(requestURL)))
							.append("?")
							.append(querys.replaceAll("page=[0-9]+",
									(new StringBuilder("page=")).append(npage)
											.toString())).toString();
				else
					requestURL = (new StringBuilder(String.valueOf(requestURL)))
							.append("?").append(querys).append("&page=")
							.append(npage).toString();
			} else {
				requestURL = (new StringBuilder(String.valueOf(requestURL)))
						.append("?").append(pageCondition_).append("&page=")
						.append(npage).toString();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestURL;
	}

	public String getQuery() {
		return ServletActionContext.getRequest().getQueryString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected int getPageRows() {
		return BaseParameter.getPageRowsMiddle();
	}

	protected int getBigPageRows() {
		return BaseParameter.getPageRowsBig();
	}

	protected int getSmallPageRows() {
		return BaseParameter.getPageRowsSmall();
	}

	public Object get(String name) {
		return ActionContext.getContext().getSession().get(name);
	}

	public void set(String name, Object value) {
		ActionContext.getContext().getSession().put(name, value);
	}

	public HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public void remove(String name) {
		if (ActionContext.getContext().getSession().containsKey(name))
			ActionContext.getContext().getSession().remove(name);
	}

	protected String getIPAddress() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	public Object getLogInfo() {
		return this.get(util.BaseParameter.LOGIN_INFO);
	}

	// 获取当前登录平台用户
	public SysUser getLoginUser() {

		Object obj = this.get(util.BaseParameter.LOGIN_INFO);
		if (obj == null || !(obj instanceof LoginUserInfo)) {
			return null;
		}
		return ((LoginUserInfo) obj).getUser();
	}
	
	/**
	 * 网站登录信息
	 * @author chail
	 * @date 2016-01-06
	 * @return
	 */
	public NetUserLoginInfo getNetUserLoginInfo() {
		Object obj = this.get(util.BaseParameter.WEB_LOGIN_INFO);
		if(obj == null || !(obj instanceof NetUserLoginInfo)){
			return null;
		}
		return (NetUserLoginInfo)obj;
	}

	protected String showDatetime(Date pidate) {
		return getFormateDate(pidate, "yyyy-MM-dd HH:mm");
	}

	protected String showDate(Date pidate) {
		return getFormateDate(pidate, "yyyy-MM-dd");
	}

	protected String showTime(Date pidate) {
		return getFormateDate(pidate, "HH:mm");
	}

	private String getFormateDate(Date pidate, String formate) {
		if (pidate == null)
			return null;
		else
			return (new SimpleDateFormat(formate)).format(pidate);
	}

	protected String strim(String s) {
		if (s != null)
			return s.trim();
		else
			return null;
	}

	private String requestURL;
	private int page;
	private int maxPage_;
	private int allRows;
	private int thisPageRows;
	private String pageCondition_;
	private int id;
	protected String DERROR;
	protected String DSUCCESS;

	public int getAllRows() {
		return allRows;
	}

	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}

	public int getThisPageRows() {
		return thisPageRows;
	}

	public void setThisPageRows(int thisPageRows) {
		this.thisPageRows = thisPageRows;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public void error(Object obj, Exception e) {
		try {
			// 获取异常信息内容
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));

			Loginfo loginfo = new Loginfo();
			loginfo.setActText(sw.toString());
			loginfo.setActTime(new Date());
			loginfo.setIp(getRequest().getRemoteAddr());
			if (obj != null) {
				loginfo.setUserName(((SysUser) obj).getUserName());
				loginfo.setUserOid(((SysUser) obj).getUserId());
			} else {
				if (this.get(util.BaseParameter.LOGIN_INFO) != null) {
					obj = this.get(util.BaseParameter.LOGIN_INFO);
					LoginUserInfo info = (LoginUserInfo) this
							.get(util.BaseParameter.LOGIN_INFO);
					loginfo.setUserName(info.getUserName());
					loginfo.setUserOid(info.getUserOid());
				}
			}
			loginfo.setType(1);
			if (BaseParameter.runMode.equals(BaseParameter.RUN_MODE_DEBUG)) {
				e.printStackTrace();
			}
			logService.insertObject(loginfo);
		} catch (Exception ex) {
			if (BaseParameter.runMode.equals(BaseParameter.RUN_MODE_DEBUG)) {
				e.printStackTrace();
			}
			System.out.println("错误日志记录失败！" + ex.toString());
		}
	}

	public void setPageParam(ResultPage rp) {
		if (rp == null)
			return;
		setMaxPage(rp.getMaxPage());
		setAllRows(rp.getAllRows());
		setThisPageRows(rp.getPageRows());
		setPages();
	}

	public String getPreURL(String mark) {
		if (mark == null) {
			return (String) get("preURL");
		} else {
			return (String) get("preURL" + mark);
		}
	}

	private String preURL;

	// add by zhujj 20130410
	// 备注：项目中返回按钮要求返回到之前带参数的页面
	// public void setPreURL(String preURL) {
	// String[] str = preURL.split(";");
	// set("preURL" + (str.length > 1 ? str[1] : ""), str.length > 1 ? str[0]
	// : preURL);
	// }

	public String getPreURL() {
		return preURL;
	}

	public void setPreURL(String preURL) {
		this.preURL = preURL;
	}

	// 相应ajax add by zhujj 20130412
	public void returnAjaxInfo(String str) throws Exception {
		ServletActionContext.getResponse().setContentType(
				"text/html; charset=gbk");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		out.print(str);
		out.flush();
		out.close();
	}

	public String oid;
	public String districtId;
	public String districtName;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public String getDistrictId() {
		if(districtId == null){
			districtId = BaseParameter.HEFEI_AREA_ID;
		}
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public List<SysArea> getAreaList() throws Exception{
		ISysAreaService sysAreaService = (ISysAreaService)Common.getService(ServletActionContext.getServletContext(),"sysAreaService");
		List<SysArea> areaList = sysAreaService.querySysAreaListByTopId(districtId);
		return areaList;
	}

	public String getDistrictName() throws Exception {
		if(districtName != null) return districtName;
		ISysAreaService sysAreaService = (ISysAreaService)Common.getService(ServletActionContext.getServletContext(),"sysAreaService");
		SysArea sysArea = sysAreaService.getSysAreaBytopId(districtId);
		districtName = sysArea == null ? "" : sysArea.getAreaName();
		return districtName;
	}

	public String getDistrictName_() throws Exception{
		if(getDistrictName() == null){
			return "";
		}
		return districtName.substring(0,districtName.length() - 1);
	}
	

}