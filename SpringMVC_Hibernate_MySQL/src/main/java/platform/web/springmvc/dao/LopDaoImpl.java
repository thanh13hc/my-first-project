package platform.web.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.model.Lop;

@Repository
public class LopDaoImpl implements LopDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<Lop> getAll() {
		List<Lop> list = new ArrayList<Lop>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createNamedQuery("From Lop").list();
		return list;
	}

	@Transactional
	public Lop getLopByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lop.class);
		criteria.add(Restrictions.eq("id", id));

		Lop lop = (Lop) criteria.list().get(0);

		return lop;
	}

	@Transactional
	public List<Lop> getLopByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lop.class);
		criteria.add(Restrictions.like("tenLop", "%" + name + "%"));
		criteria.addOrder(Order.asc("tenLop"));

		List<Lop> list = criteria.list();

		return list;
	}

	@Transactional
	public void insertLop(Lop lop) {
		Session session = sessionFactory.getCurrentSession();

		session.save(lop);
	}

	@Transactional
	public void editLop(Lop lop) {
		Session session = sessionFactory.getCurrentSession();

		session.merge(Lop.class.getSimpleName(), lop);

	}

	@Transactional
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();

		Lop lop = getLopByID(id);

		session.delete(lop);
	}

	@Transactional
	public List<Lop> getLopByMonhocID(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Lop> list = new ArrayList<Lop>();

		list.addAll(session.createQuery("From Lop l Where l.monhoc.maMonhoc=:monID").setParameter("monID", id).list());

		return list;
	}

}
