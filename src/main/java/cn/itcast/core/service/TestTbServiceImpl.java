package cn.itcast.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.core.bean.TestTable;
import cn.itcast.core.dao.TestTbDao;

/** 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:11:30 
 * 类说明 
 */
@Service
public class TestTbServiceImpl implements TestTbService {
	@Resource
	private TestTbDao testTbDao;
	
	@Override
	public void addTestTb(TestTable testTable) {
		testTbDao.addTestTb(testTable);
	}

}