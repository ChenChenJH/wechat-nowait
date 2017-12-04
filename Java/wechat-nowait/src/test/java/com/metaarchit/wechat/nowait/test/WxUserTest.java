package com.metaarchit.wechat.nowait.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.metaarchit.wechat.nowait.dao.WxUserDao;
import com.metaarchit.wechat.nowait.service.WxUserService;

public class WxUserTest extends BaseTest {

	@Resource
	private WxUserService wxUserService;

	@Resource
	private WxUserDao wxUserDao;
	
	@Test
	public void test() throws Exception {
		String phone = null;
		phone = wxUserService.getPhoneByOpenId("oyYPs0EGJw3rK0vVXXZ-dWgHxv-s");
	}
}
