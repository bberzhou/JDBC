<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/13/2021
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%--  针对JSP页面,可以使用Session 来判断session域中是否保存了用户信息--%>
  <%
      System.out.println("a.jsp页面执行了");
      Object user = session.getAttribute("user");
    //  如果等于 null, 说明还没有登录
    if (user == null){
      //  如果Session域中没有这些信息就请求转发到登录页面
      request.getRequestDispatcher("/login.jsp").forward(request,response);
      return;
    }
  %>

  <h2>这是admin中的jsp页面</h2>
</body>
</html>
