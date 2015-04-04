package com.jll.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.file.dal.OperaHDFS;

public class ShareFileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShareFileServlet() {
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
	
		 String dst =new String(request.getParameter("directory").getBytes("iso-8859-1"),"utf-8");
		 String user=new String(request.getParameter("shareuser").getBytes("iso-8859-1"),"utf-8");
			//Ϊ�û������ļ���
			  OperaHDFS openHdfs=new OperaHDFS();
			     try {
					if(openHdfs.ShareFile(dst,user))
						   respAlert(response,"����ɹ���");
					       	response.sendRedirect("../mainList.jsp");									
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

		     respAlert(response,"�����ļ��ɹ���");
		
	}
	private void  respAlert(HttpServletResponse response,String   str) 
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8"); //ת��
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
