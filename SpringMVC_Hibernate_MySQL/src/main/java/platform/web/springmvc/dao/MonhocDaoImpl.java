package platform.web.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.model.Monhoc;
import platform.web.springmvc.model.MonhocDTO;

@Repository
public class MonhocDaoImpl implements MonhocDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<MonhocDTO> getAll() {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		
		query.append("Select m.maMonhoc as maMonhoc, m.tenMonhoc as tenMonhoc");
		query.append(" from Monhoc m");

		List<MonhocDTO> list = session.createQuery(query.toString())
		.unwrap(org.hibernate.Query.class)
		.setResultTransformer(Transformers.aliasToBean(MonhocDTO.class))
		.getResultList();
		
		return list;
	}

	@Transactional
	public MonhocDTO getMonhocByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Monhoc.class);
		criteria.add(Restrictions.eq("id", id));

		Monhoc mon = (Monhoc) criteria.list().get(0);

		return new MonhocDTO(mon.getMaMonhoc(), mon.getTenMonhoc());
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
		Monhoc mon = (Monhoc) session.get(Monhoc.class.getSimpleName(), id);

		if (mon != null)
			session.delete(mon);

	}

}
