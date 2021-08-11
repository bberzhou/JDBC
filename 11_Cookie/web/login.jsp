<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/11/2021
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户免密登录</title>
</head>
<body>
    <form action="loginServlet" method="get">
        用户名：<input type="text" name="username" id="usernmae" value="${cookie.username.value}">
        密  码：<input type="password" name="password" id="password">
        <input type="submit" value="submit">
    </form>
</body>
</html>
