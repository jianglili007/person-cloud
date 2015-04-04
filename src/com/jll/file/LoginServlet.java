package com.jll.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.file.dal.userDAL;
import com.file.model.User;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName=request.getParameter("userName");
		userName=new String(userName.getBytes("iso-8859-1"),"utf-8");
		
		String Password=request.getParameter("passWord");
		Password=new String(Password.getBytes("iso-8859-1"),"utf-8");		
		
		HttpSession ImgSession=request.getSession();
		String iCode=(String)ImgSession.getAttribute("code");
	
			if(!(userName.equals("")||Password.equals("")))
			{  

			   userDAL  userdal=new userDAL();
			   User  user=userdal.findUser(userName.trim());
			   if(user!=null){
					
				    if(user.getPassword().trim().equals(Password))
					{
						
							HttpSession session=request.getSession();
							session.setAttribute("username", userName);								
							response.sendRedirect("../mainList.jsp");
						
					}
					else
						respAlert(response,"密码输入错误，请重新输入！");
				}
				else		
					respAlert(response,"该用户尚未注册，请先注册");
			}
			else  		
				respAlert(response,"用户名和密码不能为空！");
					
		


		
	}
	private void  respAlert(HttpServletResponse response,String   str) 
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8"); //转码
	    PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>");
	    out.println("alert(\""+str+"\");");
	    out.println("history.back();");
	    out.println("</script>");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
