package com.jll.file;

import java.io.*;
import java.net.URI;
import java.util.Date;


import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


import com.file.model.directory;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;





public class FileUploadServlet extends HttpServlet {

	 private ServletConfig config;

	 final public void init(ServletConfig config) throws ServletException {
	  this.config = config;
	 }


	 private String[] getfileAndName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String[] nameAndContent={"",""};
    
    	 SmartUpload mySmartUpload = new SmartUpload();
		  try {
		   mySmartUpload.initialize(config, request, response);
		   mySmartUpload.upload();
		   for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {
		    com.jspsmart.upload.File myfile = mySmartUpload.getFiles().getFile(i);
		    String fileName = myfile.getFileName();
		    String ContentString=myfile.getContentString();
		    nameAndContent[0]=fileName;		
		    nameAndContent[1]=ContentString;
		   }
		   
		  }catch (Exception e) {			  
		  }finally{			  
		  }					     
    	return  nameAndContent;
    			
    
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	       String strfail=""; 	   
		  String[] nameAndContent= getfileAndName(request,response);	    
	    try{
	       	request.getCharacterEncoding();
	    	 String  filename=nameAndContent[0]; 	 
	    	 String  fileContent=nameAndContent[1];   
		     String dst =request.getParameter("directory")+filename;			
		      System.out.println("dst:"+dst);
			  InputStream in = new  ByteArrayInputStream(fileContent.getBytes());		 
			  Configuration conf = new Configuration();  
			  FileSystem fs = FileSystem.get(URI.create(dst), conf);  
			  OutputStream out = fs.create(new Path(dst));
			  IOUtils.copyBytes(in, out, 4096, true);  
		   
		   
		   
	
		} catch (Exception e) {
			    strfail += e.getMessage();
			    response.setContentType("text/html; charset=UTF-8"); //转码
			    PrintWriter out = response.getWriter();
			    out.flush();
			    out.println("<script>alert(\"上传失败!"+strfail+"\");</script>");
			    out.println("history.back();");
			    out.flush();
			    out.close();

		} finally {
		
		
				response.setContentType("text/html; charset=UTF-8"); //转码
			    PrintWriter out = response.getWriter();
			    out.flush();
			    out.println("<script>alert('上传成功!');</script>");
			    out.println("<script>location.href='../mainList.jsp'</script>");
			    out.flush();
			    out.close();
				
			} 
		



	}
	
	
	


	
	public void destroy() {
	
		super.destroy();
	}

	
	
}
