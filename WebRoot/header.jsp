<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<div id="header">
		<div id="logo">
			<h1><a href="#">My Cloud</a></h1>
		</div>
		<div id="slogan">
		  <h2>我的私有专享世界</h2>
		</div>
	</div>
	<div id="menu">
		<ul>
			<li class="first current_page_item"><a href="mainList.jsp">全部文件</a></li>
		   <li><a href="NewFile.jsp?directory=<%=session.getAttribute("directory") %>">新建文件夹</a></li>				
			<li><a href="Upload.jsp?directory=<%=session.getAttribute("directory") %>">文件上传</a></li>			
		
		  <li><a href="Detail.jsp">关于我们</a></li>
		</ul>
		<br class="clearfix" />
	</div>
<div class="menu w">
<%if(session.getAttribute("username")!=null) {%>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=session.getAttribute("username") %>
<span>您好！已登录！ | <a href="out.jsp">退出</a></span>
<% }else{%>
<%
 
   response.getWriter().println("<script>alert('请登录!');location.href='Login.jsp'</script>");   

  %>

<%} %>
</div><!--menu end-->