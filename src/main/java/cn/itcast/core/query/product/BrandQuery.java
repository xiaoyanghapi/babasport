package cn.itcast.core.query.product;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * 品牌查询专用
 * @author 作者 yjj: 
 * @version 创建时间：2016年4月23日 下午2:49:21 
 * 
 */
public class BrandQuery {
	private Integer id;
	private String name;
	private String description;
	private String imgUrl;
	private Integer sort;
	private Integer isDisplay;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	/*************查询字段指定*************/
	private String fields;
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}

	/*************name是否为模糊查询*************/
	private Boolean nameLike = false;

	public Boolean getNameLike() {
		return nameLike;
	}
	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}
	/**************内部类*****************/
	public class FieldOrder{
		private String field;  //name id 等属性
		private String order;  //desc 或者asc
		
		public FieldOrder() {
			super();
		}
		
		public FieldOrder(String field, String order) {
			super();
			this.field = field;
			this.order = order;
		}

		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		
	}
	//orderby 的集合
	private List<FieldOrder> fieldOrders = new ArrayList<FieldOrder>();
	//按照id排序
	public void orderbyId(boolean isAsc){
		fieldOrders.add(new FieldOrder("id", isAsc == true ? "asc" : "desc"));
	}
	public void orderbyName(boolean isAsc){
		fieldOrders.add(new FieldOrder("name", isAsc == true ? "asc" : "desc"));
	}
	
	//页号
	private Integer pageNo = 1;
	
	//开始行
	private Integer startRow;
	
	//条数
	private Integer pageSize = 10;
	
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		//计算开始行
		this.startRow = (pageNo -1 ) * pageSize;
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		//计算开始行
		this.startRow = (pageNo-1) * pageSize;
		this.pageNo = pageNo;
	}
	
	
}
