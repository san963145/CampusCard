package com.sys.card.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {
	
	private static String driverClass="oracle.jdbc.driver.OracleDriver";
	private static String jdbcURL="jdbc:oracle:thin:@172.20.100.3:1529:ORCLSCHOOL";
	private static String name="StuSysUser";
	private static String pwd="StuSys12345678";
	
	
	private static DataSource   datasource;
    private BasicDataSource ds;

    private DataSource() throws Exception{
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(jdbcURL);
        ds.setUsername(name);
        ds.setPassword(pwd);
             
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    public static DataSource getInstance(){
        if (datasource == null) {
            try {
				datasource = new DataSource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() {
    	   	
        try {
			return this.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

}
