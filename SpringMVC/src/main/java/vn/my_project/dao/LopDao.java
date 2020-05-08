package vn.my_project.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;

@Repository
@Primary
public class LopDao extends DaoImplement<Integer, Lop> {

	@Autowired
	GiaovienDao giaovienDao;
	
	@Autowired
	SinhvienDao sinhvienDao;
	
	
	
	public List<Lop> getLopBySearchQuery(String q) {
		Session session = null;
		Transaction tx = null;
		List<Lop> lop = null;
		
		StringBuilder hql = new StringBuilder();
		hql.append("From Lop l");
		hql.append(" where l.tenLop like '%"+q+"%'");
		
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql.toString());
			lop = query.list();
			
			int result = lop.size();
			
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
	
	public void dangKiGiangDay(int lopId,int giaovienId) {
		Session session = null;
		Transaction tx = null;
		Lop lop = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			
			lop = session.get(Lop.class, lopId);
			Set<Giaovien> setGV = lop.getGiaoviens();
			Giaovien gv =giaovienDao.readByID(giaovienId);
			setGV.add(gv);
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
			Query q = session.createQuery("FROM Lop e JOIN FETCH e.monhoc order by e.maLop asc");
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
	
	public List<Lop> getLopByMonhoc(int monID){
		Session session = null;
		Transaction tx = null;
		List<Lop> lop = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Lop e JOIN FETCH e.monhoc where e.monhoc.maMonhoc = :mon_ID");
			q.setParameter("mon_ID", monID);
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

	public void dangKiHoc(int maLop, int maSinhvien) {
		Session session = null;
		Transaction tx = null;
		Lop lop = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			
			lop = session.get(Lop.class, maLop);
			Set<Sinhvien> setSV = lop.getSinhviens();
			Sinhvien sv = sinhvienDao.readByID(maSinhvien);
			setSV.add(sv);
			lop.setSinhviens(setSV);
			
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

}
