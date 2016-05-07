package com.sys.card.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.card.bean.RatingResult;
import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.PointExceptionDao;
import com.sys.card.dao.StudentPointDao;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.PointExceptionDaoImpl;
import com.sys.card.daoImpl.StudentPointDaoImpl;

/**
 * Servlet implementation class GetRatingResult
 */
@WebServlet("/GetRatingResult")
public class GetRatingResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();
	private static StudentPointDao studentPointDao=new StudentPointDaoImpl();   
	private static PointExceptionDao pointExceptionDao=new PointExceptionDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetRatingResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String department=request.getParameter("department");
		String major=request.getParameter("major");
		String grade=request.getParameter("grade");
		String majorNum=majorNameDao.getNumByName(department,major);
		List sList=studentPointDao.rateByDMG(department, majorNum, Integer.parseInt(grade));
		List<RatingResult>resultList=new ArrayList<RatingResult>();
		if(sList.size()>0)
		{
		  Iterator it=sList.iterator();
		  while(it.hasNext())
		  {
			  Object[]obj=(Object[]) it.next();
			  RatingResult r=new RatingResult();
			  String sno=obj[0].toString();
			  r.setSno(sno);
			  r.setSname(obj[1].toString());
			  r.setDepartment(department);
			  r.setGrade(grade);
			  r.setMajor(major);
			  r.setPoint(obj[2].toString());
			  String info=pointExceptionDao.selectInfoBySno(sno);
			  r.setInfo(info);
			  resultList.add(r);			  
		  }
		}
		List pointList=studentPointDao.selectPointByDMG(department, majorNum, Integer.parseInt(grade));
		int i;
		int []countList=new int[20];
		for(i=0;i<20;i++)
		{
			countList[i]=0;
		}
		for(i=0;i<pointList.size();i++)
		{
			Double point=(Double) pointList.get(i);
			countList[(point.intValue()-40)/3]++;
		}
		request.setAttribute("department",department);
		request.setAttribute("major",major);
		request.setAttribute("grade",grade);
		request.setAttribute("countList",countList);
		request.setAttribute("resultList",resultList);
		request.getRequestDispatcher("pages/cardSystem/ratingsResults.jsp").forward(request, response);
	}
    public static void main(String[] args)
    {
    	System.out.println(new Double(95.7).intValue());
    }
}
