package pt.wzgl.nrgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import pt.wzgl.nrgl.dao.IinfoTcontentDao;
import pt.wzgl.nrgl.pojo.InfoTcontent;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

/**
 * 发布内容信息管理 dao
 * @author Administrator
 *
 */
public class InfoTcontentDaoImpl extends BaseDaoImpl<InfoTcontent> implements IinfoTcontentDao {

	@Override
	public ResultPage queryInfoTcontentList(String delFlag,String districtFlag,String areaId,
			String contentMainTitle, InfoTcontent infoTcontent, int page,
			int pageRows) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from pt.wzgl.nrgl.pojo.InfoTcontent t where t.delFlag = ?");
		paramList.add(delFlag);
		
		if(!Common.isNullOrSpace(contentMainTitle)){
			hql.append(" and t.contentMainTitle like ?");
			paramList.add("%" + contentMainTitle.trim() + "%");
		}
		
		if(!Common.isNullOrSpace(areaId)){
			if(BaseParameter.NO.equals(districtFlag)){
				hql.append(" and t.areaId=? ");
				paramList.add(areaId.trim());
			}else{
				hql.append(" and (t.areaId=? or t.sysArea.areaPath like ?)");
				paramList.add(areaId.trim());
				paramList.add("%" + areaId.trim() + "%");
			}
		}
		
		if(null!=infoTcontent ){
			if (!Common.isNullOrSpace(infoTcontent.getColumnId())) {
				hql.append(" and (t.columnId = ? or t.infoTcategory.columnPath like ?)");
				paramList.add(infoTcontent.getColumnId());
				paramList.add("%" + infoTcontent.getColumnId() + "%");
			}
			if(null !=infoTcontent.getCheckFlags()){
				int listSize = infoTcontent.getCheckFlags().size();
				if(listSize>0){
					if(listSize ==1){
						hql.append(" and t.checkFlag = ? ");
						paramList.add(infoTcontent.getCheckFlags().get(0));
					}else if(listSize==2){
						hql.append(" and (t.checkFlag = ? ");
						paramList.add(infoTcontent.getCheckFlags().get(0));
						hql.append(" or t.checkFlag = ? ");
						paramList.add(infoTcontent.getCheckFlags().get(1));
						hql.append(")");
					}
				}
			}
		}
		hql.append(" order by t.isTop desc, t.createDate desc");
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}

	@Override
	public InfoTcontent getInfoTcontent(String contentId) throws Exception {
		return super.onLoad(contentId);
	}

	@Override
	public void saveInfoTcontent(InfoTcontent infoTcontent) throws Exception {
		super.saveOrUpdate(infoTcontent);
	}

	@Override
	public void shiftDeleteInfoTcontent(String contentId) throws Exception {
		super.delete(contentId);
	}

	@Override
	public void emptyRecycleInfoTcontent() throws Exception {
		String sql = "DELETE FROM INFO_TCONTENT WHERE DEL_FLAG="
			+ BaseParameter.YES;
		super.executeUpdateSql(sql);
		
	}

	@Override
	public List<InfoTcontent> getAllInfoTcontents() throws Exception {
		String hql = "from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and (t.needCheck = ? or (t.needCheck = ? and t.checkFlag = ?)) and t.usingFlag = ?";
		
		return super.queryByHql(hql, new Object[]{BaseParameter.NO, BaseParameter.NO, BaseParameter.YES, BaseParameter.YES, BaseParameter.YES});
	}

	@Override
	public List<InfoTcontent> getInfoTcontentList(String columnId, String areaId)
			throws Exception {
		String hql = "from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and (t.needCheck = ? or (t.needCheck = ? and t.checkFlag = ?)) and t.usingFlag = ? and t.columnId = ? and t.areaId = ? order by t.isTop desc, t.createDate desc ";
		return super.queryByHql(hql, new Object[] { BaseParameter.NO,
				BaseParameter.NO, BaseParameter.YES, BaseParameter.YES,
				BaseParameter.YES, columnId, areaId });
	}
		
	public ResultPage getInfoTcontentPage(String columnId, String areaId,
			int page, int pageRows) throws Exception {
		String hql = "from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) " +
				"and (t.needCheck = ? or (t.needCheck = ? and t.checkFlag = ?)) " +
				"and t.usingFlag = ? and t.columnId = ? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(columnId);
		
		if(!Common.isNullOrSpace(areaId)) {
			hql += " and t.areaId = ?";
			paramList.add(areaId);
		}
		hql += " order by isTop desc, createDate desc";
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}
	
	public ResultPage getInfoTcontentAttaPage(String columnId, String areaId,
			int page, int pageRows) throws Exception {
		String hql = "from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) " +
				"and (needCheck = ? or (needCheck = ? and checkFlag = ?)) " +
				"and t.usingFlag = ? and t.columnId = ?  and t.areaId = ? and  isTop = ? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(columnId);
		paramList.add(areaId);
		paramList.add(BaseParameter.YES);
		
		hql += " order by isTop desc, createDate desc";
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}
	
	@Override
	public List<InfoTcontent> getInfoTcontentList(InfoTcontent infoTcontent) throws Exception {
		StringBuilder sb = new StringBuilder("");
		List<Object> params = new ArrayList<Object>(); 
		sb.append("from InfoTcontent t where t.delFlag = ? and t.usingFlag = ? and (t.needCheck = ? or (t.needCheck = ? and checkFlag = ?)) ");
		params.add(BaseParameter.NO);
		params.add(BaseParameter.YES);
		params.add(BaseParameter.NO);
		params.add(BaseParameter.YES);
		params.add(BaseParameter.YES);
		
		if (!Common.isNullOrSpace(infoTcontent.getAreaId())) {
			sb.append(" and t.areaId = ? ");
			params.add(infoTcontent.getAreaId());
		}
		sb.append(" order by isTop desc, createDate desc");
		
 		return super.queryByHql(sb.toString(), params);
	}

	@Override
	public ResultPage queryInfoTcontentList(String delFlag, String areaId,
			String columnId, int page, int pageRows) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from InfoTcontent t where t.delFlag = ? and (t.needCheck = ? or (t.needCheck = ? and t.checkFlag = ?)) and t.usingFlag = ? ");
		paramList.add(delFlag);
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		if(!Common.isNullOrSpace(areaId)){
			hql.append(" and t.areaId=?");
			paramList.add(areaId.trim());
		}
		
		if (!Common.isNullOrSpace(columnId)) {
			hql.append(" and t.columnId = ?");
			paramList.add(columnId.trim());
		}
		// 时间有效
		/*String strDate = DateTools.getDate();
		Date nowDate = DateTools.strToDate(strDate, "yyyy-MM-dd");
		hql.append(" and (t.issurDate<= ? or t.issurDate='' or t.issurDate is null)");
		hql.append(" and (t.loseDate>= ? or t.loseDate='' or t.loseDate is null)");
		paramList.add(nowDate);
		paramList.add(nowDate);*/
		hql.append(" order by t.isTop desc, t.createDate desc");
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}

	@Override
	public ResultPage queryLastInfoTcontentList(
			InfoTcontent infoTcontent, String areaId, String columnId,
			Integer nowCount, String lnFlag, int page, int pageRows)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"from InfoTcontent w where w.delFlag=?");
		params.add(BaseParameter.NO);
		// 审核通过
		hql.append(" and (w.checkFlag=? or w.checkFlag is null)");
		params.add(BaseParameter.YES);
		// 所属栏目
		if (!Common.isNullOrSpace(columnId)) {
			hql.append("and w.columnId = ?");
			params.add(columnId.trim());
		}
		// 所属区划
		if (!Common.isNullOrSpace(areaId)) {
			hql.append("and w.areaId = ?");
			params.add(areaId.trim());
		}
		// 状态 启用
		hql.append(" and w.usingFlag=?");
		params.add(BaseParameter.YES);
		if (BaseParameter.YES.equals(infoTcontent.getIsTop())) {
			if (lnFlag.trim().equals(BaseParameter.NO)) {// 上一篇
				hql.append(" and w.isTop = ? and w.createDate > (select it.createDate from InfoTcontent it where it.contentId=?)");
				params.add(BaseParameter.YES);
				params.add(infoTcontent.getContentId().trim());
				hql.append(" order by w.isTop desc, w.createDate ");
			}
			if (lnFlag.trim().equals(BaseParameter.YES)) {// 下一篇
				String hqlDown = hql.toString();
				hqlDown += " and w.isTop = ? and w.createDate< (select it.createDate from InfoTcontent it where it.contentId=?) order by w.isTop desc, w.createDate desc";
				params.add(BaseParameter.YES);
				params.add(infoTcontent.getContentId().trim());
				ResultPage rpDown = super.getResultPage(hqlDown, params, page, pageRows);
				if(rpDown.getAllRows() > 0) {
					return rpDown;
				} else {
					hqlDown = hql.toString();
					hqlDown += " and w.isTop = ? order by w.isTop desc, w.createDate desc ";
					params.remove(params.size()-1);
					params.remove(params.size()-1);
					params.add(BaseParameter.NO);
					rpDown = super.getResultPage(hqlDown, params, page, pageRows);
					return rpDown;
				}
			}
		}
 		if (!Common.isNullOrSpace(infoTcontent.getIsTop())&&infoTcontent.getIsTop().trim().equals(BaseParameter.NO)) {
			if (lnFlag.trim().equals(BaseParameter.NO)) {// 上一篇
				String hqlDown = hql.toString();
				hqlDown += " and w.isTop = ? and w.createDate > (select it.createDate from InfoTcontent it where it.contentId=?) order by w.isTop desc, w.createDate ";
				params.add(BaseParameter.NO);
				params.add(infoTcontent.getContentId().trim());
				ResultPage rpDown = super.getResultPage(hqlDown, params, page, pageRows);
				if(rpDown.getAllRows() > 0) {
					return rpDown;
				} else {
					hqlDown = hql.toString();
					hqlDown += " and w.isTop = ?  order by w.isTop desc, w.createDate ";
					params.remove(params.size()-1);
					params.remove(params.size()-1);
					params.add(BaseParameter.YES);
					rpDown = super.getResultPage(hqlDown, params, page, pageRows);
					return rpDown;
				}
			}
			if (lnFlag.trim().equals(BaseParameter.YES)) {// 下一篇
				hql.append(" and w.isTop = ? and w.createDate < (select it.createDate from InfoTcontent it where it.contentId=?)");
				params.add(BaseParameter.NO);
				params.add(infoTcontent.getContentId().trim());
				hql.append(" order by w.isTop desc, w.createDate desc");
			}
		}
		ResultPage rp = super.getResultPage(hql.toString(), params, page,
				pageRows);
		return rp;
	}

	@Override
	public ResultPage queryInfoTcontentList(InfoTcontent infoTcontent,
			int page, int pageRows) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and ((t.needCheck is null or t.needCheck = ?) or (t.needCheck = ? and t.checkFlag = ?)) and t.usingFlag = ? ");
		
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		paramList.add(BaseParameter.YES);
		if(!Common.isNullOrSpace(infoTcontent.getAreaId())){
			hql.append(" and t.areaId=?");
			paramList.add(infoTcontent.getAreaId().trim());
		}
		
		if (!Common.isNullOrSpace(infoTcontent.getColumnId())) {
			String[] columnId = infoTcontent.getColumnId().split(",");
			String temp = "";
			int count = 0;
			for (int i=0; i<columnId.length; i++) {
				temp += "?";
				if (++count < columnId.length) {
					temp +=",";
				}
			}
			if (columnId != null && columnId.length > 0) {
				hql.append(" and t.columnId in (" + temp + ")");
				for (int i=0; i<columnId.length; i++) {
					paramList.add(columnId[i]);
					
				}
			}
		}
		
		if (!Common.isNullOrSpace(infoTcontent.getJumpUrlFlag())) {
			if (BaseParameter.NO.equals(infoTcontent.getJumpUrlFlag())) {
				hql.append(" and (t.jumpUrlFlag is null or t.jumpUrlFlag = ?) ");
			} else {
				hql.append(" and t.jumpUrlFlag = ? ");
			}
			
			paramList.add(infoTcontent.getJumpUrlFlag());
		}
		
		if (!Common.isNullOrSpace(infoTcontent.getIsPicVideo())) {
			hql.append(" and t.isPicVideo = ? ");
			paramList.add(infoTcontent.getIsPicVideo());
		}
		
		hql.append(" order by t.isTop desc, t.createDate desc");
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}

	@Override
	public ResultPage getInfoTcontentListForMap(String columnId,String areaId,int page,int pageRows) throws Exception {
		String hql = "from pt.wzgl.nrgl.pojo.InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and t.columnId = ? and t.areaId = ? and t.isMapSearch = ? order by t.isTop desc, t.createDate desc ";
		return super.getResultPage(hql, new Object[] { BaseParameter.NO, columnId, areaId,BaseParameter.YES }, page, pageRows);
	}

	@Override
	public ResultPage getMapLocalSearch(String themeId, String areaId,
			int page, int pageRows, String name) throws Exception {
		StringBuffer sb = new StringBuffer(" from InfoTcontent a where 1=1 ");
		ArrayList<Object> params = new ArrayList<Object>();
		sb.append(" and a.usingFlag = ?");
		sb.append(" and (a.delFlag is null or a.delFlag = ?)");
		sb.append(" and (a.needCheck = ? or a.checkFlag = ? )");
		params.add(BaseParameter.YES);
		params.add(BaseParameter.NO);
		params.add(BaseParameter.NO);
		params.add(BaseParameter.YES);
		
		if( themeId != null && !themeId.trim().equals("") ){
			sb.append(" and a.themeId = ? ");
			params.add( themeId.trim() );
		}
		if( areaId != null && areaId.trim().length() > 0 ){
			sb.append(" and a.areaId = ? ");
			params.add( areaId.trim() );
		}
		if( name != null && name.trim().length() > 0 ){
			sb.append(" and a.departmenName like ? ");
			params.add("%" + name.trim() + "%");
		}
		sb.append(" order by a.departmenName asc ");
		return getResultPage(sb.toString(), params, page, pageRows);
	}

	@Override
	public List<InfoTcontent> getMapLocalSearch(String themeId, String areaId,
			String name) throws Exception {
		StringBuffer sb = new StringBuffer(" from pt.wzgl.nrgl.pojo.InfoTcontent a where 1=1 ");
		ArrayList<Object> params = new ArrayList<Object>();
		sb.append(" and a.usingFlag = ?");
		sb.append(" and (a.delFlag is null or a.delFlag = ?)");
		sb.append(" and (a.needCheck = ? or a.checkFlag = ? )");
		params.add(BaseParameter.YES);
		params.add(BaseParameter.NO);
		params.add(BaseParameter.NO);
		params.add(BaseParameter.YES);
		
		if( themeId != null && !themeId.trim().equals("") ){
			sb.append(" and a.themeId = ? ");
			params.add( themeId.trim() );
		}
		if( areaId != null && areaId.trim().length() > 0 ){
			sb.append(" and a.areaId = ? ");
			params.add( areaId.trim() );
		}
		if( name != null && name.trim().length() > 0 ){
			sb.append(" and a.departmenName like ? ");
			params.add("%" + name.trim() + "%");
		}
		sb.append(" order by a.isTop desc, a.createDate desc");
		return super.queryByHql(sb.toString(), params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoTcontent> getInfoTcontentPage(List<String> tcs,String areaId) throws Exception {
		String hql = "from pt.wzgl.nrgl.pojo.InfoTcontent t where (t.delFlag is null or t.delFlag = 0) " +
				"and (t.needCheck = 0 or (t.needCheck = 1 and t.checkFlag = 1)) " +
				"and t.usingFlag = 1 and t.columnId in (:columnIds) ";
		if(!Common.isNullOrSpace(areaId)) {
			hql += " and t.areaId = '" + areaId + "'";
		}
		hql += " order by isTop desc, createDate desc";
		Query query = getSession().createQuery(hql); 
		query.setParameterList("columnIds", tcs);//所有通知公告的ID
		query.setMaxResults(6);//最大显示值
		return query.list();
	}

	@Override
	public ResultPage getInfoTcontentsBycolumnIdAndDictId(InfoTcontent infoTcontent,String columnId,
			String areaId, int page, int pageRows) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select new InfoTcontent(t.contentId,t.columnId,t.contentMainTitle,t.issurDate) from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and t.columnId = ? and t.areaId = ? and t.isMapSearch = ?");
		sb.append(" and t.checkFlag = ? ");
		param.add(BaseParameter.NO);
		param.add(columnId);
		param.add(areaId);
		param.add(BaseParameter.AUDIT_PASS);
		param.add(BaseParameter.AUDIT_SUBMIT);
		if(infoTcontent != null){
			if(!Common.isNullOrSpace(infoTcontent.getContentMainTitle())){
				sb.append(" and t.contentMainTitle like ?");
				param.add("%"+infoTcontent.getContentMainTitle().trim()+"%");
			}
		}
		sb.append(" order by t.isTop desc, t.createDate desc ");
		return super.getResultPage(sb.toString(),param, page, pageRows);
	}

	@Override
	public List<InfoTcontent> getExposureTableToWebIndex(String districtId)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select new InfoTcontent(t.contentId,t.columnId,t.contentMainTitle,t.issurDate) from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and t.isMapSearch = ? and  t.columnId = ?");
		sb.append(" and t.checkFlag = ? ");
		param.add(BaseParameter.NO);
		param.add(BaseParameter.AUDIT_PASS);
		param.add(BaseParameter.EXPOSURETABLE);
		param.add(BaseParameter.AUDIT_SUBMIT);
		if(!Common.isNullOrSpace(districtId)){
			sb.append(" and t.areaId = ?");
			param.add(districtId);
		}
		sb.append(" order by t.isTop desc, t.createDate desc ");
		return queryListByHqlTopNum(sb.toString(), param, BaseParameter.WEB_EXPOSURETABLECOUNT);
	}

	@Override
	public List<InfoTcontent> getNewsDynamicToWebIndex(InfoTcontent infoTcontent,String districtId,Integer topNumber,String TcontentType)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("from pt.wzgl.nrgl.pojo.InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and  t.columnId = ?");
		sb.append(" and t.checkFlag = ? ");
		param.add(BaseParameter.NO);
		param.add(BaseParameter.NEWSDYNAMIC);
		param.add(BaseParameter.AUDIT_SUBMIT);
		if(!Common.isNullOrSpace(TcontentType)){
			sb.append(" and t.isPicVideo = ? ");
			param.add(TcontentType);
		}
		if(!Common.isNullOrSpace(districtId)){
			sb.append(" and t.areaId = ?");
			param.add(districtId);
		}
		if(infoTcontent != null && !Common.isNullOrSpace(infoTcontent.getContentMainTitle())){
			sb.append(" and t.contentMainTitle like ?");
			param.add("%"+infoTcontent.getContentMainTitle().trim()+"%");
		}
		sb.append(" order by t.isTop desc, t.createDate desc ");
		return queryListByHqlTopNum(sb.toString(), param, topNumber);
	}

	@Override
	public List<InfoTcontent> getInfoTcontentToWebListByPage(int topNumber,
			String infoTcontentParentId,String searchKeyWord) throws Exception {
		if(Common.isNullOrSpace(infoTcontentParentId) || Common.isNullOrSpace(searchKeyWord)) return null;
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select new InfoTcontent(t.contentId,t.columnId,t.contentMainTitle,t.issurDate) from InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and t.isMapSearch = ? and  t.columnId = ?");
		sb.append(" and t.checkFlag = ? ");
		param.add(BaseParameter.NO);
		param.add(BaseParameter.AUDIT_PASS);
		param.add(infoTcontentParentId);
		param.add(BaseParameter.AUDIT_SUBMIT);
		sb.append(" and t.contentMainTitle like ?");
		param.add("%" + searchKeyWord.trim() + "%");
		sb.append(" order by t.isTop desc, t.createDate desc ");
		return queryListByHqlTopNum(sb.toString(), param, topNumber);
	}
	
	@Override
	public ResultPage getInfoTcontentBySearch(InfoTcontent infoTcontent,int page,int pageRows) throws Exception{
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("from pt.wzgl.nrgl.pojo.InfoTcontent t where (t.delFlag is null or t.delFlag = ?) and  t.columnId = ?");
		sb.append(" and t.checkFlag = ? ");
		param.add(BaseParameter.NO);
		param.add(BaseParameter.NEWSDYNAMIC);
		param.add(BaseParameter.AUDIT_SUBMIT);
		if(infoTcontent != null && !Common.isNullOrSpace(infoTcontent.getContentMainTitle())){
			sb.append(" and t.contentMainTitle like ?");
			param.add("%"+infoTcontent.getContentMainTitle().trim()+"%");
		}
		sb.append(" order by t.isTop desc, t.createDate desc ");
		return super.getResultPage(sb.toString(), param, page, pageRows);
	}
}
