package com.hack4b.dao.impl;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public int getNumForMsg() {
		Session session = ssf.openSession();
		String hql = "from Message";
		Query query = session.createQuery(hql);
		List<Message> list = query.list();
		session.close();
		return list.size();
	}

	@Override
	public List<Message> getAllMessage(int currentPage, int pageSize) {
		Session session = ssf.openSession();
		String hql = "from Message";
		Query query = session.createQuery(hql);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		List<Message> list = query.list();
		session.close();
		return list;
	}

}
