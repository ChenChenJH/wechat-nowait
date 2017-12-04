package com.metaarchit.wechat.nowait.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metaarchit.wechat.nowait.model.FeedBack;
import com.metaarchit.wechat.nowait.service.FeedBackService;
import com.metaarchit.wechat.nowait.util.CommonUtil;

/**
 * 反饋信息JSON控制類
 * @author ZhengHuanBin
 * @createTime 2017-11-20 16:52:39
 */
@Controller
@RequestMapping(value="/feedBack")
public class FeedBackJSONController {
	
	@Resource
	private FeedBackService feedBackService;
	
	/**
	 * 保存反饋信息
	 * @param wxuserId 微信用戶Id
	 * @param info 反饋內容
	 * @return String
	 */
	@RequestMapping("/saveFeedBackInfo")
	public @ResponseBody String saveUserInfo(@RequestParam Integer wxuserId, @RequestParam String info) {
		info = CommonUtil.encodeStr(info);
		System.out.println("微信用戶Id：" + wxuserId + "\n反饋內容：" + info); 
		FeedBack feedBack = new FeedBack(wxuserId, info);
		feedBackService.saveFeedBack(feedBack);
		return "success";
	}
}
