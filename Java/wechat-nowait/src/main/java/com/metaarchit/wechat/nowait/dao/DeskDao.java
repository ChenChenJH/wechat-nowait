package com.metaarchit.wechat.nowait.dao;

import java.util.List;

import com.metaarchit.wechat.nowait.model.Desk;
/**
 * 座位信息DAO事務處理接口
 * @author HuangKailie
 * @createTime 2017-11-23 16:28:26
 */
public interface DeskDao {
	
	/**
	 * 獲取所有座位信息
	 * @return List<Desk> 座位信息列表
	 */
	List<Desk> selectAllDesk();
	
	/**
	 * 根據餐廳Id獲取座位信息
	 * @param restId 餐廳Id號
	 * @return List<Desk> 座位信息列表
	 */
	List<Desk> selectDeskByRestId(int restId);
}
