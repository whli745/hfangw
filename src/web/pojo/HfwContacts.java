package web.pojo;

import java.util.Date;

import util.base.BaseObject;


/**
 * 领取人实体类
 * Created by whli
 *
 * @version 2016/7/16 15:51
 */
public class HfwContacts extends BaseObject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oid;
    private String linkMan;  //领取人姓名
    private String linkPhone; //领取人手机
    private String packetId; //户型红包关联表
    private Integer status; //红包是否领取 1未领取 2 已领取
    private Date createTime;  //顾客领取时间
    private RedPacket packet; //对应的红包

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RedPacket getPacket() {
        return packet;
    }

    public void setPacket(RedPacket packet) {
        this.packet = packet;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
