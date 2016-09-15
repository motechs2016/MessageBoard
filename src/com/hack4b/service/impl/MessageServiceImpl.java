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
		Iterator<Message> itr = list.iterator();
		while(itr.hasNext()){  //遍历结果集截取正文内容
			Message msg = itr.next();
			if(msg.getContent().length()>35){
				String content = msg.getContent().substring(0, 35);
				msg.setContent(content);
			}
		}
		return list;
	}

}
