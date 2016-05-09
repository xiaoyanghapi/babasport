package cn.itcast.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Feature;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.bean.product.Type;
import cn.itcast.core.query.product.BrandQuery;
import cn.itcast.core.query.product.FeatureQuery;
import cn.itcast.core.query.product.ProductQuery;
import cn.itcast.core.query.product.SkuQuery;
import cn.itcast.core.query.product.TypeQuery;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.FeatureService;
import cn.itcast.core.service.product.ProductService;
import cn.itcast.core.service.product.SkuService;
import cn.itcast.core.service.product.TypeService;

/** 
 * 
 * 约定-->由于要添加页面缓存Oscached
 * 拦截规则：类似于/product/*（前后台都会拦截）
 * 用/product/display/*拦截-->oscached
 * 前台的商品列表页面
 * 商品详情页面
 * 测试
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午1:48:16 
 * 
 */
@Controller
public class FrontProductController{
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private SkuService skuService;
	/**
	 * 商品详情页面
	 * 编辑人:yjj
	 * 2016年5月9日
	 * 下午5:37:33
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/detail.shtml")
	public String detail(ModelMap model,Integer productId){
//		返回商品页面
		Product product = productService.getProductByKey(productId);
		model.addAttribute("product", product);
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setProductId(productId);
		/**
		 * 加载sku和颜色
		 */
		List<Sku> skus = skuService.getSkuList(skuQuery);
		List<Color> colors = new ArrayList<Color>();
		for (Sku sku : skus) {
			if(colors.contains(sku.getColor())){
				colors.add(sku.getColor());
			}
		}
		model.addAttribute("colors", colors);
		model.addAttribute("skus", skus);
		return "product/productDetail";
	}
	/**
	 * 商品列表页面
	 * 编辑人:yjj
	 * 2016年5月5日
	 * 上午10:09:12
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/display/list.shtml")
	public String list(ModelMap model,Integer pageNo,Integer brandId,String brandName,Integer typeId,String typeName){
		
		/**
		 * 加载数据集
		 */
		//分页参数集
		StringBuilder params = new StringBuilder();
		
		//条件参数集合
		Map<String, String> query = new LinkedHashMap<String,String>();
		ProductQuery productQuery = new ProductQuery();
		//设置页号
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//设置每页数
		productQuery.setPageSize(Product.FRONT_PAGE_SIZE);
		//设置倒序排列
		productQuery.orderbyBrandId(false);
		
		Boolean flag = false;
		/**
		 * 品牌
		 */
		if(null != brandId){
			productQuery.setBrandId(brandId);
			model.addAttribute("brandName",brandName);
			model.addAttribute("brandId",brandId);
			query.put("品牌", brandName);
			params.append("brandId="+brandId).append("&brandName="+brandName);
			flag = true;
		}else{
			/**
			 * 所有的品牌
			 */
			BrandQuery brandQuery = new BrandQuery();
			//只查询两个字段
			brandQuery.setFields("id,name");
			//加载不显示的
			brandQuery.setIsDisplay(1);
			//加载品牌
			List<Brand> brands = brandService.getBrandList(brandQuery);
			//返回到页面
			model.addAttribute("brands",brands);
		}
		/**
		 * 类型
		 */
		if(null != typeId){
			productQuery.setTypeId(typeId);
			model.addAttribute("typeName", typeName);
			model.addAttribute("typeId", typeId);
			query.put("类型", typeName);
			params.append("&typeId="+typeId).append("&typeName="+typeName);
			flag = true;
		}else{
			/**
			 * 所有的类型
			 */
			TypeQuery typeQuery = new TypeQuery();
			typeQuery.setFields("id,name");
			typeQuery.setIsDisplay(1);
			typeQuery.setParentId(0);
			List<Type> types = typeService.getTypeList(typeQuery);
			model.addAttribute("types", types);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("query", query);
		
		/**
		 * 商品属性
		 */
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		featureQuery.setIsDel(1);
		List<Feature> features = featureService.getFeatureList(featureQuery);
		model.addAttribute("features", features);
		
		Pagination pagination = productService.getProductListWithPage(productQuery);
		
		//分页页面展示 
		//String params = "brandId=1&name=2014瑜伽服&pageNo=1"
		String url = "/product/display/list.shtml";
		pagination.pageView(url, params.toString());
		
		model.addAttribute("pagination", pagination);
		
		return "product/product";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/test/springmvc.do")
	public void controllerTest(String name,Date birthday)  throws Exception{
		System.out.println(name);
		System.out.println(birthday);
	}
	/**
	 * 局部转换日期格式   implements WebBindingInitializer
	 */
/*	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/
}
