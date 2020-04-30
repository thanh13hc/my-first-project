package platform.web.springmvc.service;

import java.util.List;

import platform.web.springmvc.model.Monhoc;

public interface MonhocService {

public List<Monhoc> getAll();
	
	public Monhoc getMonhocByID(int id);
	
	public List<Monhoc> getMonhocByName(String name);
	
	public void insertMonhoc(Monhoc mon);
	
	public void editMonhoc(Monhoc mon);
	
	public void delete(int id);
}
