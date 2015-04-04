<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
         <form action="servlet/FileUploadServlet?directory=<%=request.getParameter("directory")%>" method="POST"
			enctype="multipart/form-data">
		
			 <a href="mainList.jsp?directory=<%=new String(request.getParameter("directory").getBytes("iso-8859-1"),"utf-8") %>">返回上级</a>  </br> 
		                    当前所在目录 ：  <%=new String(request.getParameter("directory").getBytes("iso-8859-1"),"utf-8")%></BR> 
		   
				发送文件:<input type="file" name="file" size="40"> 
                  <input  align="left" type="submit" value="开始发送" /> 
                  <input align="left" type="reset" value="重 设"/>
				
			
		</form>
	
			<br class="clearfix" />
		</div>
		

		<br class="clearfix" />
	</div>
</div>
<div id="footer">
	Copyright (c) 2012 . All rights reserved. Design by <a href="#" rel="nofollow">MY Cloud</a>.
</div>
</body>
</html>



