package com.hack4b.service;

import com.hack4b.model.Settings;

public interface SettingsService {
	/**
	 * 根据名称获取网站设置值
	 * @param name
	 * @return
	 */
	public Settings getSettingsByName(String name);
	
	/**
	 * 修改设置信息
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean modifySettings(String key, String value);
}
