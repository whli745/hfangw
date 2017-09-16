package pt.wzgl.nrgl.service.impl;

import java.util.Date;
import java.util.List;

import pt.wzgl.nrgl.dao.IinfoTcontentDao;
import pt.wzgl.nrgl.pojo.FileAtta;
import pt.wzgl.nrgl.pojo.InfoTcontent;
import pt.wzgl.nrgl.service.IInfoTcontentService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.service.BaseServiceImpl;
import web.dao.IRedPacketDao;
import web.pojo.RedPacket;
/**
 * 发布内容信息管理 service
 * @author Administrator
 *
 */
public class InfoTcontentServiceImpl extends BaseServiceImpl<InfoTcontent> implements IInfoTcontentService {
	
	private IinfoTcontentDao infoTcontentDao;//内容发布信息管理dao
	private IRedPacketDao redPacketDao;

	@Override
	public ResultPage queryInfoTcontentList(String delFlag,String districtFlag,String areaId,
			String contentMainTitle, InfoTcontent infoTcontent, int page,
			int pageRows) throws Exception {
		return infoTcontentDao.queryInfoTcontentList(delFlag,districtFlag,areaId,contentMainTitle,infoTcontent,page,pageRows);
	}

	public IinfoTcontentDao getInfoTcontentDao() {
		return infoTcontentDao;
	}

	public void setInfoTcontentDao(IinfoTcontentDao infoTcontentDao) {
		this.infoTcontentDao = infoTcontentDao;
	}

	public IRedPacketDao getRedPacketDao() {
		return redPacketDao;
	}

	public void setRedPacketDao(IRedPacketDao redPacketDao) {
		this.redPacketDao = redPacketDao;
	}

	@Override
	public InfoTcontent getInfoTcontent(String contentId) throws Exception {
		return super.onGet(contentId);
	}
	
	@Override
	public void saveInfoTcontent(InfoTcontent infoTcontent,FileAtta fileAtta,String userId) throws Exception {
		if (infoTcontent == null)
			return;
		if (Common.isNullOrSpace(infoTcontent.getContentId()))
			infoTcontent.setContentId(null);
		infoTcontent.setCreateBy(userId);
		infoTcontent.setCreateDate(new Date());
		infoTcontent.setIssurDate(new Date());//发布时间
		
		// 不需要审核
		if (!Common.isNullOrSpace(infoTcontent.getNeedCheck())
				&& infoTcontent.getNeedCheck().trim()
						.equals(BaseParameter.NO)) {
			infoTcontent.setCheckFlag(null);// 无需审核的
			infoTcontent.setCheckDate(null);// 审核时间
			infoTcontent.setCheckBy(null);// 审核人
		} else {
			infoTcontent.setCheckFlag(BaseParameter.NO);// 未审核
			infoTcontent.setCheckDate(null);// 审核时间清空
			infoTcontent.setCheckBy(null);// 审核人清空
		}
		infoTcontent.setCheckOpinion("");// 审核意见清空
		
		// 是否启用
		if (!Common.isNullOrSpace(infoTcontent.getUsingFlag())
				&& infoTcontent.getUsingFlag().trim()
						.equals(BaseParameter.NO)) {
			infoTcontent.setUsingFlag(BaseParameter.NO);

		} else {
			infoTcontent.setUsingFlag(BaseParameter.YES);
		}
		
		infoTcontent.setDelFlag(BaseParameter.NO);// 不删除
		if (fileAtta != null && !Common.isNullOrSpace(fileAtta.getAttaId()))
			infoTcontent.setAttaId(fileAtta.getAttaId());
		infoTcontentDao.saveInfoTcontent(infoTcontent);
		
	}

	@Override
	public void deleteInfoTcontent(String contentId) throws Exception {
		InfoTcontent infoTcontent = this.getInfoTcontent(contentId);
		infoTcontent.setDelFlag(BaseParameter.YES);
		infoTcontentDao.saveInfoTcontent(infoTcontent);
	}

	@Override
	public void usingInfoTcontent(String contentId, String toUsingFlag)
			throws Exception {
		//得到内容发布信息管理对象
		InfoTcontent infoTcontent = this.getInfoTcontent(contentId);
		if(infoTcontent == null) return;
		//修改启用标志
		infoTcontent.setUsingFlag(toUsingFlag);
		infoTcontentDao.saveInfoTcontent(infoTcontent);
		
	}

	@Override
	public void batchEnableInfoTcontent(String contentIds, String toUsingFlag)
			throws Exception {
		if (Common.isNullOrSpace(contentIds))
			return;
		String[] arrContentId = contentIds.split(",");
		if (arrContentId == null || arrContentId.length <= 0)
			return;
		String contentId = "";
		InfoTcontent infoTcontent = null;
		for (int i = 0; i < arrContentId.length; i++) {
			contentId = arrContentId[i];// 得到主键
			if (Common.isNullOrSpace(contentId))
				continue;
			infoTcontent = this.getInfoTcontent(contentId);// 通过主键得到对象
			// 将当前对象改成启用/禁用状态
			if (!toUsingFlag.equals(BaseParameter.AREA_LEVEL_2)) // 2 表示删除了
				infoTcontent.setUsingFlag(toUsingFlag);
			else
				infoTcontent.setDelFlag(BaseParameter.YES);
			infoTcontentDao.saveInfoTcontent(infoTcontent);
		}
	}

	@Override
	public void shiftDeleteInfoTcontent(String contentId) throws Exception {
		infoTcontentDao.shiftDeleteInfoTcontent(contentId);
	}

	@Override
	public void restoreInfoTcontent(String contentId) throws Exception {
		InfoTcontent infoTcontent = this.getInfoTcontent(contentId);
		// 修改删除标志
		infoTcontent.setDelFlag(BaseParameter.NO);
		// 状态改成禁用状态
		infoTcontent.setUsingFlag(BaseParameter.NO);// 禁用
		// 不需要审核
		if (!Common.isNullOrSpace(infoTcontent.getNeedCheck())
				&& infoTcontent.getNeedCheck().trim()
						.equals(BaseParameter.YES)) {// 看是否需要审核
			infoTcontent.setCheckFlag(BaseParameter.YES);// 已审核
			infoTcontent.setCheckDate(new Date());// 审核时间

		} else {
			infoTcontent.setCheckFlag(BaseParameter.NO);// 未审核
			infoTcontent.setCheckDate(null);// 审核时间清空
			infoTcontent.setCheckBy("");// 审核人清空
		}
		infoTcontent.setCheckOpinion("");// 审核意见清空
		infoTcontentDao.saveInfoTcontent(infoTcontent);
	}

	@Override
	public void batchRestoreInfoContent(String contentIds) throws Exception {
		if (Common.isNullOrSpace(contentIds))
			return;
		String[] arrContentId = contentIds.split(",");
		if (arrContentId == null || arrContentId.length <= 0)
			return;
		String contentId = "";
		InfoTcontent infoTcontent = null;
		for (int i = 0; i < arrContentId.length; i++) {
			contentId = arrContentId[i];// 得到主键
			if (Common.isNullOrSpace(contentId))
				continue;
			infoTcontent = this.getInfoTcontent(contentId);
			// 修改删除标志
			infoTcontent.setDelFlag(BaseParameter.NO);
			// 状态改成禁用状态
			infoTcontent.setUsingFlag(BaseParameter.NO);// 禁用
			// 不需要审核
			if (!Common.isNullOrSpace(infoTcontent.getNeedCheck())
					&& infoTcontent.getNeedCheck().trim()
							.equals(BaseParameter.YES)) {// 看是否需要审核
				infoTcontent.setCheckFlag(BaseParameter.YES);// 已审核
				infoTcontent.setCheckDate(new Date());// 审核时间

			} else {
				infoTcontent.setCheckFlag(BaseParameter.NO);// 未审核
				infoTcontent.setCheckDate(null);// 审核时间清空
				infoTcontent.setCheckBy("");// 审核人清空
			}
			infoTcontent.setCheckOpinion("");// 审核意见清空
			infoTcontentDao.saveInfoTcontent(infoTcontent);
		}
		
	}

	@Override
	public void emptyRecycleInfoTcontent() throws Exception {
		infoTcontentDao.emptyRecycleInfoTcontent();
	}

	@Override
	public void addOrUpdateAuditInfoContent(String contentId, String checkFlag,
			String checkOpinion, String userId) throws Exception {
		// 得到发布内容管理对象
		InfoTcontent infoTcontent = this.getInfoTcontent(contentId);
		if (infoTcontent == null)
			return;
		infoTcontent.setCheckFlag(checkFlag);// 审核结果
		infoTcontent.setCheckOpinion(checkOpinion);// 审核意见
		infoTcontent.setCheckBy(userId);// 审核人
		infoTcontent.setCheckDate(new Date());// 审核时间
		infoTcontentDao.saveInfoTcontent(infoTcontent);
		
	}

	@Override
	public void batchAuditInfoTcontent(String contentIds, String checkFlag,
			String userId) throws Exception {
		if (Common.isNullOrSpace(contentIds) || Common.isNullOrSpace(checkFlag))
			return;
		String[] arrContentId = contentIds.split(",");
		if (arrContentId == null || arrContentId.length <= 0)
			return;
		String contentId = "";
		InfoTcontent infoTcontent = null;
		for (int i = 0; i < arrContentId.length; i++) {
			contentId = arrContentId[i];// 得到主键
			if (Common.isNullOrSpace(contentId))
				continue;
			infoTcontent = this.getInfoTcontent(contentId);
			infoTcontent.setCheckFlag(checkFlag.trim());// 审核状态
			infoTcontent.setCheckDate(new Date());// 审核时间
			infoTcontent.setCheckBy(userId);// 审核人
			// 审核意见
			infoTcontent.setCheckOpinion(checkFlag.equals(BaseParameter.AREA_LEVEL_2) ? BaseParameter.CHECK_MESSAGE_NO: "");
			
			infoTcontentDao.saveInfoTcontent(infoTcontent);
		}
		
	}

	@Override
	public List<InfoTcontent> getAllInfoTcontents() throws Exception {
		return infoTcontentDao.getAllInfoTcontents();
	}

	@Override
	public List<InfoTcontent> getInfoTcontentList(String columnId, String areaId) throws Exception {
		return infoTcontentDao.getInfoTcontentList(columnId, areaId);
	}
	
	
	
	public ResultPage getInfoTcontentPage(String columnId, String areaId,int page,int pageRows)
			throws Exception {
		return infoTcontentDao.getInfoTcontentPage(columnId, areaId,page,pageRows);
	}

	@Override
	public List<InfoTcontent> getInfoTcontentList(InfoTcontent infoTcontent)
			throws Exception {
		return infoTcontentDao.getInfoTcontentList(infoTcontent);
	}

	@Override
	public ResultPage queryInfoTcontentList(String delFlag, String areaId,
			String columnId, int page, int pageRows) throws Exception {
		return infoTcontentDao.queryInfoTcontentList(delFlag,areaId,columnId,page,pageRows);
	}
	@Override
	public ResultPage queryLastInfoTcontentList(
			InfoTcontent infoTcontent, String areaId, String columnId,
			Integer nowCount, String lnFlag, int page, int pageRows)
			throws Exception {
		return infoTcontentDao.queryLastInfoTcontentList(infoTcontent,areaId,
				columnId,nowCount,lnFlag,page,pageRows);
	}

	@Override
	public void saveOrUpdateInfoTcontent(InfoTcontent infoTcontent) throws Exception {
		infoTcontentDao.saveInfoTcontent(infoTcontent);
	}

	@Override
	public ResultPage queryInfoTcontentList(InfoTcontent infoTcontent,
			int page, int pageRows) throws Exception {
		return infoTcontentDao.queryInfoTcontentList(infoTcontent, page, pageRows);
	}

	@Override
	public ResultPage getInfoTcontentAttaPage(String columnId, String areaId,
			int page, int pageRows) throws Exception {
		return infoTcontentDao.getInfoTcontentAttaPage(columnId, areaId, page, pageRows);
	}

	@Override
	public ResultPage getInfoTcontentListForMap(String columnId,
			String areaId,int page,int pageRows) throws Exception {
		return infoTcontentDao.getInfoTcontentListForMap(columnId, areaId,page,pageRows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMapLocalSearch(String themeId, String areaId, int page,
			int pageRows, String name) throws Exception {
		ResultPage rp = infoTcontentDao.getMapLocalSearch(themeId, areaId,
				page, pageRows, name);
		List<InfoTcontent> results = rp.getResultList();
		StringBuffer sb = new StringBuffer();
		String returnStr = "";

		if (results != null && results.size() > 0) {
			sb.append("[");
			InfoTcontent pojo = null;
			for (int i = 0; i < results.size(); i++) {
				pojo = results.get(i);
				sb.append("{\"id\":\"").append(pojo.getContentId())
						.append("\", \"departmenName\":\"")
						.append(pojo.getDepartmenName())
						.append("\",\"zipCode\":\"").append(pojo.getZipCode())
						.append("\", \"workDate\":\"")
						.append(pojo.getWorkDate())
						.append("\", \"contactPhone\": \"")
						.append(pojo.getContactPhone())
						.append("\", \"contactPerson\": \"")
						.append(pojo.getContactPerson())
						.append("\", \"complaintsPhone\": \"")
						.append(pojo.getComplaintsPhone())
						.append("\", \"belongArea\": \"")
						.append(pojo.getBelongArea())
						.append("\", \"address\": \"")
						.append(pojo.getAddress())
						//.append("\", \"introduction\": \"")
						//.append(pojo.getIntroduction())
						.append("\", \"lng\": \"").append(pojo.getLng())
						.append("\", \"lat\": \"").append(pojo.getLat())
						.append("\", \"allRows\": \"").append(rp.getAllRows())
						.append("\", \"maxpages\": \"").append(rp.getMaxPage())
						.append("\", \"currentPage\": \"")
						.append(rp.getCurrentPage()).append("\"},");
			}
			returnStr = sb.toString();
			if (results.size() > 0) {// 去掉最后一个,
				returnStr = returnStr.substring(0, returnStr.length() - 1);
			}
			returnStr += "]";
		} else {
			sb.append("[]");
			returnStr = sb.toString();
		}
		return returnStr;
	}

	@Override
	public String getMapLocalSearch(String themeId, String areaId, String name)
			throws Exception {
		List<InfoTcontent> results = infoTcontentDao.getMapLocalSearch(themeId, areaId, name);
		StringBuffer sb = new StringBuffer();
		String returnStr = "";

		if (results != null && results.size() > 0) {
			sb.append("[");
			InfoTcontent pojo = null;
			for (int i = 0; i < results.size(); i++) {
				pojo = results.get(i);
				sb.append("{\"id\":\"").append(pojo.getContentId())
						.append("\", \"departmenName\":\"")
						.append(pojo.getDepartmenName())
						.append("\",\"zipCode\":\"").append(pojo.getZipCode())
						.append("\", \"workDate\":\"")
						.append(pojo.getWorkDate())
						.append("\", \"contactPhone\": \"")
						.append(pojo.getContactPhone())
						.append("\", \"contactPerson\": \"")
						.append(pojo.getContactPerson())
						.append("\", \"complaintsPhone\": \"")
						.append(pojo.getComplaintsPhone())
						.append("\", \"belongArea\": \"")
						.append(pojo.getBelongArea())
						.append("\", \"address\": \"")
						.append(pojo.getAddress())
						//.append("\", \"introduction\": \"")
						//.append(pojo.getIntroduction())
						.append("\", \"lng\": \"").append(pojo.getLng())
						.append("\", \"lat\": \"").append(pojo.getLat())
						.append("\"},");
			}
			returnStr = sb.toString();
			if (results.size() > 0) {// 去掉最后一个,
				returnStr = returnStr.substring(0, returnStr.length() - 1);
			}
			returnStr += "]";
		} else {
			sb.append("[]");
			returnStr = sb.toString();
		}
		return returnStr;
	}

	@Override
	public List<InfoTcontent> getInfoTcontentPage(List<String> tcs,String areaId) throws Exception {
		return infoTcontentDao.getInfoTcontentPage(tcs, areaId);
	}

	@Override
	public ResultPage getInfoTcontentsBycolumnIdAndDictId(InfoTcontent infoTcontent,String columnId,
			String areaId, int page, int pageRows) throws Exception {
		return infoTcontentDao.getInfoTcontentsBycolumnIdAndDictId(infoTcontent,columnId, areaId, page, pageRows);
	}

	@Override
	public List<InfoTcontent> getExposureTableToWebIndex(String districtId)
			throws Exception {
		return infoTcontentDao.getExposureTableToWebIndex(districtId);
	}

	@Override
	public List<InfoTcontent> getNewsDynamicToWebIndex(InfoTcontent infoTcontent,String districtId,Integer topNumber,String TcontentType)
			throws Exception {
		return infoTcontentDao.getNewsDynamicToWebIndex(infoTcontent,districtId,topNumber,TcontentType);
	}

	@Override
	public List<InfoTcontent> getInfoTcontentToWebListByPage(int topNumber,
			String infoTcontentParentId,String searchKeyWord) throws Exception {
		return infoTcontentDao.getInfoTcontentToWebListByPage(topNumber, infoTcontentParentId,searchKeyWord);
	}

	@Override
	public ResultPage getInfoTcontentBySearch(InfoTcontent infoTcontent,
			int page, int pageRows) throws Exception {
		return infoTcontentDao.getInfoTcontentBySearch(infoTcontent, page, pageRows);
	}

	@Override
	public void saveOrUpdateRedPacket(RedPacket packet) throws Exception {
		redPacketDao.saveOrUpdateRedPacket(packet);
	}

	@Override
	public void delAllRedPacket(String houseId) throws Exception {
		String hql = "update RedPacket set isDel = 1 where newId = ?";
		redPacketDao.delAllRedPacket(hql,houseId);
		
	}
}
