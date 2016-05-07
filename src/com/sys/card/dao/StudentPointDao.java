package com.sys.card.dao;

import java.util.List;


import com.sys.card.bean.StudentPoint;


public interface StudentPointDao {
	
	public List selectSchoolAvg();
	/**
	 *  ��ѧ�Ų�ѯ
	 */
	 public StudentPoint selectByPerson(String sno);	 
	 /**
	  *  ��ѧ�Ų�ѯ����
	  */
	 public String selectNameBySno(String sno);	 
	 /**
	  *  ��ѧԺ���꼶��ѯ
	  */
     public List select(String department,int grade);
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ�����Ѽ�¼��
	  */
     public List selectByDMG(String department,String major,int grade);
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ������Ԥ�⣩
	  */
     public List predictByDMG(String department,String major,int grade);
     /**
	  *  ��ѧԺ��רҵ���꼶��ѯ������������
	  */
     public List rateByDMG(String department,String major,int grade);
     /**
      *  ��ѧԺ��ѯ
      */
     public List select(String department);    
     /**
      *  ��ѯȫ��ѧ��
      */
     public List select();   
     /**
      *  ����ѧ������
      */
     public void updatePoint(String sno,double point);  
	 /**
      *  ͳ�ƻ�ͼ ����ѯȫУƽ���������ݣ�
      */
     public List selectDrawDataBySchool();
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯȫУƽ���������ݣ�
      */
     public List selectDrawDataBySchool(String gender);
     /**
      *  ͳ�ƻ�ͼ ��ѧԺ�����ѶԱ����ݣ�
      */
     public List selectDrawByDepartmentComparison();
     /**
      *  ͳ�ƻ�ͼ ��ѧԺ�䰴 �Ա� ���ѶԱ����ݣ�
      */
     public List selectDrawByDepartmentComparison(String gender);
     /**
      *  ͳ�ƻ�ͼ ����ѯѧԺƽ���������ݣ�
      */
     public List selectDrawDataByDepartment(String department);
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯѧԺƽ���������ݣ�
      */
     public List selectDrawDataByDepartment(String department,String gender);

     /**
      *  ͳ�ƻ�ͼ ��ѧԺ���꼶�����ѶԱ����ݣ�
      */
     public List selectDrawByGradeComparison(String department);
     /**
      *  ͳ�ƻ�ͼ ���꼶�䰴 �Ա� ���ѶԱ����ݣ�
      */
     public List selectDrawByGradeComparison(String department,String gender);
     /**
      *  ͳ�ƻ�ͼ ����ѯ�꼶��ƽ���������ݣ�
      */
     public List selectDrawDataByGrade(String department,String grade);
     /**
      *  ͳ�ƻ�ͼ �������Ա��ѯ�꼶��ƽ���������ݣ�
      */
     public List selectDrawDataByGrade(String department,String grade,String gender);
     /**
      *  ��ѧ���Ƽ��ȼ����� ������ѧԺ��רҵ���꼶��ȡ����ѧ�������֣�
      */
     public List selectPointByDMG(String department,String major,int grade);
     public void formatDecimal();

}
