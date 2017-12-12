package com.metaarchit.wechat.nowait.service;

import java.text.ParseException;

import com.metaarchit.wechat.nowait.model.FeedBack;

/**
 * 反饋信息業務邏輯處理接口
 * @author ZhengHuanBin
 * @createTime 2017-11-20 16:39:48
 */
public interface FeedBackService {
	
	/**
	 * 添加反饋記錄
	 * @param feedBack 反饋信息實體類
	 * @return int 成功添加的數量
	 */
	int saveFeedBack(FeedBack feedBack);
	
	/**
	 * 獲取反饋時間間隔
	 * @param wxUserId 微信用戶id
	 * @return int -1：當前用戶沒有反饋記錄  0:反饋時間間隔不超過1天  1：超過1天
	 */
	int getFeedBackTime(Integer wxUserId) throws ParseException;
}
