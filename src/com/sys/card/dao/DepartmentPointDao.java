package com.sys.card.dao;

import java.util.List;


import com.sys.card.bean.DepartmentPoint;
import com.sys.card.bean.User;


public interface DepartmentPointDao {
	
	/**
	 *  ���һ������
	 */
	 public  void add(DepartmentPoint departmentPoint);
	 
	 /**
	  *  �����������
	  */
	 public  void clear();
	 
	 /**
	  *  �����������
	  */
	 public  List getAllData();
	 
	 /**
	  *  ����Ȩ�޻������ѧԺ���б�
	  */
     public  List<String> getDepartmentsByUser(User user);
     /**
	  *  �������ѧԺ���б�
	  */
     public  List<String> getDepartments();
     /**
      *  ���ĳѧԺ�����꼶�б�
      */
	 public  List<Integer> getGrades(String department);
	 
	 /**
	  *  ��ȡѧԺdepartment���꼶grade���ֶ�column�����ֵ
	  */
     public  double getMax(String column,String department,int grade);
     
     /**
      *  ��ȡѧԺdepartment���꼶grade���ֶ�column��ƽ��ֵ
      */
     public  double getMean(String column,String department,int grade);     
     /**
      *  ��ȡѧԺdepartment���꼶grade���ֶ�column�ı�׼��
      */
     public  double getSD(String column,String department,int grade,double mean);
     
     /**
      *  ��ȡѧԺdepartment���꼶grade���ֶ�column���ķ�λֵ
      */
     public  double[] getQ1Q2Q3(String column,String department,int grade);
     public  List getGenderComparison();
     public  void formatDecimal();

}
