package com.metaarchit.wechat.nowait.model;
/**
 * 座位信息實體類
 * @author HuangKailie
 * @createTime 2017-11-23 16:17:27
 */
public class Desk implements Comparable<Desk> {

	private Integer id;	// 座位編號
	private String style;	// 座位類型
	private String info;	// 人數信息
	private Integer restId;	// 餐廳Id號
	private Integer waitTableSum;	// 等待桌子數量
	
	/**
	 * 根據座位人數排序，小桌在前大桌在后
	 * @param Desk 座位實體對象
	 */
	public int compareTo(Desk desk) {
		int a = Integer.parseInt(this.info.split("-")[0]);
		int b = Integer.parseInt(desk.info.split("-")[0]);
		return a > b ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Desk [id=" + id + ", style=" + style + ", info=" + info
				+ ", restId=" + restId + ", waitTableNum=" + waitTableSum + "]";
	}

	public Integer getWaitTableSum() {
		return waitTableSum;
	}
	public void setWaitTableSum(Integer waitTableSum) {
		this.waitTableSum = waitTableSum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getRestId() {
		return restId;
	}
	public void setRestId(Integer restId) {
		this.restId = restId;
	}
	
}
