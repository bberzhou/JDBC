<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/11/2021
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表达重复提交的问题</title>
</head>
<body>
<form action="resubmitServlet" method="get">
  用户名：<input type="text" name="username" ><br>
  密  码：<input type="password" name="password" ><br>
  验证码：<input type="text" name="Vcode" style="width: 50px">
    <img src="http://localhost:8080/08_servlet/Kaptcha.jpg" style="width: 100px;height: 28px"><br>
  <input type="submit" value="submit">
</form>
</body>
</html>
