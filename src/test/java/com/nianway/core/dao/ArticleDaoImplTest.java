/**
 * 
 */
package com.nianway.core.dao;

import junit.framework.Assert;

import com.nianway.core.mapping.Article;
import com.nianway.core.vo.PageForm;
import com.nianway.core.vo.QueryResult;

/**
 * @author zhizhang.zhou
 * 
 */
public class ArticleDaoImplTest extends DaoTest {

	ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		articleDaoImpl.setSessionFactory(sessionFactory);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#queryArticle(com.nianway.core.vo.PageForm)}
	 * .
	 */
	public void testQueryArticle() {
		PageForm pageForm = new PageForm();
		pageForm.setAscending(false);
		pageForm.setOrderBy("articleId");
		pageForm.setPageNo(1);
		QueryResult queryResult = articleDaoImpl.queryArticle(pageForm);

		Assert.assertNotNull(queryResult);
		Assert.assertTrue(queryResult.getPageCount() >= 1);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#queryArticleById(java.lang.String)}
	 * .
	 */
	public void testQueryArticleById() {
		Article article = articleDaoImpl
				.queryArticleById("8a8fe4aa3cebcc0a013cebcc2bc80000");
		Assert.assertNotNull(article);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#add(com.nianway.core.mapping.Article)}
	 * .
	 * 
	 * @throws InterruptedException
	 */
	public void testAdd() throws InterruptedException {
		Article article = new Article();
		article.setArticleName("登录中心dd");
		article.setArticleContent("登录中心的内容是什么");
		article.setState(0);
		articleDaoImpl.add(article);
		Thread.sleep(1000);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#edit(com.nianway.core.mapping.Article)}
	 * .
	 */
	public void testEdit() {
		Article article = articleDaoImpl
				.queryArticleById("8a8fe4aa3cebcc0a013cebcc2bc80000");
		articleDaoImpl.edit(article);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#delete(java.lang.String[])}.
	 */
	public void testDelete() {
		Article article = new Article();
		article.setArticleName("登录中心dd");
		article.setArticleContent("登录中心的内容是什么");
		article.setState(0);
		articleDaoImpl.add(article);

		String articleId = article.getArticleId();
		articleDaoImpl.delete(new String[] { articleId });
	}

}
