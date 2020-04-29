package platform.web.springmvc.dao;

import java.util.List;

import platform.web.springmvc.model.Lop;

public interface LopDao {
	
	public List<Lop> getAll();
	
	public Lop getLopByID(int id);
	
	public List<Lop> getLopByMonhocID(int id);
	
	public List<Lop> getLopByName(String name);
	
	public void insertLop(Lop lop);
	
	public void editLop(Lop lop);
	
	public void delete(int id);
	
}
