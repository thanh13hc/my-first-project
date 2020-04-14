package vn.my_project.dao;

import java.io.Serializable;
import java.util.List;

public interface DaoInterface <ID extends Serializable,T> {
	
	void create(T obj);
	
	List<T> readAll();
	
	T readByID(ID id);
	
	void update(T obj);
	
	void delete(ID id);
}
