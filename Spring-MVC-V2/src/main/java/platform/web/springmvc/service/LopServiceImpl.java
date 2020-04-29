package platform.web.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.dao.LopDao;
import platform.web.springmvc.model.Lop;

@Service
public class LopServiceImpl implements LopService{

	@Autowired
	LopDao lopDao;
	
	@Transactional
	public List<Lop> getAll() {
		return lopDao.getAll();
	}

	@Transactional
	public Lop getLopByID(int id) {
		return lopDao.getLopByID(id);
	}

	@Transactional
	public List<Lop> getLopByMonhocID(int id) {
		return lopDao.getLopByMonhocID(id);
	}

	@Transactional
	public List<Lop> getLopByName(String name) {
		return lopDao.getLopByName(name);
	}

	@Transactional
	public void insertLop(Lop lop) {
		lopDao.insertLop(lop);
	}

	@Transactional
	public void editLop(Lop lop) {
		lopDao.editLop(lop);
	}

	@Transactional
	public void delete(int id) {
		lopDao.delete(id);
	}
}
