/**
 * 
 */
package com.nianway.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询歌手
	 */
	public QueryResult queryArticle(PageForm pageForm) {

		QueryResult queryResult = null;
		PageVO pageVO = new PageVO();
		pageVO.setPageForm(pageForm);

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Article.class);
			criteria.add(Restrictions.eq("state", 0));

			criteria.setProjection(Projections.rowCount());

			long rowCount = (Long) criteria.uniqueResult();
			pageVO.setRowCount(rowCount);

			queryResult = new QueryResult();
			queryResult.setPageVO(pageVO);

			criteria.setProjection(null);

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
			queryResult.setResult(articleList);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return queryResult;
	}

	/**
	 * 查询总条数
	 */

	public int countArticle(PageForm pageVO) {
		int rowCount = 0;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Article.class);
			criteria.add(Restrictions.eq("state", 0));

			session.getTransaction().commit();
		} catch (Exception ex) {
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
		try {
			session.beginTransaction();
			Object articleObj = session.get(Article.class, articleId);

			if (articleObj == null) {
				return null;
			} else {
				article = (Article) articleObj;
			}

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return article;
	}

	public void add(Article article) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(article);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public void edit(Article article) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(article);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public Article delete(String[] ids) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			for (String articleId : ids) {
				Object articleObj = session.get(Article.class, articleId);
				session.delete(articleObj);
			}
			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		return null;
	}

}
