package com.sys.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.sys.card.bean.CostTime;
import com.sys.card.bean.StudentPoint;
import com.sys.card.bean.User;
import com.sys.card.dao.CostTimeDao;
import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.StudentInfoDao;
import com.sys.card.dao.StudentPointDao;
import com.sys.card.daoImpl.CostTimeDaoImpl;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.StudentInfoDaoImpl;
import com.sys.card.daoImpl.StudentPointDaoImpl;
import com.sys.card.util.ChartGenerator;

/**
 * Servlet implementation class HeatMap
 */
@WebServlet("/HeatMap")
public class HeatMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StudentPointDao studentPointDao=new StudentPointDaoImpl();   
	private static StudentInfoDao studentInfoDao=new StudentInfoDaoImpl(); 
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();
	private static CostTimeDao costTimeDao=new CostTimeDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeatMap() {
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
		String sno=request.getParameter("sno");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String accessFlag="1";
		if(user.getAuthority().equals("Dean"))
		   accessFlag=studentInfoDao.Check(sno, user.getDepartment());
		if(user.getAuthority().equals("Instructor"))
		   accessFlag=studentInfoDao.Check(sno, user.getDepartment(),user.getGrade());
		
		String deparmentGrade=studentInfoDao.getDepartmentGradeBySno(sno);
		if(deparmentGrade==null)
		{
			request.getRequestDispatcher("pages/cardSystem/searchByPerson.jsp").forward(request, response);
			return;						
		}
		String major=studentInfoDao.selectMajorBySno(sno);
		major=majorNameDao.getNameByNum(major);
		String department=deparmentGrade.split("#")[0];
		String g=deparmentGrade.split("#")[1];
		int grade=Integer.parseInt(g);
        
		List<CostTime> sList=costTimeDao.getBySno(sno);
		List<CostTime> gList=costTimeDao.getByDepartmentGrade(department, grade);
	    List<String> months=costTimeDao.getMonths(department, grade);
	    
	    String sData=ChartGenerator.generatorHeatMap(sList, months);
	    String gData=ChartGenerator.generatorHeatMap(gList, months);
	    String monthData=ChartGenerator.generatorMonths(months);
		StudentPoint studentPoint=null;
	    studentPoint=studentPointDao.selectByPerson(sno);
	    String sname=studentPointDao.selectNameBySno(sno);
	    request.setAttribute("department",department);
	    request.setAttribute("grade",g);
	    request.setAttribute("major",major);
	    request.setAttribute("studentPoint",studentPoint);
	    request.setAttribute("sData",sData);
	    request.setAttribute("gData",gData);
	    request.setAttribute("monthData",monthData);
	    request.setAttribute("sname",sname);
	    request.setAttribute("accessFlag", accessFlag);
	    request.setAttribute("result", "1");
    	request.getRequestDispatcher("pages/statistics/heatMap.jsp").forward(request, response);		
	}

}
