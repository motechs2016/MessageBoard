package com.hack4b.service.impl;

import java.util.Iterator;
import java.util.List;

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

	@Override
	public int getNumForMsg() {
		return dao.getNumForMsg();
	}

	@Override
	public List<Message> getAllMessage(int currentPage, int pageSize) {
		List<Message> list = dao.getAllMessage(currentPage, pageSize);
		return processContent(list, 35);
	}

	@Override
	public int getNumByUserName(String username) {
		return dao.getNumByUserName(username);
	}

	@Override
	public List<Message> getMsgByUserName(String username) {
		List<Message> list = dao.getMsgByUserName(username);
		return processContent(list, 35);
	}

	@Override
	public int getNumByMail(String email) {
		return dao.getNumByMail(email);
	}

	@Override
	public List<Message> getMsgByMail(String email) {
		List<Message> list = dao.getMsgByMail(email);
		return processContent(list, 35);
	}

	@Override
	public int getNumByContent(String content) {
		return dao.getNumByContent(content);
	}

	@Override
	public List<Message> getMsgByContent(String content) {
		List<Message> list = dao.getMsgByContent(content);
		return processContent(list, 35);
	}

	@Override
	public List<Message> processContent(List<Message> list, int n) {
		Iterator<Message> itr = list.iterator();
		while(itr.hasNext()){
			Message msg = itr.next();
			if(msg.getContent().length()>n){
				String content = msg.getContent().substring(0, n);
				msg.setContent(content);
			}
		}
		return list;
	}

}
