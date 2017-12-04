package com.metaarchit.wechat.nowait.model;
/**
 * 訂單實體類
 * @author ZhengHuanBin
 * @createTime 2017-11-21 10:44:36
 */
public class Order {
	private Integer id;			//訂單id
	private Integer wxuserId;	//微信用戶id
	private Integer restId;		//餐廳id
	private String restName;	//餐廳名
	private String style;		//桌子類型
	private String info;		//人數信息
	private String waitNo;		//排隊號碼
	private String createDate;	//下單時間
	private char isUsage;		//是否能用 ：是/否
	private String warnInfo;	//取號告示
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWxuserId() {
		return wxuserId;
	}
	public void setWxuserId(Integer wxuserId) {
		this.wxuserId = wxuserId;
	}
	public Integer getRestId() {
		return restId;
	}
	public void setRestId(Integer restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getWaitNo() {
		return waitNo;
	}
	public void setWaitNo(String waitNo) {
		this.waitNo = waitNo;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public char getIsUsage() {
		return isUsage;
	}
	public void setIsUsage(char isUsage) {
		this.isUsage = isUsage;
	}
	public String getWarnInfo() {
		return warnInfo;
	}
	public void setWarnInfo(String warnInfo) {
		this.warnInfo = warnInfo;
	}
	
}
