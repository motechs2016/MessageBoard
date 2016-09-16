package com.hack4b.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring工具类，提供获取IoC中的对象功能
 * @author lejie
 *
 */
public class SpringUtil implements ApplicationContextAware{

	public static ApplicationContext applicationContext = null;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringUtil.applicationContext = arg0;
	}

	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
}
