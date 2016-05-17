package com.sys.card.dao;

import java.util.List;

import com.sys.card.bean.CostTime;

public interface CostTimeDao {
	
	public List<CostTime> getBySno(String sno);
	public List<CostTime> getByDepartmentGrade(String department,int grade);
	public List<String> getMonths(String department,int grade);

}
