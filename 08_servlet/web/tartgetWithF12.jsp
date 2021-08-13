<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/13/2021
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        System.out.println("tartgetWithF12.jsp 页面执行了");
        System.out.println("目标资源  线程名:"+Thread.currentThread().getName());
        System.out.println("目标资源  " + request.getParameter("username"));

    %>
</body>
</html>
