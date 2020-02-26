package com.Mysite.service;

import java.io.Serializable;
import java.util.List;

public interface InterfaceService <ID extends Serializable, T>{
	List<T> getAll();
	
	void update(T obj);
	
	void add(T obj);
	
	T findByID(ID id);
	
	void delete(List<ID> id);
}
