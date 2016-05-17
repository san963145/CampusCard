package com.sys.card.daoImpl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.card.dao.StudentInfoDao;
import com.sys.card.dao.SessionManager;

public class StudentInfoDaoImpl implements StudentInfoDao{
	
	public String selectDepartmentBySno(String sno)
	{
	   Session session=SessionManager.getSession();
  	   String hql="select s.department from StudentInfo s where trim(s.sno)=?";
  	   Query query=session.createQuery(hql);
       query.setString(0,sno);
       List list=query.list();
       String department=null;
       if(list!=null)
       {
      	 if(list.size()!=0)
      	 {
      		 department=(String) list.get(0);
      	 }
       }
       session.clear();
       session.close();      
       return department;
	}  
	 public String selectMajorBySno(String sno)
	 {
		Session session=SessionManager.getSession();
   	    String hql="select substr(s.classNo,3,4) from StudentInfo s where trim(s.sno)=?";
   	    Query query=session.createQuery(hql);
        query.setString(0,sno);
        List list=query.list();
        String major=null;
        if(list!=null)
        {
       	 if(list.size()!=0)
       	 {
       		 major=(String) list.get(0);
       	 }
        }
        session.clear();
        session.close();
        return major;
	 }
	 public List selectGMBySno(String sno)
	 {
		Session session=SessionManager.getSession();
   	    String hql="select s.grade,s.classNo from StudentInfo s where trim(s.sno)=?";
   	    Query query=session.createQuery(hql);
        query.setString(0,sno);
        List list=query.list();
        session.clear();
        session.close();
        return list;
	 }
	 
	   	/**
	   	 * 根据学院获取专业列表
	   	 * @param args
	   	 */
	   	public List getMajorNumByDepartment(String department)
	    {
	 	   Session session=SessionManager.getSession();
	   	   String hql="select distinct substr(t.classNo,3,4) from StudentInfo t where t.department=?";
	   	   Query query=session.createQuery(hql);
	   	   query.setString(0, department);
	        List result=null;
	        result=query.list();
	        if(result!=null)
	        {
	        	Object[] obj=result.toArray();
	    		Arrays.sort(obj);
	    		result=Arrays.asList(obj);
	        }
	        session.clear();
	        session.close();
	        return result;
	    }
	    /**
	     * 根据学院号来获取所有年级
	     */
	    public List getGradesByDepartment(String department){
	    	Session session=SessionManager.getSession();
	   	    String hql="select distinct t.grade from StudentInfo t where t.department=? order by t.grade";
	   	    Query query=session.createQuery(hql);
	   	    query.setString(0, department);
	        List result=null;
	        result=query.list();
	        session.clear();
	        session.close();
	        return result;
	    }
	    /**
	     * 判断学号是否在学院范围内
	     */
	    public String Check(String sno,String department)
	   	{
	   		Session session=SessionManager.getSession();
	       	String hql = "select t.sno from StudentInfo t where t.department = ? and trim(t.sno) = ?";
	       	Query query=session.createQuery(hql);
	       	query.setString(0, department);
	       	query.setString(1, sno);
	       	List list = query.list();
	       	session.clear();
	       	session.close();
	       	if(list.size()>0)
	       	    return "1";
	       	else
	       		return "2";
	   	}
	    /**
	     * 判断学号是否在学院、年级范围内
	     */
	    public String Check(String sno,String department,String grade)
	   	{
	    	String flag="2";
	    	String[] grades=grade.split("#");
	   		Session session=SessionManager.getSession();
	       	String hql = "select t.sno from StudentInfo t where t.department = ? and trim(t.sno) = ? and substr(t.classNo,0,2) = ?";
	       	Query query=null;
	       	for(int i=0;i<grades.length;i++)
	       	{
	       	  query=session.createQuery(hql);
	       	  query.setString(0, department);
	       	  query.setString(1, sno);
	       	  query.setString(2, grades[i].substring(2,4));
	       	  List list = query.list();
	       	  if(list.size()>0)
	       	  {
	       		  flag="1";
	       		  break;
	       	  }
	       	}
	       	session.clear();
	       	session.close();
	       	return flag;
	   	}
		@Override
		public String getDepartmentGradeBySno(String sno) {
			// TODO Auto-generated method stub
			Session session=SessionManager.getSession();
		  	   String hql="select s.department,s.grade from StudentInfo s where trim(s.sno)=?";
		  	   Query query=session.createQuery(hql);
		       query.setString(0,sno);
		       List list=query.list();
		       String result=null;
		       if(list!=null)
		       {
		      	 if(list.size()!=0)
		      	 {
		      		 Object[] obj=(Object[]) list.get(0);
		      		 String d=obj[0].toString();
		      		 String g=obj[1].toString();
		      		 result=d+"#"+g;
		      	 }
		       }
		       session.clear();
		       session.close();      
		       return result;
		}

}
