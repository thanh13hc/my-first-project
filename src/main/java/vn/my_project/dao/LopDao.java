package vn.my_project.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;

@Repository
public class LopDao extends DaoImplement<Integer, Lop> {

	@Autowired
	GiaovienDao giaovienDao;
	
	public void dangKiGiangDay(int lopId,int giaovienId) {
		Session session = null;
		Transaction tx = null;
		Lop lop = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			
			lop = session.get(Lop.class, lopId);
			Set<Giaovien> setGV = lop.getGiaoviens();
			setGV.add(giaovienDao.readByID(giaovienId));
			lop.setGiaoviens(setGV);
			
			session.merge(lop);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
	}
	
	public Set<Giaovien> getSetGiaovienByLop(int id) {
		Session session = null;
		Transaction tx = null;
		Set<Giaovien> set = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Lop lop = (Lop) session.get(Lop.class, id);
			set = lop.getGiaoviens();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return set;
	}

	public List<Lop> getLopLimit(int rowStart, int maxRow) {
		Session session = null;
		Transaction tx = null;
		List<Lop> lop = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Lop lop order by lop.maLop asc");
			q.setFirstResult(rowStart);
			q.setMaxResults(maxRow);
			lop = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return lop;
	}

	public int getNumberOfLop() {
		Session session = null;
		Transaction tx = null;
		String query = ("from Lop");
		int numberOfLop = 0;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			numberOfLop = session.createQuery(query).list().size();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return numberOfLop;
	}
}
