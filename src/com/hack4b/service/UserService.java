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
	
	/**
	 * 根据用户ID获取用户的散列盐值
	 * @param id
	 * @return 如果查询成功，则返回salt值，否则返回空串
	 */
	public String getSalt(int id);
	
	/**
	 * 根据用户名获取用户的散列盐值
	 * @param username
	 * @return 如果查询成功，则返回salt值，否则返回空串
	 */
	public String getSalt(String username);
}