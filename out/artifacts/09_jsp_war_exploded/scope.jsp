<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/4/2021
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--演示不同范围内的作用域大小--%>

    <h1>scope.jsp页面</h1>
    <%
        //  往四个域中分别保存数据
        request.setAttribute("key","request");
        pageContext.setAttribute("key","pageContext");
        application.setAttribute("key","application");
        session.setAttribute("key","session");
    %>
    pageContext域是否有值：<%=pageContext.getAttribute("key")%><br>
    session域是否有值：<%=session.getAttribute("key")%><br>
    request域是否有值：<%=request.getAttribute("key")%><br>
    application域是否有值:<%=application.getAttribute("key")%>
<%--<%--%>
<%--    request.getRequestDispatcher("/scope2.jsp").forward(request,response);--%>
<%--%>--%>
        <%--
            <jsp:forward page=""></jsp:forward>
            使用JSP的请求转发
            page属性设置请求转发的路径
        --%>
    <jsp:forward page="scope2.jsp"></jsp:forward>
</body>
</html>
