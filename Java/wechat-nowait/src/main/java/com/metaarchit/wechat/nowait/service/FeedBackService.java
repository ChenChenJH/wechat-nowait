package com.metaarchit.wechat.nowait.service;

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
}
