package com.jll.file;

import java.io.*;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.jspsmart.upload.SmartUpload;

public class FileDownloadServlet extends HttpServlet {
	 private ServletConfig config;
	 final public void init(ServletConfig config) throws ServletException {
	  this.config = config;
	 }
	 
	@Override	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		  String temp_p =req.getParameter("filename");
		  byte[] temp_t=temp_p.getBytes("ISO8859_1");
		  String fileName=new String(temp_t,"utf-8"); 
		  resp.setContentType("application/x-msdownload; charset=utf-8");
		  resp.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");
		  OutputStream out = new DataOutputStream(resp.getOutputStream());
		  
		  
	 	  String dst = fileName;
		  
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(URI.create(dst), conf);
			FSDataInputStream hdfsInStream = fs.open(new Path(dst));
			
			
			
	//		OutputStream out = new FileOutputStream("d:/hdfs.java");
			byte[] ioBuffer = new byte[1024];
			int readLen = hdfsInStream.read(ioBuffer);
			while (-1 != readLen) {
				out.write(ioBuffer, 0, readLen);
				readLen = hdfsInStream.read(ioBuffer);
			}
			out.close();
			hdfsInStream.close();
			fs.close();
		
		
		   
	}
}
