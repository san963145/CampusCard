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

import com.sys.card.dao.MajorNameDao;
import com.sys.card.dao.StudentInfoDao;
import com.sys.card.daoImpl.MajorNameDaoImpl;
import com.sys.card.daoImpl.StudentInfoDaoImpl;


/**
 * Servlet implementation class SelectorMajor
 */
@WebServlet("/SelectorMajor")
public class SelectorMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MajorNameDao majorNameDao=new MajorNameDaoImpl();
	private static StudentInfoDao studentInfoDao=new StudentInfoDaoImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectorMajor() {
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
		List list=studentInfoDao.getMajorNumByDepartment(department);
		PrintWriter out=response.getWriter();
		String majors="";
		Iterator it=list.iterator();
		while(it.hasNext())
		{
			String s=it.next().toString();
			s=majorNameDao.getNameByNum(s);
			majors+=s;
			majors+="#";
		}
		if(majors.length()>0)
			majors=majors.substring(0,majors.length()-1);		
		out.print(majors);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
