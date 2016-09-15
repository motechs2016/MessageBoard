package com.hack4b.service.impl;

import com.hack4b.dao.MessageDao;
import com.hack4b.model.Message;
import com.hack4b.service.MessageService;

public class MessageServiceImpl implements MessageService {
	private MessageDao dao = null;
	

	public MessageDao getDao() {
		return dao;
	}

	public void setDao(MessageDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean addMessage(Message msg) {
		return dao.addMessage(msg);
	}

}
