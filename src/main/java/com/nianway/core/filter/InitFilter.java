/**
 * 
 */
package com.nianway.core.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nianway.core.db.HsqlHelper;

/**
 * @author zhizhang.zhou
 * 
 */
public class InitFilter implements ServletContextListener {

	private HsqlHelper hsqlHelper;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		hsqlHelper.start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		hsqlHelper.stop();

	}

}
