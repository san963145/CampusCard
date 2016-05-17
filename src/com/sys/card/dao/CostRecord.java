package com.sys.card.dao;

import java.util.List;

import com.sys.card.bean.StudentCostRecord;


public interface CostRecord {
	
	public List<StudentCostRecord> getBreakfastRecord(String sno,String department,int grade);	
	public List<StudentCostRecord> getLunchRecord(String sno,String department,int grade);	
	public List<StudentCostRecord> getSupperRecord(String sno,String department,int grade);
	public List<Double> getAverage(String sno);
}
