package com.Mysite.dao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDao <ID extends Serializable, T>{
	List<T> getAllEntities();
	
	void update(T obj);
	
	void add(T obj);
	
	T findByID(ID id);
	
	void delete(ID id);
}
