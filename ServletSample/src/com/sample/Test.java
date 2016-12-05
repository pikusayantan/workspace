package com.sample;

import java.io.*;  

import javax.servlet.*;  
import javax.servlet.http.HttpServlet;
  
public class Test extends HttpServlet{  
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
ServletConfig config=null;  
  
public void init(ServletConfig config){  
this.config=config;  
System.out.println("servlet is initialized");  
}  
  
public void service(ServletRequest req,ServletResponse res)  
throws IOException,ServletException{  
  
res.setContentType("text/html");  
  
PrintWriter out=res.getWriter();  
out.print("<html><body>");  
out.print("<b>hello simple HTTPservlet</b>");  
out.print("</body></html>");  
out.close();
  
}  
public void destroy(){System.out.println("servlet is destroyed");}  
public ServletConfig getServletConfig(){return config;}  
public String getServletInfo(){return "copyright 2007-1010";}  
  
}  