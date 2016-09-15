package com.hack4b.dao;

import com.hack4b.model.Message;

public interface MessageDao {
	
	/**
	 * 添加留言信息
	 * @param msg
	 * @return
	 */
	public boolean addMessage(Message msg);
}
