package com.sys.card.dao;

import java.util.List;

public interface StudentInfoDao {
	
	 public String selectDepartmentBySno(String sno);   //get学院
	 public String selectMajorBySno(String sno);        //get专业
	 public List selectGMBySno(String sno);             //get年级、班级号
	 
	 public List getGradesByDepartment(String department);
	 public List getMajorNumByDepartment(String department);
	 /**
	     * 判断学号是否在学院范围内
	     */
	 public String Check(String sno,String department);

	    /**
	     * 判断学号是否在学院、年级范围内
	     */
	 public String Check(String sno,String department,String grade);

}
