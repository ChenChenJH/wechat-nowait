package com.metaarchit.wechat.nowait.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.metaarchit.wechat.nowait.model.Order;
/**
 * 訂單信息DAO事務處理類
 * @author ZhengHuanBin
 * @createTime 2017-11-21 11:01:24
 */
@Repository
public interface OrderDao {

	/**
	 * 根據餐廳Id以及桌子類型查詢可用訂單數量
	 * @param restId 餐廳類型
	 * @param tableStyle 桌子類型
	 * @return int 訂單數量
	 */
	int selectOrderCountByRestIdAndTableStyle(@Param("restId") Integer restId, @Param("tableStyle") String tableStyle);
	
	/**
	 * 通過微信用戶id來查詢單條可用訂單
	 * @param wxuserId 微信用戶id
	 * @return Order 訂單類
	 */
	Order selectOrderByWxUserId(Integer wxuserId);

	/**
	 * 通過餐廳Id查詢訂單信息
	 * @param restId 餐廳Id號
	 * @return List<Order> 訂單信息列表
	 */
	List<Order> selectOrdersByRestId(@Param("id") int restId);
	
	/**
	 * 通過restId、style、waitNo來查詢排單數量
	 * @param restId 餐廳Id號
	 * @param style 桌子類型
	 * @param waitNo 排單號
	 * @return int 排在當前排單號前的訂單數量
	 */
	int selectCountOfOrder(@Param("restId") Integer restId, @Param("style") String style,
			@Param("waitNo") String waitNo);
	
	/**
	 * 插入壹條訂單記錄
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @param restName 餐廳名
	 * @param style 桌子類型
	 * @param String info 人數信息
	 * @param waitNo 排單號
	 * @param warnInfo 取號告示
	 * @return int 插入成功行數
	 */
	int insertOrder(@Param("wxuserId") Integer wxuserId, @Param("restId") Integer restId,
			@Param("restName") String restName, @Param("style") String style, @Param("info") String info, @Param("waitNo") String waitNo,
			@Param("warnInfo") String warnInfo);
	
	/**
	 * 通過餐廳id和桌子類型來查詢最新排單號
	 * @param restId 餐廳id
	 * @param style  桌子類型
	 * @return String 最新的排單號
	 */
	String selectWaitNoByRestIdAndStyle(@Param("restId") Integer restId,@Param("style") String style);
	
	/**
	 * 通過微信用戶id和餐廳id來查詢該用戶在該餐廳的可用訂單數量
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return int 可用訂單數量
	 */
	int selectCountOfOrderByWxUserIdAndRestId(@Param("wxuserId")Integer wxuserId, @Param("restId") Integer restId);
	
	/**
	 * 通過微信用戶id、餐廳id 修改訂單為不可用
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return int 修改行數
	 */
	int updateIsUsageByWxUserId(@Param("wxuserId")Integer wxuserId, @Param("restId") Integer restId);
}