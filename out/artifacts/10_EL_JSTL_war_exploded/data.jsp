<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("key","value1");
    %>
    表达式脚本数据key的值：<%=request.getAttribute("key1")%><br><br>
    <%--
        当不存在变量 key1 的时候，
          表达式脚本输出：null
          EL表达式输出：空串
    --%>
    EL 表达式输出key的值是：${key1};
</body>
</html>
