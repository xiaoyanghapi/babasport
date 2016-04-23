package cn.itcast.core.dao;

import java.util.List;

import cn.itcast.core.bean.TestTable;

/** 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:10:16 
 * 类说明  mybatis自己处理
 */
public interface TestTbDao {
	public void addTestTb(TestTable testTable);
	public List<TestTable> testSelect();
}
