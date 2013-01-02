/**
 * 
 */
package com.nianway.core.mapping;

import java.io.Serializable;

/**
 * @author zhizhang.zhou
 * 
 */
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8667467258831512804L;
	private String companyId;
	private String companyName;
	private String companyIntroduce;
	private Integer state = new Integer(0);

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
