package cn.itcast.core.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fckeditor.response.UploadResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.itcast.comment.web.ResponseUtils;
import cn.itcast.core.web.Constants;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/** 
 * 
 * 上传图片控制器 商品的 品牌的 fck的
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月21日 下午4:28:46 
 * 
 */
@Controller
public class UploadController {
	
	/**
	 * fck上传图片
	 * 编辑人:yjj
	 * 2016年4月27日
	 * 下午1:37:13
	 * 返回值类型: void
	 */
	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request,HttpServletResponse response){
		/**
		 * 多文件上传的设置
		 */
		//强转MutipartHttpServletRequest 支持多个图片
		MultipartHttpServletRequest mr =(MultipartHttpServletRequest)request;
//		获取多个
		Map<String, MultipartFile> fileMap = mr.getFileMap();
//		遍历map
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			/**
			 * 生成图片
			 */
//			上传来的图片
			MultipartFile pic = entry.getValue();
//			获取扩展名通用   getOriginalFilename获取原始名字
			String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
			//图片名称生成策略
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String format = df.format(new Date());
			//随即三个数
			Random r = new Random();
			//在多大范围内随即 
			for (int i = 0; i < 3; i++) {
				format+=r.nextInt(10);
			}
			//实例化一个Jersey
			Client client = new Client();
//			保存数据库
			String path = "upload/"+format+"." + ext;
			//给请求路径去发送
			String url = Constants.IMG_URL+path;
			//设置请求路径 具备发送图片的能力
			WebResource resource = client.resource(url);
			//读取图片到内存中
			try {
				//put提交
				resource.put(String.class, pic.getBytes());
				
				System.out.println("发送完成！");
			}catch (IOException e) {
				e.printStackTrace();
			}
//			返回url给fck 用到java的jar包
			UploadResponse ok = UploadResponse.getOK(url);
//			response 返回对象
//			response.getWriter().print(对象)
//			返回的是字节流
//			response.getWriter().write(字符串)
//			返回的是字符流
			try {
				response.getWriter().print(ok);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("上传fck！");
	}
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(HttpServletResponse response,@RequestParam(required = false) MultipartFile pic) throws Exception{
		/**
		 * 上传单个图片
		 */
//		获取扩展名通用   getOriginalFilename获取原始名字
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		//图片名称生成策略
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());
		//随即三个数
		Random r = new Random();
		//在多大范围内随即 
		for (int i = 0; i < 3; i++) {
			format+=r.nextInt(10);
		}
		//实例化一个Jersey
		Client client = new Client();
//		保存数据库
		String path = "upload/"+format+"." + ext;
		//给请求路径去发送
		String url = Constants.IMG_URL+path;
		//设置请求路径 具备发送图片的能力
		WebResource resource = client.resource(url);
		//读取图片到内存中
		try {
			//put提交
			resource.put(String.class, pic.getBytes());
			
			System.out.println("发送完成！");
		}catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * 返回两个路径
		 */
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		jo.put("path", path);
		ResponseUtils.renderJson(response,jo.toString());
	}
	
}
