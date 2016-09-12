package com.hack4b.service;

import com.hack4b.model.User;

public interface UserService {
	/**
	 * 用户登录检查
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 登录成功返回一个User对象，登录失败返回null
	 */
	public User login(String username,String password);
}
