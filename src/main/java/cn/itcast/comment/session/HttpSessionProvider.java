package cn.itcast.comment.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

/** 
 * 
 * session实现类
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月13日 上午9:18:15 
 * 
 */
public class HttpSessionProvider implements SessionProvider{

	@Override
	public void setAttribute(HttpServletRequest request, String name,
			Serializable value) {
		//如果存在则获取，没有就创建 通过服务器端的
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
		
	}

	@Override
	public Serializable getAttribute(HttpServletRequest request, String name) {
		/**
		 * 获取老的id
		 */
		HttpSession session = request.getSession(false);
		if(null != session){
			return (Serializable)session.getAttribute(name);
		}
		return null;
	}

	@Override
	public void logout(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		/**
		 * 销毁ID
		 */
		if(null != session){
			session.invalidate();
		}
	}

	@Override
	public String getSessionId(HttpServletRequest request) {
		//获取的是地址栏中的JSESSIONID
		//request.getRequestedSessionId();
		return request.getSession().getId();
	}

}
