<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bberzhou.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 9:29 AM
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
<%--    练习二：JSP输出一个表格，里面有10个学生信息--%>
    <%
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i+1,"name"+i, 15+i,"phone"+i));
        }
    %>

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
        <% for (Student s:students) {%>
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
