package com.metaarchit.wechat.nowait.dao;

import org.springframework.stereotype.Repository;

import com.metaarchit.wechat.nowait.model.FeedBack;
/**
 * 反饋信息DAO事務處理類
 * @author ZhengHuanBin
 * @createTime 2017-11-20 16:15:13
 */
@Repository
public interface FeedBackDao {
	
	/**
	 * 添加新的反饋信息
	 * @param feedBack 反饋信息實體類
	 * @return int 返回插入成功行數
	 */
	int insertFeedBack(FeedBack feedBack);
	
	/**
	 * 通過微信用戶id獲取最新的壹條反饋記錄的時間
	 * @param wxuserId 微信用護id
	 * @return String 反饋時間
	 */
	String selectCreateDateByWxUserId(Integer wxuserId);
}
