package com.metaarchit.wechat.nowait.model;

import java.util.List;

/**
 * 連鎖店實體類
 * @author HuangKailie
 * @createTime 2017-11-19 13:41:20
 */
public class ChainShop {
	
	private Integer id;	// 連鎖店ID
	private String name;	// 連鎖店名稱
	private String pic;	// 連鎖店圖片URL
	// 連鎖店分店，壹對多級聯
	private List<Restaurant> restaurants = null;
	
	@Override
	public String toString() {
		return "ChainShop [id=" + id + ", name=" + name + ", pic=" + pic + ", restaurants=" + restaurants + "]";
	}

	public ChainShop(Integer id, String name, String pic, List<Restaurant> restaurants) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.restaurants = restaurants;
	}

	public ChainShop() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
}