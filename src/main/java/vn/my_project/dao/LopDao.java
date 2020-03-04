package vn.my_project.dao;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;

@Repository
public class LopDao extends DaoImplement<Integer, Lop> {

	@Autowired
	GiaovienDao giaovienDao;
	
	@Autowired
	SinhvienDao sinhvienDao;
	
	static Logger log = Logger.getLogger(LopDao.class.getName());

	
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
			log.info("Load lop by name like '"+q+"' sucessfully, result: "+result);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("Load lop by name like '"+q+"' unsucessfully");
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
			log.info("Dang ki giang day thanh cong, Lop detail: "+lop+" - Giaovien detail:"+gv);
			tx.commit();
		} catch (Exception e) {
			log.info("Dang ki giang day khong thanh cong");
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
			log.info("Load Lop("+rowStart+"-"+(rowStart+maxRow)+") sucessfully, Monhoc detail: "+lop);
			tx.commit();
		} catch (Exception e) {
			log.info("Load Mon hoc("+rowStart+"-"+(rowStart+maxRow)+") unsucessfully");
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
			
			log.info("Đăng ki học thanh cong, Sinhvien detail: "+sv+" - Lop detail:"+lop);
			tx.commit();
		} catch (Exception e) {
			log.error("Đăng ki học khong thanh cong");
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
