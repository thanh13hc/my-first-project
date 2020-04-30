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

import platform.web.springmvc.model.Sinhvien;

@Repository
public class SinhvienDaoImpl implements SinhvienDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<Sinhvien> getAll() {
		Session session = sessionFactory.getCurrentSession();

		List<Sinhvien> list = new ArrayList<Sinhvien>();
		list = session.createQuery("From Sinhvien").list();

		return list;
	}

	@Transactional
	public Sinhvien getSinhvienByID(int id) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Sinhvien.class);
		criteria.add(Restrictions.eq("maSinhvien", id));

		Sinhvien sinhvien = (Sinhvien) criteria.list().get(0);

		return sinhvien;
	}

	@Transactional
	public List<Sinhvien> getSinhvienByLopID(int id) {
		Session session = sessionFactory.getCurrentSession();

		List<Sinhvien> list = new ArrayList<Sinhvien>();
		list.add((Sinhvien) session.createQuery("From Sinhvien sv where sv.lop.maLop =:lopID").setParameter("lopID", id)
				.list());

		return list;
	}

	@Transactional
	public List<Sinhvien> getSinhvienByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Sinhvien.class);
		criteria.add(Restrictions.like("tenSinhvien", "%" + name + "%"));
		criteria.addOrder(Order.asc("tenSinhvien"));

		List<Sinhvien> list = criteria.list();

		return list;
	}

	@Transactional
	public void insertSinhvien(Sinhvien sinhvien) {
		Session session = sessionFactory.getCurrentSession();

		session.save(sinhvien);
	}

	@Transactional
	public void editSinhvien(Sinhvien sinhvien) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(Sinhvien.class.getSimpleName(), sinhvien);
	}

	@Transactional
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Sinhvien sinhvien = getSinhvienByID(id);

		if (sinhvien != null)
			session.delete(sinhvien);
	}

}
