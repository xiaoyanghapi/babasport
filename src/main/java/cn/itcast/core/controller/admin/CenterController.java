package cn.itcast.core.controller.admin;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月20日 下午1:48:16 
 * 
 */
@Controller
@RequestMapping(value = "/control")
public class CenterController{
	
	/**
	 * 初始页面
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午4:15:46
	 * @param name
	 * @param birthday
	 * @throws Exception
	 * 返回值类型: void
	 */
	@RequestMapping(value="/top.do")
	public String top()  throws Exception{
		return "top";
	}
	
	/**
	 * 跳转头页面
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午4:22:10
	 * @param name
	 * @param birthday
	 * @return
	 * @throws Exception
	 * 返回值类型: String
	 */
	@RequestMapping(value="/main.do")
	public String main()  throws Exception{
		return "main";
	}
	/**
	 * 跳转身体页面
	 * 编辑人:yjj
	 * 2016年4月20日
	 * 下午4:22:10
	 * @param name
	 * @param birthday
	 * @return
	 * @throws Exception
	 * 返回值类型: String
	 */
	@RequestMapping(value="/index.do")
	public String index()  throws Exception{
		return "index";
	}
	@RequestMapping(value="/left.do")
	public String left()  throws Exception{
		return "left";
	}
	@RequestMapping(value="/right.do")
	public String right()  throws Exception{
		return "right";
	}
	@RequestMapping(value="/backspringmvc.do")
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
