package com.sys.card.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
	private static SessionFactory factory;
	
	/**
	 *  生成一个Session
	 */
	@SuppressWarnings("deprecation")
	public static Session getSession()
	{
		if(factory==null)
		{
			Configuration config=new Configuration().configure();  
	        factory=config.buildSessionFactory();  
		}
		
        return factory.openSession();
	}

}
