package com.metaarchit.wechat.nowait.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.metaarchit.wechat.nowait.model.Order;
import com.metaarchit.wechat.nowait.service.OrderService;
import com.metaarchit.wechat.nowait.util.CommonUtil;
/**
 * 訂單信息JSON控制類
 * @author ZhengHuanBin
 * @createTime 2017-11-21 11:39:22
 */
@Controller
@RequestMapping(value="/order")
public class OrderJSONController {
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 獲取我的排隊單號信息
	 * @param wxuserId 微信用戶id
	 * @return String
	 */
	@RequestMapping("/getOrderByWxUserId")
	public @ResponseBody String getOrderByWxUserId(Integer wxuserId){
		Order order = null;
		int count; //排隊數量
		int waitTime; //預計等候時間
		order = orderService.getOrderByWxUserId(wxuserId);
		//當前有訂單存在
		if(order != null) {
			count = orderService.getCountOfOrder(order.getRestId(), order.getStyle(), order.getWaitNo());
			waitTime = orderService.getWaitTime(order.getInfo(), count);
			System.out.println("微信用戶id："+wxuserId+"\n排在當前單號前的訂單數量："+count+"\n預計等候時間："+waitTime);
			JSONObject result = new JSONObject();
			result.put("restId", order.getRestId());
			result.put("restName", order.getRestName());
			result.put("createDate", order.getCreateDate());
			result.put("waitNo", order.getWaitNo());
			result.put("count", count);
			result.put("waitTime", waitTime);
			return result.toJSONString();
		}
		//當前無訂單
		else {
			return "noOrder";
		}
	}
	
	/**
	 * 添加訂單
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @param restName 餐廳名
	 * @param numberOfPeople 用餐人數
	 * @param warnInfo 使用告示
	 * @return String
	 */
	@RequestMapping("/saveOrder")
	public @ResponseBody String saveOrder(@RequestParam Integer wxuserId, @RequestParam Integer restId, @RequestParam String restName, @RequestParam Integer numberOfPeople, @RequestParam String warnInfo) {
		Order order = null;
		order = orderService.getOrderByWxUserId(wxuserId);
		if (order != null) {
			return "多余訂單";
		} else {
			if (CommonUtil.isMessyCode(restName)) {
				restName = CommonUtil.encodeStr(restName);
			}
			if (CommonUtil.isMessyCode(warnInfo)) {
				warnInfo = CommonUtil.encodeStr(warnInfo);
			}
			String waitNO;
			waitNO = orderService.saveOrder(wxuserId, restId, restName, numberOfPeople, warnInfo);
			if (!"".equals(waitNO)) 
				return waitNO;
			return "取號失敗";
		}
	}
	
	/**
	 * 取消當前餐廳的訂單
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return String
	 */
	@RequestMapping("cancelOrder")
	public @ResponseBody String cancelOrder(@RequestParam Integer wxuserId, @RequestParam Integer restId) {
		int flag = orderService.updateIsUsageByWxUserId(wxuserId, restId);
		if(flag>0){
			return "取消成功";
		} else {
			return "取消失敗";
		}
	}
}
