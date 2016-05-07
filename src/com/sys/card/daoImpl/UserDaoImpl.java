package com.sys.card.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.card.dao.UserDao;
import com.sys.card.bean.User;
import com.sys.card.dao.SessionManager;

public class UserDaoImpl implements UserDao{

	/**
 	 *  按账户名查询
 	 */
     public User select(String userId)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="from User where userId=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,userId);
         List<User>result=null;
         result=query.list();
         User user=null;
         if(result!=null)
         {
        	 if(result.size()!=0)
        	   user=result.get(0);
         }
         session.clear();
         session.close();
         
         return user;
     }
     public void addUser(User user)
     {
		 Session session=SessionManager.getSession();
		 Transaction transaction=session.beginTransaction();
		 session.save(user);
		 transaction.commit();    	 
		 session.clear();
         session.close();
         
     }
}
