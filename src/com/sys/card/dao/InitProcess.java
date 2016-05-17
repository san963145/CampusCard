package com.sys.card.dao;

public interface InitProcess {
	
	public void generateHistrjnTemp();
	public void generateBreakfastRecord();
	public void generateLunchfastRecord();
	public void generateSupperfastRecord();
    public void generateStudentPoint();
    
    //
    public void callInitProcess();
}
