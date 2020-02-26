package com.Mysite.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.Mysite.model.Sinhvien;

@Repository
public class SinhvienDao extends DaoImpl<Integer, Sinhvien> {

	List<Sinhvien> getSinhvienLimit(int rowStart, int maxRow) {
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

	int getNumberOfSinhvien() {
		Session session = null;
		Transaction tx = null;
		String query = ("from Sinhvien sv order by sv.ten asc");
		int numberOfSv = 0;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			numberOfSv = session.createQuery(query).list().size();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return numberOfSv;
	}
	
	/*code chia page sv */
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		SinhvienDao sinhvienDao = (SinhvienDao) context.getBean("sinhvienDao");
//		int pageNum = sinhvienDao.getNumberOfSinhvien() / 10 + (sinhvienDao.getNumberOfSinhvien() % 10 == 0 ? 0 : 1);
//		for(int i = 0;i<pageNum;i++) {
//			List<Sinhvien> sv = sinhvienDao.getSinhvienLimit(i*10, 10);
//			System.out.println("Page "+(i+1)+"/"+pageNum);
//			for(Sinhvien e: sv) {
//				System.out.println(e.getHo()+" "+e.getTen());
//			}
//			System.out.println("___________________________________");
//		}
//	}
}
