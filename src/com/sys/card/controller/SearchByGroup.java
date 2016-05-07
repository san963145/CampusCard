package com.sys.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.StudentPointDao;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.StudentPointDaoImpl;

/**
 * ����ѧԺ���꼶��ѯ����
 */
@WebServlet("/SearchByGroup")
public class SearchByGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();
	private static StudentPointDao studentPointDao=new StudentPointDaoImpl();   
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByGroup() {
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
		major=majorNameDao.getNumByName(department,major);
		List list=null;
	    if(department.equals("ȫ��"))
	    {
	    	list=studentPointDao.select();
	    }
	    else if(grade.equals("ȫ��"))
	    {
	    	list=studentPointDao.select(department);
	    }
	    else
	    {
	    	list=studentPointDao.selectByDMG(department,major,Integer.parseInt(grade));
	    }
	    request.setAttribute("list",list);
	    request.setAttribute("result", "1");
	    request.setAttribute("department",department);
	    major=majorNameDao.getNameByNum(major);
	    request.setAttribute("major",major);
	    request.setAttribute("grade",grade);
    	request.getRequestDispatcher("pages/cardSystem/searchByGroup.jsp").forward(request, response);
	}

}
