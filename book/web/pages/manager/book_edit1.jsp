<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--
       方案二：book_manager1.jsp页面的操作，通过判断 是否带有 id来进行动态选择执行的方法
       方案三：因为修改操作是会先按照id进行查询，然后将Book对象返回到request域中，而addBook 是不会有这个步骤的，
       所以也可以直接Request域中判断是否有这个 book 对象





    --%>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@ include file="/pages/common/header.jsp"%>
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
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">

    <%--<form action="manager/bookServlet?action=${empty param.id?"addBook":"updateBook"}" method="post">--%>
    <form action="manager/bookServlet?action=${empty requestScope.book?"addBook":"updateBook"}" method="post">

        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>

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

<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>