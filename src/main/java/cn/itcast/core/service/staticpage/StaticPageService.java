package cn.itcast.core.service.staticpage;

import java.util.Map;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月12日 上午9:03:09 
 * 
 */
public interface StaticPageService {
	
	/**
	 * 生产静态页
	 * 编辑人:yjj
	 * 2016年5月12日
	 * 上午9:07:10
	 * 返回值类型: void
	 */
	public void produceStaticPage(Map<String, Object> map,String folderAndProductId,String pageJsp);
	
}
