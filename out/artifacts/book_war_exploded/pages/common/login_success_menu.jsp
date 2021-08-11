<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/7/2021
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>

<div>
    <%--
        这边显示用户信息，使用UserServlet2中login() 方法中 设置session 域中的User属性值
        sessionScope 就是对应着JSP中的session 域
    --%>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet2?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

