package cn.itcast.comment.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/** 
 * 
 * 返回各种格式
 * json
 * xml
 * text
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月21日 下午5:48:38 
 * 
 */
public class ResponseUtils {

	//发送内容 "application/json;charset=UTF-8"
	/**
	 * 发送内容的
	 * 编辑人:yjj
	 * 2016年4月21日
	 * 下午5:54:30
	 * 返回值类型: void
	 */
	public static void render(HttpServletResponse response,String contentType,String text){
		response.setContentType(contentType);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送JSON
	 * 编辑人:yjj
	 * 2016年4月21日
	 * 下午5:55:56
	 * 返回值类型: void
	 */
	public static void renderJson(HttpServletResponse response,String text){
		render(response, "application/json;charset=UTF-8", text);
	}
	/**
	 * 发送XML
	 * 编辑人:yjj
	 * 2016年4月21日
	 * 下午5:55:56
	 * 返回值类型: void
	 */
	public static void renderXml(HttpServletResponse response,String text){
		render(response, "text/xml;charset=UTF-8", text);
	}
	/**
	 * 发送TEXT
	 * 编辑人:yjj
	 * 2016年4月21日
	 * 下午5:55:56
	 * 返回值类型: void
	 */
	public static void renderTest(HttpServletResponse response,String text){
		render(response, "text/plain;charset=UTF-8", text);
	}
}
