package com.Mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mysite.dao.KhoaDao;
import com.Mysite.model.Khoa;
import com.Mysite.model.Lop;
import com.Mysite.model.Sinhvien;

@Service
public class KhoaService implements InterfaceService<Integer, Khoa> {

	@Autowired
	KhoaDao khoaDao;

	public List<Khoa> getAll() {
		List<Khoa> khoa = khoaDao.getAllEntities();
		return khoa;
	}

	public void update(Khoa obj) {
		khoaDao.update(obj);
	}

	public void add(Khoa obj) {
		khoaDao.add(obj);
	}

	public Khoa findByID(Integer id) {
		Khoa khoa = khoaDao.findByID(id);
		return khoa;
	}
	
	public void delete(List<Integer> id) {
		for (Integer i : id) {
			khoaDao.delete(i);
		}
	}

	public List<Lop> getLopByKhoaID(int maKhoa) {
		List<Lop> lop = khoaDao.getLopByKhoaID(maKhoa);
		return lop;
	}

	public List<Sinhvien> getSinhvienByKhoaID(int maKhoa) {
		List<Sinhvien> sv = khoaDao.getSinhvienByKhoaID(maKhoa);
		return sv;
	}
	
}
