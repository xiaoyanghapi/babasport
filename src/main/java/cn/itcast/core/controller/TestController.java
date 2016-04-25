package cn.itcast.core.controller;

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
public class TestController{
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
