package cn.itcast.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.comment.session.SessionProvider;
import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.web.Constants;

/**
 * 
 * 处理上下文的 全局
 * 
 * @author 作者 yjj:
 * @version 创建时间：2016年5月19日 上午9:14:56
 * 
 */
public class SpringMvcInterceptor implements HandlerInterceptor {

	@Autowired
	private SessionProvider sessionProvider;
	/**
	 * 是否是管理员
	 */
	private Integer isAdmin;

	/**
	 * 方法前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 取出session中的值
		 */
		Buyer userSession = (Buyer) sessionProvider.getAttribute(request,
				Constants.BUYER_SESSION);
		/**
		 * 是否是管理员
		 */
		if (null != isAdmin) {
			if (null == userSession) {
				Buyer buyer = new Buyer();
				buyer.setUsername("fbb2014");
				request.setAttribute("isLogin", true);
				sessionProvider.setAttribute(request, Constants.BUYER_SESSION,
						buyer);
			} else {
				request.setAttribute("isLogin", true);
			}
		} else {
			Boolean flag = false;
			if (null != userSession) {
				flag = true;
				request.setAttribute("isLogin", flag);
			}
			// 正常的逻辑 先获取请求的地址 然后对比拦截的路径
			String requestURI = request.getRequestURI();
			/**
			 * 查看是否是拦截路径
			 */
			if (requestURI.startsWith(Constants.INTERCEPTOR_SHOPPING_URL)) {
				if (null == userSession) {
					response.sendRedirect("/shopping/login.shtml?returnUrl="
							+ request.getAttribute("returnUrl"));
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 方法后
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 页面渲染后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

}
