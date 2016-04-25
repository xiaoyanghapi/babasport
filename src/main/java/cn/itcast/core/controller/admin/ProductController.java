package cn.itcast.core.controller.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.query.product.BrandQuery;
import cn.itcast.core.query.product.ProductQuery;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.ProductService;

/** 
 * 
 * 后台商品管理
 * 商品列表
 * 商品添加
 * 商品上架
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月25日 上午10:47:22 
 * 
 */
@Controller
public class ProductController {
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product/list.do")
	public String list(Integer pageNo,String name,Integer isShow,Integer brandId,ModelMap model){
//		查询的条件
		StringBuilder params = new StringBuilder();
		BrandQuery brandQuery = new BrandQuery();
//		只查询两个字段
		brandQuery.setFields("id,name");
//		加载不显示的
		brandQuery.setIsDisplay(1);
		//加载品牌
		List<Brand> brands = brandService.getBrandList(brandQuery);
//		返回到页面
		model.addAttribute("brands",brands);
		
		ProductQuery productQuery = new ProductQuery();
		//判断条件是否为空
		if(StringUtils.isNotBlank(name)){
			productQuery.setName(name);
//			模糊查询
			productQuery.setNameLike(true);
			params.append("&name=").append(name);
//			回写name查询条件
			model.addAttribute("name", name);
		}
//		是否上架
		if(null != isShow){
			productQuery.setIsShow(isShow);
		}else{
			productQuery.setIsShow(0);
		}
		params.append("&isShow=").append(productQuery.getIsShow());
//		回写isShow查询条件
		model.addAttribute("isShow", productQuery.getIsShow());
//		单个品牌
		if(null != brandId){
			productQuery.setBrandId(brandId);
			params.append("&brandId=").append(brandId);
//			回写brandId查询条件
			model.addAttribute("brandId",brandId);
		}
//		设置初始页
		productQuery.setPageNo(Pagination.cpn(pageNo));
//		每页数
		productQuery.setPageSize(5);
		
		Pagination pagination = productService.getProductListWithPage(productQuery);
		
		String url = "/product/list.do";
		
		pagination.pageView(url, params.toString());
		//加载商品数据信息
		model.addAttribute("pagination", pagination);
		
		
		return "product/list";
	}
	
}
