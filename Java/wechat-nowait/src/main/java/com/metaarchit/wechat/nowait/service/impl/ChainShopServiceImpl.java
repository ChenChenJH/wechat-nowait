package com.metaarchit.wechat.nowait.service.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metaarchit.wechat.nowait.dao.ChainShopDao;
import com.metaarchit.wechat.nowait.model.ChainShop;
import com.metaarchit.wechat.nowait.model.Restaurant;
import com.metaarchit.wechat.nowait.service.ChainShopService;
import com.metaarchit.wechat.nowait.service.RestaurantService;
/**
 * 連鎖店信息業務邏輯處理接口實現類
 * @author HuangKailie
 * @createTime 2017-11-19 14:27:08
 */
@Service
public class ChainShopServiceImpl implements ChainShopService {
	
	@Resource
	private ChainShopDao chainShopDao;
	
	@Resource
	private RestaurantService restaurantService;
	
	/**
	 * 獲取所有連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	public List<ChainShop> listAllChainShopByLocation(double longitude,
			double latitude) {
		List<ChainShop> chainShops = null;
		chainShops = chainShopDao.selectAllChainShop();
		if (chainShops == null) {
			System.out.println("找不到該連鎖店！");
		} else {
			Iterator<ChainShop> ItChainShop = chainShops.iterator();
			List<Restaurant> restaurants = null;
			while (ItChainShop.hasNext()) {
				ChainShop chainShop = (ChainShop) ItChainShop.next();
				restaurants = chainShop.getRestaurants();
				Collections.sort(restaurants);
				// 設置連鎖店餐廳列表的座位信息
				restaurants = restaurantService.setRestaurantDeskInfo(restaurants);
				// 設置連鎖店餐廳列表的距離
				try {
					restaurants = restaurantService.setRestaurantDistance(restaurants, longitude, latitude);
				} catch (Exception e) {
					e.printStackTrace();
				}
				chainShop.setRestaurants(restaurants);
			}
		}
		return chainShops;
	}

	/**
	 * 根據當前位置以及連鎖店名稱模糊查詢連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @param name 連鎖店名稱
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	public List<ChainShop> listChainShopLikeName(double longitude,
			double latitude, String name) {
		List<ChainShop> chainShops = null;
		chainShops = chainShopDao.selectChainShopLikeName(name);
		if (chainShops == null) {
			System.out.println("找不到該連鎖店！");
		} else {
			Iterator<ChainShop> ItChainShop = chainShops.iterator();
			List<Restaurant> restaurants = null;
			while (ItChainShop.hasNext()) {
				ChainShop chainShop = (ChainShop) ItChainShop.next();
				restaurants = chainShop.getRestaurants();
				Collections.sort(restaurants);
				// 設置連鎖店餐廳列表的座位信息
				restaurants = restaurantService.setRestaurantDeskInfo(restaurants);
				// 設置連鎖店餐廳列表的距離
				try {
					chainShop.setRestaurants(restaurantService.setRestaurantDistance(restaurants, longitude, latitude));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return chainShops;
	}

}
