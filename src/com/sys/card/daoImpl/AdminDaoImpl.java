package com.sys.card.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.card.dao.AdminDao;
import com.sys.card.dao.SessionManager;

public class AdminDaoImpl implements AdminDao{

	@Override
	public List select(String userName, String password) {
		// TODO Auto-generated method stub
		 Session session=SessionManager.getSession();
    	 String hql="select t.userName from Admin t where t.userName=? and t.password=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,userName);
         query.setString(1,password);
         List result=query.list();
         session.clear();
         session.close();        
         return result;
	}
	
}
