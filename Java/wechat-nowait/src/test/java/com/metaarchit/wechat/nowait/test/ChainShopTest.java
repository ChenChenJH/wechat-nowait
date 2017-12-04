package com.metaarchit.wechat.nowait.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.metaarchit.wechat.nowait.dao.ChainShopDao;
import com.metaarchit.wechat.nowait.model.ChainShop;

public class ChainShopTest extends BaseTest {

	@Resource
	private ChainShopDao chainShopDao;
	
	@Test
	public void test() {
		List<ChainShop> chainShops = chainShopDao.selectAllChainShop();
		for (ChainShop chainShop : chainShops) {
			System.out.println(chainShop.toString());
		}
	}
}
