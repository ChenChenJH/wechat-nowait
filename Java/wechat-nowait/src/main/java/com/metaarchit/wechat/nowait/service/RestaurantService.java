package com.metaarchit.wechat.nowait.service;

import java.util.List;

import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;

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
	 * 根據餐廳Id號獲取餐廳信息
	 * @param id 餐廳Id號
	 * @return Restaurant 餐廳實體類對象
	 */
	Restaurant getRestaurantById(int id);

	/**
	 * 獲取附近餐廳信息
	 * @param longtitude 經度
	 * @param latitude 緯度
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	List<Restaurant> listNearRestaurants(double longitude, double latitude);

	/**
	 * 通過條件篩選以及分頁查詢獲取附近餐廳信息
	 * @param longitude 經度
	 * @param latitude 緯度
	 * @param status 是否可取號，true為可取，false為不可取
	 * @param isOverdue 是否為過號不作廢，true為是，false為否
	 * @param start 開始位置
	 * @param limit 信息數量
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	List<Restaurant> listRestaurantByConditionAndLimit(double longitude, double latitude, boolean status, boolean isOverdue, int start, int limit);

	/**
	 * 通過餐廳名稱模糊獲取餐廳列表
	 * @param longitude 經度
	 * @param latitude 緯度
	 * @param name 餐廳名
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	List<Restaurant> listRestaurantLikeName(double longitude, double latitude, String name);
}
