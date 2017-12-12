package com.metaarchit.wechat.nowait.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	/**
	 * 獲取反饋時間間隔
	 * @param wxUserId 微信用戶id
	 * @return int -1：當前用戶沒有反饋記錄  0:反饋時間間隔不超過1天   1：超過1天
	 */
	public int getFeedBackTime(Integer wxUserId) throws ParseException {
		String createDate = feedBackDao.selectCreateDateByWxUserId(wxUserId);
		System.out.println("createDate："+createDate);
		if(createDate == null) {  //找不到歷史反饋記錄
			return -1;
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  //規定日期格式，截取數據庫中的日期，去掉時間
		String stringNowTime= format.format(new Date());
	    Date nowTime = format.parse(stringNowTime);
		Date createDateTime = format.parse(createDate);
		System.out.println("nowTime："+nowTime);
		System.out.println("createDateTime："+createDateTime);
		long minus = nowTime.getTime() - createDateTime.getTime();
		if(minus == 0) { //反饋時間間隔不超過1天
			return 0;
		} else {		//反饋時間間隔超過1天
			return 1;
		}
	}

}
