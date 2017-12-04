package com.metaarchit.wechat.nowait.test;

import org.junit.Test;

import com.metaarchit.wechat.nowait.util.CommonUtil;

public class CommonUtilTest extends BaseTest {

	@Test
	public void getmsgCode() {
		for (int i = 0; i < 55500000; i++) {
			int a = CommonUtil.getmsgCode();
			if (a == 100000) {
				System.out.println("OK :" + a);
			}
			if (a == 999999) {
				System.out.println("OK :" + a);
			}
			if (a < 100000) {
				System.out.println("GG");
			}
			if (a > 999999) {
				System.out.println("GG");
			}
		}
	}
}
