package platform.web.springmvc.service;

import java.util.List;

import platform.web.springmvc.dto.GiaovienDTO;
import platform.web.springmvc.model.Giaovien;

public interface GiaovienService {
	
	public List<GiaovienDTO> getAll();
	
	public GiaovienDTO getGiaovienByID(int id);
	
	public List<Giaovien> getGiaovienByLopID(int id);
	
	public List<Giaovien> getGiaovienByName(String name);
	
	public void insertGiaovien(Giaovien giaovien);
	
	public void editGiaovien(Giaovien giaovien);
	
	public void delete(int id);
	
}
