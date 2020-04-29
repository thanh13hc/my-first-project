package platform.web.springmvc.dao;

import java.util.List;

import platform.web.springmvc.model.Sinhvien;

public interface SinhvienDao {
	
	public List<Sinhvien> getAll();
	
	public Sinhvien getSinhvienByID(int id);
	
	public List<Sinhvien> getSinhvienByLopID(int id);
	
	public List<Sinhvien> getSinhvienByName(String name);
	
	public void insertSinhvien(Sinhvien sinhvien);
	
	public void editSinhvien(Sinhvien sinhvien);
	
	public void delete(int id);
	
}
