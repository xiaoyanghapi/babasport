package cn.itcast.core.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.comment.web.ResponseUtils;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.query.product.SkuQuery;
import cn.itcast.core.service.product.SkuService;

/** 
 * 
 * 库存管理
 * 修改库存
 * 跳转到库存管理页面
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月9日 下午2:40:01 
 * 
 */
@Controller
public class SkuController {
	@Autowired
	private SkuService skuService;
	
	/**
	 * sku添加价格等数据
	 * 编辑人:yjj
	 * 2016年5月9日
	 * 下午3:36:43
	 * 返回值类型: void
	 */
	@RequestMapping(value = "/sku/update.do")
	public void update(Sku sku,ModelMap model,HttpServletResponse response){
//		更新sku数据
		skuService.updateSkuByKey(sku);
//		回显
		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("message", "更新成功");
		ResponseUtils.renderJson(response, jsonObject.toString());
	}
	/**
	 * 去库存管理页面
	 * pno商品编号
	 * productId商品id
	 * 编辑人:yjj
	 * 2016年5月9日
	 * 下午2:36:58
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/sku/list.do")
	public String list(ModelMap model,Integer productId,String pno){
		model.addAttribute("pno",pno);
		//加载sku
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setProductId(productId);
		List<Sku> skus = skuService.getSkuList(skuQuery);
		
		model.addAttribute("skus", skus);
		
		return "sku/list";
	}
}
