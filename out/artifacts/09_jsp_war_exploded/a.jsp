<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/4/2021
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    errorPage 表示错误后自动跳转去的路径
    这个路径一般都是以斜杠开头，它表示请求地址为http://ip:port/工程路径/
    映射到代码的web目录，
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  这是JSP页面数据
<%--
    声明脚本（极少使用）
    练习：
        1、声明类属性
        2、声明static静态代码块
        3、声明类方法
        4、声明内部类

--%>

<%--1、声明类属性--%>
    <%!
            private Integer id;
            private String name;
            private static Map<String ,Object> map;
    %>
<%--2、声明static静态代码块--%>
  <%!
      static {
          map = new HashMap<>();
          map.put("key1","value1");
          map.put("key2","value2");
          map.put("key3","value3");
      }
  %>
  <%--3、声明类方法--%>
  <%!
      public void eat(){

      }
  %>
  <%-- 4、声明内部类--%>
  <%!
      public static class A{
          private final Integer id = 123;
          private final String abc = "kkk";

      }

  %>

<%--
    表达式脚本（常用）
        表达式脚本的格式是：<%= 表达式%>
    表达式脚本的作用是：在JSP页面上输出数据
        １、输出整型
        ２、输出浮点型
        ３、输出字符串
        ４、输出对象
    表达式脚本的特点：
        1、所有的表达式脚本都会被翻译到 _jspService方法中
        2、表达式脚本都会被翻译成为out.print() 输出到页面上
        3、由于表达式脚本翻译的内容都在_jspService()方法中，所以_jspService()方法中的对象都可以直接使用。
        4、表达式脚本中的表达式不能以分号结束
--%>
<%--例如使用 request 对象--%>
  <%=request.getParameter("username")%>
<%=12%>
<%=12.22%>
<%="输出字符串"%>
<%=map%>


<%--
    代码脚本
    格式如下：
        <%
            Java代码
        %>
    代码脚本的作用是：可以在jsp页面中，编写我们自己需要的功能（写的是Java代码）
    练习：
        if语句
        for循环语句
        翻译之后Java文件中_jspService方法内的代码都可以写

    代码脚本的特点是：
        1、代码脚本翻译之后都在 _jspService()方法中
        2、代码脚本由于翻译到 _jspService()方法中，所以在 _jspService()方法中的现有对象都可以直接使用
        3、可以由多个代码脚本块组合完成一个完整的Java语句；
        4、代码脚本还可以和表达式脚本一起组合使用。在JSP页面上输出数据
--%>
<%
        //  if语句
        int i = 12;
        if (i == 12){
            System.out.println("相等");
        }else {
            System.out.println("不相等");
        }

        //  for循环
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
        }

        //  翻译之后Java文件中_jspService方法内的代码都可以写
        request.getParameter("username");
%>
<%--代码脚本和表达式脚本组合使用输出表格--%>
  <table border="1px" cellspacing="0">
      <%
        for (int j = 0; j < 10; j++) {
      %>
        <tr>
            <td>第<%=j+1%>行</td>
        </tr>
     <%
        }
     %>

  </table>

</body>
</html>
