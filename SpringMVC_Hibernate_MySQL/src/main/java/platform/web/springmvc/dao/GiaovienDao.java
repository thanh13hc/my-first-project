package platform.web.springmvc.dao;

import java.util.List;

import platform.web.springmvc.model.Giaovien;
import platform.web.springmvc.model.GiaovienDTO;

public interface GiaovienDao {
	
	public List<GiaovienDTO> getAll();
	
	public GiaovienDTO getGiaovienByID(int id);
	
	public List<Giaovien> getGiaovienByLopID(int id);
	
	public List<Giaovien> getGiaovienByName(String name);
	
	public void insertGiaovien(Giaovien giaovien);
	
	public void editGiaovien(Giaovien giaovien);
	
	public void delete(int id);
	
}
