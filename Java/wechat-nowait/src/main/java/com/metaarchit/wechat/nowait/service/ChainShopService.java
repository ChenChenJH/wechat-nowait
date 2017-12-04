package com.metaarchit.wechat.nowait.service;

import java.util.List;

import com.metaarchit.wechat.nowait.model.ChainShop;

/**
 * 連鎖店業務邏輯處理接口
 * @author HuangKailie
 * @createTime 2017-11-19 14:24:59
 */
public interface ChainShopService {

	/**
	 * 根據當前位置獲取所有連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	List<ChainShop> listAllChainShopByLocation(double longitude, double latitude);
	
	/**
	 * 根據當前位置以及連鎖店名稱模糊查詢連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @param name 連鎖店名稱
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	List<ChainShop> listChainShopLikeName(double longitude, double latitude, String name);
}
