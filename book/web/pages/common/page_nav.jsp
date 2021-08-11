<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/11/2021
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%-- 设置分页条的公共部分--%>

<%--分页条的开始位置--%>
<div id="page_nav">
    <%--注意这里应该先做一个判断，如果是本来就是首页，那么就不用显示首页了要 pageNo >1 的时候才显示 --%>
    <c:if test="${requestScope.page.pageNo >1}">
        <%-- 点击首页 action = page ， pageNo 设置为1--%>
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <%-- 点击上一页，就是pageNo -1 并判断一下当前这一页是不是第一页--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo -1}">上一页</a>
        <%--<a href="#">${requestScope.page.pageNo-1}</a>--%>
    </c:if>

    <%-- 页码输出的开始--%>
    <c:choose>
        <%-- 情况1：如果总页码数小于等于5的情况，页码的范围是： 1- 页码--%>
        <c:when test="${requestScope.page.pageTotal <=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <%--这里要做一个判断，当前页的时候不可点击，其他页的时候可以点击--%>
                <c:if test="${ i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${ i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%-- 情况2：如果总页码数大于5的情况，  19/2--%>
        <c:when test="${requestScope.page.pageTotal >5}">
            <c:choose>
                <%-- 小情况1：当前页码为前面3个： 1，2，3 的情况，页码范围是 1-5--%>
                <c:when test="${requestScope.page.pageNo <=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <%--这里要做一个判断，当前页的时候不可点击，其他页的时候可以点击--%>
                        <c:if test="${ i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%-- 小情况2：当前页码为最后面3个： 8，9，10 的情况，页码范围是 6，7，8，9，10，即总页码-4 - 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}"
                               var="i">
                        <%--这里要做一个判断，当前页的时候不可点击，其他页的时候可以点击--%>
                        <c:if test="${ i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%-- 小情况3：当前页码为中间的时候 3，4，5，6，7个： 页码范围是 当前页码-2  -  当前页码 +2--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo -2}" end="${requestScope.page.pageNo+2}" var="i">
                        <%--这里要做一个判断，当前页的时候不可点击，其他页的时候可以点击--%>
                        <c:if test="${ i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>
    <%-- 页码输出的结尾--%>


    <%--
        页码输出和结尾的优化版本，一部分代码可以直接复用
        直接设置值，然后复用一部分代码
        --%>

    <%-- 页码输出的开始--%>
    <%--        <c:choose>--%>
    <%--            &lt;%&ndash; 情况1：如果总页码数小于等于5的情况，页码的范围是： 1- 页码&ndash;%&gt;--%>
    <%--            <c:when test="${requestScope.page.pageTotal <=5}">--%>
    <%--                <c:set var="begin" value="1"/>--%>
    <%--                <c:set var="end" value="${requestScope.page.pageTotal}"/>--%>
    <%--            </c:when>--%>
    <%--            &lt;%&ndash; 情况2：如果总页码数大于5的情况，  19/2&ndash;%&gt;--%>
    <%--            <c:when test="${requestScope.page.pageTotal >5}">--%>
    <%--                <c:choose>--%>
    <%--                    &lt;%&ndash; 小情况1：当前页码为前面3个： 1，2，3 的情况，页码范围是 1-5&ndash;%&gt;--%>
    <%--                    <c:when test="${requestScope.page.pageNo <=3}">--%>
    <%--                        <c:set var="begin" value="1"/>--%>
    <%--                        <c:set var="end" value="5"/>--%>
    <%--                    </c:when>--%>
    <%--                    &lt;%&ndash; 小情况2：当前页码为最后面3个： 8，9，10 的情况，页码范围是 6，7，8，9，10，即总页码-4 - 总页码&ndash;%&gt;--%>
    <%--                    <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">--%>
    <%--                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>--%>
    <%--                        <c:set var="end" value="${requestScope.page.pageTotal}"/>--%>
    <%--                    </c:when>--%>
    <%--                    &lt;%&ndash; 小情况3：当前页码为中间的时候 3，4，5，6，7个： 页码范围是 当前页码-2  -  当前页码 +2&ndash;%&gt;--%>
    <%--                    <c:otherwise>--%>
    <%--                        <c:set var="begin" value="${requestScope.page.pageNo-2} "/>--%>
    <%--                        <c:set var="end" value="${requestScope.page.pageNo+2}"/>--%>
    <%--                    </c:otherwise>--%>
    <%--                </c:choose>--%>
    <%--            </c:when>--%>
    <%--        </c:choose>--%>
    <%--        &lt;%&ndash;            这里只需要获取对应的 begin 和 end 值即可&ndash;%&gt;--%>
    <%--        <c:forEach begin="${begin}" end="${end}" var="i">--%>
    <%--            &lt;%&ndash;这里要做一个判断，当前页的时候不可点击，其他页的时候可以点击&ndash;%&gt;--%>
    <%--            <c:if test="${ i == requestScope.page.pageNo}">--%>
    <%--                【${i}】--%>
    <%--            </c:if>--%>
    <%--            <c:if test="${ i != requestScope.page.pageNo}">--%>
    <%--                <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>--%>
    <%--            </c:if>--%>
    <%--        </c:forEach>--%>
    <%-- 页码输出的结尾--%>


    <%--从request 域中获取当前 的 页码数--%>
    <%--第【${requestScope.page.pageNo}】页--%>
    <%--<a href="#">5</a>--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <%--下一页也是同样的--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    <%-- 这里使用EL的隐藏域对象 param获取一下pageNo的值，实时的更新到输入框中，设置value的值--%>
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script>
        <%--    给确定按钮添加上点击事件--%>
        $(function () {
            //  点击确定按钮跳转到指定的页面
            $("#searchPageBtn").click(function () {
                console.log("点击了")
                //  1、获取输入框中的
                let pageNo = $("#pn_input").val();
                //  JavaScript语言中提供了一个location的地址栏对象，他有一个属性叫做 href, 可以获取浏览器地址栏中地址
                //  href 属性可读，可写
                location.href = "${pageContext.getAttribute("appPath")}${requestScope.page.url}&pageNo=" + pageNo;

            });
        })
    </script>
</div>
