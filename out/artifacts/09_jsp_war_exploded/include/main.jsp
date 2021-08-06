<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        头部信息<br>
        主体内容<br>
        <%--
            <%@include file="footer.jsp"%> 就是静态包含
                file属性指定要包含的jsp页面的路径
                地址中 第一个斜杠表示为 http://ip:port/工程路径/
                映射到代码的web目录

                静态包含的特点：
                    1、静态包含不会翻译被包含的jsp页面
                    2、静态包含其实是把被包含的jsp页面的代码拷贝到包含的位置执行输出

        --%>
<%--        <%@include file="/include/footer.jsp"%>--%>


<%--
    <jsp:include page="/include/footer.jsp"></jsp:include> 动态包含
    page属性：指定要包含的jsp页面的路径
    动态包含也可以想静态包含一样，把被包含的内容执行输出到包含位置

    动态包含的特点：
        1、动态包含会把包含的jsp页面也翻译成为Java代码
        2、动态包含底层代码使用如下代码去调用被包含的jsp页面执行输出：
            JspRuntimeLibrary.include(request,response,"/include/footer/jsp",out.false)
        3、动态包含，还可以传递参数
--%>
        <jsp:include page="/include/footer.jsp">
            <jsp:param name="username" value="bbj"/>
            <jsp:param name="password" value="root"/>
        </jsp:include>
</body>
</html>
