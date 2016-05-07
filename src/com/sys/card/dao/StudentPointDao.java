package com.sys.card.dao;

import java.util.List;


import com.sys.card.bean.StudentPoint;


public interface StudentPointDao {
	
	public List selectSchoolAvg();
	/**
	 *  按学号查询
	 */
	 public StudentPoint selectByPerson(String sno);	 
	 /**
	  *  按学号查询姓名
	  */
	 public String selectNameBySno(String sno);	 
	 /**
	  *  按学院、年级查询
	  */
     public List select(String department,int grade);
     /**
	  *  按学院、专业、年级查询（消费记录）
	  */
     public List selectByDMG(String department,String major,int grade);
     /**
	  *  按学院、专业、年级查询（消费预测）
	  */
     public List predictByDMG(String department,String major,int grade);
     /**
	  *  按学院、专业、年级查询（消费评级）
	  */
     public List rateByDMG(String department,String major,int grade);
     /**
      *  按学院查询
      */
     public List select(String department);    
     /**
      *  查询全部学生
      */
     public List select();   
     /**
      *  更新学生评分
      */
     public void updatePoint(String sno,double point);  
	 /**
      *  统计绘图 （查询全校平均消费数据）
      */
     public List selectDrawDataBySchool();
     /**
      *  统计绘图 （根据性别查询全校平均消费数据）
      */
     public List selectDrawDataBySchool(String gender);
     /**
      *  统计绘图 （学院间消费对比数据）
      */
     public List selectDrawByDepartmentComparison();
     /**
      *  统计绘图 （学院间按 性别 消费对比数据）
      */
     public List selectDrawByDepartmentComparison(String gender);
     /**
      *  统计绘图 （查询学院平均消费数据）
      */
     public List selectDrawDataByDepartment(String department);
     /**
      *  统计绘图 （根据性别查询学院平均消费数据）
      */
     public List selectDrawDataByDepartment(String department,String gender);

     /**
      *  统计绘图 （学院内年级间消费对比数据）
      */
     public List selectDrawByGradeComparison(String department);
     /**
      *  统计绘图 （年级间按 性别 消费对比数据）
      */
     public List selectDrawByGradeComparison(String department,String gender);
     /**
      *  统计绘图 （查询年级内平均消费数据）
      */
     public List selectDrawDataByGrade(String department,String grade);
     /**
      *  统计绘图 （根据性别查询年级内平均消费数据）
      */
     public List selectDrawDataByGrade(String department,String grade,String gender);
     /**
      *  助学金推荐等级划分 （根据学院、专业、年级获取所有学生的评分）
      */
     public List selectPointByDMG(String department,String major,int grade);
     public void formatDecimal();

}
