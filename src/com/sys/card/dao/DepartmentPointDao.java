package com.sys.card.dao;

import java.util.List;


import com.sys.card.bean.DepartmentPoint;
import com.sys.card.bean.User;


public interface DepartmentPointDao {
	
	/**
	 *  添加一条数据
	 */
	 public  void add(DepartmentPoint departmentPoint);
	 
	 /**
	  *  清空所有数据
	  */
	 public  void clear();
	 
	 /**
	  *  获得所有数据
	  */
	 public  List getAllData();
	 
	 /**
	  *  根据权限获得所有学院名列表
	  */
     public  List<String> getDepartmentsByUser(User user);
     /**
	  *  获得所有学院名列表
	  */
     public  List<String> getDepartments();
     /**
      *  获得某学院所有年级列表
      */
	 public  List<Integer> getGrades(String department);
	 
	 /**
	  *  获取学院department、年级grade、字段column的最大值
	  */
     public  double getMax(String column,String department,int grade);
     
     /**
      *  获取学院department、年级grade、字段column的平均值
      */
     public  double getMean(String column,String department,int grade);     
     /**
      *  获取学院department、年级grade、字段column的标准差
      */
     public  double getSD(String column,String department,int grade,double mean);
     
     /**
      *  获取学院department、年级grade、字段column的四分位值
      */
     public  double[] getQ1Q2Q3(String column,String department,int grade);
     public  List getGenderComparison();
     public  void formatDecimal();

}
