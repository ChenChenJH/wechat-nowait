package com.metaarchit.wechat.nowait.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metaarchit.wechat.nowait.model.ChainShop;
import com.metaarchit.wechat.nowait.service.ChainShopService;
import com.metaarchit.wechat.nowait.util.CommonUtil;
/**
 * 連鎖店信息JSON控制類
 * @author HuangKailie
 * @createTime 2017-11-19 14:29:44
 */
@Controller
@RequestMapping(value="/chainShop")
public class ChainShopJSONController {

	@Resource
	private ChainShopService chainShopService;
	
	/**
	 * 根據連鎖店名稱模糊查詢連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置經度
	 * @param name 連鎖店名稱
	 * @return List<ChainShop> JSON格式的連鎖店信息列表
	 */
	@RequestMapping(value="/showChainShopLikeName")
	public @ResponseBody List<ChainShop> showChainShopLikeName(@RequestParam double longitude, @RequestParam double latitude, @RequestParam String name) {
		// 格式化編碼，防止中文亂碼
		name = CommonUtil.encodeStr(name);
		List<ChainShop> chainShops = null;
		if (!"".equals(name.trim())) {	// 判斷關鍵字是否為空
			chainShops = chainShopService.listChainShopLikeName(longitude, latitude, name);
			if (chainShops != null) {
				System.out.println("---所搜索的連鎖店信息---");
				for (ChainShop chainShop : chainShops) {
					System.out.println(chainShop.toString());
				}
			}
		}
		return chainShops;
	}
	
	/**
	 * 獲取所有連鎖店信息
	 * @param longitude 當前位置經度
	 * @param latitude 當前位置緯度
	 * @return List<ChainShop> JSON格式的所有連鎖店信息列表
	 */
	@RequestMapping(value="/showAllChainShop")
	public @ResponseBody List<ChainShop> showAllChainShop(@RequestParam double longitude, @RequestParam double latitude) {
		List<ChainShop> chainShops = null;
		chainShops = chainShopService.listAllChainShopByLocation(longitude, latitude);
		System.out.println("---所有連鎖店信息---");
		for (ChainShop chainShop : chainShops) {
			System.out.println(chainShop.toString());
		}
		return chainShops;
	}
}
