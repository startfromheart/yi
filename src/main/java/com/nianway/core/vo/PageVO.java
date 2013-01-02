/**
 * 
 */
package com.nianway.core.vo;

import java.io.Serializable;

import com.nianway.core.BizConstants;

/**
 * @author zhou
 * 
 */
public class PageVO implements Serializable {
	private PageForm pageForm;
	private long rowCount;

	public PageForm getPageForm() {
		return pageForm;
	}

	public void setPageForm(PageForm pageForm) {
		this.pageForm = pageForm;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public long getPageCount() {
		long pageCount = (long) Math.ceil((double) rowCount
				/ BizConstants.pageSize);
		return pageCount;
	}

	public int getPageNo() {
		if (pageForm == null) {
			return 1;
		} else {
			return pageForm.getPageNo();
		}
	}

	public String getOrderBy() {
		if (pageForm == null) {
			return null;
		} else {
			return pageForm.getOrderBy();
		}
	}

	public String getOrder() {
		if (pageForm == null) {
			return null;
		} else {
			return pageForm.getOrder();
		}
	}
}
