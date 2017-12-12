package com.metaarchit.wechat.nowait.service.impl;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metaarchit.wechat.nowait.dao.OrderDao;
import com.metaarchit.wechat.nowait.dao.RestaurantDao;
import com.metaarchit.wechat.nowait.model.Desk;
import com.metaarchit.wechat.nowait.model.Restaurant;
import com.metaarchit.wechat.nowait.service.RestaurantService;
import com.metaarchit.wechat.nowait.util.CommonUtil;
/**
 * 餐廳信息業務接口實現類
 * @author HuangKailie
 * @createTime 2017-11-14 17:10:35
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Resource
	private RestaurantDao restaurantDao;

	@Resource
	private OrderDao orderDao;
	
	/**
	 * 獲取所有餐廳信息
	 * @return List<Restaurant> 餐廳信息列表
	 */
	public List<Restaurant> listAllRestaurant() {
		List<Restaurant> restaurants = null;
		restaurants = restaurantDao.selectAllRestaurant();
		restaurants = setRestaurantDeskInfo(restaurants);
		Collections.sort(restaurants);
		return restaurants;
	}

	/**
	 * 獲取附近餐廳信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	public List<Restaurant> listNearRestaurants(double longitude, double latitude) {
		List<Restaurant> restaurants = listAllRestaurant();
		restaurants = setRestaurantDeskInfo(restaurants);
		try {
			restaurants = setRestaurantDistance(restaurants, longitude, latitude);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return restaurants;
	}

	/**
	 * 根據餐廳Id號獲取餐廳信息
	 * @param id 餐廳Id號
	 * @return Restaurant 餐廳實體類對象
	 */
	public Restaurant getRestaurantById(int id) {
		Restaurant restaurant = null;
		restaurant = restaurantDao.selectRestaurantById(id);
		if (restaurant == null) {
			System.out.println("該餐廳Id不存在！");
		}
		return restaurant;
	}

	/**
	 * 設置餐廳列表的距離
	 * @param restaurants 餐廳列表
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<Restaurant> 設置了餐廳與當前距離的餐廳列表
	 */
	public List<Restaurant> setRestaurantDistance(List<Restaurant> restaurants,
			double longitude, double latitude) throws Exception {
		// 用於保留兩位小數
		DecimalFormat df = new DecimalFormat(".##");
		double distance;	// 距離
		Iterator<Restaurant> itRestaurant = restaurants.iterator();
		while (itRestaurant.hasNext()) {
			Restaurant restaurant = (Restaurant) itRestaurant.next();
			// 計算出當前位置與餐廳位置距離（單位km）
			distance = CommonUtil.latitudeLongitudeDist(longitude, latitude, restaurant.getLng(), restaurant.getLat());
			restaurant.setDistance(Double.valueOf(df.format(distance)));
		}
		return restaurants;
	}

	/**
	 * 對餐廳列表中各個餐廳的桌子進行從小桌到大桌排序，并設置各類型桌子的等待數量以及所有桌子的等待總量
	 * @param restaurants 餐廳列表
	 * @return List<Restaurant> 對桌子排序后的餐廳信息列表
	 */
	public List<Restaurant> setRestaurantDeskInfo(List<Restaurant> restaurants) {
		Iterator<Restaurant> itRestaurants = restaurants.iterator();
		Iterator<Desk> itDesks = null;
		List<Desk> desks = null;
		Restaurant restaurant = null;
		Desk desk = null;
		int n;
		int count;
		while (itRestaurants.hasNext()) {
			count = 0;
			restaurant = (Restaurant) itRestaurants.next();
			desks = restaurant.getDesks();
			Collections.sort(desks);
			itDesks = desks.iterator();
			while (itDesks.hasNext()) {
				desk = (Desk) itDesks.next();
				n = orderDao.selectOrderCountByRestIdAndTableStyle(restaurant.getId(), desk.getStyle());
				count += n;
				desk.setWaitTableSum(n);
			}
			restaurant.setWaitTableSum(count);
			restaurant.setDesks(desks);
		}
		return restaurants;
	}

	/**
	 * 通過分頁查詢獲取附近餐廳信息
	 * @param start 開始位置
	 * @param limit 信息數量
	 * @param longtitude 經度
	 * @param latitude 緯度
	 * @return List<Restaurant> 附近餐廳信息列表
	 */
	public List<Restaurant> listRestaurantByLimit(int start, int limit,
			double longitude, double latitude) {
		List<Restaurant> restaurants = null;
		restaurants = restaurantDao.selectRestaurantByLimit(start, limit);
		restaurants = setRestaurantDeskInfo(restaurants);
		try {
			restaurants = setRestaurantDistance(restaurants, longitude, latitude);
		} catch (Exception e) {
			System.out.println("設置距離出錯！");
			e.printStackTrace();
		}
		Collections.sort(restaurants);
		return restaurants;
	}

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
	public List<Restaurant> listRestaurantByConditionAndLimit(double longitude,
			double latitude, boolean status, boolean isOverdue, int start,
			int limit) {
		List<Restaurant> restaurants = null;
		restaurants = restaurantDao.selectRestaurantsByConditionAndLimit(status, isOverdue, start, limit);
		restaurants = setRestaurantDeskInfo(restaurants);
		try {
			restaurants = setRestaurantDistance(restaurants, longitude, latitude);
		} catch (Exception e) {
			System.out.println("屬性設置出錯！");
			e.printStackTrace();
		}
		Collections.sort(restaurants);
		return restaurants;
	}
}
