<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/7/2021
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!--写base 标签，永远固定相对路径跳转的结果，但是这里的ip和端口号不应该写死了，应该动态的获取过来-->


<%--
    request.getScheme() 获取请求的协议
    request.getRemoteAddr():获取请求的ip地址
    request.getRemotePort()：获取请求的端口号

--%>
<%
    String appPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<base href="<%=appPath%>">
<%--<%=basePath%>--%>
<%-- 用实际获取到的地址进行替换--%>
<%--<base href="http://localhost:8080/book/">--%>
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-3.6.0.js"></script>
