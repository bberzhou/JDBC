<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP输出乘法口诀表</title>
    <style>
        table{
            width: 650px;
        }
    </style>
</head>
<body>
<%--        在页面中输出--%>

<%--    <%--%>
<%--    for (int i = 1; i <=9; i++) {--%>
<%--        for (int j = 1; j <=i ; j++) {--%>
<%--    %>--%>
<%--        <%=j+"*"+i+"="+(i*j)%>--%>
<%--    <%--%>
<%--        }--%>
<%--        //  每循环完一次内层就换行--%>
<%--    %>--%>
<%--        <br>--%>
<%--    <%--%>
<%--    }--%>
<%--    %>--%>


    <h1 align="center">九九乘法表</h1>
    <table align="center">
        <% for (int i = 1; i <=9; i++) { %>
            <tr>
            <% for (int j = 1; j <=i ; j++) {%>
                <td><%=j+"×"+i+"="+(i*j)%></td>
            <%}%>
            <%--//  每循环完一次内层就换行--%>
            </tr>
        <%}%>
    </table>
</body>
</html>
