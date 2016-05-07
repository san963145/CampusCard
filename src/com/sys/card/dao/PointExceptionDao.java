package com.sys.card.dao;

import java.util.List;


public interface PointExceptionDao {
	

      public void add(String sno,String info);
      public void clear();
      public List selectAllStudents();
      public List selectMeanQ(String sno);
      public String selectInfoBySno(String sno);

}
