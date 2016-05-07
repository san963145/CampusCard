package com.sys.card.dao;

import java.util.List;

public interface StudentIdentityDao {
	
	public List getSchoolCountData();
	public List getSchoolCostData();
	public List getCountData(String department,String major,String grade,String sex);
	public List getCostData(String department,String major,String grade,String sex);
}
