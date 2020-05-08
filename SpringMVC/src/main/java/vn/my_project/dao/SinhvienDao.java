package vn.my_project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Sinhvien;
import vn.my_project.service.SinhvienService;

@Repository
@Primary
public class SinhvienDao extends DaoImplement<Integer, Sinhvien> {

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
		} finally {
			if (session != null)
				session.close();
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
		} finally {
			if (session != null)
				session.close();
		}
		return sv;
	}

	public List<Sinhvien> getSVbyLopID(int maLop, int rowStart) {
		Session session = null;
		Transaction tx = null;
		List<Sinhvien> sv = null;

		StringBuilder hql = new StringBuilder();
		hql.append("Select sv from Sinhvien sv");
		hql.append(" inner join sv.lops l");
		hql.append(" where l.maLop = " + maLop);
		hql.append(" order by sv.ten asc");

		int startPosition = (rowStart - 1) * 10;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(hql.toString());
			q.setFirstResult(startPosition);
			q.setMaxResults(10);
			sv = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return sv;
	}

	public int getNumberOfSVByLopID(int maLop) {
		Session session = null;
		Transaction tx = null;
		int result = 0;

		StringBuilder hql = new StringBuilder();
		hql.append("Select sv from Sinhvien sv");
		hql.append(" inner join sv.lops l");
		hql.append(" where l.maLop = " + maLop);
		hql.append(" order by sv.ten asc");

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(hql.toString());
			result = q.list().size();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	public List<Sinhvien> getSinhvienNotInLop(int maLop) {
		Session session = null;
		Transaction tx = null;
		List<Sinhvien> list = null;

		StringBuilder hql = new StringBuilder();
		hql.append("SELECT sv FROM Sinhvien sv");
		hql.append(" WHERE sv.maSinhvien NOT IN  (");
		hql.append("SELECT svl.maSinhvien FROM Sinhvien svl join svl.lops l");
		hql.append(" Where l.maLop = 1)");
		
		System.out.println(hql);
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			list = session.createQuery(hql.toString(),Sinhvien.class)
					.getResultList();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return list;
	}

}
