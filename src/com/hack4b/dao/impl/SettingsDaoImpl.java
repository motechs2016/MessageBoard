package com.hack4b.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hack4b.dao.SettingsDao;
import com.hack4b.model.Settings;

public class SettingsDaoImpl implements SettingsDao {
	private SessionFactory ssf = null;
	
	public SessionFactory getSsf() {
		return ssf;
	}

	public void setSsf(SessionFactory ssf) {
		this.ssf = ssf;
	}

	@Override
	public Settings getSettingsByName(String name) {
		Session session = ssf.openSession();
		String hql = "from Settings where config like :key";
		Query query = session.createQuery(hql);
		query.setString("key", name);
		query.setMaxResults(1);
		Settings sst = (Settings) query.uniqueResult();
		session.close();
		return sst;
	}

	@Override
	public boolean modifySettings(String key, String value) {
		Session session = ssf.openSession();
		session.beginTransaction();
		String hql = "update Settings set value = :value where config = :key";
		Query query =session.createQuery(hql);
		query.setString("value", value);
		query.setString("key", key);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return true;
	}

}
