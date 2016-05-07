package com.sys.card.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.SessionManager;

public class MajorNameDaoImpl implements MajorNameDao{
	
	/**
 	 * 根据专业名获取专业号
 	 * 
 	 */
   public String getNumByName(String majorName)
   {
	   Session session=SessionManager.getSession();
  	   String hql="select t.majorNum from MajorName t where t.majorName=?";
  	   Query query=session.createQuery(hql);
  	   query.setString(0, majorName);
       List result=null;
       result=query.list();
       session.clear();
       session.close();
       
       String majorNum=null;
       if(result!=null)
       {
    	   if(result.size()>0)
             majorNum=result.get(0).toString();
       }
       return majorNum;
   }
   /**
	 * 根据学院、专业名获取专业号
	 * 
	 */
  public String getNumByName(String department,String majorName)
  {
	   Session session=SessionManager.getSession();
 	   String hql="select t.majorNum from MajorName t,StudentInfo d where t.majorName=? and substr(d.classNo,3,4)=t.majorNum";
 	   Query query=session.createQuery(hql);
 	   query.setString(0, majorName);
      List result=null;
      result=query.list();
      session.clear();
      session.close();
     
      String majorNum=null;
      if(result!=null)
      {
   	   if(result.size()>0)
            majorNum=result.get(0).toString();
      }
      return majorNum;
  }
   /**
	 * 根据专业号获取专业名
	 * 
	 */
  public String getNameByNum(String majorNum)
  {
	   Session session=SessionManager.getSession();
 	   String hql="select t.majorName from MajorName t where t.majorNum=?";
 	   Query query=session.createQuery(hql);
 	   query.setString(0, majorNum);
       List result=null;
       result=query.list();
       session.clear();
       session.close();
       
       String majorName=null;
       if(result!=null)
       {
   	    if(result.size()>0)
   		  majorName=result.get(0).toString();
       }
       return majorName;
  }

}
