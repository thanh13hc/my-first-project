package com.Mysite.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javassist.tools.rmi.ObjectNotFoundException;

public class DaoImpl<ID extends Serializable, T> implements InterfaceDao<ID, T> {
	@Autowired
	SessionFactory sessionFactory;

	Class<T> persistantClass = getPersistantClass();

	Class<T> getPersistantClass() {
		Class<T> classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return classType;
	}

	public List<T> getAllEntities() {
		Session session = null;
		Transaction transaction = null;
		List<T> list = null;
		String query = "From " + this.persistantClass.getSimpleName();

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = session.createQuery(query).list();
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

	public void update(T obj) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.merge(this.persistantClass.getSimpleName(), obj);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void add(T obj) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(this.persistantClass.getSimpleName(), obj);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T findByID(ID id) {
		Session session = null;
		Transaction transaction = null;
		T result = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (T) session.get(this.persistantClass, id);
			if (result == null)
				throw new ObjectNotFoundException("Not found " + id, null);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	public void delete(ID id) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			T obj = (T) findByID(id);
			if (obj != null)
				session.delete(this.persistantClass.getSimpleName(), obj);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
