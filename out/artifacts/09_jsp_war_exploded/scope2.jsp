<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/4/2021
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>scope2.jsp页面</h1>
    pageContext域是否有值：<%=pageContext.getAttribute("key")%><br>
    session域是否有值：<%=session.getAttribute("key")%><br>
    request域是否有值：<%=request.getAttribute("key")%><br>
    application域是否有值:<%=application.getAttribute("key")%>
</body>
</html>
