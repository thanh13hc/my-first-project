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

import platform.web.springmvc.dto.GiaovienDTO;
import platform.web.springmvc.model.Giaovien;

@Repository
public class GiaovienDaoImpl implements GiaovienDao{
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<GiaovienDTO> getAll() {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		
		query.append("Select gv.maGiaovien as maGiaovien, gv.ho as ho, gv.ten as ten");
		query.append(" from Giaovien gv");
		
		List<GiaovienDTO> list = session.createQuery(query.toString())
				.unwrap(Query.class)
				.setResultTransformer(Transformers.aliasToBean(GiaovienDTO.class))
				.getResultList();

		return list;
	}

	@Transactional
	public GiaovienDTO getGiaovienByID(int id) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Giaovien.class);
		criteria.add(Restrictions.eq("maGiaovien", id));

		Giaovien gv = (Giaovien) criteria.list().get(0);

		return new GiaovienDTO(gv.getMaGiaovien(), gv.getHo(), gv.getTen());
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
		Giaovien giaovien = (Giaovien) session.get(Giaovien.class, id);

		if (giaovien != null)
			session.delete(giaovien);
	}
	
}
