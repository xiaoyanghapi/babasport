package cn.itcast.core.query;
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
		private String order = "desc";  //desc 或者asc
		
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
	
}
