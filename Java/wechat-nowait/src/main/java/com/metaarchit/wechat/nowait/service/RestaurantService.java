package com.metaarchit.wechat.nowait.service;

import java.util.List;

import com.metaarchit.wechat.nowait.model.Restaurant;

/**
 * 餐廳信息業務處理接口
 * @author HuangKailie
 * @createTime 2017-11-14 17:07:41
 */
public interface RestaurantService {
	
	/**
	 * 對餐廳列表中各個餐廳的桌子進行從小桌到大桌排序，并設置各類型桌子的等待數量以及所有桌子的等待總量
	 * @param restaurants 餐廳列表
	 * @return List<Restaurant> 對桌子排序后的餐廳信息列表
	 */
	List<Restaurant> setRestaurantDeskInfo(List<Restaurant> restaurants);
	
	/**
	 * 設置餐廳列表的距離
	 * @param restaurants 餐廳列表
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<Restaurant> 設置了餐廳與當前距離的餐廳列表
	 */
	List<Restaurant> setRestaurantDistance(List<Restaurant> restaurants, double longitude, double latitude) throws Exception;
	
	/**
	 * 根據條件獲取附近餐廳列表
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @param status 是否可取號，true為可取，false為不可取
	 * @param isOverdue 是否為過號不作廢，true為是，false為否
	 * @return List<Restaurant> 篩選後的餐廳列表
	 */
	List<Restaurant> listRestaurantsByCondition(double longitude, double latitude, boolean status, boolean isOverdue);
	
	/**
	 * 根據餐廳Id號獲取餐廳信息
	 * @param id 餐廳Id號
	 * @return Restaurant 餐廳實體類對象
	 */
	Restaurant getRestaurantById(int id);
	
	/**
	 * 根據餐廳名稱模糊獲取餐廳信息
	 * @param name 餐廳名稱
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> listRestaurantsLikeName(String name);
	
	/**
	 * 獲取所有餐廳信息
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> listAllRestaurant();
	
	/**
	 * 獲取附近餐廳信息
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	List<Restaurant> listNearRestaurants(Double longitude, Double latitude);

}
