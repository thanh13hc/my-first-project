package com.Mysite.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.Mysite.model.Khoa;
import com.Mysite.model.Lop;
import com.Mysite.model.Sinhvien;

import javassist.tools.rmi.ObjectNotFoundException;

@Repository
public class KhoaDao extends DaoImpl<Integer, Khoa> {
	
	public List<Lop> getLopByKhoaID(int id) {
		Transaction transaction = null;
		Session session = null;
		Khoa khoa = null;
		List<Lop> lop = null;
		
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			khoa = session.get(Khoa.class, id);
			lop = khoa.getLop();
			if(khoa == null)
				throw new ObjectNotFoundException("Not found Lop", null);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return lop;
	}
	
	public List<Sinhvien> getSinhvienByKhoaID(int id) {
		Transaction transaction = null;
		Session session = null;
		Khoa khoa = null;
		List<Sinhvien> sv = null;
		
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			khoa = session.get(Khoa.class, id);
			sv = khoa.getSinhvien();
			if(sv == null)
				throw new ObjectNotFoundException("Not found SV", null);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return sv;
	}
	
}
