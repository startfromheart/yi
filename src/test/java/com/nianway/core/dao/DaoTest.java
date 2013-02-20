/**
 * 
 */
package com.nianway.core.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nianway.core.mapping.Article;
import com.nianway.core.mapping.UserInfo;

import junit.framework.TestCase;

/**
 * @author zhizhang.zhou
 * 
 */
public class DaoTest extends TestCase {
	protected SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {

		Configuration config = new Configuration()
				.setProperty("hibernate.dialect",
						"org.hibernate.dialect.HSQLDialect")
				.setProperty("hibernate.connection.driver_class",
						"org.hsqldb.jdbcDriver")
				.setProperty(
						"hibernate.connection.url",
						"jdbc:hsqldb:file:D:/UserData/zhizhang.zhou/dbFolder/demo;hsqldb.write_delay=false;")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.connection.pool_size", "1")
				.setProperty("hibernate.connection.autocommit", "true")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.current_session_context_class",
						"thread").addClass(Article.class)
				.addClass(UserInfo.class);
		sessionFactory = config.buildSessionFactory();
	}
}
