package com.metaarchit.wechat.nowait.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.metaarchit.wechat.nowait.dao.RestaurantDao;
import com.metaarchit.wechat.nowait.model.Restaurant;
import com.metaarchit.wechat.nowait.service.RestaurantService;

public class RestaurantTest extends BaseTest {
	
	@Resource
	private RestaurantService restaurantService;
	
	@Resource
	private RestaurantDao restaurantDao;
	
	@Test
	public void test() {
//		List<Restaurant> restaurants = restaurantService.listAllRestaurant();
//		System.out.println("---排序前---");
//		for (Restaurant restaurant : restaurants) {
//			System.out.println(restaurant.getName() + " " + restaurant.getStatus() + " " + restaurant.getOrders().size());
//		}
//		System.out.println("---排序后---");
//		Collections.sort(restaurants);
//		for (Restaurant restaurant : restaurants) {
//			System.out.println(restaurant.getName() + " " + restaurant.getStatus());
//		}
		List<Restaurant> restaurants = restaurantService.listAllRestaurant();
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant.toString());
		}
	}
}
