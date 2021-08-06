<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bberzhou.pojo.Person" %>
<%@ page import="com.bberzhou.pojo.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL标签</title>
    <style type="text/css">
        table{
            width: 650px;
            border: 1px solid red;
            border-collapse: collapse;
        }
        th,td{
            border: 1px solid red;
        }
    </style>
</head>
<body>
    <%--
        <c:set>
            作用：set标签可以往域中保存数据
            域对象 setAttribute(key,value);
            scope 属性设置保存到哪个域
                    page表示PageContext域（默认值）
                    request表示Request域
                    session表示Session域
                    application表示ServletContext域

            var属性设置key是多少
            value属性设置保存的value是多少


    --%>
    保存之前：${requestScope.abc}<br><br>
    <c:set scope="request" var="abc" value="abcValue"/>
    保存之后：${requestScope.abc}<br><br>

    <br>
    <hr>
        <%--
            if标签
            <c:if>
                if标签用来做if判断
                test属性表示判断的条件（使用EL表达式输出）
                如果判断为真，就会执行中间代码
                这个不能写成if else

        --%>
        <c:if test="${12 !=12}">
            <h1>12 等于12</h1>
        </c:if>
    <br>
    <hr>
    <%--
        < c:choose> < c: when><c: otherwise> 标签
        作用：用作多路判断， 跟 switch ...case... default 接近
        choose标签开始选择判断
        when标签表示每一种判断情况
            test表示当前这种判断情况的值，test里面依然是写el表达式
        otherwise 表示其他剩下的情况

        注意的地方：
            1、标签里面不能使用html注释，要使用JSP注释
            2 when标签的父标签一定要是 choose标签,必须的嵌套起来
    --%>
    <%
        request.setAttribute("height",169);
    %>
    <c:choose>
            <%--   对request域中的height 属性做判断      --%>
        <c:when test="${requestScope.height >190}">
            <h2>小巨人</h2>
        </c:when>
        <%--<!--JHTML注释--> 加HTML注释会报错--%>
        <c:when test="${requestScope.height >180}">
            <h2>很高</h2>
        </c:when>
        <c:when test="${requestScope.height >170}">
            <h2>正常水平</h2>
        </c:when>
        <c:otherwise>
            <h2>剩下的比170矮</h2>
            <c:choose>
                <c:when test="${requestScope.height >160}">
                    <h2>小巨人</h2>
                </c:when>
                <%--<!--JHTML注释--> 加HTML注释会报错--%>
                <c:when test="${requestScope.height >150}">
                    <h2>很高</h2>
                </c:when>
                <c:when test="${requestScope.height >140}">
                    <h2>正常水平</h2>
                </c:when>
                <c:otherwise>
                    <h2>其他小于140</h2>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <br>
    <hr>
        <%--
            < c : foreach>
                遍历输出
                begin属性设置开始的索引,
                end属性设置结束的索引
                var属性表示循环的变量(同时也是当前正在遍历得到的数据)
        --%>
            <%--遍历 1 到 10 --%>
        <c:forEach begin="1" end="10" var="i">
            ${i}
        </c:forEach>
        <table>
            <c:forEach begin="1" end="10" var="i">
                <tr>
                    <td>第${i}行</td>
                </tr>
            </c:forEach>
        </table>
<br>
    <hr>
<br>
        <%--
            遍历Object对象数组
                items 表示遍历的数据源(遍历的集合)
                var 表示当前遍历到的数据
        --%>
        <%
            request.setAttribute("arr",new String[]{"18623333333","1288887744","23787878787"});
        %>
        <c:forEach items="${requestScope.arr}" var="item">
            ${item}<br>
        </c:forEach>
    <hr>
    <br>
        <%--
            遍历Map集合

        --%>
        <%
            HashMap<String ,Object> map = new HashMap<>();
            map.put("key1","value1");
            map.put("key2","value2");
            map.put("key3","value3");
            request.setAttribute("map",map);
        %>
        <c:forEach items="${requestScope.map}" var="entry">
            ${entry.key} = ${entry.value}<br>
        </c:forEach>

        <%--
                遍历List集合,List集合里面存放有Student类
                items 表示遍历的集合
                var 表示遍历到的数据
                begin 表示遍历的开始索引值
                end 表示结束的索引值
                varStatus 表示属性var当前的状态
                step 属性表示遍历的步长值,就指的 i++的这个属性
                for( int i; i<10; i++)


         --%>
        <%
            ArrayList<Student> studentArrayList = new ArrayList<>();
            studentArrayList.add(new Student(1,"name1","ps1",1,"phone1"));
            studentArrayList.add(new Student(2,"name2","ps2",2,"phone2"));
            studentArrayList.add(new Student(3,"name3","ps1",3,"phone3"));
            studentArrayList.add(new Student(4,"name4","ps1",4,"phone4"));
            request.setAttribute("stus",studentArrayList);
        %>
        <table align="center">
            <tr>
                <th>id</th>
                <th>用户名</th>
                <th>密码</th>
                <th>年龄</th>
                <th>电话</th>
                <th>操作</th>
                <th>操作</th>
            </tr>

        <c:forEach items="${requestScope.stus}" var="stu" varStatus="status">
            <tr>
                <td>${stu.id}</td>
                <td>${stu.username}</td>
                <td>${stu.password}</td>
                <td>${stu.age}</td>
                <td>${stu.phone}</td>
                <td>删除\修改</td>
                <td>${status.first}</td>
            </tr>
        </c:forEach>
        </table>
</body>
</html>
