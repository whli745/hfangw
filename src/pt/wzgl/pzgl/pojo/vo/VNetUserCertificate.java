package pt.wzgl.pzgl.pojo.vo;

/**
 * @Description �û�֤���м����
 *
 * @author Liangmh
 * @createDate 2014-11-20
 */
public class VNetUserCertificate {
	
	private String id;	//���
	private String certificateName;//֤�����
	private String certificateNo;//֤������
	private String certificateBeginDate;//֤����Ч��ʼ����
	private String certificateEndDate;//֤����Ч��������
	private String certificateImageAddr;//֤��ͼƬ��ַ
	private String certificationStatus;//֤����֤״̬
	private String userId;//�û�ID
	private String certificationType;//֤������
	private String certificateID;//֤��ID
	
	/**
	 * �޲ι��캯��
	 */
	public VNetUserCertificate(){}
	
	/**
	 * @return	���
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id ���
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return ֤�����
	 */
	public String getCertificateName() {
		return certificateName;
	}
	/**
	 * @param ֤�����
	 */
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	/**
	 * @return	֤������
	 */
	public String getCertificateNo() {
		return certificateNo;
	}
	/**
	 * @param ֤������
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * @return	֤����Ч��ʼ����
	 */
	public String getCertificateBeginDate() {
		return certificateBeginDate;
	}
	/**
	 * @param	֤����Ч��ʼ����
	 */
	public void setCertificateBeginDate(String certificateBeginDate) {
		this.certificateBeginDate = certificateBeginDate;
	}
	/**
	 * @return	֤����Ч��������
	 */
	public String getCertificateEndDate() {
		return certificateEndDate;
	}
	/**
	 * @param ֤����Ч��������
	 */
	public void setCertificateEndDate(String certificateEndDate) {
		this.certificateEndDate = certificateEndDate;
	}
	/**
	 * @return	֤��ͼƬ��ַ
	 */
	public String getCertificateImageAddr() {
		return certificateImageAddr;
	}
	/**
	 * @param ֤��ͼƬ��ַ
	 */
	public void setCertificateImageAddr(String certificateImageAddr) {
		this.certificateImageAddr = certificateImageAddr;
	}
	/**
	 * @return	֤����֤״̬
	 */
	public String getCertificationStatus() {
		return certificationStatus;
	}
	/**
	 * @param ֤����֤״̬
	 */
	public void setCertificationStatus(String certificationStatus) {
		this.certificationStatus = certificationStatus;
	}
	/**
	 * @return	�û�����
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param	�û�����
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return	֤������
	 */
	public String getCertificationType() {
		return certificationType;
	}
	/**
	 * @param ֤������
	 */
	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getCertificateID() {
		return certificateID;
	}

	public void setCertificateID(String certificateID) {
		this.certificateID = certificateID;
	}
}
