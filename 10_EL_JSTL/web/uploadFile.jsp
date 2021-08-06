<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/6/2021
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传功能</title>
</head>
<body>
    <form action="http://localhost:8080/10_EL_JSTL/uploadServlet" method="post" enctype="multipart/form-data">
        用户名: <input type="text" name="username" ><br>
        头  像: <input type="file" name="photo"><br>
        <input type="submit" value="上传头像">
    </form>

</body>
</html>
