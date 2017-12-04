package com.metaarchit.wechat.nowait.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metaarchit.wechat.nowait.model.Restaurant;
import com.metaarchit.wechat.nowait.service.RestaurantService;
import com.metaarchit.wechat.nowait.util.CommonUtil;

/**
 * 餐廳信息JSON控制類
 * @author HuangKailie
 * @createTime 2017-11-14 17:33:53
 */
@Controller
@RequestMapping(value="/restaurant")
public class RestJSONController {
	
	@Resource
	private RestaurantService restaurantService;
	
	@Value("#{configProperties['jdbc.username']}")
	private String test;
	
	@Value("#{configProperties['wechat.appid']}")
	private String appid;
	
	@Value("#{configProperties['wechat.secret']}")
	private String secret;
	
	/**
	 * 根據條件篩選附近餐廳
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @param status 是否可手機取號，true代表是，false代表否
	 * @param isOverdue 是否為過號不作廢，true代表是，false代表否
	 * @return List<Restaurant> JSON格式的篩選后的餐廳列表
	 */
	@RequestMapping(value="/showRestaurantsByCondition")
	public @ResponseBody List<Restaurant> showRestaurantsByCondition(@RequestParam double longitude, @RequestParam double latitude, @RequestParam("btn1") boolean status, @RequestParam("btn2") boolean isOverdue) {
		System.out.println("可手機取號：" + status + "\n過號不作廢：" + isOverdue);
		List<Restaurant> restaurants = null;
		restaurants = restaurantService.listRestaurantsByCondition(longitude, latitude, status, isOverdue);
		return restaurants;
	}
	
	/**
	 * 根據餐廳Id號獲取餐廳信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @param id 餐廳Id號
	 * @return Restaurant 餐廳對象
	 */
	@RequestMapping(value="/showRestaurantInfoById")
	public @ResponseBody Restaurant showRestaurantInfoById(@RequestParam int id, @RequestParam double longitude, @RequestParam double latitude) {
		System.out.println("經度：" + longitude + "\t緯度：" + latitude);
		Restaurant restaurant = null;
		restaurant = restaurantService.getRestaurantById(id);
		// 格式化數據，保留兩位小數
		DecimalFormat df = new DecimalFormat(".##");
		// 通過經緯度計算當前位置與餐廳的距離
		double distance = CommonUtil.latitudeLongitudeDist(longitude, latitude, restaurant.getLng(), restaurant.getLat());
		// 將距離格式化并設置為restaurant對象的distance屬性
		restaurant.setDistance(Double.valueOf(df.format(distance)));
		return restaurant;
	}
	
	/**
	 * 根據餐廳名稱模糊獲取餐廳信息
	 * @param name 餐廳名稱
	 * @return List<Restaurant> JSON格式的餐廳信息列表
	 */
	@RequestMapping(value="/showRestaurantsLikeName")
	public @ResponseBody List<Restaurant> showRestaurantsLikeName(String name) {
		name = CommonUtil.encodeStr(name);
		System.out.println("餐廳名稱：" + name);
		List<Restaurant> restaurants = restaurantService.listRestaurantsLikeName(name);
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant.toString());
		}
		return restaurants;
	}
	
	/**
	 * 獲取附近所有餐廳
	 * @param longitude 當前位置的經度
	 * @param latitude 當前位置的緯度
	 * @return List<Restaurant> JSON格式的附近餐廳信息列表
	 */
	@RequestMapping(value="/showNearRestaurant")
	public @ResponseBody List<Restaurant> showNearRestaurant(@RequestParam Double longitude, @RequestParam Double latitude) {
		System.out.println("經度：" + longitude + "\t緯度：" + latitude);
		List<Restaurant> restaurants = restaurantService.listNearRestaurants(longitude, latitude);
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant.toString());
		}
		return restaurants;
	}

}
