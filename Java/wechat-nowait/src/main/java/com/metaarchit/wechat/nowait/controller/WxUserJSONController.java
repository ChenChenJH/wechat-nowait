package com.metaarchit.wechat.nowait.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.metaarchit.wechat.nowait.model.WxUser;
import com.metaarchit.wechat.nowait.service.WxUserService;
import com.metaarchit.wechat.nowait.util.CommonUtil;
import com.metaarchit.wechat.nowait.util.HttpsUtil;
/**
 * 微信用戶信息JSON控制類
 * @author HuangKailie
 * @createTime 2017-11-17 11:25:01
 */
@Controller
@RequestMapping(value="/wxUser")
public class WxUserJSONController {

	@Resource
	private WxUserService wxUserService;
	
	@Value("#{configProperties['wechat.appid']}")
	private String appid;
	
	@Value("#{configProperties['wechat.secret']}")
	private String secret;
	
	/**
	 * 用戶登錄獲取openId以及已綁定的手機號
	 * @param code 微信用戶code
	 * @return String 微信用戶openId以及已綁定的手機號
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public @ResponseBody String login(@RequestParam String code) throws Exception {
		String phone = null;	// 用戶已綁定的手機號
		// 發起方羅請求，通過appid，secret以及code獲取微信用戶數據
		String json = HttpsUtil.getSession_keyAndOpenID(appid, secret, code);
		JSONObject jsonObject = JSONObject.parseObject(json);
		String openId = jsonObject.getString("openid");	// 微信用戶openId
		System.out.println("openId为：" + openId);
		// 通過用戶openId獲取用戶綁定的手機號
		phone = wxUserService.getPhoneByOpenId(openId);
		// 通過用戶openId獲取用戶id號
		int id = wxUserService.getWxUserId(openId);
		// 創建JSONObject對象
		JSONObject result = new JSONObject();
		// 將openId，phone以及id添加到JSONOjbect對象中
		result.put("openId", openId);
		result.put("phone", phone);
		result.put("wxUserId", id);
		return result.toJSONString();
	}
	
	/**
	 * 獲取短信驗證碼（非正式短信接口）
	 * @param session HttpSession
	 * @param phone 手機號
	 * @return String 若手機號沒有被其他用戶綁定則返回驗證碼，手機號已被綁定則返回錯誤提示信息
	 */
	@RequestMapping("/getmsgCode")
	public @ResponseBody String getmsgCode(@RequestParam String phone) {
		System.out.println("手機號：" + phone);
		if (wxUserService.containsPhone(phone)) {	// 判斷手機號是否存在數據庫表中
			System.out.println("手機號已被綁定！");
			return "error-msg：手機號已被綁定！";
		}
		// 短信驗證碼，6位數
		int msgCode = CommonUtil.getmsgCode();
		return Integer.toString(msgCode);
	}

	/**
	 * 保存用戶信息
	 * @param phone 用戶手機號
	 * @param openid 微信用戶openId
	 * @return String
	 */
	@RequestMapping("/saveUserInfo")
	public @ResponseBody int saveUserInfo(@RequestParam String phone, @RequestParam String openid) {
		System.out.println("手机号：" + phone + "\nopenID：" + openid); 
		WxUser wxUser = new WxUser(openid, phone);
		int i = wxUserService.saveWxUser(wxUser);
		if (i > 0) return i;
		return -1;
	}
}
