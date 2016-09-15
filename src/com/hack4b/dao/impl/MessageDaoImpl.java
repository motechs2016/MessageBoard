package com.hack4b.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hack4b.dao.MessageDao;
import com.hack4b.model.Message;

public class MessageDaoImpl implements MessageDao {
	
	private SessionFactory ssf = null;

	public SessionFactory getSsf() {
		return ssf;
	}

	public void setSsf(SessionFactory ssf) {
		this.ssf = ssf;
	}

	@Override
	public boolean addMessage(Message msg) {
		Session session = ssf.openSession();
		session.beginTransaction();
		session.save(msg);
		session.getTransaction().commit();
		session.close();
		return true;
	}

}
