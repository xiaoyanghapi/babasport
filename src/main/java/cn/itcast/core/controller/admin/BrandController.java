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
	 * 跳转图片添加页面
	 * 编辑人:yjj
	 * 2016年4月21日
	 * 下午3:58:48
	 * 返回值类型: String
	 */
	@RequestMapping(value ="/brand/toAdd.do")
	public String toAdd() throws Exception{
		return "brand/add";
	}
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
		//装分页参数
		StringBuilder params = new StringBuilder();
		
		Brand brand = new Brand();
			//判断传进来的值是否为null还要判断是否为空串isNotBlank空串形式两种1""2"   "  isNotEmpty
		if(StringUtils.isNotBlank(name)){
			params.append("name=").append(name);
			brand.setName(name);
		}
		if(null!=isDisplay){
			params.append("&isDisplay=").append(isDisplay);
			brand.setIsDisplay(isDisplay);
		}else{
			params.append("&isDisplay=").append(1);
			brand.setIsDisplay(1);
		}
		
		//如果页号为空或者小于一则赋值为1Pagination.cpn(pageNo)
		//设置页数 设置每页大小时要先设置页数
		brand.setPageSize(5);
		//输入页号
		brand.setPageNo(Pagination.cpn(pageNo));
		//分页对象
		Pagination pagination = brandService.getBrandListWithPage(brand);//request.setAttribute返回
		
		/**
		 * 分页展示  /brand/list.do?name = ""& isDisplay = ""& pageNo = 2
		 */
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		
		
		model.addAttribute("pagination",pagination);
		model.addAttribute("name",name);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("isDisplay",brand.getIsDisplay());
		return "brand/list";
	}
	/**
	 * 添加品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:44:46
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/brand/add.do")
	public String addBrand(Brand brand,ModelMap model){
		
		brandService.addBrand(brand);
		model.addAttribute("isDisplay",brand.getIsDisplay());
		//重定向
		return "redirect:/brand/list.do";
	}
	/**
	 * 删除品牌
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 上午9:45:57
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/brand/delete.do")
	public String deleteBrand(Integer id,String name,Integer isDisplay,ModelMap model,Integer pageNo){
		
		brandService.deleteBrandByKey(id);
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name",name);
		}
		if(null!=isDisplay){
			model.addAttribute("isDisplay",isDisplay);
		}
		model.addAttribute("pageNo",pageNo);
		return "redirect:/brand/list.do";
	}
	/**
	 * 批量删除
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午5:32:59
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/brand/deletes.do")
	public String deletes(Integer[] ids,String name,Integer isDisplay,ModelMap model){
		
		brandService.deleteBrandByKeys(ids);
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name",name);
		}
		if(null!=isDisplay){
			model.addAttribute("isDisplay",isDisplay);
		}
//		model.addAttribute("pageNo",pageNo);
		return "redirect:/brand/list.do";
	}
	/**
	 * 去修改页面
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午6:53:47
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(ModelMap model,Integer id,String name,Integer pageNo){
		Brand brand = brandService.getBrandByKey(id);
		brand.setPageNo(pageNo);
		model.addAttribute("brand", brand);
		model.addAttribute("name", name);
		return "/brand/editor";
	}
	/**
	 * 修改
	 * 编辑人:yjj
	 * 2016年4月22日
	 * 下午6:53:47
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/brand/edit.do")
	public String edit(ModelMap model,Brand brand,String name,Integer pageNo){
		brandService.updateBrandByKey(brand);
		model.addAttribute("name", name);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/brand/list.do";
	}
}
