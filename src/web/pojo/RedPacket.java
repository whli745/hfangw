package web.pojo;

import util.base.BaseObject;

import java.util.Date;

import pt.wzgl.nrgl.pojo.InfoTcontent;

/**
 * Created by whli
 *
 * @version 2016/7/16 15:46
 */
public class RedPacket extends BaseObject{
	private static final long serialVersionUID = 1L;
	private String oid;
    private String packetName;  //红包名称
    private String packetMoney; //红包金额
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private String houseId; //楼盘id
    private String newsId;  //楼盘活动id
    private Integer isDel;  //逻辑删除 （0 未删除  1删除）
    private Date createTime; //创建时间
    private HousingProject housePro; //对应的楼盘
    private InfoTcontent news; //对应的楼盘活动
    
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getPacketMoney() {
        return packetMoney;
    }

    public void setPacketMoney(String packetMoney) {
        this.packetMoney = packetMoney;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public HousingProject getHousePro() {
		return housePro;
	}

	public void setHousePro(HousingProject housePro) {
		this.housePro = housePro;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public InfoTcontent getNews() {
		return news;
	}

	public void setNews(InfoTcontent news) {
		this.news = news;
	}
	
}
