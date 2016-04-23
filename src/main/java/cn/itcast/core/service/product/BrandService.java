package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.query.BrandQuery;

/** 
 * 
 * 品牌接口
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午5:28:52 
 * 
 */
public interface BrandService {
	/**
	 * 查询所有品牌
	 * 编辑人:Hapi
	 * 2016-4-23
	 * 下午11:55:01
	 * @param brandQuery
	 * @return
	 * 返回值类型: List<Brand>
	 */
	public List<Brand> getBrandList(BrandQuery brandQuery);
	/**
	 * 分页查询品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:01:05
	 * 返回值类型: Pagination
	 */
	public Pagination getBrandListWithPage(Brand brand);
	/**
	 * 添加品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:01:42
	 * 返回值类型: void
	 */
	public void addBrand(Brand brand);
	
	/**
	 * 删除品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:50:22
	 * 返回值类型: void
	 */
	public void deleteBrandByKey(Integer id);
	/**
	 * 批量删除
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午12:28:39
	 * 返回值类型: void
	 */
	public void deleteBrandByKeys(Integer[] id);
	/**
	 * 更新
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午12:28:54
	 * 返回值类型: void
	 */
	public void updateBrandByKey(Brand brand);
	/**
	 * 获取一个品牌类
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午6:44:28
	 * 返回值类型: void
	 */
	public Brand getBrandByKey(Integer id);
	
}
