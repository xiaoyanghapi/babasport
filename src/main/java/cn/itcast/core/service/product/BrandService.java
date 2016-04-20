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
	public Pagination getBrandListWithPage(Brand brand);

}
