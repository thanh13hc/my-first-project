package vn.my_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.my_project.dao.GiaovienDao;
import vn.my_project.model.Giaovien;

@Service
public class GiaovienService implements ServiceInterface<Integer, Giaovien> {

	@Autowired
	GiaovienDao giaovienDao;

	public List<Giaovien> getLimitList(int pageNum) {
		// so lop moi page
		final int soGiaovien = 10;
		int rowStart = (pageNum - 1) * 10;
		int limit;
		int numberOfGiaovien = giaovienDao.getNumberOfGiaovien();

		// tim limit neu cac lop con lai < so lop yeu cau
		// vd yeu cau 50 lop ma chi co 46 lop -> limit = 6
		if ((rowStart * soGiaovien + soGiaovien) < numberOfGiaovien)
			limit = soGiaovien;
		else
			limit = numberOfGiaovien - (rowStart * soGiaovien);
		
		// page = 1 -> list 1-10
		List<Giaovien> list = giaovienDao.getGiaovienLimit(rowStart, limit);
		return list;
	}

	public int getNumberOfGiaovien() {
		return giaovienDao.getNumberOfGiaovien();
	}
	
	@Override
	public void create(Giaovien obj) {
		giaovienDao.create(obj);
	}

	@Override
	public List<Giaovien> readAll() {
		return giaovienDao.readAll();
	}

	@Override
	public Giaovien readByID(Integer id) {
		return giaovienDao.readByID(id);
	}

	@Override
	public void update(Giaovien obj) {
		giaovienDao.update(obj);
	}

	@Override
	public void delete(Integer id) {
		giaovienDao.delete(id);
	}

}
