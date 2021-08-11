<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/7/2021
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <%--
        这里指定成为  manager/bookServlet?action=list
        表示点击图书管理的时候就调用  bookServlet ，然后根据传入的参数 action=list 利用反射来调用对应的方法，
        list就是 BookServlet 类里面的 list() 方法

        注意：使用分页之后，点击图书管理的时候就会分页请求，所以这里就不应该是 直接 action=list 请求所有的数据
     --%>

    <a href="manager/bookServlet?action=page">图书管理</a>
    <a href="pages/manager/order_manager.jsp">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>
</body>
</html>
