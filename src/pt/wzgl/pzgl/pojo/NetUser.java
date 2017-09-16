package pt.wzgl.pzgl.pojo;

import java.util.Date;

import util.base.BaseObject;

/**
 * 网上申报用户
 * TABLE_NAME ：NET_USER
 * 
 * @author : yaosw
 * @date : 2014-09-24
 */
public class NetUser extends BaseObject {
	private static final long serialVersionUID = 1L;
	
	private java.lang.String id;	//主键
	private java.lang.String userLogin;//用户登录名
	private java.lang.String userPass;//用户登录密码
	private java.lang.String userName;//用户真实姓名
	private java.lang.String userType;//用户类型，【个人、企业、社会团体】
	private java.lang.String userCardno;//证件号码：其值可能是身份证号码，也可能是护照，也可能是军官证等
	private java.lang.String userCurrentArea;//目前所在区域
	private java.lang.String userCensus;//是否本地户
	private java.lang.String userAddress;//联系地址/邮寄地址
	private java.lang.String userTel;//联系电话
	private java.lang.String userPhone;//移动电话
	private java.lang.String userEmail;//电子邮箱
	private java.lang.String companyOrganCode;//企业：组织机构代码证
	private java.lang.String companyBusinessLicense;//企业：营业执照号码（企业工商注册号）
	private java.lang.String companyType;//企业：企业类型，（企业法人/非企业法人）
	private java.lang.String companyRegCapital;//企业：注册资金
	private java.lang.String companyLegal;//企业：企业法人 （企业负责人姓名）
	private java.lang.String societyName;//社会团体：社团名称
	private java.lang.String societyChargeDept;//社会团体：业务主管单位
	private java.lang.String societyRegisterNumber;//社会团体：登记证号
	private java.lang.String societyNature;//社会团体：性质，【行业、专业、学术、联合】
	private java.lang.String societyLawPerson;//社会团体：法人代表
	private java.lang.String societyMsz;//社会团体：秘书长
	private java.lang.String societyContactPerson;//社会团体：联系人
	private java.lang.String societyAddress;//社会团体：住所
	private java.util.Date userCreatedate;//用户注册日期
	private java.lang.String userImageUrl;//用户头像地址
	private java.lang.String userActivating;//当前用户账户是否已经激活，0-未激活，1-已激活
	private java.lang.String userAttest;//当前用户是否已经实名认证，0-未认证，1-已认证
	private java.lang.String userDelStatus;//用户删除状态，0-未删除，1-已删除
	private java.lang.String userSecrQuesIdA;//密保问题1（字典表ID）
	private java.lang.String userSecrQuesIdB;//密保问题2（字典表ID）
	private java.lang.String userSecrQuesAnswA;//答案1
	private java.lang.String userSecrQuesAnswB;//答案2
	private java.util.Date userSecrModiDate;//最近一次密保修改时间 (最后登陆时间,暂用）
	private java.util.Date userPassModiDate;//最近一次密码修改时间
	private String verifyEmailCode;//邮箱验证码 停用 zhujj 20141119
	private String emailUnVerified;//等待被验证的新邮箱地址
	private String userHeadImage;//用户头像
	private String sex;//性别
	private Date birthday;//出生日期
	private String mz;// 民族
	private String certType;//证件类型，证件值存放在userCardNo 属性中
	private String zipCode;//邮编
	private String qq;//QQ 号码
//	private java.lang.String safeEmail;//用于找回密码安全邮箱   已于注册邮箱合并
	private java.lang.String vaidCode;//激活安全邮箱的验证码
	private String companyName;//企业名称
	private String webSite;//单位网址
	private String weiBoSite;//单位微博地址
	private String companyInfoSite;//单位信息网址
	private String companyIntroduction;//单位介绍
	private String taxNo;//税务登记号
	private String depositBank;//开户银行
	private String bankAccount;//银行账号
	private String contacterName;//联系人姓名
	private String contacterTel;//联系人电话
	private String contacterPhone;//联系人手机号
	private String contacterEmail;//联系人邮箱
	
	private String faxNum;//传真
	private String setUpDate;//企业成立时间
	private String companyRegisteredAddR;//企业注册地址
	private String bussinessScope;//经营范围
	private String registeredCapital;//注册资本
	private String proAndBussAddr;//生产经营地址
//	private String userCheckStatus;//用户验证状态 0-全部验证 1-手机验证 2-无验证   停用 zhujj 20141119
	
	private String bangdingPhoneStatus;//用户中心手机绑定状态 1-已绑定   zhangh 20141209 添加
	
	private String jpushRegId;	// 该值为手机app在jpush服务器端注册的id
	
	/**
	 * @param id the id to set<br/>
	 */
	public void setId(java.lang.String id) {
		if(util.Common.isNullOrSpace(id)) {
			this.id = null;
		} else {
			this.id = id;
		}
	}
	/**
	 * @return the id<br/>
	 */
	public java.lang.String getId() {
		return id;
	}
	/**
	 * @param userLogin the userLogin to set<br/>
	 */
	public void setUserLogin(java.lang.String userLogin) {
		this.userLogin = userLogin;
	}
	/**
	 * @return the userLogin<br/>
	 */
	public java.lang.String getUserLogin() {
		return userLogin;
	}
	/**
	 * @param userPass the userPass to set<br/>
	 */
	public void setUserPass(java.lang.String userPass) {
		this.userPass = userPass;
	}
	/**
	 * @return the userPass<br/>
	 */
	public java.lang.String getUserPass() {
		return userPass;
	}
	/**
	 * @param userName the userName to set<br/>
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userName<br/>
	 */
	public java.lang.String getUserName() {
		return userName;
	}
	/**
	 * @param userType the userType to set<br/>
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}
	/**
	 * @return the userType<br/>
	 */
	public java.lang.String getUserType() {
		return userType;
	}
	/**
	 * @param userCardno the userCardno to set<br/>
	 */
	public void setUserCardno(java.lang.String userCardno) {
		this.userCardno = userCardno;
	}
	/**
	 * @return the userCardno<br/>
	 */
	public java.lang.String getUserCardno() {
		return userCardno;
	}
	/**
	 * @param userCurrentArea the userCurrentArea to set<br/>
	 */
	public void setUserCurrentArea(java.lang.String userCurrentArea) {
		this.userCurrentArea = userCurrentArea;
	}
	/**
	 * @return the userCurrentArea<br/>
	 */
	public java.lang.String getUserCurrentArea() {
		return userCurrentArea;
	}
	/**
	 * @param userCensus the userCensus to set<br/>
	 */
	public void setUserCensus(java.lang.String userCensus) {
		this.userCensus = userCensus;
	}
	/**
	 * @return the userCensus<br/>
	 */
	public java.lang.String getUserCensus() {
		return userCensus;
	}
	/**
	 * @param userAddress the userAddress to set<br/>
	 */
	public void setUserAddress(java.lang.String userAddress) {
		this.userAddress = userAddress;
	}
	/**
	 * @return the userAddress<br/>
	 */
	public java.lang.String getUserAddress() {
		return userAddress;
	}
	/**
	 * @param userTel the userTel to set<br/>
	 */
	public void setUserTel(java.lang.String userTel) {
		this.userTel = userTel;
	}
	/**
	 * @return the userTel<br/>
	 */
	public java.lang.String getUserTel() {
		return userTel;
	}
	/**
	 * @param userPhone the userPhone to set<br/>
	 */
	public void setUserPhone(java.lang.String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * @return the userPhone<br/>
	 */
	public java.lang.String getUserPhone() {
		return userPhone;
	}
	/**
	 * @param userEmail the userEmail to set<br/>
	 */
	public void setUserEmail(java.lang.String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userEmail<br/>
	 */
	public java.lang.String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param companyOrganCode the companyOrganCode to set<br/>
	 */
	public void setCompanyOrganCode(java.lang.String companyOrganCode) {
		this.companyOrganCode = companyOrganCode;
	}
	/**
	 * @return the companyOrganCode<br/>
	 */
	public java.lang.String getCompanyOrganCode() {
		return companyOrganCode;
	}
	/**
	 * @param companyBusinessLicense the companyBusinessLicense to set<br/>
	 */
	public void setCompanyBusinessLicense(java.lang.String companyBusinessLicense) {
		this.companyBusinessLicense = companyBusinessLicense;
	}
	/**
	 * @return the companyBusinessLicense<br/>
	 */
	public java.lang.String getCompanyBusinessLicense() {
		return companyBusinessLicense;
	}
	/**
	 * @param companyType the companyType to set<br/>
	 */
	public void setCompanyType(java.lang.String companyType) {
		this.companyType = companyType;
	}
	/**
	 * @return the companyType<br/>
	 */
	public java.lang.String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyRegCapital the companyRegCapital to set<br/>
	 */
	public void setCompanyRegCapital(java.lang.String companyRegCapital) {
		this.companyRegCapital = companyRegCapital;
	}
	/**
	 * @return the companyRegCapital<br/>
	 */
	public java.lang.String getCompanyRegCapital() {
		return companyRegCapital;
	}
	/**
	 * @param companyLegal the companyLegal to set<br/>
	 */
	public void setCompanyLegal(java.lang.String companyLegal) {
		this.companyLegal = companyLegal;
	}
	/**
	 * @return the companyLegal<br/>
	 */
	public java.lang.String getCompanyLegal() {
		return companyLegal;
	}
	/**
	 * @param societyName the societyName to set<br/>
	 */
	public void setSocietyName(java.lang.String societyName) {
		this.societyName = societyName;
	}
	/**
	 * @return the societyName<br/>
	 */
	public java.lang.String getSocietyName() {
		return societyName;
	}
	/**
	 * @param societyChargeDept the societyChargeDept to set<br/>
	 */
	public void setSocietyChargeDept(java.lang.String societyChargeDept) {
		this.societyChargeDept = societyChargeDept;
	}
	/**
	 * @return the societyChargeDept<br/>
	 */
	public java.lang.String getSocietyChargeDept() {
		return societyChargeDept;
	}
	/**
	 * @param societyRegisterNumber the societyRegisterNumber to set<br/>
	 */
	public void setSocietyRegisterNumber(java.lang.String societyRegisterNumber) {
		this.societyRegisterNumber = societyRegisterNumber;
	}
	/**
	 * @return the societyRegisterNumber<br/>
	 */
	public java.lang.String getSocietyRegisterNumber() {
		return societyRegisterNumber;
	}
	/**
	 * @param societyNature the societyNature to set<br/>
	 */
	public void setSocietyNature(java.lang.String societyNature) {
		this.societyNature = societyNature;
	}
	/**
	 * @return the societyNature<br/>
	 */
	public java.lang.String getSocietyNature() {
		return societyNature;
	}
	/**
	 * @param societyLawPerson the societyLawPerson to set<br/>
	 */
	public void setSocietyLawPerson(java.lang.String societyLawPerson) {
		this.societyLawPerson = societyLawPerson;
	}
	/**
	 * @return the societyLawPerson<br/>
	 */
	public java.lang.String getSocietyLawPerson() {
		return societyLawPerson;
	}
	/**
	 * @param societyMsz the societyMsz to set<br/>
	 */
	public void setSocietyMsz(java.lang.String societyMsz) {
		this.societyMsz = societyMsz;
	}
	/**
	 * @return the societyMsz<br/>
	 */
	public java.lang.String getSocietyMsz() {
		return societyMsz;
	}
	/**
	 * @param societyContactPerson the societyContactPerson to set<br/>
	 */
	public void setSocietyContactPerson(java.lang.String societyContactPerson) {
		this.societyContactPerson = societyContactPerson;
	}
	/**
	 * @return the societyContactPerson<br/>
	 */
	public java.lang.String getSocietyContactPerson() {
		return societyContactPerson;
	}
	/**
	 * @param societyAddress the societyAddress to set<br/>
	 */
	public void setSocietyAddress(java.lang.String societyAddress) {
		this.societyAddress = societyAddress;
	}
	/**
	 * @return the societyAddress<br/>
	 */
	public java.lang.String getSocietyAddress() {
		return societyAddress;
	}
	/**
	 * @param userCreatedate the userCreatedate to set<br/>
	 */
	public void setUserCreatedate(java.util.Date userCreatedate) {
		this.userCreatedate = userCreatedate;
	}
	/**
	 * @return the userCreatedate<br/>
	 */
	public java.util.Date getUserCreatedate() {
		return userCreatedate;
	}
	/**
	 * @param userImageUrl the userImageUrl to set<br/>
	 */
	public void setUserImageUrl(java.lang.String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	/**
	 * @return the userImageUrl<br/>
	 */
	public java.lang.String getUserImageUrl() {
		return userImageUrl;
	}
	/**
	 * @param userActivating the userActivating to set<br/>
	 */
	public void setUserActivating(java.lang.String userActivating) {
		this.userActivating = userActivating;
	}
	/**
	 * @return the userActivating<br/>
	 */
	public java.lang.String getUserActivating() {
		return userActivating;
	}
	/**
	 * @param userAttest the userAttest to set<br/>
	 */
	public void setUserAttest(java.lang.String userAttest) {
		this.userAttest = userAttest;
	}
	/**
	 * @return the userAttest<br/>
	 */
	public java.lang.String getUserAttest() {
		return userAttest;
	}
	/**
	 * @param userDelStatus the userDelStatus to set<br/>
	 */
	public void setUserDelStatus(java.lang.String userDelStatus) {
		this.userDelStatus = userDelStatus;
	}
	/**
	 * @return the userDelStatus<br/>
	 */
	public java.lang.String getUserDelStatus() {
		return userDelStatus;
	}
	
	public java.lang.String getUserSecrQuesIdA() {
		return userSecrQuesIdA;
	}
	public void setUserSecrQuesIdA(java.lang.String userSecrQuesIdA) {
		this.userSecrQuesIdA = userSecrQuesIdA;
	}
	public java.lang.String getUserSecrQuesIdB() {
		return userSecrQuesIdB;
	}
	public void setUserSecrQuesIdB(java.lang.String userSecrQuesIdB) {
		this.userSecrQuesIdB = userSecrQuesIdB;
	}
	public java.lang.String getUserSecrQuesAnswA() {
		return userSecrQuesAnswA;
	}
	public void setUserSecrQuesAnswA(java.lang.String userSecrQuesAnswA) {
		this.userSecrQuesAnswA = userSecrQuesAnswA;
	}
	public java.lang.String getUserSecrQuesAnswB() {
		return userSecrQuesAnswB;
	}
	public void setUserSecrQuesAnswB(java.lang.String userSecrQuesAnswB) {
		this.userSecrQuesAnswB = userSecrQuesAnswB;
	}
	/**
	 * 获取2014-9-26最近一次密保修改时间  
	 * @return userSecrModiDate 2014-9-26最近一次密保修改时间  
	 */
	public java.util.Date getUserSecrModiDate() {
		return userSecrModiDate;
	}
	/**
	 * 设置2014-9-26最近一次密保修改时间
	 * @param userSecrModiDate 2014-9-26最近一次密保修改时间  
	 */
	public void setUserSecrModiDate(java.util.Date userSecrModiDate) {
		this.userSecrModiDate = userSecrModiDate;
	}
	/**
	 * 获取2014-9-26最近一次密码修改时间  
	 * @return userPassModiDate 2014-9-26最近一次密码修改时间  
	 */
	public java.util.Date getUserPassModiDate() {
		return userPassModiDate;
	}
	/**
	 * 设置2014-9-26最近一次密码修改时间
	 * @param userPassModiDate 2014-9-26最近一次密码修改时间  
	 */
	public void setUserPassModiDate(java.util.Date userPassModiDate) {
		this.userPassModiDate = userPassModiDate;
	}
	/**
	 * @return the 邮箱验证码
	 */
	public String getVerifyEmailCode() {
		return verifyEmailCode;
	}
	/**
	 * @param 邮箱验证码 the verifyEmailCode to set
	 */
	public void setVerifyEmailCode(String verifyEmailCode) {
		this.verifyEmailCode = verifyEmailCode;
	}
	/**
	 * @return the 等待被验证的新邮箱地址
	 */
	public String getEmailUnVerified() {
		return emailUnVerified;
	}
	/**
	 * @param 等待被验证的新邮箱地址 the emailUnVerified to set
	 */
	public void setEmailUnVerified(String emailUnVerified) {
		this.emailUnVerified = emailUnVerified;
	}
	
	public String getUserHeadImage() {
		return userHeadImage;
	}
	public void setUserHeadImage(String userHeadImage) {
		this.userHeadImage = userHeadImage;
	}
	/**
	 * @return the 性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param 性别 the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the 出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param 出生日期 the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the 证件类型，证件值存放在userCardNo属性中
	 */
	public String getCertType() {
		return certType;
	}
	/**
	 * @param 证件类型，证件值存放在userCardNo属性中 the certType to set
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}
	/**
	 * @return the 邮编
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param 邮编 the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the QQ号码
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param QQ号码 the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取激活安全邮箱的验证码  
	 * @return vaidCode 激活安全邮箱的验证码  
	 */
	public java.lang.String getVaidCode() {
		return vaidCode;
	}
	/**
	 * 设置激活安全邮箱的验证码
	 * @param vaidCode 激活安全邮箱的验证码  
	 */
	public void setVaidCode(java.lang.String vaidCode) {
		this.vaidCode = vaidCode;
	}
	/**
	 * @return the 企业名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param 企业名称 the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the 单位网址
	 */
	public String getWebSite() {
		return webSite;
	}
	/**
	 * @param 单位网址 the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	/**
	 * @return the 单位微博地址
	 */
	public String getWeiBoSite() {
		return weiBoSite;
	}
	/**
	 * @param 单位微博地址 the weiBoSite to set
	 */
	public void setWeiBoSite(String weiBoSite) {
		this.weiBoSite = weiBoSite;
	}
	/**
	 * @return the 单位介绍
	 */
	public String getCompanyIntroduction() {
		return companyIntroduction;
	}
	/**
	 * @param 单位介绍 the companyIntroduction to set
	 */
	public void setCompanyIntroduction(String companyIntroduction) {
		this.companyIntroduction = companyIntroduction;
	}
	/**
	 * @return the 单位信息网址
	 */
	public String getCompanyInfoSite() {
		return companyInfoSite;
	}
	/**
	 * @param 单位信息网址 the companyInfoSite to set
	 */
	public void setCompanyInfoSite(String companyInfoSite) {
		this.companyInfoSite = companyInfoSite;
	}
	/**
	 * @return the 税务登记号
	 */
	public String getTaxNo() {
		return taxNo;
	}
	/**
	 * @param 税务登记号 the taxNo to set
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	/**
	 * @return the 开户银行
	 */
	public String getDepositBank() {
		return depositBank;
	}
	/**
	 * @param 开户银行 the depositBank to set
	 */
	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	/**
	 * @return the 银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}
	/**
	 * @param 银行账号 the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * @return the 联系人姓名
	 */
	public String getContacterName() {
		return contacterName;
	}
	/**
	 * @param 联系人姓名 the contacterName to set
	 */
	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}
	/**
	 * @return the 联系人电话
	 */
	public String getContacterTel() {
		return contacterTel;
	}
	/**
	 * @param 联系人电话 the contacterTel to set
	 */
	public void setContacterTel(String contacterTel) {
		this.contacterTel = contacterTel;
	}
	/**
	 * @return the 联系人邮箱
	 */
	public String getContacterEmail() {
		return contacterEmail;
	}
	/**
	 * @param 联系人邮箱 the contacterEmail to set
	 */
	public void setContacterEmail(String contacterEmail) {
		this.contacterEmail = contacterEmail;
	}
	/**
	 * @return the 传真
	 */
	public String getFaxNum() {
		return faxNum;
	}
	/**
	 * @param 传真 the contacterEmail to set
	 */
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	/**
	 * @return 企业成立时间
	 */
	public String getSetUpDate() {
		return setUpDate;
	}
	/**
	 * @param 企业成立时间
	 */
	public void setSetUpDate(String setUpDate) {
		this.setUpDate = setUpDate;
	}
	/**
	 * @return 企业注册地址 
	 */
	public String getCompanyRegisteredAddR() {
		return companyRegisteredAddR;
	}
	/**
	 * @param 企业注册地址
	 */
	public void setCompanyRegisteredAddR(String companyRegisteredAddR) {
		this.companyRegisteredAddR = companyRegisteredAddR;
	}
	/**
	 * @return 经营范围
	 */
	public String getBussinessScope() {
		return bussinessScope;
	}
	/**
	 * @param 经营范围
	 */
	public void setBussinessScope(String bussinessScope) {
		this.bussinessScope = bussinessScope;
	}
	/**
	 * 
	 * @return 注册资本
	 */
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	/**
	 * @param 注册资本
	 */
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	/**
	 * @return 生产经营地址
	 */
	public String getProAndBussAddr() {
		return proAndBussAddr;
	}
	/**
	 * @param 生产经营地址
	 */
	public void setProAndBussAddr(String proAndBussAddr) {
		this.proAndBussAddr = proAndBussAddr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 获取bangdingPhoneStatus  
	 * @return bangdingPhoneStatus bangdingPhoneStatus  
	 */
	public String getBangdingPhoneStatus() {
		return bangdingPhoneStatus;
	}
	/**
	 * 设置bangdingPhoneStatus
	 * @param bangdingPhoneStatus bangdingPhoneStatus  
	 */
	public void setBangdingPhoneStatus(String bangdingPhoneStatus) {
		this.bangdingPhoneStatus = bangdingPhoneStatus;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getContacterPhone() {
		return contacterPhone;
	}
	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}
	/**
	 * @return the jpushRegId
	 */
	public String getJpushRegId() {
		return jpushRegId;
	}
	/**
	 * @param jpushRegId the jpushRegId to set
	 */
	public void setJpushRegId(String jpushRegId) {
		this.jpushRegId = jpushRegId;
	}
}
