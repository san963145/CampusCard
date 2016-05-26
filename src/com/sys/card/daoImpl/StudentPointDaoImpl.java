package com.sys.card.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.card.bean.StudentPoint;
import com.sys.card.dao.StudentPointDao;
import com.sys.card.dao.SessionManager;

public class StudentPointDaoImpl implements StudentPointDao{
	
	/**
     *  ����ȫУƽ����������
     */
    public List selectSchoolAvg()
    {
   	    Session session=SessionManager.getSession();
   	    String hql="select round(avg(s.average),2),round(avg(s.lunchAVG),2),round(avg(s.supperAVG),2) from StudentPoint s";
   	    Query query=session.createQuery(hql);
        List result=null;
        result=query.list();
        session.clear();
        session.close();
        return result;
    }
	/**
	 *  ��ѧ�Ų�ѯ
	 */
	 public StudentPoint selectByPerson(String sno)
	 {
		 Session session=SessionManager.getSession();
    	 String hql="from StudentPoint where trim(sno)=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,sno);
         StudentPoint studentPoint=null;
         List<StudentPoint>list=query.list();
         if(list!=null)
         {
        	 if(list.size()!=0)
        	 {
        		 studentPoint=list.get(0);
        	 }
         }
         session.clear();
         session.close();
         return studentPoint;
	 }
	 
	 /**
	  *  ��ѧ�Ų�ѯ����
	  */
	 public  String selectNameBySno(String sno)
	 {
		 Session session=SessionManager.getSession();
    	 String hql="select t.sname from StudentInfo t where trim(sno)=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,sno);
         List list=query.list();
         String result=null;
         if(list!=null)
         {
        	 Iterator it=list.iterator();
        	 if(it.hasNext())
        	 {
        		 String s=(String) it.next();
        		 if(s!=null)
        		 result=s;
        	 }
         }
         session.clear();
         session.close();
         return result;
	 }
	 
	 /**
	  *  ��ѧԺ���꼶��ѯ
	  */
     public  List select(String department,int grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,s.gender,s.total,s.count,s.average,s.lunchTTL,s.lunchCNT,s.lunchAVG,s.supperTTL,s.supperCNT,s.supperAVG,s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and t.grade=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setInteger(1,grade);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ�����Ѽ�¼��
	  */
     public  List selectByDMG(String department,String major,int grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,s.gender,s.total,s.count,s.average,s.lunchTTL,s.lunchCNT,s.lunchAVG,s.supperTTL,s.supperCNT,s.supperAVG,s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and substr(t.classNo,3,4) = ? and t.grade=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setString(1,major);
         query.setInteger(2,grade);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ������Ԥ�⣩
	  */
     public  List predictByDMG(String department,String major,int grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,d.department,d.grade,s.total from StudentPoint s,StudentInfo t,DepartmentPoint d where trim(s.sno)=trim(t.sno) and t.department=d.department and t.grade=d.grade and d.department=? and substr(t.classNo,3,4) = ? and t.grade=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setString(1,major);
         query.setInteger(2,grade);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ������������
	  */
     public  List rateByDMG(String department,String major,int grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and substr(t.classNo,3,4) = ? and t.grade=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setString(1,major);
         query.setInteger(2,grade);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     /**
      *  ��ѧԺ��ѯ
      */
     public  List select(String department)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,s.gender,s.total,s.count,s.average,s.lunchTTL,s.lunchCNT,s.lunchAVG,s.supperTTL,s.supperCNT,s.supperAVG,s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     
     /**
      *  ��ѯȫ��ѧ��
      */
     public  List select()
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.sno,t.sname,s.gender,s.total,s.count,s.average,s.lunchTTL,s.lunchCNT,s.lunchAVG,s.supperTTL,s.supperCNT,s.supperAVG,s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno)";
    	 Query query=session.createQuery(hql);
         List result=null;
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     
     /**
      *  ����ѧ������
      */
     public  void updatePoint(String sno,double point)
     {
    	 Session session=SessionManager.getSession();
		 Transaction transaction=session.beginTransaction();
    	 String hql="update StudentPoint s set s.point=? where trim(s.sno)=?";
    	 Query query=session.createQuery(hql);
    	 query.setDouble(0,point);
    	 query.setString(1,sno);
    	 query.executeUpdate();
		 transaction.commit();    
		 session.clear();
		 session.close();
     }  
     
	 /**
      *  ͳ�ƻ�ͼ ����ѯȫУƽ���������ݣ�
      */
     public  List selectDrawDataBySchool()
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2),round(avg(s.lunchCNT),2),round(avg(s.supperCNT),2),round(avg(s.count),2) from StudentPoint s";
    	 Query query=session.createQuery(hql);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯȫУƽ���������ݣ�
      */
     public  List selectDrawDataBySchool(String gender)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2) from StudentPoint s where s.gender=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,gender);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ ��ѧԺ�����ѶԱ����ݣ�
      */
     public  List selectDrawByDepartmentComparison()
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select t.department,round(avg(s.lunchAVG),2),round(avg(s.supperAVG),2),round(avg(s.average),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) group by t.department order by t.department";
    	 Query query=session.createQuery(hql);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
         
     }
     /**
      *  ͳ�ƻ�ͼ ��ѧԺ�䰴 �Ա� ���ѶԱ����ݣ�
      */
     public  List selectDrawByDepartmentComparison(String gender)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select t.department,round(avg(s.average),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and s.gender=? group by t.department order by t.department";
    	 Query query=session.createQuery(hql);
         query.setString(0,gender);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ ����ѯѧԺƽ���������ݣ�
      */
     public  List selectDrawDataByDepartment(String department)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2),round(avg(s.lunchCNT),2),round(avg(s.supperCNT),2),round(avg(s.count),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? ";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯѧԺƽ���������ݣ�
      */
     public  List selectDrawDataByDepartment(String department,String gender)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and s.gender=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setString(1,gender);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ ��ѧԺ���꼶�����ѶԱ����ݣ�
      */
     public  List selectDrawByGradeComparison(String department)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select t.grade,round(avg(s.lunchAVG),2),round(avg(s.supperAVG),2),round(avg(s.average),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? group by t.grade order by t.grade";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ ���꼶�䰴 �Ա� ���ѶԱ����ݣ�
      */
     public  List selectDrawByGradeComparison(String department,String gender)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select t.grade,round(avg(s.lunchAVG),2),round(avg(s.supperAVG),2),round(avg(s.average),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and s.gender=? group by t.grade order by t.grade";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setString(1,gender);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ ����ѯ�꼶��ƽ���������ݣ�
      */
     public  List selectDrawDataByGrade(String department,String grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2),round(avg(s.lunchCNT),2),round(avg(s.supperCNT),2),round(avg(s.count),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and t.grade=? ";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setInteger(1,Integer.parseInt(grade));
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯ�꼶��ƽ���������ݣ�
      */
     public  List selectDrawDataByGrade(String department,String grade,String gender)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select round(avg(s.lunchTTL),2),round(avg(s.supperTTL),2),round(avg(s.total),2) from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and t.grade=? and s.gender=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,department);
         query.setInteger(1,Integer.parseInt(grade));
         query.setString(2,gender);
         List result=query.list();
         session.clear();
         session.close();
         if(result!=null)
        	 if(result.size()>0)
        	 {
        		 Object[] obj=(Object[]) result.get(0);
        		 if(obj[0]==null)
        			 result.clear();
        	 }
         return result;
     }
     /**
      *  ��ѧ���Ƽ��ȼ����� ������ѧԺ��רҵ���꼶��ȡ����ѧ�������֣�
      */
     public  List selectPointByDMG(String department,String major,int grade)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="select s.point from StudentPoint s,StudentInfo t where trim(s.sno)=trim(t.sno) and t.department=? and substr(t.classNo,3,4)=? and t.grade=? order by s.point desc";
    	 Query query=session.createQuery(hql);
         List result=null;
         query.setString(0,department);
         query.setString(1,major);
         query.setInteger(2,grade);
         result=query.list();
         session.clear();
         session.close();
         return result;
     }
     /**
      *  ��ʽ��StudentPoint��
      */
     public  void formatDecimal()
     {
    	 Session session=SessionManager.getSession();
  		 Transaction transaction=session.beginTransaction();
    	 String hql="update StudentPoint s set s.average=round(s.average,2),s.lunchAVG=round(s.lunchAVG,2),s.supperAVG=round(s.supperAVG,2)";
    	 Query query=session.createQuery(hql);
  		 query.executeUpdate();
  		 hql="update StudentPoint s set s.point=round(s.point,2)";
  		 query=session.createQuery(hql);
 		 query.executeUpdate();
  		 transaction.commit(); 
  		 session.clear();
  		 session.close();
     }
	@Override
	public void clean() {
		// TODO Auto-generated method stub
		 Session session=SessionManager.getSession();
 		 Transaction transaction=session.beginTransaction();
   	     String hql="delete from StudentPoint t where t.point is null";
   	     Query query=session.createQuery(hql);
 		 query=session.createQuery(hql);
		 query.executeUpdate();
 		 transaction.commit(); 
 		 session.clear();
 		 session.close();
	}

}
