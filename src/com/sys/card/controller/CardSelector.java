package com.sys.card.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.StudentInfoDao;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.StudentInfoDaoImpl;
import com.sys.card.bean.User;

/**
 * Servlet implementation class CardSelector
 */
@WebServlet("/CardSelector")
public class CardSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StudentInfoDao studentInfoDao=new StudentInfoDaoImpl();
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardSelector() {
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
		response.setContentType("text/html");
		String department=URLDecoder.decode(request.getParameter("department"), "utf-8");
		List majorList=studentInfoDao.getMajorNumByDepartment(department);
		List gradeList=studentInfoDao.getGradesByDepartment(department);	
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getAuthority().equals("Instructor"))
		{
			gradeList.clear();
			String s=user.getGrade();
			String[] gs=s.split("#");
			for(int i=0;i<gs.length;i++)
			{
				gradeList.add(gs[i]);
			}
		}
		PrintWriter out=response.getWriter();
		String majors="";
		String grades="";
		Iterator it=majorList.iterator();
		while(it.hasNext())
		{
			String s=it.next().toString();
			s=majorNameDao.getNameByNum(s);
			majors+=s;
			majors+="#";
		}
		if(majors.length()>0)
		   majors=majors.substring(0,majors.length()-1);
		it=gradeList.iterator();
		while(it.hasNext())
		{	
			String s=it.next().toString();
			if(!s.equals("0"))
			{
			 grades+=s;
			 grades+="#";
			}
		}
		if(grades.length()>0)
		   grades=grades.substring(0,grades.length()-1);
		out.print(majors+"|"+grades);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
