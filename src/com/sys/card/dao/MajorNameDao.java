package com.sys.card.dao;



public interface MajorNameDao {

	/**
 	 * ����רҵ����ȡרҵ��
 	 * 
 	 */
   public String getNumByName(String majorName);
   /**
	 * ����ѧԺ��רҵ����ȡרҵ��
	 * 
	 */
  public String getNumByName(String department,String majorName);
   /**
	 * ����רҵ�Ż�ȡרҵ��
	 * 
	 */
  public String getNameByNum(String majorNum);
}
