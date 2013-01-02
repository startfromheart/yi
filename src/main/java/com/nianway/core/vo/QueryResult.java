/**
 * 
 */
package com.nianway.core.vo;

import java.util.List;

/**
 * @author zhou
 * 
 */
public class QueryResult {

	private PageVO pageVO = null;
	private List result = null;

	public long getRowCount() {
		return (pageVO == null) ? 0 : pageVO.getRowCount();
	}

	public long getPageCount() {
		return (pageVO == null) ? 0 : pageVO.getPageCount();
	}

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

}
