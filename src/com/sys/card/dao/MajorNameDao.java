package com.sys.card.dao;



public interface MajorNameDao {

	/**
 	 * 根据专业名获取专业号
 	 * 
 	 */
   public String getNumByName(String majorName);
   /**
	 * 根据学院、专业名获取专业号
	 * 
	 */
  public String getNumByName(String department,String majorName);
   /**
	 * 根据专业号获取专业名
	 * 
	 */
  public String getNameByNum(String majorNum);
}
