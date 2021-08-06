<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            pageContext.setAttribute("key1","pageContext");
            pageContext.setAttribute("key2","pageContext");
            session.setAttribute("key2","session");
            request.setAttribute("key2","request");
            application.setAttribute("key2","application");
        %>
        <%-- 使用EL表达式来获取域中的数据   --%>
        <%--
                当四个域中都有key2 属性时，如果直接使用EL表达式，会按照域范围从小到大进行访问
                如果要单独的指定就需要使用内置对象来操作
        --%>
        ${applicationScope.key2}
</body>
</html>
