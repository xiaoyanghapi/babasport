package cn.itcast.comment.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/** 
 * 
 * session接口
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月13日 上午9:18:01 
 * 
 */
public interface SessionProvider {
	/**
	 * 往session设置值  value是用户对象   name
	 */
	public void setAttribute(HttpServletRequest request,String name,Serializable value);
	
	/**
	 * 从session中取值
	 */
	public Serializable getAttribute(HttpServletRequest request,String name);
	
	/**
	 * 清空session
	 */
	public void logout(HttpServletRequest request,String name);
	
	/**
	 * 获取sessionID
	 */
	public String getSessionId(HttpServletRequest request);
	
	
	
}
