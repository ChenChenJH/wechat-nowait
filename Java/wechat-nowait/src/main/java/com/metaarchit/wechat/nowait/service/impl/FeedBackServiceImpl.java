package com.metaarchit.wechat.nowait.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metaarchit.wechat.nowait.dao.FeedBackDao;
import com.metaarchit.wechat.nowait.model.FeedBack;
import com.metaarchit.wechat.nowait.service.FeedBackService;
/**
 * 反饋信息業務邏輯處理接口實現類
 * @author ZhengHuanBin
 * @createTime 2017-11-20 16:41:30
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Resource
	private FeedBackDao feedBackDao;
	
	/**
	 * 添加反饋記錄
	 * @param feedBack 反饋信息實體類
	 * @return int 成功添加的數量
	 */
	public int saveFeedBack(FeedBack feedBack) {
		return feedBackDao.insertFeedBack(feedBack);
	}

}
