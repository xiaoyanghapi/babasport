package cn.itcast.core.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.service.product.BrandService;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午4:28:58 
 * 
 */
@Controller
public class BrandController {
	
	
	@Autowired
	private BrandService brandService;
	/**
	 * 返回品牌列表页面
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午4:31:56
	 * @return
	 * @throws Exception
	 * 返回值类型: String
	 */
	@RequestMapping(value ="/brand/list.do")
	public String brandList(String name,Integer isDisplay,Integer pageNo,ModelMap model) throws Exception{
		Brand brand = new Brand();
			//判断传进来的值是否为null还要判断是否为空串isNotBlank空串形式两种1""2"   "  isNotEmpty
		if(StringUtils.isNotBlank(brand.getName())){
			brand.setName(name);
		}
		brand.setIsDisplay(isDisplay);
		//如果页号为空或者小于一则赋值为1Pagination.cpn(pageNo)
		//输入页数
		brand.setPageNo(Pagination.cpn(pageNo));
		//分页对象
		Pagination pagination = brandService.getBrandListWithPage(brand);//request.setAttribute返回
		
		model.addAttribute("pagination",pagination);
		return "brand/list";
	}
	
	
	
}
