package platform.web.springmvc.dao;

import java.util.List;

import platform.web.springmvc.model.Sinhvien;
import platform.web.springmvc.model.SinhvienDTO;

public interface SinhvienDao {
	
	public List<SinhvienDTO> getAll();
	
	public SinhvienDTO getSinhvienByID(int id);
	
	public List<Sinhvien> getSinhvienByLopID(int id);
	
	public List<Sinhvien> getSinhvienByName(String name);
	
	public void insertSinhvien(Sinhvien sinhvien);
	
	public void editSinhvien(Sinhvien sinhvien);
	
	public void delete(int id);
	
}