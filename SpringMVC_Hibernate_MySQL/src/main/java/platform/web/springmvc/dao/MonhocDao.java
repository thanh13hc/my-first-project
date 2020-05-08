package platform.web.springmvc.dao;

import java.util.List;

import platform.web.springmvc.dto.MonhocDTO;
import platform.web.springmvc.model.Monhoc;

public interface MonhocDao {
	
	public List<MonhocDTO> getAll();
	
	public MonhocDTO getMonhocByID(int id);
	
	public List<Monhoc> getMonhocByName(String name);
	
	public void insertMonhoc(Monhoc mon);
	
	public void editMonhoc(Monhoc mon);
	
	public void delete(int id);
	
}
