package platform.web.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.model.Giaovien;

public class GiaovienDaoImpl {
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<Giaovien> getAll() {
		Session session = sessionFactory.getCurrentSession();

		List<Giaovien> list = new ArrayList<Giaovien>();
		list = session.createQuery("From Giaovien").list();

		return list;
	}

	@Transactional
	public Giaovien getGiaovienByID(int id) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Giaovien.class);
		criteria.add(Restrictions.eq("maGiaovien", id));

		Giaovien giaovien = (Giaovien) criteria.list().get(0);

		return giaovien;
	}

	@Transactional
	public List<Giaovien> getGiaovienByLopID(int id) {
		Session session = sessionFactory.getCurrentSession();

		List<Giaovien> list = new ArrayList<Giaovien>();
		list.add((Giaovien) session.createQuery("From Giaovien gv where gv.lop.maLop =:lopID").setParameter("lopID", id)
				.list());

		return list;
	}

	@Transactional
	public List<Giaovien> getGiaovienByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Giaovien.class);
		criteria.add(Restrictions.like("tenGiaovien", "%" + name + "%"));
		criteria.addOrder(Order.asc("tenGiaovien"));

		List<Giaovien> list = criteria.list();

		return list;
	}

	@Transactional
	public void insertGiaovien(Giaovien giaovien) {
		Session session = sessionFactory.getCurrentSession();

		session.save(giaovien);
	}

	@Transactional
	public void editGiaovien(Giaovien giaovien) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(Giaovien.class.getSimpleName(), giaovien);
	}

	@Transactional
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Giaovien giaovien = getGiaovienByID(id);

		if (giaovien != null)
			session.delete(giaovien);
	}
}
