/**
 * 
 */
package com.nianway.core.mapping;

/**
 * @author zhizhang.zhou
 * 
 */
public class Blog {
	private String starId;
	private String companyId;
	private String starName;
	private String starAliasName1;
	private String starAliasName2;
	private Integer focusTime = new Integer(0);
	private Integer sex = new Integer(0);
	private String country;
	private String starIntroduce;
	private Integer state = new Integer(0);

	public String getStarId() {
		return starId;
	}

	public void setStarId(String starId) {
		this.starId = starId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getStarName() {
		return starName;
	}

	public void setStarName(String starName) {
		this.starName = starName;
	}

	public String getStarAliasName1() {
		return starAliasName1;
	}

	public void setStarAliasName1(String starAliasName1) {
		this.starAliasName1 = starAliasName1;
	}

	public String getStarAliasName2() {
		return starAliasName2;
	}

	public void setStarAliasName2(String starAliasName2) {
		this.starAliasName2 = starAliasName2;
	}

	public Integer getFocusTime() {
		return focusTime;
	}

	public void setFocusTime(Integer focusTime) {
		this.focusTime = focusTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStarIntroduce() {
		return starIntroduce;
	}

	public void setStarIntroduce(String starIntroduce) {
		this.starIntroduce = starIntroduce;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
