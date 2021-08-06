<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bberzhou.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border:  1px solid black;
            width: 650px;
            /*边框合并*/
            border-collapse: collapse;

        }
        td,th{
            border:  1px solid black;
        }
    </style>
</head>
<body>
<%--
                注意这里不能直接访问 showStudent.jsp 页面，如果直接访问的话，Servlet并没有设置 Request 域对象数据，
                所以JSP页面在访问的时候就不能获取到数据，就会报空指针的异常错误


--%>



<%--    从 Request域中获取Student List--%>
<%
       ArrayList<Student> studentArrayList = (ArrayList<Student>) request.getAttribute("stuList");
%>
<%--展示数据--%>
<%--
    遍历输出学生信息
        tr 元素定义表格行
        th 元素定义表格头
        td 元素定义表格单元格
--%>
<table>
    <thead>
    <td>学生ID</td>
    <td>学生姓名</td>
    <td>学生年纪</td>
    <td>学生电话</td>
    <td>操作</td>

    </thead>
    <% for (Student s:studentArrayList) {%>
    <%--                每循环一次，就创建一个 一行四列的 学生数据--%>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getAge()%></td>
        <td><%=s.getPhone()%></td>
        <td>删除 修改</td>
    </tr>
    <%}%>

</table>

</body>
</html>
