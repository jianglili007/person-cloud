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
         <form action="servlet/FileDownloadServlet" method="post">
               <p>下载文件的名称： <input type="text" name="file" size="20" maxlength="80"></p>
               <input type="submit" value="下载">
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



