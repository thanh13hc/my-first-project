package platform.web.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.model.Monhoc;

@Repository
public class MonhocDaoImpl implements MonhocDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<Monhoc> getAll() {
		List<Monhoc> list = new ArrayList<Monhoc>();
		Session session = sessionFactory.getCurrentSession();

		list = session.createQuery("from Monhoc").list();

		return list;
	}

	@Transactional
	public Monhoc getMonhocByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Monhoc.class);
		criteria.add(Restrictions.eq("id", id));

		Monhoc mon = (Monhoc) criteria.list().get(0);

		return mon;
	}

	@Transactional
	public List<Monhoc> getMonhocByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Monhoc.class);
		criteria.add(Restrictions.like("tenMonhoc", "%" + name + "%"));
		criteria.addOrder(Order.asc("tenMonhoc"));
		
		List<Monhoc> list = criteria.list();

		return list;
	}

	@Transactional
	public void insertMonhoc(Monhoc mon) {
		Session session = sessionFactory.getCurrentSession();

		session.save(mon);
	}

	@Transactional
	public void editMonhoc(Monhoc mon) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(Monhoc.class.getSimpleName(), mon);
	}

	@Transactional
	public void delete(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Monhoc mon = getMonhocByID(id);

		if (mon != null)
			session.delete(mon);

	}

}
