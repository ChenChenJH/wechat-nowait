package com.metaarchit.wechat.nowait.model;

import java.util.List;

/**
 * 餐廳實體類
 * @author HuangKailie
 * @createTime 2017-11-14 16:57:29
 */
public class Restaurant implements Comparable<Restaurant> {

	private Integer id;	// 編號
	private String name;	// 餐廳名
	private String priceInfo;	// 價格簡介
	private String warnInfo;	// 取號告示
	private String address;	// 地址
	private String phone;	// 客服電話
	private String shopTime;	// 營業時間
	private String status;	// 餐廳狀況
	private Integer userId;	// 用戶id
	private String chainName;	// 連鎖店名稱
	private String coverPic;	// 餐廳門面圖
	private String navPic;	// 餐廳導航圖
	private Double lng;	// 經度
	private Double lat;	// 緯度
	private String createDate;	// 創建時間
	private String isOverdue;	// 是否為過號不作廢
	private Double distance;	// 距離
	private int waitTableSum;	// 正在等待的總桌數
	// 餐廳訂單，壹對多級聯
	private List<Order> orders = null;
	// 餐廳座位，一對多級聯
	private List<Desk> desks = null;
	
	/**
	 * 根據各餐廳狀態排序
	 */
	public int compareTo(Restaurant restaurant) {
		if ("前方正在等待".equals(this.status)) {
			return -1;
		}
		if ("前方正在等待".equals(restaurant.getStatus())) {
			return 1;
		}
		if ("當前排隊狀況".equals(this.status)) {
			return -1;
		}
		if ("當前排隊狀況".equals(restaurant.getStatus())) {
			return 1;
		}
		if ("餐廳暫停取號".equals(this.status)) {
			return -1;
		}
		if ("餐廳暫停取號".equals(restaurant.getStatus())) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", priceInfo="
				+ priceInfo + ", warnInfo=" + warnInfo + ", address=" + address
				+ ", phone=" + phone + ", shopTime=" + shopTime + ", status="
				+ status + ", userId=" + userId + ", chainName=" + chainName
				+ ", coverPic=" + coverPic + ", navPic=" + navPic + ", lng="
				+ lng + ", lat=" + lat + ", createDate=" + createDate
				+ ", isOverdue=" + isOverdue + ", distance=" + distance
				+ ", waitTableSum=" + waitTableSum + ", orders=" + orders
				+ ", desks=" + desks + "]";
	}

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public List<Desk> getDesks() {
		return desks;
	}
	public void setDesks(List<Desk> desks) {
		this.desks = desks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriceInfo() {
		return priceInfo;
	}
	public void setPriceInfo(String priceInfo) {
		this.priceInfo = priceInfo;
	}
	public String getWarnInfo() {
		return warnInfo;
	}
	public void setWarnInfo(String warnInfo) {
		this.warnInfo = warnInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopTime() {
		return shopTime;
	}
	public void setShopTime(String shopTime) {
		this.shopTime = shopTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getNavPic() {
		return navPic;
	}
	public void setNavPic(String navPic) {
		this.navPic = navPic;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public int getWaitTableSum() {
		return waitTableSum;
	}
	public void setWaitTableSum(int waitTableSum) {
		this.waitTableSum = waitTableSum;
	}


}
