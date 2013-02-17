/**
 * 
 */
package com.nianway.core.dao;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nianway.core.mapping.Article;
import com.nianway.core.vo.PageForm;

/**
 * @author zhizhang.zhou
 * 
 */
public class ArticleDaoImplTest extends TestCase {

	ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		Configuration config = new Configuration()
				.setProperty("hibernate.dialect",
						"org.hibernate.dialect.HSQLDialect")
				.setProperty("hibernate.connection.driver_class",
						"org.hsqldb.jdbcDriver")
				.setProperty("hibernate.connection.url",
						"jdbc:hsqldb:file:D:/UserData/zhizhang.zhou/dbFolder/demo")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.connection.pool_size", "1")
				.setProperty("hibernate.connection.autocommit", "true")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.current_session_context_class", "thread")
				.addClass(Article.class);
		SessionFactory sessionFactory = config.buildSessionFactory();
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
		articleDaoImpl.queryArticle(pageForm);
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#countArticle(com.nianway.core.vo.PageForm)}
	 * .
	 */
	public void testCountArticle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#queryArticleById(java.lang.String)}
	 * .
	 */
	public void testQueryArticleById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#add(com.nianway.core.mapping.Article)}
	 * .
	 */
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#edit(com.nianway.core.mapping.Article)}
	 * .
	 */
	public void testEdit() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.nianway.core.dao.ArticleDaoImpl#delete(java.lang.String[])}.
	 */
	public void testDelete() {
		fail("Not yet implemented");
	}

}
