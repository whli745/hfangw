package pt.wzgl.pzgl.pojo;

/**
 * @Description 证件实体
 *
 * @author Liangmh
 * @createDate 2014-11-20
 */
public class NetCertification {
	
	private String id;//主键
	private String certificationName;//证件名称
	private String certificationType;//证件类型
	private String certificationImg;//证件默认图片
	
	/**
	 * 构造函数
	 */
	public NetCertification(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getCertificationType() {
		return certificationType;
	}

	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getCertificationImg() {
		return certificationImg;
	}

	public void setCertificationImg(String certificationImg) {
		this.certificationImg = certificationImg;
	}
}
