<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL  other_el_obj 表达式的其他内置对象</title>
</head>
<body>
    <%--
        ?username=KKK&password=lll&hobby=java&hobby=cpp&hobby=js

    --%>
    输出请求参数username的值，在地址栏显式输入：${param.username}<br><br>
    输出请求参数password的值，在地址栏显式输入：${param.password}<br><br>
<%--    使用paramValues--%>
    输出请求参数username的值，在地址栏显式输入：${paramValues.username[0]}<br><br>
    <%-- 针对那种多选框的时候--%>
    输出请求参数username的值，在地址栏显式输入：${paramValues.hobby[0]}<br><br>
    输出请求参数username的值，在地址栏显式输入：${paramValues.hobby[1]}<br><br>
<hr>
    <%--
        输出请求头 [user-Agent]的值：0
        注意这里有特殊字符，应该使用 中括号的方式 []
        header里面都是以键值对形式存在的
   --%>
    <%--输出请求头 [user-Agent]的值：${header.User-Agent}<br><br>--%>
    输出请求头 [user-Agent]的值：${header["User-Agent"]}<br><br>
    输出请求头 [user-Connection]的值：${header["Connection"]}<br><br>
<%--
        也可以使用headerValues获取
        headerValues["User-Agent"] 会返回一个地址 数组
        --%>
    输出请求头 [user-Agent]的值：${headerValues["User-Agent"][0]}<br><br>

    <hr><hr>
    <%--输出
    Cookie：{JSESSIONID=jakarta.servlet.http.Cookie@46bb989c,
     Idea-b5dd7237=jakarta.servlet.http.Cookie@4bb21161}--%>
    输出Cookie：${cookie}<br><br>
    获取Cookie的昵称：${cookie.JSESSIONID.name}<br><br>
    获取Cookie的值：${cookie.JSESSIONID.value}<br><br>

    <hr><hr>
    输出 init-param：${initParam}<br><br>
    获取init-param参数值：${initParam["username"]}<br><br>
    获取init-param参数值：${initParam.username}<br><br>

</body>
</html>
