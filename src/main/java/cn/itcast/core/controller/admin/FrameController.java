package cn.itcast.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午4:28:58 
 * 
 */
@Controller
@RequestMapping(value ="/control")
public class FrameController {
	/**
	 * 展示跳转页面没有业务逻辑
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午4:31:56
	 * @return
	 * @throws Exception
	 * 返回值类型: String
	 */
	@RequestMapping(value ="/frame/product_left.do")
	public String product_left() throws Exception{
		return "frame/product_left";
	}
	@RequestMapping(value ="/frame/product_main.do")
	public String product_main() throws Exception{
		return "frame/product_main";
	}
	@RequestMapping(value ="/frame/order_main.do")
	public String order_main() throws Exception{
		return "frame/order_main";
	}
	@RequestMapping(value ="/frame/order_left.do")
	public String order_left() throws Exception{
		return "frame/order_left";
	}
	
	
	
}
