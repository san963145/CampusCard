package com.sys.card.daoImpl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sys.card.dao.StudentIdentityDao;
import com.sys.card.util.Test;

public class StudentIdentityDaoImpl implements StudentIdentityDao{

	private static String driverClass="";
	private static String jdbcURL="";
	private static String name="";
	private static String pwd="";
	
	public static void init()
	{
		Properties prop=new Properties();
	    String path = Test.basePath;
	    InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driverClass = prop.getProperty("driverClass");
	    jdbcURL = prop.getProperty("jdbcURL");
	    name = prop.getProperty("name");
	    pwd = prop.getProperty("pwd");
	}
	
	@Override
	public List getSchoolCostData() {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(jdbcURL, name, pwd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		try
		{
			String sql="select p.provincename provinceName,round(avg(temp.ave),2) aveCost from (select s.identity,t.average ave from student_identity s,student_point t where trim(s.sno)=trim(t.sno) and t.count>10)temp,"
	    	 		+ "province_list p where substr(temp.identity,0,2)=p.provinceid group by p.provincename order by aveCost desc";
			
		//	String sql="select p.provincename,round(avg(ave),2) ave from (select s.identity,t.average ave from student_identity s,student_point t where trim(s.sno)=trim(t.sno) and t.sno not in (select sno from point_exception e where e.info <> '留级' ))temp,province_list p "
		//			+ "where substr(temp.identity,0,2)=p.provinceid group by p.provincename order by ave desc";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List list=new ArrayList();
			while(rs.next())
			{
				String provinceName=rs.getString("provinceName");
				String aveCost=rs.getString("aveCost");
				Object[] obj=new Object[2];
				obj[0]=provinceName;
				obj[1]=aveCost;
				list.add(obj);
			}	
			return list;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return new ArrayList();
		 
	}
    

	@Override
	public List getSchoolCountData() {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(jdbcURL, name, pwd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		try
		{
			String sql="select p.provincename,count(*) count from (select s.identity  from student_identity s,student_point t "
					  + "where trim(s.sno)=trim(t.sno))temp,province_list p where substr(temp.identity,0,2)=p.provinceid group by p.provincename order by count desc";
		    PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List list=new ArrayList();
			while(rs.next())
			{
				String provinceName=rs.getString("provinceName");
				String aveCost=rs.getString("count");
				Object[] obj=new Object[2];
				obj[0]=provinceName;
				obj[1]=aveCost;
				list.add(obj);
			}	
			return list;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return new ArrayList();
	}
	
	public static void main(String[] args)
    {
    	List list=new StudentIdentityDaoImpl().getCountData("全部","全部","全部","全部");
    	for(int i=0;i<list.size();i++)
    	{
    		Object[] obj=(Object[]) list.get(i);
    		System.out.println(obj[0]+" "+obj[1]);
    	}
    }

	@Override
	public List getCountData(String department, String major, String grade,
			String sex) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(jdbcURL, name, pwd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		String d="";
		String m="";
		String g="";
		String x="";
		if(!department.equals("全部"))
		{
			d=" and e.department='"+department+"'";
		}
		if(!major.equals("全部"))
		{
			m=" and substr(e.classno,3,4)='"+major+"'";
		}
		if(!grade.equals("全部"))
		{
			g=" and substr(e.classno,0,2)='"+grade.substring(2)+"'";
		}
		if(!sex.equals("全部"))
		{
			x=" and e.sex='"+sex+"'";
		}
		try
		{
			String sql="select p.provincename provinceName,count(*) count from (select s.identity from student_identity s,(select trim(sno) sno from student_info e where 1=1 "+d+m+g+x+") "+
					   "temp1 "+
                       "where s.sno=temp1.sno) temp2,province_list p "+
                       "where substr(temp2.identity,0,2)=p.provinceid group by p.provincename order by count desc";
		    
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List list=new ArrayList();
			while(rs.next())
			{
				String provinceName=rs.getString("provinceName");
				String aveCost=rs.getString("count");
				Object[] obj=new Object[2];
				obj[0]=provinceName;
				obj[1]=aveCost;
				list.add(obj);
			}	
			return list;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return new ArrayList();
	}

	@Override
	public List getCostData(String department, String major, String grade,
			String sex) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(jdbcURL, name, pwd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		String d="";
		String m="";
		String g="";
		String x="";
		if(!department.equals("全部"))
		{
			d=" and e.department='"+department+"'";
		}
		if(!major.equals("全部"))
		{
			m=" and substr(e.classno,3,4)='"+major+"'";
		}
		if(!grade.equals("全部"))
		{
			g=" and substr(e.classno,0,2)='"+grade.substring(2)+"'";
		}
		if(!sex.equals("全部"))
		{
			x=" and e.sex='"+sex+"'";
		}
		try
		{
			String sql="select p.provincename provinceName,round(avg(temp2.ave),2) aveCost from "+
                       "(select s.identity ,temp1.ave from student_identity s,(select t.sno,t.average ave from student_point t,(select trim(sno) sno from student_info e where 1=1 "+d+m+g+x+") st where t.sno=st.sno and t.count>10) temp1 "+
                       "where s.sno=temp1.sno) temp2, province_list p "+
                       "where substr(temp2.identity,0,2)=p.provinceid group by p.provincename order by aveCost desc";

			PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List list=new ArrayList();
			while(rs.next())
			{
				String provinceName=rs.getString("provinceName");
				String aveCost=rs.getString("aveCost");
				Object[] obj=new Object[2];
				obj[0]=provinceName;
				obj[1]=aveCost;
				list.add(obj);
			}	
			return list;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return new ArrayList();
	}
}
