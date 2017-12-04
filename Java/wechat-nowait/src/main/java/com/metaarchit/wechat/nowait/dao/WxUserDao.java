package com.metaarchit.wechat.nowait.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.metaarchit.wechat.nowait.model.WxUser;
/**
 * 微信用戶信息DAO事務處理類
 * @author HuangKailie
 * @createTime 2017-11-16 15:00:44
 */
@Repository
public interface WxUserDao {

	/**
	 * 根據用戶openId獲取用戶Id
	 * @param openId 微信用戶openId
	 * @return 用戶ID
	 */
	int selectIdByOpenId(String openId);
	
	/**
	 * 查詢所有微信用戶信息
	 * @return List<WxUser> 微信用戶信息列表
	 */
	List<WxUser> selectAllWxUser();
	
	/**
	 * 添加新微信用戶
	 * @param wxUser WxUser類對象
	 * @return int 成功插入數據庫的記錄數
	 */
	int insertWxUser(WxUser wxUser);
	
	/**
	 * 查詢手機號是否已經存在
	 * @param phone 手機號
	 * @return int 出現次數
	 */
	int selectCountByPhone(@Param("phone") String phone);
	
	/**
	 * 根據用戶openId獲取用戶手機號
	 * @param openid 微信用戶openId
	 * @return String 用戶手機號
	 */
	String selectPhoneByOpenId(@Param("openid") String openid);
}
