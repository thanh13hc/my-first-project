package vn.my_project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;

@Repository
public class GiaovienDao extends DaoImplement<Integer, Giaovien>{
	
	public List<Giaovien> getGiaovienLimit(int rowStart, int maxRow) {
		Session session = null;
		Transaction tx = null;
		List<Giaovien> giaovien = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Giaovien giaovien order by giaovien.ten asc");
			q.setFirstResult(rowStart);
			q.setMaxResults(maxRow);
			giaovien = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return giaovien;
	}
	
	public int getNumberOfGiaovien() {
		Session session = null;
		Transaction tx = null;
		String query = ("from Giaovien");
		int numberOfGiaovien = 0;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			numberOfGiaovien = session.createQuery(query).list().size();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return numberOfGiaovien;
	}
	
}
