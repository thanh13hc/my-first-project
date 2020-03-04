package vn.my_project.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javassist.NotFoundException;
import net.bytebuddy.asm.Advice.This;

public class DaoImplement<ID extends Serializable, T> implements DaoInterface<ID, T> {

	@Autowired
	SessionFactory sessionFactory;
	
	static Logger log = Logger.getLogger(DaoImplement.class.getName());
	
	Class<T> persistantClass = getPersistantClass();

	Class<T> getPersistantClass() {
		Class<T> classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return classType;
	}

	String getPersistantClassName() {
		return this.persistantClass.getSimpleName();
	}

	public void create(T obj) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(obj);
			log.info("CREATE "+getPersistantClassName()+" successfull, "+getPersistantClassName()+" detail:"+obj);
			transaction.commit();
		} catch (Exception e) {
			log.error("CREATE "+this.getPersistantClassName()+" unsucessfully");
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<T> readAll() {
		Session session = null;
		Transaction transaction = null;
		List<T> list = null;
		String query = "From " + this.getPersistantClassName();

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = (List<T>) session.createQuery(query).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return list;
	}

	public T readByID(ID id) {
		Session session = null;
		Transaction transaction = null;
		T obj = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			obj = (T) session.get(this.persistantClass, id);
			if (obj == null) {
				throw new NotFoundException("NOT FOUND " + this.getPersistantClassName() + " " + id, null);
			}
			log.info("GET "+getPersistantClassName()+" successfull, "+getPersistantClassName()+" detail:"+obj);
			transaction.commit();
		} catch (Exception e) {
			log.error("GET "+this.getPersistantClassName()+" unsucessfully");
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return obj;
	}

	public void update(T obj) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.merge(this.getPersistantClassName(), obj);
			log.info("UPDATE "+getPersistantClassName()+" successfull, "+getPersistantClassName()+" detail:"+obj);
			transaction.commit();
		} catch (Exception e) {
			log.error("UPDATE "+this.getPersistantClassName()+" unsucessfully");
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void delete(ID id) {
		Session session = null;
		Transaction transaction = null;
		T obj = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			obj = this.readByID(id);
			session.delete(this.getPersistantClassName(), obj);
			log.info("DELETE "+getPersistantClassName()+" successfull, "+getPersistantClassName()+" detail:"+obj);
			transaction.commit();
		} catch (Exception e) {
			log.info("DELETE "+getPersistantClassName()+" unsuccessfull, ");
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
