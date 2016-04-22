package cn.itcast.core.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(HttpServletResponse response,@RequestParam(required = false) MultipartFile pic) throws Exception{
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
//		返回两个路径
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		jo.put("path", path);
		ResponseUtils.renderJson(response,jo.toString());
	}
	
}
