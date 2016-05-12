package cn.itcast.core.service.staticpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.jdbc.datasource.embedded.OutputStreamFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 
 * 使用没有注解的方式
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月12日 上午9:02:49 
 * 
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{
	
	private ServletContext servletContext;
	private Configuration conf;
	

	/**
	 * pageJsp-ftl下的模板页面
	 * folderAndProductId /product/productDetail.html 生成的页面的地址加上页面名称
	 * 生成静态页面
	 * 编辑人:yjj
	 * 2016年5月12日
	 * 上午10:48:15
	 * 返回值类型: void
	 */
	public void produceStaticPage(Map<String, Object> map, String folderAndProductId,String pageJsp) {
		
		Writer out  = null;
//		html输出路径
		String htmlPath  = getPath("/html");
		File path  = new File(htmlPath+folderAndProductId);
		/**
		 * 根据需要创建上级文件夹
		 */
		creatFolder(path);
		try {
//			模板获取目录(在freemarker中设置了获取模板的地址)
//			String ftl = servletContext.getRealPath("ftl");
//			设置模板路径
//			conf.setDirectoryForTemplateLoading(new File(ftl));
			Template template = conf.getTemplate(pageJsp);
			out = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
			
//			输出文件
			template.process(map, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取configeration
	 * 编辑人:yjj
	 * 2016年5月12日
	 * 上午10:23:25
	 * 返回值类型: void
	 */
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
			 this.conf = freeMarkerConfigurer.getConfiguration();
	}
	/**
	 * 上下文设置
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	/**
	 * 获取路径
	 * 编辑人:yjj
	 * 2016年5月12日
	 * 上午11:11:11
	 * 返回值类型: String
	 */
	public String getPath(String name){
		return servletContext.getRealPath(name);
	}
	/**
	 * 判断某个文件夹是否存在并创建
	 * 编辑人:yjj
	 * 2016年5月12日
	 * 上午11:12:08
	 * 返回值类型: void
	 */
	public void creatFolder(File path){
		File parentFile = path.getParentFile();
		if(!parentFile.exists()){
			try {
				parentFile.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
