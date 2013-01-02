/**
 * 
 */
package com.nianway.core.vo;

import com.nianway.core.BizConstants;

/**
 * @author zhou
 * 
 */
public class PageForm {
	private int pageNo = 1;
	private String orderBy;
	private boolean ascending = false;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		if (ascending) {
			return "ascending";
		} else {
			return "descending";
		}
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public int getFirstResult() {
		int firstResult = (pageNo - 1) * BizConstants.pageSize;
		return firstResult;
	}

}
