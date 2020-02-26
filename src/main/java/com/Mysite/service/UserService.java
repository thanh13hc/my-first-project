package com.Mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mysite.dao.UserDao;
import com.Mysite.model.User;

@Service
public class UserService implements InterfaceService<Integer, User> {

	@Autowired
	UserDao userDao;

	public List<User> getAll() {
		List<User> user = userDao.getAllEntities();
		return user;
	}

	public void update(User obj) {
		userDao.update(obj);
	}

	public void add(User obj) {
		userDao.add(obj);
	}

	public User findByID(Integer id) {
		User user = userDao.findByID(id);
		return user;
	}

	public void delete(List<Integer> id) {
		for (Integer i : id) {
			userDao.delete(i);
		}

	}

}
