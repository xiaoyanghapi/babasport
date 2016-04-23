package cn.itcast;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.comment.junit.SpringJunitTest;
import cn.itcast.core.bean.TestTable;
import cn.itcast.core.service.TestTbService;

/** 
 * 
 * 测试
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:26:14 
 * @ContextConfiguration(locations = "classpath:application-context.xml,后边可以跟很多")
 */
public class TestTestTb extends SpringJunitTest{
	@Autowired
	private TestTbService testTbService;
/*	@Test
	public void testAdd() throws Exception {
		TestTable testTable = new TestTable();
		testTable.setName("aaaa");
		testTable.setBirthday(new Date());
		testTbService.addTestTb(testTable);
	}*/
	@Test
	public void testGet() throws Exception {
		List<TestTable> testSelect = testTbService.testSelect();
		for (TestTable testTable : testSelect) {
			System.out.println(testTable);
		}
	}
}
