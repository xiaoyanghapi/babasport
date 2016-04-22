package cn.itcast.core.bean;

import java.util.Date;

/** 
 * 测试java
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月19日 下午2:06:56 
 * 类说明 
 */
public class TestTable {
	private Integer id;
	private String name;
	private Date birthday;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
