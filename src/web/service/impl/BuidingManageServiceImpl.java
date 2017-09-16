package web.service.impl;

import java.util.Date;

import util.BaseParameter;
import util.Common;
import util.ResultPage;
import web.dao.*;
import web.dao.impl.RedPacketDaoImpl;
import web.pojo.*;
import web.service.IBuidingManageService;

public class BuidingManageServiceImpl implements IBuidingManageService {
	private IHousingProjectDao housingProjectDao;
	private IHfwKftYyDao hfwKftYyDao;
	private IHfwKftDao hfwKftDao;
	private IHfwRealtyConsultantDao hfwRealtyConsultantDao;
	private IHousingProjectPriceDao housingProjectPriceDao;
	private IRedPacketDao redPacketDao;
	
	@Override
	public ResultPage queryHousingProjectList(HousingProject q, int page,
			int pageRows) throws Exception {
		return housingProjectDao.queryHousingProjectList(q, page, pageRows);
	}
	
	@Override
	public void saveOrUpdateHousingProject(HousingProject hp) throws Exception {
		if(hp != null && Common.isNullOrSpace(hp.getOid())){
			hp.setOid(null);
		}
		hp.setLrDate(new Date());
		housingProjectDao.saveOrUpdateHousingProject(hp);
	}
	
	@Override
	public HousingProject getHousingProjectByOid(String oid) throws Exception {
		return housingProjectDao.getHousingProjectByOid(oid);
	}
	
	@Override
	public void saveOrUpdateHfwKft(HfwKft hfwKft) throws Exception {
		if(hfwKft != null && Common.isNullOrSpace(hfwKft.getOid())){
			hfwKft.setOid(null);
		}
		hfwKftDao.saveOrUpdateHfwKft(hfwKft);
	}
	
	@Override
	public ResultPage queryHfwKftYyList(HfwKftYy q,int type,int page,int pageRows) throws Exception {
		return hfwKftYyDao.queryHfwKftYyList(q, type, page, pageRows);
	}
	
	@Override
	public void saveOrUpdateHfwKftYy(HfwKftYy o) throws Exception {
		hfwKftYyDao.saveOrUpdateHfwKftYy(o);
	}

	@Override
	public ResultPage queryHfwRealtyConsultantList(HfwRealtyConsultant q,
			int page, int pageRows) throws Exception {
		return hfwRealtyConsultantDao.queryHfwRealtyConsultantList(q, page, pageRows);
	}
	
	@Override
	public void delHfwRealtyConsultant(String oid) throws Exception {
		hfwRealtyConsultantDao.delHfwRealtyConsultant(oid);
	}
	
	@Override
	public HfwRealtyConsultant gethfwRealtyConsultantByOid(String oid)
			throws Exception {
		return hfwRealtyConsultantDao.gethfwRealtyConsultantByOid(oid);
	}
	
	@Override
	public void saveOrUpdateHfwRealtyConsultant(
			HfwRealtyConsultant hfwRealtyConsultant) throws Exception {
		if(Common.isNullOrSpace(hfwRealtyConsultant.getOid())){
			hfwRealtyConsultant.setOid(null);
		}
		hfwRealtyConsultantDao.saveOrUpdateHfwRealtyConsultant(hfwRealtyConsultant);
	}
	
	@Override
	public void saveOrUpdateHousingProject(HousingProject hp, HfwKft hfwKft) throws Exception {
		hp.setDel_flag(BaseParameter.NO);
		saveOrUpdateHousingProject(hp);
		hfwKft.setHousing_proj_id(hp.getOid());
		saveOrUpdateHfwKft(hfwKft);
	}
	
	@Override
	public void saveOrUpdateHousingProjectPrice(HousingProjectPrice price)
			throws Exception {
		housingProjectPriceDao.saveOrUpdateHousingProjectPrice(price);		
	}

	@Override
	public void saveOrUpdateRedPacket(RedPacket packet) throws Exception {
		redPacketDao.saveOrUpdateRedPacket(packet);
	}

	@Override
	public void delAllRedPacket(String houseId) throws Exception {
        String hql = "update RedPacket set isDel = 1 where houseId = ?";
		redPacketDao.delAllRedPacket(hql,houseId);
	}

	@Override
	public void delAllHousingProjectPrice(String housing_proj_id) throws Exception {
		housingProjectPriceDao.delAllHousingProjectPrice(housing_proj_id);
	}
	
	@Override
	public HfwKftYy getHfwKftYyByOid(String oid) throws Exception {
		return hfwKftYyDao.getHfwKftYyByOid(oid);
	}

	public IHousingProjectDao getHousingProjectDao() {
		return housingProjectDao;
	}
	public void setHousingProjectDao(IHousingProjectDao housingProjectDao) {
		this.housingProjectDao = housingProjectDao;
	}
	public IHfwKftYyDao getHfwKftYyDao() {
		return hfwKftYyDao;
	}
	public void setHfwKftYyDao(IHfwKftYyDao hfwKftYyDao) {
		this.hfwKftYyDao = hfwKftYyDao;
	}
	public IHfwKftDao getHfwKftDao() {
		return hfwKftDao;
	}
	public void setHfwKftDao(IHfwKftDao hfwKftDao) {
		this.hfwKftDao = hfwKftDao;
	}

	public IHfwRealtyConsultantDao getHfwRealtyConsultantDao() {
		return hfwRealtyConsultantDao;
	}

	public void setHfwRealtyConsultantDao(
			IHfwRealtyConsultantDao hfwRealtyConsultantDao) {
		this.hfwRealtyConsultantDao = hfwRealtyConsultantDao;
	}

	public IHousingProjectPriceDao getHousingProjectPriceDao() {
		return housingProjectPriceDao;
	}

	public void setHousingProjectPriceDao(
			IHousingProjectPriceDao housingProjectPriceDao) {
		this.housingProjectPriceDao = housingProjectPriceDao;
	}

	public void setRedPacketDao(IRedPacketDao redPacketDao) {
		this.redPacketDao = redPacketDao;
	}

	public IRedPacketDao getRedPacketDao() {
		return redPacketDao;
	}
}
