package vn.my_project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;

@Repository
public class SinhvienDao extends DaoImplement<Integer, Sinhvien>{

	public int getNumberOfSinhvien() {
		Session session = null;
		Transaction tx = null;
		String query = ("from Sinhvien");
		int numberOfLop = 0;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			numberOfLop = session.createQuery(query).list().size();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return numberOfLop;
	}

	public List<Sinhvien> getSinhvienLimit(int rowStart, int maxRow) {
		Session session = null;
		Transaction tx = null;
		List<Sinhvien> sv = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Sinhvien sv order by sv.ten asc");
			q.setFirstResult(rowStart);
			q.setMaxResults(maxRow);
			sv = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return sv;
	}

}
