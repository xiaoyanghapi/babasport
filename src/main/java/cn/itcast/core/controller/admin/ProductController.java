package cn.itcast.core.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Feature;
import cn.itcast.core.bean.product.Img;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.bean.product.Type;
import cn.itcast.core.query.product.BrandQuery;
import cn.itcast.core.query.product.ColorQuery;
import cn.itcast.core.query.product.FeatureQuery;
import cn.itcast.core.query.product.ProductQuery;
import cn.itcast.core.query.product.TypeQuery;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.ColorService;
import cn.itcast.core.service.product.FeatureService;
import cn.itcast.core.service.product.ProductService;
import cn.itcast.core.service.product.SkuService;
import cn.itcast.core.service.product.TypeService;
import cn.itcast.core.service.staticpage.StaticPageService;

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
	@Autowired
	private TypeService typeService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private StaticPageService staticPageService;

	/**
	 * 返回商品上架
	 * 编辑人:yjj
	 * 2016年4月28日
	 * 下午2:15:13
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/isShow.do")
	public String isShow(Integer[] ids,Integer pageNo,String name,Integer brandId,Integer isShow,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Product product = new Product();
		product.setIsShow(1);
		//上架
		if(null != ids && ids.length > 0){
			for (Integer id : ids) {
				product.setId(id);
//				修改上级状态
				productService.updateProductByKey(product);
				/**
				 * TODO 静态化
				 */
				Map<String, Object> map  = new HashMap<String, Object>();
				Product prod = productService.getProductByKey(id);
				map.put("product", prod);
				/**
				 * 加载sku和颜色
				 */
				List<Sku> skus = skuService.getStock(id);
				List<Color> colors = new ArrayList<Color>();
				
				for (Sku sku : skus) {
					if(!colors.contains(sku.getColor())){
						colors.add(sku.getColor());
					}
					
				}
				map.put("colors", colors);
				map.put("skus", skus);
				staticPageService.produceStaticPage(map,("/product/"+id+".html"), "productDetail.html");
			}
		}
		
		/**
		 * 判断页号为空
		 */
		if(null != pageNo){
			model.addAttribute("pageNo", pageNo);
		}
		/**
		 * 判断查询名字
		 */
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name", name);
		}
		/**
		 * 判断品牌id
		 */
		if(null != brandId){
			model.addAttribute("brandId", brandId);
		}
		/**
		 * 判断是否上架
		 */
		if(null != isShow){
			model.addAttribute("isShow", isShow);
		}	
		
		
		return "redirect:/product/list.do";
	}
	
	/**
	 * 返回商品下架
	 * 编辑人:yjj
	 * 2016年4月28日
	 * 下午2:15:13
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/isNotShow.do")
	public String isNotShow(Integer[] ids,Integer pageNo,String name,Integer brandId,Integer isShow,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Product product = new Product();
		product.setIsShow(0);
		//上架
		if(null != ids && ids.length > 0){
			for (Integer id : ids) {
				product.setId(id);
//				修改上级状态
				productService.updateProductByKey(product);
				/**
				 * TODO 下架
				 */
				File file = new File(request.getServletContext().getRealPath("/html")+("/product/"+id+".html"));
				if(file.exists()){
					file.delete();
				}
			}
		}
		
		/**
		 * 判断页号为空
		 */
		if(null != pageNo){
			model.addAttribute("pageNo", pageNo);
		}
		/**
		 * 判断查询名字
		 */
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name", name);
		}
		/**
		 * 判断品牌id
		 */
		if(null != brandId){
			model.addAttribute("brandId", brandId);
		}
		/**
		 * 判断是否上架
		 */
		if(null != isShow){
			model.addAttribute("isShow", isShow);
		}	
		
		
		return "redirect:/product/list.do";
	}
	
	
	/**
	 * 去添加页面
	 * 编辑人:yjj
	 * 2016年4月27日
	 * 上午9:29:09
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/toAdd.do")
	public String toAdd(ModelMap model){
		/**
		 * 所有的类型
		 */
		TypeQuery typeQuery = new TypeQuery();
		typeQuery.setFields("id,name");
		typeQuery.setIsDisplay(1);
		typeQuery.setParentId(0);
		List<Type> types = typeService.getTypeList(typeQuery);
		model.addAttribute("types", types);
		
		/**
		 * 所有的品牌
		 */
		BrandQuery brandQuery = new BrandQuery();
//		只查询两个字段
		brandQuery.setFields("id,name");
//		加载不显示的
		brandQuery.setIsDisplay(1);
		//加载品牌
		List<Brand> brands = brandService.getBrandList(brandQuery);
//		返回到页面
		model.addAttribute("brands",brands);
		
		/**
		 * 商品属性
		 */
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		featureQuery.setIsDel(1);
		List<Feature> features = featureService.getFeatureList(featureQuery);
		model.addAttribute("features", features);
		
		/**
		 * 颜色大全
		 */
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		colorQuery.setParentId(0);
		List<Color> colors = colorService.getColorList(colorQuery);
		model.addAttribute("colors", colors);
		
		return "product/add";
	}
	/**
	 * 添加商品返回到商品list
	 * 编辑人:yjj
	 * 2016年4月27日
	 * 上午11:46:41
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/product/add.do")
	public String add(ModelMap model,Product product,Img img){
		//商品表 图片表 sku表三张表 可以添加包装域
		product.setImg(img);
		
		productService.addProduct(product);
		
		return "redirect:/product/list.do";
	}
	
	/**
	 * 商品的列表页
	 * 编辑人:yjj
	 * 2016年4月28日
	 * 下午2:13:59
	 * 返回值类型: String
	 */
	@RequestMapping(value="/product/list.do")
	public String list(Integer pageNo,String name,Integer isShow,Integer brandId,ModelMap model){
//		查询的条件
		StringBuilder params = new StringBuilder();
		/**
		 * 返回品牌类型
		 */
		BrandQuery brandQuery = new BrandQuery();
//		只查询两个字段
		brandQuery.setFields("id,name");
//		加载不显示的
		brandQuery.setIsDisplay(1);
		//加载品牌
		List<Brand> brands = brandService.getBrandList(brandQuery);
//		返回到页面
		model.addAttribute("brands",brands);
		/**
		 * 分页返回商品		
		 */
		ProductQuery productQuery = new ProductQuery();
		//判断条件是否为空
		if(StringUtils.isNotBlank(name)){
			productQuery.setName(name);
//			模糊查询
			productQuery.setNameLike(true);
			params.append("&name=").append(name);
			/**
			 * 回写name查询条件
			 */
			model.addAttribute("name", name);
		}
//		是否上架
		if(null != isShow){
			productQuery.setIsShow(isShow);
		}else{
			productQuery.setIsShow(0);
		}
		params.append("&isShow=").append(productQuery.getIsShow());
		/**
		 * 回写isShow查询条件
		 */
		model.addAttribute("isShow", productQuery.getIsShow());
//		单个品牌
		if(null != brandId){
			productQuery.setBrandId(brandId);
			params.append("&brandId=").append(brandId);
			/**
			 * 回写brandId查询条件
			 */
			model.addAttribute("brandId",brandId);
		}
//		设置初始页
		productQuery.setPageNo(Pagination.cpn(pageNo));
//		每页数
		productQuery.setPageSize(5);
//		按照id倒序排列
		productQuery.orderbyId(false);
		Pagination pagination = productService.getProductListWithPage(productQuery);
		
		String url = "/product/list.do";
		
		pagination.pageView(url, params.toString());
		/**
		 * 加载商品数据信息
		 */
		model.addAttribute("pagination", pagination);
		
		
		return "product/list";
	}
	
}
