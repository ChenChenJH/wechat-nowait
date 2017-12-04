package com.metaarchit.wechat.nowait.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metaarchit.wechat.nowait.dao.DeskDao;
import com.metaarchit.wechat.nowait.dao.OrderDao;
import com.metaarchit.wechat.nowait.model.Desk;
import com.metaarchit.wechat.nowait.model.Order;
import com.metaarchit.wechat.nowait.service.OrderService;
/**
 * 訂單信息業務邏輯處理接口實現類
 * @author ZhengHuanBin
 * @createTime 2017-11-21 11:37:03
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private DeskDao deskDao;
	
	/**
	 * 根據微信用戶id獲取單條可用的訂單信息
	 * @param wxuserId 微信用戶id
	 * @return Order 訂單實體類
	 */
	public Order getOrderByWxUserId(Integer wxuserId) {
		return orderDao.selectOrderByWxUserId(wxuserId);
	}
	
	/**
	 * 根據restId、style、waitNo來獲取排單數量
	 * @param restId 餐廳id
	 * @param style 桌子類型
	 * @param waitNo 排單號
	 * @return int 排在當前排單號前的訂單數量
	 */
	public int getCountOfOrder(Integer restId, String style, String waitNo) {
		return orderDao.selectCountOfOrder(restId, style, waitNo);
	}
	
	/**
	 * 根據info、count來獲取等候時間 
	 * @param info 用餐人數
	 * @param count 排在當前排單號前的訂單數量
	 * @return int 預計等候時間
	 */
	public int getWaitTime(String info, int count) {
		info = info.replace("人", "");
		String[] str = info.split("-");
		int avg = (int)Math.ceil(( Integer.parseInt(str[0]) + Integer.parseInt(str[1]) ) / 2);
		int waitTime;
		System.out.println("平均用餐人數avg："+avg);
		switch(avg) {
		  case 1:
		  case 2:
		  case 3: waitTime = 3; break;
		  case 4:
		  case 5:
		  case 6: waitTime = 5; break;
		  case 7:
		  case 8:
		  case 9: waitTime = 7; break;
		  default: waitTime = 10; break;
		}
		return waitTime * count;
	}

	/**
	 * 根據用餐人數獲取桌子類型
	 * @param num 用餐人數
	 * @return String[0] 桌子類型
	 */
	public String getStyleByNum(int num) {
		String str = "小桌";
		if (num < 5) {
			str = "小桌";
		} else if (num < 9) {
			str = "中桌";
		} else if (num < 13) {
			str = "大桌";
		}
		return str;
	}

	/**
	 * 根据餐廳id和桌子類型來查詢最新排單號
	 * @param restId 餐廳id
	 * @param style  桌子類型
	 * @return String 当前的排單號
	 */
	public String gettWaitNoByRestIdAndStyle(Integer restId, String style) {
		String waitNo = orderDao.selectWaitNoByRestIdAndStyle(restId, style);
		if (waitNo != null) {
			System.out.println("等待编号：" + waitNo);
			String head = waitNo.substring(0,1);	//截取编号中的首位
			String no = waitNo.substring(1,3);		//截取编号中的数字部分
			int noTemp = Integer.valueOf(no);
			if (noTemp < 9) {
				noTemp++;
				waitNo = head + "0" + String.valueOf(noTemp);
			} else {
				noTemp++;
				waitNo = head + String.valueOf(noTemp);
			}
		} else {
			if ("小桌".equals(style)) {
				waitNo = "C01";
			} else if ("中桌".equals(style)) {
				waitNo = "B01";
			} else {
				waitNo = "A01";
			}
		}
		return waitNo;
	}

	public int getCountOfOrderByWxUserIdAndRestId(Integer wxuserId,
			Integer restId) {
		return orderDao.selectCountOfOrderByWxUserIdAndRestId(wxuserId, restId);
	}

	/**
	 * 添加一條訂單記錄
	 * @param wxUserId 微信用戶id
	 * @param restId 餐廳id
	 * @param restName 餐廳名
	 * @param numberOfPeople 用餐人數
	 * @param warnInfo 取號告示
	 * @return String 牌號編碼
	 */
	public String saveOrder(Integer wxUserId, Integer restId, String restName,
			Integer numberOfPeople, String warnInfo) {
		List<Desk> desks = deskDao.selectDeskByRestId(restId);
		System.out.println(desks.toString());
		String info = "", style = "", waitNo = "";
		int minPeopleSum, maxPeopleSum;
		String[] temp;
		for (Desk desk : desks) {
			info = desk.getInfo();
			info = info.replace("人", "");
			temp = info.split("-");
			minPeopleSum = Integer.parseInt(temp[0]);
			maxPeopleSum = Integer.parseInt(temp[1]);
			if (numberOfPeople >= minPeopleSum && numberOfPeople <= maxPeopleSum) {
				style = desk.getStyle();
				info = desk.getInfo();
				waitNo = gettWaitNoByRestIdAndStyle(restId, style);
				break;
			}
		}
		int i = orderDao.insertOrder(wxUserId, restId, restName, style, info, waitNo, warnInfo);
		if (i > 0) {
			return waitNo;
		}
		return "";
	}
	
	/**
	 * 取消訂單：通過微信用戶id和餐廳id修改訂單為不可用
	 * @param wxuserId 微信用戶id
	 * @param restId 餐廳id
	 * @return int 修改行數
	 */
	public int updateIsUsageByWxUserId(Integer wxuserId, Integer restId) {
		int result = orderDao.updateIsUsageByWxUserId(wxuserId, restId);
		if(result > 0) {
			System.out.println("成功取消訂單 ");
		} else {
			System.out.println("取消訂單失敗！");
		}
		return result;
	}
}
