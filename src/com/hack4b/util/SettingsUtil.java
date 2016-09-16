package com.hack4b.util;

import java.util.HashMap;
import java.util.Map;

import com.hack4b.service.SettingsService;

/**
 * 网站配置工具类
 * @author lejie
 *
 */
public class SettingsUtil {
	
	private static final SettingsUtil uniqueInstance = null;
	private Map<String,String> map = new HashMap<>();  //用户保存配置信息
	private boolean isInit = false;
	
	private SettingsUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static SettingsUtil getInstance(){
		if(uniqueInstance==null){
			return new SettingsUtil();
		}
		return uniqueInstance;
	}
	
	public Map<String, String> getSettings(){
		if(isInit==false){
			SettingsService settingsService = (SettingsService) SpringUtil.getBean("settingsService");
			String WebTitle = settingsService.getSettingsByName("WebTitle").getValue();  //网站标题
			String Title1 = settingsService.getSettingsByName("Title1").getValue();  //首页一级标题
			String Title2 = settingsService.getSettingsByName("Title2").getValue();  //首页二级标题
			String WebButton = settingsService.getSettingsByName("WebButton").getValue();  //首页按钮名
			String MsgNumber = settingsService.getSettingsByName("MsgNumber").getValue();  //首页留言数量
			String CopyRight1 = settingsService.getSettingsByName("Copyright1").getValue();  //首页一级版权
			String CopyRight2 = settingsService.getSettingsByName("Copyright2").getValue();  //首页二级版权
			map.put("WebTitle", WebTitle);
			map.put("Title1", Title1);
			map.put("Title2", Title2);
			map.put("WebButton", WebButton);
			map.put("MsgNumber", MsgNumber);
			map.put("Copyright1", CopyRight1);
			map.put("Copyright2", CopyRight2);
			this.isInit = true;
		}
		return map;
	}
}
