package web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.wzgl.nrgl.pojo.InfoTcategory;
import pt.wzgl.nrgl.pojo.InfoTcontent;
import pt.wzgl.nrgl.service.IInfoTcategoryService;
import pt.wzgl.nrgl.service.IInfoTcontentService;
import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;
import web.pojo.HfwKftYy;
import web.pojo.HfwRealtyConsultant;
import web.pojo.HousingProject;
import web.pojo.SecondHandHouse;
import web.service.IBuidingManageService;
import web.service.ISecondHandHouseService;

/**
 * 网站首页管理
 */
public class WebIndexAction extends BaseAction {
	private static final long serialVersionUID = 4037269867637952679L;
	private IBuidingManageService buidingManageService;
	private ISysDictService sysDictService;
	private IInfoTcategoryService infoTcategoryService;//栏目信息管理service
	private IInfoTcontentService infoTcontentService;//内容发布信息管理service
	private ISecondHandHouseService secondHandHouseService;//
	private Map<String,List<HousingProject>> hp_map_1;
	private HousingProject housingProj;
	private HousingProject q;
	private SecondHandHouse shh_q;
	private SecondHandHouse secondHandHouse;
	private List<HfwRealtyConsultant> clist;
	private int bmrs;
	private List<InfoTcategory> infoTcategoryList;
	private List<InfoTcategory> infoTcategoryList_2;
	private ResultPage rp;
	private List<SecondHandHouse> shhList;
	private String columnId;
	private InfoTcontent infoTcontent;
	private String contentId;
	private String type;
	private List<InfoTcontent> ctList_1;//楼盘活动 
	private List<InfoTcontent> ctList_2;//市场动态 
	
	private List<HousingProject> hpList_1;//合肥最新楼盘 
	private List<HousingProject> hpList_2;//合肥购房团购排行 
	
	private String lxxm;
	private String sjhm;
	private String kft_id;
	
	/**
	 * 网站首页
	 */
	@SuppressWarnings("unchecked")
	public String initWebIndex() {
		try {
			hp_map_1 = new HashMap<String, List<HousingProject>>();
			HousingProject q = new HousingProject();
			String area_id = super.getDistrictId();
			List<SysArea> areaList = super.getAreaList();
			for(int i =0;i < areaList.size();i++){
				SysArea area = areaList.get(i);
				q.setArea_id(area.getAreaId());
				q.setProj_type("1");
				hp_map_1.put(area.getAreaId(), buidingManageService.queryHousingProjectList(q, 1, 6).getResultList());
			}
			InfoTcontent q_t = new InfoTcontent();
			q_t.setAreaId(area_id);
			q_t.setColumnId("402881fd515b4f9b01515b5914d40003");//楼盘活动
			ctList_1 = infoTcontentService.queryInfoTcontentList(q_t, 1, 5).getResultList();
			q_t.setColumnId("402881fd515b4f9b01515b5987390004");//市场动态
			ctList_2 = infoTcontentService.queryInfoTcontentList(q_t, 1, 3).getResultList();
			
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");
			q_hp.setArea_id(area_id);
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查看详情
	 */
	@SuppressWarnings("unchecked")
	public String showBuidingDetail(){
		try {
			String area_id = super.getDistrictId();
			housingProj = buidingManageService.getHousingProjectByOid(oid);
			
			boolean ck = Common.chkAttentionRecord(super.getIPAddress() + oid);
			if(ck){
				housingProj.setAttention(housingProj.getAttention() + 1);//关注度
				buidingManageService.saveOrUpdateHousingProject(housingProj);
			}
			
			clist = buidingManageService.queryHfwRealtyConsultantList(null, getPage(), getPageRows()).getResultList();
			HfwKftYy q = new HfwKftYy();
			q.setKft_id(housingProj.getHfwKft().getOid());
			bmrs = buidingManageService.queryHfwKftYyList(q, 1, 1, 999).getResultList().size();
			
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(area_id);
			ResultPage rp = buidingManageService.queryHousingProjectList(q_hp, 1, 4);
			int maxPage = rp.getMaxPage();
			int queryPage = 1;
			queryPage = Common.calculateQueryPage(oid,maxPage);
			//猜你喜欢
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, queryPage, 4).getResultList();
			q_hp.setProj_type("2");
			rp = buidingManageService.queryHousingProjectList(q_hp, 1, 4);
			maxPage = rp.getMaxPage();
			queryPage = Common.calculateQueryPage(oid,maxPage);
			//看了该楼盘的人还看过
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, queryPage, 4).getResultList();
			
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 最新快讯
	 */
	@SuppressWarnings("unchecked")
	public String newsFrames(){
		try {
			String area_id = super.getDistrictId();
			InfoTcategory q = new InfoTcategory();
			q.setParentId("402881fd515b4f9b01515b58cf050002");
			infoTcategoryList = infoTcategoryService.queryInfoTcategoryList(q, 1, 10).getResultList();
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(area_id);
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	
	/**
	 * 最新快讯
	 */
	public String newsList(){
		try {
			String area_id = super.getDistrictId();
			InfoTcontent q = new InfoTcontent();
			q.setColumnId(columnId);
			q.setAreaId(area_id);
			rp = infoTcontentService.queryInfoTcontentList(q, getPage(), getPageRows());
			super.setPageParam(rp);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 查看最新快讯
	 */
	@SuppressWarnings("unchecked")
	public String newsDetail(){
		try {
			String area_id = super.getDistrictId();
			infoTcontent = infoTcontentService.getInfoTcontent(contentId);
			HousingProject q_hp = new HousingProject();
			q_hp.setArea_id(area_id);
			q_hp.setProj_type("1");//最新开盘
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 最新快讯
	 */
	@SuppressWarnings("unchecked")
	public String newsAboutUsFrames(){
		try {
			String area_id = super.getDistrictId();
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(area_id);
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 查看最新快讯
	 */
	public String newsAboutUsDetail(){
		try {
			infoTcontent = infoTcontentService.getInfoTcontentList(columnId,super.getDistrictId()).get(0);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 预约看房
	 */
	@SuppressWarnings("unchecked")
	public void bookingRoomAjax(){
		try {
			HfwKftYy q = new HfwKftYy();
			q.setLxxm(lxxm);
			q.setPhone(sjhm);
			q.setKft_id(kft_id);
			q.setArea_id(super.getDistrictId());
			List<HfwKftYy> l = buidingManageService.queryHfwKftYyList(q, 1, 1, 1).getResultList();
			if(l.size()>0){
				super.returnAjaxInfo("0");
			}else{
				q.setHandle_flag(BaseParameter.NO);
				q.setYysj(new Date());
				buidingManageService.saveOrUpdateHfwKftYy(q);
				super.returnAjaxInfo("1");
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 楼盘搜索
	 */
	@SuppressWarnings("unchecked")
	public String buidingSearch(){
		try {
			
			BaseParameter.hxflDictList = sysDictService.querySysDictList(BaseParameter.DICT_HXFL);
			BaseParameter.jzlxDictList = sysDictService.querySysDictList(BaseParameter.DICT_JZLX);
			BaseParameter.rxbqDictList = sysDictService.querySysDictList(BaseParameter.DICT_RXBQ);
			BaseParameter.lpdjDictList = sysDictService.querySysDictList(BaseParameter.DICT_LPDJ);
			BaseParameter.slztDictList = sysDictService.querySysDictList(BaseParameter.DICT_SLZT);
			
			String area_id = super.getDistrictId();
			if(q == null){
				q = new HousingProject();
			}
			q.setProj_type(type);//最新开盘
//			q.setArea_id(area_id);
			if(Common.isNullOrSpace(q.getArea_id())){
				q.setArea_id(area_id);
			}
					
			rp = buidingManageService.queryHousingProjectList(q, getPage(), 30);
			super.setPageParam(rp);
			
			super.getRequest().setAttribute("querystring", super.getRequest().getQueryString());
			
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(area_id);
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");//团购楼盘
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	/**
	 * 二手房源搜索
	 */
	@SuppressWarnings("unchecked")
	public String houseSearch(){
		try{
			
			BaseParameter.esfsjDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_SJ);
			BaseParameter.esfmjDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_MJ);
			BaseParameter.esffxDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_FX);
			BaseParameter.esfjzndDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_JZND);
			BaseParameter.esflcDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_LC);
			BaseParameter.esffwlxDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_FWLX);
			
			String area_id = super.getDistrictId();
			if(shh_q == null){
				shh_q = new SecondHandHouse();
				shh_q.setArea_id(area_id);
			}
			rp = secondHandHouseService.querySecondHandHouseList(shh_q, getPage(), getPageRows());
			super.setPageParam(rp);
			
			super.getRequest().setAttribute("querystring", super.getRequest().getQueryString());
			
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(area_id);
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			q_hp.setProj_type("2");//团购楼盘
			hpList_2 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			
		}catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	/**
	 * 查看房源详细信息
	 */
	@SuppressWarnings("unchecked")
	public String showHouseDetail(){
		try{
			secondHandHouse = secondHandHouseService.getSecondHandHouseByOid(oid);
			
			String area_id = secondHandHouse.getArea_id();
			if(shh_q == null){
				shh_q = new SecondHandHouse();
			}
			shh_q.setOid(secondHandHouse.getOid());
			shh_q.setArea_id(area_id);
			shhList = secondHandHouseService.querySecondHandHouseList(shh_q, 1, 4).getResultList();
			
			HousingProject q_hp = new HousingProject();
			q_hp.setProj_type("1");//最新开盘
			q_hp.setArea_id(super.getDistrictId());
			hpList_1 = buidingManageService.queryHousingProjectList(q_hp, 1, 10).getResultList();
			
		}catch (Exception e) {
			
		}
		return SUCCESS;
	}

	public IBuidingManageService getBuidingManageService() {
		return buidingManageService;
	}

	public void setBuidingManageService(IBuidingManageService buidingManageService) {
		this.buidingManageService = buidingManageService;
	}

	public Map<String, List<HousingProject>> getHp_map_1() {
		return hp_map_1;
	}

	public void setHp_map_1(Map<String, List<HousingProject>> hp_map_1) {
		this.hp_map_1 = hp_map_1;
	}

	public HousingProject getHousingProj() {
		return housingProj;
	}

	public void setHousingProj(HousingProject housingProj) {
		this.housingProj = housingProj;
	}


	public List<HfwRealtyConsultant> getClist() {
		return clist;
	}

	public void setClist(List<HfwRealtyConsultant> clist) {
		this.clist = clist;
	}

	public int getBmrs() {
		return bmrs;
	}

	public void setBmrs(int bmrs) {
		this.bmrs = bmrs;
	}

	public IInfoTcategoryService getInfoTcategoryService() {
		return infoTcategoryService;
	}

	public void setInfoTcategoryService(IInfoTcategoryService infoTcategoryService) {
		this.infoTcategoryService = infoTcategoryService;
	}

	public List<InfoTcategory> getInfoTcategoryList() {
		return infoTcategoryList;
	}

	public void setInfoTcategoryList(List<InfoTcategory> infoTcategoryList) {
		this.infoTcategoryList = infoTcategoryList;
	}

	public IInfoTcontentService getInfoTcontentService() {
		return infoTcontentService;
	}

	public void setInfoTcontentService(IInfoTcontentService infoTcontentService) {
		this.infoTcontentService = infoTcontentService;
	}

	public ResultPage getRp() {
		return rp;
	}

	public void setRp(ResultPage rp) {
		this.rp = rp;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public InfoTcontent getInfoTcontent() {
		return infoTcontent;
	}

	public void setInfoTcontent(InfoTcontent infoTcontent) {
		this.infoTcontent = infoTcontent;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<InfoTcontent> getCtList_1() {
		return ctList_1;
	}

	public void setCtList_1(List<InfoTcontent> ctList_1) {
		this.ctList_1 = ctList_1;
	}

	public List<InfoTcontent> getCtList_2() {
		return ctList_2;
	}

	public void setCtList_2(List<InfoTcontent> ctList_2) {
		this.ctList_2 = ctList_2;
	}

	public List<HousingProject> getHpList_1() {
		return hpList_1;
	}

	public void setHpList_1(List<HousingProject> hpList_1) {
		this.hpList_1 = hpList_1;
	}

	public List<HousingProject> getHpList_2() {
		return hpList_2;
	}

	public void setHpList_2(List<HousingProject> hpList_2) {
		this.hpList_2 = hpList_2;
	}

	@SuppressWarnings("unchecked")
	public List<InfoTcategory> getInfoTcategoryList_2() {
		try {
			InfoTcategory q = new InfoTcategory();
			q.setParentId("4028808153d6b5790153d7054ea00003");
			infoTcategoryList_2 = infoTcategoryService.queryInfoTcategoryList(q, 1, 10).getResultList();
		} catch (Exception e) {
		}
		return infoTcategoryList_2;
	}
	
	

	public void setInfoTcategoryList_2(List<InfoTcategory> infoTcategoryList_2) {
		this.infoTcategoryList_2 = infoTcategoryList_2;
	}

	public String getLxxm() {
		return lxxm;
	}

	public void setLxxm(String lxxm) {
		this.lxxm = lxxm;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getKft_id() {
		return kft_id;
	}

	public void setKft_id(String kft_id) {
		this.kft_id = kft_id;
	}

	public HousingProject getQ() {
		return q;
	}

	public void setQ(HousingProject q) {
		this.q = q;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public ISecondHandHouseService getSecondHandHouseService() {
		return secondHandHouseService;
	}

	public void setSecondHandHouseService(
			ISecondHandHouseService secondHandHouseService) {
		this.secondHandHouseService = secondHandHouseService;
	}

	public SecondHandHouse getShh_q() {
		return shh_q;
	}

	public void setShh_q(SecondHandHouse shh_q) {
		this.shh_q = shh_q;
	}

	public SecondHandHouse getSecondHandHouse() {
		return secondHandHouse;
	}

	public void setSecondHandHouse(SecondHandHouse secondHandHouse) {
		this.secondHandHouse = secondHandHouse;
	}

	public List<SecondHandHouse> getShhList() {
		return shhList;
	}

	public void setShhList(List<SecondHandHouse> shhList) {
		this.shhList = shhList;
	}



}
