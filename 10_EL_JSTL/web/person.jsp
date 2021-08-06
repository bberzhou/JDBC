<%@ page import="com.bberzhou.pojo.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: ZhouZhaoJian
  Date: 8/5/2021
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL显示数据</title>
</head>
<body>
    <%--
          注意：
            使用EL表达式输出这些属性，不是直接找的Perosn对象的属性，实际上是通过getXXX()方法进行访问的
            例如，Person类里面的age属性没有get方法，就不能正常访问到
    --%>

    <%
      Person person = new Person();
      person.setName("我是渣渣辉");
      person.setPhones(new String[]{"17263767","21389898","189898933"});
      ArrayList<String > cities = new ArrayList<>();
      cities.add("Beijing");
      cities.add("Chongqing");
      cities.add("Shanghai");
      cities.add("chengdu");
      person.setCities(cities);
      HashMap<String ,Object> keyMap = new HashMap<>();
      keyMap.put("key1","value1");
      keyMap.put("key2","value2");
      keyMap.put("key3","value3");
      keyMap.put("key4","value4");
      person.setMap(keyMap);
      //  将Person对象放入域数据中
      pageContext.setAttribute("person",person);
    %>
      <%--
          在JSP中使用EL表达式输出Person类中的普通属性，数组属性，List集合属性和map集合属性.
      --%>
    输出Person：${person}<br><br>
    输出Person对象的name属性：${person.name}<br><br>
    <%--
        直接输出，会输出地址值，[Ljava.lang.String;@2bd0844
        输出数组中的元素，可以按照数组角标进行访问
      --%>
    <%--输出Person对象的phones数组属性：${person.phones}<br><br>--%>
    输出Person对象的phones数组个别属性：${person.phones[0]}<br><br>
    <%--
        输出Person对象的citiesList集合属性：[Beijing, Chongqing, Shanghai, chengdu]

    --%>
    输出Person对象的citiesList集合属性：${person.cities}<br><br>

    <%--  输出List集合中的个别元素 值，使用角标进行访问--%>
    输出Person对象的citiesList集合个别属性：${person.cities[0]}<br><br>
    <%--
        输出Map 集合属性
        输出Person对象的citiesList集合属性：{key1=value1, key2=value2, key3=value3, key4=value4}
    --%>
    输出Person对象的map集合属性：${person.map}<br><br>
    <%--
          输出map中的某一个key的值

    --%>
    输出Person对象的Map集合个别属性：${person.map["key1"]}<br><br>
    输出Person对象的Map集合个别属性：${person.map.key1}<br><br>

      <%-- 输出Person类里面没有 get方法的age 属性 就会报错--%>
<%--   ${person.age};--%>
</body>
</html>
