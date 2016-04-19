package cn.itcast;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.core.bean.TestTable;
import cn.itcast.core.service.TestTbService;

/** 
 * 
 * 测试
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:26:14 
 * @ContextConfiguration(locations = "classpath:application-context.xml,后边可以跟很多")
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestTestTb {
	@Autowired
	private TestTbService testTbService;
	@Test
	public void testAdd() throws Exception {
		TestTable testTable = new TestTable();
		testTable.setName("杨景江");
		testTable.setBirthday(new Date());
		testTbService.addTestTb(testTable);
	}
}
