<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%-- 用静态包含的方式加载 common里面的header页面  base标签 css样式， jQuery文件--%>
    <%@ include file="/pages/common/header.jsp"%>
    <%--	<base href="http://localhost:8080/book/">--%>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%--<div>--%>
    <%--    <a href="book_manager.jsp">图书管理</a>--%>
    <%--    <a href="order_manager.jsp">订单管理</a>--%>
    <%--    <a href="../../index.jsp">返回商城</a>--%>
    <%--</div>--%>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <%--

       管理页面点击修改之后，会首先根据 getBook() 查询到的Book对象，设置到 book_edit 里面进行表单的回显，
       但是，注意！！提交表单数据的时候，并没有将 book 对象的 id 值传给 updateBook() 方法，
       所以这里就需要把 id 值传过去， 可以利用 隐藏域 进行操作，也可以直接将id 值放在action后面 传入


        param 是EL中的隐藏对象
        通过获取当前页面的request 域中的id 来传给Servlet
        版本三， 也可以直接将参数写到 manager/bookServlet?action 后面

    --%>
    <form action="manager/bookServlet?action=${param.method}&id=${requestScope.book.id}&pageNo=${param.pageNo}" method="post">

        <%--这里也可以使用 使用隐藏域的方式 来获取 action 的值--%>
        <%--  版本一 --%>
        <%--<form action="manager/bookServlet" method="post">--%>
        <%--<input type="hidden"  name="action" value="addBook">--%>

        <%--
            这里因为修改和添加是共用的一个页面，那么如何动态的去传入需要的action值呢？
            可以在book_manager.jsp 页面中给修改和添加 绑定一个method参数，然后通过EL表达式进行获取
            隐藏域可以有多个
        --%>

        <%--  版本二 升级版本 一 --%>
        <%--<form action="manager/bookServlet" method="post">--%>
        <%--&lt;%&ndash;  获取method参数，赋值给value ，这个method是从book_manager.jsp中的a 标签传过来的 &ndash;%&gt;--%>
        <%--<input type="hidden"  name="action" value="${param.method}">--%>
        <%--    <input type="hidden" name="id" value="${requestScope.book.id}">--%>
        <%--    <input type="hidden" name="id" value="${requestScope.page.pageNo}">--%>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <%--<tr>--%>
            <%--    <td><input name="book_name" type="text" value="时间简史"/></td>--%>
            <%--    <td><input name="book_price" type="text" value="30.00"/></td>--%>
            <%--    <td><input name="book_author" type="text" value="霍金"/></td>--%>
            <%--    <td><input name="book_sales" type="text" value="200"/></td>--%>
            <%--    <td><input name="book_stock" type="text" value="300"/></td>--%>
            <%--    <td><input type="submit" value="提交"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td><input name="book_name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="book_price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="book_author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="book_sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="book_stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>

        </table>
    </form>


</div>

<%--<div id="bottom">--%>
<%--			<span>--%>
<%--				尚硅谷书城.Copyright &copy;2015--%>
<%--			</span>--%>
<%--</div>--%>
<%--使用静态inlcude 引入--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>