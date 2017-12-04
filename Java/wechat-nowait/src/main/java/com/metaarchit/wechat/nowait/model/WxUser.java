package com.metaarchit.wechat.nowait.model;

/**
 * 微信用戶實體類
 * @author HuangKailie
 * @createTime 2017-11-19 13:38:25
 */
public class WxUser {
	
	private Integer id;	// 微信用戶編號
	private String openid;	// 微信用戶openId
	private String phone;	// 用戶綁定手機號
	private String createDate;	// 創建時間
	
	public WxUser(String openid, String phone) {
		super();
		this.openid = openid;
		this.phone = phone;
	}

	public WxUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WxUser [id=" + id + ", openid=" + openid + ", phone=" + phone
				+ ", createDate=" + createDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
