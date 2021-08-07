<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%-- 用静态包含的方式加载 common里面的header页面  base标签 css样式， jQuery文件--%>
    <%@ include file="/pages/common/header.jsp"%>
    <%--	<base href="http://localhost:8080/book/">--%>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%--<div>--%>
    <%--    <a href="book_manager.jsp">图书管理</a>--%>
    <%--    <a href="order_manager.jsp">订单管理</a>--%>
    <%--    <a href="../../index.jsp">返回商城</a>--%>
    <%--</div>--%>
    <%@ include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>

        </tr>
        <tr>
            <td>2015.04.23</td>
            <td>90.00</td>
            <td><a href="#">查看详情</a></td>
            <td><a href="#">点击发货</a></td>
        </tr>

        <tr>
            <td>2015.04.20</td>
            <td>20.00</td>
            <td><a href="#">查看详情</a></td>
            <td>已发货</td>
        </tr>

        <tr>
            <td>2014.01.23</td>
            <td>190.00</td>
            <td><a href="#">查看详情</a></td>
            <td>等待收货</td>
        </tr>
    </table>
</div>

<%--<div id="bottom">--%>
<%--		<span>--%>
<%--			尚硅谷书城.Copyright &copy;2015--%>
<%--		</span>--%>
<%--</div>--%>
<%--使用静态inlcude 引入--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>