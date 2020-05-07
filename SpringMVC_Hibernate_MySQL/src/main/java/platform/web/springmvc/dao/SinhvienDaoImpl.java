package platform.web.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.model.Sinhvien;
import platform.web.springmvc.model.SinhvienDTO;

@Repository
public class SinhvienDaoImpl implements SinhvienDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<SinhvienDTO> getAll() {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		
		query.append("Select sv.maSinhvien as maSinhvien, sv.ho as ho, sv.ten as ten");
		query.append(" from Sinhvien sv");
		
		List<SinhvienDTO> list = session.createQuery(query.toString())
				.unwrap(Query.class)
				.setResultTransformer(Transformers.aliasToBean(SinhvienDTO.class))
				.getResultList();

		return list;
	}

	@Transactional
	public SinhvienDTO getSinhvienByID(int id) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Sinhvien.class);
		criteria.add(Restrictions.eq("maSinhvien", id));

		Sinhvien sv = (Sinhvien) criteria.list().get(0);

		return new SinhvienDTO(sv.getMaSinhvien(), sv.getHo(), sv.getTen());
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
		Sinhvien sinhvien = (Sinhvien) session.get(Sinhvien.class.getSimpleName(), id);

		if (sinhvien != null)
			session.delete(sinhvien);
	}

}
