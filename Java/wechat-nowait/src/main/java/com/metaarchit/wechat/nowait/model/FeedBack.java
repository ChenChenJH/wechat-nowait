package com.metaarchit.wechat.nowait.model;

/**
 * 反饋信息實體類
 * @author ZhengHuanBin
 * @createTime 2017-11-20 16:12:52
 */
public class FeedBack {
	private Integer id;			// 反饋表id
	private Integer wxuserId;	// 微信用戶Id
	private String info;		// 反饋內容
	private String createDate;	// 反饋時間
	private char isRead;		//狀態
	
	public FeedBack(Integer wxuserId, String info) {
		super();
		this.wxuserId = wxuserId;
		this.info = info;
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public char getIsRead() {
		return isRead;
	}
	public void setIsRead(char isRead) {
		this.isRead = isRead;
	}
	
	
}
