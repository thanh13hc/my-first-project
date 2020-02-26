package com.Mysite.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.Mysite.model.Lop;
import com.Mysite.model.Sinhvien;

import javassist.tools.rmi.ObjectNotFoundException;

@Repository
public class LopDao extends DaoImpl<Integer, Lop>{
	
	
	public List<Sinhvien> getSinhvienByLopID(int id) {
		Transaction transaction = null;
		Session session = null;
		Lop lop = null;
		List<Sinhvien> sv = null;
		
		try {
			session =this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			lop = session.get(Lop.class, id);
			sv = lop.getSinhVien();
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
