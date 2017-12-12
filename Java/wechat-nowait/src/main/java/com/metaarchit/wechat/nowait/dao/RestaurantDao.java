package com.metaarchit.wechat.nowait.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.metaarchit.wechat.nowait.model.Restaurant;
/**
 * 餐廳信息事務處理DAO接口
 * @author HuangKailie
 * @createTime 2017-11-14 17:00:11
 */
@Repository
public interface RestaurantDao {
	
	/**
	 * 根據“可手機取號”、“過號不作廢”以及LIMIT查詢餐廳信息
	 * @param status 為true表示“可手機取號”
	 * @param isOverdue 為true表示“過號不作廢”
	 * @param start 開始位置
	 * @param limit 信息數量
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> selectRestaurantsByConditionAndLimit(@Param("status") boolean status, @Param("isOverdue") boolean isOverdue, @Param("start") int start, @Param("limit") int limit);

	/**
	 * 根據Id獲取餐廳信息
	 * @param id 餐廳ID號
	 * @return Restaurant 餐廳實體類對象
	 */
	Restaurant selectRestaurantById(int id);
	
	/**
	 * 查詢所有餐廳信息
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> selectAllRestaurant();
	
	/**
	 * 分頁查詢餐廳信息
	 * @param start 開始位置
	 * @param limit 信息數量
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> selectRestaurantByLimit(@Param("start") int start, @Param("limit") int limit);
	
	/**
	 * 根據連鎖店名稱查詢餐廳信息
	 * @param name 連鎖店名稱
	 * @return List<Restaurant> 餐廳信息列表
	 */
	List<Restaurant> selectRestaurantsByChainName(String name);

}
