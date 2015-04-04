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
import com.file.dal.*;
import com.file.model.*;
public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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

		String userName=request.getParameter("username");
		userName=new String(userName.getBytes("iso-8859-1"),"utf-8");
		
		String Password=request.getParameter("password");		
		Password=new String(Password.getBytes("iso-8859-1"),"utf-8");
		
		String Password2=request.getParameter("password2");		
		Password2=new String(Password2.getBytes("iso-8859-1"),"utf-8");			
		
		String Email=request.getParameter("email");
		Email=new String(Email.getBytes("iso-8859-1"),"utf-8");	

	
				
			if(!(userName.equals("")||Password.equals("")))
			{
				if(Password.equals(Password2))
				{
					 User user=new User();
					 user.setUsername(userName);
					 user.setPassword(Password);
					 user.setEmail(Email);
					 userDAL  userdal=new userDAL();
					
					if( userdal.findUser(user.getUsername())!=null)
					{
						response.setContentType("text/html; charset=UTF-8"); //转码
					    PrintWriter out = response.getWriter();
					    out.flush();
					    out.println("<script>");
					    out.println("alert('该用户名已被注册，请重新选择用户名');");
					    out.println("history.back();");
					    out.println("</script>");
					}
					else
					{
						int ret=userdal.insert(user);
						if(ret!=0)
						{
							//为用户创建文件夹
							  OperaHDFS openHdfs=new OperaHDFS();
							     try {
									if(openHdfs.CreateUserDirectoryOnHDFS(userName))
									       	response.sendRedirect("../Login.jsp");									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
						else
						{
							response.setContentType("text/html; charset=UTF-8"); //转码
						    PrintWriter out = response.getWriter();
						    out.flush();
						    out.println("<script>");
						    out.println("alert('注册用户失败，请重新注册！');");
						    out.println("history.back();");
						    out.println("</script>");
						}
					}
					
					
				}
				else
				{
					response.setContentType("text/html; charset=UTF-8"); //转码
				    PrintWriter out = response.getWriter();
				    out.flush();
				    out.println("<script>");
				    out.println("alert('两次密码不一致，请重新输入');");
				    out.println("history.back();");
				    out.println("</script>");
				}
			}
			else
			{
				response.setContentType("text/html; charset=UTF-8"); //转码
			    PrintWriter out = response.getWriter();
			    out.flush();
			    out.println("<script>");
			    out.println("alert('用户名和密码不能为空！');");
			    out.println("history.back();");
			    out.println("</script>");
			}			
		
	
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
