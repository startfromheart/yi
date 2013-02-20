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
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nianway.core.mapping.UserInfo;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class UserInfoDaoImpl {

	/** ��־ */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserInfoDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * �����û�Id��ѯ�û���Ϣ
	 */

	public UserInfo getUserInfoById(String userName) {
		Transaction tx = null;
		UserInfo user = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			Object userObj = session.get(UserInfo.class, userName);
			if (userObj == null) {
				return null;
			} else {
				user = (UserInfo) userObj;
			}
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			LOGGER.error("����Id��ȡ�û�����", ex);
			if (tx != null) {
				tx.rollback();
			}
		}
		return user;
	}

	/**
	 * �����û���¼�Ų�ѯ�û���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public UserInfo getUserInfoByUserName(String userName) {
		Transaction tx = null;
		List userInfoList = null;
		try {
			Session session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserInfo.class);
			criteria.add(Restrictions.eq("userName", userName));
			userInfoList = criteria.list();
			tx.commit();
		} catch (HibernateException ex) {
			LOGGER.error("��ѯ����", ex);
			if (tx != null) {
				tx.rollback();
			}
		}
		if (userInfoList == null || userInfoList.isEmpty()) {
			return null;
		}

		return (UserInfo) userInfoList.get(0);
	}

}
