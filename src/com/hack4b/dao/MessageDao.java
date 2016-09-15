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
}
