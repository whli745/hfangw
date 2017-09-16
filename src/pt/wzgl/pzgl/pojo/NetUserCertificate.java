package pt.wzgl.pzgl.pojo;

/**
 * @Description 用户证件实体BEAN
 *
 * @author Liangmh
 * @createDate 2014-11-13
 */
public class NetUserCertificate {
	
	private String id;	//主键
	private String certificateID;//证件名称
	private String certificateNo;//证件编码
	private String certificateBeginDate;//证件有效开始日期
	private String certificateEndDate;//证件有效结束日期
	private String certificateImageAddr;//证件图片地址
	private String certificationStatus;//证件认证状态
	private String userId;//用户ID
	private String certificationType;//证件类型
	private String auditContent;//审核意见
	
	/**
	 * 无参构造函数
	 */
	public NetUserCertificate(){}
	
	/**
	 * @return	主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return	证件编码
	 */
	public String getCertificateNo() {
		return certificateNo;
	}
	/**
	 * @param 证件编码
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * @return	证件有效开始日期
	 */
	public String getCertificateBeginDate() {
		return certificateBeginDate;
	}
	/**
	 * @param	证件有效开始日期
	 */
	public void setCertificateBeginDate(String certificateBeginDate) {
		this.certificateBeginDate = certificateBeginDate;
	}
	/**
	 * @return	证件有效结束日期
	 */
	public String getCertificateEndDate() {
		return certificateEndDate;
	}
	/**
	 * @param 证件有效结束日期
	 */
	public void setCertificateEndDate(String certificateEndDate) {
		this.certificateEndDate = certificateEndDate;
	}
	/**
	 * @return	证件图片地址
	 */
	public String getCertificateImageAddr() {
		return certificateImageAddr;
	}
	/**
	 * @param 证件图片地址
	 */
	public void setCertificateImageAddr(String certificateImageAddr) {
		this.certificateImageAddr = certificateImageAddr;
	}
	/**
	 * @return	证件认证状态
	 */
	public String getCertificationStatus() {
		return certificationStatus;
	}
	/**
	 * @param 证件认证状态
	 */
	public void setCertificationStatus(String certificationStatus) {
		this.certificationStatus = certificationStatus;
	}
	/**
	 * @return	用户编码
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param	用户编码
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return	证件类型
	 */
	public String getCertificationType() {
		return certificationType;
	}
	/**
	 * @param 证件类型
	 */
	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}
	/**
	 * 
	 * @return	证件名称
	 */
	public String getCertificateID() {
		return certificateID;
	}
	/**
	 * 
	 * @param 证件名称
	 */
	public void setCertificateID(String certificateID) {
		this.certificateID = certificateID;
	}

	public String getAuditContent() {
		return auditContent;
	}

	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}
}
