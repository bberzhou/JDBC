<%@ page contentType="text/html;charset=utf-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <%-- 用静态包含的方式加载 common里面的header页面  base标签 css样式， jQuery文件--%>
    <%@ include file="/pages/common/header.jsp" %>
    <%--<base href="http://localhost:8080/book/">--%>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <%--<span class="errorMsg">请输入用户名和密码</span>--%>
                    <span class="errorMsg">
                        <%--注意，这里要做一个判断，因为开始的时候还没有把 msg 保存到request域中，会输出一个null值 --%>
                        <%--  使用EL表达式来进行替换 --%>
<%--                        <%=request.getAttribute("msg") == null ? "请输入用户名和密码！" : request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg ? "请输入用户名和密码！":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <%--<form action="loginServlet" method="post">--%>
                    <%--<form action="userServlet" method="post">--%>
                    <form action="userServlet2" method="post">
                        <%--给这个表单添加一个隐藏域--%>
                        <input type="hidden" name="action" value="login">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username"
                        <%--
                                这里对input 输入框的数据进行回显，如果用户名或者密码错误，
                                后台就把username的值放入到request域中，前端就把username的值获取到，放到input的value值中--%>
<%--                                    使用EL表达式进行替换，因为EL 表达式在输出空值的时候，不会输出null ，所以直接替换即可，不用判断--%>
                               <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                                value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--<div id="bottom">--%>
<%--	<span>--%>
<%--		尚硅谷书城.Copyright &copy;2015--%>
<%--	</span>--%>
<%--</div>--%>
<%--使用静态inlcude 引入--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>