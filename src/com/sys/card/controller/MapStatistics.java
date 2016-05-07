package com.sys.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sys.card.bean.User;
import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.StudentIdentityDao;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.StudentIdentityDaoImpl;
import com.sys.card.util.ChartGenerator;

/**
 * Servlet implementation class MapStatistics
 */
@WebServlet("/MapStatistics")
public class MapStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapStatistics() {
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
		if(user.getAuthority().equals("Instructor"))
		{
			request.getRequestDispatcher("pages/statistics/map.jsp").forward(request, response);
		}
		request.getRequestDispatcher("pages/statistics/map.jsp").forward(request, response);
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
		String sex=request.getParameter("sex");
		if(!major.equals("全部"))
		{
		  major=majorNameDao.getNumByName(major);
		}
		if(!sex.equals("全部"))
		{
			if(sex.equals("男"))
			{
				sex="1";
			}
			else
			{
				sex="2";
			}
		}
		StudentIdentityDao studentIdentityDao=new StudentIdentityDaoImpl();
		List list1=studentIdentityDao.getCountData(department,major,grade,sex);		
		List list2=studentIdentityDao.getCostData(department,major,grade,sex);
		String map1=ChartGenerator.generatorMap("map1", "各省学生人数分布", "人数", list1);
		String map2=ChartGenerator.generatorMap("map2", "各省学生消费水平", "平均消费金额(￥)", list2);
		request.setAttribute("map1", map1);
		request.setAttribute("map2", map2);
		request.setAttribute("list2", list2);
		request.setAttribute("department",department);	
		major=majorNameDao.getNameByNum(major);
		request.setAttribute("major",major);
		request.setAttribute("grade",grade);
		if(!sex.equals("全部"))
		{
			if(sex.equals("1"))
			{
				sex="男";
			}
			else
			{
				sex="女";
			}
		}
		request.setAttribute("sex",sex);
		request.getRequestDispatcher("pages/statistics/map.jsp").forward(request, response);
		
	}

}
