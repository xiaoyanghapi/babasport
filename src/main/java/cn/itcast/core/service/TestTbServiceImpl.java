package cn.itcast.core.service;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.bean.TestTable;
import cn.itcast.core.dao.TestTbDao;

/** 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:11:30 
 * 类说明 
 */
@Service
@Transactional
public class TestTbServiceImpl implements TestTbService {
	@Resource
	private TestTbDao testTbDao;
	
	@Override
	public void addTestTb(TestTable testTable) {
		testTbDao.addTestTb(testTable);
//		throw new RuntimeException();
	}

}
