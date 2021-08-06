<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>empty测试</title>
</head>
<body>
    <%
        // 1、值为null值得时候，为空
        request.setAttribute("emptyNull",null);
        // 2、值为空串得时候，为空
        request.setAttribute("emptyStr","");
        // 3、值为Object类型数组，长度为零得时候
        request.setAttribute("emptyArr",new Object[]{});
        // 4、list集合元素个数为零
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("add");
        request.setAttribute("emptyList",objects);
        // 5、map集合，元素个数为零
        Map<String ,Object> map = new HashMap<>();
        request.setAttribute("emptyMap",map);
    %>
    <%--  返回true      --%>
    ${empty emptyNull}<br><br>
    ${empty emptyStr}<br><br>
    ${empty emptyArr}<br><br>
    ${empty emptyList}<br><br>
    ${empty emptyMap}<br><br>

<%--三元表达式--%>
        ${12 == 12? "等于":" 不等于"}
</body>
</html>
