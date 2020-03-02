package vn.my_project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.my_project.dao.GiaovienDao;
import vn.my_project.dao.LopDao;
import vn.my_project.model.Lop;
import vn.my_project.model.Giaovien;

@Service
public class LopService implements ServiceInterface<Integer, Lop> {

	@Autowired
	LopDao lopDao;
	

	public void DangKiGiangDay(int maLop,int maGiaovien) {
//		Lop lop = lopDao.readByID(maLop);
//		Set<Giaovien> set = lopDao.getSetGiaovienByLop(maLop);
//		Giaovien gv = giaovienDao.readByID(maGiaovien);
//		
//		System.out.println(set.size());
//		
//		set.add(gv);
//		lop.setGiaoviens(set);
//
//		lopDao.update(lop);
		
		lopDao.dangKiGiangDay(maLop, maGiaovien);
	}
	
	public List<Lop> getLimitList(int pageNum) {
		// so lop moi page
		final int soLop = 10;
		int rowStart = (pageNum - 1) * 10;
		int limit;
		int numberOfLop = lopDao.getNumberOfLop();

		// tim limit neu cac lop con lai < so lop yeu cau
		// vd yeu cau 50 lop ma chi co 46 lop -> limit = 6
		if ((rowStart * soLop + soLop) < numberOfLop)
			limit = soLop;
		else
			limit = numberOfLop - (rowStart * soLop);
		
		// page = 1 -> list 1-10
		List<Lop> list = lopDao.getLopLimit(rowStart, limit);
		return list;
	}

	public int getNumberOfLop() {
		return lopDao.getNumberOfLop();
	}

	@Override
	public void create(Lop obj) {
		lopDao.create(obj);
	}

	@Override
	public List<Lop> readAll() {
		return lopDao.readAll();
	}

	@Override
	public Lop readByID(Integer id) {
		return lopDao.readByID(id);
	}

	@Override
	public void update(Lop obj) {
		lopDao.update(obj);
	}

	@Override
	public void delete(Integer id) {
		lopDao.delete(id);
	}

}
