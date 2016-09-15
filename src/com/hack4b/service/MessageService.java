package com.hack4b.service;

import com.hack4b.model.Message;

public interface MessageService {

	/**
	 * 添加留言信息
	 * @param msg
	 * @return
	 */
	public boolean addMessage(Message msg);
}
