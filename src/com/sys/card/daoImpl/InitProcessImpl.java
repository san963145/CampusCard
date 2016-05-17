package com.sys.card.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sys.card.dao.DataSource;
import com.sys.card.dao.InitProcess;

public class InitProcessImpl implements InitProcess{

	@Override
	public void generateHistrjnTemp() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="drop table histrjn_temp;"
				+ " create table histrjn_temp as select to_char(t.jndatetime,'yyyy-mm-dd') logicdate,t.fromaccount,t.toaccount,to_char(t.jndatetime,'hh24:mi:ss') jntime,(0-t.tranamt)/100 tranamt from histrjn t";        		
	    try
	   {			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.execute();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
	}

	@Override
	public void generateBreakfastRecord() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="drop table record_breakfast;"
				+ "create table record_breakfast as select e.logicdate,a.sno,e.breakfast from (select s.logicdate,s.fromaccount,sum(s.tranamt) breakfast from (select r.logicdate,r.fromaccount,r.tranamt from mercacc_category m,(select * from histrjn_temp t where t.jntime>'06:30:00' and t.jntime<'10:20:00') r where m.account=r.toaccount and m.category=1) s group by s.logicdate,s.fromaccount) e,account_sno a where e.fromaccount=a.account and e.breakfast>0.5 and length(a.sno)=8 and substr(a.sno,0,1)='2'";        		
	    
		try
	   {			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.execute();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
		
	}

	@Override
	public void generateLunchfastRecord() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="drop table record_lunch;"
				+ "create table record_lunch as select e.logicdate,a.sno,e.lunch from (select s.logicdate,s.fromaccount,sum(s.tranamt) lunch from (select r.logicdate,r.fromaccount,r.tranamt from mercacc_category m,(select * from histrjn_temp t where t.jntime>'10:30:00' and t.jntime<'13:30:00') r where m.account=r.toaccount and m.category=1) s group by s.logicdate,s.fromaccount) e,account_sno a where e.fromaccount=a.account and e.lunch>1 and length(a.sno)=8 and substr(a.sno,0,1)='2'";        		
	    
		try
	   {			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.execute();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
		
	}

	@Override
	public void generateSupperfastRecord() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="drop table record_supper;"
				+ "create table record_supper as select e.logicdate,a.sno,e.supper from (select s.logicdate,s.fromaccount,sum(s.tranamt) supper from (select r.logicdate,r.fromaccount,r.tranamt from mercacc_category m,(select * from histrjn_temp t where t.jntime>'17:00:00' and t.jntime<'23:00:00') r where m.account=r.toaccount and m.category=1) s group by s.logicdate,s.fromaccount) e,account_sno a where e.fromaccount=a.account and e.supper>1 and length(a.sno)=8 and substr(a.sno,0,1)='2'";        		
	    
		try
	   {			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.execute();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
	}

	@Override
	public void generateStudentPoint() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="drop table student_point2;"
				+ "create table student_point2 as "
                +"select lunchtemp.sno sno,(lunchtemp.lunchcnt*lunchtemp.lunchavg+suppertemp.suppercnt*suppertemp.supperavg) total,(lunchtemp.lunchcnt+suppertemp.suppercnt) count,"
                +"round((lunchtemp.lunchcnt*lunchtemp.lunchavg+suppertemp.suppercnt*suppertemp.supperavg)/(lunchtemp.lunchcnt+suppertemp.suppercnt),2) average ,"
                +"lunchtemp.lunchcnt*lunchtemp.lunchavg lunchttl,lunchtemp.lunchcnt,lunchtemp.lunchavg,"
                +"suppertemp.suppercnt*suppertemp.supperavg supperttl,suppertemp.suppercnt,suppertemp.supperavg"
                +"from (select sno,round(avg(lunch),2) lunchavg,count(*) lunchcnt from record_lunch l group by l.sno) lunchtemp,"
                +"(select sno,round(avg(supper),2) supperavg,count(*) suppercnt from record_supper s group by s.sno) suppertemp where lunchtemp.sno=suppertemp.sno";        		
	    
		try
	   {			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.execute();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
	}

	@Override
	public void callInitProcess() {
		// TODO Auto-generated method stub
		Connection conn=DataSource.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="call initprocess()";       		
	    try
	   {			
	    CallableStatement ps=conn.prepareCall(sql);
		ps.executeUpdate();
		conn.commit();
	   }  
	    catch(Exception e)
	     {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }	
	     finally{
	    	 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	     }
	}

	public static void main(String []args)
	{
		new InitProcessImpl().callInitProcess();
	}
}
