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
                  <form action="servlet/LoginServlet" method="post" name="regform" >
                     <table>
                       <caption>
                            MyCloud用户登录
                       </caption>
                       <tr>
                       <td>用户名：</td>
                         <td><input type="text" name="userName" /></td>
                          </tr>
                          <tr>
                          <td>密码：</td>
                            <td><input type="password" name="passWord" /></td>
                           </tr>
                               <tr>
                        <td>&nbsp;</td>
                            <td>
                         <input type="submit" name="button"  value="登录" /></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                             <td><a href="Register.jsp">注册账户 </a></td>	
                          </tr>
                          </table>
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



