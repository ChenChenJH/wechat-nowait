package com.metaarchit.wechat.nowait.service;

import java.util.List;

import com.metaarchit.wechat.nowait.model.WxUser;
/**
 * 微信用戶信息業務邏輯處理接口
 * @author HuangKailie
 * @createTime 2017-11-16 15:16:05
 */
public interface WxUserService {

	/**
	 * 根據用戶openId獲取用戶Id
	 * @param openId 微信用戶openId
	 * @return 用戶Id
	 */
	int getWxUserId(String openId);
	
	/**
	 * 微信用戶登錄
	 * @param appid 小程序ID
	 * @param secret 小程序密鑰
	 * @param code 登錄憑證
	 * @return String JSON格式數據，包括openid以及session_key
	 */
	String login(String appid, String secret, String code) throws Exception;
	
	/**
	 * 獲取所有微信用戶
	 * @return List<WxUser> 微信用戶信息列表
	 */
	List<WxUser> listAllWxUser();
	
	/**
	 * 添加新微信用戶
	 * @param wxUser WxUser類對象
	 * @return int 用戶id號
	 */
	int saveWxUser(WxUser wxUser);
	
	/**
	 * 判斷手機號是否存在
	 * @param phone 手機號
	 * @return boolean 存在返回true，不存在返回false
	 */
	boolean containsPhone(String phone);
	
	/**
	 * 根據用戶openId獲取用戶已綁定手機號
	 * @param openid 用戶openId
	 * @return String 用戶所綁定的手機號
	 */
	String getPhoneByOpenId(String openid);
}
