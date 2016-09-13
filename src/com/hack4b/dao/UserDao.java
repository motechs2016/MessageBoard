package com.hack4b.dao;

import java.util.List;

import com.hack4b.model.User;

public interface UserDao {
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
	 * @return  如果查询成功，则返回salt值，否则返回空串
	 */
	public String getSalt(int id);
	
	/**
	 * 根据用户名获取用户的散列盐值
	 * @param username
	 * @return 如果查询成功，则返回salt值，否则返回空串
	 */
	public String getSalt(String username);
	
	/**
	 * 添加用户
	 * @param user
	 * @return 成功返回true，失败返回false
	 */
	public boolean addUser(User user);
	
	/**
	 * 用户信息查询
	 * @param currentPage 起始页，从第几页开始查询
	 * @param pageSize  页面的大小（每页显示的数量）
	 * @return
	 */
	public List<User> queryUser(int currentPage,int pageSize);
	
	/**
	 * 获取所有的用户数量
	 * @return
	 */
	public int getTotalUser();
}
