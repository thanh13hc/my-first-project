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

import platform.web.springmvc.dto.LopDTO;
import platform.web.springmvc.dto.MonhocDTO;
import platform.web.springmvc.model.Lop;

@Repository
public class LopDaoImpl implements LopDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<LopDTO> getAll() {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();

		query.append("Select l.maLop as maLop, l.tenLop as tenLop, l.ngayBatdau as ngayBatdau,");
		query.append(" l.ngayKetthuc as ngayKetthuc, l.soGioHoc as soGioHoc");
		query.append(" from Lop as l");

		List<LopDTO> list = session.createQuery(query.toString()).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(LopDTO.class)).getResultList();

		return list;
	}

	@Transactional
	public LopDTO getLopByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lop.class);
		criteria.add(Restrictions.eq("id", id));

		Lop l = (Lop) criteria.list().get(0);

		return new LopDTO(l.getMaLop(), l.getTenLop(), l.getNgayBatdau(), l.getNgayKetthuc(), l.getSoGioHoc());
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

		Lop lop = (Lop) session.get(Lop.class, id);
		if (lop != null)
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
