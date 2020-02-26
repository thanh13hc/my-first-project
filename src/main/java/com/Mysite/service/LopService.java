package com.Mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mysite.dao.LopDao;
import com.Mysite.model.Lop;
import com.Mysite.model.Sinhvien;

@Service
public class LopService implements InterfaceService<Integer, Lop> {

	@Autowired
	LopDao lopDao;

	public List<Lop> getAll() {
		List<Lop> lop = lopDao.getAllEntities();
		return lop;
	}

	public void update(Lop obj) {
		lopDao.update(obj);
	}

	public void add(Lop obj) {
		lopDao.add(obj);
	}

	public Lop findByID(Integer id) {
		Lop lop = lopDao.findByID(id);
		return lop;
	}
	
	public List<Sinhvien> getSinhvienByLopID(Integer id) {
		List<Sinhvien> sv = lopDao.getSinhvienByLopID(id);
		return sv;
	}

	public void delete(List<Integer> id) {
		for (Integer i : id) {
			lopDao.delete(i);
		}
	}
}
