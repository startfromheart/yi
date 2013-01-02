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

import com.nianway.core.mapping.Company;

/**
 * @author zhizhang.zhou
 * 
 */
@Repository
public class CompanyDaoImpl {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询娱乐公司
	 */

	@SuppressWarnings("unchecked")
	public List<Company> queryCompany() {

		List<Company> companyList = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("state", 0));
			criteria.setMaxResults(10);

			companyList = criteria.list();

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return companyList;
	}

	/**
	 * 通过ID号查询娱乐公司
	 */

	public Company queryCompanyById(String companyId) {

		Company company = null;

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Object companyObj = session.get(Company.class, companyId);

			if (companyObj == null) {
				return null;
			} else {
				company = (Company) companyObj;
			}

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

		return company;
	}

	public void add(Company company) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(company);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}

	public void edit(Company company) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(company);

			session.getTransaction().commit();

		} catch (Exception ex) {
			session.getTransaction().rollback();
		}

	}
}
