package com.metaarchit.wechat.nowait.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metaarchit.wechat.nowait.model.ChainShop;
/**
 * 連鎖店信息DAO事務處理類
 * @author HuangKailie
 * @createTime 2017-11-19 14:08:58
 */
@Repository
public interface ChainShopDao {

	/**
	 * 查詢所有連鎖店信息
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	List<ChainShop> selectAllChainShop();
	
	/**
	 * 根據連鎖店名稱模糊查詢連鎖店信息
	 * @param name 連鎖店名稱
	 * @return List<ChainShop> 連鎖店信息列表
	 */
	List<ChainShop> selectChainShopLikeName(String name);
}