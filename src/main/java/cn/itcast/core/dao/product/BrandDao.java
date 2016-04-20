package cn.itcast.core.dao.product;

import java.util.List;

import cn.itcast.core.bean.product.Brand;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午5:25:24 
 * 
 */
public interface BrandDao {
	/**
	 * 返回集合
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午5:26:18
	 * 返回值类型: List<Brand>
	 */
	public List<Brand> getBrandListWithPage(Brand brand);
	/**
	 * 查询总记录数
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午5:42:39
	 * 返回值类型: int
	 */
	public int getBrandCount(Brand brand);
}
