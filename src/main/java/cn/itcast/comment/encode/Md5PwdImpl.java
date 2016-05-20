package cn.itcast.comment.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/** 
 * 
 * @author 作者 yjj: 
 * @version 创建时间：2016年5月18日 上午11:29:53 
 * 
 */
public class Md5PwdImpl implements Md5Pwd {
	//MD5加密
	public String encode(String password){
		char[] encodeHex = null;
		//加点盐
		password = "yjj"+password+"yjj";
		String algorithm = "MD5";
		//先实例化加密程序
		try {
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			//二进制加密
			byte[] digest = instance.digest(password.getBytes());
			//十六进制加密
			encodeHex =  Hex.encodeHex(digest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(encodeHex);
	}
}
