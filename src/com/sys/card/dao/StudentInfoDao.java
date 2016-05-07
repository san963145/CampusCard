package com.sys.card.dao;

import java.util.List;

public interface StudentInfoDao {
	
	 public String selectDepartmentBySno(String sno);   //getѧԺ
	 public String selectMajorBySno(String sno);        //getרҵ
	 public List selectGMBySno(String sno);             //get�꼶���༶��
	 
	 public List getGradesByDepartment(String department);
	 public List getMajorNumByDepartment(String department);
	 /**
	     * �ж�ѧ���Ƿ���ѧԺ��Χ��
	     */
	 public String Check(String sno,String department);

	    /**
	     * �ж�ѧ���Ƿ���ѧԺ���꼶��Χ��
	     */
	 public String Check(String sno,String department,String grade);

}
