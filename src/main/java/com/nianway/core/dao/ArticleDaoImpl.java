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

	/** ��־ */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ArticleDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * ��ȡ��ѯ���½��
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
			LOGGER.error("��ѯ����", ex);
			session.getTransaction().rollback();
		}
		return articleList;
	}

	/**
	 * ��ѯ������
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
			LOGGER.error("ͳ��������������", ex);
			session.getTransaction().rollback();
		}
		return rowCount;
	}

	/**
	 * ͨ��ID�Ų�ѯ����
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
			LOGGER.error("����Id��ȡ���³���", ex);
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
			LOGGER.error("������³���", ex);
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
			LOGGER.error("�༭���³���", ex);
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
			LOGGER.error("ɾ�����³���", ex);
			session.getTransaction().rollback();
		}
	}

}
