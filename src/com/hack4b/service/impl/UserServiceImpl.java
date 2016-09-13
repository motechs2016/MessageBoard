package com.hack4b.service.impl;

import java.util.List;

import com.hack4b.dao.UserDao;
import com.hack4b.model.User;
import com.hack4b.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = null;
	
	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public User login(String username, String password) {
		return dao.login(username, password);
	}

	@Override
	public String getSalt(int id) {
		return dao.getSalt(id);
	}

	@Override
	public String getSalt(String username) {
		return dao.getSalt(username);
	}

	@Override
	public boolean addUser(User user) {
		return dao.addUser(user);
	}

	@Override
	public List<User> queryUser(int currentPage, int pageSize) {
		return dao.queryUser(currentPage, pageSize);
	}

	@Override
	public int getTotalUser() {
		return dao.getTotalUser();
	}

}
