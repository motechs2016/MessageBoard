package com.hack4b.service.impl;

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

}
