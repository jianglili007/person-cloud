<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="menu w">
<%if(session.getAttribute("username")!=null) {%>
<% session.setAttribute("username", null);%>
<%} %>
<%

   response.getWriter().println("<script>alert('您已退出登录，请重新登录!');location.href='Login.jsp'</script>");   

  %>


</div><!--menu end-->