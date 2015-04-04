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
	<div id="header">
		<div id="logo">
			<h1><a href="#">My Cloud</a></h1>
		</div>
		<div id="slogan">
		  <h2>我的私有专享世界</h2>
		</div>
	</div>
	
	<div id="page">
		<div id="content">				
                 <form name="Name" action="servlet/RegisterServlet" method="post"><p>

				<label for="username">昵 称：</label>				

				<input type="text" name="username" id="username"/>

				</p>

				<p>

				<label for="password">密 码：</label>				

				<input type="password" name="password" id="password" style="width: 156px; "/>

				</p>
				
				<p>

				<label for="password2">再次输入密码：</label>				

				<input type="password" name="password2" id="password2" style="width: 156px; "/>

				</p>

				
				<p>

				<label for="email">邮 箱：</label>				

				<input type="text" name="email" id="email"/>

				</p>

				
				
				<p> 

				<input type="submit" name="Submit" value="注册" style="width: 120px; "/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				 <input type="reset" name="Reset" value="取消" style="width: 120px; "/>

				</p>

				<p><a href="Login.jsp">已经注册，直接登录</a> </p>

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



