package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;

/** 
 * 
 * 品牌接口
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午5:28:52 
 * 
 */
public interface BrandService {
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
	public void updateBrandByKey(Integer id);
	
}
