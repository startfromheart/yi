/**
 * 
 */
package com.nianway.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nianway.core.mapping.Category;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class CategoryDaoImpl {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询歌手
	 */

	@SuppressWarnings("unchecked")
	public List<Category> queryCategory() {

		List<Category> categoryList = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("state", 0));
			criteria.setMaxResults(10);

			categoryList = criteria.list();

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return categoryList;
	}

	/**
	 * 通过ID号查询歌曲
	 */

	public Category queryCategoryById(String categoryId) {

		Category category = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Object categoryObj = session.get(Category.class, categoryId);

			if (categoryObj == null) {
				return null;
			} else {
				category = (Category) categoryObj;
			}

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return category;
	}

	public void add(Category category) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(category);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public void edit(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(category);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

}
