package com.hack4b.service.impl;

import com.hack4b.dao.SettingsDao;
import com.hack4b.model.Settings;
import com.hack4b.service.SettingsService;

public class SettingsServiceImpl implements SettingsService {
	private SettingsDao dao = null;
	
	public SettingsDao getDao() {
		return dao;
	}

	public void setDao(SettingsDao dao) {
		this.dao = dao;
	}

	@Override
	public Settings getSettingsByName(String name) {
		return dao.getSettingsByName(name);
	}

	@Override
	public boolean modifySettings(String key, String value) {
		return dao.modifySettings(key, value);
	}

}
