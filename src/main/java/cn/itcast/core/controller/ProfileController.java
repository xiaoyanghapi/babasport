package cn.itcast.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.image.ImageCaptchaService;

import cn.itcast.comment.encode.Md5Pwd;
import cn.itcast.comment.session.SessionProvider;
import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.service.user.BuyerService;
import cn.itcast.core.web.Constants;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月18日 上午10:29:31 
 * 
 */
@Controller
public class ProfileController {
	
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private Md5Pwd md5Pwd;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	
	/**
	 * 页面登陆跳转
	 * 编辑人:yjj
	 * 2016年5月18日
	 * 上午10:30:47
	 * 返回值类型: String
	 */
	@RequestMapping(value = "/shopping/toLogin.shtml",method = RequestMethod.GET)
	public String login(ModelMap model,String returnUrl){
		return "/buyer/login";
	}
	/**
	 * 页面登陆
	 * 编辑人:yjj
	 * 2016年5月18日
	 * 上午10:31:05
	 * 返回值类型: String
	 * 1用户名是否为空
	 * 2密码是否为空
	 * 3验证码是否为空
	 * 4验证码是否正确
	 * 5用户是否存在
	 * 6密码是否正确（md5加密  加盐）
	放进session 跳转returnUrl

	 */
	@RequestMapping(value = "/shopping/login.shtml",method = RequestMethod.POST)
	public String login(ModelMap model,Buyer buyer,String returnUrl,String captcha,HttpServletRequest request){
		
		if(StringUtils.isNotBlank(buyer.getUsername())){
			
			if(StringUtils.isNotBlank(buyer.getPassword())){
				
				if(StringUtils.isNotBlank(captcha)){
					
					if(imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request), captcha)){
						
						Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
						if(null!=b){
							
							if(b.getPassword().equals(md5Pwd.encode(buyer.getPassword()))){
								
								sessionProvider.setAttribute(request, Constants.BUYER_SESSION, b);
								return "redirect:"+returnUrl;
							}else{
								model.addAttribute("errors", "密码错误！");
							}
						}else{
							model.addAttribute("errors", "用户名不存在！");
						}
					}else{
						model.addAttribute("errors", "验证码错误！");
					}
				}else{
					model.addAttribute("errors", "验证码不能为空！");
				}
			}else{
				model.addAttribute("errors", "密码不能为空！");
			}
		}else{
			model.addAttribute("errors", "用户名不能为空！");
		}
		model.addAttribute("user", buyer);
//		model.addAttribute("password", buyer.getPassword());
//		model.addAttribute("returnUrl", returnUrl);
		return "buyer/login";
	}
	/**
	 * 返回个人数据
	 * 编辑人:yjj
	 * 2016年5月18日
	 * 下午5:24:04
	 * 返回值类型: String
	 */
	@RequestMapping(value ="/buyer/index.shtml")
	public String index(){
		return "/buyer/index";
	}
}
