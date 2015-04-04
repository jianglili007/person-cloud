<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.file.dal.*" %>
<%@ page import="com.file.model.*" %>

<%
     if(session.getAttribute("username")==null)
        response.getWriter().println("<script>alert('请登录!');location.href='Login.jsp'</script>");   
     String user=session.getAttribute("username").toString();
     OperaHDFS openHdfs=new OperaHDFS();
     List<directory> listdir=openHdfs.GetHDFSDirectoryListAll("",user);
     List<document>  listdoc=openHdfs.GetHDFSDocumentListAll("",user);
     session.setAttribute("directory", "hdfs://10.15.8.188:9000//user/"+user+"/");
  
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My Cloud</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="images/style.css">
	
  </head>
<body>
<div id="wrapper">
  <%@ include file="header.jsp"%>
	<div id="page">
		<div id="content">				
		    <div id="direcoryNow">  
		    
		      </div>
		<div id="listdir">
             <p>文件夹</p>

<%

	for(int i=0; i<listdir.size(); i++){
		directory dir=listdir.get(i);
	
 %> 
         <div class="dir list"><span>
         <img src="images/file.png"  width="50" height="50" alt="1" />
                                      文件夹名： <%=dir.getName()%>   &nbsp;&nbsp;&nbsp;&nbsp;
                                     创建时间：  <%=dir.getTime()%>   &nbsp;&nbsp;&nbsp;&nbsp;
           <a href="mainList.jsp?directory=<%=dir.getName()+"/" %>">打开此文件夹 </a> &nbsp;&nbsp;&nbsp;&nbsp;                           
           <a href="DownLoad.jsp?directory=<%=dir.getName()+"/"%>">修改 </a>&nbsp;&nbsp;&nbsp;&nbsp;                           
           <a href="ShareFile.jsp?directory=<%=dir.getName()+"/"%>">分享 </a>
          </span></div>	
      <%   
            
		}
     %>

</div><!--listdir end-->
 
   			<div id="listdoc">
             <p>文件</p>

<%

	for(int i=0; i<listdoc.size(); i++){
		document doc=listdoc.get(i);
	
 %> 
         <div class="doc list"><span>
         <img src="images/document.png"  width="50" height="50" alt="1" />
                                      文件名： <%=doc.getFilename()%>   &nbsp;&nbsp;&nbsp;&nbsp;
                                      创建时间：  <%=doc.getTime() %>   &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="Rename.jsp?filename=<%=doc.getFilename()%>">修改 </a> &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="servlet/FileDownloadServlet?filename=<%=doc.getFilename() %>">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="ShareFile.jsp?directory=<%=doc.getFilename()%>">分享 </a>
          </span></div>	
      <%   
            
		}
     %>

</div><!--listdoc end-->


	
 

		
		
		
		
		

		</div><!--content end-->

		<br class="clearfix" />
	</div><!--page end-->
</div>
<div id="footer">
	Copyright (c) 2012 . All rights reserved. Design by <a href="#" rel="nofollow">MY Cloud</a>.
</div>
</body>
</html>



