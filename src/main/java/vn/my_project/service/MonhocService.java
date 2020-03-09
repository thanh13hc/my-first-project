package vn.my_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import vn.my_project.dao.DaoImplement;
import vn.my_project.dao.MonhocDao;
import vn.my_project.model.Monhoc;

@Service
public class MonhocService implements ServiceInterface<Integer, Monhoc> {

	@Autowired
	@Qualifier(value="monhocDao")
	MonhocDao monhocDao;

	public List<Monhoc> getLimitList(int pageNum) {
		// so mon moi page
		final int soMon = 10;
		int rowStart = (pageNum - 1) * 10;
		int limit;
		int numberOfMonhoc = monhocDao.getNumberOfMonhoc();

		// tim limit neu cac mon con lai < so mon yeu cau
		// vd yeu cau 50 mon ma chi co 46 mon -> limit = 6
		if ((rowStart + soMon) < numberOfMonhoc)
			limit = soMon;
		else
			limit = numberOfMonhoc - rowStart ;
		
		// page = 1 -> list 1-10
		List<Monhoc> list = monhocDao.getMonhocLimit(rowStart, limit);
		return list;
	}
	
	public int getNumberOfMonhoc() {
		return monhocDao.getNumberOfMonhoc();
	}
	
	@Override
	public void create(Monhoc obj) {
		monhocDao.create(obj);
	}

	@Override
	public List<Monhoc> readAll() {
		return monhocDao.readAll();
	}

	@Override
	public Monhoc readByID(Integer id) {
		return monhocDao.readByID(id);
	}

	@Override
	public void update(Monhoc obj) {
		monhocDao.update(obj);
	}

	@Override
	public void delete(Integer id) {
		monhocDao.delete(id);
	}

}
