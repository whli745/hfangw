package web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;
import web.pojo.*;
import web.service.IBuidingManageService;

/**
 * 楼盘管理
 * by zhujj 20160315
 */
public class BuidingManageAction extends BaseAction {

	private static final long serialVersionUID = -42010314316456007L;
	private ISysAreaService sysAreaService;
	private ISysDictService sysDictService;
	private IBuidingManageService buidingManageService;
	private String proj_type;//楼盘类型：1-最新楼盘，2-团购楼盘
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private String areaId;
	private String areaName;
	private HousingProject housingProj;
	private ResultPage rp;
	private HousingProject queryObj;
	private HfwKft hfwKft;
	private Date defaultDate;
	private HfwKftYy q;
	private HfwRealtyConsultant q_r;
	private HfwRealtyConsultant hfwRealtyConsultant;
	
	private String h_flag;
	
	private List<HousingProjectPrice> priceList;
	private List<RedPacket> packetList;
	
	/**
	 * 最新楼盘、团购楼盘 列表
	 */
	public String queryHousingProjectList(){
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
			
			if(queryObj == null){
				queryObj = new HousingProject();
			}
			queryObj.setArea_id(areaId);
			queryObj.setProj_type(proj_type);
			
			rp = buidingManageService.queryHousingProjectList(queryObj, getPage(),getPageRows());
			
			super.setPageParam(rp);
			
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到楼盘信息编制界面
	 */
	public String initHousingProjectEdit(){
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
			
			BaseParameter.hxflDictList = sysDictService.querySysDictList(BaseParameter.DICT_HXFL);
			BaseParameter.jzlxDictList = sysDictService.querySysDictList(BaseParameter.DICT_JZLX);
			BaseParameter.rxbqDictList = sysDictService.querySysDictList(BaseParameter.DICT_RXBQ);
			BaseParameter.lpdjDictList = sysDictService.querySysDictList(BaseParameter.DICT_LPDJ);
			BaseParameter.slztDictList = sysDictService.querySysDictList(BaseParameter.DICT_SLZT);
			
			housingProj = buidingManageService.getHousingProjectByOid(oid);
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 20);
			defaultDate = c.getTime();
			
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String housingProjectEdit(){
		try {
			buidingManageService.saveOrUpdateHousingProject(housingProj,hfwKft);
			buidingManageService.delAllHousingProjectPrice(housingProj.getOid());
			buidingManageService.delAllRedPacket(housingProj.getOid());
			for(int i=0;i<priceList.size();i++){
				HousingProjectPrice p = priceList.get(i);
				if(p == null)continue;
				p.setOid(null);
				p.setHousing_proj_id(housingProj.getOid());
				buidingManageService.saveOrUpdateHousingProjectPrice(p);
			}
			//添加红包
			for(int i=0;i<packetList.size();i++){
				RedPacket packet = packetList.get(i);
				if(packet == null || packet.getPacketName()==null || "".equals(packet.getPacketName())) continue;
				
				packet.setOid(null);
				packet.setHousePro(housingProj);
				packet.setIsDel(0);
				packet.setCreateTime(new Date());
				buidingManageService.saveOrUpdateRedPacket(packet);
			}

		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	public void delHousingProject(){
		try {
			
			HousingProject housingProj = buidingManageService.getHousingProjectByOid(oid);
			housingProj.setDel_flag(BaseParameter.YES);
			buidingManageService.saveOrUpdateHousingProject(housingProj);
			super.returnAjaxInfo(SUCCESS);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 预约看房
	 */
	public String yuyueKftFrames(){
		return SUCCESS;
	}
	
	/**
	 * 平台组织的看房团
	 */
	public String queryPlatKftList(){
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
			
			if(q == null){
				q = new HfwKftYy();
				q.setHandle_flag(BaseParameter.DATA_FILTER_1);
			}
			q.setArea_id(areaId);
			q.setYy_type(1);
			
			rp = buidingManageService.queryHfwKftYyList(q, 1, getPage(), getPageRows());
			
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	/**
	 * 标记为已处理
	 */
	public void markHandledAjax(){
		try {
			HfwKftYy y = buidingManageService.getHfwKftYyByOid(oid);
			y.setHandle_flag(h_flag);
			buidingManageService.saveOrUpdateHfwKftYy(y);
			super.returnAjaxInfo("1");
		} catch (Exception e) {
		}
	}
	
	/**
	 * 自主预约看房团
	 */
	public String queryPlanKftList(){
		try {
			if(q == null){
				q = new HfwKftYy();
				q.setHandle_flag(BaseParameter.DATA_FILTER_1);
			}
			q.setYy_type(2);
			rp = buidingManageService.queryHfwKftYyList(q, 2, getPage(), getPageRows());
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	/**
	 * 置业顾问列表
	 */
	public String queryRealtyConsultantList(){
		try {
			rp = buidingManageService.queryHfwRealtyConsultantList(q_r, getPage(), getPageRows());
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	public String initHfwRealtyConsultantEdit(){
		try {
			hfwRealtyConsultant = buidingManageService.gethfwRealtyConsultantByOid(oid);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	public String editHfwRealtyConsultant(){
		try {
			buidingManageService.saveOrUpdateHfwRealtyConsultant(hfwRealtyConsultant);
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	public void delHfwRealtyConsultantAjax(){
		try {
			buidingManageService.delHfwRealtyConsultant(oid);
			super.returnAjaxInfo(SUCCESS);
		} catch (Exception e) {
		}
	}
	
	public String getProj_type() {
		return proj_type;
	}

	public void setProj_type(String proj_type) {
		this.proj_type = proj_type;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
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

	public HousingProject getHousingProj() {
		return housingProj;
	}

	public void setHousingProj(HousingProject housingProj) {
		this.housingProj = housingProj;
	}


	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public IBuidingManageService getBuidingManageService() {
		return buidingManageService;
	}

	public void setBuidingManageService(IBuidingManageService buidingManageService) {
		this.buidingManageService = buidingManageService;
	}

	public ResultPage getRp() {
		return rp;
	}

	public void setRp(ResultPage rp) {
		this.rp = rp;
	}

	public HousingProject getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(HousingProject queryObj) {
		this.queryObj = queryObj;
	}

	public HfwKft getHfwKft() {
		return hfwKft;
	}

	public void setHfwKft(HfwKft hfwKft) {
		this.hfwKft = hfwKft;
	}

	public Date getDefaultDate() {
		return defaultDate;
	}

	public void setDefaultDate(Date defaultDate) {
		this.defaultDate = defaultDate;
	}

	public HfwKftYy getQ() {
		return q;
	}

	public void setQ(HfwKftYy q) {
		this.q = q;
	}

	public HfwRealtyConsultant getQ_r() {
		return q_r;
	}

	public void setQ_r(HfwRealtyConsultant q_r) {
		this.q_r = q_r;
	}

	public HfwRealtyConsultant getHfwRealtyConsultant() {
		return hfwRealtyConsultant;
	}

	public void setHfwRealtyConsultant(HfwRealtyConsultant hfwRealtyConsultant) {
		this.hfwRealtyConsultant = hfwRealtyConsultant;
	}

	public List<HousingProjectPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<HousingProjectPrice> priceList) {
		this.priceList = priceList;
	}

	public String getH_flag() {
		return h_flag;
	}

	public void setH_flag(String h_flag) {
		this.h_flag = h_flag;
	}

	public List<RedPacket> getPacketList() {
		return packetList;
	}

	public void setPacketList(List<RedPacket> packetList) {
		this.packetList = packetList;
	}
}
