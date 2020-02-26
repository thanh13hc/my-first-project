package com.Mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mysite.dao.SinhvienDao;
import com.Mysite.model.Sinhvien;

@Service(value="sinhvienService")
public class SinhvienService implements InterfaceService<Integer, Sinhvien>{

	@Autowired
	SinhvienDao sinhvienDao;

	public List<Sinhvien> getAll() {
		List<Sinhvien> sinhvien = sinhvienDao.getAllEntities();
		return sinhvien;
	}

	public void update(Sinhvien obj) {
		sinhvienDao.update(obj);
	}

	public void add(Sinhvien obj) {
		sinhvienDao.add(obj);
	}

	public Sinhvien findByID(Integer id) {
		Sinhvien sinhvien = sinhvienDao.findByID(id);
		return sinhvien;
	}

	public void delete(List<Integer> id) {
		for (Integer i : id) {
			sinhvienDao.delete(i);
		}
	}
}
