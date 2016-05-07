package com.sys.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sys.card.dao.StudentPointDao;
import com.sys.card.daoImpl.StudentPointDaoImpl;
import com.sys.card.bean.User;

/**
 * Servlet implementation class DrawComparison
 */
@WebServlet("/DrawComparison")
public class DrawComparison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StudentPointDao studentPointDao=new StudentPointDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawComparison() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null)   //直接地址栏方式访问
		{
			request.getRequestDispatcher("pages/cardSystem/cardSystem.jsp").forward(request, response);
		}
		else
		{
		   if(user.getAuthority().equals("Admin"))
		  {
			List list1=studentPointDao.selectDrawByDepartmentComparison();
			List list2_1=studentPointDao.selectDrawByDepartmentComparison("男");
			List list2_2=studentPointDao.selectDrawByDepartmentComparison("女");
			request.setAttribute("flag","1");
			request.setAttribute("list1",list1);
			request.setAttribute("list2_1",list2_1);
			request.setAttribute("list2_2",list2_2);
			request.getRequestDispatcher("pages/cardSystem/resultsComparisonAdmin.jsp").forward(request, response);
		  }
		   else if(user.getAuthority().equals("Dean"))
		  {
			String department=user.getDepartment();
			List list1=studentPointDao.selectDrawByGradeComparison(department);
			List list2_1=studentPointDao.selectDrawByGradeComparison(department,"男");
			List list2_2=studentPointDao.selectDrawByGradeComparison(department,"女");
			request.setAttribute("flag","1");
			request.setAttribute("list1",list1);
			request.setAttribute("list2_1",list2_1);
			request.setAttribute("list2_2",list2_2);
			request.getRequestDispatcher("pages/cardSystem/resultsComparisonDean.jsp").forward(request, response);
		  }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
