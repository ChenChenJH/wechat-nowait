package com.metaarchit.wechat.nowait.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metaarchit.wechat.nowait.dao.WxUserDao;
import com.metaarchit.wechat.nowait.model.WxUser;
import com.metaarchit.wechat.nowait.service.WxUserService;
import com.metaarchit.wechat.nowait.util.HttpsUtil;
/**
 * 微信用戶業務邏輯處理接口實現類
 * @author HuangKailie
 * @createTime 2017-11-16 15:21:54
 */
@Service
public class WxUserServiceImpl implements WxUserService {
	
	@Resource
	private WxUserDao wxUserDao;
	
	/**
	 * 微信用戶登錄
	 * @param appid 小程序ID
	 * @param secret 小程序密鑰
	 * @param code 登錄憑證
	 * @return String JSON格式數據，包括openid以及session_key
	 */
	public String login(String appid, String secret, String code) throws Exception {
		String json = HttpsUtil.getSession_keyAndOpenID(appid, secret, code);
		System.out.println(json);
		return null;
	}
	
	/**
	 * 獲取所有微信用戶
	 * @return List<WxUser> 微信用戶信息列表
	 */
	public List<WxUser> listAllWxUser() {
		List<WxUser> wxUsers = wxUserDao.selectAllWxUser();
		if (wxUsers.size() <= 0) {
			System.out.println("數據庫表為空！");
		}
		return wxUsers;
	}
	
	/**
	 * 添加新微信用戶
	 * @param wxUser WxUser類對象
	 * @return int 用戶id號
	 */
	public int saveWxUser(WxUser wxUser) {
		wxUserDao.insertWxUser(wxUser);
		int i = wxUser.getId();
		if (i > 0) {
			System.out.println("添加成功，微信用戶Id號為：" + i);
		} else {
			System.out.println("添加失敗！");
		}
		return i;
	}

	/**
	 * 判斷手機號是否存在
	 * @param phone 手機號
	 * @return boolean 存在返回true，不存在返回false
	 */
	public boolean containsPhone(String phone) {
		int count = wxUserDao.selectCountByPhone(phone);
		if (count > 0)
			return true;
		return false;
	}

	/**
	 * 根據用戶openId獲取用戶已綁定手機號
	 * @param openid 用戶openId
	 * @return String 用戶所綁定的手機號
	 */
	public String getPhoneByOpenId(String openid) {
		String phone = null;
		phone = wxUserDao.selectPhoneByOpenId(openid);
		return phone;
	}

	/**
	 * 根據用戶openId獲取用戶Id
	 * @param openId 微信用戶openId
	 * @return 用戶Id
	 */
	public int getWxUserId(String openId) {
		int id = 0;
		try {
			id = wxUserDao.selectIdByOpenId(openId);
		} catch (Exception e) {
			System.out.println("用戶未綁定手機號，因此數據庫未錄入用戶信息！");
		}
		return id;
	}

}
