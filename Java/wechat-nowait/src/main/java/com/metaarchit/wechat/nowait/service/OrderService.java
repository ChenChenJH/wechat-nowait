package com.metaarchit.wechat.nowait.service;

import com.metaarchit.wechat.nowait.model.Order;

/**
 * 訂單信息業務邏輯處理接口
 * @author ZhengHuanBin
 * @createTime 2017-11-21 11:35:49
 */
public interface OrderService {
	
	/**
	 * 根據微信用戶id獲取單條可用的訂單信息
	 * @param wxuserId 微信用戶id
	 * @return Order 訂單實體類
	 */
	Order getOrderByWxUserId(Integer wxuserId);
	
	/**
	 * 根據restId、style、waitNo來獲取排單數量
	 * @param restId 餐廳id
	 * @param style 桌子類型
	 * @param waitNo 排單號
	 * @return int 排在當前排單號前的訂單數量
	 */
	int getCountOfOrder(Integer restId, String style, String waitNo);
	
	/**
	 * 根據info、count來獲取等候時間 
	 * @param info 用餐人數
	 * @param count 排在當前排單號前的訂單數量
	 * @return int 預計等候時間
	 */
	int getWaitTime(String info, int count);
	
	/**
	 * 添加一條訂單記錄
	 * @param wxUserId 微信用戶id
	 * @param restId 餐廳id
	 * @param restName 餐廳名
	 * @param numberOfPeople 用餐人數
	 * @param warnInfo 取號告示
	 * @return String 牌號編碼
	 */
	String saveOrder(Integer wxUserId, Integer restId, String restName, Integer numberOfPeople, String warnInfo);
	
	/**
	 * 根據用餐人數獲取桌子類型
	 * @param num 用餐人數
	 * @return String 桌子類型
	 */
	String getStyleByNum(int num);
	
	/**
	 * 根据餐廳id和桌子類型來查詢最新排單號
	 * @param restId 餐廳id
	 * @param style  桌子類型
	 * @return String 最新的排單號
	 */
	String gettWaitNoByRestIdAndStyle(Integer restId,String style);
	
	/**
	 * 根据微信用戶id和餐廳id來查詢該用戶在該餐廳的可用訂單數量
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return int 可用訂單數量
	 */
	int getCountOfOrderByWxUserIdAndRestId(Integer wxuserId, Integer restId);
	
	/**
	 * 取消訂單：通過微信用戶id和餐廳id修改訂單為不可用
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return int 修改行數
	 */
	int updateIsUsageByWxUserId(Integer wxuserId, Integer restId);
}
