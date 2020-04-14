package vn.my_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import vn.my_project.dao.SinhvienDao;
import vn.my_project.model.Lop;
import vn.my_project.model.Sinhvien;

@Service
public class SinhvienService implements ServiceInterface<Integer, Sinhvien> {
	@Autowired
	SinhvienDao sinhvienDao;

	@Override
	public void create(Sinhvien obj) {
		sinhvienDao.create(obj);
	}

	@Override
	public List<Sinhvien> readAll() {
		return sinhvienDao.readAll();
	}

	@Override
	public Sinhvien readByID(Integer id) {
		return sinhvienDao.readByID(id);
	}

	@Override
	public void update(Sinhvien obj) {
		sinhvienDao.update(obj);
	}

	@Override
	public void delete(Integer id) {
		sinhvienDao.delete(id);
	}

	public int getNumberOfSinhvien() {
		return sinhvienDao.getNumberOfSinhvien();
	}

	public List<Sinhvien> getSVbyLopID(int maLop,int rowStart){
		return sinhvienDao.getSVbyLopID(maLop,rowStart);
	}
	
	public int getNumberOfSVByLopID(int maLop) {
		return sinhvienDao.getNumberOfSVByLopID(maLop);
	}
	
	public List<Sinhvien> getLimitList(int pageNum) {
		// so sinhvien moi page
		final int soSinhvien = 10;
		int rowStart = (pageNum - 1) * 10;
		int limit;
		int numberOfSinhvien = sinhvienDao.getNumberOfSinhvien();

		// tim limit neu cac sinhvien con lai < so sinhvien yeu cau
		// vd yeu cau 50 sinhvien ma chi co 46 sinhvien -> limit = 6
		if ((rowStart + soSinhvien) < numberOfSinhvien)
			limit = soSinhvien;
		else
			limit = numberOfSinhvien - rowStart ;

		// page = 1 -> list 1-10
		List<Sinhvien> list = sinhvienDao.getSinhvienLimit(rowStart, limit);
		return list;
	}

	public List<Sinhvien> getSinhvienNotInLop(int maLop) {
		return sinhvienDao.getSinhvienNotInLop(maLop) ;
	}

}
