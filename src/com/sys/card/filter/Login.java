package com.sys.card.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sys.card.bean.User;
import com.sys.card.daoImpl.StudentIdentityDaoImpl;
import com.sys.card.daoImpl.UserDaoImpl;
import com.sys.card.util.Test;
import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;

/**
 * Servlet Filter implementation class Login
 */
@WebFilter(filterName="/Login",urlPatterns="/*")
public class Login implements Filter {

	private static HashSet<String>set=new HashSet<String>();
    /**
     * Default constructor. 
     */
    public Login() {
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
        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/"),req.getRequestURI().length());         
        HttpSession session=req.getSession();
        if("/".equals(requestURI)||("/index.jsp").equals(requestURI))
        {
        	String is_config = req.getServletContext().getRealPath("/client.properties");
            Cookie all_cookies[] = req.getCookies();
            Cookie myCookie;
            String decodedCookieValue = null;
            if (all_cookies != null) 
            {
                for(int i=0; i< all_cookies.length; i++) 
                {
                    myCookie = all_cookies[i];
                    if( myCookie.getName().equals("iPlanetDirectoryPro") ) 
                    {
                        decodedCookieValue = URLDecoder.decode(myCookie.getValue(),"GB2312");
                    }
                }
            }

        	IdentityFactory factory=null;
    		try {
    			factory = IdentityFactory.createFactory( is_config );
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	IdentityManager im = factory.getIdentityManager();    	
        	String curUser = "";
        	String authority="";    	
        	if (decodedCookieValue != null ) {
            	curUser = im.getCurrentUser(decodedCookieValue);
            }
        	if(curUser.length()==0)
        	{
        		String gotoURL = "http://stusys2.cqu.edu.cn/CampusCard/index.jsp";
        		String loginURL = im.getLoginURL() +"?goto=" +java.net.URLEncoder.encode(gotoURL,"UTF-8");
        		res.sendRedirect(loginURL);
        	}
        	User user=null;
        	if(curUser.length()!=0)     // 数据库中是否注册该用户
        	{
        		user=(User)session.getAttribute("user");
        		if(user==null)
        		{
        			user=new UserDaoImpl().select(curUser);
        			if(user==null)
        			{
        				 res.sendRedirect("http://i.cqu.edu.cn/deny.html");
        			}
        			else
        			{
        			   session.setAttribute("user",user);
        			   authority=user.getAuthority();
        			   session.setAttribute("authority", authority);
        			   session.setAttribute("curUser", curUser);
        			}
        		}
        		else
        		{
        			authority=user.getAuthority();
        			session.setAttribute("authority", authority);
        			session.setAttribute("curUser", curUser);
        		}
        	}
        }       
        if(!set.contains(requestURI))    // 判断 未登录访问系统
        {
    		User u=(User) session.getAttribute("user");
    		if(u==null)
    		{
    			res.sendRedirect("index.jsp");
    			return;
    		}             
        } 
        if(Test.basePath==null)
        {
         String path = req.getServletContext().getRealPath("/indexUrl.properties");
         Test.basePath=path;
         StudentIdentityDaoImpl.init();
        }
        String basePath=(String) session.getAttribute("basePath");
        if(basePath==null)
        {
        	String path = req.getContextPath();
        	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            session.setAttribute("basePath", basePath);
        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		set.add("/index.jsp");
		set.add("/");
		set.add("/adminLogin.jsp");
		set.add("/pages/admin/calculate.jsp");
		set.add("/pages/admin/calculateCard.jsp");
		set.add("/pages/admin/config.jsp");
		set.add("/calculate.jsp");
		set.add("/calculateCard.jsp");
		set.add("/config.jsp");
		set.add("/AdminLogin");
		set.add("/CalculateStatistics");
		set.add("/CheckApplication");
		set.add("/EnterSafeModel");
		set.add("/ExitSafeModel");
		set.add("/error.jsp");
		System.out.println(123);
	}

}
