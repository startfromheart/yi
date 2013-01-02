/**
 * 
 */
package com.nianway.core.mapping;

import java.io.Serializable;

/**
 * @author zhizhang.zhou
 * 
 */
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2826321348380619603L;
	private String itemId;
	private String categoryId;
	private String itemName;
	private String itemUrl;
	private String itemIntroduce;
	private Integer state = new Integer(0);

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getItemIntroduce() {
		return itemIntroduce;
	}

	public void setItemIntroduce(String itemIntroduce) {
		this.itemIntroduce = itemIntroduce;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
