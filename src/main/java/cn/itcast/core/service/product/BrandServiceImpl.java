package cn.itcast.core.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.dao.product.BrandDao;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午5:30:45 
 * 
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandDao brandDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public Pagination getBrandListWithPage(Brand brand) {
		//参数传递  1.起始页 startRow = (pageNo - 1) * pageSize,2.每页条数,3总记录数
		Pagination pagination = new Pagination(brand.getPageNo(),brand.getPageSize(),brandDao.getBrandCount(brand));
		//数据集合brand集合
		pagination.setList(brandDao.getBrandListWithPage(brand));
		
		return pagination;
	}

}
