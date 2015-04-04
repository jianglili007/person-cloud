package com.jll.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.file.dal.*;
import com.file.model.*;

public class NewFolderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewFolderServlet() {
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

	   doPost(request, response);
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
		 String dst =new String((request.getParameter("directory")+request.getParameter("file")).getBytes("iso-8859-1"),"utf-8");
		//为用户创建文件夹
		  OperaHDFS openHdfs=new OperaHDFS();
		     try {
				if(openHdfs.CreateDirectoryOnHDFS(dst))
				       	response.sendRedirect("../mainList.jsp");									
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

	     respAlert(response,"新建子目录成功！");
	    
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
	
	public void init() throws ServletException {
		// Put your code here
	}

}
