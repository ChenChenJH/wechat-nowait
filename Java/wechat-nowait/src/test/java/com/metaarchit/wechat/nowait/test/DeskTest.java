package com.metaarchit.wechat.nowait.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.metaarchit.wechat.nowait.dao.DeskDao;
import com.metaarchit.wechat.nowait.model.Desk;

public class DeskTest extends BaseTest {

	@Resource
	private DeskDao deskDao;
	
	@Test
	public void test() {
		List<Desk> desks = deskDao.selectAllDesk();
		for (Desk desk : desks) {
			System.out.println(desk.toString());
		}
	}
}