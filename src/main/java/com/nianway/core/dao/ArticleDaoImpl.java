/**
 * 
 */
package com.nianway.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nianway.core.BizConstants;
import com.nianway.core.mapping.Article;
import com.nianway.core.vo.PageForm;
import com.nianway.core.vo.PageVO;
import com.nianway.core.vo.QueryResult;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class ArticleDaoImpl {

	/** 日志 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ArticleDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取查询文章结果
	 */
	public QueryResult queryArticle(PageForm pageForm) {

		QueryResult queryResult = new QueryResult();
		PageVO pageVO = new PageVO();
		pageVO.setPageForm(pageForm);
		long rowCount = countArticle(pageForm);
		pageVO.setRowCount(rowCount);
		List articleList = getArticleList(pageForm);
		queryResult.setResult(articleList);
		queryResult = new QueryResult();
		queryResult.setPageVO(pageVO);
		return queryResult;
	}

	private List getArticleList(PageForm pageForm) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Article.class);
		criteria.add(Restrictions.eq("state", 0));
		Order order = null;
		if (pageForm.isAscending()) {
			order = Order.asc(pageForm.getOrderBy());
		} else {
			order = Order.desc(pageForm.getOrderBy());
		}
		criteria.addOrder(order);
		criteria.setFirstResult(pageForm.getFirstResult());
		criteria.setMaxResults(BizConstants.pageSize);
		@SuppressWarnings("rawtypes")
		List articleList = criteria.list();
		try {
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("查询出错", ex);
			session.getTransaction().rollback();
		}
		return articleList;
	}

	/**
	 * 查询总条数
	 */

	private long countArticle(PageForm pageVO) {
		long rowCount = 0;
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Article.class);
		criteria.add(Restrictions.eq("state", 0));
		criteria.setProjection(Projections.rowCount());
		rowCount = (Long) criteria.uniqueResult();
		try {
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("统计文章数量出错", ex);
			session.getTransaction().rollback();
		}
		return rowCount;
	}

	/**
	 * 通过ID号查询歌曲
	 */

	public Article queryArticleById(String articleId) {

		Article article = null;

		Session session = this.sessionFactory.getCurrentSession();

		session.beginTransaction();
		Object articleObj = session.get(Article.class, articleId);

		if (articleObj == null) {
			return null;
		} else {
			article = (Article) articleObj;
		}
		try {
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("根据Id获取文章出错", ex);
			session.getTransaction().rollback();
		}
		return article;
	}

	public void add(Article article) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(article);
			tx.commit();
		} catch (HibernateException ex) {
			LOGGER.error("添加文章出错", ex);
			if (tx != null) {
				tx.rollback();
			}
		}

	}

	public void edit(Article article) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(article);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("编辑文章出错", ex);
			session.getTransaction().rollback();
		}
	}

	public void delete(String[] ids) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			for (String articleId : ids) {
				Object articleObj = session.get(Article.class, articleId);
				session.delete(articleObj);
			}
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("删除文章出错", ex);
			session.getTransaction().rollback();
		}
	}

}
