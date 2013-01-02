/**
 * 
 */
package com.nianway.core.mapping;

/**
 * @author zhizhang.zhou
 * 
 */
public class Category {
	private String categoryId;
	private String categoryName;
	private String categoryIntroduce;
	private Integer state = new Integer(0);

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryIntroduce() {
		return categoryIntroduce;
	}

	public void setCategoryIntroduce(String categoryIntroduce) {
		this.categoryIntroduce = categoryIntroduce;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
