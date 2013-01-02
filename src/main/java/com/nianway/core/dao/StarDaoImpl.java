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

import com.nianway.core.mapping.Star;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class StarDaoImpl {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询歌手
	 */

	@SuppressWarnings("unchecked")
	public List<Star> queryStar() {

		List<Star> starList = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Star.class);
			criteria.add(Restrictions.eq("state", 0));
			criteria.setMaxResults(10);

			starList = criteria.list();

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return starList;
	}

	/**
	 * 通过ID号查询歌曲
	 */

	public Star queryStarById(String starId) {

		Star star = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Object starObj = session.get(Star.class, starId);

			if (starObj == null) {
				return null;
			} else {
				star = (Star) starObj;
			}

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return star;
	}

	public void add(Star star) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(star);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public void edit(Star star) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(star);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public List<Star> queryMaleStar() {
		// TODO Auto-generated method stub
		return null;
	}

}
