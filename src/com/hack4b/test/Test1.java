package com.hack4b.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hack4b.model.User;

import javassist.bytecode.Descriptor.Iterator;

public class Test1 {
	
	@Test
	public void initTest(){
		ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		SessionFactory ssf = (SessionFactory) context.getBean("sessionFactory");
		Session session = ssf.openSession();
		String hql = "from User";
		List<User> list = session.createQuery(hql).list();
		session.close();
		java.util.Iterator<User> itr = list.iterator();
		while(itr.hasNext()){
			User user = itr.next();
			System.out.println(user.getUsername()+"--"+user.getPassword());
		}
		
		
	}
}
