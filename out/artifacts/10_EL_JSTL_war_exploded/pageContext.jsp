<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pageContext内置对象</title>
</head>
<body>
  <%--
    org.apache.jasper.runtime.PageContextImpl@7cc4980b
    可以看出是PageContextImpl 的对象

    可以使用PageContext来访问JSP 内置对象
   --%>
  ${pageContext}
  ${pageContext.servletContext}

  <br>
<br>
<%--
  request.getScheme() 可以获取请求的协议
  request.getServerName() 获取请求的服务器ip或者域名
  request.getServerPort() 获取请求的服务器端口号
  request.getContextPath() 获取请求的工程路径
  request.getMethod()     获取请求方法(GET 或者POST)
  request.getRemoteHost() 获取客户端的 ip地址
  session.getId   获取会话（session）的id



--%>

<%--
    EL 表达式的使用
    1、输出协议信息
    2、服务器ip地址
    3、服务器端口号
    4、获取工程路径
    5、获取请求方法
    6、获取客户端ip地址
    7、获取会话（session）的id编号
--%>
<%-- 输出协议信息 --%>
  输出协议信息: ${pageContext.request.scheme}<br><br>
<%-- 获取服务器ip--%>
  服务器ip地址: ${pageContext.request.serverName}<br><br>
<%--  获取服务器端口号--%>
  服务器端口号: ${pageContext.request.serverPort}<br><br>
<%--  获取工程路径--%>
  获取工程路径: ${pageContext.request.contextPath}<br><br>
<%--  获取请求方法--%>
  获取请求方法: ${pageContext.request.method}<br><br>
<%--获取客户端ip地址--%>
  获取客户端ip地址: ${pageContext.request.remoteAddr}<br><br>
  获取客户端ip地址: ${pageContext.request.remoteHost}<br><br>
<%--获取会话（session）的id--%>
  获取会话（session）的id: ${pageContext.session.id}<br><br>
  获取会话（session）的id: ${pageContext.request.session.id}

<%-- 也可以直接把request对象放到 pageContext中 --%>
      <%
        pageContext.setAttribute("req",request);
      %>
        <%--下面使用就更方便--%>
        ${req.session.id}
</body>
</html>
