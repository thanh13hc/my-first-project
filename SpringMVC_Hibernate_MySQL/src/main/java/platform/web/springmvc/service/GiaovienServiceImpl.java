package platform.web.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.dao.GiaovienDao;
import platform.web.springmvc.dto.GiaovienDTO;
import platform.web.springmvc.model.Giaovien;

@Service
public class GiaovienServiceImpl implements GiaovienService{

	@Autowired
	GiaovienDao giaovienDao;
	
	@Transactional
	public List<GiaovienDTO> getAll() {
		return giaovienDao.getAll();
	}

	@Transactional
	public GiaovienDTO getGiaovienByID(int id) {
		return giaovienDao.getGiaovienByID(id);
	}

	@Transactional
	public List<Giaovien> getGiaovienByLopID(int id) {
		return giaovienDao.getGiaovienByLopID(id);
	}

	@Transactional
	public List<Giaovien> getGiaovienByName(String name) {
		return giaovienDao.getGiaovienByName(name);
	}

	@Transactional
	public void insertGiaovien(Giaovien giaovien) {
		giaovienDao.insertGiaovien(giaovien);
	}

	@Transactional
	public void editGiaovien(Giaovien giaovien) {
		giaovienDao.editGiaovien(giaovien);
	}

	@Transactional
	public void delete(int id) {
		giaovienDao.delete(id);
	}

}
