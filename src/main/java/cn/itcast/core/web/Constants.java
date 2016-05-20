package cn.itcast.core.web;
/** 
 * 
 * 业务常量
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月22日 上午9:20:28 
 * 
 */
public interface Constants {
	/**
	 * 图片路径
	 */
	public static final String IMG_URL = "http://localhost:8088/image-web/";
	/**
	 * 用户session
	 */
	public static final String BUYER_SESSION = "buyer_session";
	/**
	 * 是否登陆拦截页面
	 */
	public static final String INTERCEPTOR_SHOPPING_URL ="/buyer/";
	/**
	 * 判断是否登陆过(京东不拦截)
	 */
	public static final String INTERCEPTOR_LOGIN_URL = "";
}
