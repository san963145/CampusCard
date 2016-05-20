package com.sys.card.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sys.card.bean.CostTime;
import com.sys.card.dao.CostTimeDao;
import com.sys.card.dao.DataSource;
import com.sys.card.util.ChartGenerator;

public class CostTimeDaoImpl implements CostTimeDao{

	@Override
	public List<CostTime> getBySno(String sno) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select t.months,t.hours,count from cost_time t where t.sno=? order by t.months,t.hours");
			ps.setString(1, sno);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<CostTime> list=new ArrayList<CostTime>();
			while(rs.next())
			{
				String months=rs.getString("months");
				String hours=rs.getString("hours");
				int count=rs.getInt("count");
				CostTime c=new CostTime();
				c.setMonths(months);
				c.setHours(hours);
				c.setCount(count);
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
		return new ArrayList<CostTime>();
	}

	@Override
	public List<CostTime> getByDepartmentGrade(String department, int grade) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select t.months,t.hours,round(sum(count)/(select count(*) from student_info info where info.department=? and info.grade=?),0) count from cost_time t,student_info s where t.sno=trim(s.sno) and s.department=? and s.grade=? group by  t.months,t.hours order by t.months,t.hours");
			ps.setString(1, department);
			ps.setInt(2, grade);
			ps.setString(3, department);
			ps.setInt(4, grade);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<CostTime> list=new ArrayList<CostTime>();
			while(rs.next())
			{
				String months=rs.getString("months");
				String hours=rs.getString("hours");
				int count=rs.getInt("count");
				CostTime c=new CostTime();
				c.setMonths(months);
				c.setHours(hours);
				c.setCount(count);
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
		return new ArrayList<CostTime>();
	}
    
	public static void main(String[] args)
	{
		List<CostTime> list=new CostTimeDaoImpl().getByDepartmentGrade("计算机学院", 2012);
		List<String> months=new CostTimeDaoImpl().getMonths("计算机学院", 2012);
        System.out.println(months.get(0));
			String s=ChartGenerator.generatorHeatMap(list, months);
			System.out.println(s);

			
	}

	@Override
	public List<String> getMonths(String department, int grade) {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select months from cost_time t,student_info s where t.sno=trim(s.sno) and s.department=? and s.grade=? group by months order by months");
			ps.setString(1, department);
			ps.setInt(2, grade);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			List<String> list=new ArrayList<String>();
			while(rs.next())
			{
				String months=rs.getString("months");
				list.add(months);
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
		return new ArrayList<String>();
	}
}
