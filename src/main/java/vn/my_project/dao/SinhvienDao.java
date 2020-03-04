package vn.my_project.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;
import vn.my_project.service.SinhvienService;

@Repository
public class SinhvienDao extends DaoImplement<Integer, Sinhvien> {

	static Logger log = Logger.getLogger(SinhvienDao.class.getName());
	
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
		}finally {
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
			log.info("Load Sinhvien("+rowStart+"-"+(rowStart+maxRow)+") sucessfully, Sinhvien detail: "+sv);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.info("Load Sinhvien("+rowStart+"-"+(rowStart+maxRow)+") unsucessfully");
			e.printStackTrace();
		}finally {
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
			log.info("Load Sinhvien("+rowStart+"-"+(rowStart+10)+") from LopID("+maLop+") sucessfully, Sinhvien detail: "+sv);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("Load Sinhvien("+rowStart+"-"+(rowStart+10)+") from LopID("+maLop+") unsucessfully");
			e.printStackTrace();
		}finally {
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
		}finally {
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
		hql.append("SELECT sv FROM Sinhvien sv ");
		hql.append(" WHERE sv.maSinhvien NOT IN ");
		hql.append(" (SELECT sv.maSinhvien FROM Sinhvien sv inner join sv.lops l");
		hql.append(" WHERE l.maLop = " + maLop + ")");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(hql.toString());
			list = q.list();
			log.info("Load Sinhvien Not in Lop from LopID("+maLop+") sucessfully, Sinhvien detail: "+list);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("Load Sinhvien Not in Lop from LopID("+maLop+") unsucessfully");
			e.printStackTrace();
		}finally {
			if (session != null)
				session.close();
		}

		return list;
	}

}
