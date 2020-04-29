package platform.web.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.dao.SinhvienDao;
import platform.web.springmvc.model.Sinhvien;

@Service
public class SinhvienServiceImpl implements SinhvienService{

	@Autowired
	SinhvienDao sinhvienDao;
	
	@Transactional
	public List<Sinhvien> getAll() {
		return sinhvienDao.getAll();
	}

	@Transactional
	public Sinhvien getSinhvienByID(int id) {
		return sinhvienDao.getSinhvienByID(id);
	}

	@Transactional
	public List<Sinhvien> getSinhvienByLopID(int id) {
		return sinhvienDao.getSinhvienByLopID(id);
	}

	@Transactional
	public List<Sinhvien> getSinhvienByName(String name) {
		return sinhvienDao.getSinhvienByName(name);
	}

	@Transactional
	public void insertSinhvien(Sinhvien sinhvien) {
		sinhvienDao.insertSinhvien(sinhvien);
	}

	@Transactional
	public void editSinhvien(Sinhvien sinhvien) {
		sinhvienDao.editSinhvien(sinhvien);
	}

	@Transactional
	public void delete(int id) {
		sinhvienDao.delete(id);
	}

}
