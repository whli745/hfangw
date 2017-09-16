package pt.wzgl.pzgl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pt.wzgl.pzgl.dao.INetCertificationDao;
import pt.wzgl.pzgl.dao.INetUserCertificateDao;
import pt.wzgl.pzgl.pojo.NetCertification;
import pt.wzgl.pzgl.pojo.NetUserCertificate;
import pt.wzgl.pzgl.pojo.vo.VNetUserCertificate;
import pt.wzgl.pzgl.service.INetUserCertificateService;
import util.BaseParameter;
import util.Common;
import util.SysAppParamUtil;

public class NetUserCertificateServiceImpl implements INetUserCertificateService {
	
	/**
	 * 持久层对象
	 */
	private INetUserCertificateDao netUserCertificateDao;
	/**
	 * 证件持久层对象
	 */
	private INetCertificationDao netCertificationDao;
	
	public INetUserCertificateDao getNetUserCertificateDao() {
		return netUserCertificateDao;
	}

	public void setNetUserCertificateDao(
			INetUserCertificateDao netUserCertificateDao) {
		this.netUserCertificateDao = netUserCertificateDao;
	}

	public INetCertificationDao getNetCertificationDao() {
		return netCertificationDao;
	}

	public void setNetCertificationDao(INetCertificationDao netCertificationDao) {
		this.netCertificationDao = netCertificationDao;
	}

	@Override
	public void saveOrUpdate(NetUserCertificate certificate) throws Exception {
		netUserCertificateDao.saveOrUpdate(certificate);
	}

	@Override
	public List<NetUserCertificate> getCertificationByType(String typeId)
			throws Exception {
		return netUserCertificateDao.getCertificationByType(typeId);
	}

	@Override
	public List<VNetUserCertificate> getCertificationByUser(String netUserId)
			throws Exception {
		List list = netUserCertificateDao.getCertificationByUser(netUserId);
		List<VNetUserCertificate> vNetUserCertificates = null;
		if( list != null && list.size() > 0 ){
			vNetUserCertificates = new ArrayList<VNetUserCertificate>();
			NetUserCertificate cate = null;
			NetCertification netCate = null;
			VNetUserCertificate vcate = null;
			for (int i = 0; i < list.size(); i++) {
				cate = (NetUserCertificate)((Object[])list.get(i))[0];
				netCate = (NetCertification)((Object[])list.get(i))[1];
				vcate = new VNetUserCertificate();
				vcate.setId( cate.getId() );
				vcate.setCertificateName(netCate.getCertificationName());
				vcate.setCertificateBeginDate(cate.getCertificateBeginDate());
				vcate.setCertificateEndDate(cate.getCertificateEndDate());
				vcate.setCertificateImageAddr(cate.getCertificateImageAddr());
				vcate.setCertificateNo(cate.getCertificateNo());
				vcate.setCertificationStatus(cate.getCertificationStatus());
				vcate.setCertificationType(cate.getCertificationType());
				vcate.setUserId(cate.getUserId());
				vcate.setCertificateID(cate.getCertificateID());
				vNetUserCertificates.add(vcate);
			}
		}
		return vNetUserCertificates;
	}

	@Override
	public NetUserCertificate getCertificationImage(String certificationId)
			throws Exception {
		return netUserCertificateDao.getCertificationImage(certificationId);
	}

	@Override
	public List<NetCertification> getNetCertificationByType(
			String certificateType) throws Exception {
		return netCertificationDao.getAllNetCertificationByType(certificateType);
	}
	
	@Override
	public String doUploadCertification(HttpServletRequest request,NetUserCertificate cate,
			File file1,String file1ContentType,String file1FileName) throws Exception{
		if( cate != null ){
			if( !Common.isNullOrSpace(file1FileName) ){
				String zcyhglMaxsize = SysAppParamUtil.getParamValByParamId(BaseParameter.ZCYHGL_MAXSIZE);
				if( file1.length() > (Integer.parseInt(zcyhglMaxsize) * 1024 * 1024 ) ){
					return "lengthError";
				}
				String fileType = file1FileName.substring(file1FileName.lastIndexOf(".")+1).toLowerCase();
				String zcyhglFiletype = SysAppParamUtil.getParamValByParamId(BaseParameter.ZCYHGL_FILETYPE);
				if( zcyhglFiletype.indexOf(fileType) >= 0 ){
					InputStream in = new FileInputStream( file1 );
			        //String dir = request.getRealPath(BaseParameter.UPLOADDIR );
					String zcyhglFiledir = SysAppParamUtil.getParamValByParamId(BaseParameter.ZCYHGL_FILEDIR);
					String dir = Common.getAttachmentRealPath(zcyhglFiledir);
			        File uploadFile = new File(dir, file1FileName);
			        if(!uploadFile.exists()){
			        	uploadFile.getParentFile().mkdirs();
			        }
			        OutputStream out = new FileOutputStream(uploadFile);
			        
			        byte[] buffer = new byte[1024 * 1024];  
		            int length;  
		            while ((length = in.read(buffer)) > 0) {  
		                out.write(buffer, 0, length);  
		            }
		            String attaPath = SysAppParamUtil.getParamValByParamId(BaseParameter.ATTAPATH);
		            cate.setCertificateImageAddr(attaPath + zcyhglFiledir + file1FileName);
		            return "success";
				}else{
					return "typeError";
				}
			}
		}
		return "error";
	}

	@Override
	public List<NetUserCertificate> getUserCertificationByUser(String userId)
			throws Exception {
		return netUserCertificateDao.getUserCertificationByUser(userId);
	}

	@Override
	public void doAuditCertification(String ucId, String status, String content)
			throws Exception {
		netUserCertificateDao.doAuditCertification(ucId, status, content);
	}

	@Override
	public NetUserCertificate getNetUserCertificationIsExsit(
			String certificationId, String userId) throws Exception {
		return netUserCertificateDao.getNetUserCertificationIsExsit(certificationId, userId);
	}

	@Override
	public NetCertification getCertification(String certificationId)
			throws Exception {
		return netCertificationDao.getCertification(certificationId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String delete(NetUserCertificate cate,String path,HttpServletRequest request) throws Exception {
		String fileDir = request.getRealPath( path );
		File file = new File(fileDir,cate.getCertificateImageAddr());
		if( file.exists() ){
			boolean isDelete = file.delete();
			if( isDelete ){
				netUserCertificateDao.delete(cate);
				return "success";
			}else {
				return "delError";
			}
		}else{
			return "noFile";
		}
	}
}
