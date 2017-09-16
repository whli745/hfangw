package pt.wzgl.pzgl.pojo.vo;

/**
 * @Description ����û�֤��������
 *
 * @author Liangmh
 * @createDate 2014-11-21
 */
public class VCertificationCheckStauts {
	
	private String userId;//�û�ID
	private String isHaveCheck;//�Ƿ�����Ҫ��˵�֤��
	private String certificationNo;//֤�����
	
	public VCertificationCheckStauts(){}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsHaveCheck() {
		return isHaveCheck;
	}

	public void setIsHaveCheck(String isHaveCheck) {
		this.isHaveCheck = isHaveCheck;
	}

	public String getCertificationNo() {
		return certificationNo;
	}

	public void setCertificationNo(String certificationNo) {
		this.certificationNo = certificationNo;
	}
}
