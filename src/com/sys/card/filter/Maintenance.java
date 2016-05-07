package com.sys.card.filter;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class Maintenance
 */
@WebFilter(filterName="/Maintenance",urlPatterns="/*")
public class Maintenance implements Filter {
	
	private static HashSet<String>set=new HashSet<String>();
    /**
     * Default constructor. 
     */
    public Maintenance() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse res = (HttpServletResponse)response;
        ServletContext application=req.getServletContext();
        String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/",1),req.getRequestURI().length());       
        String flag=(String)application.getAttribute("flag");
		String flagCard=(String)application.getAttribute("flagCard");
		if(flag!=null||flagCard!=null)
		{
			
			if(!set.contains(requestURI))
	        {  
				res.sendRedirect("error.jsp");
				return;          
	        } 
			
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
		set.add("/error.jsp");
		set.add("/CheckApplication");
		set.add("/ExitSafeModel");
		set.add("/CalculateStatistics");
		set.add("/pages/admin/calculate.jsp");
		set.add("/pages/admin/calculateCard.jsp");
		set.add("/pages/admin/config.jsp");
	}

}
