<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%-- 用静态包含的方式加载 common里面的header页面  base标签 css样式， jQuery文件--%>
    <%@ include file="/pages/common/header.jsp"%>
    <%--<base href="http://localhost:8080/book/">--%>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>

    <script>
        $(function () {
            //  给删除按钮绑定一个单击事件
            $("a.deleteItemClass").click(function () {
                //  这里通过 this获取到当前的DOM对象，然后通过 父元素的父元素的第一个子元素的text值来获取
                return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
            });
            //  同样的，给清空也绑定一个单击事件
            $("#clearCart").click(function () {
                //  这里通过 this获取到当前的DOM对象，然后通过 父元素的父元素的第一个子元素的text值来获取
                return confirm("你确定要清空购物车吗？ ")
            });

            //  给输入商品数量绑定响应事件, 用户输入完成 失去焦点之后就响应
            // $(".updateCount").blur(function () {
            //
            // });
            //  另外一个事件是 onchange 内容发生改变事件，这里使用onchange 更好，因为如果输入框中的数据没有改变，就不触发这个事件
            $(".updateCount").change(function () {
                //  change这个事件，会自动判断输入框的内容是否和原来的相同，相同就不提示，不同才提示用户是否需要修改。
                let count = this.value;
                if ( confirm("确定要修改商品【"+$(this).parent().parent().find("td:first").text()+"】的数量为"+count+"吗？")){
                    //  如果确定要修改，则向后台服务器发起请求

                    //  获取传入的 bookId的值  $(this) 转换为DOM对象
                    let bookId = $(this).attr("bookId");

                    //  获取商品的数量 就等于
                    location.href="http://localhost:8080/book/cartServlet?action=updateCount&bookId="+bookId+"&count="+count;
                }else {
                    //  如果没有更新的话，就将默认值赋值给value
                    //  defaultValue 属性是表单项Dom 对象的属性，他是表示默认的Value属性值，
                    <%--//  因为我们在表单中设置了 value="${item.value.count} ，所以 会将这个值保存到defaultValue中--%>
                    this.value = this.defaultValue;
                }

            })

        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--<div>--%>
    <%--	<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>--%>
    <%--	<a href="pages/order/order.jsp">我的订单</a>--%>
    <%--	<a href="index.jsp">注销</a>&nbsp;&nbsp;--%>
    <%--	<a href="index.jsp">返回</a>--%>
    <%--</div>--%>
    <%-- 静态包含登录成功之后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <%-- 因为将后台保存的数据存放到了Session中，所以就可以通过Session来获取到购物车里面的信息 --%>
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <%--    如果购物车空的情况下        --%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前购物车为空,快去选购吧！</a>！</td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <%--    如果购物车非空的情况下        --%>
            <c:forEach items="${sessionScope.cart.items}" var="item">

                <tr>
                    <td>${item.value.name}</td>
                    <td>
                        <%-- 通过输入框来修改商品的数量，并绑定事件来实时更新--%>
                        <input class="updateCount" type="text"
                               <%-- 同时保存下bookId ，方便后面的更新操作--%>
                               bookId="${item.value.id}" style="width:80px" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a class="deleteItemClass" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>



    </table>
        <%-- 如果购物车 非空的情况下，才输出这些信息--%>
        <c:if test="${not empty sessionScope.cart.items}">

        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=removeItem">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
        </c:if>


</div>

<%--<div id="bottom">--%>
<%--		<span>--%>
<%--			尚硅谷书城.Copyright &copy;2015--%>
<%--		</span>--%>
<%--</div>--%>
<%--使用静态inlcude 引入--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>