package com.hack4b.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hack4b.dao.UserDao;
import com.hack4b.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory ssf = null;
	
	public SessionFactory getSsf() {
		return ssf;
	}

	public void setSsf(SessionFactory ssf) {
		this.ssf = ssf;
	}

	@Override
	public User login(String username, String password) {
		Session session = ssf.openSession();
		String hql = "from User where username = :username and password = :password";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		query.setMaxResults(1);
		User user = (User) query.uniqueResult();
		session.close();
		if(user!=null){
			return user;
		}
		return null;
	}

	@Override
	public String getSalt(int id) {
		Session session = ssf.openSession();
		String hql = "select salt from User where id = :id";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setInteger("id", id);
		String salt = (String) query.uniqueResult();
		session.close();
		if(salt!=null){
			return salt;
		}
		return "";
	}

	@Override
	public String getSalt(String username) {
		Session session = ssf.openSession();
		String hql = "select salt from User where username = :username";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setString("username", username);
		String salt = (String) query.uniqueResult();
		session.close();
		if(salt!=null){
			return salt;
		}
		return "";
	}

	@Override
	public boolean addUser(User user) {
		Session session = ssf.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public List<User> queryUser(int currentPage, int pageSize) {
		Session session = ssf.openSession();
		String hql = "from User";
		Query query = session.createQuery(hql);
		int startRows = (currentPage-1)*pageSize;
		query.setFirstResult(startRows);
		query.setMaxResults(pageSize);
		List<User> list = query.list();
		session.close();
		return list;
	}

	@Override
	public int getTotalUser() {
		Session session = ssf.openSession();
		String hql = "from User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		session.close();
		return list.size();
	}

}
