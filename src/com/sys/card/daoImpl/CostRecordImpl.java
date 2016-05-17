package com.sys.card.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sys.card.bean.StudentCostRecord;
import com.sys.card.dao.CostRecord;
import com.sys.card.dao.DataSource;

public class CostRecordImpl implements CostRecord{

	@Override
	public List<StudentCostRecord> getBreakfastRecord(String sno,String department,int grade) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select r.logicdate, r.breakfast, d.average from (select t.logicdate,round(avg(t.breakfast),2) average from record_breakfast t,student_info s where t.sno=trim(s.sno) and s.department=? and s.grade=? group by t.logicdate) d,record_breakfast r where r.sno=? and r.logicdate=d.logicdate order by r.logicdate");
			ps.setString(1, department);
			ps.setInt(2, grade);
			ps.setString(3, sno);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<StudentCostRecord> list=new ArrayList<StudentCostRecord>();
			while(rs.next())
			{
				String date=rs.getString("logicdate");
				Double breakfast=rs.getDouble("breakfast");
				Double average=rs.getDouble("average");
				StudentCostRecord c=new StudentCostRecord();
				c.setLogicDate(date);
				c.setCost(breakfast);
				c.setAverage(average);
				list.add(c);
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
		return new ArrayList<StudentCostRecord>();
	}

	@Override
	public List<StudentCostRecord> getLunchRecord(String sno,String department,int grade) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select r.logicdate, r.lunch, d.average from (select t.logicdate,round(avg(t.lunch),2) average from record_lunch t,student_info s where t.sno=trim(s.sno) and s.department=? and s.grade=? group by t.logicdate) d,record_lunch r where r.sno=? and r.logicdate=d.logicdate order by r.logicdate");
			ps.setString(1, department);
			ps.setInt(2, grade);
			ps.setString(3, sno);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<StudentCostRecord> list=new ArrayList<StudentCostRecord>();
			while(rs.next())
			{
				String date=rs.getString("logicdate");
				Double lunch=rs.getDouble("lunch");
				Double average=rs.getDouble("average");
				StudentCostRecord c=new StudentCostRecord();
				c.setLogicDate(date);
				c.setCost(lunch);
				c.setAverage(average);
				list.add(c);
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
		return new ArrayList<StudentCostRecord>();
	}

	@Override
	public List<StudentCostRecord> getSupperRecord(String sno,String department,int grade) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select r.logicdate, r.supper, d.average from (select t.logicdate,round(avg(t.supper),2) average from record_supper t,student_info s where t.sno=trim(s.sno) and s.department=? and s.grade=? group by t.logicdate) d,record_supper r where r.sno=? and r.logicdate=d.logicdate order by r.logicdate");
			ps.setString(1, department);
			ps.setInt(2, grade);
			ps.setString(3, sno);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<StudentCostRecord> list=new ArrayList<StudentCostRecord>();
			while(rs.next())
			{
				String date=rs.getString("logicdate");
				Double supper=rs.getDouble("supper");
				Double average=rs.getDouble("average");
				StudentCostRecord c=new StudentCostRecord();
				c.setLogicDate(date);
				c.setCost(supper);
				c.setAverage(average);
				list.add(c);
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
		return new ArrayList<StudentCostRecord>();
	}
    
	public static void main(String[] args)
	{
		List<Double> list=new CostRecordImpl().getAverage("20126652");
	    for(int i=0;i<list.size();i++)
	    {
	    	Double s=list.get(i);
	    	System.out.println(s);
	    	
	    }
	}

	@Override
	public List<Double> getAverage(String sno) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		List<Double>list=new ArrayList<Double>();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select round(avg(breakfast),2) breakfast from record_breakfast r where r.sno=?");
			ps.setString(1, sno);
			ps.execute();
			ResultSet rs=ps.getResultSet();			
			if(rs.next())
			{
				Double breakfast=rs.getDouble("breakfast");				
				list.add(breakfast);
			}
			PreparedStatement ps2=conn.prepareStatement("select round(avg(lunch),2) lunch from record_lunch r where r.sno=?");
			ps2.setString(1, sno);
			ps2.execute();
			ResultSet rs2=ps2.getResultSet();			
			if(rs2.next())
			{
				Double lunch=rs2.getDouble("lunch");				
				list.add(lunch);
			}
			PreparedStatement ps3=conn.prepareStatement("select round(avg(supper),2) supper from record_supper r where r.sno=?");
			ps3.setString(1, sno);
			ps3.execute();
			ResultSet rs3=ps3.getResultSet();			
			if(rs3.next())
			{
				Double supper=rs3.getDouble("supper");				
				list.add(supper);
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
		return new ArrayList<Double>();
	}
	

}
