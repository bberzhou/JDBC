<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%-- 用静态包含的方式加载 common里面的header页面  base标签 css样式， jQuery文件--%>
    <%@ include file="/pages/common/header.jsp"%>
    <%--<!--写base 标签，永远固定相对路径跳转的结果-->--%>
    <%--<base href="http://localhost:8080/book/">--%>

    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>
    <%--<script type="text/javascript" src="static/script/jquery-3.6.0.js"></script>--%>


    
    <script type="text/javascript">
        <!--页面加载完成之后-->
        $(function () {

            //  给验证码切换绑定单击事件。
            $("#code_img").click(function () {
                //  在事件响应的function 函数中有一个this对象，这个this 对象，是当前正在响应事件的dom 对象

                //  src 属性表示验证码img 标签的  图片路径。他可读可写
                this.src= "${basePath}KaptchaServlet.jpg"
            });










            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                //  验证用户名：必须由字母、数字下划线组成，并且长度为 5到12为2

                //  1、创建用户名输入框里的内容
                let usernameText = $("#username").val();
                //  2、创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;

                //  3、使用test方法验证，如果不匹配就提示用户
                if (!usernamePatt.test(usernameText)) {
                    //  4、提示用户结果
                    $(".errorMsg").text("用户名不合法!");
                    return false;
                }

                //  验证密码：必须由字母、数字下划线组成，并且长度为5到12位
                let passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)){
                    $(".errorMsg").text("密码错误！");
                    return false;
                }


                //  验证确认密码：和密码是否相同
                //  1、获取确认密码内容
                let repeatPWD = $("#repwd").val();
                //  2、和密码比较
                //  如果两个密码不相等
                if (repeatPWD !== passwordText){
                    //  3、提示用户
                    $(".errorMsg").text("两者密码不一致！");
                    return  false;
                }

                //  邮箱验证：XXXXX@XXX.com
                //  1、获取邮箱里面的内容
                let emailText = $("#email").val();
                //  2、创建正则表达式对象
                let emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //  3、使用test方法验证是否合法
                if (!emailPatt.test(emailText)){
                    //  4、提示用户
                    $(".errorMsg").text("邮箱错误！");
                    return  false;
                }
                //  验证码：验证码后续，现在脂砚斋用户已经输入，后续是由服务器生成
                let val = $("#code").val();
                //  去掉验证码前后的空格；
                let codeText =  $.trim(val);
                //如果验证码为空，或者为Null
                if (codeText == null || codeText ===""){
                    //  提示用户
                    $(".errorMsg").text("验证码错误！");
                    return  false;
                }

            })
        })

    </script>
    <style  type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <!--错误的提示信息-->
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("username")==null?"请输入用户名":request.getAttribute("msg")%>--%>
<%--                        使用EL 表达式进行替换 --%>
                        ${empty requestScope.msg ? "请输入用户名和密码！":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <!--
                        这里的registerServlet 是相对路径，实际的路径就是
                        http://localhost:8080/book/registerServlet
                     -->
                    <%--<form action="registerServlet" method="post">--%>
                    <%--<form action="userServlet" method="post">--%>
                    <form action="userServlet2" method="post">
                        <input type="hidden" name="action" value="register">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1"
                               name="username" id="username"
                               <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
<%--                                使用EL表达式替换，并且因为EL表达式输出空的时候，不会输出null，所以不用判断--%>
                                value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1"
                               name="password" id="password"
                               <%--value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
                        />
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"
                               <%--value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
                        />
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1"
                               name="email" id="email"
                               <%--value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
                               value="${requestScope.email}"

                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
                        <%-- 切换验证码 绑定一个单击时间--%>
                        <img id="code_img" alt="" src="http://localhost:8080/book/KaptchaServlet.jpg" style="float: right; margin-right: 40px;width:80px;height: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
    <%--<div id="bottom">--%>
    <%--    <span>--%>
    <%--        尚硅谷书城.Copyright &copy;2015--%>
    <%--    </span>--%>
    <%--</div>--%>
<%--使用静态inlcude 引入--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>