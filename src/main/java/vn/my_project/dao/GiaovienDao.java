package vn.my_project.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;

@Repository
public class GiaovienDao extends DaoImplement<Integer, Giaovien>{
	
	static Logger log = Logger.getLogger(GiaovienDao.class.getName());
	
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
			log.info("Load Giaovien("+rowStart+"-"+(rowStart+maxRow)+") sucessfully, Giaovien detail: "+giaovien);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.info("Load Giaovien("+rowStart+"-"+(rowStart+maxRow)+") unsucessfully");
			e.printStackTrace();
		}finally {
			if (session != null)
				session.close();
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
		}finally {
			if (session != null)
				session.close();
		}
		return numberOfGiaovien;
	}
	
	public List<Giaovien> getGVbyLopID(int maLop, int rowStart) {
		Session session = null;
		Transaction tx = null;
		List<Giaovien> gv = null;

		StringBuilder hql = new StringBuilder();
		hql.append("Select gv from Giaovien gv");
		hql.append(" inner join gv.lops l");
		hql.append(" where l.maLop = "+maLop);
		hql.append(" order by gv.ten asc");
		
		int startPosition = (rowStart-1)*10;
		
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(hql.toString());
			q.setFirstResult(startPosition);
			q.setMaxResults(10);
			gv = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			if (session != null)
				session.close();
		}
		return gv;
	}
	
	public int getNumberOfGVByLopID(int maLop) {
		Session session = null;
		Transaction tx = null;
		int result = 0;

		StringBuilder hql = new StringBuilder();
		hql.append("Select gv from Giaovien gv");
		hql.append(" inner join gv.lops l");
		hql.append(" where l.maLop = "+maLop);
		hql.append(" order by gv.ten asc");
		
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

	public List<Giaovien> getGiaovienNotInLop(int maLop) {
		Session session = null;
		Transaction tx = null;
		List<Giaovien> list = null;

		StringBuilder hql = new StringBuilder();
		hql.append("SELECT gv FROM Giaovien gv ");
		hql.append(" WHERE gv.maGiaovien NOT IN ");
		hql.append(" (SELECT gv.maGiaovien FROM Giaovien gv inner join gv.lops l");
		hql.append(" WHERE l.maLop = " + maLop + ")");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(hql.toString());
			list = q.list();
			
			log.info("Load Giaovien Not in Lop from LopID("+maLop+") sucessfully, Sinhvien detail: "+list);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.info("Load Giaovien Not in Lop from LopID("+maLop+") unsucessfully");
			e.printStackTrace();
		}finally {
			if (session != null)
				session.close();
		}

		return list;
	}
}
