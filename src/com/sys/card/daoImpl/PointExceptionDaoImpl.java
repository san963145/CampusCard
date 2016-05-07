package com.sys.card.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.card.bean.PointException;
import com.sys.card.dao.PointExceptionDao;
import com.sys.card.dao.SessionManager;

public class PointExceptionDaoImpl implements PointExceptionDao{

	@Override
	public void add(String sno, String info) {
		// TODO Auto-generated method stub
		 Session session=SessionManager.getSession();
		 Transaction transaction=session.beginTransaction();
		 PointException pointException=new PointException();
		 pointException.setSno(sno);
		 pointException.setInfo(info);
		 session.save(pointException);
		 transaction.commit();
		 session.clear();
         session.close();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		 Session session=SessionManager.getSession();
 		 Transaction transaction=session.beginTransaction();
 		 String hql="delete from PointException";
 		 Query query=session.createQuery(hql);
 		 query.executeUpdate();
 		 transaction.commit(); 
 		 session.clear();
         session.close();
	}

	@Override
	public List selectAllStudents() {
		// TODO Auto-generated method stub
		  Session session=SessionManager.getSession();
     	  String hql="select s.sno,s.total,s.count,s.average from StudentPoint s";
     	  Query query=session.createQuery(hql);
          List result=null;
          result=query.list();
          session.clear();
          session.close();          
          return result;
	}

	@Override
	public List selectMeanQ(String sno) {
		// TODO Auto-generated method stub
		 Session session=SessionManager.getSession();
    	 String hql="select d.ave_MEAN,d.ave_Q1,d.ave_Q3,d.count_Q1,d.count_Q2,d.count_Q3 from StudentInfo s,DepartmentPoint d where s.department=d.department and s.grade=d.grade and trim(s.sno)=?";
    	 Query query=session.createQuery(hql);
    	 query.setString(0,sno);
         List result=null;
         result=query.list();
         session.clear();
         session.close();   
         return result;
	}

	@Override
	public String selectInfoBySno(String sno) {
		// TODO Auto-generated method stub
		Session session=SessionManager.getSession();
   	    String hql="select s.info from PointException s where trim(s.sno)=?";
   	    Query query=session.createQuery(hql);
   	    query.setString(0,sno);
        List result=null;
        result=query.list();
        session.clear();
        session.close();    
        String r="нч";
        if(result!=null)
      	  if(result.size()>0)
             r=(String) result.get(0);
        return r;
	}

}
