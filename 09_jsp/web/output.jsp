<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%

        out.write("out输出1</br>");
        //  这里先刷新缓冲区，就会先将out输出1 添加到response缓冲区里面
        out.flush();
        out.write("out输出2</br>");
        response.getWriter().write("response输出1</br>");
        response.getWriter().write("response输出2</br><br><br><br><br>");


        //  out的write() 和print() 方法的区别
        // out.print(12);


        //  write() 单独输出int类型的时候有问题
        out.write(12);

    %>

</body>
</html>
