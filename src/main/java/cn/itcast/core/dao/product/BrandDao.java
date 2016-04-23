package cn.itcast.core.dao.product;

import java.util.List;

import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.query.BrandQuery;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午5:25:24 
 * 
 */
public interface BrandDao {
	/**
	 * 
	 * 编辑人:yjj
	 * 2016年4月23日
	 * 下午3:30:52
	 * 返回值类型: List<Brand>
	 */
	public List<Brand> getBrandList(BrandQuery brandQuery);
	/**
	 * 返回集合分页 limit 0,5集合
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
	/**
	 * 插入品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:00:26
	 * 返回值类型: void
	 */
	public void addBrand(Brand brand);
	/**
	 * 删除品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午10:17:50
	 * 返回值类型: void
	 */
	public void deleteBrandByKey(Integer id);
	/**
	 * 批量删除品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午12:11:16
	 * 返回值类型: void
	 */
	public void deleteBrandByKeys(Integer[] ids);
	/**
	 * 修改品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午12:12:07
	 * 返回值类型: void
	 */
	public void updateBrandByKey(Brand brand);
	/**
	 * 查询一个brand品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午6:43:44
	 * 返回值类型: void
	 * @return 
	 */
	public Brand getBrandByKey(Integer id);
}
