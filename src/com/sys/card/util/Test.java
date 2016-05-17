package com.sys.card.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Test {
	
	
	private static String driverClass="oracle.jdbc.driver.OracleDriver";
	private static String jdbcURL="jdbc:oracle:thin:@172.20.100.3:1529:ORCLSCHOOL";
	private static String name="StuSysUser";
	private static String pwd="StuSys12345678";
	
	public static String basePath=null;
		
	public static void read() throws Exception
	{
        Class.forName(driverClass);		
		Connection conn=DriverManager.getConnection(jdbcURL, name, pwd);
		File file=new File("D:\\data.csv");
		FileWriter w=new FileWriter(file);
		w.write("SNO,IDENTITY\r\n");
		try
		{
			PreparedStatement ps=conn.prepareStatement("select trim(sno) sno,trim(identity) identity from account where length(trim(identity))=18 and length(trim(sno))=8");
			ps.execute();
			ResultSet rs=ps.getResultSet();
			while(rs.next())
			{
				String sno=rs.getString("sno");
				String identity=rs.getString("identity");
				w.write(sno+","+identity+"\r\n");
			}	
			
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				w.flush();
				w.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	}
	private static void add() throws Exception
	{
		Class.forName(driverClass);
		Connection conn=DriverManager.getConnection(jdbcURL, name, pwd);
		File file=new File("D:\\data.csv");
		FileReader reader=new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(reader);
		br.readLine();
		String s=br.readLine();
		int n=0;
		while(s!=null)
		{					
			if(n>200)
			{
				conn=DriverManager.getConnection(jdbcURL, name, pwd);
				n=0;
			}
			conn.setAutoCommit(false);
		    try
		   {			
			PreparedStatement ps=conn.prepareStatement("insert into student_identity values(?,?)");
			String sno=s.split(",")[0];
			String identity=s.split(",")[1];
			ps.setString(1, sno);
			ps.setString(2, identity);
			ps.execute();
			conn.commit();
		   }  
		    catch(Exception e)
		     {

				conn.rollback();
		      }	
		     finally{
			   
		     }
		  s=br.readLine();
		  n++;
		  if(n>200)
		  {
			  conn.close();
		  }
		}   //while
		
	}
	
	
	
	public static void main(String[] args) throws Exception
    {
		read();
		add();
		
    }

}
