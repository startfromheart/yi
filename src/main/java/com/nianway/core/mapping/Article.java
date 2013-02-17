/**
 * 
 */
package com.nianway.core.mapping;

import java.io.Serializable;

/**
 * @author zhizhang.zhou
 * 
 */
public class Article implements Serializable {
	/**
	 * serialVersionUID for Article
	 */
	private static final long serialVersionUID = -1321404629068103982L;
	private String articleId;
	private String categoryId;
	private String articleName;
	private String articleUrl;
	private String articleContent;
	private Integer state = new Integer(0);

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
