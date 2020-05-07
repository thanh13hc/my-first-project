package platform.web.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import platform.web.springmvc.dao.MonhocDao;
import platform.web.springmvc.dao.MonhocDaoImpl;
import platform.web.springmvc.model.Monhoc;
import platform.web.springmvc.model.MonhocDTO;

@Service
public class MonhocServiceImpl implements MonhocService{

	@Autowired
	MonhocDao monhocDaoImpl;

	@Transactional
	public List<MonhocDTO> getAll() {
		return monhocDaoImpl.getAll();
	}

	@Transactional
	public MonhocDTO getMonhocByID(int id) {
		return monhocDaoImpl.getMonhocByID(id);
	}

	@Transactional
	public List<Monhoc> getMonhocByName(String name) {
		return monhocDaoImpl.getMonhocByName(name);
	}

	@Transactional
	public void insertMonhoc(Monhoc mon) {
		monhocDaoImpl.insertMonhoc(mon);
	}

	@Transactional
	public void editMonhoc(Monhoc mon) {
		monhocDaoImpl.editMonhoc(mon);
	}

	@Transactional
	public void delete(int id) {
		monhocDaoImpl.delete(id);
	}

}
