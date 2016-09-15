package com.hack4b.dao;

import java.util.List;

import com.hack4b.model.Message;

public interface MessageDao {
	
	/**
	 * 添加留言信息
	 * @param msg
	 * @return
	 */
	public boolean addMessage(Message msg);
	
	/**
	 * 获取所有留言记录的行数
	 * @return
	 */
	public int getNumForMsg();
	
	/**
	 * 分页获取留言信息
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Message> getAllMessage(int currentPage,int pageSize);
	
	/**
	 * 按照指定用户名发表留言的数量
	 * @param username
	 * @return
	 */
	public int getNumByUserName(String username);
	
	/**
	 * 获取指定用户名的留言记录
	 * @param username
	 * @return
	 */
	public List<Message> getMsgByUserName(String username);
	
	/**
	 * 获取指定邮箱的记录数量
	 * @param email
	 * @return
	 */
	public int getNumByMail(String email);
	
	/**
	 * 按邮箱查询留言记录
	 * @param email
	 * @return
	 */
	public List<Message> getMsgByMail(String email);
	
	/**
	 * 查询指定内容的留言数量
	 * @param content
	 * @return
	 */
	public int getNumByContent(String content);
	
	/**
	 * 查询指定内容的留言
	 * @param content
	 * @return
	 */
	public List<Message> getMsgByContent(String content);
}
