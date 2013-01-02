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
import com.nianway.core.mapping.Item;
import com.nianway.core.vo.PageForm;
import com.nianway.core.vo.PageVO;
import com.nianway.core.vo.QueryResult;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class ItemDaoImpl {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询歌手
	 */
	public QueryResult queryItem(PageForm pageForm) {

		QueryResult queryResult = null;
		PageVO pageVO = new PageVO();
		pageVO.setPageForm(pageForm);

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
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

			List itemList = criteria.list();
			queryResult.setResult(itemList);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return queryResult;
	}

	/**
	 * 查询总条数
	 */

	public int countItem(PageForm pageVO) {
		int rowCount = 0;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
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

	public Item queryItemById(String itemId) {

		Item item = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Object itemObj = session.get(Item.class, itemId);

			if (itemObj == null) {
				return null;
			} else {
				item = (Item) itemObj;
			}

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return item;
	}

	public void add(Item item) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(item);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public void edit(Item item) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(item);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public Item delete(String[] ids) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			for (String itemId : ids) {
				Object itemObj = session.get(Item.class, itemId);
				session.delete(itemObj);
			}
			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		return null;
	}

}
