package com.hack4b.service.impl;

import java.util.List;

import com.hack4b.dao.UserDao;
import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.hack4b.util.MD5Util;
import com.hack4b.util.SecurityCode;
import com.hack4b.util.SecurityCode.SecurityCodeLevel;

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
		//用户密码加盐
		SecurityCode scd = new SecurityCode();
		String salt = scd.getSecurityCode(6, SecurityCodeLevel.Hard, false);  //生成salt
		String password = MD5Util.getMd5(user.getPassword()+ salt);
		user.setPassword(password);
		user.setSalt(salt);
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

	@Override
	public User queryUserById(int id) {
		return dao.queryUserById(id);
	}

	@Override
	public List<User> queryUserByName(int currentPage, int pageSize, String username) {
		return dao.queryUserByName(currentPage, pageSize, username);
	}

	@Override
	public List<User> queryUserByMail(int currentPage, int pageSize, String mail) {
		return dao.queryUserByMail(currentPage, pageSize, mail);
	}

	@Override
	public int queryUserByName(String username) {
		return dao.queryUserByName(username);
	}

	@Override
	public int queryUserByMail(String mail) {
		return dao.queryUserByMail(mail);
	}
}
