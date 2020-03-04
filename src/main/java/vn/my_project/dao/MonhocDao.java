package vn.my_project.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import net.bytebuddy.asm.Advice.This;
import vn.my_project.model.Monhoc;

@Repository
public class MonhocDao extends DaoImplement<Integer, Monhoc>{

	static Logger log = Logger.getLogger(MonhocDao.class.getName());
	
	public List<Monhoc> getMonhocLimit(int rowStart, int maxRow) {
		Session session = null;
		Transaction tx = null;
		List<Monhoc> mon = null;

		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Monhoc mon order by mon.maMonhoc asc");
			q.setFirstResult(rowStart);
			q.setMaxResults(maxRow);
			mon = q.list();
			log.info("Load Mon hoc("+rowStart+"-"+(rowStart+maxRow)+") sucessfully, Monhoc detail: "+mon);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("Load Mon hoc unsucessfully");
			e.printStackTrace();
		}
		return mon;
	}
	
	public int getNumberOfMonhoc() {
		Session session = null;
		Transaction tx = null;
		String query = ("from Monhoc");
		int numberOfMon = 0;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			numberOfMon = session.createQuery(query).list().size();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return numberOfMon;
	}
}
