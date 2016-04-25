package cn.itcast;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.comment.junit.SpringJunitTest;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.query.product.BrandQuery;
import cn.itcast.core.service.product.BrandService;

/** 
 * 
 * 测试
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:26:14 
 * @ContextConfiguration(locations = "classpath:application-context.xml,后边可以跟很多")
 */
public class TestBrand extends SpringJunitTest{
	@Autowired
	private BrandService brandService;
	@Test
	public void testGet() throws Exception {
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.orderbyId(false);
//		brandQuery.setFields("id");
//		brandQuery.setNameLike(true);
		brandQuery.setName("金乐乐");
		brandQuery.setPageNo(2);
		brandQuery.setPageSize(3);
		List<Brand> brandList = brandService.getBrandList(brandQuery);
		
/*		Brand brand = new Brand();
		brand.setName("金乐乐");
		List<Brand> brandLists = brandService.getBrandLists(brand);*/
		for (Brand brands : brandList) {
			System.out.println(brands);
		}
		
		
	}
}
