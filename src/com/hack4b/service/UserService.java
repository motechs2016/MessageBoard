package com.hack4b.service;

import java.util.List;

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
	
	/**
	 * 添加用户
	 * @param user
	 * @return 成功返回true，失败返回false
	 */
	public boolean addUser(User user);
	
	/**
	 * 用户信息查询
	 * @param currentPage 起始页
	 * @param pageSize  页面的大小（每页显示的数量）
	 * @return
	 */
	public List<User> queryUser(int currentPage,int pageSize);
	
	/**
	 * 获取所有的用户数量
	 * @return
	 */
	public int getTotalUser();
	
	/**
	 * 按ID查询用户信息
	 * @return 如果查询成功，则返回User对象，否则返回null
	 */
	public User queryUserById(int id);
	
	/**
	 * 按ID删除用户
	 * @param id
	 * @return 删除成功返回true，删除失败返回false
	 */
	public boolean deleteUserById(int id);
	
	/**
	 * 按用户名查询用户信息
	 * @param currentpage
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public List<User> queryUserByName(int currentPage,int pageSize,String username);
	
	/**
	 * 按用户名查询用户数量
	 * @param username
	 * @return
	 */
	public int queryUserByName(String username);
	
	/**
	 * 按邮箱查询用户信息
	 * @param currentPage
	 * @param pageSize
	 * @param mail
	 * @return
	 */
	public List<User> queryUserByMail(int currentPage,int pageSize,String mail);
	
	/**
	 * 按邮箱查询用户数量
	 * @param mail
	 * @return
	 */
	public int queryUserByMail(String mail);
	
	/**
	 * 修改用户信息
	 * @param id
	 * @return 修改成功返回true，失败返回false
	 */
	public Boolean modifyUserById(User user);
}
